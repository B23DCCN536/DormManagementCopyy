package com.dorm.database;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

public class DatabaseManager {
    private static final String CONFIG_RESOURCE = "/config/application.properties";
    private static final Path DEFAULT_DOCS_DIRECTORY = Path.of("docs");
    
    private static final String SCHEMA_SCRIPT = "Tao_bang.sql";
    private static final String SEED_SCRIPT = "Nhap_du_lieu_large.sql";
    
    private static final String DEFAULT_HEALTH_TABLE = "TAI_KHOAN";
    private static DatabaseManager instance;
    
    private final String url;
    private final String driverClass;
    private final String username;
    private final String password;
    private final Path docsDirectory;
    private final String healthCheckTable;
    private Connection connection;
    
    private DatabaseManager() {
        Properties properties = loadConfiguration();
        this.url = properties.getProperty("database.url", "").trim();
        this.driverClass = properties.getProperty("database.driver", "").trim();
        this.username = properties.getProperty("database.username", "").trim();
        this.password = properties.getProperty("database.password", "").trim();
        String configuredDocsPath = properties.getProperty("database.docs.path", "").trim();
        this.docsDirectory = configuredDocsPath.isEmpty() ? DEFAULT_DOCS_DIRECTORY : Path.of(configuredDocsPath);
        this.healthCheckTable = properties.getProperty("database.health.table", "TAI_KHOAN").trim();
        
        validateConfiguration();
        connect();
        initializeSchemaIfNeeded();
    }
    
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    private Properties loadConfiguration() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getResourceAsStream(CONFIG_RESOURCE)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + CONFIG_RESOURCE);
                return properties;
            }
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Failed to read configuration: " + ex.getMessage());
        }
        return properties;
    }
    
    private void validateConfiguration() {
        if (this.url.isEmpty()) {
            throw new IllegalStateException("database.url must be configured for SQL Server connection");
        }
    }
    
    private void connect() {
        try {
            System.out.println("Connecting to database: " + this.url);
            DriverManager.setLoginTimeout(5); // 5 seconds timeout
            if (!this.driverClass.isEmpty()) {
                Class.forName(this.driverClass);
            }
            if (this.username.isEmpty()) {
                this.connection = DriverManager.getConnection(this.url);
            } else {
                this.connection = DriverManager.getConnection(this.url, this.username, this.password);
            }
            System.out.println("Connected to database successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Connection failed: " + e.getMessage());
            throw new IllegalStateException("Failed to establish database connection", e);
        }
    }
    
    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to validate SQL connection", e);
        }
        return this.connection;
    }
    
    public synchronized void initializeSchemaIfNeeded() {
        if (!tableExists(this.healthCheckTable) || !tableExists("ThongBao") || !tableExists("SuCo") || !tableExists("YeuCauGiaHan")) {
            executeDocScript(SCHEMA_SCRIPT);
        }
    }
    
    public synchronized void seedSampleData() {
        executeDocScript(SEED_SCRIPT);
    }
    
    public synchronized void executeDocScript(String scriptFileName) {
        Objects.requireNonNull(scriptFileName, "scriptFileName");
        Path scriptPath = this.docsDirectory.resolve(scriptFileName);
        if (!Files.exists(scriptPath)) {
            System.err.println("SQL script not found: " + scriptPath.toAbsolutePath());
            return;
        }
        try {
            System.out.println("Executing script: " + scriptFileName);
            String script = Files.readString(scriptPath, StandardCharsets.UTF_8);
            executeScript(script, scriptFileName);
            System.out.println("Successfully executed script: " + scriptFileName);
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to read SQL script: " + scriptPath, ex);
        }
    }
    
    private void executeScript(String scriptContent, String origin) {
        boolean previousAutoCommit = true;
        List<String> statements = parseStatements(scriptContent);
        if (statements.isEmpty()) {
            System.out.println("No executable statements found in " + origin);
            return;
        }
        Connection conn = getConnection();
        
        try {
            previousAutoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            
            try (Statement stmt = conn.createStatement()) {
                for (String sql : statements) {
                    stmt.addBatch(sql);
                }
                stmt.executeBatch();
            }
            
            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.addSuppressed(ex);
                throw new IllegalStateException("Failed to execute script and rollback", rollbackEx);
            }
            throw new IllegalStateException("Failed to execute script " + origin, ex);
        } finally {
            try {
                conn.setAutoCommit(previousAutoCommit);
            } catch (SQLException e) {
                System.err.println("Failed to restore auto-commit: " + e.getMessage());
            }
        }
    }
    
    private boolean tableExists(String tableName) {
        String normalized = Optional.ofNullable(tableName).map(name -> name.trim().toUpperCase(Locale.ROOT)).orElse("");
        if (normalized.isEmpty()) {
            return false;
        }
        try {
            DatabaseMetaData metaData = getConnection().getMetaData();
            try (ResultSet rs = metaData.getTables(null, null, normalized, null)) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to inspect existing tables", e);
        }
    }
    
    private List<String> parseStatements(String scriptContent) {
        List<String> statements = new ArrayList<>();
        String[] parts = scriptContent.split(";");
        for (String part : parts) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty()) {
                statements.add(trimmed);
            }
        }
        return statements;
    }

    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to close database connection: " + e.getMessage());
        }
    }
}

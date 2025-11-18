package com.dorm.database;

import com.dorm.database.DatabaseManager;

public final class DatabaseBootstrapper {
    public static void main(String[] args) {
        System.out.println("Starting Database Bootstrapper...");
        System.out.println("Ensure your database configuration in src/main/resources/config/application.properties is correct.");
        
        try {
            DatabaseManager manager = DatabaseManager.getInstance();
            manager.initializeSchemaIfNeeded();
            manager.seedSampleData();
            System.out.println("Database schema ensured and sample data applied successfully.");
            manager.closeConnection();
        } catch (Exception e) {
            System.err.println("Error during database initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

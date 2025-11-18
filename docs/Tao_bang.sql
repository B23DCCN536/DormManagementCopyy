-- Tạo bảng TaiKhoan
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'TaiKhoan')
BEGIN
    CREATE TABLE TaiKhoan (
        username VARCHAR(50) PRIMARY KEY,
        password VARCHAR(100),
        role VARCHAR(20) -- ADMIN, SINH_VIEN
    )
END;

-- Tạo bảng Admin
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Admin')
BEGIN
    CREATE TABLE Admin (
        username VARCHAR(50) PRIMARY KEY,
        hoTen NVARCHAR(50),
        sdt VARCHAR(15),
        email VARCHAR(100),
        FOREIGN KEY (username) REFERENCES TaiKhoan(username)
    )
END;

-- Tạo bảng SinhVien
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'SinhVien')
BEGIN
    CREATE TABLE SinhVien (
        maSinhVien VARCHAR(20) PRIMARY KEY,
        username VARCHAR(50) UNIQUE,
        hoTen NVARCHAR(50),
        ngaySinh DATE,
        gioiTinh NVARCHAR(10),
        lop VARCHAR(20),
        sdt VARCHAR(15),
        cccd VARCHAR(20),
        email VARCHAR(100),
        queQuan NVARCHAR(200),
        FOREIGN KEY (username) REFERENCES TaiKhoan(username)
    )
END;

-- Tạo bảng Toa
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Toa')
BEGIN
    CREATE TABLE Toa (
        maToa VARCHAR(10) PRIMARY KEY,
        tenToa NVARCHAR(50),
        ghiChu NVARCHAR(200)
    )
END;

-- Tạo bảng LoaiPhong
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'LoaiPhong')
BEGIN
    CREATE TABLE LoaiPhong (
        maLoaiPhong VARCHAR(10) PRIMARY KEY,
        tenLoaiPhong NVARCHAR(50),
        donGia DECIMAL(18,0),
        soNguoiToiDa INT
    )
END;

-- Tạo bảng Phong
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Phong')
BEGIN
    CREATE TABLE Phong (
        maPhong VARCHAR(10) PRIMARY KEY,
        maToa VARCHAR(10),
        maLoaiPhong VARCHAR(10),
        tenPhong NVARCHAR(50),
        soNguoiHienTai INT DEFAULT 0,
        gioiTinh NVARCHAR(10), -- NAM, NU
        trangThai VARCHAR(20), -- TRONG, DA_DU, DANG_SUA
        FOREIGN KEY (maToa) REFERENCES Toa(maToa),
        FOREIGN KEY (maLoaiPhong) REFERENCES LoaiPhong(maLoaiPhong)
    )
END;

-- Tạo bảng HopDong
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'HopDong')
BEGIN
    CREATE TABLE HopDong (
        maHopDong VARCHAR(20) PRIMARY KEY,
        maSinhVien VARCHAR(20),
        maPhong VARCHAR(10),
        giaPhong DECIMAL(18,0),
        ngayLap DATE,
        ngayBatDau DATE,
        ngayKetThuc DATE,
        trangThai VARCHAR(20),
        FOREIGN KEY (maSinhVien) REFERENCES SinhVien(maSinhVien),
        FOREIGN KEY (maPhong) REFERENCES Phong(maPhong)
    )
END;

-- Tạo bảng ChiSoDienNuoc
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'ChiSoDienNuoc')
BEGIN
    CREATE TABLE ChiSoDienNuoc (
        maChiSo VARCHAR(20) PRIMARY KEY,
        maPhong VARCHAR(10),
        thang INT,
        nam INT,
        soDien INT,
        soNuoc INT,
        donGiaDien DECIMAL(18,0),
        donGiaNuoc DECIMAL(18,0),
        thanhTienDien DECIMAL(18,0),
        thanhTienNuoc DECIMAL(18,0),
        FOREIGN KEY (maPhong) REFERENCES Phong(maPhong)
    )
END;

-- Tạo bảng HoaDon
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'HoaDon')
BEGIN
    CREATE TABLE HoaDon (
        maHoaDon VARCHAR(20) PRIMARY KEY,
        maPhong VARCHAR(10),
        maChiSo VARCHAR(20),
        thang INT,
        nam INT,
        ngayLap DATE,
        tienDien DECIMAL(18,0),
        tienNuoc DECIMAL(18,0),
        tongTien DECIMAL(18,0),
        trangThai VARCHAR(20), -- CHUA_TT, DA_TT
        ngayDong DATE,
        nguoiNopTien VARCHAR(20),
        FOREIGN KEY (maPhong) REFERENCES Phong(maPhong),
        FOREIGN KEY (maChiSo) REFERENCES ChiSoDienNuoc(maChiSo),
        FOREIGN KEY (nguoiNopTien) REFERENCES SinhVien(maSinhVien)
    )
END;

-- Tạo bảng ThongBao
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'ThongBao')
BEGIN
    CREATE TABLE ThongBao (
        maThongBao VARCHAR(20) PRIMARY KEY,
        tieuDe NVARCHAR(100),
        noiDung NVARCHAR(MAX),
        ngayDang DATE
    )
END;

-- Tạo bảng SuCo
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'SuCo')
BEGIN
    CREATE TABLE SuCo (
        maSuCo VARCHAR(20) PRIMARY KEY,
        maPhong VARCHAR(10),
        maSinhVien VARCHAR(20),
        tieuDe NVARCHAR(100),
        noiDung NVARCHAR(MAX),
        ngayBaoCao DATE,
        trangThai VARCHAR(20), -- CHO_XU_LY, DANG_XU_LY, DA_XU_LY
        FOREIGN KEY (maPhong) REFERENCES Phong(maPhong),
        FOREIGN KEY (maSinhVien) REFERENCES SinhVien(maSinhVien)
    )
END;

-- Tạo bảng YeuCauGiaHan
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'YeuCauGiaHan')
BEGIN
    CREATE TABLE YeuCauGiaHan (
        maYeuCau INT IDENTITY(1,1) PRIMARY KEY,
        maHopDong VARCHAR(20) NOT NULL,
        maSinhVien VARCHAR(20) NOT NULL,
        soThang INT NOT NULL,
        tongTien DECIMAL(18,0) NOT NULL,
        ngayGui DATE DEFAULT GETDATE(),
        trangThai VARCHAR(20) DEFAULT 'CHO_DUYET', -- CHO_DUYET, DA_DUYET, TU_CHOI
        FOREIGN KEY (maHopDong) REFERENCES HopDong(maHopDong),
        FOREIGN KEY (maSinhVien) REFERENCES SinhVien(maSinhVien)
    )
END;


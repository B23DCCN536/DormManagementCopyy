-- Xóa dữ liệu cũ để đảm bảo tính nhất quán
DELETE FROM YeuCauGiaHan;
DELETE FROM SuCo;
DELETE FROM ThongBao;
DELETE FROM HoaDon;
DELETE FROM ChiSoDienNuoc;
DELETE FROM HopDong;
DELETE FROM Phong;
DELETE FROM LoaiPhong;
DELETE FROM Toa;
DELETE FROM SinhVien;
DELETE FROM Admin;
DELETE FROM TaiKhoan;

-- 1. Nhập dữ liệu TaiKhoan
-- Admin
IF NOT EXISTS (SELECT * FROM TaiKhoan WHERE username = 'admin')
INSERT INTO TaiKhoan (username, password, role) VALUES ('admin', 'admin', 'ADMIN');

-- Sinh Vien (sv001 -> sv020)
DECLARE @i INT = 1;
DECLARE @username VARCHAR(20);
WHILE @i <= 20
BEGIN
    SET @username = 'sv' + RIGHT('000' + CAST(@i AS VARCHAR(3)), 3);
    IF NOT EXISTS (SELECT * FROM TaiKhoan WHERE username = @username)
        INSERT INTO TaiKhoan (username, password, role) VALUES (@username, '123', 'SINH_VIEN');
    SET @i = @i + 1;
END;

-- 2. Nhập dữ liệu Admin
IF NOT EXISTS (SELECT * FROM Admin WHERE username = 'admin')
INSERT INTO Admin (username, hoTen, sdt, email) VALUES ('admin', N'Nguyễn Quản Lý', '0901234567', 'admin@ptit.edu.vn');

-- 3. Nhập dữ liệu SinhVien
-- Nam (sv001 - sv010)
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN001')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN001', 'sv001', N'Nguyễn Văn An', '2002-01-15', N'NAM', 'D20CQCN01', '0912345678', '001202000001', 'an.nguyen@st.ptit.edu.vn', N'Hà Nội');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN002')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN002', 'sv002', N'Trần Văn Bình', '2002-02-20', N'NAM', 'D20CQCN01', '0912345679', '001202000002', 'binh.tran@st.ptit.edu.vn', N'Nam Định');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN003')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN003', 'sv003', N'Lê Văn Cường', '2002-03-25', N'NAM', 'D20CQCN02', '0912345680', '001202000003', 'cuong.le@st.ptit.edu.vn', N'Thái Bình');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN004')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN004', 'sv004', N'Phạm Văn Dũng', '2002-04-30', N'NAM', 'D20CQCN02', '0912345681', '001202000004', 'dung.pham@st.ptit.edu.vn', N'Hải Phòng');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN005')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN005', 'sv005', N'Hoàng Văn Em', '2002-05-05', N'NAM', 'D20CQCN03', '0912345682', '001202000005', 'em.hoang@st.ptit.edu.vn', N'Nghệ An');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN006')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN006', 'sv006', N'Đỗ Văn Hùng', '2002-06-10', N'NAM', 'D20CQCN03', '0912345683', '001202000006', 'hung.do@st.ptit.edu.vn', N'Thanh Hóa');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN007')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN007', 'sv007', N'Bùi Văn Khánh', '2002-07-15', N'NAM', 'D20CQCN04', '0912345684', '001202000007', 'khanh.bui@st.ptit.edu.vn', N'Hà Nam');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN008')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN008', 'sv008', N'Ngô Văn Long', '2002-08-20', N'NAM', 'D20CQCN04', '0912345685', '001202000008', 'long.ngo@st.ptit.edu.vn', N'Hưng Yên');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN009')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN009', 'sv009', N'Vũ Văn Minh', '2002-09-25', N'NAM', 'D20CQCN05', '0912345686', '001202000009', 'minh.vu@st.ptit.edu.vn', N'Bắc Ninh');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN010')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN010', 'sv010', N'Đặng Văn Nam', '2002-10-30', N'NAM', 'D20CQCN05', '0912345687', '001202000010', 'nam.dang@st.ptit.edu.vn', N'Bắc Giang');

-- Nu (sv011 - sv020)
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN011')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN011', 'sv011', N'Nguyễn Thị Lan', '2002-01-11', N'NU', 'D20CQCN01', '0912345688', '001202000011', 'lan.nguyen@st.ptit.edu.vn', N'Hà Nội');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN012')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN012', 'sv012', N'Trần Thị Mai', '2002-02-12', N'NU', 'D20CQCN01', '0912345689', '001202000012', 'mai.tran@st.ptit.edu.vn', N'Nam Định');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN013')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN013', 'sv013', N'Lê Thị Ngọc', '2002-03-13', N'NU', 'D20CQCN02', '0912345690', '001202000013', 'ngoc.le@st.ptit.edu.vn', N'Thái Bình');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN014')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN014', 'sv014', N'Phạm Thị Oanh', '2002-04-14', N'NU', 'D20CQCN02', '0912345691', '001202000014', 'oanh.pham@st.ptit.edu.vn', N'Hải Phòng');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN015')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN015', 'sv015', N'Hoàng Thị Phương', '2002-05-15', N'NU', 'D20CQCN03', '0912345692', '001202000015', 'phuong.hoang@st.ptit.edu.vn', N'Nghệ An');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN016')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN016', 'sv016', N'Đỗ Thị Quyên', '2002-06-16', N'NU', 'D20CQCN03', '0912345693', '001202000016', 'quyen.do@st.ptit.edu.vn', N'Thanh Hóa');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN017')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN017', 'sv017', N'Bùi Thị Thu', '2002-07-17', N'NU', 'D20CQCN04', '0912345694', '001202000017', 'thu.bui@st.ptit.edu.vn', N'Hà Nam');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN018')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN018', 'sv018', N'Ngô Thị Uyên', '2002-08-18', N'NU', 'D20CQCN04', '0912345695', '001202000018', 'uyen.ngo@st.ptit.edu.vn', N'Hưng Yên');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN019')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN019', 'sv019', N'Vũ Thị Vân', '2002-09-19', N'NU', 'D20CQCN05', '0912345696', '001202000019', 'van.vu@st.ptit.edu.vn', N'Bắc Ninh');
IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = 'B20DCCN020')
INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('B20DCCN020', 'sv020', N'Đặng Thị Xuyến', '2002-10-20', N'NU', 'D20CQCN05', '0912345697', '001202000020', 'xuyen.dang@st.ptit.edu.vn', N'Bắc Giang');

-- 4. Nhập dữ liệu Toa
IF NOT EXISTS (SELECT * FROM Toa WHERE maToa = 'A1')
INSERT INTO Toa (maToa, tenToa, ghiChu) VALUES ('A1', N'Nhà A1', N'Khu nhà nam');
IF NOT EXISTS (SELECT * FROM Toa WHERE maToa = 'A2')
INSERT INTO Toa (maToa, tenToa, ghiChu) VALUES ('A2', N'Nhà A2', N'Khu nhà nữ');
IF NOT EXISTS (SELECT * FROM Toa WHERE maToa = 'A3')
INSERT INTO Toa (maToa, tenToa, ghiChu) VALUES ('A3', N'Nhà A3', N'Khu dịch vụ VIP');

-- 5. Nhập dữ liệu LoaiPhong
IF NOT EXISTS (SELECT * FROM LoaiPhong WHERE maLoaiPhong = 'LP01')
INSERT INTO LoaiPhong (maLoaiPhong, tenLoaiPhong, donGia, soNguoiToiDa) VALUES ('LP01', N'Phòng thường 8 người', 800000, 8);
IF NOT EXISTS (SELECT * FROM LoaiPhong WHERE maLoaiPhong = 'LP02')
INSERT INTO LoaiPhong (maLoaiPhong, tenLoaiPhong, donGia, soNguoiToiDa) VALUES ('LP02', N'Phòng dịch vụ 4 người', 1500000, 4);
IF NOT EXISTS (SELECT * FROM LoaiPhong WHERE maLoaiPhong = 'LP03')
INSERT INTO LoaiPhong (maLoaiPhong, tenLoaiPhong, donGia, soNguoiToiDa) VALUES ('LP03', N'Phòng VIP 2 người', 3000000, 2);

-- 6. Nhập dữ liệu Phong
-- A1 (Nam)
IF NOT EXISTS (SELECT * FROM Phong WHERE maPhong = 'P101') INSERT INTO Phong (maPhong, maToa, maLoaiPhong, tenPhong, soNguoiHienTai, gioiTinh, trangThai) VALUES ('P101', 'A1', 'LP01', N'Phòng 101', 8, N'NAM', 'DA_DU');
IF NOT EXISTS (SELECT * FROM Phong WHERE maPhong = 'P102') INSERT INTO Phong (maPhong, maToa, maLoaiPhong, tenPhong, soNguoiHienTai, gioiTinh, trangThai) VALUES ('P102', 'A1', 'LP01', N'Phòng 102', 2, N'NAM', 'TRONG');
IF NOT EXISTS (SELECT * FROM Phong WHERE maPhong = 'P103') INSERT INTO Phong (maPhong, maToa, maLoaiPhong, tenPhong, soNguoiHienTai, gioiTinh, trangThai) VALUES ('P103', 'A1', 'LP02', N'Phòng 103', 0, N'NAM', 'TRONG');
IF NOT EXISTS (SELECT * FROM Phong WHERE maPhong = 'P104') INSERT INTO Phong (maPhong, maToa, maLoaiPhong, tenPhong, soNguoiHienTai, gioiTinh, trangThai) VALUES ('P104', 'A1', 'LP02', N'Phòng 104', 0, N'NAM', 'DANG_SUA_CHUA');

-- A2 (Nu)
IF NOT EXISTS (SELECT * FROM Phong WHERE maPhong = 'P201') INSERT INTO Phong (maPhong, maToa, maLoaiPhong, tenPhong, soNguoiHienTai, gioiTinh, trangThai) VALUES ('P201', 'A2', 'LP01', N'Phòng 201', 8, N'NU', 'DA_DU');
IF NOT EXISTS (SELECT * FROM Phong WHERE maPhong = 'P202') INSERT INTO Phong (maPhong, maToa, maLoaiPhong, tenPhong, soNguoiHienTai, gioiTinh, trangThai) VALUES ('P202', 'A2', 'LP01', N'Phòng 202', 2, N'NU', 'TRONG');
IF NOT EXISTS (SELECT * FROM Phong WHERE maPhong = 'P203') INSERT INTO Phong (maPhong, maToa, maLoaiPhong, tenPhong, soNguoiHienTai, gioiTinh, trangThai) VALUES ('P203', 'A2', 'LP02', N'Phòng 203', 0, N'NU', 'TRONG');

-- A3 (VIP)
IF NOT EXISTS (SELECT * FROM Phong WHERE maPhong = 'P301') INSERT INTO Phong (maPhong, maToa, maLoaiPhong, tenPhong, soNguoiHienTai, gioiTinh, trangThai) VALUES ('P301', 'A3', 'LP03', N'Phòng 301', 0, N'NAM', 'TRONG');
IF NOT EXISTS (SELECT * FROM Phong WHERE maPhong = 'P302') INSERT INTO Phong (maPhong, maToa, maLoaiPhong, tenPhong, soNguoiHienTai, gioiTinh, trangThai) VALUES ('P302', 'A3', 'LP03', N'Phòng 302', 0, N'NU', 'TRONG');

-- 7. Nhập dữ liệu HopDong
-- P101 (Full 8 Nam: sv001 -> sv008)
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD001') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD001', 'B20DCCN001', 'P101', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD002') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD002', 'B20DCCN002', 'P101', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD003') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD003', 'B20DCCN003', 'P101', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD004') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD004', 'B20DCCN004', 'P101', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD005') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD005', 'B20DCCN005', 'P101', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD006') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD006', 'B20DCCN006', 'P101', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD007') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD007', 'B20DCCN007', 'P101', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD008') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD008', 'B20DCCN008', 'P101', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');

-- P102 (2 Nam: sv009, sv010)
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD009') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD009', 'B20DCCN009', 'P102', 800000, '2024-08-20', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD010') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD010', 'B20DCCN010', 'P102', 800000, '2024-08-20', '2024-09-01', '2025-06-30', 'HIEU_LUC');

-- P201 (Full 8 Nu: sv011 -> sv018)
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD011') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD011', 'B20DCCN011', 'P201', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD012') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD012', 'B20DCCN012', 'P201', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD013') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD013', 'B20DCCN013', 'P201', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD014') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD014', 'B20DCCN014', 'P201', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD015') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD015', 'B20DCCN015', 'P201', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD016') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD016', 'B20DCCN016', 'P201', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD017') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD017', 'B20DCCN017', 'P201', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD018') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD018', 'B20DCCN018', 'P201', 800000, '2024-08-15', '2024-09-01', '2025-06-30', 'HIEU_LUC');

-- P202 (2 Nu: sv019, sv020)
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD019') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD019', 'B20DCCN019', 'P202', 800000, '2024-08-20', '2024-09-01', '2025-06-30', 'HIEU_LUC');
IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = 'HD020') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('HD020', 'B20DCCN020', 'P202', 800000, '2024-08-20', '2024-09-01', '2025-06-30', 'HIEU_LUC');

-- 8. Nhập dữ liệu ChiSoDienNuoc
-- P101 (Thang 9, 10)
IF NOT EXISTS (SELECT * FROM ChiSoDienNuoc WHERE maChiSo = 'CS_P101_09_2024') INSERT INTO ChiSoDienNuoc (maChiSo, maPhong, thang, nam, soDien, soNuoc, donGiaDien, donGiaNuoc, thanhTienDien, thanhTienNuoc) VALUES ('CS_P101_09_2024', 'P101', 9, 2024, 200, 30, 3000, 8000, 600000, 240000);
IF NOT EXISTS (SELECT * FROM ChiSoDienNuoc WHERE maChiSo = 'CS_P101_10_2024') INSERT INTO ChiSoDienNuoc (maChiSo, maPhong, thang, nam, soDien, soNuoc, donGiaDien, donGiaNuoc, thanhTienDien, thanhTienNuoc) VALUES ('CS_P101_10_2024', 'P101', 10, 2024, 180, 28, 3000, 8000, 540000, 224000);

-- P201 (Thang 9, 10)
IF NOT EXISTS (SELECT * FROM ChiSoDienNuoc WHERE maChiSo = 'CS_P201_09_2024') INSERT INTO ChiSoDienNuoc (maChiSo, maPhong, thang, nam, soDien, soNuoc, donGiaDien, donGiaNuoc, thanhTienDien, thanhTienNuoc) VALUES ('CS_P201_09_2024', 'P201', 9, 2024, 220, 35, 3000, 8000, 660000, 280000);
IF NOT EXISTS (SELECT * FROM ChiSoDienNuoc WHERE maChiSo = 'CS_P201_10_2024') INSERT INTO ChiSoDienNuoc (maChiSo, maPhong, thang, nam, soDien, soNuoc, donGiaDien, donGiaNuoc, thanhTienDien, thanhTienNuoc) VALUES ('CS_P201_10_2024', 'P201', 10, 2024, 200, 30, 3000, 8000, 600000, 240000);

-- 9. Nhập dữ liệu HoaDon
-- P101
IF NOT EXISTS (SELECT * FROM HoaDon WHERE maHoaDon = 'HD_P101_09_2024') INSERT INTO HoaDon (maHoaDon, maPhong, maChiSo, thang, nam, ngayLap, tienDien, tienNuoc, tongTien, trangThai, ngayDong, nguoiNopTien) VALUES ('HD_P101_09_2024', 'P101', 'CS_P101_09_2024', 9, 2024, '2024-10-01', 600000, 240000, 840000, 'DA_TT', '2024-10-05', 'B20DCCN001');
IF NOT EXISTS (SELECT * FROM HoaDon WHERE maHoaDon = 'HD_P101_10_2024') INSERT INTO HoaDon (maHoaDon, maPhong, maChiSo, thang, nam, ngayLap, tienDien, tienNuoc, tongTien, trangThai, ngayDong, nguoiNopTien) VALUES ('HD_P101_10_2024', 'P101', 'CS_P101_10_2024', 10, 2024, '2024-11-01', 540000, 224000, 764000, 'CHUA_TT', NULL, NULL);

-- P201
IF NOT EXISTS (SELECT * FROM HoaDon WHERE maHoaDon = 'HD_P201_09_2024') INSERT INTO HoaDon (maHoaDon, maPhong, maChiSo, thang, nam, ngayLap, tienDien, tienNuoc, tongTien, trangThai, ngayDong, nguoiNopTien) VALUES ('HD_P201_09_2024', 'P201', 'CS_P201_09_2024', 9, 2024, '2024-10-01', 660000, 280000, 940000, 'DA_TT', '2024-10-06', 'B20DCCN011');
IF NOT EXISTS (SELECT * FROM HoaDon WHERE maHoaDon = 'HD_P201_10_2024') INSERT INTO HoaDon (maHoaDon, maPhong, maChiSo, thang, nam, ngayLap, tienDien, tienNuoc, tongTien, trangThai, ngayDong, nguoiNopTien) VALUES ('HD_P201_10_2024', 'P201', 'CS_P201_10_2024', 10, 2024, '2024-11-01', 600000, 240000, 840000, 'CHUA_TT', NULL, NULL);

-- 10. Nhập dữ liệu ThongBao
IF NOT EXISTS (SELECT * FROM ThongBao WHERE maThongBao = 'TB001')
INSERT INTO ThongBao (maThongBao, tieuDe, noiDung, ngayDang) VALUES ('TB001', N'Chào mừng năm học mới 2024-2025', N'Ban quản lý KTX chào mừng các bạn sinh viên quay trở lại trường. Chúc các bạn một năm học mới nhiều thành công.', '2024-08-15');

IF NOT EXISTS (SELECT * FROM ThongBao WHERE maThongBao = 'TB002')
INSERT INTO ThongBao (maThongBao, tieuDe, noiDung, ngayDang) VALUES ('TB002', N'Thông báo nộp tiền điện nước tháng 9', N'Đề nghị các phòng trưởng các phòng nộp tiền điện nước tháng 9 trước ngày 10/10/2024. Quá hạn sẽ bị cắt điện.', '2024-10-01');

IF NOT EXISTS (SELECT * FROM ThongBao WHERE maThongBao = 'TB003')
INSERT INTO ThongBao (maThongBao, tieuDe, noiDung, ngayDang) VALUES ('TB003', N'Lịch phun thuốc muỗi', N'Nhà trường sẽ tiến hành phun thuốc muỗi toàn bộ khu KTX vào sáng Chủ Nhật ngày 20/10/2024. Đề nghị các bạn sinh viên dọn dẹp phòng ở gọn gàng.', '2024-10-15');

IF NOT EXISTS (SELECT * FROM ThongBao WHERE maThongBao = 'TB004')
INSERT INTO ThongBao (maThongBao, tieuDe, noiDung, ngayDang) VALUES ('TB004', N'Đăng ký ở lại hè 2025', N'Sinh viên có nhu cầu ở lại hè 2025 vui lòng đăng ký tại văn phòng BQL trước ngày 30/05/2025.', '2024-11-01');

-- 11. Nhập dữ liệu SuCo
IF NOT EXISTS (SELECT * FROM SuCo WHERE maSuCo = 'SC001')
INSERT INTO SuCo (maSuCo, maPhong, maSinhVien, tieuDe, noiDung, ngayBaoCao, trangThai) VALUES ('SC001', 'P101', 'B20DCCN001', N'Hỏng bóng đèn', N'Bóng đèn tuýp ở giữa phòng bị cháy, cần thay mới.', '2024-09-10', 'DA_XU_LY');

IF NOT EXISTS (SELECT * FROM SuCo WHERE maSuCo = 'SC002')
INSERT INTO SuCo (maSuCo, maPhong, maSinhVien, tieuDe, noiDung, ngayBaoCao, trangThai) VALUES ('SC002', 'P201', 'B20DCCN011', N'Vòi nước rò rỉ', N'Vòi nước trong nhà vệ sinh bị rò rỉ nước liên tục.', '2024-10-05', 'DANG_XU_LY');

IF NOT EXISTS (SELECT * FROM SuCo WHERE maSuCo = 'SC003')
INSERT INTO SuCo (maSuCo, maPhong, maSinhVien, tieuDe, noiDung, ngayBaoCao, trangThai) VALUES ('SC003', 'P102', 'B20DCCN009', N'Cửa sổ kẹt', N'Cửa sổ bên trái không đóng được.', '2024-11-02', 'CHO_XU_LY');

-- 12. Nhập dữ liệu YeuCauGiaHan
IF NOT EXISTS (SELECT * FROM YeuCauGiaHan WHERE maHopDong = 'HD001')
INSERT INTO YeuCauGiaHan (maHopDong, maSinhVien, soThang, tongTien, ngayGui, trangThai) VALUES ('HD001', 'B20DCCN001', 3, 2400000, '2024-11-15', 'CHO_DUYET');

IF NOT EXISTS (SELECT * FROM YeuCauGiaHan WHERE maHopDong = 'HD011')
INSERT INTO YeuCauGiaHan (maHopDong, maSinhVien, soThang, tongTien, ngayGui, trangThai) VALUES ('HD011', 'B20DCCN011', 5, 4000000, '2024-11-20', 'DA_DUYET');

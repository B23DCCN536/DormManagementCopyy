import random
import datetime

# Configuration
NUM_STUDENTS = 200
START_YEAR = 2024
CURRENT_YEAR = 2025

# Data Arrays
HO = ["Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Huỳnh", "Phan", "Vũ", "Võ", "Đặng", "Bùi", "Đỗ", "Hồ", "Ngô", "Dương", "Lý"]
TEN_DEM = ["Văn", "Thị", "Hữu", "Minh", "Ngọc", "Thanh", "Quốc", "Gia", "Đức", "Xuân", "Thu", "Phương", "Anh", "Tuấn"]
TEN = ["An", "Bình", "Cường", "Dũng", "Em", "Hùng", "Khánh", "Long", "Minh", "Nam", "Lan", "Mai", "Ngọc", "Oanh", "Phương", "Quyên", "Thu", "Uyên", "Vân", "Xuyến", "Tâm", "Thảo", "Trang", "Hương", "Hạnh", "Phúc", "Lộc", "Thọ", "Kiên", "Vinh"]
QUE_QUAN = ["Hà Nội", "Hồ Chí Minh", "Đà Nẵng", "Hải Phòng", "Cần Thơ", "Nam Định", "Thái Bình", "Nghệ An", "Thanh Hóa", "Hà Nam", "Hưng Yên", "Bắc Ninh", "Bắc Giang", "Quảng Ninh", "Hải Dương", "Vĩnh Phúc", "Phú Thọ"]
LOP = ["D20CQCN01", "D20CQCN02", "D20CQCN03", "D20CQCN04", "D20CQCN05", "D21CQCN01", "D21CQCN02"]

def get_random_name(gender):
    ho = random.choice(HO)
    ten = random.choice(TEN)
    dem = random.choice(TEN_DEM)
    if gender == "NAM":
        while dem == "Thị": dem = random.choice(TEN_DEM)
    else:
        if random.random() < 0.7: dem = "Thị"
    return f"{ho} {dem} {ten}"

def get_random_date(start_year, end_year):
    start = datetime.date(start_year, 1, 1)
    end = datetime.date(end_year, 12, 31)
    return start + datetime.timedelta(days=random.randint(0, (end - start).days))

def get_random_phone():
    return "09" + "".join([str(random.randint(0, 9)) for _ in range(8)])

def get_random_cccd():
    return "00120" + "".join([str(random.randint(0, 9)) for _ in range(7)])

sql_statements = []

# Clean up
sql_statements.append("-- Xóa dữ liệu cũ")
sql_statements.append("DELETE FROM YeuCauGiaHan;")
sql_statements.append("DELETE FROM SuCo;")
sql_statements.append("DELETE FROM ThongBao;")
sql_statements.append("DELETE FROM HoaDon;")
sql_statements.append("DELETE FROM ChiSoDienNuoc;")
sql_statements.append("DELETE FROM HopDong;")
sql_statements.append("DELETE FROM Phong;")
sql_statements.append("DELETE FROM LoaiPhong;")
sql_statements.append("DELETE FROM Toa;")
sql_statements.append("DELETE FROM SinhVien;")
sql_statements.append("DELETE FROM Admin;")
sql_statements.append("DELETE FROM TaiKhoan;")
sql_statements.append("")

# 1. TaiKhoan & Admin
sql_statements.append("-- 1. TaiKhoan & Admin")
sql_statements.append("IF NOT EXISTS (SELECT * FROM TaiKhoan WHERE username = 'admin') INSERT INTO TaiKhoan (username, password, role) VALUES ('admin', 'admin', 'ADMIN');")
sql_statements.append("IF NOT EXISTS (SELECT * FROM Admin WHERE username = 'admin') INSERT INTO Admin (username, hoTen, sdt, email) VALUES ('admin', N'Nguyễn Quản Lý', '0901234567', 'admin@ptit.edu.vn');")

# 2. Toa
sql_statements.append("-- 2. Toa")
toas = [
    ('A1', 'Nhà A1', 'Khu nhà nam'),
    ('A2', 'Nhà A2', 'Khu nhà nữ'),
    ('A3', 'Nhà A3', 'Khu dịch vụ VIP')
]
for t in toas:
    sql_statements.append(f"IF NOT EXISTS (SELECT * FROM Toa WHERE maToa = '{t[0]}') INSERT INTO Toa (maToa, tenToa, ghiChu) VALUES ('{t[0]}', N'{t[1]}', N'{t[2]}');")

# 3. LoaiPhong
sql_statements.append("-- 3. LoaiPhong")
loai_phongs = [
    ('LP01', 'Phòng thường 8 người', 800000, 8),
    ('LP02', 'Phòng dịch vụ 4 người', 1500000, 4),
    ('LP03', 'Phòng VIP 2 người', 3000000, 2)
]
for lp in loai_phongs:
    sql_statements.append(f"IF NOT EXISTS (SELECT * FROM LoaiPhong WHERE maLoaiPhong = '{lp[0]}') INSERT INTO LoaiPhong (maLoaiPhong, tenLoaiPhong, donGia, soNguoiToiDa) VALUES ('{lp[0]}', N'{lp[1]}', {lp[2]}, {lp[3]});")

# 4. Phong
sql_statements.append("-- 4. Phong")
generated_rooms = []

def generate_rooms_for_building(building_code, start_floor, num_floors, rooms_per_floor, gender_default, lp_default):
    for f in range(start_floor, start_floor + num_floors):
        for r in range(1, rooms_per_floor + 1):
            ma_phong = f"P{f}{r:02d}"
            lp = lp_default
            # Randomly upgrade some rooms
            if building_code == 'A3':
                lp = 'LP03'
            elif r > 8:
                lp = 'LP02'
            
            gender = gender_default
            if building_code == 'A3':
                gender = 'NAM' if r <= rooms_per_floor/2 else 'NU'
            
            room = {
                'maPhong': ma_phong,
                'maToa': building_code,
                'maLoaiPhong': lp,
                'tenPhong': f"Phòng {f}{r:02d}",
                'soNguoiHienTai': 0,
                'gioiTinh': gender,
                'trangThai': 'TRONG',
                'capacity': next(x[3] for x in loai_phongs if x[0] == lp)
            }
            generated_rooms.append(room)
            sql_statements.append(f"IF NOT EXISTS (SELECT * FROM Phong WHERE maPhong = '{room['maPhong']}') INSERT INTO Phong (maPhong, maToa, maLoaiPhong, tenPhong, soNguoiHienTai, gioiTinh, trangThai) VALUES ('{room['maPhong']}', '{room['maToa']}', '{room['maLoaiPhong']}', N'{room['tenPhong']}', 0, N'{room['gioiTinh']}', 'TRONG');")

generate_rooms_for_building('A1', 1, 3, 10, 'NAM', 'LP01') # Floors 1,2,3. Rooms 101-310.
generate_rooms_for_building('A2', 4, 3, 10, 'NU', 'LP01')  # Floors 4,5,6. Rooms 401-610.
generate_rooms_for_building('A3', 7, 2, 10, 'NAM', 'LP03') # Floors 7,8. Rooms 701-810.

# 5. SinhVien & TaiKhoan
sql_statements.append("-- 5. SinhVien & TaiKhoan")
students = []
for i in range(1, NUM_STUDENTS + 1):
    ma_sv = f"B20DCCN{i:03d}"
    username = ma_sv # Use Student ID as username
    gender = "NAM" if random.random() < 0.5 else "NU"
    name = get_random_name(gender)
    dob = get_random_date(2000, 2003)
    lop = random.choice(LOP)
    phone = get_random_phone()
    cccd = get_random_cccd()
    email = f"{username}@st.ptit.edu.vn"
    que = random.choice(QUE_QUAN)
    
    students.append({
        'maSinhVien': ma_sv,
        'username': username,
        'hoTen': name,
        'gioiTinh': gender
    })
    
    sql_statements.append(f"IF NOT EXISTS (SELECT * FROM TaiKhoan WHERE username = '{username}') INSERT INTO TaiKhoan (username, password, role) VALUES ('{username}', '123', 'SINH_VIEN');")
    sql_statements.append(f"IF NOT EXISTS (SELECT * FROM SinhVien WHERE maSinhVien = '{ma_sv}') INSERT INTO SinhVien (maSinhVien, username, hoTen, ngaySinh, gioiTinh, lop, sdt, cccd, email, queQuan) VALUES ('{ma_sv}', '{username}', N'{name}', '{dob}', N'{gender}', '{lop}', '{phone}', '{cccd}', '{email}', N'{que}');")

# 6. HopDong
sql_statements.append("-- 6. HopDong")
contracts = []
contract_counter = 1

# Current date for reference: Nov 2025
# We want some contracts to be expired (End < Nov 2025) and some valid (End > Nov 2025)
# Expired: Start ~Sep 2024 -> End ~June 2025
# Valid: Start ~Sep 2025 -> End ~June 2026

for sv in students:
    # Find room
    suitable_rooms = [r for r in generated_rooms if r['gioiTinh'] == sv['gioiTinh'] and r['soNguoiHienTai'] < r['capacity']]
    if suitable_rooms:
        room = random.choice(suitable_rooms)
        room['soNguoiHienTai'] += 1
        if room['soNguoiHienTai'] == room['capacity']:
            room['trangThai'] = 'DA_DU'
        
        ma_hd = f"HD{contract_counter:03d}"
        contract_counter += 1
        gia = next(x[2] for x in loai_phongs if x[0] == room['maLoaiPhong'])
        
        # Randomly decide if this contract is expired or valid
        is_expired = random.random() < 0.4 # 40% expired
        
        if is_expired:
            # Expired contract (2024-2025)
            ngay_lap = get_random_date(2024, 2024)
            ngay_bd = ngay_lap + datetime.timedelta(days=random.randint(1, 15))
            ngay_kt = datetime.date(2025, 6, 30) # End of academic year 2025
            trang_thai = 'HET_HAN'
        else:
            # Valid contract (2025-2026)
            ngay_lap = get_random_date(2025, 2025)
            # Ensure start date is not in future relative to "now" (Nov 2025) if possible, or just recent
            # Let's say they started in Aug/Sep 2025
            ngay_lap = datetime.date(2025, random.randint(8, 10), random.randint(1, 28))
            ngay_bd = ngay_lap + datetime.timedelta(days=random.randint(1, 7))
            ngay_kt = datetime.date(2026, 6, 30) # End of academic year 2026
            trang_thai = 'HIEU_LUC'

        contracts.append({
            'maHopDong': ma_hd,
            'maSinhVien': sv['maSinhVien'],
            'maPhong': room['maPhong'],
            'giaPhong': gia,
            'ngayLap': ngay_lap,
            'ngayBatDau': ngay_bd,
            'ngayKetThuc': ngay_kt,
            'trangThai': trang_thai
        })
        
        sql_statements.append(f"IF NOT EXISTS (SELECT * FROM HopDong WHERE maHopDong = '{ma_hd}') INSERT INTO HopDong (maHopDong, maSinhVien, maPhong, giaPhong, ngayLap, ngayBatDau, ngayKetThuc, trangThai) VALUES ('{ma_hd}', '{sv['maSinhVien']}', '{room['maPhong']}', {gia}, '{ngay_lap}', '{ngay_bd}', '{ngay_kt}', '{trang_thai}');")

# Update Room Status in SQL
sql_statements.append("-- Update Room Status")
for r in generated_rooms:
    if r['soNguoiHienTai'] > 0:
        status = 'DA_DU' if r['soNguoiHienTai'] >= r['capacity'] else 'TRONG'
        sql_statements.append(f"UPDATE Phong SET soNguoiHienTai = {r['soNguoiHienTai']}, trangThai = '{status}' WHERE maPhong = '{r['maPhong']}';")

# 7. ChiSoDienNuoc & HoaDon
sql_statements.append("-- 7. ChiSoDienNuoc & HoaDon")
# Generate for Sep, Oct 2025 for occupied rooms (Valid contracts)
months = [9, 10]
year = 2025
occupied_rooms = [r for r in generated_rooms if r['soNguoiHienTai'] > 0]

for r in occupied_rooms:
    # Check if room has valid contracts currently
    # For simplicity, just generate bills for all occupied rooms
    for m in months:
        ma_cs = f"CS_{r['maPhong']}_{m:02d}_{year}"
        so_dien = random.randint(100, 300)
        so_nuoc = random.randint(10, 40)
        dg_dien = 3000
        dg_nuoc = 8000
        tt_dien = so_dien * dg_dien
        tt_nuoc = so_nuoc * dg_nuoc
        
        sql_statements.append(f"IF NOT EXISTS (SELECT * FROM ChiSoDienNuoc WHERE maChiSo = '{ma_cs}') INSERT INTO ChiSoDienNuoc (maChiSo, maPhong, thang, nam, soDien, soNuoc, donGiaDien, donGiaNuoc, thanhTienDien, thanhTienNuoc) VALUES ('{ma_cs}', '{r['maPhong']}', {m}, {year}, {so_dien}, {so_nuoc}, {dg_dien}, {dg_nuoc}, {tt_dien}, {tt_nuoc});")
        
        # HoaDon
        ma_hd = f"HD_{r['maPhong']}_{m:02d}_{year}"
        tong_tien = tt_dien + tt_nuoc
        ngay_lap = datetime.date(year, m, 25) + datetime.timedelta(days=random.randint(1, 5))
        
        # Randomly paid or not
        is_paid = random.random() < 0.8
        trang_thai = 'DA_TT' if is_paid else 'CHUA_TT'
        ngay_dong = "NULL"
        nguoi_nop = "NULL"
        
        if is_paid:
            ngay_dong = f"'{ngay_lap + datetime.timedelta(days=random.randint(1, 5))}'"
            # Find a student in this room
            room_contracts = [c for c in contracts if c['maPhong'] == r['maPhong']]
            if room_contracts:
                nguoi_nop = f"'{room_contracts[0]['maSinhVien']}'"
        
        sql_statements.append(f"IF NOT EXISTS (SELECT * FROM HoaDon WHERE maHoaDon = '{ma_hd}') INSERT INTO HoaDon (maHoaDon, maPhong, maChiSo, thang, nam, ngayLap, tienDien, tienNuoc, tongTien, trangThai, ngayDong, nguoiNopTien) VALUES ('{ma_hd}', '{r['maPhong']}', '{ma_cs}', {m}, {year}, '{ngay_lap}', {tt_dien}, {tt_nuoc}, {tong_tien}, '{trang_thai}', {ngay_dong}, {nguoi_nop});")

# 8. ThongBao
sql_statements.append("-- 8. ThongBao")
thong_baos = [
    ('TB001', 'Chào mừng năm học mới 2025-2026', 'Ban quản lý KTX chào mừng các bạn sinh viên quay trở lại trường. Chúc các bạn một năm học mới nhiều thành công.', '2025-08-15'),
    ('TB002', 'Thông báo nộp tiền điện nước tháng 9/2025', 'Đề nghị các phòng trưởng các phòng nộp tiền điện nước tháng 9 trước ngày 10/10/2025. Quá hạn sẽ bị cắt điện.', '2025-10-01'),
    ('TB003', 'Lịch phun thuốc muỗi đợt 1', 'Nhà trường sẽ tiến hành phun thuốc muỗi toàn bộ khu KTX vào sáng Chủ Nhật ngày 20/10/2025. Đề nghị các bạn sinh viên dọn dẹp phòng ở gọn gàng.', '2025-10-15'),
    ('TB004', 'Đăng ký ở lại hè 2025', 'Sinh viên có nhu cầu ở lại hè 2025 vui lòng đăng ký tại văn phòng BQL trước ngày 30/05/2025.', '2025-05-01'),
    ('TB005', 'Thông báo cắt điện bảo trì trạm biến áp', 'Để phục vụ công tác bảo trì, KTX sẽ cắt điện toàn khu A1, A2 từ 8h00 đến 17h00 ngày 05/11/2025.', '2025-11-01'),
    ('TB006', 'Giải bóng đá KTX mở rộng', 'Đoàn thanh niên tổ chức giải bóng đá KTX. Các phòng đăng ký tham gia tại phòng trực ban trước ngày 15/11/2025.', '2025-11-05'),
    ('TB007', 'Quy định về an toàn phòng cháy chữa cháy', 'Nghiêm cấm sinh viên đun nấu trong phòng. Vi phạm sẽ bị xử lý kỷ luật.', '2025-09-05'),
    ('TB008', 'Thông báo tuyển cộng tác viên tự quản', 'Ban quản lý tuyển 10 sinh viên tham gia đội tự quản KTX. Quyền lợi: Cộng điểm rèn luyện và ưu tiên xét duyệt phòng.', '2025-09-10'),
    ('TB009', 'Lịch thi học kỳ 1 năm học 2025-2026', 'Lịch thi dự kiến bắt đầu từ 15/12/2025. Sinh viên chú ý ôn tập.', '2025-11-20'),
    ('TB010', 'Thông báo về việc giữ gìn vệ sinh chung', 'Đề nghị sinh viên đổ rác đúng nơi quy định, không vứt rác ra hành lang.', '2025-09-20')
]
for tb in thong_baos:
    sql_statements.append(f"IF NOT EXISTS (SELECT * FROM ThongBao WHERE maThongBao = '{tb[0]}') INSERT INTO ThongBao (maThongBao, tieuDe, noiDung, ngayDang) VALUES ('{tb[0]}', N'{tb[1]}', N'{tb[2]}', '{tb[3]}');")

# 9. SuCo
sql_statements.append("-- 9. SuCo")
su_cos = [
    ('Hỏng bóng đèn', 'Bóng đèn bị cháy'),
    ('Hỏng vòi nước', 'Vòi nước rò rỉ'),
    ('Hỏng khóa cửa', 'Khóa cửa bị kẹt'),
    ('Mất mạng', 'Wifi không kết nối được'),
    ('Tắc bồn cầu', 'Bồn cầu bị tắc'),
    ('Hỏng quạt trần', 'Quạt trần không quay')
]
for i in range(1, 21): # 20 incidents
    ma_sc = f"SC{i:03d}"
    contract = random.choice(contracts)
    sc_type = random.choice(su_cos)
    ngay_bc = get_random_date(2025, 2025)
    status = random.choice(['DA_XU_LY', 'DANG_XU_LY', 'CHO_XU_LY'])
    
    sql_statements.append(f"IF NOT EXISTS (SELECT * FROM SuCo WHERE maSuCo = '{ma_sc}') INSERT INTO SuCo (maSuCo, maPhong, maSinhVien, tieuDe, noiDung, ngayBaoCao, trangThai) VALUES ('{ma_sc}', '{contract['maPhong']}', '{contract['maSinhVien']}', N'{sc_type[0]}', N'{sc_type[1]}', '{ngay_bc}', '{status}');")

# 10. YeuCauGiaHan
sql_statements.append("-- 10. YeuCauGiaHan")
for i in range(1, 11): # 10 requests
    contract = random.choice(contracts)
    months = random.randint(1, 6)
    total = months * contract['giaPhong']
    ngay_gui = get_random_date(2024, 2024)
    status = random.choice(['CHO_DUYET', 'DA_DUYET'])
    
    sql_statements.append(f"INSERT INTO YeuCauGiaHan (maHopDong, maSinhVien, soThang, tongTien, ngayGui, trangThai) VALUES ('{contract['maHopDong']}', '{contract['maSinhVien']}', {months}, {total}, '{ngay_gui}', '{status}');")

# Write to file
with open('d:\\DormManagement\\docs\\Nhap_du_lieu_large.sql', 'w', encoding='utf-8') as f:
    f.write('\n'.join(sql_statements))

print("Done")

# Hệ Thống Quản Lý Ký Túc Xá PTIT

## Giới Thiệu Dự Án
Hệ thống Quản lý Ký túc xá PTIT là một ứng dụng Winform được thiết kế để hỗ trợ việc quản lý và vận hành ký túc xá PTIT một cách hiệu quả và chuyên nghiệp. Ứng dụng giúp quản lý viên và nhân viên ký túc xá thực hiện các công việc như: đăng ký phòng, theo dõi hợp đồng thuê, quản lý thông tin sinh viên, tính toán hóa đơn và quản lý các dịch vụ trong ký túc xá.

Dự án được xây dựng bằng ngôn ngữ Java với giao diện JavaFX thân thiện, sử dụng cơ sở dữ liệu SQL để lưu trữ thông tin và tuân theo mô hình thiết kế Model-View-Controller (MVC) để đảm bảo tính bảo trì và mở rộng cao.

## Cấu Trúc Thư Mục
```
DormManagement/
├── src/
│   └── main/
│       ├── java/
│       │   ├── Main.java                 # File chính chạy ứng dụng
│       │   └── model/
│       │       └── entity/               # Các class thực thể
│       │           ├── HopDong.java      # Quản lý hợp đồng
│       │           ├── SinhVien.java     # Thông tin sinh viên
│       │           ├── Phong.java        # Quản lý phòng
│       │           ├── DichVu.java       # Dịch vụ ký túc xá
│       │           └── HoaDon.java       # Hóa đơn thanh toán
│       └── resources/                    # Tài nguyên dự án
├── .gitignore                           # File bỏ qua Git
└── README.md                            # Tài liệu dự án
```

## Tính Năng Chính
- **Quản lý sinh viên**: Thêm, sửa, xóa thông tin sinh viên
- **Quản lý phòng**: Theo dõi tình trạng và thông tin phòng ở
- **Quản lý hợp đồng**: Xử lý hợp đồng thuê phòng của sinh viên
- **Quản lý dịch vụ**: Theo dõi các dịch vụ trong ký túc xá
- **Quản lý hóa đơn**: Tính toán và theo dõi các khoản thanh toán
- Giao diện thân thiện với người dùng được xây dựng bằng JavaFX
- Kết nối cơ sở dữ liệu để quản lý dữ liệu hiệu quả
- Kiến trúc MVC để tổ chức tốt hơn và khả năng mở rộng

## Công Nghệ Sử Dụng
- **Ngôn ngữ**: Java
- **Giao diện**: JavaFX
- **Cơ sở dữ liệu**: SQL
- **Kiến trúc**: Model-View-Controller (MVC)
- **IDE**: Visual Studio Code

## Hướng Dẫn Cài Đặt
1. **Clone repository**:
   ```bash
   git clone https://github.com/qkhanhbe/DormManagement.git
   cd DormManagement
   ```

2. **Cài đặt Java Development Kit (JDK)**:
   - Đảm bảo đã cài đặt JDK 8 trở lên
   - Thiết lập biến môi trường JAVA_HOME

3. **Cài đặt JavaFX**:
   - Tải xuống JavaFX SDK từ trang chủ Oracle
   - Thiết lập đường dẫn trong IDE

4. **Thiết lập cơ sở dữ liệu**:
   - Cài đặt MySQL/PostgreSQL
   - Tạo database với tên `dormmanagement`
   - Import schema từ file SQL (nếu có)

5. **Chạy ứng dụng**:
   ```bash
   javac src/main/java/Main.java src/main/java/model/entity/*.java
   java -cp src/main/java Main
   ```

## Hướng Dẫn Sử Dụng
1. Khởi động ứng dụng
2. Sử dụng các chức năng để:
   - Quản lý thông tin sinh viên
   - Xem và cập nhật tình trạng phòng
   - Tạo và theo dõi hợp đồng
   - Quản lý dịch vụ và hóa đơn

## Đóng Góp
Mọi đóng góp đều được chào đón! Vui lòng:
1. Fork dự án
2. Tạo feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit thay đổi (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Mở Pull Request

## Giấy Phép
Dự án này được cấp phép theo giấy phép MIT. Xem file [LICENSE](LICENSE) để biết thêm chi tiết.

---
*Dự án được phát triển trong khuôn khổ môn học tại Học viện Công nghệ Bưu chính Viễn thông (PTIT)*
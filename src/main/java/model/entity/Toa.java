package model.entity;

public class Toa {
    private String TenToa;
    private int SoTang;
    private int SoPhong;
    private int SoNguoiMoiPhong;
    public Toa(String tenToa, int soTang, int soPhong, int SoNguoiMoiPhong) {
        this.TenToa = tenToa;
        this.SoTang = soTang;
        this.SoPhong = soPhong;
        this.SoNguoiMoiPhong = SoNguoiMoiPhong;
    }

    // Getters
    public String getTenToa() {
        return TenToa;
    }
    public int getSoTang() {
        return SoTang;
    }
    public int getSoPhong() {
        return SoPhong;
    }
    public int getSoNguoiMoiPhong() {
        return SoNguoiMoiPhong;
    }

    // Setters
    public void setTenToa(String tenToa) {
        if (!tenToa.equals("")) this.TenToa = tenToa;
    }
    public void setSoTang(int soTang) {
        if (soTang > 0) this.SoTang = soTang;
    }
    public void setSoPhong(int soPhong) {
        if (soPhong > 0) this.SoPhong = soPhong;
    }
    public void setSoNguoiMoiPhong(int SoNguoiMoiPhong) {
        if (SoNguoiMoiPhong > 0) this.SoNguoiMoiPhong = SoNguoiMoiPhong;
    }

    public void capNhatThongTinToa(String tenToa, int soTang, int soPhong, int SoNguoiMoiPhong) {
        setTenToa(tenToa);
        setSoTang(soTang);
        setSoPhong(soPhong);
        setSoNguoiMoiPhong(SoNguoiMoiPhong);
    }
    public void hienThiThongTinToa() {
        System.out.printf("Toa: %s, So tang: %d, So phong: %d, So nguoi moi phong: %d\n",
                this.TenToa, this.SoTang, this.SoPhong, this.SoNguoiMoiPhong);
    }
}


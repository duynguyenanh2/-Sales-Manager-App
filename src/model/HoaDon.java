package model;

import dao.KhachHangDao;
import dao.TaiKhoanDao;
import java.util.Date;

public class HoaDon {

    private int maHD;
    private String maNV;
    private int maKH;
    private long tongTien;
    private Date ngayBan;
    private String ghiChu;

    public HoaDon() {
    }

    public HoaDon(String maNV, int maKH, long tongTien, Date ngayBan, String ghiChu) {
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;
        this.ngayBan = ngayBan;
        this.ghiChu = ghiChu;
    }

    public HoaDon(int maHD, String maNV, int maKH, long tongTien, Date ngayBan, String ghiChu) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;
        this.ngayBan = ngayBan;
        this.ghiChu = ghiChu;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTenKH() {
        KhachHang kh = new KhachHangDao().findById(maKH);
        return kh.getTenKH();
    }

    public String getTenNV() {
        TaiKhoan tk = new TaiKhoanDao().findById(maNV);
        return tk.getHoTen();
    }

    @Override
    public String toString() {
        return "Hóa đơn số: " + maHD;
    }
}

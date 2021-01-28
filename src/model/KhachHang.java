package model;

import dao.KhachHangDao;
import java.util.Date;

public class KhachHang {

    private int maKH;
    private String tenKH;
    private Date ngaySinh;
    private boolean gioiTinh;
    private String diaChi, soDT;
    private int loaiKH;

    public KhachHang() {
    }

    public KhachHang(String tenKH, Date ngaySinh, boolean gioiTinh, String diaChi, String soDT, int loaiKH) {
        this.tenKH = tenKH;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.loaiKH = loaiKH;
    }

    public KhachHang(int maKH, String tenKH, Date ngaySinh, boolean gioiTinh, String diaChi, String soDT, int loaiKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.loaiKH = loaiKH;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public int getLoaiKH() {
        return loaiKH;
    }

    public void setLoaiKH(int loaiKH) {
        this.loaiKH = loaiKH;
    }

    public String getTenLoaiKH() {
        LoaiKhachHang lkh = new KhachHangDao().findById1(loaiKH);
        return lkh.getTenLoaiKH();
    }

    @Override
    public String toString() {
        return tenKH;
    }
}

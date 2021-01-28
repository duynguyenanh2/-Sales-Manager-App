package model;

import dao.NhaPPDao;
import dao.TaiKhoanDao;
import java.util.Date;

public class PhieuNhap {

    private int maPN;
    private String maNV;
    private int maNPP;
    private long tongTien;
    private Date ngayNhap;
    private String ghiChu;

    public PhieuNhap() {
    }

    public PhieuNhap(String maNV, int maNPP, long tongTien, Date ngayNhap, String ghiChu) {
        this.maNV = maNV;
        this.maNPP = maNPP;
        this.tongTien = tongTien;
        this.ngayNhap = ngayNhap;
        this.ghiChu = ghiChu;
    }

    public PhieuNhap(int maPN, String maNV, int maNPP, long tongTien, Date ngayNhap, String ghiChu) {
        this.maPN = maPN;
        this.maNV = maNV;
        this.maNPP = maNPP;
        this.tongTien = tongTien;
        this.ngayNhap = ngayNhap;
        this.ghiChu = ghiChu;
    }

    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getMaNPP() {
        return maNPP;
    }

    public void setMaNPP(int maNPP) {
        this.maNPP = maNPP;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTenNPP() {
        NhaPhanPhoi npp = new NhaPPDao().findById(maNPP);
        return npp.getTenNPP();
    }

    public String getTenNV() {
        TaiKhoan tk = new TaiKhoanDao().findById(maNV);
        return tk.getHoTen();
    }

    @Override
    public String toString() {
        return "Phiếu nhập số: " + maPN;
    }
}

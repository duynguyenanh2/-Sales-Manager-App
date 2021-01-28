package model;

import dao.SanPhamDao;

public class ChiTietPhieuNhap {

    private int maPN, maSP, soLuong;
    private long donGia, thanhTien;

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(int maPN, int maSP, int soLuong, long donGia, long thanhTien) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTenSP() {
        SanPham sp = new SanPhamDao().findById(maSP);
        return sp.getTenSP();
    }
}

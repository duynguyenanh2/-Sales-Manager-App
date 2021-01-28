package model;

import dao.HangSXDao;
import dao.SanPhamDao;

public class SanPham {

    private int maSP;
    private String tenSP;
    private int loaiSP, hangSX;
    private long giaNhap, giaBan;
    private int tonKho;
    private boolean trangThai;
    private String hinh, moTa;

    public SanPham() {
    }

    public SanPham(String tenSP, int loaiSP, int hangSX, long giaNhap, long giaBan, int tonKho, boolean trangThai, String hinh, String moTa) {
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.hangSX = hangSX;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.tonKho = tonKho;
        this.trangThai = trangThai;
        this.hinh = hinh;
        this.moTa = moTa;
    }

    public SanPham(int maSP, String tenSP, int loaiSP, int hangSX, long giaNhap, long giaBan, int tonKho, boolean trangThai, String hinh, String moTa) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.hangSX = hangSX;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.tonKho = tonKho;
        this.trangThai = trangThai;
        this.hinh = hinh;
        this.moTa = moTa;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(int loaiSP) {
        this.loaiSP = loaiSP;
    }

    public int getHangSX() {
        return hangSX;
    }

    public void setHangSX(int hangSX) {
        this.hangSX = hangSX;
    }

    public long getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(long giaNhap) {
        this.giaNhap = giaNhap;
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    public int getTonKho() {
        return tonKho;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenLoaiSP() {
        LoaiSanPham lsp = new SanPhamDao().findById1(loaiSP);
        return lsp.getTenLoaiSP();
    }

    public String getTenHangSX() {
        HangSanXuat hsx = new HangSXDao().findById(hangSX);
        return hsx.getTenHangSX();
    }

    @Override
    public String toString() {
        return tenSP;
    }
}

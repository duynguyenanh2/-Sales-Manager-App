package model;

public class HangSanXuat {

    private int maHangSX;
    private String tenHangSX;

    public HangSanXuat() {
    }

    public HangSanXuat(String tenHangSX) {
        this.tenHangSX = tenHangSX;
    }

    public HangSanXuat(int maHangSX, String tenHangSX) {
        this.maHangSX = maHangSX;
        this.tenHangSX = tenHangSX;
    }

    public int getMaHangSX() {
        return maHangSX;
    }

    public void setMaHangSX(int maHangSX) {
        this.maHangSX = maHangSX;
    }

    public String getTenHangSX() {
        return tenHangSX;
    }

    public void setTenHangSX(String tenHangSX) {
        this.tenHangSX = tenHangSX;
    }

    @Override
    public String toString() {
        return tenHangSX;
    }
}

package model;

public class LoaiSanPham {

    private int maLoaiSP;
    private String tenLoaiSP;

    public LoaiSanPham() {
    }

    public LoaiSanPham(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public LoaiSanPham(int maLoaiSP, String tenLoaiSP) {
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
    }

    public int getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(int maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    @Override
    public String toString() {
        return tenLoaiSP;
    }
}

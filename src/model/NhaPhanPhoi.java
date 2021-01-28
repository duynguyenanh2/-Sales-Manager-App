package model;

public class NhaPhanPhoi {

    private int maNPP;
    private String tenNPP, diaChi, soDT, email;

    public NhaPhanPhoi() {
    }

    public NhaPhanPhoi(String tenNPP, String diaChi, String soDT, String email) {
        this.tenNPP = tenNPP;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.email = email;
    }

    public NhaPhanPhoi(int maNPP, String tenNPP, String diaChi, String soDT, String email) {
        this.maNPP = maNPP;
        this.tenNPP = tenNPP;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.email = email;
    }

    public int getMaNPP() {
        return maNPP;
    }

    public void setMaNPP(int maNPP) {
        this.maNPP = maNPP;
    }

    public String getTenNPP() {
        return tenNPP;
    }

    public void setTenNPP(String tenNPP) {
        this.tenNPP = tenNPP;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return tenNPP;
    }
}

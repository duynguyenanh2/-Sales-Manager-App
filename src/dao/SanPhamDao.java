package dao;

import helper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.LoaiSanPham;
import model.SanPham;

public class SanPhamDao {

    // Phần Loại Sản Phẩm
    public ArrayList<LoaiSanPham> select1() {
        String sql = "select * from loaisanpham";
        return select1(sql);
    }

    public LoaiSanPham findById1(int maLoaiSP) {
        String sql = "select * from loaisanpham where MaLoaiSP=?";
        ArrayList<LoaiSanPham> list = select1(sql, maLoaiSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<LoaiSanPham> select1(String sql, Object... args) {
        ArrayList<LoaiSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery(sql, args);
            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham(rs.getInt(1), rs.getString(2));
                list.add(lsp);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insert1(LoaiSanPham lsp) {
        String sql = "insert into loaisanpham (TenLoaiSP) values (?)";
        Jdbc.executeUpdate(sql, lsp.getTenLoaiSP());
    }

    public void update1(LoaiSanPham lsp) {
        String sql = "update loaisanpham set TenLoaiSP=? where MaLoaiSP=?";
        Jdbc.executeUpdate(sql, lsp.getTenLoaiSP(), lsp.getMaLoaiSP());
    }

    public void delete1(int maLoaiSP) {
        String sql = "delete from loaisanpham where MaLoaiSP=?";
        Jdbc.executeUpdate(sql, maLoaiSP);
    }

    // Phần Sản Phẩm
    public ArrayList<SanPham> select() {
        String sql = "select * from sanpham";
        return select(sql);
    }

    public ArrayList<SanPham> selectSP() {
        String sql = "select * from sanpham where TrangThai=1";
        return select(sql);
    }

    public ArrayList<SanPham> selectByKeyword(String keyword) {
        String sql = "select * from sanpham where TenSP like ?";
        return select(sql, "%" + keyword + "%");
    }

    public SanPham findById(int maSP) {
        String sql = "select * from sanpham where MaSP=?";
        ArrayList<SanPham> list = select(sql, maSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<SanPham> select(String sql, Object... args) {
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery(sql, args);
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getLong(5), rs.getLong(6), rs.getInt(7),
                        rs.getBoolean(8), rs.getString(9), rs.getString(10));
                list.add(sp);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insert(SanPham sp) {
        String sql = "insert into sanpham (TenSP, LoaiSP, HangSX, GiaNhap, GiaBan, TonKho, TrangThai, Hinh, MoTa) values (?,?,?,?,?,?,?,?,?)";
        Jdbc.executeUpdate(sql, sp.getTenSP(), sp.getLoaiSP(), sp.getHangSX(), sp.getGiaNhap(), sp.getGiaBan(), sp.getTonKho(), sp.isTrangThai(),
                sp.getHinh(), sp.getMoTa());
    }

    public void update(SanPham sp) {
        String sql = "update sanpham set TenSP=?, LoaiSP=?, HangSX=?, GiaNhap=?, GiaBan=?, TonKho=?, TrangThai=?, Hinh=?, MoTa=? where MaSP=?";
        Jdbc.executeUpdate(sql, sp.getTenSP(), sp.getLoaiSP(), sp.getHangSX(), sp.getGiaNhap(), sp.getGiaBan(), sp.getTonKho(), sp.isTrangThai(),
                sp.getHinh(), sp.getMoTa(), sp.getMaSP());
    }

    public void delete(int maSP) {
        String sql = "delete from sanpham where MaSP=?";
        Jdbc.executeUpdate(sql, maSP);
    }
}

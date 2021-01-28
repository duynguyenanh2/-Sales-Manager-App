package dao;

import helper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.LoaiKhachHang;
import model.KhachHang;

public class KhachHangDao {

    // Phần Loại Khách Hàng
    public ArrayList<LoaiKhachHang> select1() {
        String sql = "select * from loaikhachhang";
        return select1(sql);
    }

    public LoaiKhachHang findById1(int maLoaiKH) {
        String sql = "select * from loaikhachhang where MaLoaiKH=?";
        ArrayList<LoaiKhachHang> list = select1(sql, maLoaiKH);
        return list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<LoaiKhachHang> select1(String sql, Object... args) {
        ArrayList<LoaiKhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery(sql, args);
            while (rs.next()) {
                LoaiKhachHang lkh = new LoaiKhachHang(rs.getInt(1), rs.getString(2));
                list.add(lkh);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insert1(LoaiKhachHang lkh) {
        String sql = "insert into loaikhachhang (TenLoaiKH) values (?)";
        Jdbc.executeUpdate(sql, lkh.getTenLoaiKH());
    }

    public void update1(LoaiKhachHang lkh) {
        String sql = "update loaikhachhang set TenLoaiKH=? where MaLoaiKH=?";
        Jdbc.executeUpdate(sql, lkh.getTenLoaiKH(), lkh.getMaLoaiKH());
    }

    public void delete1(int maLoaiKH) {
        String sql = "delete from loaikhachhang where MaLoaiKH=?";
        Jdbc.executeUpdate(sql, maLoaiKH);
    }

    // Phần Khách Hàng
    public ArrayList<KhachHang> select() {
        String sql = "select * from khachhang";
        return select(sql);
    }

    public ArrayList<KhachHang> selectByKeyword(String keyword) {
        String sql = "select * from khachhang where TenKH like ? or SoDT like ?";
        return select(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    public KhachHang findById(int maKH) {
        String sql = "select * from khachhang where MaKH=?";
        ArrayList<KhachHang> list = select(sql, maKH);
        return list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<KhachHang> select(String sql, Object... args) {
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery(sql, args);
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getBoolean(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                list.add(kh);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Date getLatestDate(int maKH) {
        String sql = "select NgayBan from hoadon where MaKH=?";
        ArrayList<Date> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery(sql, maKH);
            while (rs.next()) {
                list.add(rs.getDate(1));
            }
        } catch (Exception e) {
        }
        return list.size() > 0 ? list.get(list.size() - 1) : null;
    }

    public void insert(KhachHang kh) {
        String sql = "insert into khachhang (TenKH, NgaySinh, GioiTinh, DiaChi, SoDT, LoaiKH) values (?,?,?,?,?,?)";
        Jdbc.executeUpdate(sql, kh.getTenKH(), kh.getNgaySinh(), kh.isGioiTinh(), kh.getDiaChi(), kh.getSoDT(), kh.getLoaiKH());
    }

    public void update(KhachHang kh) {
        String sql = "update khachhang set TenKH=?, NgaySinh=?, GioiTinh=?, DiaChi=?, SoDT=?, LoaiKH=? where MaKH=?";
        Jdbc.executeUpdate(sql, kh.getTenKH(), kh.getNgaySinh(), kh.isGioiTinh(), kh.getDiaChi(), kh.getSoDT(), kh.getLoaiKH(), kh.getMaKH());
    }

    public void delete(int maKH) {
        String sql = "delete from khachhang where MaKH=?";
        Jdbc.executeUpdate(sql, maKH);
    }
}

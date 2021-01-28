package dao;

import helper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.ChiTietPhieuNhap;
import model.PhieuNhap;

public class PhieuNhapDao {

    // Phần Phiếu Nhập
    public ArrayList<PhieuNhap> select() {
        String sql = "select * from phieunhap";
        return select(sql);
    }

    public ArrayList<PhieuNhap> selectById(int maPN) {
        String sql = "select * from phieunhap where MaPN=?";
        return select(sql, maPN);
    }

    public ArrayList<PhieuNhap> selectByDate(Date from, Date to) {
        String sql = "select * from phieunhap where NgayNhap between ? and ?";
        return select(sql, from, to);
    }

    public PhieuNhap findById(int maPN) {
        String sql = "select * from phieunhap where MaPN=?";
        ArrayList<PhieuNhap> list = select(sql, maPN);
        return list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<PhieuNhap> select(String sql, Object... args) {
        ArrayList<PhieuNhap> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery(sql, args);
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getLong(4), rs.getDate(5), rs.getString(6));
                list.add(pn);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getMaPN() {
        int maPN = 0;
        try {
            ResultSet rs = Jdbc.executeQuery("select MAX(MaPN) from phieunhap");
            if (rs.next()) {
                maPN = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return maPN;
    }

    public void insert(PhieuNhap pn) {
        String sql = "insert into phieunhap (MaNV, MaNPP, TongTien, NgayNhap, GhiChu) values (?,?,?,?,?)";
        Jdbc.executeUpdate(sql, pn.getMaNV(), pn.getMaNPP(), pn.getTongTien(), pn.getNgayNhap(), pn.getGhiChu());
    }

    public void delete(int maPN) {
        String sql = "delete from phieunhap where MaPN=?";
        Jdbc.executeUpdate(sql, maPN);
    }

    // Phần Chi Tiết Phiếu Nhập
    public ArrayList<ChiTietPhieuNhap> select1(int maPN) {
        String sql = "select * from chitietphieunhap where MaPN=?";
        return select1(sql, maPN);
    }

    public ChiTietPhieuNhap findById1(int maPN, int maSP) {
        String sql = "select * from chitietphieunhap where MaPN=? and MaSP=?";
        ArrayList<ChiTietPhieuNhap> list = select1(sql, maPN, maSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<ChiTietPhieuNhap> select1(String sql, Object... args) {
        ArrayList<ChiTietPhieuNhap> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery(sql, args);
            while (rs.next()) {
                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getLong(4), rs.getLong(5));
                list.add(ctpn);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insert1(ChiTietPhieuNhap ctpn) {
        String sql = "CALL sp_NhapVao(?,?,?,?,?)";
        Jdbc.executeUpdate(sql, ctpn.getMaPN(), ctpn.getMaSP(), ctpn.getSoLuong(), ctpn.getDonGia(), ctpn.getThanhTien());
    }

    public void delete1(int maPN) {
        String sql = "delete from chitietphieunhap where MaPN=?";
        Jdbc.executeUpdate(sql, maPN);
    }
}

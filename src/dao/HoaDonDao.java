package dao;

import helper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.ChiTietHoaDon;
import model.HoaDon;

public class HoaDonDao {

    // Phần Hóa Đơn
    public ArrayList<HoaDon> select() {
        String sql = "select * from hoadon";
        return select(sql);
    }

    public ArrayList<HoaDon> selectById(int maHD) {
        String sql = "select * from hoadon where MaHD=?";
        return select(sql, maHD);
    }

    public ArrayList<HoaDon> selectByDate(Date from, Date to) {
        String sql = "select * from hoadon where NgayBan between ? and ?";
        return select(sql, from, to);
    }

    public HoaDon findById(int maHD) {
        String sql = "select * from hoadon where MaHD=?";
        ArrayList<HoaDon> list = select(sql, maHD);
        return list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<HoaDon> select(String sql, Object... args) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery(sql, args);
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getLong(4), rs.getDate(5), rs.getString(6));
                list.add(hd);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getMaHD() {
        int maHD = 0;
        try {
            ResultSet rs = Jdbc.executeQuery("select MAX(MaHD) from hoadon");
            if (rs.next()) {
                maHD = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return maHD;
    }

    public void insert(HoaDon hd) {
        String sql = "insert into hoadon (MaNV, MaKH, TongTien, NgayBan, GhiChu) values (?,?,?,?,?)";
        Jdbc.executeUpdate(sql, hd.getMaNV(), hd.getMaKH(), hd.getTongTien(), hd.getNgayBan(), hd.getGhiChu());
    }

    public void delete(int maHD) {
        String sql = "delete from hoadon where MaHD=?";
        Jdbc.executeUpdate(sql, maHD);
    }

    // Phần Chi Tiết Hóa Đơn
    public ArrayList<ChiTietHoaDon> select1(int maHD) {
        String sql = "select * from chitiethoadon where MaHD=?";
        return select1(sql, maHD);
    }

    public ChiTietHoaDon findById1(int maHD, int maSP) {
        String sql = "select * from chitiethoadon where MaHD=? and MaSP=?";
        ArrayList<ChiTietHoaDon> list = select1(sql, maHD, maSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<ChiTietHoaDon> select1(String sql, Object... args) {
        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery(sql, args);
            while (rs.next()) {
                ChiTietHoaDon cthd = new ChiTietHoaDon(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getLong(4), rs.getInt(5), rs.getLong(6));
                list.add(cthd);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insert1(ChiTietHoaDon cthd) {
        String sql = "CALL sp_BanRa(?,?,?,?,?,?)";
        Jdbc.executeUpdate(sql, cthd.getMaHD(), cthd.getMaSP(), cthd.getSoLuong(), cthd.getDonGia(), cthd.getGiamGia(), cthd.getThanhTien());
    }

    public void delete1(int maHD) {
        String sql = "delete from chitiethoadon where MaHD=?";
        Jdbc.executeUpdate(sql, maHD);
    }
}

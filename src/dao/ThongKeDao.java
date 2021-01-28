package dao;

import helper.Jdbc;
import helper.XDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class ThongKeDao {

    public ArrayList<Object[]> ThongKeBanRa(Date from, Date to) {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery("CALL sp_TKBanRa(?,?)", from, to);
            while (rs.next()) {
                list.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getLong(4)});
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Object[]> ThongKeNhapVao(Date from, Date to) {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery("CALL sp_TKNhapVao(?,?)", from, to);
            while (rs.next()) {
                list.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getLong(4)});
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Object[]> ThongKeDoanhThuThang(Date from, Date to) {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery("CALL sp_DoanhThuThang(?,?)", from, to);
            while (rs.next()) {
                list.add(new Object[]{rs.getString(1), rs.getLong(2)});
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Object[]> ThongKeDoanhThuNgay(Date from, Date to) {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery("CALL sp_DoanhThuNgay(?,?)", from, to);
            while (rs.next()) {
                list.add(new Object[]{XDate.toString(rs.getDate(1), "E, dd/MM/yyyy"), rs.getLong(2)});
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Object[]> ThongKeKhachHang() {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery("SELECT * FROM TKKhachHang");
            while (rs.next()) {
                list.add(new Object[]{rs.getString(1), rs.getString(2), rs.getInt(3), rs.getLong(4)});
            }
        } catch (Exception e) {
        }
        return list;
    }
}

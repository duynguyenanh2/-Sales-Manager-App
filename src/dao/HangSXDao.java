package dao;

import helper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.HangSanXuat;

public class HangSXDao {

    public ArrayList<HangSanXuat> select() {
        String sql = "select * from hangsanxuat";
        return select(sql);
    }

    public HangSanXuat findById(int maHangSX) {
        String sql = "select * from hangsanxuat where MaHangSX=?";
        ArrayList<HangSanXuat> list = select(sql, maHangSX);
        return list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<HangSanXuat> select(String sql, Object... args) {
        ArrayList<HangSanXuat> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery(sql, args);
            while (rs.next()) {
                HangSanXuat lsp = new HangSanXuat(rs.getInt(1), rs.getString(2));
                list.add(lsp);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insert(HangSanXuat hsx) {
        String sql = "insert into hangsanxuat (TenHangSX) values (?)";
        Jdbc.executeUpdate(sql, hsx.getTenHangSX());
    }

    public void update(HangSanXuat hsx) {
        String sql = "update hangsanxuat set TenHangSX=? where MaHangSX=?";
        Jdbc.executeUpdate(sql, hsx.getTenHangSX(), hsx.getMaHangSX());
    }

    public void delete(int maHangSX) {
        String sql = "delete from hangsanxuat where MaHangSX=?";
        Jdbc.executeUpdate(sql, maHangSX);
    }
}

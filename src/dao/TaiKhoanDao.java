package dao;

import helper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.TaiKhoan;

public class TaiKhoanDao {

    public ArrayList<TaiKhoan> select() {
        String sql = "select * from taikhoan";
        return select(sql);
    }

    public ArrayList<TaiKhoan> selectByKeyword(String keyword) {
        String sql = "select * from taikhoan where TenDangNhap like ?";
        return select(sql, "%" + keyword + "%");
    }

    public TaiKhoan findById(String tenDangNhap) {
        String sql = "select * from taikhoan where TenDangNhap=?";
        ArrayList<TaiKhoan> list = select(sql, tenDangNhap);
        return list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<TaiKhoan> select(String sql, Object... args) {
        ArrayList<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery(sql, args);
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                list.add(tk);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insert(TaiKhoan tk) {
        String sql = "insert into taikhoan (TenDangNhap, MatKhau, HoTen, Email, QRCode, VaiTro) values (?,?,?,?,?,?)";
        Jdbc.executeUpdate(sql, tk.getTenDangNhap(), tk.getMatKhau(), tk.getHoTen(), tk.getEmail(), tk.getQrCode(), tk.isVaiTro());
    }

    public void update(TaiKhoan tk) {
        String sql = "update taikhoan set MatKhau=?, HoTen=?, Email=?, VaiTro=? where TenDangNhap=?";
        Jdbc.executeUpdate(sql, tk.getMatKhau(), tk.getHoTen(), tk.getEmail(), tk.isVaiTro(), tk.getTenDangNhap());
    }

    public void change(TaiKhoan tk) {
        String sql = "update taikhoan set MatKhau=? where TenDangNhap=?";
        Jdbc.executeUpdate(sql, tk.getMatKhau(), tk.getTenDangNhap());
    }

    public void delete(String tenDangNhap) {
        String sql = "delete from taikhoan where TenDangNhap=?";
        Jdbc.executeUpdate(sql, tenDangNhap);
    }
}

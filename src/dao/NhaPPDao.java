package dao;

import helper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.NhaPhanPhoi;

public class NhaPPDao {

    public ArrayList<NhaPhanPhoi> select() {
        String sql = "select * from nhaphanphoi";
        return select(sql);
    }

    public ArrayList<NhaPhanPhoi> selectByKeyword(String keyword) {
        String sql = "select * from nhaphanphoi where TenNPP like ?";
        return select(sql, "%" + keyword + "%");
    }

    public NhaPhanPhoi findById(int maNPP) {
        String sql = "select * from nhaphanphoi where MaNPP=?";
        ArrayList<NhaPhanPhoi> list = select(sql, maNPP);
        return list.size() > 0 ? list.get(0) : null;
    }

    private ArrayList<NhaPhanPhoi> select(String sql, Object... args) {
        ArrayList<NhaPhanPhoi> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.executeQuery(sql, args);
            while (rs.next()) {
                NhaPhanPhoi npp = new NhaPhanPhoi(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(npp);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insert(NhaPhanPhoi npp) {
        String sql = "insert into nhaphanphoi (TenNPP, DiaChi, SoDT, Email) values (?,?,?,?)";
        Jdbc.executeUpdate(sql, npp.getTenNPP(), npp.getDiaChi(), npp.getSoDT(), npp.getEmail());
    }

    public void update(NhaPhanPhoi npp) {
        String sql = "update nhaphanphoi set TenNPP=?, DiaChi=?, SoDT=?, Email=? where MaNPP=?";
        Jdbc.executeUpdate(sql, npp.getTenNPP(), npp.getDiaChi(), npp.getSoDT(), npp.getEmail(), npp.getMaNPP());
    }

    public void delete(int maNPP) {
        String sql = "delete from nhaphanphoi where MaNPP=?";
        Jdbc.executeUpdate(sql, maNPP);
    }
}

package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Jdbc {

    public static PreparedStatement preparedStatement(String sql, Object... args) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLBH?serverTimezone=UTC", "root", "");
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pst.setObject(i + 1, args[i]);
        }
        return pst;
    }

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement pst = preparedStatement(sql, args);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement pst = preparedStatement(sql, args);
            return pst.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package helper;

import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import model.TaiKhoan;

public class XShare {

    public static final Image APP_ICON;

    static {
        // Tải biểu tượng ứng dụng
        String file = "/images/sale.png";
        APP_ICON = new ImageIcon(XShare.class.getResource(file)).getImage();
    }

    public static boolean saveLogo(File file) {
        File dir = new File("images");
        // Tạo thư mục nếu chưa tồn tại
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            // Copy vào thư mục (đè nếu đã tồn tại)
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static ImageIcon readLogo(String fileName) {
        File path = new File("images", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }

    // Kiểm tra đăng nhập
    public static String KEY = null;
    public static TaiKhoan USER = null;
    public static boolean CHG = false;

    public static void logout() {
        XShare.KEY = null;
        XShare.USER = null;
        XShare.CHG = false;
    }

    public static boolean authenticated() {
        return XShare.USER != null;
    }
}

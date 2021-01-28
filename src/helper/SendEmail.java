package helper;

import java.util.HashMap;
import java.util.Map;

public class SendEmail {

    public static void send(String mailTo, String hoTen, String tenDN, String qrcode) {
        // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "okemani123@gmail.com";
        String password = "lamkyyen8758432";

        // message info
        String subject = "THÔNG TIN TÀI KHOẢN";
        StringBuilder body = new StringBuilder("<head>");
        body.append("<style type='text/css'>");
        body.append(".red { color: #f00; }");
        body.append("</style>");
        body.append("</head>");
        body.append("<div align='center'>");
        body.append("<h1 class='red'>Nhân viên: ").append(hoTen).append("</h1>");
        body.append("<h2>Tên đăng nhập: ").append(tenDN).append("</h2>");
        body.append("<h2>Mật khẩu (mặc định): ").append(tenDN).append("</h2>");
        body.append("<img src='cid:image' width='300'>");
        body.append("<h2 class='red'><i>Vui lòng đăng nhập để đổi mật khẩu!</i></h2>");
        body.append("</div>");

        // inline images
        Map<String, String> inlineImages = new HashMap<>();
        inlineImages.put("image", "images/" + qrcode);

        try {
            EmbedImage.send(host, port, mailFrom, password, mailTo, subject, body.toString(), inlineImages);
        } catch (Exception e) {
        }
    }
}

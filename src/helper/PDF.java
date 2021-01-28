package helper;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JTable;

public class PDF {

    private static final File fontFile = new File("fonts/vuTimes.ttf");

    public static void inHoaDon(String path, String maHD, String ngayBan, String tenNV, String tenKH, String diaChi, String soDT, String tongTien,
            String ghiChu, JTable table) {
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        try {
            Document doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, new FileOutputStream(path + ".pdf"));
            BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 24, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17));
            Font font1 = new Font(bf, 18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17));
            Font font2 = new Font(bf, 14, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17));
            Font font3 = new Font(bf, 16, Font.BOLDITALIC);
            Font font4 = new Font(bf, 14, Font.BOLDITALIC);
            Font font5 = new Font(bf, 14, Font.NORMAL);
            doc.open();

            Paragraph title = new Paragraph("HÓA ĐƠN BÁN LẺ", font);
            Paragraph subtitle1 = new Paragraph("Thông tin chung:\n", font2);
            Paragraph subtitle2 = new Paragraph("Thông tin sản phẩm:\n", font2);
            String tencongty = "CÔNG TY TNHH THƯƠNG MẠI DỊCH VỤ SMS";
            String ttcongty = "Địa chỉ: 81/8 Đồng Đen, Phường 12, Quận Tân Bình\n"
                    + "Hotline: 1900 636 980 - (028) 7800 8888\n"
                    + "Website: thegioicongnghe.com";
            String tthoadon = "Mã hóa đơn: " + maHD + "\n"
                    + "Ngày bán: " + ngayBan + "\n"
                    + "Nhân viên: " + tenNV;
            String ttkhachhang = "Khách hàng: " + tenKH + "\n"
                    + "Địa chỉ: " + diaChi + "\n"
                    + "Điện thoại: " + soDT;

            title.setIndentationLeft(80);
            title.setIndentationRight(80);
            title.setSpacingAfter(15);
            title.setAlignment(Element.ALIGN_CENTER);

            subtitle1.setSpacingBefore(15);
            subtitle1.setSpacingAfter(15);
            subtitle1.setAlignment(Element.ALIGN_LEFT);

            subtitle2.setSpacingBefore(15);
            subtitle2.setSpacingAfter(15);
            subtitle2.setAlignment(Element.ALIGN_LEFT);

            PdfPTable tbl = new PdfPTable(2);
            tbl.setWidths(new int[]{1, 2});
            tbl.setTotalWidth(580);
            tbl.setLockedWidth(true);
            tbl.setSpacingAfter(15);
            tbl.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            Image img = Image.getInstance("images/logo.png");
            img.scalePercent(25f);

            PdfPCell hinh = new PdfPCell(img, false);
            PdfPCell tencty = new PdfPCell(new Phrase(tencongty, font1));
            PdfPCell ttcty = new PdfPCell(new Phrase(ttcongty, font2));
            hinh.setRowspan(2);
            hinh.setHorizontalAlignment(Element.ALIGN_CENTER);
            hinh.setBorder(Rectangle.NO_BORDER);
            tencty.setBorder(Rectangle.NO_BORDER);
            ttcty.setBorder(Rectangle.NO_BORDER);
            tbl.addCell(hinh);
            tbl.addCell(tencty);
            tbl.addCell(ttcty);

            PdfPTable tbl1 = new PdfPTable(2);
            tbl1.setTotalWidth(520);
            tbl1.setLockedWidth(true);
            tbl1.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell cot1 = new PdfPCell(new Phrase(tthoadon, font4));
            PdfPCell cot2 = new PdfPCell(new Phrase(ttkhachhang, font4));
            cot1.setBorder(Rectangle.NO_BORDER);
            cot2.setBorder(Rectangle.NO_BORDER);
            cot1.setBackgroundColor(new CMYKColor(19, 0, 20, 3));
            cot2.setBackgroundColor(new CMYKColor(19, 0, 20, 3));
            tbl1.addCell(cot1);
            tbl1.addCell(cot2);

            Chunk linebreak = new Chunk(new DottedLineSeparator());

            doc.add(tbl);
            doc.add(linebreak);
            doc.add(title);
            doc.add(subtitle1);
            doc.add(tbl1);
            doc.add(subtitle2);

            PdfPTable tbl2 = new PdfPTable(6);
            tbl2.setWidths(new int[]{1, 2, 1, 1, 1, 1});
            tbl2.setTotalWidth(520);
            tbl2.setLockedWidth(true);
            tbl2.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell sott = new PdfPCell(new Phrase("Số TT", font4));
            PdfPCell tensp = new PdfPCell(new Phrase("Tên sản phẩm", font4));
            PdfPCell sl = new PdfPCell(new Phrase("Số lượng", font4));
            PdfPCell dg = new PdfPCell(new Phrase("Đơn giá", font4));
            PdfPCell gg = new PdfPCell(new Phrase("Giảm giá", font4));
            PdfPCell tt = new PdfPCell(new Phrase("Thành tiền", font4));

            sott.setBorder(Rectangle.NO_BORDER);
            tensp.setBorder(Rectangle.NO_BORDER);
            sl.setBorder(Rectangle.NO_BORDER);
            dg.setBorder(Rectangle.NO_BORDER);
            gg.setBorder(Rectangle.NO_BORDER);
            tt.setBorder(Rectangle.NO_BORDER);

            tbl2.addCell(sott);
            tbl2.addCell(tensp);
            tbl2.addCell(sl);
            tbl2.addCell(dg);
            tbl2.addCell(gg);
            tbl2.addCell(tt);

            CustomSeparator separator = new CustomSeparator();
            separator.setDash(10);
            separator.setGap(0);
            separator.setLineWidth(2);

            PdfPCell linebf = new PdfPCell(new Phrase(new Chunk(separator)));
            linebf.setColspan(6);
            linebf.setBorder(Rectangle.NO_BORDER);
            tbl2.addCell(linebf);

            for (int i = 0; i < table.getRowCount(); i++) {
                tbl2.addCell(i + 1 + "");
                tbl2.addCell(new Paragraph(table.getValueAt(i, 1).toString(), font5));
                tbl2.addCell(table.getValueAt(i, 2).toString());
                tbl2.addCell(currencyVN.format(Long.valueOf(table.getValueAt(i, 3).toString())));
                tbl2.addCell(table.getValueAt(i, 4).toString() + "%");
                tbl2.addCell(currencyVN.format(Long.valueOf(table.getValueAt(i, 5).toString())));
            }

            PdfPCell lineaf = new PdfPCell(new Phrase(new Chunk(new DottedLineSeparator())));
            lineaf.setColspan(6);
            lineaf.setBorder(Rectangle.NO_BORDER);
            tbl2.addCell(lineaf);

            PdfPCell tong = new PdfPCell(new Phrase("Tổng tiền", font3));
            tong.setColspan(4);
            tong.setBorder(Rectangle.NO_BORDER);
            tbl2.addCell(tong);

            PdfPCell tien = new PdfPCell(new Phrase(currencyVN.format(Long.valueOf(tongTien)), font3));
            tien.setColspan(2);
            tien.setBorder(Rectangle.NO_BORDER);
            tien.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tbl2.addCell(tien);

            doc.add(tbl2);

            Paragraph tongchu = new Paragraph("Tổng tiền (bằng chữ): " + ghiChu, font4);
            tongchu.setSpacingBefore(15);
            tongchu.setAlignment(Element.ALIGN_RIGHT);
            doc.add(tongchu);

            doc.close();
        } catch (Exception e) {
        }
    }

    public static void inPhieuNhap(String path, String maPN, String ngayNhap, String tenNV, String tenNPP, String diaChi, String soDT, String tongTien,
            String ghiChu, JTable table) {
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        try {
            Document doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, new FileOutputStream(path + ".pdf"));
            BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 24, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17));
            Font font1 = new Font(bf, 18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17));
            Font font2 = new Font(bf, 14, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17));
            Font font3 = new Font(bf, 16, Font.BOLDITALIC);
            Font font4 = new Font(bf, 14, Font.BOLDITALIC);
            Font font5 = new Font(bf, 14, Font.NORMAL);
            doc.open();

            Paragraph title = new Paragraph("PHIẾU NHẬP HÀNG", font);
            Paragraph subtitle1 = new Paragraph("Thông tin chung:\n", font2);
            Paragraph subtitle2 = new Paragraph("Thông tin sản phẩm:\n", font2);
            String tencongty = "CÔNG TY TNHH THƯƠNG MẠI DỊCH VỤ SMS";
            String ttcongty = "Địa chỉ: 81/8 Đồng Đen, Phường 12, Quận Tân Bình\n"
                    + "Hotline: 1900 636 980 - (028) 7800 8888\n"
                    + "Website: thegioicongnghe.com";
            String ttphieunhap = "Mã phiếu nhập: " + maPN + "\n"
                    + "Ngày nhập: " + ngayNhap + "\n"
                    + "Nhân viên: " + tenNV;
            String ttnhapp = "Nhà phân phối: " + tenNPP + "\n"
                    + "Địa chỉ: " + diaChi + "\n"
                    + "Điện thoại: " + soDT;

            title.setIndentationLeft(80);
            title.setIndentationRight(80);
            title.setSpacingAfter(15);
            title.setAlignment(Element.ALIGN_CENTER);

            subtitle1.setSpacingBefore(15);
            subtitle1.setSpacingAfter(15);
            subtitle1.setAlignment(Element.ALIGN_LEFT);

            subtitle2.setSpacingBefore(15);
            subtitle2.setSpacingAfter(15);
            subtitle2.setAlignment(Element.ALIGN_LEFT);

            PdfPTable tbl = new PdfPTable(2);
            tbl.setWidths(new int[]{1, 2});
            tbl.setTotalWidth(580);
            tbl.setLockedWidth(true);
            tbl.setSpacingAfter(15);
            tbl.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            Image img = Image.getInstance("images/logo.png");
            img.scalePercent(25f);

            PdfPCell hinh = new PdfPCell(img, false);
            PdfPCell tencty = new PdfPCell(new Phrase(tencongty, font1));
            PdfPCell ttcty = new PdfPCell(new Phrase(ttcongty, font2));
            hinh.setRowspan(2);
            hinh.setHorizontalAlignment(Element.ALIGN_CENTER);
            hinh.setBorder(Rectangle.NO_BORDER);
            tencty.setBorder(Rectangle.NO_BORDER);
            ttcty.setBorder(Rectangle.NO_BORDER);
            tbl.addCell(hinh);
            tbl.addCell(tencty);
            tbl.addCell(ttcty);

            PdfPTable tbl1 = new PdfPTable(2);
            tbl1.setTotalWidth(520);
            tbl1.setLockedWidth(true);
            tbl1.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell cot1 = new PdfPCell(new Phrase(ttphieunhap, font4));
            PdfPCell cot2 = new PdfPCell(new Phrase(ttnhapp, font4));
            cot1.setBorder(Rectangle.NO_BORDER);
            cot2.setBorder(Rectangle.NO_BORDER);
            cot1.setBackgroundColor(new CMYKColor(19, 0, 20, 3));
            cot2.setBackgroundColor(new CMYKColor(19, 0, 20, 3));
            tbl1.addCell(cot1);
            tbl1.addCell(cot2);

            Chunk linebreak = new Chunk(new DottedLineSeparator());

            doc.add(tbl);
            doc.add(linebreak);
            doc.add(title);
            doc.add(subtitle1);
            doc.add(tbl1);
            doc.add(subtitle2);

            PdfPTable tbl2 = new PdfPTable(5);
            tbl2.setWidths(new int[]{1, 2, 1, 1, 1});
            tbl2.setTotalWidth(520);
            tbl2.setLockedWidth(true);
            tbl2.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell sott = new PdfPCell(new Phrase("Số TT", font4));
            PdfPCell tensp = new PdfPCell(new Phrase("Tên sản phẩm", font4));
            PdfPCell sl = new PdfPCell(new Phrase("Số lượng", font4));
            PdfPCell dg = new PdfPCell(new Phrase("Đơn giá", font4));
            PdfPCell tt = new PdfPCell(new Phrase("Thành tiền", font4));

            sott.setBorder(Rectangle.NO_BORDER);
            tensp.setBorder(Rectangle.NO_BORDER);
            sl.setBorder(Rectangle.NO_BORDER);
            dg.setBorder(Rectangle.NO_BORDER);
            tt.setBorder(Rectangle.NO_BORDER);

            tbl2.addCell(sott);
            tbl2.addCell(tensp);
            tbl2.addCell(sl);
            tbl2.addCell(dg);
            tbl2.addCell(tt);

            CustomSeparator separator = new CustomSeparator();
            separator.setDash(10);
            separator.setGap(0);
            separator.setLineWidth(2);

            PdfPCell linebf = new PdfPCell(new Phrase(new Chunk(separator)));
            linebf.setColspan(5);
            linebf.setBorder(Rectangle.NO_BORDER);
            tbl2.addCell(linebf);

            for (int i = 0; i < table.getRowCount(); i++) {
                tbl2.addCell(i + 1 + "");
                tbl2.addCell(new Paragraph(table.getValueAt(i, 1).toString(), font5));
                tbl2.addCell(table.getValueAt(i, 2).toString());
                tbl2.addCell(currencyVN.format(Long.valueOf(table.getValueAt(i, 3).toString())));
                tbl2.addCell(currencyVN.format(Long.valueOf(table.getValueAt(i, 4).toString())));
            }

            PdfPCell lineaf = new PdfPCell(new Phrase(new Chunk(new DottedLineSeparator())));
            lineaf.setColspan(5);
            lineaf.setBorder(Rectangle.NO_BORDER);
            tbl2.addCell(lineaf);

            PdfPCell tong = new PdfPCell(new Phrase("Tổng tiền", font3));
            tong.setColspan(3);
            tong.setBorder(Rectangle.NO_BORDER);
            tbl2.addCell(tong);

            PdfPCell tien = new PdfPCell(new Phrase(currencyVN.format(Long.valueOf(tongTien)), font3));
            tien.setColspan(2);
            tien.setBorder(Rectangle.NO_BORDER);
            tien.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tbl2.addCell(tien);

            doc.add(tbl2);

            Paragraph tongchu = new Paragraph("Tổng tiền (bằng chữ): " + ghiChu, font4);
            tongchu.setSpacingBefore(15);
            tongchu.setAlignment(Element.ALIGN_RIGHT);
            doc.add(tongchu);

            doc.close();
        } catch (Exception e) {
        }
    }

    public static void inQRCode(String path, String qrcode) {
        try {
            Document doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, new FileOutputStream(path + ".pdf"));
            doc.open();
            Image img = Image.getInstance("images/" + qrcode);
            img.scalePercent(200f);
            img.setAlignment(Element.ALIGN_CENTER);
            doc.add(img);
            doc.close();
        } catch (Exception e) {
        }
    }
}

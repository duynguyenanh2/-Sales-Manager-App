package view;

import dao.ThongKeDao;
import helper.XDialog;
import helper.XShare;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ThongKeFrm extends javax.swing.JFrame {

    int xMouse, yMouse, chon;
    DefaultTableModel banRa, nhapVao, dtThang, dtNgay, tkkh;
    ThongKeDao dao = new ThongKeDao();
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    public ThongKeFrm() {
        initComponents();
        init();
    }

    private void init() {
        setIconImage(XShare.APP_ICON);
        if (!XShare.USER.isVaiTro()) {
            TabbedPane.setEnabledAt(2, false);
            TabbedPane.setEnabledAt(3, false);
        }
        customTable(tblBanRa);
        customTable(tblNhapVao);
        customTable(tblDTThang);
        customTable(tblDTNgay);
        customTable(tblTKKH);
        banRa = new DefaultTableModel(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng bán ra", "Thành tiền"}, 0);
        nhapVao = new DefaultTableModel(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng nhập vào", "Thành tiền"}, 0);
        dtThang = new DefaultTableModel(new String[]{"Tháng", "Doanh thu"}, 0);
        dtNgay = new DefaultTableModel(new String[]{"Ngày", "Doanh thu"}, 0);
        tkkh = new DefaultTableModel(new String[]{"Tên khách hàng", "Số điện thoại", "Lượt mua", "Tổng tiền"}, 0);
    }

    private void hintText() {
        //txtText.setUI(new HintTextField("Nhập...", true, Color.lightGray));
    }

    private void customTable(JTable table) {
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(new Color(149, 26, 40));

        table.setDefaultEditor(Object.class, null);
        table.setShowGrid(true);
        table.setGridColor(new Color(44, 44, 44));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setSelectionBackground(new Color(232, 57, 95));
        table.setSelectionForeground(new Color(255, 255, 255));
        table.setRowHeight(40);
    }

    private void fillBanRa(int chon, Date from, Date to) {
        banRa.setRowCount(0);
        try {
            ArrayList<Object[]> list = dao.ThongKeBanRa(from, to);

            if (chon == 0) {
                sortBySLAsc(list);
            } else if (chon == 1) {
                sortBySLDesc(list);
            } else if (chon == 2) {
                sortByTTAsc(list);
            } else if (chon == 3) {
                sortByTTDesc(list);
            }

            for (Object[] row : list) {
                banRa.addRow(row);
            }
            tblBanRa.setModel(banRa);
            getTongBR();
        } catch (Exception e) {
            XDialog.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private void fillNhapVao(int chon, Date from, Date to) {
        nhapVao.setRowCount(0);
        try {
            ArrayList<Object[]> list = dao.ThongKeNhapVao(from, to);

            if (chon == 0) {
                sortBySLAsc(list);
            } else if (chon == 1) {
                sortBySLDesc(list);
            } else if (chon == 2) {
                sortByTTAsc(list);
            } else if (chon == 3) {
                sortByTTDesc(list);
            }

            for (Object[] row : list) {
                nhapVao.addRow(row);
            }
            tblNhapVao.setModel(nhapVao);
            getTongNV();
        } catch (Exception e) {
            XDialog.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private void fillDTThang(int chon, Date from, Date to) {
        dtThang.setRowCount(0);
        try {
            ArrayList<Object[]> list = dao.ThongKeDoanhThuThang(from, to);

            if (chon == 1) {
                sortByDTAsc(list);
            } else if (chon == 2) {
                sortByDTDesc(list);
            }

            for (Object[] row : list) {
                dtThang.addRow(row);
            }
            tblDTThang.setModel(dtThang);
            getTongDTThang();
        } catch (Exception e) {
            XDialog.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private void fillDTNgay(int chon, Date from, Date to) {
        dtNgay.setRowCount(0);
        try {
            ArrayList<Object[]> list = dao.ThongKeDoanhThuNgay(from, to);

            if (chon == 1) {
                sortByDTAsc(list);
            } else if (chon == 2) {
                sortByDTDesc(list);
            }

            for (Object[] row : list) {
                dtNgay.addRow(row);
            }
            tblDTNgay.setModel(dtNgay);
            getTongDTNgay();
        } catch (Exception e) {
            XDialog.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private void fillTKKH() {
        tkkh.setRowCount(0);
        try {
            ArrayList<Object[]> list = dao.ThongKeKhachHang();
            for (Object[] row : list) {
                tkkh.addRow(row);
            }
            tblTKKH.setModel(tkkh);
        } catch (Exception e) {
            XDialog.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private void getTongBR() {
        long tong = 0;
        for (int i = 0; i < tblBanRa.getRowCount(); i++) {
            tong += (Long) tblBanRa.getValueAt(i, 3);
        }
        lblTongBR.setText("Tổng: " + currencyVN.format(tong));
    }

    private void getTongNV() {
        long tong = 0;
        for (int i = 0; i < tblNhapVao.getRowCount(); i++) {
            tong += (Long) tblNhapVao.getValueAt(i, 3);
        }
        lblTongNV.setText("Tổng: " + currencyVN.format(tong));
    }

    private void getTongDTThang() {
        long tong = 0;
        for (int i = 0; i < tblDTThang.getRowCount(); i++) {
            tong += (Long) tblDTThang.getValueAt(i, 1);
        }
        lblTongDTThang.setText("Tổng: " + currencyVN.format(tong));
    }

    private void getTongDTNgay() {
        long tong = 0;
        for (int i = 0; i < tblDTNgay.getRowCount(); i++) {
            tong += (Long) tblDTNgay.getValueAt(i, 1);
        }
        lblTongDTNgay.setText("Tổng: " + currencyVN.format(tong));
    }

    private void tangGiam(int index) {
        DecimalFormat df = new DecimalFormat("##.##%");
        long tn = (Long) tblDTThang.getValueAt(index, 1);
        long tt = (Long) tblDTThang.getValueAt(index - 1, 1);
        double pc = ((double) (tn - tt)) / tt;
        if (pc > 0) {
            lblSSTG.setText("Tăng " + df.format(pc) + " so với tháng trước");
            lblSSTG.setForeground(Color.GREEN);
        } else if (pc < 0) {
            lblSSTG.setText("Giảm " + df.format(pc * (-1)) + " so với tháng trước");
            lblSSTG.setForeground(Color.RED);
        } else {
            lblSSTG.setText("Không có sự tăng giảm");
            lblSSTG.setForeground(new Color(187, 187, 187));
        }
    }

    private void sortBySLAsc(ArrayList<Object[]> list) {
        list.sort((Object[] o, Object[] o1) -> (Integer) o[2] < (Integer) o1[2] ? -1 : 1);
    }

    private void sortBySLDesc(ArrayList<Object[]> list) {
        list.sort((Object[] o, Object[] o1) -> (Integer) o[2] > (Integer) o1[2] ? -1 : 1);
    }

    private void sortByTTAsc(ArrayList<Object[]> list) {
        list.sort((Object[] o, Object[] o1) -> (Long) o[3] < (Long) o1[3] ? -1 : 1);
    }

    private void sortByTTDesc(ArrayList<Object[]> list) {
        list.sort((Object[] o, Object[] o1) -> (Long) o[3] > (Long) o1[3] ? -1 : 1);
    }

    private void sortByDTAsc(ArrayList<Object[]> list) {
        list.sort((Object[] o, Object[] o1) -> (Long) o[1] < (Long) o1[1] ? -1 : 1);
    }

    private void sortByDTDesc(ArrayList<Object[]> list) {
        list.sort((Object[] o, Object[] o1) -> (Long) o[1] > (Long) o1[1] ? -1 : 1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
        pnlMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        TabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboBanRa = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        fromBR = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        toBR = new com.toedter.calendar.JDateChooser();
        lblTongBR = new javax.swing.JLabel();
        lblTim = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBanRa = new javax.swing.JTable();
        lblBDBR = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cboNhapVao = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        fromNV = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        toNV = new com.toedter.calendar.JDateChooser();
        lblTongNV = new javax.swing.JLabel();
        lblTim1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhapVao = new javax.swing.JTable();
        lblBDNV = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cboDTThang = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        fromDTThang = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        toDTThang = new com.toedter.calendar.JDateChooser();
        lblTim2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDTThang = new javax.swing.JTable();
        lblBDThang = new javax.swing.JLabel();
        lblTongDTThang = new javax.swing.JLabel();
        lblSSTG = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cboDTNgay = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        fromDTNgay = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        toDTNgay = new com.toedter.calendar.JDateChooser();
        lblTim3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDTNgay = new javax.swing.JTable();
        lblBDNgay = new javax.swing.JLabel();
        lblTongDTNgay = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTKKH = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1000, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setForeground(new java.awt.Color(255, 255, 255));
        pnlHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlHeaderMouseDragged(evt);
            }
        });
        pnlHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlHeaderMousePressed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(255, 255, 255));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Exit.png"))); // NOI18N
        btnExit.setContentAreaFilled(false);
        btnExit.setFocusable(false);
        btnExit.setOpaque(true);
        btnExit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Exit (2).png"))); // NOI18N
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnMinimize.setBackground(new java.awt.Color(255, 255, 255));
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minimize.png"))); // NOI18N
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.setFocusable(false);
        btnMinimize.setOpaque(true);
        btnMinimize.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minimize (2).png"))); // NOI18N
        btnMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseExited(evt);
            }
        });
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnExit))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(44, 62, 80));
        jLabel2.setText("PHẦN MỀM QUẢN LÝ BÁN HÀNG");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(127, 140, 141));
        jLabel4.setText("CÔNG TY TNHH TM-DV SMS");

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedPane.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Sắp xếp theo");

        cboBanRa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboBanRa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số lượng tăng dần", "Số lượng giảm dần", "Thành tiền tăng dần", "Thành tiền giảm dần" }));
        cboBanRa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboBanRaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Từ ngày");

        fromBR.setDateFormatString("dd-MM-yyyy");
        fromBR.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("đến ngày");

        toBR.setDateFormatString("dd-MM-yyyy");
        toBR.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        lblTongBR.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblTongBR.setForeground(new java.awt.Color(255, 0, 0));
        lblTongBR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money.png"))); // NOI18N
        lblTongBR.setText("Tổng tiền");

        lblTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        lblTim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTimMouseClicked(evt);
            }
        });

        tblBanRa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblBanRa);

        lblBDBR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBDBR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graph.png"))); // NOI18N
        lblBDBR.setText("Xem biểu đồ thống kê");
        lblBDBR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBDBR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBDBRMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboBanRa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fromBR, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toBR, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addComponent(lblTongBR))
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblBDBR)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboBanRa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fromBR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toBR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTongBR, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBDBR, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Thống kê bán ra", jPanel1);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Sắp xếp theo");

        cboNhapVao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboNhapVao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số lượng tăng dần", "Số lượng giảm dần", "Thành tiền tăng dần", "Thành tiền giảm dần" }));
        cboNhapVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNhapVaoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Từ ngày");

        fromNV.setDateFormatString("dd-MM-yyyy");
        fromNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("đến ngày");

        toNV.setDateFormatString("dd-MM-yyyy");
        toNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        lblTongNV.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblTongNV.setForeground(new java.awt.Color(255, 0, 0));
        lblTongNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money.png"))); // NOI18N
        lblTongNV.setText("Tổng tiền");

        lblTim1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        lblTim1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTim1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTim1MouseClicked(evt);
            }
        });

        tblNhapVao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblNhapVao);

        lblBDNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBDNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graph.png"))); // NOI18N
        lblBDNV.setText("Xem biểu đồ thống kê");
        lblBDNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBDNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBDNVMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNhapVao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fromNV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toNV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTim1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addComponent(lblTongNV))
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblBDNV)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboNhapVao, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fromNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTim1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBDNV, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Thống kê nhập vào", jPanel2);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Sắp xếp theo");

        cboDTThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboDTThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng", "Doanh thu tăng dần", "Doanh thu giảm dần" }));
        cboDTThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDTThangActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Từ ngày");

        fromDTThang.setDateFormatString("dd-MM-yyyy");
        fromDTThang.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("đến ngày");

        toDTThang.setDateFormatString("dd-MM-yyyy");
        toDTThang.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        lblTim2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        lblTim2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTim2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTim2MouseClicked(evt);
            }
        });

        tblDTThang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDTThang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDTThangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDTThang);

        lblBDThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBDThang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graph.png"))); // NOI18N
        lblBDThang.setText("Xem biểu đồ thống kê");
        lblBDThang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBDThang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBDThangMouseClicked(evt);
            }
        });

        lblTongDTThang.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblTongDTThang.setForeground(new java.awt.Color(255, 0, 0));
        lblTongDTThang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money.png"))); // NOI18N
        lblTongDTThang.setText("Tổng tiền");

        lblSSTG.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblSSTG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sales.png"))); // NOI18N
        lblSSTG.setText("So sánh tăng giảm giữa các tháng");
        lblSSTG.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblBDThang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSSTG))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboDTThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fromDTThang, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toDTThang, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTim2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(lblTongDTThang))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDTThang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(fromDTThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(toDTThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTim2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblTongDTThang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBDThang, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(lblSSTG, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))
        );

        TabbedPane.addTab("Thống kê d. thu tháng", jPanel3);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Sắp xếp theo");

        cboDTNgay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboDTNgay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày", "Doanh thu tăng dần", "Doanh thu giảm dần" }));
        cboDTNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDTNgayActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Từ ngày");

        fromDTNgay.setDateFormatString("dd-MM-yyyy");
        fromDTNgay.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("đến ngày");

        toDTNgay.setDateFormatString("dd-MM-yyyy");
        toDTNgay.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        lblTim3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        lblTim3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTim3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTim3MouseClicked(evt);
            }
        });

        tblDTNgay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tblDTNgay);

        lblBDNgay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBDNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graph.png"))); // NOI18N
        lblBDNgay.setText("Xem biểu đồ thống kê");
        lblBDNgay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBDNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBDNgayMouseClicked(evt);
            }
        });

        lblTongDTNgay.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblTongDTNgay.setForeground(new java.awt.Color(255, 0, 0));
        lblTongDTNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money.png"))); // NOI18N
        lblTongDTNgay.setText("Tổng tiền");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblBDNgay)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboDTNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fromDTNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toDTNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTim3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(lblTongDTNgay))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDTNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(fromDTNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(toDTNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTim3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblTongDTNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBDNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Thống kê d. thu ngày", jPanel5);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(149, 26, 40));
        jLabel16.setText("DANH SÁCH KHÁCH HÀNG MUA NHIỀU HÀNG NHẤT");

        tblTKKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tblTKKH);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Thống kê khách hàng", jPanel4);

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPane)
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addComponent(TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBody, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1000, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setBackground(new Color(232, 17, 35));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseEntered
        btnMinimize.setBackground(new Color(229, 229, 229));
    }//GEN-LAST:event_btnMinimizeMouseEntered

    private void btnMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseExited
        btnMinimize.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnMinimizeMouseExited

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        setState(ICONIFIED);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void pnlHeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_pnlHeaderMousePressed

    private void pnlHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_pnlHeaderMouseDragged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        fillBanRa(0, fromBR.getDate(), toBR.getDate());
        fillNhapVao(0, fromNV.getDate(), toNV.getDate());
        fillDTThang(0, fromDTThang.getDate(), toDTThang.getDate());
        fillDTNgay(0, fromDTNgay.getDate(), toDTNgay.getDate());
        fillTKKH();
    }//GEN-LAST:event_formWindowOpened

    private void tblDTThangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDTThangMouseClicked
        int index = tblDTThang.getSelectedRow();
        if (index > 0) {
            tangGiam(index);
        }
    }//GEN-LAST:event_tblDTThangMouseClicked

    private void cboBanRaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBanRaActionPerformed
        chon = cboBanRa.getSelectedIndex();
        fillBanRa(chon, fromBR.getDate(), toBR.getDate());
    }//GEN-LAST:event_cboBanRaActionPerformed

    private void cboNhapVaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNhapVaoActionPerformed
        chon = cboNhapVao.getSelectedIndex();
        fillNhapVao(chon, fromNV.getDate(), toNV.getDate());
    }//GEN-LAST:event_cboNhapVaoActionPerformed

    private void cboDTThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDTThangActionPerformed
        chon = cboDTThang.getSelectedIndex();
        fillDTThang(chon, fromDTThang.getDate(), toDTThang.getDate());
    }//GEN-LAST:event_cboDTThangActionPerformed

    private void cboDTNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDTNgayActionPerformed
        chon = cboDTNgay.getSelectedIndex();
        fillDTNgay(chon, fromDTNgay.getDate(), toDTNgay.getDate());
    }//GEN-LAST:event_cboDTNgayActionPerformed

    private void lblTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimMouseClicked
        chon = cboBanRa.getSelectedIndex();
        fillBanRa(chon, fromBR.getDate(), toBR.getDate());
    }//GEN-LAST:event_lblTimMouseClicked

    private void lblTim1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTim1MouseClicked
        chon = cboNhapVao.getSelectedIndex();
        fillNhapVao(chon, fromNV.getDate(), toNV.getDate());
    }//GEN-LAST:event_lblTim1MouseClicked

    private void lblTim2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTim2MouseClicked
        chon = cboDTThang.getSelectedIndex();
        fillDTThang(chon, fromDTThang.getDate(), toDTThang.getDate());
    }//GEN-LAST:event_lblTim2MouseClicked

    private void lblTim3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTim3MouseClicked
        chon = cboDTNgay.getSelectedIndex();
        fillDTNgay(chon, fromDTNgay.getDate(), toDTNgay.getDate());
    }//GEN-LAST:event_lblTim3MouseClicked

    private void lblBDBRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBDBRMouseClicked
        new ChartBRFrm(tblBanRa).setVisible(true);
    }//GEN-LAST:event_lblBDBRMouseClicked

    private void lblBDNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBDNVMouseClicked
        new ChartNVFrm(tblNhapVao).setVisible(true);
    }//GEN-LAST:event_lblBDNVMouseClicked

    private void lblBDThangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBDThangMouseClicked
        new ChartDTThangFrm(tblDTThang).setVisible(true);
    }//GEN-LAST:event_lblBDThangMouseClicked

    private void lblBDNgayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBDNgayMouseClicked
        new ChartDTNgayFrm(tblDTNgay).setVisible(true);
    }//GEN-LAST:event_lblBDNgayMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongKeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ThongKeFrm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JComboBox<String> cboBanRa;
    private javax.swing.JComboBox<String> cboDTNgay;
    private javax.swing.JComboBox<String> cboDTThang;
    private javax.swing.JComboBox<String> cboNhapVao;
    private com.toedter.calendar.JDateChooser fromBR;
    private com.toedter.calendar.JDateChooser fromDTNgay;
    private com.toedter.calendar.JDateChooser fromDTThang;
    private com.toedter.calendar.JDateChooser fromNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblBDBR;
    private javax.swing.JLabel lblBDNV;
    private javax.swing.JLabel lblBDNgay;
    private javax.swing.JLabel lblBDThang;
    private javax.swing.JLabel lblSSTG;
    private javax.swing.JLabel lblTim;
    private javax.swing.JLabel lblTim1;
    private javax.swing.JLabel lblTim2;
    private javax.swing.JLabel lblTim3;
    private javax.swing.JLabel lblTongBR;
    private javax.swing.JLabel lblTongDTNgay;
    private javax.swing.JLabel lblTongDTThang;
    private javax.swing.JLabel lblTongNV;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JTable tblBanRa;
    private javax.swing.JTable tblDTNgay;
    private javax.swing.JTable tblDTThang;
    private javax.swing.JTable tblNhapVao;
    private javax.swing.JTable tblTKKH;
    private com.toedter.calendar.JDateChooser toBR;
    private com.toedter.calendar.JDateChooser toDTNgay;
    private com.toedter.calendar.JDateChooser toDTThang;
    private com.toedter.calendar.JDateChooser toNV;
    // End of variables declaration//GEN-END:variables
}

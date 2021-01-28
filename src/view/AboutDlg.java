package view;

import helper.XShare;
import java.awt.Image;
import javax.swing.ImageIcon;

public class AboutDlg extends javax.swing.JDialog {

    public AboutDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    private void init() {
        lblHoTen.setText(XShare.USER.getHoTen());
        lblTenDN.setText("Tên đăng nhập: " + XShare.USER.getTenDangNhap());
        lblEmail.setText("Email: " + XShare.USER.getEmail());
        lblVaiTro.setText(XShare.USER.isVaiTro() ? "Vai trò: Trưởng phòng" : "Vai trò: Nhân viên");
        try {
            ImageIcon icon = new ImageIcon(XShare.readLogo(XShare.USER.getQrCode()).getImage().getScaledInstance(lblQR.getWidth(), lblQR.getHeight(), Image.SCALE_SMOOTH));
            lblQR.setIcon(icon);
        } catch (Exception e) {
            lblQR.setIcon(null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblQR = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblTenDN = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/qrcode.png"))); // NOI18N
        jPanel1.add(lblQR, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 256, 256));

        lblHoTen.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
        lblHoTen.setForeground(new java.awt.Color(204, 204, 204));
        lblHoTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoTen.setText("Tuấn Kiệt");
        jPanel1.add(lblHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 240, -1));

        lblTenDN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTenDN.setForeground(new java.awt.Color(204, 204, 204));
        lblTenDN.setText("Tên đăng nhập: kietnt");
        jPanel1.add(lblTenDN, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(204, 204, 204));
        lblEmail.setText("Email: kietnt@mail.com");
        jPanel1.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        lblVaiTro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblVaiTro.setForeground(new java.awt.Color(204, 204, 204));
        lblVaiTro.setText("Vai trò: Trưởng phòng");
        jPanel1.add(lblVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/splash.gif"))); // NOI18N
        lblLogo.setPreferredSize(new java.awt.Dimension(700, 470));
        lblLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoMouseClicked(evt);
            }
        });
        jPanel1.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 470));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoMouseClicked
        dispose();
    }//GEN-LAST:event_lblLogoMouseClicked

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
            java.util.logging.Logger.getLogger(AboutDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AboutDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AboutDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AboutDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            AboutDlg dialog = new AboutDlg(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblQR;
    private javax.swing.JLabel lblTenDN;
    private javax.swing.JLabel lblVaiTro;
    // End of variables declaration//GEN-END:variables
}

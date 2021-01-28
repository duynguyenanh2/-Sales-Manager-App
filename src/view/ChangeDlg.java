package view;

import dao.TaiKhoanDao;
import helper.XDialog;
import helper.XShare;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import model.TaiKhoan;

public class ChangeDlg extends javax.swing.JDialog {

    boolean showold, shownew = true;
    int xMouse, yMouse;
    TaiKhoanDao dao = new TaiKhoanDao();

    public ChangeDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    private void init() {
        txtUsername.setText(XShare.USER.getTenDangNhap());
    }

    private void change() {
        String username = txtUsername.getText().trim();
        String oldpass = txtOldPass.getText().trim();
        String newpass = txtNewPass.getText().trim();
        String repass = txtRePass.getText().trim();

        if (oldpass.isEmpty()) {
            XDialog.alert(this, "Nhập mật khẩu cũ!");
            txtOldPass.requestFocus();
            return;
        } else if (!oldpass.equals(XShare.USER.getMatKhau())) {
            XDialog.alert(this, "Mật khẩu cũ không trùng khớp!");
            txtOldPass.requestFocus();
            return;
        }

        if (newpass.isEmpty()) {
            XDialog.alert(this, "Nhập mật khẩu mới!");
            txtNewPass.requestFocus();
            return;
        } else if (newpass.length() < 8) {
            XDialog.alert(this, "Mật khẩu phải từ 8 ký tự!");
            txtNewPass.requestFocus();
            return;
        }

        if (repass.isEmpty()) {
            XDialog.alert(this, "Xác nhận lại mật khẩu!");
            txtRePass.requestFocus();
            return;
        } else if (!repass.equals(newpass)) {
            XDialog.alert(this, "Mật khẩu không trùng khớp!");
            txtRePass.requestFocus();
            return;
        }

        try {
            TaiKhoan tk = new TaiKhoan(username, newpass);
            dao.change(tk);
            XShare.CHG = true;
            XDialog.alert(this, "Đổi mật khẩu thành công!");
            dispose();
        } catch (Exception e) {
            XDialog.alert(this, "Đổi mật khẩu thất bại!");
        }
    }

    private void showOldPass() {
        if (showold) {
            txtOldPass.setEchoChar((char) 0);
        } else {
            txtOldPass.setEchoChar('\u002a');
        }
        showold = !showold;
    }

    private void showNewPass() {
        if (shownew) {
            txtNewPass.setEchoChar((char) 0);
        } else {
            txtNewPass.setEchoChar('\u002a');
        }
        shownew = !shownew;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnlTop = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblExit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblViewC = new javax.swing.JLabel();
        txtOldPass = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        lblViewM = new javax.swing.JLabel();
        txtNewPass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtRePass = new javax.swing.JPasswordField();
        btnChange = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(236, 236, 236));

        jPanel2.setBackground(new java.awt.Color(27, 28, 30));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTop.setBackground(new java.awt.Color(27, 28, 30));
        pnlTop.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlTopMouseDragged(evt);
            }
        });
        pnlTop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTopMousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(236, 236, 236));
        jLabel4.setText("ĐỔI MẬT KHẨU");

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel2.add(pnlTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblExit.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblExit.setForeground(new java.awt.Color(236, 236, 236));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setText("╳");
        lblExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExitMouseExited(evt);
            }
        });
        jPanel2.add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 0, 42, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(236, 236, 236));
        jLabel1.setText("Tên đăng nhập");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 68, -1, 39));

        txtUsername.setEditable(false);
        txtUsername.setBackground(new java.awt.Color(27, 28, 30));
        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(236, 236, 236));
        txtUsername.setText("username");
        txtUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(236, 236, 236)));
        txtUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsernameFocusLost(evt);
            }
        });
        txtUsername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtUsernameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtUsernameMouseExited(evt);
            }
        });
        jPanel2.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 113, 333, 39));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(236, 236, 236));
        jLabel2.setText("Mật khẩu cũ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 170, -1, 39));

        lblViewC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eye.png"))); // NOI18N
        lblViewC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblViewC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblViewCMouseClicked(evt);
            }
        });
        jPanel2.add(lblViewC, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, 30));

        txtOldPass.setBackground(new java.awt.Color(27, 28, 30));
        txtOldPass.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtOldPass.setForeground(new java.awt.Color(236, 236, 236));
        txtOldPass.setText("password");
        txtOldPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(236, 236, 236)));
        txtOldPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOldPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtOldPassFocusLost(evt);
            }
        });
        txtOldPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtOldPassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtOldPassMouseExited(evt);
            }
        });
        jPanel2.add(txtOldPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 215, 333, 39));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(236, 236, 236));
        jLabel5.setText("Mật khẩu mới");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 272, -1, 39));

        lblViewM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eye.png"))); // NOI18N
        lblViewM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblViewM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblViewMMouseClicked(evt);
            }
        });
        jPanel2.add(lblViewM, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, -1, 30));

        txtNewPass.setBackground(new java.awt.Color(27, 28, 30));
        txtNewPass.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNewPass.setForeground(new java.awt.Color(236, 236, 236));
        txtNewPass.setText("password");
        txtNewPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(236, 236, 236)));
        txtNewPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNewPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNewPassFocusLost(evt);
            }
        });
        txtNewPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtNewPassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtNewPassMouseExited(evt);
            }
        });
        txtNewPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNewPassKeyPressed(evt);
            }
        });
        jPanel2.add(txtNewPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 317, 333, 39));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(236, 236, 236));
        jLabel3.setText("Xác nhận mật khẩu");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 374, -1, 39));

        txtRePass.setBackground(new java.awt.Color(27, 28, 30));
        txtRePass.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtRePass.setForeground(new java.awt.Color(236, 236, 236));
        txtRePass.setText("password");
        txtRePass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(236, 236, 236)));
        txtRePass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRePassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRePassFocusLost(evt);
            }
        });
        txtRePass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtRePassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtRePassMouseExited(evt);
            }
        });
        txtRePass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRePassKeyPressed(evt);
            }
        });
        jPanel2.add(txtRePass, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 419, 333, 39));

        btnChange.setBackground(new java.awt.Color(33, 140, 60));
        btnChange.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnChange.setForeground(new java.awt.Color(255, 255, 255));
        btnChange.setText("Thay đổi");
        btnChange.setBorder(null);
        btnChange.setContentAreaFilled(false);
        btnChange.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChange.setOpaque(true);
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });
        jPanel2.add(btnChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 476, 333, 42));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusGained
        if (txtUsername.getText().equals("username")) {
            txtUsername.setText("");
            txtUsername.setForeground(new Color(236, 236, 236));
        }
    }//GEN-LAST:event_txtUsernameFocusGained

    private void txtUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusLost
        if (txtUsername.getText().equals("") || txtUsername.getText().equals("username")) {
            txtUsername.setText("username");
            txtUsername.setForeground(new Color(236, 236, 236));
        }
    }//GEN-LAST:event_txtUsernameFocusLost

    private void txtUsernameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsernameMouseEntered
        Border border = BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(25, 181, 254));
        txtUsername.setBorder(border);
    }//GEN-LAST:event_txtUsernameMouseEntered

    private void txtUsernameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsernameMouseExited
        Border border = BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(236, 236, 236));
        txtUsername.setBorder(border);
    }//GEN-LAST:event_txtUsernameMouseExited

    private void txtOldPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOldPassFocusGained
        String pass = String.valueOf(txtOldPass.getPassword());
        if (pass.equals("password")) {
            txtOldPass.setText("");
            txtOldPass.setForeground(new Color(236, 236, 236));
        }
    }//GEN-LAST:event_txtOldPassFocusGained

    private void txtOldPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOldPassFocusLost
        String pass = String.valueOf(txtOldPass.getPassword());
        if (pass.equals("") || pass.equals("password")) {
            txtOldPass.setText("password");
            txtOldPass.setForeground(new Color(236, 236, 236));
        }
    }//GEN-LAST:event_txtOldPassFocusLost

    private void txtOldPassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOldPassMouseEntered
        Border border = BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(25, 181, 254));
        txtOldPass.setBorder(border);
    }//GEN-LAST:event_txtOldPassMouseEntered

    private void txtOldPassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOldPassMouseExited
        Border border = BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(236, 236, 236));
        txtOldPass.setBorder(border);
    }//GEN-LAST:event_txtOldPassMouseExited

    private void txtNewPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNewPassFocusGained
        String pass = String.valueOf(txtNewPass.getPassword());
        if (pass.equals("password")) {
            txtNewPass.setText("");
            txtNewPass.setForeground(new Color(236, 236, 236));
        }
    }//GEN-LAST:event_txtNewPassFocusGained

    private void txtNewPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNewPassFocusLost
        String pass = String.valueOf(txtNewPass.getPassword());
        if (pass.equals("") || pass.equals("password")) {
            txtNewPass.setText("password");
            txtNewPass.setForeground(new Color(236, 236, 236));
        }
    }//GEN-LAST:event_txtNewPassFocusLost

    private void txtNewPassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNewPassMouseEntered
        Border border = BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(25, 181, 254));
        txtNewPass.setBorder(border);
    }//GEN-LAST:event_txtNewPassMouseEntered

    private void txtNewPassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNewPassMouseExited
        Border border = BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(236, 236, 236));
        txtNewPass.setBorder(border);
    }//GEN-LAST:event_txtNewPassMouseExited

    private void txtNewPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewPassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            change();
        }
    }//GEN-LAST:event_txtNewPassKeyPressed

    private void txtRePassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRePassFocusGained
        String pass = String.valueOf(txtRePass.getPassword());
        if (pass.equals("password")) {
            txtRePass.setText("");
            txtRePass.setForeground(new Color(236, 236, 236));
        }
    }//GEN-LAST:event_txtRePassFocusGained

    private void txtRePassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRePassFocusLost
        String pass = String.valueOf(txtRePass.getPassword());
        if (pass.equals("") || pass.equals("password")) {
            txtRePass.setText("password");
            txtRePass.setForeground(new Color(236, 236, 236));
        }
    }//GEN-LAST:event_txtRePassFocusLost

    private void txtRePassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRePassMouseEntered
        Border border = BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(25, 181, 254));
        txtRePass.setBorder(border);
    }//GEN-LAST:event_txtRePassMouseEntered

    private void txtRePassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRePassMouseExited
        Border border = BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(236, 236, 236));
        txtRePass.setBorder(border);
    }//GEN-LAST:event_txtRePassMouseExited

    private void txtRePassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRePassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            change();
        }
    }//GEN-LAST:event_txtRePassKeyPressed

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        dispose();
    }//GEN-LAST:event_lblExitMouseClicked

    private void lblExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseEntered
        lblExit.setForeground(new Color(255, 0, 0));
    }//GEN-LAST:event_lblExitMouseEntered

    private void lblExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseExited
        lblExit.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_lblExitMouseExited

    private void pnlTopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_pnlTopMousePressed

    private void pnlTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_pnlTopMouseDragged

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        change();
    }//GEN-LAST:event_btnChangeActionPerformed

    private void lblViewCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViewCMouseClicked
        showOldPass();
    }//GEN-LAST:event_lblViewCMouseClicked

    private void lblViewMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViewMMouseClicked
        showNewPass();
    }//GEN-LAST:event_lblViewMMouseClicked

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
            java.util.logging.Logger.getLogger(ChangeDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangeDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangeDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangeDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            ChangeDlg dialog = new ChangeDlg(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnChange;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblViewC;
    private javax.swing.JLabel lblViewM;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JPasswordField txtOldPass;
    private javax.swing.JPasswordField txtRePass;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}

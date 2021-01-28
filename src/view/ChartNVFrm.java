package view;

import helper.XShare;
import java.awt.Color;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartNVFrm extends javax.swing.JFrame {

    int xMouse, yMouse;
    JTable table;

    public ChartNVFrm(JTable table) {
        initComponents();
        this.table = table;
        init();
    }

    private void init() {
        setIconImage(XShare.APP_ICON);
        setBackground(new Color(0, 0, 0, 0));
        showChart(table);
    }

    private void showChart(JTable table) {
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();

        for (int i = 0; i < table.getRowCount(); i++) {
            dcd.setValue((Integer) table.getValueAt(i, 2), "Số lượng", (String) table.getValueAt(i, 1));
        }

        JFreeChart chart = ChartFactory.createBarChart("Số lượng nhập vào theo sản phẩm", "Sản phẩm", "Số lượng", dcd, PlotOrientation.VERTICAL, true, true, false);

        CategoryPlot plot = chart.getCategoryPlot();

        plot.setBackgroundPaint(Color.DARK_GRAY);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
        ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());

        ChartPanel panel = new ChartPanel(chart);
        pnlReport.removeAll();
        pnlReport.add(panel);
        pnlReport.updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnXem = new javax.swing.JButton();
        lblClose = new javax.swing.JLabel();
        pnlReport = new javax.swing.JPanel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnXem.setBackground(new java.awt.Color(123, 123, 123));
        btnXem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graph.png"))); // NOI18N
        btnXem.setContentAreaFilled(false);
        btnXem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXem.setFocusable(false);
        btnXem.setPreferredSize(new java.awt.Dimension(40, 40));
        btnXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemActionPerformed(evt);
            }
        });
        getContentPane().add(btnXem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 40, 40));

        lblClose.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblClose.setForeground(new java.awt.Color(255, 0, 0));
        lblClose.setText("Close");
        lblClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
        });
        getContentPane().add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, -1, -1));

        pnlReport.setBackground(new java.awt.Color(102, 102, 102));
        pnlReport.setLayout(new javax.swing.BoxLayout(pnlReport, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(pnlReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1000, 520));

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.png"))); // NOI18N
        lblBackground.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblBackgroundMouseDragged(evt);
            }
        });
        lblBackground.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblBackgroundMousePressed(evt);
            }
        });
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        setSize(new java.awt.Dimension(1000, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblBackgroundMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackgroundMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_lblBackgroundMousePressed

    private void lblBackgroundMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackgroundMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_lblBackgroundMouseDragged

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        dispose();
    }//GEN-LAST:event_lblCloseMouseClicked

    private void btnXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemActionPerformed
        showChart(table);
    }//GEN-LAST:event_btnXemActionPerformed

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
            java.util.logging.Logger.getLogger(ChartNVFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChartNVFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChartNVFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChartNVFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            //new dashboard().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXem;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblClose;
    private javax.swing.JPanel pnlReport;
    // End of variables declaration//GEN-END:variables
}

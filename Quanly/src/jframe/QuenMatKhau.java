package jframe;

import dao.Email;
import dao.KetNoisql;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class QuenMatKhau extends javax.swing.JFrame {
    //khởi tạo một đối tượng kết nối CSDL thông qua class ketnoisql
    KetNoisql cn = new KetNoisql();
    //Khai báo biến kết nối CSDL
    Connection conn;
    //Khởi tạo đối tượng email
    Email em = new Email();
    public QuenMatKhau() {
        initComponents();
        // Thay đổi logo và tiêu đề
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/logo.png"));
        setIconImage(icon.getImage());
        setTitle("Quên mật khẩu");
        //Màn hình xuất hiện ở trung tâm màn hình
        setLocationRelativeTo(null);
        //Giao diện cố định
        setResizable(false);
    }
    
    private void layMa(){
        // Lấy kết nối CSDL thông qua phương thức ketNoi() của đối tượng ketnoisql.
        conn = cn.ketNoi();
        //Kiểm tra xem các đối tượng được nhập vào có trống hay không
        if(txttimkiem.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập tên tài khoản hoặc email đăng kí");
            return;
        }
        try {
            //Tạo một PreparedStatement để truy vấn CSDL với câu lệnh SQL đã được khai báo trước đó.
            PreparedStatement pst = conn.prepareStatement("select * from nguoidung where email = ? or manguoidung = ? ");
            pst.setString(1, txttimkiem.getText());
            pst.setString(2, txttimkiem.getText());
            //Thực thi truy vấn CSDL và lưu kết quả trả về vào đối tượng ResultSet rs.
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                em.MaXacThuc(rs.getString("email"));
                //hiển thị một thông báo thành công
                JOptionPane.showMessageDialog(this,"Mã xác nhận đã được gửi đến " + rs.getString("email"));
            }
            else{
                //hiển thị một thông báo thành công
                JOptionPane.showMessageDialog(this,"Không tìm thấy");
            }
            //giải phóng bộ nhớ
            pst.close();
            rs.close();
            conn.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this,e);
        }
    }
    
    private void xacNhan(){
        // Lấy kết nối CSDL thông qua phương thức ketNoi() của đối tượng ketnoisql.
        conn = cn.ketNoi();
        //Kiểm tra xem các đối tượng được nhập vào có trống hay không
        if(txttimkiem.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập tên tài khoản hoặc email đăng kí");
            return;
        }
        if(txtmaxacthuc.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập mã xác thực");
            return;
        }
        try {
            //Tạo một PreparedStatement để truy vấn CSDL với câu lệnh SQL đã được khai báo trước đó.
            PreparedStatement pst = conn.prepareStatement("select * from nguoidung where email = ? or manguoidung = ? ");
            pst.setString(1, txttimkiem.getText());
            pst.setString(2, txttimkiem.getText());
            //Thực thi truy vấn CSDL và lưu kết quả trả về vào đối tượng ResultSet rs.
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                if(rs.getString("maxacnhan").equals(txtmaxacthuc.getText())){
                    em.MatKhau(rs.getString("email"));
                    //hiển thị một thông báo thành công
                    JOptionPane.showMessageDialog(this,"Mật khẩu mới đã được gửi đến " + rs.getString("email"));
                }
                else{
                    JOptionPane.showMessageDialog(this,"Mã xác nhận không đúng");
                }
            }
            else{
                //hiển thị một thông báo thành công
                JOptionPane.showMessageDialog(this,"Không tìm thấy");
            }
            //giải phóng bộ nhớ
            pst.close();
            rs.close();
            conn.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this,e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtmaxacthuc = new javax.swing.JTextField();
        btnlayma = new javax.swing.JButton();
        btnxacnhan = new javax.swing.JButton();
        laexit = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 5));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Nhập mã xác nhận:");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        btnlayma.setBackground(new java.awt.Color(153, 153, 153));
        btnlayma.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnlayma.setForeground(new java.awt.Color(204, 0, 0));
        btnlayma.setText("Lấy mã");
        btnlayma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlaymaActionPerformed(evt);
            }
        });

        btnxacnhan.setBackground(new java.awt.Color(153, 153, 153));
        btnxacnhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxacnhan.setForeground(new java.awt.Color(204, 0, 0));
        btnxacnhan.setText("Xác nhận");
        btnxacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxacnhanActionPerformed(evt);
            }
        });

        laexit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        laexit.setForeground(new java.awt.Color(242, 242, 242));
        laexit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        laexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png"))); // NOI18N
        laexit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                laexitFocusGained(evt);
            }
        });
        laexit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laexitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                laexitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                laexitMouseExited(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Nhập tên tài khoản hoặc email đăng kí");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Quên mật khẩu:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(laexit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnxacnhan)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnlayma, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmaxacthuc)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                    .addComponent(txttimkiem)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(laexit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(btnlayma, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(txtmaxacthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnxacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void laexitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_laexitFocusGained
        laexit.setOpaque(true);
    }//GEN-LAST:event_laexitFocusGained

    private void laexitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laexitMouseClicked
        this.dispose();
    }//GEN-LAST:event_laexitMouseClicked

    private void laexitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laexitMouseEntered
        laexit.setBackground(new Color(255,255,255));
        //dùng để hiển thị background label
        laexit.setOpaque(true);
    }//GEN-LAST:event_laexitMouseEntered

    private void laexitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laexitMouseExited
        laexit.setBackground(new Color(204,204,204));
        laexit.setOpaque(false);
    }//GEN-LAST:event_laexitMouseExited

    private void btnlaymaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlaymaActionPerformed
        layMa();
    }//GEN-LAST:event_btnlaymaActionPerformed

    private void btnxacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhanActionPerformed
        xacNhan();
    }//GEN-LAST:event_btnxacnhanActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuenMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlayma;
    private javax.swing.JButton btnxacnhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel laexit;
    private javax.swing.JTextField txtmaxacthuc;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}

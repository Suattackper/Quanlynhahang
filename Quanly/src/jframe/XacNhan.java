package jframe;

import dao.Email;
import dao.KetNoisql;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class XacNhan extends javax.swing.JFrame {
    //khởi tạo một đối tượng kết nối CSDL thông qua class ketnoisql
    KetNoisql cn = new KetNoisql();
    //Khai báo biến kết nối CSDL
    Connection conn;
    //Khởi tạo đối tượng email
    Email em = new Email();
    String email;
    
    public XacNhan() {
        initComponents();
        // Thay đổi logo và tiêu đề
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/logo.png"));
        setIconImage(icon.getImage());
        setTitle("Xác nhận");
        //Màn hình xuất hiện ở trung tâm màn hình
        setLocationRelativeTo(null);
        //Giao diện cố định
        setResizable(false);
    }
    public XacNhan(String email) {
        this.email = email;
        initComponents();
        // Thay đổi logo và tiêu đề
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/logo.png"));
        setIconImage(icon.getImage());
        setTitle("Xác nhận");
        em.MaXacThuc(email);
        
        //Màn hình xuất hiện ở trung tâm màn hình
        setLocationRelativeTo(null);
        //Giao diện cố định
        setResizable(false);
    }
    
    private void kiemTra(){
        // Lấy kết nối CSDL thông qua phương thức ketNoi() của đối tượng ketnoisql.
        conn = cn.ketNoi();
        //Kiểm tra xem các đối tượng được nhập vào có trống hay không
        if(txtmaxacthuc.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập mã xác nhận");
            return;
        }
        try {
            //Tạo một PreparedStatement để truy vấn CSDL với câu lệnh SQL đã được khai báo trước đó.
            PreparedStatement pst = conn.prepareStatement("Select * from nguoidung where email = ? ");
            pst.setString(1, email);
            //Thực thi truy vấn CSDL và lưu kết quả trả về vào đối tượng ResultSet rs.
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                if(rs.getString("maxacnhan").equals(txtmaxacthuc.getText())){
                    //xóa mã xác nhận 
                    PreparedStatement pst1 = conn.prepareStatement("Update nguoidung set maxacnhan = null where email = ?");
                    pst1.setString(1, email);
                    //thực hiện cật nhật dữ liệu
                    pst1.executeUpdate();
                    //hiển thị một thông báo thành công
                    JOptionPane.showMessageDialog(this,"Đăng ký thành công");
                    //đóng
                    this.dispose();
                    //giải phóng bộ nhớ
                    pst1.close();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Mã xác nhận không đúng");
                    return;
                }
                //giải phóng bộ nhớ
                pst.close();
                rs.close();
                conn.close();
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtmaxacthuc = new javax.swing.JTextField();
        btnguilai = new javax.swing.JButton();
        btnxacnhan = new javax.swing.JButton();
        laexit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 5));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Nhập mã xác nhận:");

        btnguilai.setBackground(new java.awt.Color(153, 153, 153));
        btnguilai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnguilai.setForeground(new java.awt.Color(204, 0, 0));
        btnguilai.setText("Gửi lại");
        btnguilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguilaiActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtmaxacthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(laexit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnguilai, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnxacnhan)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(laexit)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtmaxacthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguilai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        //System.exit(0);
        // Lấy kết nối CSDL thông qua phương thức ketNoi() của đối tượng ketnoisql.
        conn = cn.ketNoi();
        try {
            //xóa dữ liệu đã nhập ở form đăng kí
            //Tạo một PreparedStatement để truy vấn CSDL với câu lệnh SQL đã được khai báo trước đó.
            PreparedStatement pst = conn.prepareStatement("delete from nguoidung where email = ?");
            pst.setString(1, email);
            //thực hiện cật nhật dữ liệu
            pst.executeUpdate();
            //hiển thị một thông báo thành công
            JOptionPane.showMessageDialog(this,"Đăng ký thất bại");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this,e);
        }
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

    private void btnxacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhanActionPerformed
        kiemTra();
    }//GEN-LAST:event_btnxacnhanActionPerformed

    private void btnguilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguilaiActionPerformed
        JOptionPane.showMessageDialog(this,"Mã xác nhận đã được gửi đến " + email);
        em.MaXacThuc(email);
    }//GEN-LAST:event_btnguilaiActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XacNhan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnguilai;
    private javax.swing.JButton btnxacnhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel laexit;
    private javax.swing.JTextField txtmaxacthuc;
    // End of variables declaration//GEN-END:variables
}

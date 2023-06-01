
package com.raven.table;
import dao.KetNoisql;
import dao.inhoadon;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ListtTable extends javax.swing.JFrame {
    //khởi tạo một đối tượng kết nối CSDL thông qua class ketnoisql
    KetNoisql cn = new KetNoisql();
    //Khai báo biến kết nối CSDL
    Connection conn;
    DefaultTableModel tableModel = new DefaultTableModel();
    int tong = 0;
    String mkm = "";
    int giam = 0;
    
    public ListtTable(){
        initComponents();
        // Thay đổi logo và tiêu đề
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/logo.png"));
        setIconImage(icon.getImage());
        setTitle("Thanh toán");
        // hiển thị dữ liệu từ CSDL lên bảng
        loadBang();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Danh sách bàn ăn");
        pack();
        setLocationRelativeTo(null);
            
    }
    public void updateTable(){
        conn = cn.ketNoi();
        String sql = "SELECT * from ban";
        try {
            // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
            PreparedStatement pst = conn.prepareStatement(sql);
            // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
            ResultSet rs = pst.executeQuery();
            // tạo một mảng để chứa dữ liệu của từng hàng trong bảng
            Object[] dulieucot = new Object[2];
            while(rs.next()){
                // lấy dữ liệu các cột trong bảng nguoidung và đưa vào mảng dulieucot
                dulieucot[0] = rs.getString("tenban");
                dulieucot[1] = "Trống";
                tableModel.addRow(dulieucot);
            }
            //giải phóng bộ nhớ
            pst.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    public void loadBang(){
        Object columnNames[] = {"Bàn", "Trạng thái"};
        // đặt tên cho các cột của bảng
        tableModel.setColumnIdentifiers(columnNames);
        //đặt mô hình dữ liệu cho bảng
        jTable1.setModel(tableModel);
        // tải dữ liệu từ CSDL vào JTable
        updateTable();
    }
    public void settrangthai(String tenban){
        int rowCount = jTable1.getRowCount();
        int colCount = jTable1.getColumnCount();
        int hang=0,cot=0;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Object cellValue = jTable1.getValueAt(row, col);
                if (cellValue != null && cellValue.toString().equals(tenban)) {
                    hang = row;
                    cot = col;
                }
            }
        }
        if(jTable1.getValueAt(hang, cot+1).toString().equals("Trống")){
            jTable1.setValueAt("Đang phục vụ", hang, cot+1);
        }
        else if(jTable1.getValueAt(hang, cot+1).toString().equals("Đang phục vụ")){
            jTable1.setValueAt("Trống", hang, cot+1);
        }
        System.out.println("fix trang thai");
    }
    public boolean kiemtra(String tenban){
        int rowCount = jTable1.getRowCount();
        int colCount = jTable1.getColumnCount();
        int hang=0,cot=0;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Object cellValue = jTable1.getValueAt(row, col);
                if (cellValue != null && cellValue.toString().equals(tenban)) {
                    hang = row;
                    cot = col;
                }
            }
        }
        if(jTable1.getValueAt(hang, cot+1).toString().equals("Trống")){
            return true;
        }
        else{
            return false;
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        bththanhtoan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmkm = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtmtong = new javax.swing.JTextField();
        btnchon = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        bththanhtoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bththanhtoan.setText("Thanh toán");
        bththanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bththanhtoanActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã khuyến mãi:");

        jLabel2.setText("Số điện thoại:");

        txtmkm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmkmFocusLost(evt);
            }
        });
        txtmkm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmkmKeyPressed(evt);
            }
        });

        jLabel3.setText("Tổng");

        txtmtong.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnchon.setText("Chọn");
        btnchon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtmkm, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(txtsdt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnchon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bththanhtoan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(40, 40, 40))
                    .addComponent(txtmtong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtmkm)
                                .addComponent(btnchon))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmtong, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bththanhtoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsdt))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bththanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bththanhtoanActionPerformed
        //kiểm tra xem có hàng nào trong bảng hay không, ko có  = -1
        if(jTable1.getSelectedRow()==-1){
            if(jTable1.getRowCount()==0){ //Kiểm tra xem bảng có hàng nào không
                JOptionPane.showMessageDialog(null, "Không có gì để thanh toán","Dữ liệu User",JOptionPane.OK_OPTION);
            }
            else{
                JOptionPane.showMessageDialog(null, "Chọn hàng để thanh toán","Dữ liệu User",JOptionPane.OK_OPTION);
            }
        }
        else{
            // Xác định tên cột muốn sửa
            String columnName = "Trạng thái";
            // Lấy chỉ số cột dựa trên tên cột
            int columnIndex = jTable1.getColumnModel().getColumnIndex(columnName);
            // Lấy hàng
            int rowIndex = jTable1.getSelectedRow();
            String tenban = jTable1.getValueAt(rowIndex, 0).toString();
            
            if(kiemtra(tenban)){
                JOptionPane.showMessageDialog(null, "Bàn trống");
            }
            else{
                conn = cn.ketNoi();
                jTable1.setValueAt("Trống", rowIndex, columnIndex);
                if(!txtsdt.getText().equals("")){
                    try {
                        PreparedStatement pst = conn.prepareStatement("select * from khachhang where sodienthoai = ?");
                        pst.setString(1, txtsdt.getText());
                        ResultSet rs = pst.executeQuery();
                        if(rs.next()){
                            PreparedStatement pst1 = conn.prepareStatement("update from khachhang set solan = "
                                    + (rs.getInt("solan")+1) + "where sodienthoai = "+txtsdt.getText());
                            pst1.executeUpdate();
                            pst1.close();
                        }
                        else{
                            PreparedStatement pst1 = conn.prepareStatement("insert into khachhang values(?,default)");
                            pst1.setString(1, txtsdt.getText());
                            pst1.executeUpdate();
                            pst1.close();
                        }
                        pst.close();
                        rs.close();
                    }
                    catch (Exception e) {
                        JOptionPane.showConfirmDialog(null, e);
                    }
                }
                if(!mkm.equals("")){
                    String mab = null;
                    int mahd = 0;
                    //tạo câu lệnh trong CSDL.
                    String sql_kiemtra = "Select * from ban where tenban = ?";
                    try {
                        // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                        PreparedStatement pst = conn.prepareStatement(sql_kiemtra);
                        pst.setString(1, tenban);
                        // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
                        ResultSet rs = pst.executeQuery();
                        System.out.println("a1");
                        if(rs.next()){
                            mab = rs.getString("maban");
                        }
                        String sqlhd = "Select * from hoadon join ban on hoadon.maban=ban.maban where ban.maban = ? order by mahoadon desc";
                        // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                        PreparedStatement psthd = conn.prepareStatement(sqlhd);
                        psthd.setString(1, mab);
                        System.out.println("a3");
                        // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
                        ResultSet rshd = psthd.executeQuery();
                        System.out.println("a4");
                        if(rshd.next()){
                            System.out.println("a5");
                            mahd = rshd.getInt("mahoadon");
                        }
                        System.out.println(mahd);
                        PreparedStatement pstfix = conn.prepareStatement("update hoadon set makhuyenmai = ? where mahoadon = ?");
                        pstfix.setString(1, mkm);
                        pstfix.setInt(2, mahd);
                        pstfix.executeUpdate();
                        pst.close();
                        rs.close();
                        psthd.close();
                        rshd.close();
                        pstfix.close();
                        conn.close();
                    }
                    catch (Exception e) {
                        JOptionPane.showConfirmDialog(null, e);
                    }
                }
                JOptionPane.showMessageDialog(null, "Thanh toán thành công");
                inhoadon in = new inhoadon(tenban);
                in.printInvoice();
                
            }
        }
    }//GEN-LAST:event_bththanhtoanActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        //kiểm tra xem có hàng nào trong bảng hay không, ko có  = -1
        if(jTable1.getSelectedRow()==-1){
            if(jTable1.getRowCount()==0){ //Kiểm tra xem bảng có hàng nào không
                JOptionPane.showMessageDialog(null, "Không có gì để thanh toán","Dữ liệu User",JOptionPane.OK_OPTION);
            }
            else{
                JOptionPane.showMessageDialog(null, "Chọn hàng để thanh toán","Dữ liệu User",JOptionPane.OK_OPTION);
            }
        }
        else{
            txtmkm.setText("");
            giam=0;
            // Lấy hàng
            int rowIndex = jTable1.getSelectedRow();
            String tenban = jTable1.getValueAt(rowIndex, 0).toString();
            if(!kiemtra(tenban)){
                conn = cn.ketNoi();
                String mab = null;
                int mahd = 0;
                //tạo câu lệnh trong CSDL.
                String sql_kiemtra = "Select * from ban where tenban = ?";
                try {
                    // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                    PreparedStatement pst = conn.prepareStatement(sql_kiemtra);
                    pst.setString(1, tenban);
                    // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
                    ResultSet rs = pst.executeQuery();
                    System.out.println("a1");
                    if(rs.next()){
                        mab = rs.getString("maban");
                    }
                    String sqlhd = "Select * from hoadon join ban on hoadon.maban=ban.maban where ban.maban = ? order by mahoadon desc";
                    // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                    PreparedStatement psthd = conn.prepareStatement(sqlhd);
                    psthd.setString(1, mab);
                    System.out.println("a3");
                    // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
                    ResultSet rshd = psthd.executeQuery();
                    System.out.println("a4");
                    if(rshd.next()){
                        mahd = rshd.getInt("mahoadon");
                    }
                    PreparedStatement pstfix = conn.prepareStatement("select * from chitietsanpham c join hoadon h on c.mahoadon=h.mahoadon "
                            + "where c.mahoadon = " + mahd);
                    PreparedStatement pstfix2 = conn.prepareStatement("select * from sanpham where masanpham = ?");
                    System.out.println("a5");
                    ResultSet rsfix = pstfix.executeQuery();
                    tong =0;
                    while(rsfix.next()){
                    System.out.println("a6");
                        pstfix2.setString(1, rsfix.getString("masanpham"));
                        ResultSet rsfix2 = pstfix2.executeQuery();
                        if(rsfix2.next()){
                            tong = tong + rsfix2.getInt("gia")*rsfix.getInt("soluong");
                            System.out.println("tc");
                        }
                        rsfix2.close();
                    }
                    if(tong<1000){
                        DecimalFormat df = new DecimalFormat(" #,### VND ");
                        txtmtong.setText(df.format(tong - giam));
                    }else if(tong>1000){
                        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                        symbols.setDecimalSeparator('.');
                        symbols.setGroupingSeparator(',');
                        DecimalFormat df = new DecimalFormat("#,###.## VND  ", symbols);
                        txtmtong.setText(df.format(tong - giam));
                    }
                    pst.close();
                    rs.close();
                    psthd.close();
                    rshd.close();
                    pstfix.close();
                    conn.close();
                }
                catch (Exception e) {
                    JOptionPane.showConfirmDialog(null, e);
                }
            }
            else{
                tong = 0;
                txtmtong.setText(null);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtmkmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmkmKeyPressed
//        if(evt.getKeyCode()==evt.VK_ENTER){
//            if(!txtmkm.getText().equals("")){
//                try {
//                    conn = cn.ketNoi();
//                    PreparedStatement pst = conn.prepareStatement("select * from khuyenmai where makhuyenmai = ?");
//                    pst.setString(1, txtmkm.getText());
//                    ResultSet rs = pst.executeQuery();
//                    if(rs.next()){
//                        mkm = rs.getString("makhuyenmai");
//                        giam = tong*rs.getInt("giam")/100;
//                        tong = tong - giam;
//                        txtmtong.setText(String.valueOf(tong));
//                    }
//                    else{
//                        JOptionPane.showMessageDialog(null, "Mã khuyến mãi không tồn tại");
//                        txtmkm.setText(null);
//                        txtmtong.setText(String.valueOf(tong));
//                        giam=0;
//                        return;
//                    }
//                    pst.close();
//                    rs.close();
//                    conn.close();
//                }
//                catch (Exception e) {
//                    JOptionPane.showConfirmDialog(null, e);
//                }
//            }
//            else{
//                giam=0;
//                txtmtong.setText(String.valueOf(tong));
//            }
//        }
    }//GEN-LAST:event_txtmkmKeyPressed

    private void txtmkmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmkmFocusLost
//        if(!txtmkm.getText().equals("")){
//                try {
//                    conn = cn.ketNoi();
//                    PreparedStatement pst = conn.prepareStatement("select * from khuyenmai where makhuyenmai = ?");
//                    pst.setString(1, txtmkm.getText());
//                    ResultSet rs = pst.executeQuery();
//                    if(rs.next()){
//                        mkm = rs.getString("makhuyenmai");
//                        giam = tong*rs.getInt("giam")/100;
//                        tong = tong - giam;
//                        txtmtong.setText(String.valueOf(tong));
//                    }
//                    else{
//                        JOptionPane.showMessageDialog(null, "Mã khuyến mãi không tồn tại");
//                        txtmkm.setText(null);
//                        txtmtong.setText(String.valueOf(tong));
//                        giam=0;
//                        return;
//                    }
//                    pst.close();
//                    rs.close();
//                    conn.close();
//                }
//                catch (Exception e) {
//                    JOptionPane.showConfirmDialog(null, e);
//                }
//            }
//            else{
//                giam=0;
//                txtmtong.setText(String.valueOf(tong));
//            }
    }//GEN-LAST:event_txtmkmFocusLost

    private void btnchonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchonActionPerformed
        if(!txtmkm.getText().equals("")){
            try {
                conn = cn.ketNoi();
                PreparedStatement pst = conn.prepareStatement("select * from khuyenmai where makhuyenmai = ?");
                pst.setString(1, txtmkm.getText());
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    mkm = rs.getString("makhuyenmai");
                    giam = tong*rs.getInt("giam")/100;
                    tong = tong - giam;
                    if(tong<1000){
                        DecimalFormat df = new DecimalFormat(" #,### VND ");
                        txtmtong.setText(df.format(tong));
                    }else if(tong>1000){
                        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                        symbols.setDecimalSeparator('.');
                        symbols.setGroupingSeparator(',');
                        DecimalFormat df = new DecimalFormat("#,###.## VND  ", symbols);
                        txtmtong.setText(df.format(tong));
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mã khuyến mãi không tồn tại");
                    txtmkm.setText(null);
                    if(tong<1000){
                        DecimalFormat df = new DecimalFormat(" #,### VND ");
                        txtmtong.setText(df.format(tong));
                    }else if(tong>1000){
                        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                        symbols.setDecimalSeparator('.');
                        symbols.setGroupingSeparator(',');
                        DecimalFormat df = new DecimalFormat("#,###.## VND  ", symbols);
                        txtmtong.setText(df.format(tong));
                    }
                    giam = 0;
                    return;
                }
                pst.close();
                rs.close();
                conn.close();
            }
            catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
            }
        }
        else{
            giam=0;
            if(tong<1000){
                DecimalFormat df = new DecimalFormat(" #,### VND ");
                txtmtong.setText(df.format(tong));
            }else if(tong>1000){
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                symbols.setDecimalSeparator('.');
                symbols.setGroupingSeparator(',');
                DecimalFormat df = new DecimalFormat("#,###.## VND  ", symbols);
                txtmtong.setText(df.format(tong));
            }
        }
    }//GEN-LAST:event_btnchonActionPerformed

   public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //dùng cấu hình giao diện nimbus
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        try {
                            UIManager.setLookAndFeel(info.getClassName());
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ListtTable.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(ListtTable.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(ListtTable.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnsupportedLookAndFeelException ex) {
                            Logger.getLogger(ListtTable.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }
                new ListtTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bththanhtoan;
    private javax.swing.JButton btnchon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtmkm;
    private javax.swing.JTextField txtmtong;
    private javax.swing.JTextField txtsdt;
    // End of variables declaration//GEN-END:variables
}

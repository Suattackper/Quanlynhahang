
package com.raven.bill;

import com.raven.model.ModelItem;
import com.raven.swing.ScrollBar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

   
    public class Bill extends javax.swing.JFrame {
    DefaultTableModel model = new DefaultTableModel();
    private String dataDate;
    
    int Tong = 0;
    String Ban = "";
    public Bill() {
        
      
          
    }
   
    public Bill(ArrayList<ModelItem> list,int value,String dataDate,String Ban){
        initComponents();
         jScrollPane1.setVerticalScrollBar(new ScrollBar());
       bill.setModel(model);
      
         setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         
         //set ngay gio 
         this.dataDate = dataDate;
         this.Ban=Ban;
         
//        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("STT");
        model.addColumn("Tên");
        model.addColumn("Số Lượng");
        model.addColumn("Giá");
        model.addColumn("Tổng");
//        model.addColumn("Hành động");
        
        // them hanh dong cho table
//        TableActionEvent event = new TableActionEvent() {
//            @Override
//            public void onEdit(int row) {
//                System.out.println("Edit row : " +row);
//            }
//
//            @Override
//            public void onDelete(int row) {
//               if(bill.isEnabled()){
//                   bill.getCellEditor().stopCellEditing();
//               }
//                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
//           model.removeRow(row);
//        } else {
//            JOptionPane.showMessageDialog(null, "không xóa");
//        }
//               
//            }
//
//            @Override
//            public void onView(int row) {
//                System.out.println("View row: "+ row);
//            }
//        };
//         bill.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
//         bill.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        z(list,value);
        Calc();
        KhuyenMai();
        JDateTime.setText("Time : "+"\n"+dataDate );
        jLabel_Ban.setText(Ban);
        
//          

    }
    public void z(ArrayList<ModelItem> list,int value){
        int i=1;
        System.out.println("z");
        for (ModelItem modelItem : list) {
            model.addRow(new Object[] {i++, modelItem.getNameItem(),value,modelItem.getPrice(),modelItem.getPrice()*value});
            i++;
         Tong +=modelItem.getPrice()*value;
         if(Tong<1000){
             DecimalFormat df = new DecimalFormat(" #,### VND ");
             jTextField_tong.setText(df.format(Tong ));
             
         }else if(Tong>1000){
             DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat("#,###.## VND  ", symbols);
       jTextField_tong.setText(df.format(Tong ));
        jTextField_ThanhToans.setText(String.valueOf(Tong));
            
         }
        
         
        
        }
       
    }
    public void Calc(){
        jTextField_khachTra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String thanhToanStr = jTextField_ThanhToans.getText();
                String khachTraStr = jTextField_khachTra.getText();
                

                if ( !khachTraStr.isEmpty()) {
                    try {
                        int thanhToan = Integer.parseInt(thanhToanStr);
                        int khachTra = Integer.parseInt(khachTraStr);
                        
                        int tienThua = khachTra - thanhToan;

                        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                        symbols.setDecimalSeparator('.');
                        symbols.setGroupingSeparator(',');
                        DecimalFormat df = new DecimalFormat("#,###.## VND ", symbols);

                        jTextField_TienThua.setText(df.format(tienThua));
                    } catch (NumberFormatException ex) {
                        jTextField_TienThua.setText("");
                    }
                } else {
                    jTextField_TienThua.setText("");
                }
            }
        });
    }
    
    public void KhuyenMai(){
        jTxt_KhuyenMai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String thanhToanStr = jTextField_ThanhToans.getText();
                String KhuyenMai = jTxt_KhuyenMai.getText();
                String okla= "thaodeptrai";
               if ( !KhuyenMai.isEmpty()&& jTxt_KhuyenMai.getText().equals(okla)){
                   
                   int thanhToan = Integer.parseInt(thanhToanStr);
//                   int khuyenMai = ();     
                   int ThanhToanNew = thanhToan - (thanhToan*50)/100;
//                    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
//                        symbols.setDecimalSeparator('.');
//                        symbols.setGroupingSeparator(',');
//                        DecimalFormat df = new DecimalFormat("#,###.## VND ", symbols);
                        jTextField_ThanhToans.setText(String.valueOf(ThanhToanNew));
                   
                } else {
//                            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
//                        symbols.setDecimalSeparator('.');
//                        symbols.setGroupingSeparator(',');
//                       DecimalFormat df = new DecimalFormat("#,###.## VND ", symbols);
                     
                    jTextField_ThanhToans.setText(String.valueOf(Tong));
                }
               
            }
           
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTable();
        imageAvatar1 = new test.ImageAvatar();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JDateTime = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTxt_KhuyenMai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_ThanhToans = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField_tong = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField_khachTra = new javax.swing.JTextField();
        jTextField_TienThua = new javax.swing.JTextField();
        jLabel_Ban = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setName(""); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        bill.setRowHeight(40);
        bill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bill);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 700, 300));

        imageAvatar1.setBorderSize(3);
        imageAvatar1.setBorderSpace(2);
        imageAvatar1.setGradientColor1(new java.awt.Color(250, 84, 84));
        imageAvatar1.setGradientColor2(new java.awt.Color(8, 225, 61));
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/menuicon/logo.jpg"))); // NOI18N

        javax.swing.GroupLayout imageAvatar1Layout = new javax.swing.GroupLayout(imageAvatar1);
        imageAvatar1.setLayout(imageAvatar1Layout);
        imageAvatar1Layout.setHorizontalGroup(
            imageAvatar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );
        imageAvatar1Layout.setVerticalGroup(
            imageAvatar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );

        jPanel1.add(imageAvatar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 329, 102));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SPEAGO RESTAURANT");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 149, -1));

        jLabel5.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel5.setText("HÓA ĐƠN BÁN HÀNG");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

        JDateTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(JDateTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 300, 20));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("***********************************************************************************");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 452, 20));

        jLabel2.setFont(new java.awt.Font("SimSun-ExtB", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SPEAGO RESTAURANT");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 255, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Mã Khuyễn Mãi :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 600, -1, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Thanh Toán :");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 600, -1, 20));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Khách Trả :");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, -1, 20));
        jPanel1.add(jTxt_KhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 590, 120, 34));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tiền Thừa :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 540, -1, 20));
        jPanel1.add(jTextField_ThanhToans, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 590, 110, 34));

        jLabel1.setText("Tổng :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 540, 76, -1));
        jPanel1.add(jTextField_tong, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 532, 120, 30));

        jButton1.setBackground(new java.awt.Color(102, 255, 51));
        jButton1.setText("Thanh Toán");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 580, 106, 40));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setText("In Hóa Đơn");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 650, -1, 41));
        jPanel1.add(jTextField_khachTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 110, 34));
        jPanel1.add(jTextField_TienThua, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, 110, 34));

        jLabel_Ban.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_Ban.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel_Ban, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 80, 20));

        jButton3.setText("Cancel");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 650, 90, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void billMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billMouseClicked
      PanelAction action = new PanelAction();
    }//GEN-LAST:event_billMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Bill().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JDateTime;
    private javax.swing.JTable bill;
    private test.ImageAvatar imageAvatar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_Ban;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_ThanhToans;
    private javax.swing.JTextField jTextField_TienThua;
    private javax.swing.JTextField jTextField_khachTra;
    private javax.swing.JTextField jTextField_tong;
    private javax.swing.JTextField jTxt_KhuyenMai;
    // End of variables declaration//GEN-END:variables
}

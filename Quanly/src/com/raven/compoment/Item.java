
package com.raven.compoment;

import com.raven.model.ModelItem;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;


public class Item extends javax.swing.JPanel{

  

    public boolean isSelected(){
       
        return selected;
    }
    
    public void setSelected(boolean selected){
        this.selected= selected;
        repaint();
     }
    // them san pham moi
    private ModelItem data;
    // get du lieu
    public ModelItem getData() {
        return data;
    }
    public void setData(ModelItem data){
        this.data=data;
        //  thong tin cho item
        pic.setImage(data.getImage());
        lbItemName.setText(data.getNameItem());
        lbDescripion.setText(data.getDescription());
        lbbrand.setText(data.getBrandName());
        DecimalFormat df = new DecimalFormat("#,###");
        lbPrice.setText(df.format(data.getPrice()));
    }
    
    private boolean selected;
    public Item() {
        initComponents();
        setOpaque(false);
        // tao tro chuot hinh ban tay
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
          Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(242,242,242));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20 , 20);
        if(selected){
            // taoj mau cho vong select
            g2.setColor(new Color(94,156,255));
            // ve vong
            g2.drawRoundRect(0 , 0, getWidth()-1, getHeight()-1, 21, 20);
        }
        super.paint(g);
    }
    
    public void isSelect()
    {
        if(selected){
            System.out.println("com.raven.compoment.Item.isSelect()");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbItemName = new javax.swing.JLabel();
        lbDescripion = new javax.swing.JLabel();
        pic = new com.raven.swing.PictureBox();
        lbPrice = new javax.swing.JLabel();
        lbbrand = new javax.swing.JLabel();

        lbItemName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbItemName.setForeground(new java.awt.Color(66, 55, 55));
        lbItemName.setText("Item Name");

        lbDescripion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDescripion.setForeground(new java.awt.Color(196, 184, 184));
        lbDescripion.setText("Description");

        pic.setImage(new javax.swing.ImageIcon(getClass().getResource("/image/img1.png"))); // NOI18N

        lbPrice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(66, 55, 55));
        lbPrice.setText("$0.00");

        lbbrand.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbbrand.setForeground(new java.awt.Color(179, 169, 169));
        lbbrand.setText("brand");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbItemName, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(lbDescripion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbbrand)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbPrice))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbItemName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDescripion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPrice)
                    .addComponent(lbbrand))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbDescripion;
    private javax.swing.JLabel lbItemName;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbbrand;
    private com.raven.swing.PictureBox pic;
    // End of variables declaration//GEN-END:variables
}

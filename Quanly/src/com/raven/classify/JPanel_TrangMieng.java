
package com.raven.classify;

import com.raven.compoment.Item;
import com.raven.events.EvenItem;
import com.raven.model.ModelItem;
import com.raven.swing.ScrollBar;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Xuan Thao
 */
public class JPanel_TrangMieng extends javax.swing.JPanel {

    public void setEvent(EvenItem event) {
        this.eventTA = event;
    }
    
     int tonggia=0;

    public int getTonggia() {
        return tonggia;
    }
    private EvenItem eventTA;
    public JPanel_TrangMieng() {
        initComponents();
        scroll.setVerticalScrollBar(new ScrollBar());
    }

    // them san pham 
    public void addItem(ModelItem data){
       Item item = new Item();
       // goi ham set data de set du lieu cho item
       item.setData(data);
       // them event chuot
       item.addMouseListener(new MouseAdapter() {
           @Override
           // an chuot trai
           public void mousePressed(MouseEvent me) {
               if(SwingUtilities.isLeftMouseButton(me)){
                   eventTA.itemClick(item, data);
               }
           }
       });
       // them panel item
       panelItem.add(item);
       panelItem.repaint();
       panelItem.revalidate(); // cap nhật lại bố cục panel
       
    }
    // nhan dien chon item
     public void setSelected(Component item){
        for(Component com:panelItem.getComponents() ){
            Item i = (Item)com;
            if(i.isSelected()){
                i.setSelected(false);
            }
        }
        ((Item) item).setSelected(true);
    }
     // show item
     public void showItem(ModelItem data){
         lbItemName.setText(data.getNameItem());
         textDescription.setText(data.getDescription());
         lbbrand.setText(data.getBrandName());
         DecimalFormat df = new DecimalFormat("####");
         lbPrice.setText(df.format(data.getPrice()));
     }
     
    public void addRowTable(DefaultTableModel model,ModelItem data, int i,int qty){
         // Thêm dữ liệu vào JTable  
        int gia = (int) data.getPrice()*qty;
        tonggia=0;
        
        model.addRow(new Object[] {i++, lbItemName.getText(),qty,lbPrice.getText(),gia});
        tonggia+=gia;    
    }
     
     // lay vi tri cua item
     public Point getPanelItemLocation(){
         Point p = scroll.getLocation();
         return new Point(p.x,p.y-scroll.getViewport().getViewPosition().y);
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textDescription = new javax.swing.JTextPane();
        lbbrand = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbItemName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        panelItem = new com.raven.swing.PanelItem();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1200, 546));

        jPanel1.setOpaque(false);

        textDescription.setBorder(null);
        textDescription.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textDescription.setForeground(new java.awt.Color(196, 184, 184));

        lbbrand.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbbrand.setForeground(new java.awt.Color(179, 169, 169));
        lbbrand.setText("brand");

        lbPrice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(66, 55, 55));
        lbPrice.setText("000 VND");

        lbItemName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbItemName.setForeground(new java.awt.Color(66, 55, 55));
        lbItemName.setText("Item Name");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quantity :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbItemName))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbPrice))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbbrand))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(textDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbItemName)
                .addGap(0, 0, 0)
                .addComponent(lbPrice)
                .addGap(0, 0, 0)
                .addComponent(lbbrand)
                .addGap(6, 6, 6)
                .addComponent(textDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel1))
        );

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setViewportView(panelItem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbItemName;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbbrand;
    private com.raven.swing.PanelItem panelItem;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextPane textDescription;
    // End of variables declaration//GEN-END:variables
}

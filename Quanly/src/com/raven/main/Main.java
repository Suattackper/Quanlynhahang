package com.raven.main;


//import ComboBoxWithTitleExample;
import com.raven.bill.Bill;
import com.raven.classify.JPanel_Nuoc;
import com.raven.classify.JPanel_ThucAn;
import com.raven.classify.JPanel_TrangMieng;
import com.raven.events.EvenItem;
import com.raven.form.FormHome;
import com.raven.model.ModelItem;
import com.raven.swing.ScrollBar;

import com.raven.table.ListtTable;
import dao.KetNoisql;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;
import javaswingdev.drawer.EventDrawer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import jframe.DangNhap;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;


public class Main extends javax.swing.JFrame {
    //khởi tạo một đối tượng kết nối CSDL thông qua class ketnoisql
    KetNoisql cn = new KetNoisql();
    //Khai báo biến kết nối CSDL
    Connection conn;
//    double gia;
    private FormHome home;
    private Animator animator;
    private Point animatePoint;
    private DrawerController drawer;
    private ModelItem itemSelected;
    private JPanel_ThucAn thucan;
    private JPanel_Nuoc nuoc;
    private JPanel_TrangMieng trangmieng;
    private Bill orderBill;
    ListtTable listTable = new ListtTable();
    String tennguoidung;
    ArrayList<ModelItem> list = new ArrayList<ModelItem>();
    String dataDate;
    DefaultTableModel model = new DefaultTableModel();
    int stt=0;
    int Tong=0;
    int kt=0;
    String banSelect="";
    public Main(){
        initComponents();
        // Thay đổi logo và tiêu đề
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/logo.png"));
        setIconImage(icon.getImage());
        setTitle("Giao diện chính");
        setBackground(new Color(0,0,0,0));
        //combo box chọn bàn
        
        jComboBox_ChonBan.setBorder(javax.swing.BorderFactory.createTitledBorder("Chọn bàn")); // Đặt tiêu đề cho JComboBox
        
        init();
        setTime();
        
        // menu bar
         drawer= Drawer.newDrawer(this)
                 .header(new logo())
//                 .separator(2, new Color(173,173,173))
                 .space(5)
//                 .background(new Color(255,51,51))
                 .backgroundTransparent(0.3f)
//                 .drawerBackground(new Color(238,238,143))
                    .enableScroll(true)
                 .addChild(new DrawerItem("Đăng Nhập").icon(new ImageIcon(getClass().getResource("/menuicon/user.png"))).build())
                 .addChild(new DrawerItem("Data item1").icon(new ImageIcon(getClass().getResource("/menuicon/report.png"))).build())
                 .addChild(new DrawerItem("Data item2").icon(new ImageIcon(getClass().getResource("/menuicon/data.png"))).build())
                 .addChild(new DrawerItem("Data item3").icon(new ImageIcon(getClass().getResource("/menuicon/cont.png"))).build())
                 .addChild(new DrawerItem("Data item3").icon(new ImageIcon(getClass().getResource("/menuicon/income.png"))).build())
                 .addChild(new DrawerItem("Data item3").icon(new ImageIcon(getClass().getResource("/menuicon/expense.png"))).build())
                
                 
                 .addFooter(new DrawerItem("Đăng Xuất").icon(new ImageIcon(getClass().getResource("/menuicon/exit.png"))).build())
                  .event(new EventDrawer() {
            @Override
            public void selected(int Data, DrawerItem item) {
                if(Data==0){
                    System.out.println("z");
                }
                else if(Data==1){
                }
                else if(Data==2){
                }
                else if(Data==3){
                }
                else if(Data==4){
                }
                else if(Data==5){
                }
                else if(Data==6){
                    dispose();
                    new DangNhap().setVisible(true);
                }
            }
                 })
                 .build();
         
               
         
        // hoat hanh tu foem animatePoint đen Targetpoint
        //duration là so lượng hoạt ảnh của item
        animator=PropertySetter.createAnimator(500, mainPanel, "imageLocation", animatePoint,mainPanel.getTargetLocation());
        
        
        
//        animator.addTarget(new PropertySetter(mainPanel, "imageSize",new Dimension(180,120),mainPanel.getTargetSize()));
        // them timing target để compoment k mất ngay lap tuc khi bam cai khac
        animator.addTarget(new TimingTargetAdapter(){
            @Override
            public void end() {
                mainPanel.setImageOld(null);// set anh cu
                
            }
            
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
    }
    public Main(String tennguoidung) {
        this.tennguoidung =  tennguoidung;
        // Thay đổi logo và tiêu đề
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/logo.png"));
        setIconImage(icon.getImage());
        setTitle("Giao diện chính");
        initComponents();
        setBackground(new Color(0,0,0,0));
        //combo box chọn bàn
        
        jComboBox_ChonBan.setBorder(javax.swing.BorderFactory.createTitledBorder("Chọn bàn")); // Đặt tiêu đề cho JComboBox
        
        init();
        setTime();
        
        // menu bar
         drawer= Drawer.newDrawer(this)
                 .header(new logo())
//                 .separator(2, new Color(173,173,173))
                 .space(5)
//                 .background(new Color(255,51,51))
                 .backgroundTransparent(0.3f)
//                 .drawerBackground(new Color(238,238,143))
                    .enableScroll(true)
                 .addChild(new DrawerItem("Đăng Nhập").icon(new ImageIcon(getClass().getResource("/menuicon/user.png"))).build())
                 .addChild(new DrawerItem("Data item1").icon(new ImageIcon(getClass().getResource("/menuicon/report.png"))).build())
                 .addChild(new DrawerItem("Data item2").icon(new ImageIcon(getClass().getResource("/menuicon/data.png"))).build())
                 .addChild(new DrawerItem("Data item3").icon(new ImageIcon(getClass().getResource("/menuicon/cont.png"))).build())
                 .addChild(new DrawerItem("Data item3").icon(new ImageIcon(getClass().getResource("/menuicon/income.png"))).build())
                 .addChild(new DrawerItem("Data item3").icon(new ImageIcon(getClass().getResource("/menuicon/expense.png"))).build())
                
                 
                 .addFooter(new DrawerItem("Đăng Xuất").icon(new ImageIcon(getClass().getResource("/menuicon/exit.png"))).build())
                  .event(new EventDrawer() {
            @Override
            public void selected(int Data, DrawerItem item) {
                if(Data==6){
                    dispose();
                    new DangNhap().setVisible(true);
                }
            }
                  
                 })
                 .build();
         
               
         
        // hoat hanh tu foem animatePoint đen Targetpoint
        //duration là so lượng hoạt ảnh của item
        animator=PropertySetter.createAnimator(500, mainPanel, "imageLocation", animatePoint,mainPanel.getTargetLocation());
        
        
        
//        animator.addTarget(new PropertySetter(mainPanel, "imageSize",new Dimension(180,120),mainPanel.getTargetSize()));
        // them timing target để compoment k mất ngay lap tuc khi bam cai khac
        animator.addTarget(new TimingTargetAdapter(){
            @Override
            public void end() {
                mainPanel.setImageOld(null);// set anh cu
                
            }
            
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
      
    }
    private void init(){
        setcbb();
//        listTable = new ListtTable();
        jScrollPane3.setVerticalScrollBar(new ScrollBar());
        // tạo table
        bill2.setModel(model);
        model.addColumn("STT");
        model.addColumn("Tên");
        model.addColumn("Số Lượng");
        model.addColumn("Giá");
        model.addColumn("Tổng");
        // get table
        
        // taoj home panel
        home = new FormHome();
        thucan = new JPanel_ThucAn();
        nuoc = new JPanel_Nuoc();
        trangmieng = new JPanel_TrangMieng();
//         String[] data = {};                 
         
        // tao nut thoat
        winButton.initEvent(this, backGround1);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(home);

        runData();
        runData_ThucAn();
        runData_Nuoc();
        runData_TrangMieng();
    }
    private void loadbang(String tenban){
        list.clear();
        if(!listTable.kiemtra(jComboBox_ChonBan.getSelectedItem().toString())){
            conn = cn.ketNoi();
            String mahd = null;
            String sql = "Select * from hoadon join ban on hoadon.maban = ban.maban where tenban = ? order by mahoadon desc";
            try {
                // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, tenban);
                // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    mahd = rs.getString("mahoadon");
                    System.out.println(mahd);
                }
                String sqlctsp = "Select * from chitietsanpham ctsp join hoadon hd on ctsp.mahoadon = hd.mahoadon "
                        + "join sanpham sp on sp.masanpham=ctsp.masanpham where ctsp.mahoadon = ?";
                // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                PreparedStatement pstctsp = conn.prepareStatement(sqlctsp);
                pstctsp.setString(1, mahd);
                // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
                ResultSet rsctsp = pstctsp.executeQuery();
                Object[] dulieucot = new Object[5];
                int i=1;
                System.out.println(1);
                int tong=0;
                while(rsctsp.next()){
                    System.out.println(2);
                    dulieucot[0] = i++;
                    dulieucot[1] = rsctsp.getString("tensanpham");
                    dulieucot[2] = rsctsp.getInt("soluong");
                    dulieucot[3] = rsctsp.getInt("gia");
                    dulieucot[4] = rsctsp.getInt("gia")*rsctsp.getInt("soluong");
                    model.addRow(dulieucot);
                    tong = tong + rsctsp.getInt("gia")*rsctsp.getInt("soluong");
                    ModelItem x = new ModelItem(rsctsp.getString("masanpham"),rsctsp.getString("tensanpham"),rsctsp.getInt("gia"));
                    list.add(x);
                }
                jTextField_Tong2.setText(String.valueOf(tong));
                System.out.println(3);
                //Giải phóng bộ nhớ
                pstctsp.close();
                rsctsp.close();
                pst.close();
                rs.close();
                conn.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    private void setcbb(){
        conn = cn.ketNoi();
        String sql = "Select * from ban order by maban asc";
        try {
            //Tạo một PreparedStatement để truy vấn CSDL với câu lệnh SQL đã được khai báo trước đó.
            PreparedStatement pst = conn.prepareStatement(sql);
            //Thực thi truy vấn CSDL và lưu kết quả trả về vào đối tượng ResultSet rs.
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                jComboBox_ChonBan.addItem(rs.getString("tenban"));
            }
            //giải phóng bộ nhớ
            pst.close();
            rs.close();
            conn.close();
        } 
        catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    public void setTime(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy");
                    String time = tf.format(date);
                    Jt_Time.setText(time.split(" ")[0]+" "+time.split(" ")[1]);
                    Jt_date.setText(df.format(date));
                }
            }
        }).start();
    }
    private void runData(){
        home.setEvent(new EvenItem() {
            // override phuong thuc evenitem
            @Override
            public void itemClick(Component com, ModelItem item) {
                       
                System.out.println("z");
                if(itemSelected!=null){
                    mainPanel.setImageOld(itemSelected.getImage());
                }
                System.out.println("z1");
                if(itemSelected != item){
                    System.out.println("z1.5");
                        if(!animator.isRunning()){
                        System.out.println("z2");
                        itemSelected=item;
                        animatePoint= getLocationOf(com); // cho test
                        mainPanel.setImage(item.getImage()); // lay hinh anh
                        mainPanel.setImageLocation(animatePoint); 
                        mainPanel.setImageSize(new Dimension(180,120));
                        mainPanel.repaint();
                        home.setSelected(com); // no selected sửa lại cho bấm 2 lần k bị lặp lại
                        home.showItem(item);
                        System.out.println(item.getPrice());
                        animator.start();
                } 
                }
                System.out.println(item.getItemID());
            }
        });
        conn = cn.ketNoi();
        String sqlsp = "Select * from sanpham INNER JOIN loai on sanpham.maloai=loai.maloai";
        try {
            // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
            PreparedStatement pstsp = conn.prepareStatement(sqlsp);
            // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
            ResultSet rssp = pstsp.executeQuery();
            while(rssp.next()){
                byte[] anh = rssp.getBytes("anh");
                // tạo đối tượng ImageIcon từ mảng byte
                ImageIcon icon = new ImageIcon(anh);
                //Thiết lập kích thước mới cho hình ảnh 
                Image newImg = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
                home.addItem(new ModelItem(rssp.getString("masanpham"),
                        rssp.getString("tensanpham"),
                        rssp.getString("mota"),
                        rssp.getInt("gia"),
                        rssp.getString("tenloai"),
                        new ImageIcon(newImg)));
            }
            //giải phóng bộ nhớ
            pstsp.close();
            rssp.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    private void runData_ThucAn(){
        thucan.setEvent(new EvenItem() {
            // override phuong thuc evenitem
            @Override
            public void itemClick(Component com, ModelItem item) {
                if(itemSelected!=null){
                    mainPanel.setImageOld(itemSelected.getImage());
                }
                if(itemSelected != item){
                       if(!animator.isRunning()){
                        itemSelected=item;
                        animatePoint= getLocationOf(com); // cho test
                        mainPanel.setImage(item.getImage()); // lay hinh anh
                        mainPanel.setImageLocation(animatePoint); 
                        mainPanel.setImageSize(new Dimension(180,120));
                        mainPanel.repaint();
                        thucan.setSelected(com);
                        thucan.showItem(item);
                        animator.start();
                } 
                }
                  

                System.out.println(item.getItemID());
//                home.setSelected(com);
//                home.showItem(item);
            }
        });
        conn = cn.ketNoi();
        String sqlsp = "Select * from sanpham INNER JOIN loai on sanpham.maloai=loai.maloai";
        try {
            // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
            PreparedStatement pstsp = conn.prepareStatement(sqlsp);
            // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
            ResultSet rssp = pstsp.executeQuery();
            while(rssp.next()){
                if(rssp.getString("tenloai").equals("Đồ ăn")){
                    byte[] anh = rssp.getBytes("anh");
                    // tạo đối tượng ImageIcon từ mảng byte
                    ImageIcon icon = new ImageIcon(anh);
                    //Thiết lập kích thước mới cho hình ảnh 
                    Image newImg = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
                    thucan.addItem(new ModelItem(rssp.getString("masanpham"),
                            rssp.getString("tensanpham"),
                            rssp.getString("mota"),
                            rssp.getInt("gia"),
                            rssp.getString("tenloai"),
                            new ImageIcon(newImg)));
                    }
            }
                //giải phóng bộ nhớ
                pstsp.close();
                rssp.close();
                conn.close();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    private void runData_Nuoc(){
        nuoc.setEvent(new EvenItem() {
            // override phuong thuc evenitem
            @Override
            public void itemClick(Component com, ModelItem item) {
                if(itemSelected!=null){
                    mainPanel.setImageOld(itemSelected.getImage());
                }
                if(itemSelected != item){
                       if(!animator.isRunning()){
                        itemSelected=item;
                        animatePoint= getLocationOf(com); // cho test
                        mainPanel.setImage(item.getImage()); // lay hinh anh
                        mainPanel.setImageLocation(animatePoint); 
                        mainPanel.setImageSize(new Dimension(180,120));
                        mainPanel.repaint();
                        nuoc.setSelected(com);
                        nuoc.showItem(item);
                        animator.start();
                } 
                }
                  


            }
        });
        conn = cn.ketNoi();
        String sqlsp = "Select * from sanpham INNER JOIN loai on sanpham.maloai=loai.maloai";
        try {
            // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
            PreparedStatement pstsp = conn.prepareStatement(sqlsp);
            // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
            ResultSet rssp = pstsp.executeQuery();
            while(rssp.next()){
                if(rssp.getString("tenloai").equals("Nước uống")){
                    byte[] anh = rssp.getBytes("anh");
                    // tạo đối tượng ImageIcon từ mảng byte
                    ImageIcon icon = new ImageIcon(anh);
                    //Thiết lập kích thước mới cho hình ảnh 
                    Image newImg = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
                    nuoc.addItem(new ModelItem(rssp.getString("masanpham"),
                            rssp.getString("tensanpham"),
                            rssp.getString("mota"),
                            rssp.getInt("gia"),
                            rssp.getString("tenloai"),
                            new ImageIcon(newImg)));
                    }
            }
                //giải phóng bộ nhớ
                pstsp.close();
                rssp.close();
                conn.close();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    private void runData_TrangMieng(){
        trangmieng.setEvent(new EvenItem() {
            // override phuong thuc evenitem
            @Override
            public void itemClick(Component com, ModelItem item) {
                if(itemSelected!=null){
                    mainPanel.setImageOld(itemSelected.getImage());
                }
                if(itemSelected != item){
                       if(!animator.isRunning()){
                        itemSelected=item;
                        animatePoint= getLocationOf(com); // cho test
                        mainPanel.setImage(item.getImage()); // lay hinh anh
                        mainPanel.setImageLocation(animatePoint); 
                        mainPanel.setImageSize(new Dimension(180,120));
                        mainPanel.repaint();
                        trangmieng.setSelected(com);
                        trangmieng.showItem(item);
                        animator.start();
                } 
                }
                  


            }
        });
        conn = cn.ketNoi();
        String sqlsp = "Select * from sanpham INNER JOIN loai on sanpham.maloai=loai.maloai";
        try {
            // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
            PreparedStatement pstsp = conn.prepareStatement(sqlsp);
            // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
            ResultSet rssp = pstsp.executeQuery();
            while(rssp.next()){
                if(rssp.getString("tenloai").equals("Đồ tráng miệng")){
                    byte[] anh = rssp.getBytes("anh");
                    // tạo đối tượng ImageIcon từ mảng byte
                    ImageIcon icon = new ImageIcon(anh);
                    //Thiết lập kích thước mới cho hình ảnh 
                    Image newImg = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
                    trangmieng.addItem(new ModelItem(rssp.getString("masanpham"),
                            rssp.getString("tensanpham"),
                            rssp.getString("mota"),
                            rssp.getInt("gia"),
                            rssp.getString("tenloai"),
                            new ImageIcon(newImg)));
                    }
            }
                //giải phóng bộ nhớ
                pstsp.close();
                rssp.close();
                conn.close();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
        
    private Point getLocationOf(Component com){
       Point p= home.getPanelItemLocation();
       int x = p.x; // lay vi tri chọn trên panel fromhome
       int y = p.y;
       int itemX= com.getX();// lay vi tri x của item
       int itemY= com.getY();// lay vi tri y cua item
       int left = 10; // ddieu chinh de đep hon
       int top = 70;
       return new Point(x+ itemX+left ,top+itemY+y);
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backGround1 = new com.raven.swing.BackGround();
        jPanel1 = new javax.swing.JPanel();
        backGround2 = new com.raven.swing.BackGround();
        header = new javax.swing.JPanel();
        winButton = new com.raven.win_button.WinButton();
        jButton_ThucAn = new javax.swing.JButton();
        jButton_TrangMieng = new javax.swing.JButton();
        jButton_Nuoc = new javax.swing.JButton();
        jButton_Home = new javax.swing.JButton();
        menubar = new javax.swing.JButton();
        Jt_date = new javax.swing.JLabel();
        Jt_Time = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        mainPanel = new com.raven.swing.MainPanel();
        jButton_Chon = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bill2 = new javax.swing.JTable();
        jTextField_Tong2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btndathang = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jComboBox_ChonBan = new javax.swing.JComboBox<>();
        quantity = new javax.swing.JSpinner();
        btnxoa = new javax.swing.JButton();

        javax.swing.GroupLayout backGround1Layout = new javax.swing.GroupLayout(backGround1);
        backGround1.setLayout(backGround1Layout);
        backGround1Layout.setHorizontalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        backGround1Layout.setVerticalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1235, 648));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        backGround2.setBackground(new java.awt.Color(255, 255, 255));

        header.setOpaque(false);
        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        header.add(winButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1144, 6, -1, -1));

        jButton_ThucAn.setBackground(new java.awt.Color(255, 153, 0));
        jButton_ThucAn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_ThucAn.setText("Thức Ăn ");
        jButton_ThucAn.setMinimumSize(new java.awt.Dimension(105, 32));
        jButton_ThucAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThucAnActionPerformed(evt);
            }
        });
        header.add(jButton_ThucAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 124, -1));

        jButton_TrangMieng.setBackground(new java.awt.Color(51, 255, 51));
        jButton_TrangMieng.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_TrangMieng.setText("Tráng Miệng");
        jButton_TrangMieng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TrangMiengActionPerformed(evt);
            }
        });
        header.add(jButton_TrangMieng, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 30, 157, -1));

        jButton_Nuoc.setBackground(new java.awt.Color(51, 204, 255));
        jButton_Nuoc.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_Nuoc.setText("Nước");
        jButton_Nuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NuocActionPerformed(evt);
            }
        });
        header.add(jButton_Nuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, -1, -1));

        jButton_Home.setBackground(new java.awt.Color(204, 204, 204));
        jButton_Home.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_Home.setText("Home");
        jButton_Home.setMinimumSize(new java.awt.Dimension(30, 40));
        jButton_Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_HomeActionPerformed(evt);
            }
        });
        header.add(jButton_Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 30, -1, -1));

        menubar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menuicon/menu-barok.png"))); // NOI18N
        menubar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menubarActionPerformed(evt);
            }
        });
        header.add(menubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 9, -1, -1));

        Jt_date.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 16)); // NOI18N
        Jt_date.setForeground(new java.awt.Color(0, 204, 204));
        Jt_date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.add(Jt_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, 190, 24));

        Jt_Time.setFont(new java.awt.Font("Lucida Console", 1, 16)); // NOI18N
        Jt_Time.setForeground(new java.awt.Color(0, 204, 204));
        Jt_Time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.add(Jt_Time, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 114, 24));

        jButton3.setText("Danh Sách Bàn");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        header.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        mainPanel.setBackground(new java.awt.Color(193, 193, 193));

        jButton_Chon.setText("Chọn");
        jButton_Chon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ChonMouseClicked(evt);
            }
        });

        bill2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Số lượng", "Giá", "Tổng"
            }
        ));
        bill2.setOpaque(false);
        jScrollPane3.setViewportView(bill2);

        jLabel5.setText("Tổng : ");

        btndathang.setText("Đặt Hàng");
        btndathang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndathangMouseClicked(evt);
            }
        });

        jButton1.setText("New");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btndathang)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField_Tong2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Tong2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btndathang)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jComboBox_ChonBan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox_ChonBanFocusLost(evt);
            }
        });

        quantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        quantity.setBorder(null);
        quantity.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton_Chon))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jComboBox_ChonBan, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(878, 878, 878))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_ChonBan, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_Chon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(quantity)
                        .addComponent(btnxoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout backGround2Layout = new javax.swing.GroupLayout(backGround2);
        backGround2.setLayout(backGround2Layout);
        backGround2Layout.setHorizontalGroup(
            backGround2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backGround2Layout.setVerticalGroup(
            backGround2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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

    private void jButton_ThucAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThucAnActionPerformed
        home.setVisible(false);
        nuoc.setVisible(false);
        trangmieng.setVisible(false);
//        runData_ThucAn();
//        mainPanel.removeAll();
//         mainPanel.setLayout(new BorderLayout());
        if(!thucan.isShowing()){
         thucan.setVisible(true);
         mainPanel.add(thucan);

        }
//         home.setVisible(false);
        
//        testData_ThucAn();
        thucan.repaint();
//        thucan.setVisible(true);
    }//GEN-LAST:event_jButton_ThucAnActionPerformed

    private void jButton_HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_HomeActionPerformed
        
//       mainPanel.removeAll();
//       mainPanel.add(home);
//       if(home.isShowing()){
//           System.out.println("okla");
//       }
       nuoc.setVisible(false);
       trangmieng.setVisible(false);
       thucan.setVisible(false);
       if(!home.isShowing()){
            home.setVisible(true);
       }
        
//       testData();
//        runData();
       home.repaint();
    }//GEN-LAST:event_jButton_HomeActionPerformed

    private void menubarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menubarActionPerformed
        if(drawer.isShow()){
            drawer.hide();
        }else {
            drawer.show();
        }
    }//GEN-LAST:event_menubarActionPerformed

    private void jButton_NuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NuocActionPerformed
//     mainPanel.removeAll();
    home.setVisible(false);
    thucan.setVisible(false);
    trangmieng.setVisible(false);
    if(!nuoc.isShowing()){
        nuoc.setVisible(true);
        mainPanel.add(nuoc);
    }
     
     nuoc.repaint();
    }//GEN-LAST:event_jButton_NuocActionPerformed

    private void jButton_TrangMiengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TrangMiengActionPerformed
        home.setVisible(false);
        thucan.setVisible(false);
        nuoc.setVisible(false);
        if(!trangmieng.isShowing()){
            trangmieng.setVisible(true);
            mainPanel.add(trangmieng);
        }
            mainPanel.add(trangmieng);
            trangmieng.repaint();
    }//GEN-LAST:event_jButton_TrangMiengActionPerformed

    private void jButton_ChonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ChonMouseClicked
        banSelect=jComboBox_ChonBan.getSelectedItem().toString();
        System.out.println(banSelect);
        for (ModelItem modelItem : list) {
            if(modelItem.getItemID().equals(itemSelected.getItemID())){
                JOptionPane.showMessageDialog(null, "Sản phẩm này đã được chọn!");
                kt++;
            }
        }
        if(kt==0){
            int qty = (int)quantity.getValue();
            if(home.isShowing()){
                stt++;
                list.add(itemSelected);
                home.addRowTable(model,itemSelected,stt,qty);
                jTextField_Tong2.setText( Integer.toString(Tong+=itemSelected.getPrice()*qty) );
            }
            if(thucan.isShowing()){
                stt++;
                list.add(itemSelected);
                thucan.addRowTable(model,itemSelected,stt,qty);
                jTextField_Tong2.setText( Integer.toString(Tong+=itemSelected.getPrice()*qty) );
            }
            if(nuoc.isShowing()){
                stt++;
                list.add(itemSelected);
                nuoc.addRowTable(model,itemSelected,stt,qty);
                jTextField_Tong2.setText( Integer.toString(Tong+=itemSelected.getPrice()*qty) );
            }
            if(trangmieng.isShowing()){
                stt++;
                list.add(itemSelected);
                trangmieng.addRowTable(model,itemSelected,stt,qty);
                jTextField_Tong2.setText(Integer.toString(Tong+=itemSelected.getPrice()*qty));
            }
        }
        kt=0;
    }//GEN-LAST:event_jButton_ChonMouseClicked

    private void btndathangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndathangMouseClicked
        int mahd = 0;
        conn = cn.ketNoi();
        if(list.isEmpty()){
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn món ăn!");
            return;
        }
        if(listTable.kiemtra(jComboBox_ChonBan.getSelectedItem().toString())){
//            dataDate= Jt_Time.getText()+ "\n  Date: "+Jt_date.getText();
//            // bang order
//           
//            orderBill = new Bill(list,home.getQuantity(),dataDate);
//            orderBill.setVisible(true);
            //tạo câu lệnh trong CSDL.
            String sql_kiemtra = "Select * from ban";
            try {
                // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                PreparedStatement pst = conn.prepareStatement(sql_kiemtra);
                // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    if(jComboBox_ChonBan.getSelectedItem().toString().equals(rs.getString("tenban"))){
                        String sql = "insert into hoadon(manguoidung,maban,ngaytao) values(?,?,default)";
                        // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                        PreparedStatement pst1 = conn.prepareStatement(sql);
                        PreparedStatement pst2 = conn.prepareStatement("SELECT MAX(mahoadon) as mahoadon FROM hoadon");
                        //truyền giá trị đối tượng cần thêm vào PreparedStatement để thực hiện truy vấn CSDL.
                        pst1.setString(1, tennguoidung);
//                        pst1.setString(1, "ad");
                        pst1.setString(2, rs.getString("maban"));
                        //thực hiện cật nhật dữ liệu
                        pst1.executeUpdate();
                        System.out.println("Them hoa đơn thanh cong");
                        listTable.settrangthai(jComboBox_ChonBan.getSelectedItem().toString());
                        // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
                        ResultSet rs2 = pst2.executeQuery();
                        if(rs2.next()){
                            mahd = rs2.getInt("mahoadon");
                        }
                        pst1.close();
                        pst2.close();
                        rs2.close();
                    }
                }
                String sqlhd = "Select * from hoadon join ban on hoadon.maban=ban.maban where mahoadon = ?";
                // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                PreparedStatement psthd = conn.prepareStatement(sqlhd);
                psthd.setInt(1, mahd);
                // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
                ResultSet rshd = psthd.executeQuery();
                while(rshd.next()){
                    if(jComboBox_ChonBan.getSelectedItem().toString().equals(rshd.getString("tenban"))){
                        String sqlctsp = "insert into chitietsanpham(mahoadon,masanpham,soluong) values(?,?,?)";
                        // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                        PreparedStatement pst1 = conn.prepareStatement(sqlctsp);
                        PreparedStatement pst2 = conn.prepareStatement("select * from sanpham where tensanpham = ?");
                        int rowCount = bill2.getRowCount();
                        System.out.println("hang: "+rowCount);
                        for(int j=0;j<rowCount;j++){
                            pst1.setInt(1, mahd);
                            pst2.setString(1, bill2.getValueAt(j, 1).toString());
                            ResultSet rs2 = pst2.executeQuery();
                            if(rs2.next()){
                                pst1.setString(2, rs2.getString("masanpham"));
                                System.out.println("tc");
                            }
                            pst1.setInt(3,(int)bill2.getValueAt(j, 2));
                            //thực hiện cật nhật dữ liệu
                            pst1.executeUpdate();
                            rs2.close();
                        }
                        System.out.println("Them chi tiet san pham thanh cong");
                        pst1.close();
                        pst2.close();
                    }
                }
                JOptionPane.showMessageDialog(null, "Hoàn tất");
                //Giải phóng bộ nhớ
                pst.close();
                rs.close();
                rshd.close();
                conn.close();
            } 
            catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
            }
        }
        else{
            //tạo câu lệnh trong CSDL.
            String sql_kiemtra = "Select * from ban where tenban = ?";
            String tenban = jComboBox_ChonBan.getSelectedItem().toString();
            String mab = null;
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
                PreparedStatement pstxoa = conn.prepareStatement("delete from chitietsanpham where mahoadon = " + mahd);
                pstxoa.executeUpdate();
                String sqlctsp = "insert into chitietsanpham(mahoadon,masanpham,soluong) values(?,?,?)";
                // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                PreparedStatement pst1 = conn.prepareStatement(sqlctsp);
                PreparedStatement pst2 = conn.prepareStatement("select * from sanpham where tensanpham = ?");
                int rowCount = bill2.getRowCount();
                System.out.println("hang: "+rowCount);
                for(int j=0;j<rowCount;j++){
                    pst1.setInt(1, mahd);
                    pst2.setString(1, bill2.getValueAt(j, 1).toString());
                    ResultSet rs2 = pst2.executeQuery();
                    if(rs2.next()){
                        pst1.setString(2, rs2.getString("masanpham"));
                        System.out.println("tc");
                    }
                    pst1.setInt(3,(int)bill2.getValueAt(j, 2));
                    //thực hiện cật nhật dữ liệu
                    pst1.executeUpdate();
                    rs2.close();
                }
                System.out.println("Sua chi tiet san pham thanh cong");
                JOptionPane.showMessageDialog(null, "Hoàn tất");
                pst1.close();
                pst2.close();
                pst.close();
                rs.close();
                psthd.close();
                rshd.close();
                conn.close();
            }
            catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
            }  
        }
    }//GEN-LAST:event_btndathangMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        model.setRowCount(0);
        list.clear();
        stt = 0;
        Tong=0;
        jTextField_Tong2.setText(String.valueOf(Tong));
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
//        listTable = new ListtTable(banSelect);
        listTable.setVisible(true);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jComboBox_ChonBanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_ChonBanFocusLost
        model.setRowCount(0);
        kt=0;
        String tenban = jComboBox_ChonBan.getSelectedItem().toString();
        if(!listTable.kiemtra(tenban)){
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
                int tong = 0;
                while(rsfix.next()){
                    System.out.println("a6");
                    pstfix2.setString(1, rsfix.getString("masanpham"));
                    ResultSet rsfix2 = pstfix2.executeQuery();
                    if(rsfix2.next()){
                        tong = tong + rsfix2.getInt("gia")*rsfix.getInt("soluong");
                        System.out.println("tc");
                    }
                    Tong = tong;
                    rsfix2.close();
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
            Tong = 0;
            jTextField_Tong2.setText(null);
        }
        stt = 0;
        loadbang(jComboBox_ChonBan.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox_ChonBanFocusLost

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        //kiểm tra xem có hàng nào trong bảng hay không, ko có  = -1
        if(bill2.getSelectedRow()==-1){
            if(bill2.getRowCount()==0){ //Kiểm tra xem bảng có hàng nào không
                JOptionPane.showMessageDialog(null, "Không có gì để xóa","Dữ liệu User",JOptionPane.OK_OPTION);
            }
            else{
                JOptionPane.showMessageDialog(null, "Chọn hàng để xóa","Dữ liệu User",JOptionPane.OK_OPTION);
            }
        }
        else{
            // lấy chỉ số hàng đang được chọn
            int rowIndex = bill2.getSelectedRow(); 
            int x = (int)Integer.parseInt(bill2.getValueAt(rowIndex, 3).toString());
            Tong = Tong - x;
            jTextField_Tong2.setText(String.valueOf(Tong));
            //xóa hàng
            model.removeRow(rowIndex);

//            conn = cn.ketNoi();
//            String masp=null;
//            try {
//                //xóa thông tin người dùng trong cơ sở dữ liệu
//                String sql = "delete from chitietsanpham where masanpham = ?";
//                int rowIndex = bill2.getSelectedRow(); // lấy chỉ số hàng đang được chọn
//                // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
//                PreparedStatement pst = conn.prepareStatement(sql);
//                PreparedStatement pst1 = conn.prepareStatement("select * from sanpham where tensanpham = ?");
//                //truyền giá trị đối tượng cần thêm vào PreparedStatement để thực hiện truy vấn CSDL.
//                pst1.setString(1, bill2.getValueAt(rowIndex, 1).toString());
//                ResultSet rs1 = pst1.executeQuery();
//                if(rs1.next()){
//                    masp = rs1.getString("masanpham");
//                }
//                pst.setString(1, masp);
//                //thực hiện cật nhật dữ liệu
//                pst.executeUpdate();
//                //xóa hàng
//                model.removeRow(rowIndex);
//                //giải phóng bộ nhớ
//                pst.close();
////                conn.close();
//            } catch (Exception e) {
//                JOptionPane.showConfirmDialog(null, e);
//            }
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthfixMouseClicked
        
    }//GEN-LAST:event_bthfixMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //dùng cấu hình giao diện nimbus
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        try {
                            UIManager.setLookAndFeel(info.getClassName());
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnsupportedLookAndFeelException ex) {
                            Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jt_Time;
    private javax.swing.JLabel Jt_date;
    private com.raven.swing.BackGround backGround1;
    private com.raven.swing.BackGround backGround2;
    private javax.swing.JTable bill2;
    private javax.swing.JButton btndathang;
    private javax.swing.JButton btnxoa;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_Chon;
    private javax.swing.JButton jButton_Home;
    private javax.swing.JButton jButton_Nuoc;
    private javax.swing.JButton jButton_ThucAn;
    private javax.swing.JButton jButton_TrangMieng;
    private javax.swing.JComboBox<String> jComboBox_ChonBan;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField_Tong2;
    private com.raven.swing.MainPanel mainPanel;
    private javax.swing.JButton menubar;
    private javax.swing.JSpinner quantity;
    private com.raven.win_button.WinButton winButton;
    // End of variables declaration//GEN-END:variables
}

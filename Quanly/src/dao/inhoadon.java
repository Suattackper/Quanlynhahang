package dao;

import java.awt.*;
import java.awt.print.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class inhoadon implements Printable {
     //khởi tạo một đối tượng kết nối CSDL thông qua class ketnoisql
    KetNoisql cn = new KetNoisql();
    //Khai báo biến kết nối CSDL
    Connection conn;
    ArrayList<String> hoadon = new ArrayList<String>();
    String tenban = null;
    int kt = 0;
    public inhoadon(){
        
    }
    public inhoadon(String tenban){
        this.tenban=tenban;
    }
    
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        // Tạo đối tượng Graphics2D từ đối tượng Graphics
        Graphics2D g2d = (Graphics2D) graphics;

        // Thiết lập font, kích thước chữ và màu sắc cho hóa đơn
        Font font = new Font("Arial", Font.BOLD, 12);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        
        // Vẽ tiêu đề
        String title = "HÓA ĐƠN";
        g2d.drawString(title, 300, 30);

        // Lấy thông tin cần in từ hóa đơn
        String[] invoiceData = getInvoiceData(); // Hàm này trả về một mảng thông tin hóa đơn

        int x = 200; // Vị trí x của văn bản
        int y = 100; // Vị trí y của văn bản

        // In từng dòng thông tin hóa đơn
        for (String line : invoiceData) {
            g2d.drawString(line, x, y);
            y += 20; // Dịch vị trí y lên cho dòng tiếp theo
        }

        return PAGE_EXISTS;
        
    }
    public void printInvoice() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        // Hiển thị hộp thoại in để người dùng chọn máy in và cấu hình in
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    private String[] getInvoiceData() {
        if(kt==0){
            conn = cn.ketNoi();
            String mab = null;
            int mahd = 0;
            int giam = 0;
            Date date = new Date();
            SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss aa");
            String time = tf.format(date);
            //tạo câu lệnh trong CSDL.
            String sql_kiemtra = "Select * from ban where tenban = ?";
            try {
                // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                PreparedStatement pst = conn.prepareStatement(sql_kiemtra);
                pst.setString(1, tenban);
                // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    mab = rs.getString("maban");
                }
                String sqlhd = "Select *, format(ngaytao,'dd/MM/yyyy') as 'ngaytaom' from hoadon join ban on hoadon.maban=ban.maban where ban.maban = ? order by mahoadon desc";
                // khởi tạo đối tượng PreparedStatement để thực thi câu truy vấn
                PreparedStatement psthd = conn.prepareStatement(sqlhd);
                psthd.setString(1, mab);
                // thực thi câu truy vấn và lấy kết quả trả về vào đối tượng ResultSet
                ResultSet rshd = psthd.executeQuery();
                if(rshd.next()){
                    System.out.println(1);
                    mahd = rshd.getInt("mahoadon");
                    hoadon.add("Bàn: " + rshd.getString("tenban") + " ngày: " + rshd.getString("ngaytaom")
                    + " giờ: " + time.split(" ")[0]+" "+time.split(" ")[1]);
                    hoadon.add("Hóa đơn số: " + mahd);
                    System.out.println(2);
                    if(rshd.getString("makhuyenmai")!=null){
                        PreparedStatement pstkm = conn.prepareStatement("Select * from khuyenmai where makhuyenmai = ?");
                        pstkm.setString(1, rshd.getString("makhuyenmai"));
                        System.out.println(3);
                        ResultSet rskm = pstkm.executeQuery();
                        if(rskm.next()){
                            System.out.println(4);
                            giam = rskm.getInt("giam");
                            hoadon.add("Mã khuyến mãi: " + rshd.getString("makhuyenmai") + " -- giảm "
                            + rskm.getInt("giam")+"%");
                        }
                        pstkm.close();
                        rskm.close();
                    }
                }
                hoadon.add("********************************************************");
                //hoadon.add("Hóa đơn số: " + mahd + "\t\t\tNgày tạo: " + );
                hoadon.add("Sản phẩm:");

                PreparedStatement pstfix = conn.prepareStatement("select * from chitietsanpham c join hoadon h on c.mahoadon=h.mahoadon "
                                + "where c.mahoadon = " + mahd);
                PreparedStatement pstfix2 = conn.prepareStatement("select * from sanpham where masanpham = ?");
                ResultSet rsfix = pstfix.executeQuery();
                int tong = 0;
                while(rsfix.next()){
                    pstfix2.setString(1, rsfix.getString("masanpham"));
                    ResultSet rsfix2 = pstfix2.executeQuery();
                    if(rsfix2.next()){
                        hoadon.add("- "+rsfix2.getString("tensanpham")+":   Mua "+rsfix.getInt("soluong")+" --          Tổng: "
                                 + rsfix2.getInt("gia")*rsfix.getInt("soluong") + "VND");
                        System.out.println(5);
                        tong = tong + rsfix2.getInt("gia")*rsfix.getInt("soluong");
                        System.out.println("tc");
                    }
                    rsfix2.close();
                }
                hoadon.add("");
                hoadon.add("Thành tiền: " + (tong-tong/100*giam) + "VND");
                hoadon.add("");
                hoadon.add("************** CAM ON QUY KHACH ***************");
                JOptionPane.showMessageDialog(null, "Lưu bill thành công");
                kt++;

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
        return hoadon.toArray(new String[0]);
    }
}

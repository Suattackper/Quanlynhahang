package dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jframe.XacNhan;

public class Email {
    //khởi tạo một đối tượng kết nối CSDL thông qua class ketnoisql
    KetNoisql cn = new KetNoisql();
    //Khai báo biến kết nối CSDL
    Connection conn;
    
    //kiểm tra 1 chuỗi có phải là email
    public static boolean checkEmail(String email) {
        // Biểu thức chính quy kiểm tra địa chỉ email
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        // Tạo đối tượng Pattern từ biểu thức chính quy
        Pattern pattern = Pattern.compile(regex);
        // Tạo đối tượng Matcher để so khớp chuỗi email với biểu thức chính quy
        Matcher matcher = pattern.matcher(email);
        // Kiểm tra xem chuỗi email có khớp với biểu thức chính quy hay không
        return matcher.matches();
    }
    
    //Tạo mã xác thực 
    public void MaXacThuc(String email){
        // Lấy kết nối CSDL thông qua phương thức ketNoi() của đối tượng ketnoisql.
        conn = cn.ketNoi();
        
        int length = 6; // Độ dài của chuỗi
        // Các ký tự có thể xuất hiện
        String characters = "0123456789"; 
        //Chuỗi ngẫu nhiên 
        StringBuilder sb = new StringBuilder();
        // tạo số ngẫu nhiên trong khoảng từ 0 đến 9
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        String maxacthuc = sb.toString();
        System.out.println(maxacthuc);
        try {
            //tạo mã xác thực trong csdl
            PreparedStatement pst = conn.prepareStatement("Update nguoidung set maxacnhan = null where email = ?");
            pst.setString(1,email);
            PreparedStatement pst1 = conn.prepareStatement("Update nguoidung set maxacnhan = ? where email = ?");
            pst1.setString(1, maxacthuc);
            pst1.setString(2, email);
            //thực hiện cật nhật dữ liệu
            pst.executeUpdate();
            pst1.executeUpdate();

            SendEmail("Mã xác nhận của NhaHangTiTi","Mã xác nhận NhaHangTiTi của bạn là : ",email,maxacthuc);
            pst.close();
            pst1.close();
            conn.close();
        } catch (Exception e) {
             e.printStackTrace();
        }
    }   
    
    //Tạo mật khẩu 
    public void MatKhau(String email){
        // Lấy kết nối CSDL thông qua phương thức ketNoi() của đối tượng ketnoisql.
        conn = cn.ketNoi();
        
        int length = 6; // Độ dài của chuỗi
        // Các ký tự có thể xuất hiện
        String characters = "qưertyuiopasdfghjklzxcvbnmQƯERTYUIOPASDFGHJKLZXCVBNM0123456789"; 
        //Chuỗi ngẫu nhiên 
        StringBuilder sb = new StringBuilder();
        // tạo ký tự ngẫu nhiên từ các ký tự đã cho
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        String matkhau = sb.toString();
        System.out.println(matkhau);
        try {
            //tạo mã xác thực trong csdl
            PreparedStatement pst1 = conn.prepareStatement("Update nguoidung set matkhau = ? where email = ?");
            pst1.setString(1, matkhau);
            pst1.setString(2, email);
            //thực hiện cật nhật dữ liệu
            pst1.executeUpdate();

            SendEmail("Mật khẩu mới tài khoản NhaHangTiTi","Mật khẩu mới tài khoản NhaHangTiTi của bạn là : ",email,matkhau);
            pst1.close();
            conn.close();
        } catch (Exception e) {
             e.printStackTrace();
        }
    }   
    
    //Gửi mail
    public void SendEmail(String chude, String noidung, String email, String maxacthuc){
        final String username = "nhahangtiti.java@gmail.com";
        final String password = "ibovrqtgzxzhfavb";
        final String nguoinhan = email;
        final String subject = chude;
        final String content = noidung;

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        //Đăng nhập
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(username,"NhaHangTiTi"));
            } 
            catch (UnsupportedEncodingException ex) {
                Logger.getLogger(XacNhan.class.getName()).log(Level.SEVERE, null, ex);
            }
            //người nhận
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(nguoinhan)
            );
            //chủ đề email
            message.setSubject(subject);
            //nội dung email
            message.setText(content + maxacthuc);
            //gửi
            Transport.send(message);

            System.out.println("Gui mail thanh cong");

        } 
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

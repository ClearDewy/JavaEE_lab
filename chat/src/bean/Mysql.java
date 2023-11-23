package bean;


import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Mysql {
    private static Connection conn;
    public static void init(){
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 连接数据库
            String url = "jdbc:mysql://43.142.187.104:6604/chat";
            String username = "root";
            String password = "040110";
            conn = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean login(String username ,String password){
        try{
            String sql="SELECT `password` FROM `user` WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getString("password").equals(password);
            }
            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean register(String username ,String password){
        boolean success=false;
        try{
            String sql="INSERT INTO `user`(username, password) VALUES (?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            success=pstmt.executeUpdate()>0;
            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return success;
    }

    public static void insertMessage(String username, String message) {
        String sql = "INSERT INTO message (username, message) VALUES (?, ?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, message);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Message> selectLast100Messages() {
        String sql = "SELECT * FROM message ORDER BY id DESC LIMIT 100;";
        ArrayList<Message> messages = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("username");
                String message = rs.getString("message");
                messages.add(new Message(username,message));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}

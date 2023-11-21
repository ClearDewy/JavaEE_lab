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

    public static boolean find(String username ,String password){
        boolean isValidUser=false;
        try{
            String sql="SELECT * FROM `user` WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                isValidUser = rs.next();
            }
            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        if (isValidUser) return true;
        else return false;
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

import javax.servlet.ServletException;
import java.sql.*;

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
}

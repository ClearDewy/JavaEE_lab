package bean;

import java.io.Serializable;
import java.sql.*;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public class Mysql implements Serializable {

    private static Connection conn;
    public static void init(){
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 连接数据库
            String url = "jdbc:mysql://43.142.187.104:6604/shop";
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

    public static int login(String username ,String password){
        try{
            String sql="SELECT `id`,`password` FROM `user` WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()&&rs.getString("password").equals(password)){
                return rs.getInt("id");
            }
            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
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


}

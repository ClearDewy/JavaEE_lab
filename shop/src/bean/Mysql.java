package bean;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static List<ShopCarItem> getAllShop(int user_id){
        ArrayList<ShopCarItem> shopCarItems = new ArrayList<>();

        try{
            String sql="SELECT `commodity`.id, `img`, `name`, `price`,COALESCE(`count`, 0) from `commodity` LEFT JOIN `order` on commodity.id = order.commodity_id and user_id= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                shopCarItems.add(new ShopCarItem(new CommodityInfo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)
                ),rs.getInt(5)));
            }

            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return shopCarItems;
    }

    public static void updateShopCar(int user_id,int commodity_id,int count){
        try{
            String sql = "INSERT INTO `order` (user_id, commodity_id, count) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE count = VALUES(count);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user_id);      // 假设 userId 是用户ID
            pstmt.setInt(2, commodity_id); // 假设 commodityId 是商品ID
            pstmt.setInt(3, count);       // 假设 count 是数量
            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ShopCar getMyShopCar(int user_id){
        ShopCar shopCar = new ShopCar();
        try{
            String sql="SELECT `commodity`.id, `img`, `name`, `price`,`count` from `commodity` inner JOIN `order` on commodity.id = order.commodity_id where user_id= ? and count!=0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user_id);      // 假设 userId 是用户ID
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                shopCar.addShopCarItem(new ShopCarItem(new CommodityInfo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)
                ),rs.getInt(5)));
            }

            pstmt.close();

            sql="SELECT SUM(`price`*`count`) FROM commodity INNER JOIN `order` ON commodity.id = `order`.commodity_id where user_id= ? and count!=0";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, user_id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                shopCar.setTotalPrice(rs.getDouble(1));
            }
            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return shopCar;
    }

}

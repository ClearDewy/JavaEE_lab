package bean;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public class CommodityInfo {
    private int id;
    private String img;
    private String name;
    private double price;

    public CommodityInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CommodityInfo(int id, String img, String name, double price) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.price = price;
    }
}

package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public class ShopCar {
    private ArrayList<ShopCarItem> car=new ArrayList<>();
    private double totalPrice=0;

    public ShopCar() {
    }

    public ShopCar(ArrayList<ShopCarItem> car, double totalPrice) {
        this.car = car;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<ShopCarItem> getCar() {
        return car;
    }

    public void setCar(ArrayList<ShopCarItem> car) {
        this.car = car;
    }

    public void addShopCarItem(ShopCarItem shopCarItem){
        car.add(shopCarItem);
    }
}

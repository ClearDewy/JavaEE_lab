package bean;

import bean.CommodityInfo;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public class ShopCarItem {
    private CommodityInfo commodityInfo;
    private int count;

    public ShopCarItem() {
    }

    public ShopCarItem(CommodityInfo commodityInfo, int count) {
        this.commodityInfo = commodityInfo;
        this.count = count;
    }

    public CommodityInfo getCommodityInfo() {
        return commodityInfo;
    }

    public void setCommodityInfo(CommodityInfo commodityInfo) {
        this.commodityInfo = commodityInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

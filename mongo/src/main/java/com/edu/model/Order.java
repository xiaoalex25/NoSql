package com.edu.model;

import java.util.Date;

public class Order {
    private long orderId;//订单ID
    private Date createDate;//下单时间
    private String userName;//下单用户
    private String goodsList;//购买商品JSON, 可以记录商品销售明细

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(String goodsList) {
        this.goodsList = goodsList;
    }
}

package com.edu.model;

public class Goods {
    private long id;
    private String adInfo;
    private String goodsInfo;
    private String specificationInfos;
    private int num;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdInfo() {
        return adInfo;
    }

    public void setAdInfo(String adInfo) {
        this.adInfo = adInfo;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getSpecificationInfos() {
        return specificationInfos;
    }

    public void setSpecificationInfos(String specificationInfos) {
        this.specificationInfos = specificationInfos;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

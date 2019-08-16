package com.edu.model;

import java.io.Serializable;
import java.util.List;

public class Advertisement implements Serializable {

    private int id;
    private String positionCode;//广告为代码
    private int tid;//广告位模板id
    private List<AdContent> adContents;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public List<AdContent> getAdContents() {
        return adContents;
    }

    public void setAdContents(List<AdContent> adContents) {
        this.adContents = adContents;
    }
}

package com.example.hoteladminactivitys;

public class UserExtraClass {
    String facility,cost,extrakey;

    public UserExtraClass() {
    }

    public String getExtrakey() {
        return extrakey;
    }

    public void setExtrakey(String extrakey) {
        this.extrakey = extrakey;
    }

    public UserExtraClass(String facility, String cost) {
        this.facility = facility;
        this.cost = cost;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}

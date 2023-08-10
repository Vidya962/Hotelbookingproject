package com.example.hoteladminactivitys;

public class UserPromoClass {

    String promocode,discount,promokey;

    public UserPromoClass() {
    }

    public UserPromoClass(String promocode, String discount) {
        this.promocode = promocode;
        this.discount = discount;
    }

    public String getPromocode() {
        return promocode;
    }

    public String getPromokey() {
        return promokey;
    }

    public void setPromokey(String promokey) {
        this.promokey = promokey;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}

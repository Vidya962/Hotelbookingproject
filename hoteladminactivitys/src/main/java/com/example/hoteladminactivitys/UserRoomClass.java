package com.example.hoteladminactivitys;

public class UserRoomClass {
    String Room_type,Cost,Discount,roomkey;

    public UserRoomClass() {
    }

    public UserRoomClass(String room_type, String cost, String discount) {
        Room_type = room_type;
        Cost = cost;
        Discount = discount;
    }

    public String getRoomkey() {
        return roomkey;
    }

    public void setRoomkey(String roomkey) {
        this.roomkey = roomkey;
    }

    public String getRoom_type() {
        return Room_type;
    }

    public void setRoom_type(String room_type) {
        Room_type = room_type;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}

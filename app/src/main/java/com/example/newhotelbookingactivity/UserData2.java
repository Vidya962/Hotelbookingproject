package com.example.newhotelbookingactivity;

public class UserData2 {
    String fname;
    String lname;
    String email;
    String mob;
    String address;

    public UserData2(String fname, String lname, String email, String mob, String address){
        this.fname=fname;
        this.lname=lname;
        this.email=email;
        this.mob=mob;
        this.address=address;


    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}

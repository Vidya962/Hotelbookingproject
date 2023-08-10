package com.example.hoteladminactivitys;

public class UserModelClass {

    String First_name,Second_name,phone_no,password,confirm_password;

    public UserModelClass() {
    }

    public UserModelClass(String first_name, String second_name, String phone_no, String password, String confirm_password) {
        First_name = first_name;
        Second_name = second_name;
        this.phone_no = phone_no;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getSecond_name() {
        return Second_name;
    }

    public void setSecond_name(String second_name) {
        Second_name = second_name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}

package com.example.newhotelbookingactivity;

public class UserHelperClass {
    String Firstname,Secondname,Email,Address,Mobile,City,Password,Confirm_password,Country;

    public UserHelperClass() {

    }

    public UserHelperClass(String firstname, String secondname, String email, String address, String mobile, String city, String password, String confirm_password, String country) {
        Firstname = firstname;
        Secondname = secondname;
        Email = email;
        Address = address;
        Mobile = mobile;
        City = city;
        Password = password;
        Confirm_password = confirm_password;
        Country = country;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getSecondname() {
        return Secondname;
    }

    public void setSecondname(String secondname) {
        Secondname = secondname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirm_password() {
        return Confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        Confirm_password = confirm_password;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}

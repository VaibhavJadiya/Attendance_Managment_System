package com.vk.creations.firebase_data_insertion;

class UserHelperClass {

    String name,email,username,number,password;

    public UserHelperClass(){}

    public UserHelperClass(String name, String email,String username, String number, String password) {
        this.name = name;
        this.email = email;
        this.username=username;
        this.number = number;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

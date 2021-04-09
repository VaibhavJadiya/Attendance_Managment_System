package com.vk.creations.firebase_data_insertion;

class AttendanceHelper {
     String name;
    String username;
    String date;

    public AttendanceHelper(){}

    public AttendanceHelper (String username, String name,String date) {
       this.name = name;
        this.username = username;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

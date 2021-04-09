package com.vk.creations.firebase_data_insertion;

class userInfo {
    String UsreId;
    String UserEmaill;
    String UserName;
    String Usernumber;
    String Userpassword;



    public userInfo(String usreId, String useremail,String userName, String userNumber, String userpassword) {
        UsreId = usreId;
        UserEmaill=useremail;
        UserName = userName;
        Usernumber=userNumber;
        Userpassword=userpassword;
    }


    public String getUsreId() {
        return UsreId;
    }
    public String getUserEmaill() { return UserEmaill;  }
    public String getUserName() {
        return UserName;
    }
    public String getUsernumber() {
        return Usernumber;
    }
    public String getUserpassword() {  return Userpassword;  }
}

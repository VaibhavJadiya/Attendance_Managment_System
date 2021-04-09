package com.vk.creations.firebase_data_insertion;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

class SessionManager {

    SharedPreferences userSession;
    SharedPreferences.Editor editor;

   Context context;

   public static final String SESSION_USER="userLoginSession";
   public static final String SESSION_Rememberme="rememberMe";
   public static final String IsRememberme="IsRememberMe";
   public static final String KEY_USERNAME="username";
   public static final String KEY_password="password";
   private static final String Is_logedin="isLoggedIn";
    public static final String RememberMe_USERNAME="username";
    public static final String RememberMe_password="password";

    public SessionManager( Context _context,String sessionName) {

        context=_context;
        userSession=context.getSharedPreferences(sessionName,Context.MODE_PRIVATE);
        editor=userSession.edit();
    }



    public void createLoginSesion(String username,String password){

        editor.putBoolean(Is_logedin,true);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_password,password);
        editor.commit();

    }

    public HashMap<String,String> getUserDeatilsFromSession(){
        HashMap<String,String> userdata=new HashMap<String,String>();
        userdata.put(KEY_USERNAME,userSession.getString(KEY_USERNAME,null));
        userdata.put(KEY_password,userSession.getString(KEY_password,null));
        return userdata;
    }

    public HashMap<String,String> getRememberMeDetailsFromSession(){
        HashMap<String,String> userdata=new HashMap<String,String>();
        userdata.put(RememberMe_USERNAME,userSession.getString(RememberMe_USERNAME,null));
        userdata.put(RememberMe_password,userSession.getString(RememberMe_password,null));
        return userdata;
    }

    public boolean CheckLogin(){
        if (userSession.getBoolean(Is_logedin,false)){
            return true;
        }else {
            return false;
        }
    }

    public boolean CheckLRememberMe(){
        if (userSession.getBoolean(IsRememberme,false)){
            return true;
        }else {
            return false;
        }
    }

    public void LogoutUserFromSession(){
        editor.clear();
        editor.commit();
    }

    public void createRemebermeSesion(String username,String password){

        editor.putBoolean(IsRememberme,true);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_password,password);
        editor.commit();

    }
}

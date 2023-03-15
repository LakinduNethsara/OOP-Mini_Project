package com.user;

import com.login.Login;
import org.w3c.dom.ls.LSOutput;

public class UserDetails {
    public String getUsername;
    private String getPassword;
    private String getType;

    public void setUserDetails(String username,String password,String type){
        getUsername=username;
        getPassword=password;
        getType=type;

    }

    public String getUserName(){
        return  getUsername;
    }
    public String getPassword(){
        return  getPassword;
    }
    public String getType(){
        return  getType;
    }

}

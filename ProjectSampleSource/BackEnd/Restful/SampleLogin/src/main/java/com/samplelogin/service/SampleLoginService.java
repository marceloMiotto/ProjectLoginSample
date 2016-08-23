package com.samplelogin.service;


import com.samplelogin.database.DatabaseHelper;
import com.samplelogin.model.User;

import java.sql.SQLException;

public class SampleLoginService {

    DatabaseHelper dbHelper;

    public SampleLoginService() {
    }

    public String userAuth(User user){

        dbHelper = new DatabaseHelper();

        try{
            if(dbHelper.isUserAuth(user)){
                return "User Verified";
            }else{
                return "User Not Verified";
            }

        }catch (SQLException e){
            e.printStackTrace();
            return "User Not Verified.";
        }

    }

}

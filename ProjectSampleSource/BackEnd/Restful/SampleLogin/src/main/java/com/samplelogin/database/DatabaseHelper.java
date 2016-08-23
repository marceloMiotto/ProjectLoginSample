package com.samplelogin.database;

import com.samplelogin.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {

    private int userFound;

    public boolean isUserAuth (User user) throws SQLException{

        ConHelper conHelper = new ConHelper();
        Connection conn = conHelper.getConnection();

        PreparedStatement st = conn.prepareStatement("SELECT count(*) FROM samples.users WHERE user_id = ? AND password = ?");
        st.setString(1,user.getUserId());
        st.setString(2,user.getUserPwd());
        ResultSet rs = st.executeQuery();

        while (rs.next())
        {
            userFound = rs.getInt(1);
        }
        rs.close();
        st.close();

        if(userFound == 1){
            return true;
        }else{
            return false;
        }


    }

}

package com.servlets.dao;


import com.servlets.beans.User;
import com.servlets.utility.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final String SELECT_QUERY = "select * from users where email=? and password_hash=?";
    public boolean validate(User user) throws SQLException, ClassNotFoundException {
        boolean status = false;


        Connection con = ConnectDB.getConnection();
        PreparedStatement ps = con.prepareStatement(SELECT_QUERY);
        ps.setString(1, user.getEmailId());
        ps.setString(2, user.getPass());
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            status=true;
            user.setUserId(rs.getInt(1));
            user.setUserName(rs.getString(2));
        }
        ConnectDB.cleanUp(rs,ps,con);
        return status;
    }
}

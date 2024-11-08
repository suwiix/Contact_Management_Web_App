package com.servlets.utility;
import java.sql.*;


public class ConnectDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/contactManagerDB";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "****"; // postgres password

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName("org.postgresql.Driver");
        con=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        return con;
    }

    //cleanup
    public static void cleanUp(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
        if (rs!=null){
            rs.close();
        }
        if (ps!=null){
            ps.close();
        }
        if (con!=null){
            con.close();
        }
    }

    public static void cleanUp( PreparedStatement ps, Connection con) throws SQLException {
        if (ps!=null){
            ps.close();
        }
        if (con!=null){
            con.close();
        }
    }


}

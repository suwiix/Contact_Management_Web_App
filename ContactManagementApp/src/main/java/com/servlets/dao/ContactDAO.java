package com.servlets.dao;

import com.servlets.beans.Contact;
import com.servlets.utility.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private static final String ADD_CONTACT
            = "insert into contacts (user_id, first_name,last_name,phone_number,email,address,note) values(?,?,?,?,?,?,?)";
    private static final String UPDATE_CONTACT_BY_ID
            = "update contacts set first_name=?,last_name=?,phone_number=?,email=?,address=?,note=? where contact_id=?";
    private static final String DELETE_CONTACT_BY_ID = "delete from contacts  where contact_id=?";
    private static final String GET_CONTACT_BY_ID = "select * from contacts where contact_id=? order by first_name";
    private static final String GET_ALL_CONTACTS = "select * from contacts where user_id=? order by first_name";
    private static final String SEARCH_CONTACT = "select * from contacts where user_id=? and first_name=? and last_name=? order by first_name";

    public List<Contact> getAllContacts(int userId) throws SQLException, ClassNotFoundException {
        List<Contact> ContactList = new ArrayList<>();
        Connection con = ConnectDB.getConnection();
        PreparedStatement ps = con.prepareStatement(GET_ALL_CONTACTS);
        ps.setInt(1, userId);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Contact contact = new Contact(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
            ContactList.add(contact);
        }
        ConnectDB.cleanUp(rs,ps,con);
        return ContactList;
    }

    public List<Contact> searchContact(int userId,String fname, String lname) throws SQLException, ClassNotFoundException {
        List<Contact> ContactList = new ArrayList<>();
        Connection con = ConnectDB.getConnection();
        PreparedStatement ps = con.prepareStatement(SEARCH_CONTACT);
        ps.setInt(1, userId);
        ps.setString(2, fname);
        ps.setString(3, lname);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Contact contact = new Contact(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
            ContactList.add(contact);
        }
        ConnectDB.cleanUp(rs,ps,con);
        return ContactList;
    }

    public int addContact(Contact contact) throws SQLException, ClassNotFoundException {
        int status = 0;
        Connection con = ConnectDB.getConnection();
        PreparedStatement ps = con.prepareStatement(ADD_CONTACT);
        ps.setInt(1, contact.getUserId());
        ps.setString(2, contact.getFirstName());
        ps.setString(3, contact.getLastName());
        ps.setString(4, contact.getPhoneNumber());
        ps.setString(5, contact.getEmail());
        ps.setString(6, contact.getAddress());
        ps.setString(7, contact.getNote());


        System.out.println(ps);
        status = ps.executeUpdate();

        ConnectDB.cleanUp(ps,con);
        return status;
    }
    public Contact getContactById(int userId) throws SQLException, ClassNotFoundException {
        int status = 0;
        Connection con = ConnectDB.getConnection();
        PreparedStatement ps = con.prepareStatement(GET_CONTACT_BY_ID);
        ps.setInt(1, userId);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        Contact c=null;
        while (rs.next()) {
            c = new Contact(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
        }
        ConnectDB.cleanUp(rs,ps,con);
        return c;
    }

    public int updateContact(Contact contact) throws SQLException, ClassNotFoundException {
        int status = 0;
        Connection con = ConnectDB.getConnection();
        PreparedStatement ps = con.prepareStatement(UPDATE_CONTACT_BY_ID);
        ps.setString(1, contact.getFirstName());
        ps.setString(2, contact.getLastName());
        ps.setString(3, contact.getPhoneNumber());
        ps.setString(4, contact.getEmail());
        ps.setString(5, contact.getAddress());
        ps.setString(6, contact.getNote());
        ps.setInt(7,contact.getContactId());
        System.out.println(ps);
        status = ps.executeUpdate();
        ConnectDB.cleanUp(ps,con);
        return status;
    }
    public int deleteContact(int contactId) throws SQLException, ClassNotFoundException {
        int status = 0;
        Connection con = ConnectDB.getConnection();
        PreparedStatement ps = con.prepareStatement(DELETE_CONTACT_BY_ID);

        ps.setInt(1, contactId);
        System.out.println(ps);
        status = ps.executeUpdate();
        ConnectDB.cleanUp(ps,con);
        return status;
    }
}

package com.servlets.Controller;

import com.servlets.beans.Contact;
import com.servlets.dao.ContactDAO;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.servlets.beans.User;
import com.servlets.dao.UserDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;

@MultipartConfig

@WebServlet(value = "/allContacts")

public class AllContactsViewController extends HttpServlet{
    public ContactDAO contactDAO;
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=0;
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = req.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            cookie = cookies[i];
            if(cookie.getName().equals("userId")){
                id=Integer.parseInt(cookie.getValue());
            }
        }
        if(id==0){
            resp.sendRedirect("LoginPage.jsp");
            return;
        }
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        List<Contact> contactList = new ArrayList<>();
        contactDAO = new ContactDAO();
        try {
            contactList =  contactDAO.getAllContacts(id);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        writer.print("<table border='1' width='100%'");
        writer.print("<tr><th>Actions</th><th>FirstName</th><th>LastName</th><th>Phone_Number</th>" +
                "<th>Email</th><th>Address</th><th>Note</th> </tr>");
        for (Contact c : contactList) {
            writer.print("<tr><td>"+ "<a href='viewContactById?id=" + c.getContactId() + "'>View / Edit / Delete</a>   </td><td>" + c.getFirstName()+"</td><td>"
                    + c.getLastName() + "</td><td>"+ c.getPhoneNumber()
                    + "</td><td>"+ c.getEmail() +"</td><td>" + c.getAddress() + "</td><td>"
                    + c.getNote() + "</td></tr>");
        }
        writer.println("</table>");
        writer.close();
    }
}

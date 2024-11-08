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

import jakarta.servlet.ServletException;

@MultipartConfig

@WebServlet(value = "/addContact")

public class AddContactController extends HttpServlet{
    public ContactDAO contactDAO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int result = 0;
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
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
        // get user data
        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String phoneNumber = req.getParameter("phoneNumber");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String note = req.getParameter("note");

        Contact contact = new Contact(0,id,firstName,lastName,phoneNumber,email,address,note);

        contactDAO = new ContactDAO();
        try {
            result = contactDAO.addContact(contact);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        if (result > 0) {
            System.out.println("contact added successfully..");
            resp.sendRedirect("WelcomePage.jsp");
        } else {
            System.out.println("\"contact addition failed..\"");
        }
    }
}

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

@WebServlet(value = "/editContact")

public class EditContactController extends HttpServlet{
    private ContactDAO contactDAO;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int status = 0;
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
        int contact_id=Integer.parseInt(req.getParameter("contact_id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNumber = req.getParameter("phoneNumber");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String note = req.getParameter("note");

        Contact contact = new Contact(contact_id,id,firstName,lastName,phoneNumber,email,address,note);

        contactDAO = new ContactDAO();
        try {
            status = contactDAO.updateContact(contact);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        if (status>0){

            System.out.println("contact updated successfully..");
            resp.sendRedirect("allContacts");
        }else {
            System.out.println("\"contact updation failed..\"");

        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int result = 0;
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
        int contact_id=Integer.parseInt(req.getParameter("contact_id"));
        contactDAO = new ContactDAO();
        try {
            result = contactDAO.deleteContact(contact_id);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        if (result>0){
            System.out.println("contact deleted successfully..");
            resp.sendRedirect("allContacts");
        }else {
            System.out.println("\"contact deletion failed..\"");

        }
    }
}

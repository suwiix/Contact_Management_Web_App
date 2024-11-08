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

@WebServlet(value = "/viewContactById")

public class ViewContactController extends HttpServlet{
    private ContactDAO contactDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        String contactId = req.getParameter("id");
        contactDAO = new ContactDAO();
        Contact contact = null;
        try {
            contact = contactDAO.getContactById(Integer.parseInt(contactId));
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        //Hidden form field , to edit the info of the employee
        writer.println("<form name=\"Contact edit form\" method=\"post\" action=\"editContact\">");
        writer.println("<h3>Contact Details</h3>");
        writer.println("<input type='hidden' name='contact_id' value="+contact.getContactId()+"><br><br>");
        writer.println("FirstName:<input type=\"text\" name=\"firstName\" value=\""+contact.getFirstName()+"\"><br>");
        writer.println("<br><br>");
        writer.println("LastName:<input type=\"text\" name=\"lastName\" value=\""+contact.getLastName()+"\"><br>");
        writer.println("<br><br>");
        writer.println("Phone Number:<input type=\"text\" name=\"phoneNumber\" value="+contact.getPhoneNumber()+"><br>");
        writer.println("<br><br>");
        writer.println("Email:<input type=\"text\" name=\"email\" value="+contact.getEmail()+"><br>");
        writer.println("<br><br>");
        writer.println("Address:<input type=\"text\" name=\"address\" value=\""+contact.getAddress()+"\"><br>");
        writer.println("<br><br>");
        writer.println("Note:<input type=\"text\" name=\"note\" value=\""+contact.getNote()+"\"><br><br>");
        writer.println("<br><br>");
        writer.println("<input type=\"submit\" value=\"Save\">");
        writer.println("</form>");
        writer.println("<form name=\"Contact delete form\" method=\"get\" action=\"editContact\">");
        writer.println("<input type='hidden' name='contact_id' value="+contact.getContactId()+">");
        writer.println("<input type=\"submit\" value=\"Delete\">");
        writer.println("</form>");
        writer.println("<br>");
        writer.println("<a href=\"allContacts\">Back</a>");
        writer.close();
    }

}

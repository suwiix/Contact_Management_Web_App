package com.servlets.Controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import com.servlets.beans.User;
import com.servlets.dao.UserDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;

@MultipartConfig

@WebServlet(value = "/login",loadOnStartup = 1)

public class LoginController extends HttpServlet {

    public UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDAO = new UserDAO();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        String userName = req.getParameter("userName");
        String userPass = req.getParameter("userPass");

        User user = new User(userName,userPass);

        boolean status = false;

        try {
            status = userDAO.validate(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

        if (!status){
            System.out.println("Invalid login, please try with valid credential");
            resp.sendRedirect("LoginPage.jsp");
            writer.println("Invalid login, please try with valid credential");
            writer.close();
            return;
            //            req.getRequestDispatcher("/emp-form.html").include(req, resp);
        }

        Cookie cookie1= new Cookie("userId",user.getUserId()+"");
        resp.addCookie(cookie1);
        writer.println("login successful..");
        System.out.println("\"login successful..\"");
        resp.sendRedirect("WelcomePage.jsp");
        writer.close();

    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = req.getCookies();
        resp.setContentType("application/json");
        for (int i = 0; i < cookies.length; i++) {
            cookie = cookies[i];
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        resp.sendRedirect("LoginPage.jsp");
    }
}

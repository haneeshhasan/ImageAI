package com.tap.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.ha.ReplicationMySQLConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
    String url = "jdbc:mysql://localhost:3306/employee";
    String un = "root";
    String pwd = "root";
    String query = "select * from student";
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet result = null;
//
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, un, pwd);
            Statement stmt = con.createStatement();
            result = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String pass = req.getParameter("pass");

            pstmt = con.prepareStatement("select * from student where username=?");
            pstmt.setString(1, name);
            ResultSet res1 = pstmt.executeQuery();

            resp.setContentType("text/html");

            if (res1.next()) {
                if (res1.getString(7).equals(pass)) {
                	req.setAttribute("name",res1.getString(2));
                    req.getRequestDispatcher("/profiledetails").forward(req, resp);
                } else {
                	resp.sendRedirect(req.getContextPath() + "/invalidlogin.html");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/invalidlogin.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

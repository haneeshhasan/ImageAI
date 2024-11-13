package com.tap.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class signup extends HttpServlet {
    String url = "jdbc:mysql://localhost:3306/employee";
    String un = "root";
    String pwd = "root";
    Connection con =null;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, un, pwd);
			
		} catch (Exception e) {
			System.out.println("database connection problem");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fullname=req.getParameter("fullname");
		String username=req.getParameter("username");
		String pass=req.getParameter("pass");
		String profilePic=req.getParameter("profilePic");
		String role=req.getParameter("role");
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS last_id FROM student");
			int lastId = 0;
			if (rs.next()) {
				lastId = rs.getInt("last_id");
			}
			
			PreparedStatement newuserinsertquery=con.prepareStatement("INSERT INTO student (id, name, username, password, role, profile_pic)\r\n"
					+ "VALUES (?, ?, ?, ?, ?, ?);\r\n"
					+ "");
			newuserinsertquery.setInt(1, lastId+1);
			newuserinsertquery.setString(2, fullname);
			newuserinsertquery.setString(3, username);
			newuserinsertquery.setString(4, pass);
			newuserinsertquery.setString(5, role);
			newuserinsertquery.setString(6, profilePic);
			if(!(newuserinsertquery.execute())) {
				resp.sendRedirect("index.html");
			}else {
				resp.getWriter().println("SOMETHING WENT WRONG TRY AGAIN");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
	}

}

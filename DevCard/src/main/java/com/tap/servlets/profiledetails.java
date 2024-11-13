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
import java.util.ArrayList;

public class profiledetails extends HttpServlet {
	
	String url = "jdbc:mysql://localhost:3306/employee";
	String un = "root";
	String pwd = "root";
	Connection con = null;
	
	@Override
	public void init() throws ServletException {
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,un,pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name = (String)req.getAttribute("name");
			PreparedStatement detailsquery = con.prepareStatement("SELECT \r\n"
					+ "    s.id AS student_id,\r\n"
					+ "    s.name AS student_name,\r\n"
					+ "    s.username AS student_username,\r\n"
					+ "    s.password AS student_password,\r\n"
					+ "    GROUP_CONCAT(DISTINCT sk.skill ORDER BY sk.skill ASC) AS skills,\r\n"
					+ "    GROUP_CONCAT(DISTINCT h.hobby ORDER BY h.hobby ASC) AS hobbies,\r\n"
					+ "    GROUP_CONCAT(DISTINCT a.achievement ORDER BY a.achievement ASC) AS achievements,\r\n"
					+ "    GROUP_CONCAT(DISTINCT sl.link ORDER BY sl.link ASC) AS links\r\n"
					+ "FROM \r\n"
					+ "    student s\r\n"
					+ "LEFT JOIN \r\n"
					+ "    skills sk ON s.id = sk.student_id\r\n"
					+ "LEFT JOIN \r\n"
					+ "    hobbies h ON s.id = h.student_id\r\n"
					+ "LEFT JOIN \r\n"
					+ "    achievements a ON s.id = a.student_id\r\n"
					+ "LEFT JOIN \r\n"
					+ "    social_links sl ON s.id = sl.student_id \r\n"
					+ "WHERE \r\n"
					+ "    s.name = ?  -- Placeholder for student name\r\n"
					+ "GROUP BY \r\n"
					+ "    s.id;\r\n"
					+ "");
			detailsquery.setString(1, name);
			ResultSet userDetails=detailsquery.executeQuery();
			int studentid = 0;
			String[] skillarray = null;
			String[] hobbiesarray =null;
			String[] achievmentsarray = null;
			String[] linksarray= null;
			while(userDetails.next()) {
				studentid = userDetails.getInt(1);
				if (!(userDetails.getString(5) == null)) {
					String skillstring = userDetails.getString(5);
					skillarray = skillstring.split(",");
					
				}else {
					skillarray = null;
				}
				
				if (!(userDetails.getString(6) == null)) {		
					String hobbiesstring = userDetails.getString(6);
					hobbiesarray = hobbiesstring.split(",");
				} else {
					hobbiesarray=null;
				}
				if (!(userDetails.getString(7) == null)) {
					
					String achievmentstring = userDetails.getString(7);
					achievmentsarray = achievmentstring.split(",");
				} else {
					achievmentsarray = null;
				}
				
				if (!(userDetails.getString(8) == null)) {
					String linkstring = userDetails.getString(8);
					linksarray = linkstring.split(",");
					
				} else {
					linksarray=null;
				}
			}
            PreparedStatement pstmt = con.prepareStatement("select * from student where name=?");
            pstmt.setString(1, name);
            ResultSet res1 = pstmt.executeQuery();
            
            
            res1.next();
        	req.setAttribute("tenth",res1.getString(3));
        	req.setAttribute("twelth",res1.getString(4));
        	req.setAttribute("grad",res1.getString(5));
        	req.setAttribute("role",res1.getString(8));
        	req.setAttribute("pic", res1.getString(9));
			req.setAttribute("skills", skillarray);
			req.setAttribute("hobbies", hobbiesarray);
			req.setAttribute("achievements", achievmentsarray);
			req.setAttribute("links", linksarray);
			req.setAttribute("studentid", studentid);
			req.getRequestDispatcher("/homepage.jsp").forward(req, resp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

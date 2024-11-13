package com.tap.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class eligible extends HttpServlet {
	Connection con = null;
	String url = "jdbc:mysql://localhost:3306/employee";
	String un = "root";
	String pwd = "root";
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, un , pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getParameter("pass");
		try {
			PrintWriter writer=resp.getWriter();
			
			writer.println("HERE ARE THE DRIVES YOU ARE ELIGBLE FOR");
			PreparedStatement pstmt= con.prepareStatement("select * from student where username=?");
			pstmt.setString(1, req.getParameter("name"));
			ResultSet userdetailsres=pstmt.executeQuery();
			userdetailsres.next();
			PreparedStatement pstmtdrive=con.prepareStatement("select * from placement where 10th <= ? and 12th <= ? and grad <= ?");
			pstmtdrive.setInt(1, userdetailsres.getInt(3));
			pstmtdrive.setInt(2, userdetailsres.getInt(4));
			pstmtdrive.setInt(3, userdetailsres.getInt(5));
			ResultSet res3=pstmtdrive.executeQuery();
			writer.println("<table border=1>\r\n"
					+ "  <tr>\r\n"
					+ "    <th>"+res3.getMetaData().getColumnName(1)+"</th>\r\n"
					+ "    <th>"+res3.getMetaData().getColumnName(2)+"</th>\r\n"
					+ "    <th>"+res3.getMetaData().getColumnName(3)+"</th>\r\n"
					+ "    <th>"+res3.getMetaData().getColumnName(4)+"</th>\r\n"
					+ "    <th>"+res3.getMetaData().getColumnName(5)+"</th>\r\n"
					+ "    <th>"+res3.getMetaData().getColumnName(6)+"</th>\r\n"
					+ "    <th>"+res3.getMetaData().getColumnName(7)+"</th>\r\n"
					+ "    <th>"+res3.getMetaData().getColumnName(8)+"</th>\r\n"
					+ "  </tr>");
			while(res3.next()) {
					resp.getWriter().println("<tr>\r\n"
							+ "    <td>"+res3.getInt(1)+"</td>\r\n"
							+ "    <td>"+res3.getString(2)+"</td>\r\n"
							+ "    <td>"+res3.getInt(3)+"</td>\r\n"
							+ "    <td>"+res3.getInt(4)+"</td>\r\n"
							+ "    <td>"+res3.getInt(5)+"</td>\r\n"
							+ "    <td>"+res3.getString(6)+"</td>\r\n"
							+ "    <td>"+res3.getString(7)+"</td>\r\n"
							+ "    <td>"+res3.getInt(8)+"</td>\r\n"
							+ "  </tr>\r\n");
				}
				resp.getWriter().println("</table>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

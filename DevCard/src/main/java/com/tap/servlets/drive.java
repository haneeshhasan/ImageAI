package com.tap.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class drive extends HttpServlet {
	Connection con = null;
	String url = "jdbc:mysql://localhost:3306/employee";
	String un = "root";
	String pwd = "root";
	
	@Override
	public void init() throws ServletException {
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,un,pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("i am in drive");
		try {
			Statement stmt=con.createStatement();
			ResultSet res = stmt.executeQuery("select * from placement");
			resp.getWriter().println("<table border=1>\r\n"
					+ "  <tr>\r\n"
					+ "    <th>"+res.getMetaData().getColumnName(1)+"</th>\r\n"
					+ "    <th>"+res.getMetaData().getColumnName(2)+"</th>\r\n"
					+ "    <th>"+res.getMetaData().getColumnName(3)+"</th>\r\n"
					+ "    <th>"+res.getMetaData().getColumnName(4)+"</th>\r\n"
					+ "    <th>"+res.getMetaData().getColumnName(5)+"</th>\r\n"
					+ "    <th>"+res.getMetaData().getColumnName(6)+"</th>\r\n"
					+ "    <th>"+res.getMetaData().getColumnName(7)+"</th>\r\n"
					+ "    <th>"+res.getMetaData().getColumnName(8)+"</th>\r\n"
					+ "  </tr>");
			while(res.next()) {
					resp.getWriter().println("<tr>\r\n"
							+ "    <td>"+res.getInt(1)+"</td>\r\n"
							+ "    <td>"+res.getString(2)+"</td>\r\n"
							+ "    <td>"+res.getInt(3)+"</td>\r\n"
							+ "    <td>"+res.getInt(4)+"</td>\r\n"
							+ "    <td>"+res.getInt(5)+"</td>\r\n"
							+ "    <td>"+res.getString(6)+"</td>\r\n"
							+ "    <td>"+res.getString(7)+"</td>\r\n"
							+ "    <td>"+res.getInt(8)+"</td>\r\n"
							+ "  </tr>\r\n");
				}
				resp.getWriter().println("</table>");
				
				req.getRequestDispatcher("/eligible").include(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

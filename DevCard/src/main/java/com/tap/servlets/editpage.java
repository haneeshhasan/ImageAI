package com.tap.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

/**
 * Servlet implementation class editpage
 */
public class editpage extends HttpServlet {
	
	String url = "jdbc:mysql://localhost:3306/employee";
	String un = "root";
	String pwd = "root";
	Connection con = null;
	int id = 0;
	
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	    id = Integer.parseInt(req.getParameter("id"));
	    
	    
	    try {
			PreparedStatement resultusingid=con.prepareStatement("SELECT \r\n"
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
					+ "    s.id = ? \r\n"
					+ "GROUP BY \r\n"
					+ "    s.id;\r\n"
					+ "");
			
			resultusingid.setInt(1, id);
			ResultSet userDetails=resultusingid.executeQuery();
			
			String[] skillarray = null;
			String[] hobbiesarray =null;
			String[] achievmentsarray = null;
			String[] linksarray= null;
			String name=null;
			while(userDetails.next()) {
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
				
				name= userDetails.getString(2);
			}
			
			
			PreparedStatement stmt=con.prepareStatement("Select * from student where id = ?");
			stmt.setInt(1, id);
			ResultSet res =stmt.executeQuery();
			int tenth = 0;
			int twelth = 0;
			int grad = 0;
			String role= null;
			String pic = null;
			while(res.next()) {
				tenth = res.getInt(3);
				twelth = res.getInt(4);
				grad = res.getInt(5);
				role = res.getString(8);
				pic = res.getString(9);
			}
			
			req.setAttribute("tenth", tenth);
			req.setAttribute("twelth", twelth);
			req.setAttribute("grad", grad);
			req.setAttribute("role", role);
			req.setAttribute("pic", pic);
			req.setAttribute("name", name);
			req.setAttribute("skills", skillarray);
			req.setAttribute("hobbies", hobbiesarray);
			req.setAttribute("achievements", achievmentsarray);
			req.setAttribute("links", linksarray);
			req.getRequestDispatcher("/editpage.jsp").forward(req, resp);
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String name = req.getParameter("name");
	    String role = req.getParameter("role");
	    String pic = req.getParameter("pic");
	    String tenth = req.getParameter("tenth");
	    String twelth = req.getParameter("twelth");
	    String grad = req.getParameter("grad");
	    String skill1 = req.getParameter("skill1");
	    String skill2 = req.getParameter("skill2");
	    String skill3 = req.getParameter("skill3");
	    String hobby1 = req.getParameter("hobby1");
	    String hobby2 = req.getParameter("hobby2");
	    String hobby3 = req.getParameter("hobby3");
	    String achievement1 = req.getParameter("achievement1");
	    String achievement2 = req.getParameter("achievement2");
	    String achievement3 = req.getParameter("achievement3");
	    String github = req.getParameter("github");
	    String instagram = req.getParameter("instagram");
	    String linkedin = req.getParameter("linkedin");
	    int studentId = id;
	    try {
	        // Start transaction
	        con.setAutoCommit(false);

	        // Update student information
	        PreparedStatement updateStudent = con.prepareStatement("UPDATE student SET name = ?, role = ?, profile_pic = ?, 10th = ?, 12th = ?, grad = ? WHERE id = ?;");
	        updateStudent.setString(1, name);
	        updateStudent.setString(2, role);
	        updateStudent.setString(3, pic);
	        updateStudent.setString(4, tenth);
	        updateStudent.setString(5, twelth);
	        updateStudent.setString(6, grad);
	        updateStudent.setInt(7, studentId);
	        updateStudent.executeUpdate();
	        
	        
	        // Delete existing skills, hobbies, achievements, and social links
	        PreparedStatement deleteSkills = con.prepareStatement("DELETE FROM skills WHERE student_id = ?;");
	        PreparedStatement deleteHobbies = con.prepareStatement("DELETE FROM hobbies WHERE student_id = ?;");
	        PreparedStatement deleteAchievements = con.prepareStatement("DELETE FROM achievements WHERE student_id = ?;");
	        PreparedStatement deleteSocialLinks = con.prepareStatement("DELETE FROM social_links WHERE student_id = ?;");

	        deleteSkills.setInt(1, studentId);
	        deleteSkills.executeUpdate();
	        
	        deleteHobbies.setInt(1, studentId);
	        deleteHobbies.executeUpdate();
	        
	        deleteAchievements.setInt(1, studentId);
	        deleteAchievements.executeUpdate();
	        
	        deleteSocialLinks.setInt(1, studentId);
	        deleteSocialLinks.executeUpdate();

	        // Prepare batch insert statements
	        PreparedStatement insertSkills = con.prepareStatement("INSERT INTO skills (student_id, skill) VALUES (?, ?);");
	        PreparedStatement insertHobbies = con.prepareStatement("INSERT INTO hobbies (student_id, hobby) VALUES (?, ?);");
	        PreparedStatement insertAchievements = con.prepareStatement("INSERT INTO achievements (student_id, achievement) VALUES (?, ?);");
	        PreparedStatement insertSocialLinks = con.prepareStatement("INSERT INTO social_links (student_id, link) VALUES (?, ?);");

	        // Insert new skills
	        insertSkills.setInt(1, studentId);
	        insertSkills.setString(2, skill1);
	        insertSkills.addBatch();
	        
	        insertSkills.setString(2, skill2);
	        insertSkills.addBatch();

	        insertSkills.setString(2, skill3);
	        insertSkills.addBatch();
	        
	        insertSkills.executeBatch();

	        // Insert new hobbies
	        insertHobbies.setInt(1, studentId);
	        insertHobbies.setString(2, hobby1);
	        insertHobbies.addBatch();
	        
	        insertHobbies.setString(2, hobby2);
	        insertHobbies.addBatch();

	        insertHobbies.setString(2, hobby3);
	        insertHobbies.addBatch();

	        insertHobbies.executeBatch();

	        // Insert new achievements
	        insertAchievements.setInt(1, studentId);
	        insertAchievements.setString(2, achievement1);
	        insertAchievements.addBatch();
	        
	        insertAchievements.setString(2, achievement2);
	        insertAchievements.addBatch();

	        insertAchievements.setString(2, achievement3);
	        insertAchievements.addBatch();

	        insertAchievements.executeBatch();

	        // Insert new social links
	        insertSocialLinks.setInt(1, studentId);
	        insertSocialLinks.setString(2, github);
	        insertSocialLinks.addBatch();
	        
	        insertSocialLinks.setString(2, instagram);
	        insertSocialLinks.addBatch();

	        insertSocialLinks.setString(2, linkedin);
	        insertSocialLinks.addBatch();

	        insertSocialLinks.executeBatch();

	        // Commit transaction
	        con.commit();
	        req.setAttribute("name",name);
	        req.getRequestDispatcher("/profiledetails").forward(req, resp); // Redirect to homepage only if all operations are successful

	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            // Rollback transaction in case of an error
	            if (con != null) {
	                con.rollback();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        resp.sendRedirect("error.jsp"); // Redirect to an error page in case of failure
	    } finally {
	        try {
	            // Restore auto-commit mode
	            con.setAutoCommit(true);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


}

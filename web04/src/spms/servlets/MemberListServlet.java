package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			ServletContext ctx = this.getServletContext();
			Class.forName(ctx.getInitParameter("driver"));
			
			conn = DriverManager.getConnection(
					ctx.getInitParameter("url"), //JDBC URL
					ctx.getInitParameter("username"),	// DBMS 사용자 아이디
					ctx.getInitParameter("password"));	// DBMS 사용자 암호
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
					" FROM MEMBERS" +
					" ORDER BY MNO ASC");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원목록</title></head>");
			out.println("<body><h1>회원목록</h1>");
			out.println("<p><a href='add'>신규 회원</a></p>");
			while(rs.next()) {
				out.println(
					rs.getInt("MNO") + "," +
					"<a href='update?no=" + rs.getInt("MNO") + "'>" +
					rs.getString("MNAME") + "</a>," +
					rs.getString("EMAIL") + "," + 
					rs.getDate("CRE_DATE") + 
					"<a href='delete?no=" + rs.getInt("MNO") + 
					"'>[삭제]</a><br>");
			}
			out.println("</body></html>");
			
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
}

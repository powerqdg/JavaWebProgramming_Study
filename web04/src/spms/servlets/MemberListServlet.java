package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
public class MemberListServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1. 사용할 JDBC 드라이버를 등록하라.
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Class.forName(this.getInitParameter("driver"));
			
			//2. 드라이버를 사용하어 Oracle 서버와 연결하라.
			conn = DriverManager.getConnection(
					this.getInitParameter("url"), //JDBC URL
					this.getInitParameter("username"),	// DBMS 사용자 아이디
					this.getInitParameter("password"));	// DBMS 사용자 암호
			
			//3. 커넥션 객체로부터 SQL을 던질 객체를 준비하라.
			stmt = conn.createStatement();
			
			//4. SQL을 던지는 객체를  사용하여 서버에 질의하라!
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
					" FROM MEMBERS" +
					" ORDER BY MNO ASC");
			
			//5. 서버에서 가져온 데이터를  사용하여 HTML만들어서 웹 브라우저로 출력하라.
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
					rs.getDate("CRE_DATE") + "<br>"
				);
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

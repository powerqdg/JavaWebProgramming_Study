package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1. 사용할 JDBC 드라이버를 등록하라.
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			//Class.forName(this.getInitParameter("driver"));
			
			ServletContext ctx = this.getServletContext();
			Class.forName(ctx.getInitParameter("driver"));
			
			//2. 드라이버를 사용하어 Oracle 서버와 연결하라.
			conn = DriverManager.getConnection(
					ctx.getInitParameter("url"), //JDBC URL
					ctx.getInitParameter("username"),	// DBMS 사용자 아이디
					ctx.getInitParameter("password"));	// DBMS 사용자 암호
			
			//3. 커넥션 객체로부터 SQL을 던질 객체를 준비하라.
			stmt = conn.createStatement();
			
			//4. SQL을 던지는 객체를  사용하여 서버에 질의하라!
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
					" FROM MEMBERS" +
					" WHERE MNO=" + request.getParameter("no"));
			if(rs.next()) {
				Member member =  new Member();
				member.setNo(rs.getInt("MNO"));
				member.setEmail(rs.getString("EMAIL"));
				member.setName(rs.getNString("MNAME"));
				member.setCreatedDate(rs.getDate("CRE_DATE"));
				
				// request에 회원 목록 데이터 보관한다.
				request.setAttribute("member", member);
			} else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
			
			// JSP로 출력을 위임한다.
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdateForm.jsp");
			rd.forward(request, response);
			
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CharacterEncodingFilter에서 처리
		//request.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			//1. 사용할 JDBC 드라이버를 등록하라.
			ServletContext ctx = this.getServletContext();
			Class.forName(ctx.getInitParameter("driver"));
			
			//2. 드라이버를 사용하어 Oracle 서버와 연결하라.
			conn = DriverManager.getConnection(
					ctx.getInitParameter("url"), //JDBC URL
					ctx.getInitParameter("username"),	// DBMS 사용자 아이디
					ctx.getInitParameter("password"));	// DBMS 사용자 암호
			
			//3. 커넥션 객체로부터 SQL을 던질 객체를 준비하라.
			stmt = conn.prepareStatement("UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=SYSDATE" + " WHERE MNO=?");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("name"));
			stmt.setInt(3, Integer.parseInt(request.getParameter("no")));
			stmt.executeUpdate();
			
			response.sendRedirect("list");
			
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
}

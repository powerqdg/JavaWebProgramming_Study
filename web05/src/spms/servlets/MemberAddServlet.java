package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/member/MemberForm.jsp");
		rd.include(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CharacterEncodingFilter에서 처리
		//request.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement stmt = null;

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
			stmt = conn.prepareStatement(
					"INSERT INTO MEMBERS(MNO,EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
					+ " VALUES (MEMBERS_SEQ.NEXTVAL,?,?,?,SYSDATE,SYSDATE)");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("password"));
			stmt.setString(3, request.getParameter("name"));
			
			//4. SQL을 던지는 객체를  사용하여 서버에 질의하라!
			stmt.executeUpdate();
			
			// 리다이렉트를 이용한 리프래시
			response.sendRedirect("list");
			
			// 주석처리 - 리다이렉트 이용시, 웹 브라우저에 출력할 내용이 없으므로 HTML을 만들필요 없다.
			/*
			//5. 서버에서 가져온 데이터를  사용하여 HTML만들어서 웹 브라우저로 출력하라. 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원등록결과</title>");
			// meta 태그를 이용한 리프래시
			out.println("<meta http-equiv='Refresh' content='1; url=list'>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>등록 성공입니다!</p>");
			out.println("</body></html>");
			
			// 리프래시 정보를 응답 헤더에 추가
			//response.setHeader("Refresh", "1;url=list");
			//response.addHeader("Refresh", "1;url=list");
			
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
			*/
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

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.security.MD5Encoder;

import sql.SqlConnectDao;
import sql.SqlOpenHelper;



/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SqlOpenHelper sqlOpenHelper;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		StringBuffer msg = new StringBuffer();
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if(username == null || username.trim().equals("")) {
				msg.append("请输入用户名：");
				out.print(msg.toString());
				return;
			}
			
			if(password == null || password.trim().equals("")) {
				msg.append("请输入密码：");
				out.print(msg.toString());
				return;
			}
			
			sqlOpenHelper = new SqlOpenHelper();
			String pwd = "";
			Map<String, String> values = new HashMap<>();
			values.put("username", username);
			ResultSet rs = sqlOpenHelper.SelectDataQuery(sqlOpenHelper.LOGIN_TABLE, values);
			while(rs.next()) {
				pwd = rs.getString("password");
			}
			
			StringBuffer stb = new StringBuffer();
			
			for(char ch:pwd.toCharArray()) {
				System.out.print((char)(ch+36));
				stb.append((char)(ch+36));
			}
			System.out.println();
			
			if(username.equals(username)&&stb.toString().equals(password)) {
				String url = "main.jsp";
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				msg.append("Redirect:"+url);
				out.print(msg.toString());
			}else {
				msg.append("用户名和密码不正确：");
				out.print(msg.toString());
				return;
			}
		} catch (Exception e) {
			msg.append("system error");
			out.print(msg.toString());
			e.printStackTrace();
		} finally {
			if(sqlOpenHelper != null) {
				sqlOpenHelper.close();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

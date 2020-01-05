package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sql.SqlOpenHelper;

/**
 * Servlet implementation class RegisterCheck
 */
@WebServlet("/RegisterCheck")
public class RegisterCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SqlOpenHelper sqlOpenHelper;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCheck() {
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
			String studentid = request.getParameter("studentid");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
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
			
			if(phone == null || phone.trim().equals("")) {
				msg.append("请输入电话/S：");
				out.print(msg.toString());
				return;
			}
			
			if(email == null || email.trim().equals("")) {
				msg.append("请输入邮箱/S：");
				out.print(msg.toString());
				return;
			}
			
			if(studentid == null || studentid.trim().equals("")) {
				msg.append("请输入学号/S：");
				out.print(msg.toString());
				return;
			}
			
			sqlOpenHelper = new SqlOpenHelper();
			StringBuffer stb = new StringBuffer();
			for(char ch:password.toCharArray()) {
				stb.append((char)(ch-36));
			}
			Map<String, String> values1 = new HashMap<>();
			Map<String, String> values2 = new HashMap<>();
			values1.put("username", username);
			
			if(sqlOpenHelper.SelectDataQuery(sqlOpenHelper.LOGIN_TABLE, values1).isLast() == false) {
				values1.put("password", stb.toString());
				values2.put("studentid", studentid);
				values2.put("studentname", username);
				if(sqlOpenHelper.SelectDataQuery(sqlOpenHelper.STUDENT_TABLE, values2).isLast() == false)
					sqlOpenHelper.insertDateQuery(sqlOpenHelper.LOGIN_TABLE, values1);
					values2.put("phone", phone);
					values2.put("email", email);
					sqlOpenHelper.insertDateQuery(sqlOpenHelper.STUDENT_TABLE, values2);
					String url = "index.jsp";
					msg.append("Redirect:"+url);
					out.print(msg.toString());
			}else {
				msg.append("用户或学号已注册");
				out.print(msg.toString());
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

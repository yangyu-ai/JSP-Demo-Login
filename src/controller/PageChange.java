package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Course;
import entity.Student;
import entity.Students;
import sql.SqlOpenHelper;

/**
 * Servlet implementation class PageChange
 */
@WebServlet("/PageChange")
public class PageChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int status = Integer.parseInt(request.getParameter("page"));
		System.out.println(status);
		StringBuffer stb = new StringBuffer();
		
		switch(status) {
			case 1:
				Map<String, String> values = new HashMap<>();
				values.put("studentname", (String)request.getSession().getAttribute("username"));
				ResultSet rs = new SqlOpenHelper().SelectDataQuery(SqlOpenHelper.STUDENT_TABLE, values);
				try {
					while(rs.next()) {
						stb.append("<tr><th><h2>ѧ��: </h2></th><th><h3>"+rs.getString(Student.STUDENTID)+"</h3></th></tr>"+
								"<th><h2>����: </h2></th><th><h3>"+rs.getString(Student.STUDENTNAME)+"</h3></th></tr>"+
								"<th><h2>�绰: </h2></th><th><h3>"+rs.getString(Student.PHONE)+"</h3></th></tr>"+
								"<th><h2>����: </h2></th><th><h3>"+rs.getString(Student.EMAIL)+"</h3></th></tr>");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				ArrayList<Student> list = new Students().getStudents();
				stb.append("<tr><th>ѧ��</th>"+
						"<th>����</th>"+
						"<th>�绰</th>"+
						"<th>����</th></tr>");
				
				for(int i=0;i<8;i++){
					if(i>=list.size()) {
						stb.append("<tr><th> </th>"+
								"<th> </th>"+
								"<th> </th>"+
								"<th> </th></tr>");
					}else {
						Student stu=list.get(i);
						stb.append("<tr><th>"+stu.getStudentid()+"</th>"+
								"<th>"+stu.getStudentname()+"</th>"+
								"<th>"+stu.getPhone()+"</th>"+
								"<th>"+stu.getEmail()+"</th></tr>");
					}
					
				}
				break;
			case 3:
//				new Course().createTxtCourse();
				ArrayList<Course> listC = new Course().getList();
				stb.append("<tr><th>�γ̱��</th>"+
						"<th>�γ�����</th></tr>");
				
				for(int i=0;i<8;i++){
					if(i>=listC.size()) {
						stb.append("<tr><th> </th>"+
								"<th> </th>"+
								"<th> </th>"+
								"<th> </th></tr>");
					}else {
						Course cou=listC.get(i);
						stb.append("<tr><th>"+cou.getClassid()+"</th>"+
								"<th>"+cou.getClassname()+"</th></tr>");
					}
					
				}
				break;
			case 4:
				break;
			case 5:
				break;
			default:stb.append("switch error");
		}
		
		request.setAttribute("tableTitle", status+"");
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

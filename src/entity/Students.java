package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sql.SqlOpenHelper;

public class Students {
	private ArrayList<Student> students;
	
	public Students() {
		// TODO Auto-generated constructor stub
		students = new ArrayList<>();
		ResultSet rs = new SqlOpenHelper().SelectDataQuery(SqlOpenHelper.STUDENT_TABLE, null);
		try {
			while(rs.next()){
				Student stu = new Student();
				stu.setStudentname(rs.getString(stu.STUDENTNAME));
				stu.setStudentid(rs.getString(stu.STUDENTID));
				stu.setPhone(rs.getString(stu.PHONE));
				stu.setEmail(rs.getString(stu.EMAIL));
				students.add(stu);
				System.out.println(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	
}

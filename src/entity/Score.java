package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sql.SqlOpenHelper;

public class Score {
	private Student student;
	private ArrayList course_score;
	
	public Score(String studentname) {
		// TODO Auto-generated constructor stub
		SqlOpenHelper sql = new SqlOpenHelper();
		Map<String, String> values = new HashMap<String, String>();
		values.put(Student.STUDENTNAME, studentname);
		ResultSet rs = sql.SelectDataQuery(sql.STUDENT_TABLE, values);
		student = new Student();
		try {
			while(rs.next()) {
				student.setStudentname(rs.getString(student.STUDENTNAME));
				student.setStudentid(rs.getString(student.STUDENTID));
				student.setPhone(rs.getString(student.PHONE));
				student.setEmail(rs.getString(student.EMAIL));
			}
			course_score = new ArrayList();
			values.remove(Student.STUDENTNAME);
			values.put(student.STUDENTID, student.getStudentid());
			rs = sql.SelectDataQuery(sql.SCORE_TABLE, values);
			while(rs.next()) {
				if(student.getStudentid().equals(rs.getString(student.STUDENTID))){
					Scores s = new Scores();
					Course cou = new Course();
					String cid = rs.getString(Course.CLASSID);
					cou.setClassid(cid);
					Map cvalue = new HashMap<>();
					cvalue.put(Course.CLASSID, cid);
					ResultSet c = sql.SelectDataQuery(sql.COURSE_TABLE, cvalue);
					while(c.next()) {
						cou.setClassname(c.getString(cou.CLASSNAME));
					}
					s.setValue(Integer.parseInt(rs.getString("score")));
					s.setC(cou);
					course_score.add(s);
				}
			}
			System.out.println(student);
			for(Object s:course_score) {
				System.out.println(((Scores)s).getValue()+"->"+((Scores)s).getC().getClassname());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public ArrayList getCourse_score() {
		return course_score;
	}
	
	public class Scores{
		Course c;
		int value;
		public Course getC() {
			return c;
		}
		public void setC(Course c) {
			this.c = c;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		
	}
}

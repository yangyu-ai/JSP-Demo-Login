package entity;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sql.SqlOpenHelper;

public class Course {
	final public static String CLASSNAME= "classname";
	final public static String CLASSID = "classid";
	
	private String classname;
	private String classid;
	protected ArrayList<Course> list;
	
	public ArrayList<Course> getList() {
		list = new ArrayList<>();
		ResultSet rs = new SqlOpenHelper().SelectDataQuery(SqlOpenHelper.COURSE_TABLE, null);
		try {
			while(rs.next()) {
				Course c = new Course();
				c.setClassid(rs.getString(CLASSID));
				c.setClassname(rs.getString(CLASSNAME));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void createTxtCourse() {
		String[] course = ("Android移动互联网应用开发,10008;" + 
				"体育与健康,10009;" + 
				"信息资源检索,10010;" + 
				"面向对象程序设计,10011;" + 
				"工程应用数学,10012;" + 
				"Java程序设计基础,10013;").split(";");
		
		for(int index=0;index<course.length;index++) {
			
			String[] str = course[index].split(",");
			if(str[0].equals(""))
				break;
			Map<String, String> values = new HashMap<String, String>();
			values.put(CLASSID, str[1]);
			values.put(CLASSNAME, str[0]);
			new SqlOpenHelper().insertDateQuery(SqlOpenHelper.COURSE_TABLE, values);
		}
			
	}
	
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	
	
	
	
}

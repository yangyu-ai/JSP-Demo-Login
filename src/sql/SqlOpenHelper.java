package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import entity.Student;

public class SqlOpenHelper {
	final private SqlConnectDao db;
	final public static String LOGIN_TABLE = "LoginTable";
	final public static String STUDENT_TABLE = "StudentTable";
	final public static String COURSE_TABLE = "CourseTable";
	final public static String SCORE_TABLE = "ScoreTable";
	
	public SqlOpenHelper() {
		// TODO Auto-generated constructor stub
		db = new SqlConnectDao();
	}
	
	public ResultSet SelectDataQuery(String table, Map<String, String> values) {
		StringBuffer sql = new StringBuffer("select * from "+table);
		if(values != null) {
			sql.append(" where ");
			boolean flag = true;
			for(Map.Entry<String, String> entry:values.entrySet()) {
				if(flag) {
					flag = false;
					sql.append(entry.getKey()+"='"+entry.getValue()+"' ");
				}else
					sql.append("and "+entry.getKey()+"='"+entry.getValue()+"'");
			}
		}
		
		System.out.println(sql.toString());
		return db.queryTable(sql.toString());
		
	}
	
	public void insertDateQuery(String table, Map<String, String> values) {
		StringBuffer sql = new StringBuffer("insert into "+table+" set ");
		boolean flag = true;
		for(Map.Entry<String, String> entry:values.entrySet()) {
			if(flag) {
				flag = false;
				sql.append(entry.getKey()+"='"+entry.getValue()+"' ");
			}else
				sql.append(", "+entry.getKey()+"='"+entry.getValue()+"' ");
		}
		System.out.println(sql.toString());
		db.updateTable(sql.toString());
	}
	
	
	
	private void createLoginTable() {
		String SQL_TABLE = "create table if not exists LoginTable(" +
				"username varchar(30) not null primary key," + 
				"password varchar(255) not null" + 
				")ENGINE=InnoDB default charset=utf8;";
		db.updateTable(SQL_TABLE);
		
	}
	
	private void createStudentTable() {
		String SQL_TABLE = "create table if not exists StudentTable(" +
				"studentname varchar(30) not null," + 
				"studentid int not null primary key," +
				"phone varchar(11)," + 
				"email varchar(255)" + 
				")ENGINE=InnoDB default charset=utf8;";
		db.updateTable(SQL_TABLE);
		
	}
	
	private void createCourseTable() {
		String SQL_TABLE = "create table if not exists CourseTable(" +
				"classname varchar(30) not null," + 
				"classid int not null primary key" + 
				")ENGINE=InnoDB default charset=utf8;";
		db.updateTable(SQL_TABLE);
		
	}
	
	private void createScoreTable() {
		String SQL_TABLE = "create table if not exists ScoreTable(" +
				"scoreid varchar(30) not null primary key," + 
				"classid int not null," + 
				"studentid int not null," +
				"score int not null" +
				")ENGINE=InnoDB default charset=utf8;";
		db.updateTable(SQL_TABLE);
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		if(db != null) {
			db.close();
		}
	}
}

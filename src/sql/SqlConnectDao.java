package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlConnectDao {
	final private String url = "jdbc:mysql://localhost:3306/fishhead?serverTimezone=UTC&characterEncoding=utf-8&useSSL=false";
	final private String root = "root";
	final private String pwd = "123456";
	final private String driver = "com.mysql.cj.jdbc.Driver";
	Connection conn;
	PreparedStatement stmt;
	
	public SqlConnectDao() {
		// TODO Auto-generated constructor stub
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, root, pwd);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isExistTable(String table) {
		try {
			stmt = conn.prepareStatement("show tables like \"" + table + "\"");
			int count = stmt.executeUpdate();
			if(count == 0) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ResultSet queryTable(String sql) {
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet r = stmt.executeQuery();
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateTable(String sql) {
		try {
			stmt = conn.prepareStatement(sql);
			int count = stmt.executeUpdate();
			if(count == 0) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void close() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

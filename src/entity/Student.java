package entity;

public class Student {
	final public static String STUDENTNAME="studentname";
	final public static String STUDENTID="studentid";
	final public static String PHONE="phone";
	final public static String EMAIL="email";
	
	
	private String studentname;
	private String studentid;
	private String phone;
	private String email;
	
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.studentid+" "+this.studentname+" "+this.phone+" "+this.email;
	}
}

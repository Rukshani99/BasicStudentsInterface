package com.students.collection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {

	public List<Student> getStudents() throws Exception {
		System.out.println("entered method");
		List<Student> students= new ArrayList<Student>();
		Connection myConn=null;
		Statement myStmt = null;
		ResultSet myRs= null;
		try {
			System.out.println("entered try");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false", "student", "student");
			System.out.println("connected");
			myStmt= myConn.createStatement();
			String sql= "select * from student order by last_name";
			myRs = myStmt.executeQuery(sql);
			System.out.println("executed query");
			while(myRs.next()){
				int id = myRs.getInt("id");
				String firstName=myRs.getString("first_name");String lastName=myRs.getString("last_name");
				System.out.println("firstname: "+firstName);
				String email = myRs.getString("email");
				Student tempStudent= new
						Student(id,firstName,lastName,email);
				students.add(tempStudent);
			}
			return students;
		} finally{
			close(myConn,myStmt,myRs);
		}
	}


	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try{
			if(myStmt!=null)
				myStmt.close();
			if(myRs!=null)
				myRs.close();
			if(myConn!=null)
				myConn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void addStudent(Student student){
		Connection myConn=null;
		PreparedStatement myStmt = null;
		ResultSet myRs= null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false", "student", "student");
			String sql = "INSERT INTO student (first_name, last_name, email) VALUES (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			String firstName = student.getFirstName();
			String lastName = student.getLastName();
			String email = student.getEmail();
			myStmt.setString(1, firstName);
			myStmt.setString(2, lastName);
			myStmt.setString(3, email);
			myStmt.execute();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			close(myConn,myStmt,myRs);
		}
	}

	public Student fetchStudent(int id) {
		Connection myConn=null;
		Statement myStmt = null;
		ResultSet myRs= null;
		Student student=null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false", "student", "student");
			myStmt= myConn.createStatement();
			String sql= "select * from student where id="+id;
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()){
				String firstName=myRs.getString("first_name");
				String lastName=myRs.getString("last_name");
				String email = myRs.getString("email");
				student = new Student(id,firstName,lastName,email);
			}
			return student;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		} finally{
			close(myConn,myStmt,myRs);
		}
	}


	public void updateStudent(Student student) {
		Connection myConn=null;
		PreparedStatement myStmt = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false", "student", "student");
			String sql = "update student set first_name=?, last_name=?,email=? where id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, student.getFirstName());
			myStmt.setString(2, student.getLastName());
			myStmt.setString(3, student.getEmail());
			myStmt.setInt(4,student.getId());
			myStmt.execute();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			close(myConn,myStmt,null);
		}
	}

	public void deleteStudent(Student student) {
		Connection myConn=null;
		PreparedStatement myStmt = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false", "student", "student");
			String sql = "delete from student where id=?  ";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1,student.getId());
			myStmt.execute();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			close(myConn,myStmt,null);
		}
	}

}

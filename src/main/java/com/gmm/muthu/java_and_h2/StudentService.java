package com.gmm.muthu.java_and_h2;

//Author : Muthu Mariyappan G

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentService {
	private static final String DRIVER = "org.h2.Driver";
	private static final String URL = "jdbc:h2:~/test";
	private static final String USER = "sa";
	private static final String PASSWORD = "";
	
	private static final String INSERTQUERY = "insert into student values(?,?,?,?)";
	private static final String UPDATEQUERY = "update student set name=?, cgpa = ?, deptid = ? where rollno = ?";
	private static final String DELETEQUERY = "delete from student where rollno = ?";
	private static final String SELECTQUERY = "select * from student where rollno = ?";
	private static final String JOINQUERY   = "select s.rollno,s.name,s.cgpa,d.name from student s join department d on s.deptid = d.id";
	
	private Connection dbConnection;
	
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement selectStatement;
	
	public StudentService(){
		this.dbConnection = getConnection();
		try {
			this.dbConnection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("Error setting Autocommit to false!");
			e.printStackTrace();
		}
		prepareStatements();
	}
	
	private static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load the database driver.");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;
		} catch (SQLException e) {
			System.out.println("Failed to connect to the database");
			e.printStackTrace();
		}
		return conn;
	}
	
	
	private void prepareStatements(){
		try{
		this.insertStatement = this.dbConnection.prepareStatement(INSERTQUERY);
		this.updateStatement = this.dbConnection.prepareStatement(UPDATEQUERY);
		this.deleteStatement = this.dbConnection.prepareStatement(DELETEQUERY);
		this.selectStatement = this.dbConnection.prepareStatement(SELECTQUERY);
		}
		catch(SQLException e){
			System.out.println("Error preparing statements!");
			e.printStackTrace();
		}
	}
	
	
	public boolean insertRecord(Student student){
		
		try{
			this.insertStatement.setInt(1, student.getRollno());
			this.insertStatement.setString(2, student.getName());
			this.insertStatement.setFloat(3, student.getCGPA());
			this.insertStatement.setInt(4, student.getDepartmentId());
			this.insertStatement.executeUpdate();
			this.dbConnection.commit();
		}
		catch(SQLException e){
			System.out.println("Error inserting record");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateRecord(Student student){
		
		try{
			this.updateStatement.setString(1, student.getName());
			this.updateStatement.setFloat(2, student.getCGPA());
			this.updateStatement.setInt(3, student.getDepartmentId());
			this.updateStatement.setInt(4, student.getRollno());
			this.updateStatement.executeUpdate();
			this.dbConnection.commit();
		}
		catch(SQLException e ){
			System.out.println("Error updating record");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteRecord(Student student){
		
		try{
			this.deleteStatement.setInt(1, student.getRollno());
			this.deleteStatement.executeUpdate();
			this.dbConnection.commit();
		}
		catch(SQLException e){
			System.out.println("Error deleting record");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public Student findRecord(int rollno){
		
		ResultSet fetchedRecord = null;
		Student studentRecord = null;
		
		try{
			this.selectStatement.setInt(1, rollno);
			fetchedRecord = this.selectStatement.executeQuery();
			if(!fetchedRecord.next())
				return null;
			int rno = fetchedRecord.getInt(1);
			String name = fetchedRecord.getString(2);
			float cgpa = fetchedRecord.getFloat(3);
			int deptid = fetchedRecord.getInt(4);
			studentRecord = new Student(rno,name,cgpa,deptid);
			return studentRecord;
		}
		catch(SQLException e){
			System.out.println("Error finding record");
			e.printStackTrace();
			return null;
		}
	}
	
	// Custom query methods
	
	public void printJoinedTable(){
		
		Statement stmt = null;
		ResultSet fetchedRecord = null;
		try {
			stmt = this.dbConnection.createStatement();
			fetchedRecord = stmt.executeQuery(JOINQUERY);
			while(fetchedRecord.next()){
				System.out.println("\nRollno :"+fetchedRecord.getInt(1)+" | Name : "+fetchedRecord.getString(2)+
						" | CGPA : "+fetchedRecord.getFloat(3)+" | Department : "+fetchedRecord.getString(4));
			}
		} catch (SQLException e) {
			System.out.println("Error printing joined table info");
			e.printStackTrace();
		}
		finally{
			try {
				stmt.close();
				fetchedRecord.close();
			} catch (SQLException e) {
				System.out.println("Error closing resultsets");
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	protected void finalize(){
		try{
			this.deleteStatement.close();
			this.insertStatement.close();
			this.updateStatement.close();
			this.selectStatement.close();
			this.dbConnection.close();
		}
		catch(SQLException e){
			System.out.println("Error closing connected objects!");
		}
	}
}
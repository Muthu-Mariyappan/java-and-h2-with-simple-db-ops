package com.gmm.muthu.java_and_h2;

//Author : Muthu Mariyappan G

public class Student {
	private int rollno;
	private String name;
	private float CGPA;
	private int departmentId;
	
	public Student(int rollno,String name, float cgpa, int deptid){
		this.rollno = rollno;
		this.name = name;
		this.CGPA = cgpa;
		this.departmentId = deptid;
	}
	
	public Student(){}
	
	public int getRollno(){
		return this.rollno;
	}
	
	public void setRollno(int rno){
		this.rollno = rno;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public float getCGPA(){
		return this.CGPA;
	}
	
	public void setCGPA(float cgpa){
		this.CGPA = cgpa;
	}
	
	public int getDepartmentId(){
		return this.departmentId;
	}
	
	public void setDepartmentId(int deptid){
		this.departmentId = deptid;
	}	
}
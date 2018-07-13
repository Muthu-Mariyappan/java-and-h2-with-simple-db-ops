package com.gmm.muthu.java_and_h2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//Author : Muthu Mariyappan G

import java.util.Scanner;

public class JavaH2Demo 
{
	private static StudentService dao = null;
	
    public static void main( String[] args ) throws Exception
    {
        int option = -1;
        System.out.print("\t\t\t Java and H2 Demo\n");
        Scanner sn = new Scanner(System.in);
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        dao = new StudentService();
        int rollno;
        String name;
        float cgpa;
        int deptid;
        while(option!=0){
        	System.out.print("\nOperations : 1. insert  2. update  3. delete  4. find  5. printjoinedtable \n\nEnter option : ");
        	option  = sn.nextInt();
	        switch(option)
	        {
	        	case 1://insert record 
	        		System.out.println("\n Inserting new record!");
	        		System.out.print("\nEnter rollno : ");
	        		rollno = sn.nextInt();
	        		System.out.print("Enter name : ");
	        		name = bf.readLine();
	        		
	        		System.out.print("Enter CGPA : ");
	        		cgpa = sn.nextFloat();
	        		System.out.print("Enter department id : ");
	        		deptid = sn.nextInt();
	        		if(dao.insertRecord(new Student(rollno,name,cgpa,deptid)))
	        			System.out.println("\n Insertion success!");
	        		else
	        			System.out.println("\n Failed insertion!");
	        		break;
	        	case 2:
	        		System.out.println("\n Updating new record!");
	        		System.out.print("\nEnter rollno : ");
	        		rollno = sn.nextInt();
	        		if(dao.findRecord(rollno)==null){
	        			System.out.println("No available records for the given rollno!");
	        			continue;
	        		}
	        		System.out.print("Enter name : ");
	        		name = sn.nextLine();
	        		System.out.print("Enter CGPA : ");
	        		cgpa = sn.nextFloat();
	        		System.out.print("Enter department id : ");
	        		deptid = sn.nextInt();
	        		if(dao.updateRecord(new Student(rollno,name,cgpa,deptid)))
	        			System.out.println("\n Updation success!");
	        		else
	        			System.out.println("\n Failed updation!");
	        		break;
	        	case 3:
	        		System.out.println("\n Deleting old record!");
	        		System.out.print("\nEnter rollno : ");
	        		rollno = sn.nextInt();
	        		if(dao.findRecord(rollno)==null){
	        			System.out.println("No available records for the given rollno!");
	        			continue;
	        		}
	        		if(dao.deleteRecord(dao.findRecord(rollno)))
	        			System.out.println("Deletion success!");
	        		else
	        			System.out.println("Failed deletion");
	        		break;
	        	case 4:
	        		System.out.println("\n Finding record!");
	        		System.out.print("\nEnter rollno : ");
	        		rollno = sn.nextInt();
	        		Student student = dao.findRecord(rollno);
	        		if(student==null){
	        			System.out.println("No available records for the given rollno!");
	        			continue;
	        		}
	        		System.out.println("\nRollno :"+student.getRollno()+" | Name : "+student.getName()+
							" | CGPA : "+student.getCGPA()+" | Department id : "+student.getDepartmentId());
	        		break;
	        	case 5:
	        		System.out.println("\n Printing joined table");
	        		dao.printJoinedTable();
	        		break;
	        	case 0:
	        		System.out.println("quitting...");
	        		break;
        		default:
        			System.out.println("Invalid input! try again");
	        }
        }
        sn.close();
    }
}
package com.base;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Priority;
import org.testng.annotations.Test;


//String url="jdbc:sqlserver://192.168.1.16\\1433;" + "databaseName=students;integratedSecurity=true";
	//String url="jdbc:sqlserver://DESKTOP-UDIRQKO\\SQLEXPRESS;" + "databaseName=students;integratedSecurity=true";
	//String url="jdbc:sqlserver://localhost:8080\\SQLEXPRESS;databaseName=students";
	

public class SqlDbConnection {
	
	static Statement statement;
	static Connection connection;
	static String url="jdbc:sqlserver://localhost;instance=SQLEXPRESS;" + "databaseName=students;integratedSecurity=true";
	static int id=7;
	static String fname="Manya";
	static String lname="Poreddy";
	static int marks=96;
	
	public SqlDbConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		connection=DriverManager.getConnection(url);
		System.out.println("Connected to SQL Server");
		statement=connection.createStatement();
	}
		
	@Test (priority=1, testName = "Insert Data From SQL Table")
	public static void insertData() throws ClassNotFoundException, SQLException {
		
		String query="insert into Students values (" +id+ ",'"+ fname +"','"+ lname +"',"+ marks +")";
		System.out.println(query);
	/*
	 * String query="insert into Students "
				+ "values (?,?,?,?)";  
		
		System.out.println(query);
		
		PreparedStatement pStatement=connection.prepareStatement(query);
		
		pStatement.setInt(1, id);
		pStatement.setString(2, fname);
		pStatement.setString(3, lname);
		pStatement.setInt(4, marks);
		*/
		int count=statement.executeUpdate(query);
    	System.out.println(count + "rows inserted");
	
	}
	
	
	@Test (priority =2,testName = "Extract Data From SQL Table")
	public static void extractData() throws SQLException, ClassNotFoundException {
	
   	
	String query="SELECT * FROM students where FirstName='"+fname+"'";
	
	ResultSet rs=statement.executeQuery(query);
	
	while(rs.next()) {
		 System.out.println(rs.getString("FirstName"));
		 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
	                  }
	
	statement.close();
	connection.close();
       }
	
}

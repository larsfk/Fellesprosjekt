package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	public static void main(String[] args) throws Exception{
		
		try{
			//Accessing driver from the JAR file needed to use mySQL
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e){
			System.out.println("Where is MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		
		Connection connection = null;
		
		try{
			//Creating a variable for the connection called "con"
			connection = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/larsfkl_felles","larsfkl_felles","bademadrass");
			//Username: larsfkl_felles
			//PW: bademadrass
		} catch (SQLException e){
			System.out.println("Connection failed...");
			e.printStackTrace();
			return;
		}
		
		if(connection != null){
			System.out.println("We made connection!");
		} else{
			System.out.println("Failed to connect...");
		}
		
		//Create a query
		PreparedStatement query = connection.prepareStatement("select * from person");
		
		//Creating a variable to execute query
		ResultSet result = query.executeQuery ();
		
		while(result.next()){
			System.out.println("Name: " + result.getString(1));
		}
	}
}

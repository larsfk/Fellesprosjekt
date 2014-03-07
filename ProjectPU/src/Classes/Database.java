package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	static Connection connection = null;
	static String input = "insert into larsfkl_felles.person(name,office,tlf,email,password) values ('Test','NTNU',94234135,'test@test.no','mojjo')";
	
	public static void main(String[] args) throws Exception{
		
		try{
			//Accessing driver from the JAR file needed to use mySQL
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e){
			System.out.println("Where is MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		
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
		
		if(connection == null){
			System.out.println("Failed to connect...");
		}
		addToDatabase(input);
	}
	
	public static void addToDatabase(String statement) throws SQLException{
			//Create a query
			Statement stmt = (Statement) connection.createStatement();
			//Execute query
			stmt.executeUpdate(statement);

	}
	
	public static void readDatabase(String res) throws SQLException{
		//Create a query
		PreparedStatement query = connection.prepareStatement(res);
		//Creating a variable to execute query
		ResultSet result = query.executeQuery();
			
		while(result.next()){
			System.out.println("Name: " + result.getString(1));

		}
	}
}

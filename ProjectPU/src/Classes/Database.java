package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {
	
//	String userName;
//	String password;
//	//static String input = "insert into larsfkl_felles.person(name,office,tlf,email,password) values ('Test','NTNU',94234135,'test@test.no','mojjo')";

	//	public static void main(String[] args) throws Exception{

	public Connection getConnection() throws SQLException {

	    Connection conn = null;
//	    Properties connectionProps = new Properties();
//	    connectionProps.put("larsfkl_felles", this.userName);
//	    connectionProps.put("bademadrass", this.password);
//	    connectionProps.setProperty("userName", "larsfkl_felles");
//	    connectionProps.setProperty("password", "bademadrass");
	    conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/larsfkl_felles","larsfkl_felles","bademadrass");
//	    conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/larsfkl_felles",  connectionProps);
	    System.out.println("Connected to database");
	    return conn;
	}
	

	public Connection getConnectionLars(){
		Connection conn = null;
//		connectionProps.put("user", this.userName);
//	    connectionProps.put("password", this.password);
		
		
		try{
			//Accessing driver from the JAR file needed to use mySQL
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e){
			System.out.println("Where is MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}

		try{
			//Creating a variable for the connection called "con"
			conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/larsfkl_felles","larsfkl_felles","bademadrass");
			if (conn != null){
				System.out.println("Klarte å koble til :D");
				return conn;
			}
			//Username: larsfkl_felles
			//PW: bademadrass
		} catch (SQLException e){
			System.out.println("Connection failed...");
			e.printStackTrace();
			return null;
		}

		if(conn == null){
			System.out.println("Failed to connect...");
			return null;
		}
		//addToDatabase(input);
		return null;
	}
	//	}

	public void addToDatabase(String statement, Connection conn) throws SQLException{
		//Create a query
		Statement stmt = (Statement) conn.createStatement();
		//Execute query
		stmt.executeUpdate(statement);

	}

	public static void readDatabase(String res, Connection conn) throws SQLException{
		//Create a query
		PreparedStatement query = conn.prepareStatement(res);
		//Creating a variable to execute query
		ResultSet result = query.executeQuery();

		while(result.next()){
			System.out.println("Name: " + result.getString(1));

		}
	}
}

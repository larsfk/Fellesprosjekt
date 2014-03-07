package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public Connection getConnection() throws SQLException {

	    Connection conn = null;
	    conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/larsfkl_felles","larsfkl_felles","bademadrass");
	    System.out.println("Connected to database");
	    return conn;
	}


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

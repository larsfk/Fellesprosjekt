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


	public void addPersonToDatabase(String statement, Connection conn) throws SQLException{
		//Create a query
		Statement stmt = (Statement) conn.createStatement();
		System.out.println("Statement created");
		//Execute query
		stmt.executeUpdate(statement);
		System.out.println("Update exectuted");
	}

//	public void removePersonFromDatabase(String statement, Connection conn) throws SQLException{
//		//Create a query
//		Statement stmt = (Statement) conn.createStatement();
//		//Execute query
//		stmt.executeUpdate(statement);
//		DELETE FROM table_name WHERE some_column = some_value
//
//	}

	public Person getPersonFromDatabase(String mail, Connection conn){

		try{
			Person lars;
			//Create a query
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT * FROM larsfkl_felles.person where email = '" + mail + "';");
			ResultSet rs = stmt.getResultSet();

			rs.next();
			String name = rs.getString(1);
			String office = rs.getString(2);
			String tlf = rs.getString(3);
			String email = rs.getString(4);
			String SSN = rs.getString(5);
			String password = rs.getString(6);
			lars = new Person(name, office, tlf, email, SSN, password);
			rs.close ();

			return lars;
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		}
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

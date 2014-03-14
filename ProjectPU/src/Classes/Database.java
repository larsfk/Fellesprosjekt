package Classes;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import org.omg.PortableServer.IdAssignmentPolicy;

public class Database {

	public Connection getConnection() throws SQLException {

		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/larsfkl_felles","larsfkl_felles","bademadrass");
		System.out.println("Connected to database");
		return conn;
	}

	//Legger til eller endrer person/appointment (Brukes i Konstrukt¿r og setters)
	public void addToDatabase(String statement, Connection conn) throws SQLException{
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

	public Appointment getAppointment(int ID, Connection conn){

		Appointment appointment;

		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery(  "SELECT * FROM larsfkl_felles.appointment " +
								"WHERE appointment_id = " + ID + ";");
			ResultSet rs = stmt.getResultSet();

			Calendar startTime = Calendar.getInstance();
			Calendar finishTime = Calendar.getInstance();
			Calendar SQLTime = Calendar.getInstance();

			rs.next();

			String stime = rs.getString(2);
			SQLTime = convertSQLTimeToCalendarTime(stime);
			startTime.set(startTime.HOUR_OF_DAY, SQLTime.get(Calendar.HOUR_OF_DAY));
			startTime.set(startTime.MINUTE, SQLTime.get(Calendar.MINUTE));
			startTime.set(startTime.SECOND, SQLTime.get(Calendar.SECOND));

			String ftime = rs.getString(3);
			SQLTime = convertSQLTimeToCalendarTime(ftime);
			finishTime.set(finishTime.HOUR_OF_DAY, SQLTime.get(Calendar.HOUR_OF_DAY));
			finishTime.set(finishTime.MINUTE, SQLTime.get(Calendar.MINUTE));
			finishTime.set(finishTime.SECOND, SQLTime.get(Calendar.SECOND));

			String date = rs.getString(4);
			SQLTime = convertSQLDateToCalendarDate(date);
			startTime.set(startTime.DATE, SQLTime.get(Calendar.DATE));
			startTime.set(startTime.MONTH, SQLTime.get(Calendar.MONTH));
			startTime.set(startTime.YEAR, SQLTime.get(Calendar.YEAR));
			finishTime.set(finishTime.DATE, SQLTime.get(Calendar.DATE));
			finishTime.set(finishTime.MONTH, SQLTime.get(Calendar.MONTH));
			finishTime.set(finishTime.YEAR, SQLTime.get(Calendar.YEAR));

			String descr = rs.getString(5);
			String meetplace = rs.getString(6);
			//		Person owner = rs.(10);
			String email = rs.getString(10);
			Person owner = getPersonFromDatabase(email, conn);

			Alarm alarm = null;
			appointment = new Appointment(startTime, finishTime, meetplace, descr, alarm, owner);
			rs.close ();

			return appointment;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Appointment> getAppointmentList(String email, Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery(  "SELECT appointment_id " +
								"FROM larsfkl_felles.appointment " +
								"WHERE owner = '" + email + "';");
			ResultSet rs = stmt.getResultSet();
			ArrayList<Appointment> appList = new ArrayList<Appointment>();
			ArrayList<Integer> IDList = new ArrayList<Integer>();

			int i = 0;
			while (rs.next()){
				//				System.out.println(rs.getString(1));
				IDList.add(i, Integer.parseInt(rs.getString(1)));
				i++;
			}

			for (int j = 0; j < IDList.size(); j++){
				//				System.out.println(getAppointment(IDList.get(j), conn).getMeetingplace());
				appList.add(getAppointment(IDList.get(j), conn));
			}

			return appList;
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public Calendar convertSQLTimeToCalendarTime(String sTime){
		Calendar time = Calendar.getInstance();

		String[] sToTime = sTime.split(":");
		Integer i = Integer.parseInt(sToTime[0]);
		Integer j = Integer.parseInt(sToTime[1]);
		Integer k = Integer.parseInt(sToTime[2]);
		time.set(Calendar.HOUR_OF_DAY, i);
		time.set(Calendar.MINUTE, j);
		time.set(Calendar.SECOND, k);

		return time;
	}

	public Calendar convertSQLDateToCalendarDate(String sDate){
		Calendar date = Calendar.getInstance();

		String[] sToDate = sDate.split("-");
		Integer i = Integer.parseInt(sToDate[0]);
		Integer j = Integer.parseInt(sToDate[1]);
		Integer k = Integer.parseInt(sToDate[2]);
		i = i - 1900;
		date.set(Calendar.YEAR, i);
		date.set(Calendar.MONTH, j);
		date.set(Calendar.DATE, k);

		return date;
	}

	public void removePerson(String mail, Connection conn){
		try{
			//Create a query
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate( "DELETE FROM larsfkl_felles.person " +
								"WHERE email = '" + mail + "';");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void removeAppointment(int ID, Connection conn){
		try{
			//Create a query
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate( "DELETE FROM larsfkl_felles.appointment " +
								"WHERE appointment_id = " + ID + ";");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void joinAppointment(Person pers, Appointment app, Connection conn){
		try{
			//Create a query
			Statement stmt = (Statement) conn.createStatement();
			System.out.println("Statement created");
			//Execute query
			stmt.executeUpdate( "INSERT into larsfkl_felles.personToGroup (email, groupID) " +
								"SELECT email, group_id " +
								"FROM larsfkl_felles.person full join larsfkl_felles.group " +
								"WHERE email = '" + pers.getEmail() + "' and group_id = "  + " " + ";");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void createGroup(Integer ID, Connection conn){
		try{
		Statement stmt = (Statement) conn.createStatement();
		stmt.executeUpdate("INSERT INTO larsfkl_felles.group (group_id) VALUES ('" + ID + "');");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void joinGroup(Person pers, Integer groupID, Connection conn){
		try{
		//Create a query
		Statement stmt = (Statement) conn.createStatement();
		System.out.println("Statement created");
		//Execute query
		stmt.executeUpdate( "INSERT into larsfkl_felles.personToGroup (email, groupID) " +
							"SELECT email, group_id " +
							"FROM larsfkl_felles.person full join larsfkl_felles.group " +
							"WHERE email = '" + pers.getEmail() + "' and group_id = " + groupID + ";");
		}
		
		catch (SQLException e){
			e.printStackTrace();
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

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

public class Database {

	public Connection getConnection() throws SQLException {

		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/larsfkl_felles","larsfkl_felles","bademadrass");
//		System.out.println("Connected to database");
		return conn;
	}

	//Legger til eller endrer person/appointment (Brukes i Konstrukt¿r og setters)
	public void addToDatabase(String statement, Connection conn) throws SQLException{
		//Create a query
		Statement stmt = (Statement) conn.createStatement();
//		System.out.println("Statement created");
		//Execute query
		stmt.executeUpdate(statement);
//		System.out.println("Update exectuted");
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
	
	public Integer generateAppointmentID(Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery( "SELECT max(appointment_id) + 1 from larsfkl_felles.appointment;");
			ResultSet rs = stmt.getResultSet();
			rs.next();
			return Integer.parseInt(rs.getString(1));
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer generateAlarmID(Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery( "SELECT max(alarm_id) + 1 from larsfkl_felles.alarm;");
			ResultSet rs = stmt.getResultSet();
			rs.next();
			return Integer.parseInt(rs.getString(1));
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer generateGroupID(Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery( "SELECT max(group_id) + 1 from larsfkl_felles.group;");
			ResultSet rs = stmt.getResultSet();
			rs.next();
			return Integer.parseInt(rs.getString(1));
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
			appointment = new Appointment(ID, startTime, finishTime, meetplace, descr, alarm, owner);
			rs.close ();

			return appointment;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Appointment> getAllAppointments(Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT * FROM larsfkl_felles.appointment;");
			ResultSet rs = stmt.getResultSet();
			ArrayList<Appointment> out = new ArrayList<Appointment>();
			Appointment app;
			int i = 0;
			Alarm al = null;
			while (rs.next()){
				app = new Appointment(Integer.parseInt(rs.getString(1)), 
						convertSQLTimeToCalendarTime(rs.getString(2)), 
						convertSQLTimeToCalendarTime(rs.getString(3)), 
						rs.getString(6), rs.getString(5), al, 
						getPersonFromDatabase(rs.getString(10), conn));
				out.add(app);
			}
			return out;
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Appointment> getAppointmentList(Person pers, Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery(  "SELECT appointment_id " +
								"FROM larsfkl_felles.appointment " +
								"WHERE owner = '" + pers.getEmail() + "';");
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
		date.set(Calendar.YEAR, i);
		date.set(Calendar.MONTH, j);
		date.set(Calendar.DATE, k);

		return date;
	}
	
	public String convertCalendarDateToSQLDate(Calendar cal){
		String date = "" + cal.get(Calendar.DATE);
		String month = "" + cal.get(Calendar.MONTH);
		String year = "" + (cal.get(Calendar.YEAR));
		return year + "-" + month + "-" + date;
	}
	
	public String convertCalendarDateToCasualDate(Calendar cal){
		String date = "" + cal.get(Calendar.DATE);
		String month = "" + cal.get(Calendar.MONTH);
		String year = "" + (cal.get(Calendar.YEAR));
		return date + "/" + month + "-" + year;
	}
	
	public Calendar createCalendarFromSQLTimeAndDate(String time, String date){
		Calendar out = Calendar.getInstance();
		Calendar t = convertSQLTimeToCalendarTime(time);
		Calendar d = convertSQLDateToCalendarDate(date);
		out.set(Calendar.HOUR_OF_DAY, t.get(Calendar.HOUR_OF_DAY));
		out.set(Calendar.MINUTE, t.get(Calendar.MINUTE));
		out.set(Calendar.DATE, d.get(Calendar.DATE));
		out.set(Calendar.MONTH, d.get(Calendar.MONTH));
		out.set(Calendar.YEAR, d.get(Calendar.YEAR));
		
		return out;
	}
	
	public String convertCalendarTimeToSQLTime(Calendar time){
		
		Integer t = time.get(Calendar.HOUR_OF_DAY);
		Integer m = time.get(Calendar.MINUTE);
		String T = t.toString();
		String M = m.toString();
		return (T + ":" + M);
		
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
			Statement stmt = (Statement) conn.createStatement();
			Integer id = app.getAppointmentID();
			ResultSet rs = stmt.executeQuery(  "SELECT appointment_id FROM larsfkl_felles.appointmentToPerson " +
								"WHERE email_id = '" + pers.getEmail() + "';");
			ArrayList<Integer> idList = new ArrayList<Integer>();
			while (rs.next()){
				idList.add(Integer.parseInt(rs.getString(1)));
			}

			boolean bool = true;
			
			for (int i = 0; i < idList.size(); i++){
				if (idList.get(i) == id){
					bool = false;
				}
			}
			
			if (bool == true){
				stmt.executeUpdate( "Insert into larsfkl_felles.appointmentToPerson (appointment_id, email_id, status_1, hidden, alarm_id)" +
									"values(" + app.getAppointmentID() + ", '" + pers.getEmail() + "', 1, 0, null);");
			}
			else{System.out.println(pers.getName() + " is already signed up for that appointment.");}
		}
		catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException f){
			System.out.println("error, men fint");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void abandonAppointment(int ID, Person pers, Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate( "DELETE FROM larsfkl_felles.appointmentToPerson WHERE appointment_id = " + ID + " AND email_id = '" + pers.getEmail() + "';");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public Integer createGroup(Connection conn){
		try{
		Statement stmt = (Statement) conn.createStatement();
		Integer ID = generateGroupID(conn);
		stmt.executeUpdate("INSERT INTO larsfkl_felles.group (group_id) VALUES ('" + ID + "');");
		return ID;
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void getGroup(Connection conn){
		
	}

	public void joinGroup(Person pers, Integer groupID, Connection conn){
		try{
		Statement stmt = (Statement) conn.createStatement();
		stmt.executeUpdate( "INSERT into larsfkl_felles.personToGroup (email, groupID) " +
							"SELECT email, group_id " +
							"FROM larsfkl_felles.person full join larsfkl_felles.group " +
							"WHERE email = '" + pers.getEmail() + "' and group_id = "  + groupID + ";");
		}
		
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void abandonGroup(int ID, Person pers, Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate( "DELETE FROM larsfkl_felles.personToGroup " +
								"WHERE groupID = " + ID + " AND email = '" + pers.getEmail() + "';");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void removeGroup(int ID, Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate( "DELETE FROM larsfkl_felles.group " +
								"WHERE group_id = " + ID + ";");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public MeetingRoom getMeetingRoom(int ID, Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery(  "SELECT * FROM larsfkl_felles.meeting_room " +
					"WHERE room_id = " + ID + ";");
			ResultSet rs = stmt.getResultSet();
			rs.next();
			String Capasity = rs.getString(2);
			MeetingRoom rom = new MeetingRoom(ID, Integer.parseInt(Capasity));
			return rom;
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Person> getPersonList(Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery(  "SELECT * " +
								"FROM larsfkl_felles.person;");
			ResultSet rs = stmt.getResultSet();
			ArrayList<Person> persons = new ArrayList<Person>();

			while (rs.next()){
				Person person = new Person(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				persons.add(person);
			}
			return persons;
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Appointment> getListOfAppointmentsInMeetingroom(MeetingRoom rom, Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery(  "SELECT * FROM larsfkl_felles.appointment WHERE room_id = " + rom.getID() + ";");
			ResultSet rs = stmt.getResultSet();
			ArrayList<Appointment> appList = new ArrayList<Appointment>();

			while (rs.next()){
				Alarm al = null;
				Calendar stime;
				Calendar ftime;
				stime = createCalendarFromSQLTimeAndDate(rs.getString(2), rs.getString(4));
				ftime = createCalendarFromSQLTimeAndDate(rs.getString(3), rs.getString(4));
				
				Appointment app = new Appointment(Integer.parseInt(rs.getString(1)), stime, ftime, rs.getString(6), rs.getString(5), al, 
						getPersonFromDatabase(rs.getString(10), conn));
				appList.add(app);
			}
			return appList;
			
			//SELECT * FROM larsfkl_felles.appointment
			//WHERE larsfkl_felles.appointment.room_id = rom.getID;
			
			
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void removeMeetingRoom(MeetingRoom rom, Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate( "DELETE FROM larsfkl_felles.meeting_room " +
								"WHERE room_id = " + rom.getID() + ";");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public String isHidden(Appointment app, Person pers, Connection conn){
		Integer ID = app.getAppointmentID();
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT hidden FROM larsfkl_felles.appointmentToPerson " +
					"WHERE appointment_id = " + ID + " AND email_id = '" + pers.getEmail() + "';");
			ResultSet rs = stmt.getResultSet();
			rs.next();
			if (rs.getString(1).equals("1")){
				return "Hidden";
			}
			else if (rs.getString(1).equals("0")){
				return "Unhidden";
			}
			else {System.out.println("Error i isHidden, hidden ikke lik 1/0"); return null;}
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void changeHidden(Appointment app, Person pers, Connection conn){
		Integer ID = app.getAppointmentID();
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT hidden FROM larsfkl_felles.appointmentToPerson " +
					"WHERE appointment_id = " + ID + " AND email_id = '" + pers.getEmail() + "';");
			ResultSet rs = stmt.getResultSet();
			rs.next();
			Integer bool = Integer.parseInt(rs.getString(1));
			if (bool == 1){
				stmt.executeUpdate("UPDATE larsfkl_felles.appointmentToPerson SET hidden = 0 " +
						"WHERE appointment_id = " + ID + " AND email_id = '" + pers.getEmail() + "';");
			}
			else if (bool == 0){
				stmt.executeUpdate("UPDATE larsfkl_felles.appointmentToPerson SET hidden = 1 " +
						"WHERE appointment_id = " + ID + " AND email_id = '" + pers.getEmail() + "';");
			}
			else {System.out.println("Error i changeHidden, hidden ikke lik 1/0");}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void changeStatus(Appointment app, Person pers, Connection conn){
		Integer ID = app.getAppointmentID();
		try{
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT status_1 FROM larsfkl_felles.appointmentToPerson " +
					"WHERE appointment_id = " + ID + " AND email_id = '" + pers.getEmail() + "';");
			ResultSet rs = stmt.getResultSet();
			rs.next();
			Integer bool = Integer.parseInt(rs.getString(1));
			if (bool == 1){
				stmt.executeUpdate("UPDATE larsfkl_felles.appointmentToPerson SET status_1 = 0 " +
						"WHERE appointment_id = " + ID + " AND email_id = '" + pers.getEmail() + "';");
			}
			else if (bool == 0){
				stmt.executeUpdate("UPDATE larsfkl_felles.appointmentToPerson SET status_1 = 1 " +
						"WHERE appointment_id = " + ID + " AND email_id = '" + pers.getEmail() + "';");
			}
			else {System.out.println("Error i changeStatus, hidden ikke lik 1/0");}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
}

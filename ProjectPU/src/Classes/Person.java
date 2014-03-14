package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class Person {
	private String name;
	private String office;
	private String tlf;
	private String SSN; 
	private String email; 
	private String password;
	private ArrayList<AppointmentToPerson> appointmentList = new ArrayList<AppointmentToPerson>();
	Database db = new Database();
	
	public Person(String name, String office, String tlf, String email, String SSN, String password){
		this.name = name;
		this.office = office;
		this.SSN = SSN;
		this.email = email;
		this.password = password;
		this.tlf = tlf;
	}
	
	public Person(String name, String office, String tlf, String email, String SSN, String password, Connection conn){
		try {
			db.addToDatabase("insert into larsfkl_felles.person(name,office,tlf,email,SSN,password) values ('" + name + "','" + office + "','" + tlf + "','" + email + "','" + SSN + "','" + password + "');", conn);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		this.name = name;
		this.office = office;
		this.SSN = SSN;
		this.email = email;
		this.password = password;
		this.tlf = tlf;
	}

	public String getName() {
		try {
			Connection conn = db.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT name FROM larsfkl_felles.person where email = '" + email + "';");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			String name = rs.getString(1);
			rs.close();
			
			return name;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getOffice() {
		try {
			Connection conn = db.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT office FROM larsfkl_felles.person where email = '" + email + "';");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			String office = rs.getString(1);
			rs.close();
			
			return office;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getSSN() {
		try {
			Connection conn = db.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT SSN FROM larsfkl_felles.person where email = '" + email + "';");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			String SSN = rs.getString(1);
			rs.close();
			
			return SSN;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getEmail() {
		try {
			Connection conn = db.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT email FROM larsfkl_felles.person where email = '" + email + "';");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			String email = rs.getString(1);
			rs.close();
			
			return email;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getPassword() {
		try {
			Connection conn = db.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT password FROM larsfkl_felles.person where email = '" + email + "';");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			String password = rs.getString(1);
			rs.close();
			
			return password;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getTlf() {
		try {
			Connection conn = db.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT tlf FROM larsfkl_felles.person where email = '" + email + "';");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			String tlf = rs.getString(1);
			rs.close();
			
			return tlf;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setName(String name) {
		try {
			Connection conn = db.getConnection();
			db.addToDatabase("update larsfkl_felles.person SET name = '"+ name + "' WHERE email = '" + this.email + "';", conn);
			this.name = name;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void setOffice(String office) {
		try {
			Connection conn = db.getConnection();
			db.addToDatabase("update larsfkl_felles.person SET office = '"+ office + "' WHERE email = '" + this.email + "';", conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.office = office;
	}
	
	public void setEmail(String email) {
		try {
			Connection conn = db.getConnection();
			db.addToDatabase("update larsfkl_felles.person SET email = '"+ email + "' WHERE email = '" + this.email + "';", conn);
			this.email = email;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPassword(String password) {
		try {
			Connection conn = db.getConnection();
			db.addToDatabase("update larsfkl_felles.person SET password = '"+ password + "' WHERE email = '" + this.email + "';", conn);
			this.password = password;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setTlf(String tlf) {
		try {
			Connection conn = db.getConnection();
			db.addToDatabase("update larsfkl_felles.person SET tlf = '"+ tlf + "' WHERE email = '" + this.email + "';", conn);
			this.tlf = tlf;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void makeAppointment(Calendar stime, Calendar ftime, String meetpl, String descr, Alarm alarm){
		new Appointment(stime, ftime, meetpl, descr, alarm, this);
	}
	
	public boolean canChangeAppointment(Appointment appoint){
		if (appoint.getOwner() == this){
			return true;
		}
		return false;
	}
	public void hideAppointment(Appointment appoint){ //Ta inn appointment
		for (int i = 0;i<appointmentList.size();i++){
			if (appointmentList.get(i).getAppointment() == appoint){
				appointmentList.get(i).setHidden(true);
				break;
			}			
		}
	}
	public void unHideAppointment(Appointment appoint){
		for (int i = 0;i<appointmentList.size();i++){
			if (appointmentList.get(i).getAppointment() == appoint){
				appointmentList.get(i).setHidden(false);
				break;
			}			
		}
	}
	public void addatp(AppointmentToPerson atp){
		appointmentList.add(atp);
	}
	
}

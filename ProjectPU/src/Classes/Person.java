package Classes;

import java.sql.Connection;
import java.sql.SQLException;

public class Person {
	private String name;
	private String office;
	private String tlf;
	private String SSN; 
	private String email; 
	private String password;
	private final PUCalendar calendar;
	Database db = new Database();
	
	public Person(String name, String office, String tlf, String email, String SSN, String password){
		this.name = name;
		this.office = office;
		this.SSN = SSN;
		this.calendar = new PUCalendar(this);
		this.email = email;
		this.password = password;
		this.tlf = tlf;
		try {
			Connection conn = db.getConnection();
			db.addPersonToDatabase("insert into larsfkl_felles.person(name,office,tlf,email,SSN,password) values ('" + name + "','" + office + "','" + tlf + "','" + email + "','" + SSN + "','" + password + "');", conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setAppointment(Appointment appoint){
		calendar.addAppointment(appoint);
	}

	public String getName() {
		return name;
	}

	public String getOffice() {
		return office;
	}
	
	public PUCalendar getPUCalendar(){
		return calendar;
	}

	public String getSSN() {
		return SSN;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getTlf() {
		return tlf;
	}
	
	public void setName(String name) {
		try {
			Connection conn = db.getConnection();
			db.addPersonToDatabase("update larsfkl_felles.person SET office = '"+ name + "' WHERE email = '" + this.email + "';", conn);
			this.name = name;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setOffice(String office) {
		try {
			Connection conn = db.getConnection();
			db.addPersonToDatabase("update larsfkl_felles.person SET office = '"+ office + "' WHERE email = '" + this.email + "';", conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.office = office;
	}
	
	public void setEmail(String email) {
		try {
			Connection conn = db.getConnection();
			db.addPersonToDatabase("update larsfkl_felles.person SET office = '"+ email + "' WHERE email = '" + this.email + "';", conn);
			this.email = email;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPassword(String password) {
		try {
			Connection conn = db.getConnection();
			db.addPersonToDatabase("update larsfkl_felles.person SET office = '"+ password + "' WHERE email = '" + this.email + "';", conn);
			this.password = password;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setTlf(String tlf) {
		try {
			Connection conn = db.getConnection();
			db.addPersonToDatabase("update larsfkl_felles.person SET office = '"+ tlf + "' WHERE email = '" + this.email + "';", conn);
			this.tlf = tlf;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}

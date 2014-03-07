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
	
	public Person(String name, String office, String SSN, String password, String tlf, String email){
		this.name = name;
		this.office = office;
		this.SSN = SSN;
		this.calendar = new PUCalendar(this);
		this.email = email;
		this.password = password;
		this.tlf = tlf;
		try {
			Connection conn = db.getConnection();
			db.addPersonToDatabase("insert into larsfkl_felles.person(name,office,tlf,email,password) values ('" + name + "','" + office + "','" + tlf + "','" + email + "','" + password + "');", conn);
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

	public void setName(String name) {
		this.name = name;
	}

	public String getOffice() {
		return office;
	}
	
	public PUCalendar getPUCalendar(){
		return calendar;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getSSN() {
		return SSN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	
	
	
	
}

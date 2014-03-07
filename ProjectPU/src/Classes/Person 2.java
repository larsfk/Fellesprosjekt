package Classes;

import java.sql.SQLException;
import java.util.Date;

public class Person {
	private String name;
	private String office;
	private int tlf;
	private int SSN; 
	private String email; 
	private String password;
	private final Calendar calendar;
	
	public Person(String name, String office, int SSN, String password, int tlf, String email){
		this.name = name;
		this.office = office;
		this.SSN = SSN;
		this.calendar = new Calendar(email);
		this.email = email;
		this.password = password;
		this.tlf = tlf;
		try {
			Database.addToDatabase("INSERT INTO `larsfkl_felles`.`person` (`name`, `office`, `tlf`, `email`, `password`) VALUES (" + name + ", " + office + "," + tlf + "," + email + "," + password + ");");
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
	
	public Calendar getCalendar(){
		return calendar;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public int getSSN() {
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

	public int getTlf() {
		return tlf;
	}

	public void setTlf(int tlf) {
		this.tlf = tlf;
	}
	
	
	
	
}

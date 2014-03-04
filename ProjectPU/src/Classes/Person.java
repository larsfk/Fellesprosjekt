package Classes;

import java.sql.Date;

public class Person {
	private String name;
	private String adress;
	private int SSN; 
	private String email; 
	Calendar calendar = new Calendar();

	public Person(String name, String adress, int SSN, Calendar calendar, String email){
		this.name = name;
		this.adress = adress;
		this.SSN = SSN;
		this.calendar = calendar;
		this.email = email;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
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
}

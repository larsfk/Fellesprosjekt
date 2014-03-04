package Classes;

import java.sql.Date;

public class Person {
	String name;
	String adress;
	int SSN;
	Calendar calendar; //La den til siden jeg trenger denne i calendarClient
	String email; //Denne ogsaa
//	Appointment appointment = new Appointment(appID, stime, ftime, dur, meetpl, descr, alarm);
	
	public Person(String name, String adress, int SSN, Calendar calendar, String email){
		this.name = name;
		this.adress = adress;
		this.SSN = SSN;
		this.calendar = calendar;
		this.email = email;
	}

	public void setAppointment(int appID, Date stime, Date ftime, ){
		
	}
}

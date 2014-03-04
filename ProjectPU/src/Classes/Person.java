package Classes;

public class Person {
	String name;
	String adress;
	int SSN;
	Calendar calendar; //La den til siden jeg trenger denne i calendarClient
	String email; //Denne ogsaa
	
	public Person(String name, String adress, int SSN, Calendar calendar, String email){
		this.name = name;
		this.adress = adress;
		this.SSN = SSN;
		this.calendar = calendar;
		this.email = email;
	}

}

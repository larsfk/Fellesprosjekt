package Classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class MainProgram {
	private String welcome = "Hello!\n";
	private ArrayList<Person> validPersons;

	private  void run() {
		//Kun testing fra her til.....
		Person rebecca = new Person("Rebecca", "Home", "3423422", "rebcox@gmail.com","080674555","heisann");
		CalendarClient cc = new CalendarClient(rebecca);		

		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();
		Alarm alarm1 = new Alarm(2013,03,18,12,30,"Kom!!");

		date1.set(date1.HOUR_OF_DAY,10);
		date2.set(date2.HOUR_OF_DAY, 11);

		Appointment appoint1; 
		appoint1 = new Appointment(date1,date2,"Hos Cox","Progging",alarm1,rebecca);
		//....her

		validPersons = new ArrayList<Person>();
		/*
		 * 
		 * 
		 * Fyll validPersons med alle tilgjengelige navn i databasen
		 * 
		 * 
		 */

		System.out.println(welcome);
		Scanner sc = new Scanner(System.in);

		int personID = -1; 
		while (personID < 0 || personID > validPersons.size()){ //Til vi faar et gyldig nr
			System.out.println("Who are you? Type number");
			for (int i = 0;i<validPersons.size();i++){
				System.out.println("(" + i + ") " + validPersons.get(i).getName());
			}
			personID = sc.nextInt();
		}
		String password = ""; //Hente dette fra databasen
		String typedPassword = ""; //kanskje "null"?
		while (password.compareTo(typedPassword) != 0){
			System.out.println("Type password");
			typedPassword = sc.next();
		}

		System.out.println("What would you like to do?\n1. Add appointment\n2. Delete appointment\n3.Edit appointment\n4. Show this calendar\n5. Show several calendars");
		int option = sc.nextInt();
		
		switch (option){
		case 1:
			System.out.println("Type starttime (yyyy.mm.dd.hh.mm)");
			String time = sc.next();
			//Burde sjekke om input er gyldig verdi om vi faar tid
			String[] timeList = time.split("."); //splitter input paa punktum
			Integer[] timeListInt = new Integer[5];
			for (int i = 0;i<5;i++){
				timeListInt[i] = Integer.parseInt(timeList[i]);
			}
			Calendar start = Calendar.getInstance();
			start.clear();
			start.set(timeListInt[0], timeListInt[1], timeListInt[2], timeListInt[3], timeListInt[4]);

			System.out.println("Type finishime (yyyy.mm.dd.hh.mm)");
			time = sc.next();
			timeList = time.split("."); //splitter input paa punktum
			for (int i = 0;i<5;i++){
				timeListInt[i] = Integer.parseInt(timeList[i]);
			}
			Calendar finish = Calendar.getInstance();
			finish.clear();
			finish.set(timeListInt[0], timeListInt[1], timeListInt[2], timeListInt[3], timeListInt[4]);

			//OSV

			//Appointment ap = new Appointment(startTime, ); //etc

		case 2:
			int numApp = cc.getAppointmentList().size(); //Number of appointments in the active calendar
			int appID = -1;
			while (appID < 0){ // ||appID > numApp
				System.out.println("Which appointment would you like to delete?");
				for (int i = 0;i<numApp;i++){ 
					System.out.println("(" + i + ") " + cc.getAppointmentList().get(i)); //returns element i in calendar appointments
				}
				appID = sc.nextInt();
			}
			cc.deleteAppointment(cc.getAppointmentList().get(appID));
		case 3:
			//Edit appointment
		case 4:
			//Skrive ut kalender, TONY
			//System.out.println(cc.showMyWeekCalendar());
		case 5:
			//Skrive ut flere kalendere, TONY
			//System.out.println(cc.showGroupCalendar());
		default:
			System.out.println("What would you like to do?\n1. Add appointment\n2. Delete appointment\n3.Edit appointment\n4. Show this calendar\n5. Show several calendars");
		}
	}

	public static void main(String[] args) {
		MainProgram MP = new MainProgram();
		MP.run();
	}

}

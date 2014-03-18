package Classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class MainProgram {
	private String welcome = "Hello!\n";
	private ArrayList<Person> validPersons;
	Database db = new Database();

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
		appoint1 = new Appointment(100,date1,date2,"Hos Cox","Progging",alarm1,rebecca);
		//Calendar stime, Calendar ftime, String meetpl, String descr, Alarm alarm, Person owner, Connection conn){
		
		//....her

		validPersons = new ArrayList<Person>();
		try {
			Connection conn = db.getConnection();
			validPersons = db.getPersonList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(welcome);
		Scanner sc = new Scanner(System.in);

		int personID = -1; 
		Person person = null;
		while (personID < 0 || personID > validPersons.size()){ //Til vi faar et gyldig nr
			System.out.println("Choose a username: ");
			for (int i = 0;i<validPersons.size();i++){
				System.out.println("(" + i + ") " + validPersons.get(i).getName());
			}
			personID = sc.nextInt();
			person = validPersons.get(personID);
		}
		String password = person.getPassword(); //Hente dette fra databasen
		String typedPassword = ""; //kanskje "null"?
		while (password.compareTo(typedPassword) != 0){
			System.out.println("Type password");
			typedPassword = sc.next();
			if(!typedPassword.equals(password)){
				System.out.println("Wrong password!");
			}
		}
		
		while(true){
			System.out.println("What would you like to do?\n1. Add appointment\n2. Delete appointment\n3. Edit appointment\n4. Show this calendar\n5. Show several calendars");
			int option = sc.nextInt();
			
			switch (option){
			case 1:
				System.out.println("Type starttime (yyyy:mm:dd:hh:mm)");
				String time = sc.next();
				System.out.println(time);
				//Burde sjekke om input er gyldig verdi om vi faar tid
				String[] timeList = time.split(":"); //splitter input paa punktum
				Integer[] timeListInt = new Integer[5];
				for (int i = 0; i < 5; i++){
					timeListInt[i] = Integer.parseInt(timeList[i]);
				}
				Calendar start = Calendar.getInstance();
				start.clear();
				start.set(timeListInt[0]-1900, timeListInt[1], timeListInt[2], timeListInt[3], timeListInt[4]);

				System.out.println("Type finishime (yyyy:mm:dd:hh:mm)");
				time = sc.next();
				timeList = time.split(":"); //splitter input paa punktum
				for (int i = 0;i<5;i++){
					timeListInt[i] = Integer.parseInt(timeList[i]);
				}
				Calendar finish = Calendar.getInstance();
				finish.clear();
				finish.set(timeListInt[0]-1900, timeListInt[1], timeListInt[2], timeListInt[3], timeListInt[4]);
				
				sc.nextLine();
				
				System.out.println("Type meeting place");
				String meetpl = sc.nextLine();
				
				System.out.println("Type description");
				String description = sc.nextLine();

				System.out.println("Type alarm time (yyyy:mm:dd:hh:mm:decription)");
				time = sc.next();
				timeList = time.split(":"); //splitter input paa punktum
				for (int i = 0;i<5;i++){
					timeListInt[i] = Integer.parseInt(timeList[i]);
				}
				
				System.out.println("Choose available room:");
				//getAvailableMeetingRooms();
				int room = sc.nextInt();
				
				
				Alarm alarm;
				Connection conn;
				try {
					conn = db.getConnection();
					alarm = new Alarm(timeListInt[0]-1900, timeListInt[1], timeListInt[2], timeListInt[3], timeListInt[4],timeList[5],conn);
					Appointment ap = new Appointment(start,finish,meetpl,description,alarm,person,conn);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 2:
				int numApp = cc.getAppointmentList(person).size(); //Number of appointments in the active calendar
				int appID = -1;
				while (appID < 0){ // ||appID > numApp
					System.out.println("Which appointment would you like to delete?");
					for (int i = 0;i<numApp;i++){ 
						System.out.println("(" + i + ") " + cc.getAppointmentList(person).get(i)); //returns element i in calendar appointments
					}
					appID = sc.nextInt();
				}

				try {
					conn = db.getConnection();
					cc.deleteAppointment(cc.getAppointmentList(person).get(appID),conn);
					System.out.println("Appointment deleted.");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				//Edit appointment
				break;
			case 4:
				//Skrive ut kalender, TONY
				//System.out.println(cc.showMyWeekCalendar());
				break;
			case 5:
				//Skrive ut flere kalendere, TONY
				//System.out.println(cc.showGroupCalendar());
				break;
			default:
				System.out.println("What would you like to do?\n1. Add appointment\n2. Delete appointment\n3.Edit appointment\n4. Show this calendar\n5. Show several calendars");
				break;
			}
		}

		}
		
	public static void main(String[] args) {
		MainProgram MP = new MainProgram();
		MP.run();
	}

}

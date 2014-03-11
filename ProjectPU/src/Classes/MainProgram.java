package Classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MainProgram {
	private String welcome = "Hello!\n"; //ID er index i kalenderlisten
	
	//Burde CalendarClient inneholde alle kalendere? I så fall skal denne listen fylles med disse:
	
	// CalendarClient cc; //= new CalendarClient();
	
	private  void run() {
		CalendarClient cc = new CalendarClient();		

		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();
		Calendar date3 = Calendar.getInstance();
		Date thisDate = new Date();
		
		date1.set(thisDate.getYear(), thisDate.getMonth(), thisDate.getDate(), 14, 00);
		date2.set(thisDate.getYear(), thisDate.getMonth(), thisDate.getDate(), 16, 00);
		
		Person rebecca = new Person("Rebecca", "Home", "3423422", "rebcox@gmail.com","080674555","heisann");
		
		Appointment appoint1; 
		appoint1 = new Appointment(1,date1,date2,"Hos Cox","Progging",date3,rebecca);		
		
		
		System.out.println(welcome);
		Scanner sc = new Scanner(System.in);
		//String input = sc.nextLine();
		//while (input.compareTo("x")!=0){ //denne gjelder kun den ytteste løkka. problem om vi skal ha det med
		
//		int personID = -1; 
//		while (personID < 0 || personID > calendars.size()){
//			System.out.println("Who are you? Type number");
//			for (int i = 0;i<calendars.size();i++){
//				System.out.println("(" + i + ") " + calendars.get(i).getUserEmail()); //email is the identificator
//			}
//			personID = sc.nextInt();
//		}
		//Sammenligne passord med database
		//activeCalendar = calendars.get(personID);
		//cc.setPerson()

		System.out.println("What would you like to do?\n1. Add appointment\n2. Delete appointment\n3. Show this calendar\n4. Show several calendars");
		int option = sc.nextInt();
		if (option==1){
			
			System.out.println("Type startTime");
			String startTime = sc.nextLine(); //evt dele opp input til en liste
			
			//Appointment ap = new Appointment(startTime); //etc
			//ELLER
			//Add appointment(startTime) bør kanskje være boolean?

		}
		else if (option == 2){
			int numApp = activeCalendar.getAppointments().size(); //Number of appointments in the active calendar
			int appID = -1;
			while (appID < 0){ // ||appID > numApp
				System.out.println("Which appointment would you like to delete?");
				for (int i = 0;i<numApp;i++){ 
					System.out.println("(" + i + ") " + activeCalendar.getAppointments().get(i)); //returns element i in calendar appointments
				}
				appID = sc.nextInt();
			}
			activeCalendar.deleteAppointment(activeCalendar.getAppointments().get(appID));
		}
		else if (option == 3){
			//Skrive ut kalender, TONY
			//System.out.println(cc.showMyCalendar());
		}
		else if (option == 4){
			//Skrive ut flere kalendere, TONY
			//System.out.println(cc.showGroupCalendar());
		}
		else{
			System.out.println("What would you like to do?\n1. Add appointment\n2. Delete appointment\n3. Show this calendar\n4. Show several calendars");
		}

	}

	public static void main(String[] args) {
		MainProgram MP = new MainProgram();
		MP.run();
	}

}

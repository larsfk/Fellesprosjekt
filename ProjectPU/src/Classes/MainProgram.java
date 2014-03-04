package Classes;

import java.util.Scanner;

public class MainProgram {
	private String options = "Hello!\n"; //ID er index i kalenderlisten
	
	//Burde CalendarClient inneholde alle kalendere? I så fall skal denne listen fylles med disse:
	//ArrayList<Calendar> calendars;
	//private Calendar activeCalendar;

	private  void run() {
		System.out.println(options);
		Scanner sc = new Scanner(System.in);
		//String input = sc.nextLine();
		//while (input.compareTo("x")!=0){ //denne gjelder kun den ytteste løkka. problem

		int calendarID = -1;
		while (calendarID < 0){ // || calendarID > calendars.size()
			System.out.println("Which calendar are you in? Type ID");
			// for (int i = 0;i<calendars.size();i++){
				//System.out.println("(" + i + ") " calendars.get(i));
			//}
			calendarID = sc.nextInt();
		}
		//activeCalendar = calendars.get(calendarID);

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
			//int numApp = activeCalendar.getAppointmentsSize();
			//for (int i = 0;i<numApp;i++){ //Antall avtaler i kalenderen
				//System.out.println("(" + i + ") " activeCalendar.getAppointments(i)); //getAppointments skal returnere element i
			//}
			int appID = -1;
			while (appID < 0){ // ||appID > numApp
				System.out.println("Which appointment would you like to delete?");
				appID = sc.nextInt();
			}
			//activeCalendar.remove(appID);
		}
		else if (option == 3){
			//Skrive ut kalender, TONY
			CalendarClient cc = new CalendarClient();
			cc.showMyCalendar();
		}
		else if (option == 4){
			//Skrive ut flere kalendere, TONY
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

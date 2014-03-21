package Classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class MainProgram {
	private String welcome = "Hello!\n";
	private ArrayList<Person> allPersons;
	private ArrayList<Appointment> appList;
	CalendarClient cc;
	Database db = new Database();

	private  void run() {
//		//Kun testing fra her til.....
//		Person rebecca = new Person("Rebecca", "Home", "3423422", "rebcox@gmail.com","080674555","heisann");
//		CalendarClient cc = new CalendarClient(rebecca);		
//
//		Calendar date1 = Calendar.getInstance();
//		Calendar date2 = Calendar.getInstance();
//		Alarm alarm1 = new Alarm(2013,03,18,12,30,"Kom!!");
//
//		date1.set(date1.HOUR_OF_DAY,10);
//		date2.set(date2.HOUR_OF_DAY, 11);
//
//		Appointment appoint1; 
//		appoint1 = new Appointment(100,date1,date2,"Hos Cox","Progging",alarm1,rebecca);
//		//Calendar stime, Calendar ftime, String meetpl, String descr, Alarm alarm, Person owner, Connection conn){
//		
//		//....her

		allPersons = new ArrayList<Person>();
		try {
			Connection conn = db.getConnection();
			allPersons = db.getPersonList(conn);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(welcome);
		Scanner sc = new Scanner(System.in);

		int personID = -1; 
		Person chosenPerson = null;
		while (personID < 0 || personID > allPersons.size()){ //Til vi faar et gyldig nr
			System.out.println("Choose a username: ");
			try{
				Connection conn = db.getConnection();
				for (int i = 0;i<allPersons.size();i++){
					System.out.println("(" + i + ") \t" + allPersons.get(i).getName(conn) ); //+ " \t\t" + validPersons.get(i).getEmail(conn) + "\n");
				}
				conn.close();
			} catch (SQLException e){e.printStackTrace();}
			System.out.println("\nWrite -42 to quit...");
			personID = sc.nextInt();
			if(personID == -42){
				return;
			}
			chosenPerson = allPersons.get(personID);
		}
		try{
			Connection conn = db.getConnection();
			String password = chosenPerson.getPassword(conn); //Hente dette fra databasen
			String typedPassword = ""; //kanskje "null"?
			while (password.compareTo(typedPassword) != 0){
				System.out.println("Type password");
				typedPassword = sc.next();
				if(!typedPassword.equals(password)){
					System.out.println("Wrong password!");
				}
			}
			conn.close();
		} catch (SQLException e) {e.printStackTrace();}

		
		Boolean brk = true;
		while(brk){
			System.out.println("What would you like to do?\n1. Add appointment\n2. Delete appointment\n3. Edit appointment\n4. Show this calendar\n5. Show several calendars\n6. Join Appointment\n7. Hide/unhide appointment\n8. Change status\n9. Log out");
			int option = sc.nextInt();
			
			switch (option){
			case 1: //Add appointment
				System.out.println("Type starttime (yyyy:mm:dd:hh:mm)");
				String time = sc.next();
				//Burde sjekke om input er gyldig verdi om vi faar tid
				String[] timeList = time.split(":"); //splitter input paa punktum
				Integer[] timeListInt = new Integer[5];
				for (int i = 0; i < 5; i++){
					timeListInt[i] = Integer.parseInt(timeList[i]);
				}
				Calendar start = Calendar.getInstance();
				start.clear();
				start.set(timeListInt[0], timeListInt[1], timeListInt[2], timeListInt[3], timeListInt[4]);

				System.out.println("Type finishime (yyyy:mm:dd:hh:mm)");
				time = sc.next();
				timeList = time.split(":"); //splitter input paa punktum
				for (int i = 0;i<5;i++){
					timeListInt[i] = Integer.parseInt(timeList[i]);
				}
				Calendar finish = Calendar.getInstance();
				finish.clear();
				finish.set(timeListInt[0], timeListInt[1], timeListInt[2], timeListInt[3], timeListInt[4]);
				
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
				
				//System.out.println("Choose available room:");
				//getAvailableMeetingRooms();
				//int room = sc.nextInt();
				Connection conn;
				Alarm alarm;
				try {
					conn = db.getConnection();
					Integer groupID = db.createGroup(conn);
					alarm = new Alarm(timeListInt[0], timeListInt[1], timeListInt[2], timeListInt[3], timeListInt[4],timeList[5],conn);
					Appointment ap = new Appointment(start,finish,meetpl,description,alarm,chosenPerson,groupID,conn);
					db.joinGroup(chosenPerson, groupID, conn);
					db.setAlarm(ap, chosenPerson, alarm, conn);
					System.out.println("You created and joined group: " + groupID + "\n");
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException iae){
					//Kanskje blir alt utf¿rt i try slettet nŒr vi kommer hit?
					iae.printStackTrace();
				}
				break;
				
				
			case 2: //Delete appointment
				ArrayList<Appointment> appRev = new ArrayList<Appointment>();
				try {
					conn = db.getConnection();
					appRev = db.getAppointmentList(chosenPerson, conn);
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				int appID = -1;
				while (appID < 0){ // ||appID > numApp
					System.out.println("Which appointment would you like to delete?");
					for (int i = 0;i<appRev.size();i++){ 
						System.out.println("(" + i + ") " + appRev.get(i)); //returns element i in calendar appointments
					}
					appID = sc.nextInt();
				}

				try {
					conn = db.getConnection();
					db.removeAppointment(appRev.get(appID).getAppointmentID(), conn);
					System.out.println("Appointment deleted.");
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				//Edit appointment
				int chosen = -1;
				appList = new ArrayList<Appointment>();
				try {
					conn = db.getConnection();
					appList = db.getAppointmentList(chosenPerson, conn);
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Choose a appointment to edit: ");
				for (int i = 0;i<appList.size();i++){
					System.out.println("(" + i + ") " + "'" + appList.get(i).getDescription() + "'" + " @ " + appList.get(i).getMeetingplace() + "\n    " +" Start time: " + db.convertCalendarTimeToSQLTime(appList.get(i).getStarttime()) + " Finish time: " + db.convertCalendarTimeToSQLTime(appList.get(i).getFinishingtime()) + "\n    " + " Start date: " + db.convertCalendarDateToCasualDate(appList.get(i).getStarttime()) + " Finish date: " + db.convertCalendarDateToCasualDate(appList.get(i).getFinishingtime()));
				}
				System.out.println("Type -1 to go back");
				chosen = sc.nextInt();
				if(chosen == -1){
					break;
				}
				Appointment app = appList.get(chosen);					
				
				Boolean running = true;
				while(running){
					System.out.println("What would you like to edit?\n1. Edit start time\n2. Edit finishing time\n3. Edit description\n4. Edit location\n5. Edit alarm\n6. Back");
					int option_edit = sc.nextInt();
					switch(option_edit){
					case 1: //Edit start time
						try{
							Connection conn2 = db.getConnection();
							System.out.println("Type starttime (yyyy:mm:dd:hh:mm)");
							String stime = sc.next();
							//Burde sjekke om input er gyldig verdi om vi faar tid
							String[] startTimeList = stime.split(":"); //splitter input paa punktum
							Integer[] startTimeListInt = new Integer[5];
							for (int i = 0; i < 5; i++){
								startTimeListInt[i] = Integer.parseInt(startTimeList[i]);
							}
							Calendar startTime = Calendar.getInstance();
							startTime.clear();
							startTime.set(startTimeListInt[0], startTimeListInt[1], startTimeListInt[2], startTimeListInt[3], startTimeListInt[4]);
							app.setStarttime(startTime, conn2);
							conn2.close();
							break;
						} catch (SQLException e){e.printStackTrace();}
					case 2: //Edit finishing time
						try{
						Connection conn2 = db.getConnection();
						System.out.println("Type finishingtime (yyyy:mm:dd:hh:mm)");
						String ftime = sc.next();
						//Burde sjekke om input er gyldig verdi om vi faar tid
						String[] finishTimeList = ftime.split(":"); //splitter input paa punktum
						Integer[] finishTimeListInt = new Integer[5];
						for (int i = 0; i < 5; i++){
							finishTimeListInt[i] = Integer.parseInt(finishTimeList[i]);
						}
						Calendar finishingTime = Calendar.getInstance();
						finishingTime.clear();
						finishingTime.set(finishTimeListInt[0], finishTimeListInt[1], finishTimeListInt[2], finishTimeListInt[3], finishTimeListInt[4]);
						app.setFinishingtime(finishingTime, conn2);
						conn2.close();
						break;
						} catch (SQLException e){e.printStackTrace();}
					case 3: //Edit description
						try{
							Connection conn2 = db.getConnection();
							sc.nextLine();
							System.out.println("Edit description");
							String desc = sc.nextLine();
							app.setDescription(desc, conn2);
							break;
						}catch (SQLException e){e.printStackTrace();}
					case 4: //Edit location
						try{
						Connection conn2 = db.getConnection();
						sc.nextLine();
						System.out.println("Edit location");
						String loc = sc.nextLine();
						app.setMeetingplace(loc, conn2);
						conn2.close();
						break;
						} catch (SQLException e){e.printStackTrace();}
					case 5: //Edit alarm
						System.out.println("Edit alarm");
						//delete Alarm
						//add Alarm
						break;
						
					case 6: //Go back
						running = false;
					default:
						if(running == true){
							System.out.println("What would you like to edit?\n1. Edit start time\n2. Edit finishing time\n3. Edit description\n4. Edit location\n5. Edit alarm\n6. Back\n");							
						}
						break;
					}
				}
				
			case 4: //Skrive ut kalender
				CalendarClient cc = new CalendarClient(chosenPerson);
				cc.printWeekCalendar(chosenPerson);
				break;
			case 5:
				//Skrive ut flere kalendere, TONY
				//System.out.println(cc.showGroupCalendar());

				ArrayList <Person> allPersons2 = new ArrayList<Person>();
				Person showThisPersonsWeekCalendar = null;
				try {
					Connection conn2 = db.getConnection();
					allPersons2 = db.getPersonList(conn2);
					int persID = -1;
					while (persID < 0 || persID > allPersons2.size()){ //Til vi faar et gyldig nr
						System.out.println("Choose a person to show his/her week calendar: ");
						for (int i = 0;i<allPersons.size();i++){
							System.out.println("(" + i + ") \t" + allPersons.get(i).getName(conn2) ); //+ " \t\t" + validPersons.get(i).getEmail(conn) + "\n");
						}
						persID = sc.nextInt();
						showThisPersonsWeekCalendar = allPersons2.get(persID);
					}
					conn2.close();
				} catch (SQLException e){e.printStackTrace();}
				
				CalendarClient cc2 = new CalendarClient(showThisPersonsWeekCalendar);
				cc2.printWeekCalendar(showThisPersonsWeekCalendar);
				break;
				
			case 6: //Join appointment
				appList = new ArrayList<Appointment>();
				try {
					conn = db.getConnection();
					appList = db.getAllAppointments(conn);
				
					System.out.println("Choose a appointment: ");
					for (int i = 0;i<appList.size();i++){
						System.out.println("(" + i + ") " + "'" + appList.get(i).getDescription() + "'" + " @ " + appList.get(i).getMeetingplace() + " ID: " + appList.get(i).getAppointmentID() + "\n    " +" Start time: " + db.convertCalendarTimeToSQLTime(appList.get(i).getStarttime()) + " Finish time: " + db.convertCalendarTimeToSQLTime(appList.get(i).getFinishingtime()) + "\n    " + " Start date: " + db.convertCalendarDateToCasualDate(appList.get(i).getStarttime()) + " Finish date: " + db.convertCalendarDateToCasualDate(appList.get(i).getFinishingtime()) + "\n    " + " Owner: " + appList.get(i).getOwner().getName(conn));
					}
					System.out.println("Type -1 to go back");
					int appointment = sc.nextInt();
					if(appointment != -1){
						db.joinAppointment(chosenPerson, appList.get(appointment), conn);						
					}
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 7: //Hide appointment
				int hide = -1;
				appList = new ArrayList<Appointment>();
				try {
					conn = db.getConnection();
					appList = db.getAppointmentList(chosenPerson, conn);
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Choose the appointment you want to hide: ");
				for (int i = 0;i<appList.size();i++){
					try{
						conn = db.getConnection();
						System.out.println("(" + i + ") " + "'" + appList.get(i).getDescription() + "'" + " @ " + appList.get(i).getMeetingplace() + "\n    " +" Start time: " + db.convertCalendarTimeToSQLTime(appList.get(i).getStarttime()) + " Finish time: " + db.convertCalendarTimeToSQLTime(appList.get(i).getFinishingtime()) + "\n    " + " Start date: " + db.convertCalendarDateToCasualDate(appList.get(i).getStarttime()) + " Finish date: " + db.convertCalendarDateToCasualDate(appList.get(i).getFinishingtime()) + "\n     " + db.isHidden(appList.get(i),chosenPerson,conn));
						conn.close();
					} catch(SQLException e){
						e.printStackTrace();
					}
				}
				
				hide = sc.nextInt();
				System.out.println("Type -1 to go back");
				if(hide == -1){
					break;
				}
				Appointment myApp = appList.get(hide);
				
				try{
					conn = db.getConnection();
					db.changeHidden(myApp,chosenPerson,conn);
					conn.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
				
				break;
			case 8: //Change status
				int status = -1;
				appList = new ArrayList<Appointment>();
				try {
					conn = db.getConnection();
					appList = db.getAppointmentList(chosenPerson, conn);
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Choose the appointment you want to change status: ");
				for (int i = 0;i<appList.size();i++){
					try{
						conn = db.getConnection();
						System.out.println("(" + i + ") " + "'" + appList.get(i).getDescription() + "'" + " @ " + appList.get(i).getMeetingplace() + "\n    " +" Start time: " + db.convertCalendarTimeToSQLTime(appList.get(i).getStarttime()) + " Finish time: " + db.convertCalendarTimeToSQLTime(appList.get(i).getFinishingtime()) + "\n    " + " Start date: " + db.convertCalendarDateToCasualDate(appList.get(i).getStarttime()) + " Finish date: " + db.convertCalendarDateToCasualDate(appList.get(i).getFinishingtime()) + "\n     Status: " + db.getStatus(appList.get(i),chosenPerson,conn));						
						conn.close();
					} catch(SQLException e){
						e.printStackTrace();
					}
				}
				
				hide = sc.nextInt();
				System.out.println("Type -1 to go back");
				if(hide == -1){
					break;
				}
				Appointment statusApp = appList.get(hide);
				
				try{
					conn = db.getConnection();
					db.changeStatus(statusApp,chosenPerson,conn);
					conn.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
				
				break;
				
			case 9:
				brk = false;
				System.out.println("Welcome back!\n");
				MainProgram MP = new MainProgram();
				MP.run();
				break;
				
			default:
				System.out.println("What would you like to do?\n1. Add appointment\n2. Delete appointment\n3.Edit appointment\n4. Show this calendar\n5. Show several calendars\n6. Join appointment\n7. Hide/unhide appointment\n8. Change status\n9. Log out");
				break;
			}
		}

	}
		
	public static void main(String[] args) {
		MainProgram MP = new MainProgram();
		MP.run();
		System.out.println("Good bye!");
	}

}

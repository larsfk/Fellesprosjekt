package Classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;
import java.util.Date;

public class CalendarClient {

	//	Calendar calendar = new Calendar();
	Person person;
	public Calendar calendar;
	ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	ArrayList<ArrayList<String>> myCalendar = new ArrayList<ArrayList<String>>();
	ArrayList<Appointment> joinedAppointments = new ArrayList<Appointment>();
	Database db = new Database();
	Connection conn;

	public CalendarClient(Person person){
		/*
		 * 
		 * 
		 * Hente alle relasjoner (AppointmentToPerson-databasen):
		 * 
		 * - legge AppointmentToPerson tilhoerende person til i appointmentslist i person
		 * - tilsvarende i alle appointments person er i?
		 * 
		 * 
		 */
	}

	public void deleteAppointment(Appointment appoint, Connection conn){
		db.removeAppointment(appoint.getAppointmentID(), conn);
	}
	public void setPerson(Person person){
		this.person = person;

	}

	public ArrayList<Appointment> getAppointmentList(Person person){
		ArrayList<Appointment> app;
		try {
			conn = db.getConnection();
			app = db.getAppointmentList(person, conn);
			return app;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return null;
	}

	public void addPersonToCC(Person per){
		/*
		 * 
		 * DATABASE: Hent alle avtaler (paa samme maate som i konstruktoeren) til denne personen
		 * og legge de til slik:
		 *  for each...
		 *      joinedAppointments.add();
		 * 
		 */
	}

	public String showMyWeekCalendar(Person pers){ //Slaatt sammen denne med showMyCalendar


		calendar = Calendar.getInstance();
		int firstDayOfWeek = calendar.getFirstDayOfWeek() + 3;
		int week = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		int week2 = calendar.get(Calendar.WEEK_OF_YEAR);
		appointments = getAppointmentList(pers);
		
		if(week == 2)
			calendar.set(Calendar.DATE, firstDayOfWeek + 6);
		else if(week == 3)
			calendar.set(Calendar.DATE, firstDayOfWeek + 13);
		else if(week == 4)
			calendar.set(Calendar.DATE, firstDayOfWeek + 20); 
		
		firstDayOfWeek = 1;
		
		for(int i = 0; i < appointments.size(); i++){
			myCalendar.add(new ArrayList<String>());
		}
		
		for(int i = 0; i < appointments.size(); i++){
			for(int j = 0; j < 7; j++){
				myCalendar.get(i).add(j, "                    ");
			}
		}

		Connection conn;
		try {
			conn = db.getConnection();
			for(int j = 0; j < 7; j++){
//				System.out.println(dayOfWeek);
				if(firstDayOfWeek == 8){
					calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - firstDayOfWeek);
					firstDayOfWeek = 1;
				}
				
				switch(firstDayOfWeek) {
				

				case 1: {
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if((((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)) || (((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1))){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek - 1) + " ");
								}
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek) + " ");
								}
								firstDayOfWeek = 0;
							}
						}
					}
				}

				case 2: {
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if((((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)) || (((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1))){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek - 1) + " ");
								}
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek) + " ");
								}
								firstDayOfWeek = 0;
							}
						}
					}
				}

				case 3: {
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if((((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)) || (((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1))){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek - 1) + " ");
								}
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek) + " ");
								}
								firstDayOfWeek = 0;
							}
						}
					}
				}

				case 4:{
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if((((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)) || (((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1))){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek - 1) + " ");
								}
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek) + " ");
								}
								firstDayOfWeek = 0;
							}
						}
					}
				}

				case 5:{
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if((((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)) || (((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1))){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek - 1) + " ");
								}
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek) + " ");
								}
								firstDayOfWeek = 0;
							}
						}
					}
				}

				case 6:{
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if((((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)) || (((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1))){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek - 1) + " ");
								}
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek) + " ");
								}
								firstDayOfWeek = 0;
							}
						}
					}
				}

				case 7:{
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if((((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)) || (((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1))){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek - 1) + " ");
								}
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek - 1, "");
								myCalendar.get(i).set(firstDayOfWeek - 1, appointments.get(i).getDescription() + " " + appointments.get(i).getAppointmentID());
								while(myCalendar.get(i).get(firstDayOfWeek - 1).length() < 20){
									myCalendar.get(i).set(firstDayOfWeek - 1, myCalendar.get(i).get(firstDayOfWeek) + " ");
								}
								firstDayOfWeek = 0;
							}
						}
					}
				}
				} //Switch
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
				firstDayOfWeek++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		String s = "";
		for(int i = 0; i < myCalendar.size(); i++){
				s += myCalendar.get(i) + "\n";
		}
		 return "  Mon 			  Tues			   Wed 			  Thu 			  Fri 			  Sat 			  Sun \n" + s;
	}
	
	public ArrayList<ArrayList<Appointment>> getWeekCalendar(Person pers){
		ArrayList<Appointment> Mandag;
		ArrayList<Appointment> Tirsdag;
		ArrayList<Appointment> Onsdag;
		ArrayList<Appointment> Torsdag;
		ArrayList<Appointment> Fredag;
		ArrayList<Appointment> Lordag;
		ArrayList<Appointment> Sondag;
		
		Calendar mandag = Calendar.getInstance();
		Calendar tirsdag = Calendar.getInstance();
		Calendar onsdag = Calendar.getInstance();
		Calendar torsdag = Calendar.getInstance();
		Calendar fredag = Calendar.getInstance();
		Calendar lordag = Calendar.getInstance();
		Calendar sondag = Calendar.getInstance();
		Calendar lol = Calendar.getInstance();
		
		mandag.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		tirsdag.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		onsdag.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		torsdag.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		fredag.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		lordag.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		sondag.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		Integer maaned = (lol.get(Calendar.MONTH) + 1);
		Integer ma = mandag.get(Calendar.DATE);
		Integer ti = tirsdag.get(Calendar.DATE);
		Integer on = onsdag.get(Calendar.DATE);
		Integer to = torsdag.get(Calendar.DATE);
		Integer fr = fredag.get(Calendar.DATE);
		Integer lo = lordag.get(Calendar.DATE);
		Integer so = sondag.get(Calendar.DATE);
		
		mandag.set(Calendar.MONTH, maaned);
		mandag.set(Calendar.DAY_OF_MONTH, ma);
		tirsdag.set(Calendar.MONTH, maaned);
		tirsdag.set(Calendar.DAY_OF_MONTH, ti);
		onsdag.set(Calendar.MONTH, maaned);
		onsdag.set(Calendar.DAY_OF_MONTH, on);
		torsdag.set(Calendar.MONTH, maaned);
		torsdag.set(Calendar.DAY_OF_MONTH, to);
		fredag.set(Calendar.MONTH, maaned);
		fredag.set(Calendar.DAY_OF_MONTH, fr);
		lordag.set(Calendar.MONTH, maaned);
		lordag.set(Calendar.DAY_OF_MONTH, lo);
		sondag.set(Calendar.MONTH, maaned);
		sondag.set(Calendar.DAY_OF_MONTH, so);
		
		Mandag = db.getPersonsAppointmentsGivenDay(pers, mandag, conn);
		Tirsdag = db.getPersonsAppointmentsGivenDay(pers, tirsdag, conn);
		Onsdag = db.getPersonsAppointmentsGivenDay(pers, onsdag, conn);
		Torsdag = db.getPersonsAppointmentsGivenDay(pers, torsdag, conn);
		Fredag = db.getPersonsAppointmentsGivenDay(pers, fredag, conn);
		Lordag = db.getPersonsAppointmentsGivenDay(pers, lordag, conn);
		Sondag = db.getPersonsAppointmentsGivenDay(pers, sondag, conn);
		
		ArrayList<ArrayList<Appointment>> weekCalendar = new ArrayList<ArrayList<Appointment>>();
		weekCalendar.add(Mandag);
		weekCalendar.add(Tirsdag);
		weekCalendar.add(Onsdag);
		weekCalendar.add(Torsdag);
		weekCalendar.add(Fredag);
		weekCalendar.add(Lordag);
		weekCalendar.add(Sondag);
		
		return weekCalendar;
	}
	
	public void printWeekCalendar(Person pers){
		ArrayList<Appointment> Mandag;
		ArrayList<Appointment> Tirsdag;
		ArrayList<Appointment> Onsdag;
		ArrayList<Appointment> Torsdag;
		ArrayList<Appointment> Fredag;
		ArrayList<Appointment> Lordag;
		ArrayList<Appointment> Sondag;
		
		Calendar mandag = Calendar.getInstance();
		Calendar tirsdag = Calendar.getInstance();
		Calendar onsdag = Calendar.getInstance();
		Calendar torsdag = Calendar.getInstance();
		Calendar fredag = Calendar.getInstance();
		Calendar lordag = Calendar.getInstance();
		Calendar sondag = Calendar.getInstance();
		Calendar lol = Calendar.getInstance();
		
		mandag.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		tirsdag.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		onsdag.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		torsdag.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		fredag.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		lordag.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		sondag.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		Integer maaned = (lol.get(Calendar.MONTH) + 1);
		Integer ma = mandag.get(Calendar.DATE);
		Integer ti = tirsdag.get(Calendar.DATE);
		Integer on = onsdag.get(Calendar.DATE);
		Integer to = torsdag.get(Calendar.DATE);
		Integer fr = fredag.get(Calendar.DATE);
		Integer lo = lordag.get(Calendar.DATE);
		Integer so = sondag.get(Calendar.DATE);
		
		mandag.set(Calendar.MONTH, maaned);
		mandag.set(Calendar.DAY_OF_MONTH, ma);
		tirsdag.set(Calendar.MONTH, maaned);
		tirsdag.set(Calendar.DAY_OF_MONTH, ti);
		onsdag.set(Calendar.MONTH, maaned);
		onsdag.set(Calendar.DAY_OF_MONTH, on);
		torsdag.set(Calendar.MONTH, maaned);
		torsdag.set(Calendar.DAY_OF_MONTH, to);
		fredag.set(Calendar.MONTH, maaned);
		fredag.set(Calendar.DAY_OF_MONTH, fr);
		lordag.set(Calendar.MONTH, maaned);
		lordag.set(Calendar.DAY_OF_MONTH, lo);
		sondag.set(Calendar.MONTH, maaned);
		sondag.set(Calendar.DAY_OF_MONTH, so);
		
		Mandag = db.getPersonsAppointmentsGivenDay(pers, mandag, conn);
		Tirsdag = db.getPersonsAppointmentsGivenDay(pers, tirsdag, conn);
		Onsdag = db.getPersonsAppointmentsGivenDay(pers, onsdag, conn);
		Torsdag = db.getPersonsAppointmentsGivenDay(pers, torsdag, conn);
		Fredag = db.getPersonsAppointmentsGivenDay(pers, fredag, conn);
		Lordag = db.getPersonsAppointmentsGivenDay(pers, lordag, conn);
		Sondag = db.getPersonsAppointmentsGivenDay(pers, sondag, conn);
		
		ArrayList<ArrayList<Appointment>> weekCalendar = new ArrayList<ArrayList<Appointment>>();
		weekCalendar.add(Mandag);
		weekCalendar.add(Tirsdag);
		weekCalendar.add(Onsdag);
		weekCalendar.add(Torsdag);
		weekCalendar.add(Fredag);
		weekCalendar.add(Lordag);
		weekCalendar.add(Sondag);
		
		
		System.out.println("\nMandag " + db.convertCalendarDateToCasualDate(mandag) + ":");
		if (weekCalendar.get(0).size() > 0){
			for (int i = 0; i < weekCalendar.get(0).size(); i++){
				System.out.println(db.convertCalendarTimeToSQLTime(weekCalendar.get(0).get(i).getStarttime()) +
						" to " + db.convertCalendarTimeToSQLTime(weekCalendar.get(0).get(i).getFinishingtime()) +
						" ID: " + weekCalendar.get(0).get(i).getAppointmentID() + ", " + 
						weekCalendar.get(0).get(i).getDescription() + " @ " + weekCalendar.get(0).get(i).getMeetingplace());
			}
		} else System.out.println("No appointments on Monday");
		
		System.out.println("\nTirsdag " + db.convertCalendarDateToCasualDate(tirsdag) + ":");
		if (weekCalendar.get(1).size() > 0){
			for (int i = 0; i < weekCalendar.get(1).size(); i++){
				System.out.println(db.convertCalendarTimeToSQLTime(weekCalendar.get(1).get(i).getStarttime()) +
						" to " + db.convertCalendarTimeToSQLTime(weekCalendar.get(1).get(i).getFinishingtime()) +
						" ID: " + weekCalendar.get(1).get(i).getAppointmentID() + ", " + 
						weekCalendar.get(1).get(i).getDescription() + " @ " + weekCalendar.get(1).get(i).getMeetingplace());
			}
		} else System.out.println("No appointments on Tuesday");
		
		System.out.println("\nOnsdag " + db.convertCalendarDateToCasualDate(onsdag) + ":");
		if (weekCalendar.get(2).size() > 0){
			for (int i = 0; i < weekCalendar.get(2).size(); i++){
				System.out.println(db.convertCalendarTimeToSQLTime(weekCalendar.get(2).get(i).getStarttime()) +
						" to " + db.convertCalendarTimeToSQLTime(weekCalendar.get(2).get(i).getFinishingtime()) +
						" ID: " + weekCalendar.get(2).get(i).getAppointmentID() + ", " + 
						weekCalendar.get(2).get(i).getDescription() + " @ " + weekCalendar.get(2).get(i).getMeetingplace());
			}
		} else System.out.println("No appointments on Wednesday");
		
		System.out.println("\nTorsdag " + db.convertCalendarDateToCasualDate(torsdag) + ":");
		if (weekCalendar.get(3).size() > 0){
			for (int i = 0; i < weekCalendar.get(3).size(); i++){
				System.out.println(db.convertCalendarTimeToSQLTime(weekCalendar.get(3).get(i).getStarttime()) +
						" to " + db.convertCalendarTimeToSQLTime(weekCalendar.get(3).get(i).getFinishingtime()) +
						" ID: " + weekCalendar.get(3).get(i).getAppointmentID() + ", " + 
						weekCalendar.get(3).get(i).getDescription() + " @ " + weekCalendar.get(3).get(i).getMeetingplace());
			}
		} else {System.out.println("No appointments on Thursday");}
		
		System.out.println("\nFredag " + db.convertCalendarDateToCasualDate(fredag) + ":");
		if (weekCalendar.get(4).size() > 0){
			for (int i = 0; i < weekCalendar.get(4).size(); i++){
				System.out.println(db.convertCalendarTimeToSQLTime(weekCalendar.get(4).get(i).getStarttime()) +
						" to " + db.convertCalendarTimeToSQLTime(weekCalendar.get(4).get(i).getFinishingtime()) +
						" ID: " + weekCalendar.get(4).get(i).getAppointmentID() + ", " + 
						weekCalendar.get(4).get(i).getDescription() + " @ " + weekCalendar.get(4).get(i).getMeetingplace());
			}
		} else System.out.println("No appointments on Friday");
		
		System.out.println("\nSaturday " + db.convertCalendarDateToCasualDate(lordag) + ":");
		if (weekCalendar.get(5).size() > 0){
			for (int i = 0; i < weekCalendar.get(5).size(); i++){
				System.out.println(db.convertCalendarTimeToSQLTime(weekCalendar.get(5).get(i).getStarttime()) +
						" to " + db.convertCalendarTimeToSQLTime(weekCalendar.get(5).get(i).getFinishingtime()) +
						" ID: " + weekCalendar.get(5).get(i).getAppointmentID() + ", " + 
						weekCalendar.get(5).get(i).getDescription() + " @ " + weekCalendar.get(5).get(i).getMeetingplace());
			}
		} else System.out.println("No appointments on Saturday");
		
		System.out.println("\nSunday " + db.convertCalendarDateToCasualDate(sondag) + ":");
		if (weekCalendar.get(6).size() > 0){
			for (int i = 0; i < weekCalendar.get(6).size(); i++){
				System.out.println(db.convertCalendarTimeToSQLTime(weekCalendar.get(6).get(i).getStarttime()) +
						" to " + db.convertCalendarTimeToSQLTime(weekCalendar.get(6).get(i).getFinishingtime()) +
						" ID: " + weekCalendar.get(6).get(i).getAppointmentID() + ", " + 
						weekCalendar.get(6).get(i).getDescription() + " @ " + weekCalendar.get(6).get(i).getMeetingplace() + "\n\n");
			}
		} else System.out.println("No appointments on Sunday\n");
	}
	
	

	public String showGroupCalendar(){

		//		Maa ha string i Participant med mail med tilhoerende participants for aa kunne vise kalenderne til alle i denne gruppen

		//		ArrayList<Appointment> appointments = person.getPUCalendar().getAppointments();
		//		for(int i = 0; i < appointments.size(); i++){
		//			appointments.get(i).getParticipants();
		//		}


		return null;
	}
}

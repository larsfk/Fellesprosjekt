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

	public String showMyWeekCalendar(Person pers){ //Slått sammen denne med showMyCalendar


		calendar = Calendar.getInstance();
		int firstDayOfWeek = calendar.getFirstDayOfWeek() + 3;
		int week = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) + 1;
		appointments = getAppointmentList(pers);
		
		if(week == 3){
			calendar.set(Calendar.DATE, firstDayOfWeek + 7);
		} else if(week == 4){
			calendar.set(Calendar.DATE, firstDayOfWeek + 14);
		}
		
		firstDayOfWeek = 1;
		
		for(int i = 0; i < 7; i++){
			myCalendar.add(new ArrayList<String>());
		}
		
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				myCalendar.get(i).add(j, "");
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

				System.out.println(firstDayOfWeek);
				System.out.println(calendar.get(Calendar.DATE));
				
				switch(firstDayOfWeek) {
				

				case 1: {
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if(((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)||((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1)){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription() + appointments.get(i).getAppointmentID());
								appointments.remove(i);
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription());
								appointments.remove(i);
								firstDayOfWeek = 0;
							}
						}
					} calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
					break;
				}

				case 2: {
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if(((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)||((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1)){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription() + appointments.get(i).getAppointmentID());
								appointments.remove(i);
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription());
								appointments.remove(i);
								firstDayOfWeek = 0;
							}
						}
					} calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
					break;
				}

				case 3: {
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if(((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)||((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1)){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription() + appointments.get(i).getAppointmentID());
								appointments.remove(i);
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription());
								appointments.remove(i);
								firstDayOfWeek = 0;
							}
						}
					} calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
					break;
				}

				case 4:{
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if(((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)||((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1)){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription() + appointments.get(i).getAppointmentID());
								appointments.remove(i);
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription());
								appointments.remove(i);
								firstDayOfWeek = 0;
							}
						}
					} calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
					break;
				}

				case 5:{
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if(((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)||((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1)){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription() + appointments.get(i).getAppointmentID());
								appointments.remove(i);
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription());
								appointments.remove(i);
								firstDayOfWeek = 0;
							}
						}
					} calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
					break;
				}

				case 6:{
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if(((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)||((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1)){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription() + appointments.get(i).getAppointmentID());
								appointments.remove(i);
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription());
								appointments.remove(i);
								firstDayOfWeek = 0;
							}
						}
					} calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
					break;
				}

				case 7:{
					Calendar date = Calendar.getInstance();
					for(int i = 0; i < appointments.size(); i++){
						date = appointments.get(i).getDate(conn);
						if(((calendar.get(Calendar.DATE)) < 30) && ((calendar.get(Calendar.MONTH)) % 2 == 0)||((calendar.get(Calendar.DATE)) < 31) && ((calendar.get(Calendar.MONTH)) % 2 == 1)){
							if(("" + date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
//							if(date.compareTo(calendar) == 0){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription() + appointments.get(i).getAppointmentID());
								appointments.remove(i);
							}
						}
						else {
							if(("" + 1 + "." + (date.get(Calendar.MONTH) + 1) + "." + date.get(Calendar.YEAR)).equals("" + calendar.get(Calendar.DATE) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR))){
								myCalendar.get(i).set(firstDayOfWeek, appointments.get(i).getDescription());
								appointments.remove(i);
								firstDayOfWeek = 0;
							}
						}
					} calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
					break;
				}
				} //Switch
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
		 return "  Mon 		  Tues		   Wed 		  Thu 		  Fri 		  Sat 		  Sun \n" + s;
	}

	public String showGroupCalendar(){

		//		Må ha string i Participant med mail med tilhørende participants for å kunne vise kalenderne til alle i denne gruppen

		//		ArrayList<Appointment> appointments = person.getPUCalendar().getAppointments();
		//		for(int i = 0; i < appointments.size(); i++){
		//			appointments.get(i).getParticipants();
		//		}


		return null;
	}
}

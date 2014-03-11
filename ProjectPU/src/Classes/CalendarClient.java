package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;
import java.util.Date;

public class CalendarClient {

	//	Calendar calendar = new Calendar();
	Person person;
	public Calendar calendar = Calendar.getInstance();
	ArrayList<Appointment> appointments = new ArrayList<Appointment>();


	//Lage database med hvilken kalender som tilh�rer hvem!
	public CalendarClient(Person person){
		
	}
	
	public void deleteAppointment(Appointment appoint){
		/*
		 * 
		 * Slette fra databasen
		 * slette appointmentToPerson-relasjonen
		 * 
		 */
	}
	public void setPerson(Person person){
		this.person = person;

	}

	public ArrayList<PUCalendar> getcalendarList(){
		return calendarList;
	}

	public void addCalendar(PUCalendar calendar){
		calendarList.add(calendar);
	}

	//	public static void main(String[] args){
	//		CalendarClient cc = new CalendarClient();
	//		CalendarClient cc1 = new CalendarClient();
	//		
	//		PUCalendar cal1 = new PUCalendar("rebcox@gmail.com");
	//		PUCalendar cal2 = new PUCalendar("tonygj@gmail.com");
	//		PUCalendar cal3 = new PUCalendar("ida@sagdahl.com");
	//		Calendar date1 = Calendar.getInstance();
	//		Calendar date2 = Calendar.getInstance();
	//		Calendar date3 = Calendar.getInstance();
	//		Date thisDate = new Date();
	//		
	//		Appointment appoint1; 
	//		Appointment appoint2; 
	//		
	//		date1.set(thisDate.getYear(), thisDate.getMonth(), thisDate.getDate(), 14, 00);
	//		date2.set(thisDate.getYear(), thisDate.getMonth(), thisDate.getDate(), 16, 00);
	//		appoint1 = new Appointment(1,date1,date2,"Hos Cox","Progging",date3);
	//		appoint2 = new Appointment(2,date1, date2, "Hos Tony", "progging ofc", date3);
	//		
	//		cal1.addAppointment(appoint1);
	//		cal1.addAppointment(appoint2);
	//		
	//		
	//		cc1.addCalendar(cal1);
	//		cc.addCalendar(cal2);
	//		cc.addCalendar(cal3);
	//		
	//		System.out.println(cc.showMyWeekCalendar());
	//	}

	public String showMyWeekCalendar(){ //Sl�tt sammen denne med showMyCalendar

		for(int i = 0; i < calendarList.size(); i++){
			appointments.addAll(calendarList.get(i).getAppointments());			
		}

		int dayOfWeek = calendar.DAY_OF_WEEK;
		int firstDayOfWeek = calendar.getFirstDayOfWeek() + 1;

		switch(dayOfWeek) {

		case 0: {
			int count = 0;
			for(int i = 0; i< appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR)){
					myCalendar.get(firstDayOfWeek + dayOfWeek + 1).set(count, appointments.get(i));		
					count++;
				}
			}
		}

		case 1: {
			int count = 0;
			for(int i = 0; i< appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + (calendar.get(Calendar.DAY_OF_MONTH) + dayOfWeek) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR)){
					myCalendar.get(firstDayOfWeek + dayOfWeek + 1).set(count, appointments.get(i));		
					count++;
				}
			}
		}

		case 2: {
			int count = 0;
			for(int i = 0; i< appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + (calendar.get(Calendar.DAY_OF_MONTH) + dayOfWeek) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR)){
					myCalendar.get(firstDayOfWeek + dayOfWeek + 1).set(count, appointments.get(i));		
					count++;
				}
			}
		}

		case 3:{
			int count = 0;
			for(int i = 0; i< appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + (calendar.get(Calendar.DAY_OF_MONTH) + dayOfWeek) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR)){
					myCalendar.get(firstDayOfWeek + dayOfWeek + 1).set(count, appointments.get(i));		
					count++;
				}
			}
		}

		case 4:{
			int count = 0;
			for(int i = 0; i< appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + (calendar.get(Calendar.DAY_OF_MONTH) + dayOfWeek) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR)){
					myCalendar.get(firstDayOfWeek + dayOfWeek + 1).set(count, appointments.get(i));		
					count++;
				}
			}
		}

		case 5:{
			int count = 0;
			for(int i = 0; i< appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + (calendar.get(Calendar.DAY_OF_MONTH) + dayOfWeek) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR)){
					myCalendar.get(firstDayOfWeek + dayOfWeek + 1).set(count, appointments.get(i));		
					count++;
				}
			}
		}

		case 6:{
			int count = 0;
			for(int i = 0; i< appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + (calendar.get(Calendar.DAY_OF_MONTH) + dayOfWeek) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR)){
					myCalendar.get(firstDayOfWeek + dayOfWeek + 1).set(count, appointments.get(i));		
					count++;
				}
			}
		}
		}// switch
		return "  Mon   Tues   Wed   Thu   Fri   Sat   Sun \n" + myCalendar.toString();
	}

	public String showGroupCalendar(){

		//		M� ha string i Participant med mail med tilh�rende participants for � kunne vise kalenderne til alle i denne gruppen

		//		ArrayList<Appointment> appointments = person.getPUCalendar().getAppointments();
		//		for(int i = 0; i < appointments.size(); i++){
		//			appointments.get(i).getParticipants();
		//		}


		return null;
	}
}

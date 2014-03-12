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
	ArrayList<ArrayList<Appointment>> myCalendar = new ArrayList<ArrayList<Appointment>>();
	ArrayList<Appointment> joinedAppointments = new ArrayList<Appointment>();

	public CalendarClient(Person person){
		/*
		 * 
		 * 
		 * Hente alle relasjoner (AppointmentToPerson-databasen) og
		 * legge avtaler tilhoerende person til i appointments
		 * 
		 * 
		 */
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

	public ArrayList<Appointment> getAppointmentList(){
		return appointments;
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


	public String showMyWeekCalendar(){ //Slått sammen denne med showMyCalendar

		//		for(int i = 0; i < calendarList.size(); i++){
		//			appointments.add(calendarList.get(i).getAppointments());
		//		}

		int dayOfWeek = calendar.DAY_OF_WEEK;
		int firstDayOfWeek = calendar.getFirstDayOfWeek() + 1;
		appointments = person.getPersonAppointments(person.getEmail());

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

		//		Må ha string i Participant med mail med tilhørende participants for å kunne vise kalenderne til alle i denne gruppen

		//		ArrayList<Appointment> appointments = person.getPUCalendar().getAppointments();
		//		for(int i = 0; i < appointments.size(); i++){
		//			appointments.get(i).getParticipants();
		//		}


		return null;
	}
}

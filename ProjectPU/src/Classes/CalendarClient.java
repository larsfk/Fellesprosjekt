package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;

public class CalendarClient {

	//	Calendar calendar = new Calendar();
	Person person;
	private ArrayList<PUCalendar> calendarList = new ArrayList<PUCalendar>();
	private ArrayList<ArrayList<Appointment>> myCalendar = new ArrayList<ArrayList<Appointment>>();
	public Calendar calendar = Calendar.getInstance();

	//Lage database med hvilken kalender som tilhører hvem!

	public void setPerson(Person person){
		this.person = person;
	}

	public ArrayList<PUCalendar> getcalendarList(){
		return calendarList;
	}

	public void addCalendar(PUCalendar calendar){
		calendarList.add(calendar);
	}

	public String showMyWeekCalendar(){ //Slått sammen denne med showMyCalendar
		//		myCalendar.get(0).set(0, "Dag/tid");
		//		myCalendar.get(1).set(0, "Man");
		//		myCalendar.get(2).set(0, "Tirs");
		//		myCalendar.get(3).set(0, "Ons");
		//		myCalendar.get(4).set(0, "Tors");
		//		myCalendar.get(5).set(0, "Fre");
		//		myCalendar.get(6).set(0, "Lør");
		//		myCalendar.get(7).set(0, "Søn");

		if(person == null)
			return "Du er ingen person!";

		ArrayList<Appointment> appointments = person.getPUCalendar().getAppointments();
		//		Collections.sort(appointments, new dateComparator());


		int date = calendar.DATE;
		int day = calendar.DAY_OF_WEEK;
		int monday = calendar.getFirstDayOfWeek();

		switch(day){

		case 0: {
			for(int i = 0; i< appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + calendar.DAY_OF_MONTH + "." + calendar.MONTH + "." + calendar.YEAR){
					myCalendar.get(monday + day).set(i, appointments.get(i));		
				}
			}
		}

		case 1: {
			for(int i = 0; i < appointments.size(); i++){

			}
		}

		case 2:
			

		case 3:
			

		case 4:
			

		case 5:
			

		case 6:
			

		case 7:
			
			
			return myCalendar.toString();
		}


		//		myCalendar.get(i).set(j)
		//		for(int i=1; i < appointments.size(); i++){
		//			if(appointments.get(i).getDate() > 0 && appointments.get(i).getDate() < 7){
		//				
		//			}
		//		}
	}

	public String showGroupCalendar(){
		return null;
	}
}

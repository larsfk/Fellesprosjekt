package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;
import java.util.Date;

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

	public static void main(String[] args){
CalendarClient cc = new CalendarClient();
		
		PUCalendar cal1 = new PUCalendar("rebcox@gmail.com");
		PUCalendar cal2 = new PUCalendar("tonygj@gmail.com");
		PUCalendar cal3 = new PUCalendar("ida@sagdahl.com");
		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();
		Calendar date3 = Calendar.getInstance();
		Date thisDate = new Date();
		
		Appointment appoint1; 
		
		date1.set(thisDate.getYear(), thisDate.getMonth(), thisDate.getDate(), 14, 00);
		date2.set(thisDate.getYear(), thisDate.getMonth(), thisDate.getDate(), 16, 00);
		appoint1 = new Appointment(1,date1,date2,"Hos Cox","Progging",date3);
		
		cal1.addAppointment(appoint1);
		
		
		cc.addCalendar(cal1);
		cc.addCalendar(cal2);
		cc.addCalendar(cal3);
		
		System.out.println(cal1.getAppointments());
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
		int dayOfWeek = calendar.DAY_OF_WEEK;
		int firstDayOfWeek = calendar.getFirstDayOfWeek() + 1;

		switch(dayOfWeek) {

		case 0: {
			for(int i = 0; i< appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + calendar.DAY_OF_MONTH + "." + calendar.MONTH + "." + calendar.YEAR){
					myCalendar.get(firstDayOfWeek + dayOfWeek).set(i, appointments.get(i));		
				}
			}
		}

		case 1: {
			for(int i = 0; i < appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + (calendar.DAY_OF_MONTH + dayOfWeek) + "." + calendar.MONTH + "." + calendar.YEAR){
					myCalendar.get(firstDayOfWeek + dayOfWeek).set(i, appointments.get(i));		
				}
			}
		}

		case 2:
			for(int i = 0; i < appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + (calendar.DAY_OF_MONTH + dayOfWeek) + "." + calendar.MONTH + "." + calendar.YEAR){
					myCalendar.get(firstDayOfWeek + dayOfWeek).set(i, appointments.get(i));		
				}
			}

		case 3:
			for(int i = 0; i < appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + (calendar.DAY_OF_MONTH + dayOfWeek) + "." + calendar.MONTH + "." + calendar.YEAR){
					myCalendar.get(firstDayOfWeek + dayOfWeek).set(i, appointments.get(i));		
				}
			}

		case 4:
			for(int i = 0; i < appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + (calendar.DAY_OF_MONTH + dayOfWeek) + "." + calendar.MONTH + "." + calendar.YEAR){
					myCalendar.get(firstDayOfWeek + dayOfWeek).set(i, appointments.get(i));		
				}
			}

		case 5:
			for(int i = 0; i < appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + (calendar.DAY_OF_MONTH + dayOfWeek) + "." + calendar.MONTH + "." + calendar.YEAR){
					myCalendar.get(firstDayOfWeek + dayOfWeek).set(i, appointments.get(i));		
				}
			}

		case 6:
			for(int i = 0; i < appointments.size(); i++){
				if(appointments.get(i).getDate() == "" + (calendar.DAY_OF_MONTH + dayOfWeek) + "." + calendar.MONTH + "." + calendar.YEAR){
					myCalendar.get(firstDayOfWeek + dayOfWeek).set(i, appointments.get(i));		
				}
			}
		}
		return myCalendar.toString();
	}

	public String showGroupCalendar(){
		return null;
	}
}

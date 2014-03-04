package Classes;

import java.util.ArrayList;
import java.util.Date;

public class CalendarClient {

	Calendar calendar = new Calendar();
	Person person;
	private ArrayList<Calendar> calendarList = new ArrayList<Calendar>();
	private ArrayList<ArrayList<String>> myCalendar = new ArrayList<ArrayList<String>>();
	public Date date = new Date();
	public int today = date.getDate();
	public int day = date.getDay();
	public int month = date.getMonth();

	//Lage database med hvilken kalender som tilhører hvem!

	//	public static void main(String[] args) {
	//		System.out.println();
	//	}


	public ArrayList<Calendar> getcalendarList(){
		return calendarList;
	}

	public void addCalendar(Calendar calendar){
		calendarList.add(calendar);
	}

	public String showMyWeekCalendar(){ //Slått sammen denne med showMyCalendar
		myCalendar.get(0).set(0, "Dag/tid");
		myCalendar.get(1).set(0, "Man");
		myCalendar.get(2).set(0, "Tirs");
		myCalendar.get(3).set(0, "Ons");
		myCalendar.get(4).set(0, "Tors");
		myCalendar.get(5).set(0, "Fre");
		myCalendar.get(6).set(0, "Lør");
		myCalendar.get(7).set(0, "Søn");

		ArrayList<Appointment> appointments = calendar.getAppointments();

		//		myCalendar.get(i).set(j)
		for(int i=1; i < appointments.size(); i++){
			if(appointments.get(i).getDate().getDay() > 1){
				
			}
		}
	}

	public String showGroupCalendar(){
		return null;
	}
}

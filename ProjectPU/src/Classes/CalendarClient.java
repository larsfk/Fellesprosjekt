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

	//Lage database med hvilken kalender som tilh¿rer hvem!

//	public static void main(String[] args) {
//		System.out.println();
//	}


	public ArrayList<Calendar> getcalendarList(){
		return calendarList;
	}
	
	public void addCalendar(Calendar calendar){
		calendarList.add(calendar);
	}

	public String showMyWeekCalendar(){ //SlŒtt sammen denne med showMyCalendar
		if(day > 0 && day < 8){
//			myCalendar.get(i).set(j)
			for(int i=0; i<)
		}
	}

	public String showGroupCalendar(){
		return null;
	}
}

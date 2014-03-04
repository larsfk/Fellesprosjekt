package Classes;

import java.util.ArrayList;
import java.util.Date;

public class CalendarClient {

	Calendar calendar = new Calendar();
	Person person;
	private ArrayList<Calendar> ar = new ArrayList<Calendar>();
	public Date date = new Date();
	public int today = date.getDate();
	public int day = date.getDay();
	public int month = date.getMonth();

	//Lage database med hvilken kalender som tilhører hvem!

//	public static void main(String[] args) {
//		System.out.println();
//	}


	public ArrayList<Calendar> getAr(){
		return ar;
	}

	public String showMyWeekCalendar(){ //Slått sammen denne med showMyCalendar
		
	}

	public String showGroupCalendar(){
		return null;
	}
}

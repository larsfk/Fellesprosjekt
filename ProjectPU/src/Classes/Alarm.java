package Classes;

import java.util.Calendar;

public class Alarm {
	
	private String description;
	private Calendar start;
	
	public Alarm(int year, int month, int date, int hourOfDay, int minute, String description){
		this.start = Calendar.getInstance();
		this.setStart(year, month, date, hourOfDay, minute);
		this.description = description;
		
	}
	
	public Alarm(Appointment app, int start, String description){
		this.start = Calendar.getInstance();
		System.out.println("HEI");
		this.start.add(app.getStarttime().MINUTE, -start);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getStart() {
		return start;
	}

	public void setStart(int year, int month, int date, int hourOfDay, int minute) {
		//this.start.set(year, month, date, hourOfDay, minute);
		start.set(start.YEAR, year);
		start.set(start.MONTH, month);
		start.set(start.DAY_OF_MONTH, date);
		start.set(start.HOUR_OF_DAY, hourOfDay);
		start.set(start.MINUTE, minute);
	}
	
	@Override
	public String toString() {
		return start.YEAR+"."+start.MONTH+"."+start.DAY_OF_MONTH+"."+start.HOUR_OF_DAY+"."+start.MINUTE;
	}
	
}

package Classes;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.Calendar;


public class Alarm {
	
	private String description;
	private Calendar start;
	Database db = new Database();
	
	public Alarm(int year, int month, int date, int hourOfDay, int minute, String description){
		this.start = Calendar.getInstance();
		this.setStart(year, month, date, hourOfDay, minute);
		this.description = description;
		
	}
	
	public Alarm(Appointment app, int start, String description){
		this.start = Calendar.getInstance();
		this.start.add(app.getStarttime().MINUTE, -start);
		this.description = description;
	}

	public Alarm(int year, int month, int date, int hourOfDay, int minute, String description, Connection conn){
		this.start = Calendar.getInstance();
		this.setStart(year, month, date, hourOfDay, minute);
		this.description = description;
		
		try {
			Integer Syear = this.add1900toCalendarYear(this.start.get(Calendar.YEAR));
			Integer Smonth = this.start.get(Calendar.MONTH);
			Integer Sday = this.start.get(Calendar.DATE);
			Integer Shour = this.start.get(Calendar.HOUR_OF_DAY);
			Integer Sminute = this.start.get(Calendar.MINUTE);

			
//			System.out.println("Saar " + Syear + " Sh " + Shour);
//			System.out.println("Faar " + Fyear + " Fh " + Fhour);
			
			db.addToDatabase(   "insert into larsfkl_felles.alarm(alarm_id,time,date,type) " +
					"values (" + db.generateAlarmID(conn) + ", '" 
					+ Shour + ":" + Sminute + "','" 
					+ Syear + "-" + Smonth + "-" + Sday + "','" 
					+ getDescription() + "');", conn);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Alarm(Appointment app, int start, String description, Connection conn){
		this.start = Calendar.getInstance();
		this.start.add(app.getStarttime().MINUTE, -start);
		this.description = description;
		
		try {
			db.addToDatabase( "insert into larsfkl_felles.alarm(time,type,alarm_id) values ('" + getStart() + "','" + getDescription() + "','" + db.generateAlarmID(conn) + "');", conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public static int add1900toCalendarYear(Integer year){
		return year + 1900;
	}
	
	@Override
	public String toString() {
		return start.YEAR+"."+start.MONTH+"."+start.DAY_OF_MONTH+"."+start.HOUR_OF_DAY+"."+start.MINUTE;
	}
	
}

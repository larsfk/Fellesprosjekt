package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.ArrayList;


public class Appointment {
	private int appointmentID = -1;
	private Calendar starttime;
	private Calendar finishingtime;
	private long duration = 0;
	private String meetingplace = null;
	private String description = null;
	
	private Alarm alarm;
	private Person owner;
	private ArrayList<Person> participants = new ArrayList<Person>();
	private ArrayList<AppointmentToPerson> participantConnect = new ArrayList<AppointmentToPerson>();
	Database db = new Database();

	public Appointment(int AppID, Calendar stime, Calendar ftime, String meetpl, String descr, Alarm alarm, Person owner){
		if (stime.after(ftime)){
			throw new IllegalArgumentException("Starttime cannot be after finishtime");
		}
		/*
		 * 
		 * DATABASE: IF appID finnes throw new IllegalArgumentException
		 * 
		 */
		this.appointmentID = AppID;
		this.owner = owner;
		this.starttime = stime;
		this.finishingtime = ftime;
		this.meetingplace = meetpl;
		this.description = descr;
		System.out.println("Calculating duration for " + this.appointmentID + ", " + this.getOwner().getEmail() + "   " + this.description);
		this.duration = calculateDuration(starttime, finishingtime);
		this.alarm = alarm;
	}
	public Appointment(int AppID, Calendar stime, int dur, String meetpl, String descr, Alarm alarm, Person owner){
		this.appointmentID = AppID;
		this.owner = owner;
		this.starttime = stime;
		this.duration = dur;
		this.meetingplace = meetpl;
		this.description = descr;
		this.duration = calculateDuration(starttime, finishingtime);
		this.alarm = alarm;
	}
	
	
	public Appointment(Calendar stime, Calendar ftime, String meetpl, String descr, Alarm alarm, Person owner, Connection conn){
		if (stime.after(ftime)){
			throw new IllegalArgumentException("Starttime cannot be after finishtime");
		}
		this.owner = owner;
		this.starttime = stime;
		this.finishingtime = ftime;
		this.meetingplace = meetpl;
		this.description = descr;
//		System.out.println("Calculating duration for " + this.appointmentID + ", " + this.getOwner().getEmail() + "   " + this.description);
		this.duration = calculateDuration(starttime, finishingtime);
		this.alarm = alarm;
		
		try {
			Integer Syear = this.add1900toCalendarYear(this.starttime.get(Calendar.YEAR));
			Integer Smonth = this.starttime.get(Calendar.MONTH);
			Integer Sday = this.starttime.get(Calendar.DATE);
			Integer Shour = this.starttime.get(Calendar.HOUR_OF_DAY);
			Integer Sminute = this.starttime.get(Calendar.MINUTE);
			
			Integer Fyear = this.add1900toCalendarYear(this.finishingtime.get(Calendar.YEAR));
			Integer Fmonth = this.finishingtime.get(Calendar.MONTH);
			Integer Fday = this.finishingtime.get(Calendar.DATE);
			Integer Fhour = this.finishingtime.get(Calendar.HOUR_OF_DAY);
			Integer Fminute = this.finishingtime.get(Calendar.MINUTE);
			
//			System.out.println("Saar " + Syear + " Sh " + Shour);
//			System.out.println("Faar " + Fyear + " Fh " + Fhour);
			
			db.addToDatabase(   "insert into larsfkl_felles.appointment(appointment_id,start,end,date,description,location,duration,room_id,group_id,owner) " +
								"values (" + db.generateAppointmentID(conn) + ", '" 
								+ Shour + ":" + Sminute + "','" 
								+ Fhour + ":" + Fminute + "','" 
								+ Syear + "-" + Smonth + "-" + Sday + "','" 
								+ descr + "','" 
								+ meetpl +  "','" 
								+ this.duration +"','"  + "1" + "','" + "0" + "','" 
								+ getOwner().getEmail() + "');", conn);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Appointment(Calendar stime, int dur, String meetpl, String descr, Alarm alarm, Person owner, Connection conn){
		this.owner = owner;
		this.starttime = stime;
		this.duration = dur;
		this.meetingplace = meetpl;
		this.description = descr;
		this.alarm = alarm;
		try {
			Integer Syear = this.add1900toCalendarYear(this.starttime.get(Calendar.YEAR));
			Integer Smonth = this.starttime.get(Calendar.MONTH);
			Integer Sday = this.starttime.get(Calendar.DATE);
			Integer Shour = this.starttime.get(Calendar.HOUR_OF_DAY);
			Integer Sminute = this.starttime.get(Calendar.MINUTE);
			
			Integer Fyear = this.add1900toCalendarYear(this.finishingtime.get(Calendar.YEAR));
			Integer Fmonth = this.finishingtime.get(Calendar.MONTH);
			Integer Fday = this.finishingtime.get(Calendar.DATE);
			Integer Fhour = this.finishingtime.get(Calendar.HOUR_OF_DAY);
			Integer Fminute = this.finishingtime.get(Calendar.MINUTE);
			Integer key = db.generateAppointmentID(conn);
//			System.out.println("Saar " + Syear + " Sh " + Shour);
//			System.out.println("Faar " + Fyear + " Fh " + Fhour);
			
			db.addToDatabase(   "Insert into larsfkl_felles.appointment(appointment_id,start,end,date,description,location,duration,room_id,group_id,owner) " +
								"values (" + key + ", '" 
								+ Shour + ":" + Sminute + "','" 
								+ Fhour + ":" + Fminute + "','" 
								+ Syear + "-" + Smonth + "-" + Sday + "','" 
								+ descr + "','" 
								+ meetpl +  "','" 
								+ this.duration +"','"  + "1" + "','" + "51" + "','" 
								+ getOwner().getEmail() + "');", conn);
			db.addToDatabase(   "Insert into appointmentToPerson (appointment_id, email_id, status_1, hidden, alarm_id)" + 
								"values (" + key + ", '" + getOwner().getEmail() + "', 1, 0, null);", conn);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addParticipant(Person par){
			participants.add(par); //overfl�dig?
			AppointmentToPerson atp = new AppointmentToPerson(par,this);
			participantConnect.add(atp);
			
			/*
			 * 
			 * DATABASE
			 * 
			 * 
			 */
	}
	public void removeParticipant(Person par){
		if (participants.contains(par)){ //Overfloedig
			participants.remove(par);
		}
		for (int i = 0;i<participantConnect.size();i++){
			if (participantConnect.get(i).getUser().equals(par)){
				participantConnect.remove(i);
				break;
			}
		}
		
		/*
		 * 
		 * DATABASE
		 * 
		 * 
		 */
		System.out.println("hei");
	}
	
	public ArrayList<Person> getParticipants(){
		return participants;
	}
	
	public int getAppointmentID(){
		return appointmentID;
	}
	
	public String getDate(){
		return starttime.DAY_OF_MONTH+"."+ (starttime.MONTH)+"."+ (starttime.YEAR); // getYear()+1900);
	}
	
	public Calendar getStarttime(){
		return starttime;
	}
	
	public Calendar getFinishingtime(){
		return finishingtime;
	}
	
	public long getDuration(){
		return duration;
	}
	
	public String getMeetingplace(){
		return meetingplace;
	}
	
	public Alarm getAlarm(){
		return alarm;
	}
	
	public String getDescription(){
		return description;
	}
	
	public Person getOwner(){
		return owner;
	}
	
	public void setStarttime(Calendar stime){
		
		try {
			Connection conn = db.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT * FROM larsfkl_felles.appointment WHERE appointment_id = " + this.appointmentID + ";");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			
			String end = rs.getString(3);
			Calendar endCal = Calendar.getInstance();
			endCal = db.convertSQLTimeToCalendarTime(end);
			
			long dur = calculateDuration(stime, endCal);
			String durstring = "" + dur;
			
			if((endCal.get(Calendar.HOUR_OF_DAY) > stime.get(Calendar.HOUR_OF_DAY)) || ((endCal.get(Calendar.HOUR_OF_DAY) == stime.get(Calendar.HOUR_OF_DAY)) && (endCal.get(Calendar.MINUTE) > stime.get(Calendar.MINUTE)))){
				db.addToDatabase("update larsfkl_felles.appointment SET start = '" + db.convertCalendarTimeToSQLTime(stime) +"' WHERE appointment_id = " + this.appointmentID + ";", conn);	
				db.addToDatabase("update larsfkl_felles.appointment SET duration = " + dur + " WHERE appointment_id = " + this.appointmentID + ";", conn);
			}
			else
				System.out.println("Det er ikke mulig � sette start-tid til � v�re f�r slutt-tid.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.starttime = Calendar.getInstance();
		this.starttime.set(this.starttime.HOUR_OF_DAY, stime.get(Calendar.HOUR_OF_DAY));
//		System.out.println("Starttime = " + starttime.get(Calendar.HOUR_OF_DAY) + " stime (parameter) = " + stime.get(Calendar.HOUR_OF_DAY));
		this.starttime.set(this.starttime.MINUTE, stime.get(Calendar.MINUTE));
		this.starttime.set(this.starttime.DATE, stime.get(Calendar.DATE));
		this.starttime.set(this.starttime.MONTH, stime.get(Calendar.MONTH));
		this.starttime.set(this.starttime.YEAR, stime.get(Calendar.YEAR));
//		System.out.println("startime.year = " + starttime.get(Calendar.YEAR));
	}
	
	public void setFinishingtime(Calendar ftime){

		try {
			Connection conn = db.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT * FROM larsfkl_felles.appointment WHERE appointment_id = " + this.appointmentID + ";");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			
			String start = rs.getString(2);
			Calendar startCal = Calendar.getInstance();
			startCal = db.convertSQLTimeToCalendarTime(start);
			String calendar = db.convertCalendarTimeToSQLTime(ftime);
			
			long dur = calculateDuration(startCal, ftime);
			String durstring = "" + dur;
			
			if((startCal.get(Calendar.HOUR_OF_DAY) < ftime.get(Calendar.HOUR_OF_DAY)) || ((startCal.get(Calendar.HOUR_OF_DAY) == ftime.get(Calendar.HOUR_OF_DAY)) && (startCal.get(Calendar.MINUTE) < ftime.get(Calendar.MINUTE)))){
				db.addToDatabase("update larsfkl_felles.appointment SET end = '" + calendar +"' WHERE appointment_id = " + this.appointmentID + ";", conn);	
				db.addToDatabase("update larsfkl_felles.appointment SET duration = " + dur + " WHERE appointment_id = " + this.appointmentID + ";", conn);
			}
			else
				System.out.println("Det er ikke mulig � sette slutt-tid til � v�re f�r start-tid.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.finishingtime = Calendar.getInstance();
		this.finishingtime.set(this.finishingtime.HOUR_OF_DAY, ftime.get(Calendar.HOUR_OF_DAY));
		this.finishingtime.set(this.finishingtime.MINUTE, ftime.get(Calendar.MINUTE));
		this.finishingtime.set(this.finishingtime.DATE, ftime.get(Calendar.DATE));
		this.finishingtime.set(this.finishingtime.MONTH, ftime.get(Calendar.MONTH));
		this.finishingtime.set(this.finishingtime.YEAR, ftime.get(Calendar.YEAR));
		
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public void setFinishingTime(int dur){
		if (dur > 0){
			duration = dur;
			// final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
			//feiling if-setning pga casting
			int t = (int) (starttime.getTimeInMillis()/(60000*60));
			Integer ftime = starttime.get(Calendar.HOUR_OF_DAY);
			Integer fminute = starttime.get(Calendar.MINUTE);
			Integer totaltid = (ftime * 60) + fminute + dur;
			
			Double start = totaltid.doubleValue();
			Double i = start % 1440;
//			System.out.println(i);
			Double j = i/60;
			Double floorTime = Math.floor(j);
			Double floorMinute = Math.floor((j - floorTime) * 60);
			
			Integer Time = floorTime.intValue();
			Integer Minute = floorMinute.intValue();
					
			finishingtime = Calendar.getInstance();
			finishingtime.set(finishingtime.HOUR_OF_DAY, Time);
			finishingtime.set(finishingtime.MINUTE, Minute);
		}
	}
	
	public long calculateDuration(Calendar stime, Calendar ftime) { 
		//stime.getInstance();
		//ftime.getInstance();
//		long milsecs1= stime.getTimeInMillis();
//		long milsecs2 = ftime.getTimeInMillis();
//		long duration = (milsecs2-milsecs1)/(60 * 1000);
		System.out.print("stime = " + stime.get(Calendar.HOUR_OF_DAY) + ":" + stime.get(Calendar.MINUTE) + "   " + stime.get(Calendar.DATE) 
				+ "/" + stime.get(Calendar.MONTH) + "-" + stime.get(Calendar.YEAR));
		System.out.print(" .. ftime = " + ftime.get(Calendar.HOUR_OF_DAY) + ":" + ftime.get(Calendar.MINUTE) + "   " + ftime.get(Calendar.DATE) 
				+ "/" + ftime.get(Calendar.MONTH) + "-" + ftime.get(Calendar.YEAR));
		long dur = (ftime.getTimeInMillis()-stime.getTimeInMillis())/60000;
		System.out.println(" ..  Duration: " + dur);
		return  dur; //i minutt
	}

	
	public void setMeetingplace(String meetpl){
		meetingplace = meetpl;
	}
	
	public void setAlarm(Alarm al){
		alarm = al;
	}
	
	public void setDescription(String descr){
		description = descr; 
	}
	
	public void setIsGoing(AppointmentToPerson atp, boolean isGoing){ //?
		atp.setIsGoing(isGoing);
	}
	
	public void setRoom(MeetingRoom rom, Connection conn){
		try {
			db.addToDatabase("update larsfkl_felles.appointment SET room_id = "+ rom.getID() 
					+ " WHERE appointment_id = " + this.appointmentID + ";", conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int add1900toCalendarYear(Integer year){
		return year + 1900;
	}
	
	public static Integer subtract1900fromSQLYear(Integer YEAR){
		return YEAR - 1900;
	}
	
	@Override
	public String toString(){
		return "AppointmentID: " + appointmentID + ", Calendar: " + getDate();
	}
	
}

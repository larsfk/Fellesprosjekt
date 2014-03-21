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
			try{
			Connection conn = db.getConnection();
			System.out.println(AppID + " " + owner.getEmail(conn) + descr + " start: " + db.convertCalendarTimeToSQLTime(stime) + " end: " + db.convertCalendarTimeToSQLTime(ftime));
			conn.close();
			throw new IllegalArgumentException("Starttime cannot be after finishtime");
			} catch (SQLException e){ e.printStackTrace();}
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
//		System.out.println("Calculating duration for " + this.appointmentID + ", " + this.getOwner().getEmail() + "   " + this.description);
		this.duration = calculateDuration(starttime, finishingtime);
		this.alarm = alarm;
//		System.out.println("done");
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
	
	
	public Appointment(Calendar stime, Calendar ftime, String meetpl, String descr, Alarm alarm, Person owner, Integer groupID, Connection conn){
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
		Integer key = db.generateAppointmentID(conn);
		this.appointmentID = key;
		
		try {
			Integer Syear = this.starttime.get(Calendar.YEAR);
			Integer Smonth = this.starttime.get(Calendar.MONTH);
			Integer Sday = this.starttime.get(Calendar.DATE);
			Integer Shour = this.starttime.get(Calendar.HOUR_OF_DAY);
			Integer Sminute = this.starttime.get(Calendar.MINUTE);
			
			Integer Fyear = this.finishingtime.get(Calendar.YEAR);
			Integer Fmonth = this.finishingtime.get(Calendar.MONTH);
			Integer Fday = this.finishingtime.get(Calendar.DATE);
			Integer Fhour = this.finishingtime.get(Calendar.HOUR_OF_DAY);
			Integer Fminute = this.finishingtime.get(Calendar.MINUTE);
			
//			System.out.println("Saar " + Syear + " Sh " + Shour);
//			System.out.println("Faar " + Fyear + " Fh " + Fhour);
			
			db.addToDatabase(   "insert into larsfkl_felles.appointment(appointment_id,start,end,date,description,location,duration,room_id,groupID,owner) " +
								"values (" + key + ", '" 
								+ Shour + ":" + Sminute + "','" 
								+ Fhour + ":" + Fminute + "','" 
								+ Syear + "-" + Smonth + "-" + Sday + "','" 
								+ descr + "','" 
								+ meetpl +  "','" 
								+ this.duration +"', '1','" + groupID + "','" 
								+ getOwner().getEmail(conn) + "');", conn);
			db.joinAppointment(owner, this, conn);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Appointment(Calendar stime, int dur, String meetpl, String descr, Alarm alarm, Person owner, Integer groupID, Connection conn){
		this.owner = owner;
		this.starttime = stime;
		setFinishingTime(dur);
		this.duration = dur;
		this.meetingplace = meetpl;
		this.description = descr;
		this.alarm = alarm;
		Integer key = db.generateAppointmentID(conn);
		this.appointmentID = key;
		try {
			Integer Syear = this.starttime.get(Calendar.YEAR);
			Integer Smonth = this.starttime.get(Calendar.MONTH);
			Integer Sday = this.starttime.get(Calendar.DATE);
			Integer Shour = this.starttime.get(Calendar.HOUR_OF_DAY);
			Integer Sminute = this.starttime.get(Calendar.MINUTE);
			
			Integer Fyear = this.finishingtime.get(Calendar.YEAR);
			Integer Fmonth = this.finishingtime.get(Calendar.MONTH);
			Integer Fday = this.finishingtime.get(Calendar.DATE);
			Integer Fhour = this.finishingtime.get(Calendar.HOUR_OF_DAY);
			Integer Fminute = this.finishingtime.get(Calendar.MINUTE);
			
//			System.out.println("Saar " + Syear + " Sh " + Shour);
//			System.out.println("Faar " + Fyear + " Fh " + Fhour);
			
			db.addToDatabase(   "Insert into larsfkl_felles.appointment(appointment_id,start,end,date,description,location,duration,room_id,groupID,owner) " +
								"values (" + key + ", '" 
								+ Shour + ":" + Sminute + "','" 
								+ Fhour + ":" + Fminute + "','" 
								+ Syear + "-" + Smonth + "-" + Sday + "','" 
								+ descr + "','" 
								+ meetpl +  "','" 
								+ this.duration +"', '1','" + groupID + "','" 
								+ getOwner().getEmail(conn) + "');", conn);
			db.joinAppointment(owner, this, conn);
		}
		catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException f){
			System.out.println("Her er det error, men funker fett");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void addParticipant(Person par){
			participants.add(par); //overflødig?
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
	
	public Calendar getDate(Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT start, date FROM larsfkl_felles.appointment WHERE appointment_id = " + this.appointmentID + ";");
			rs.next();
			Calendar currentStartTime = db.createCalendarFromSQLTimeAndDate(rs.getString(1), rs.getString(2));
			return currentStartTime;
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		} 
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
	
	public void setYearOnlyInDB(Integer year, Connection conn){
		try{
			System.out.println("StartAar = " + this.starttime.get(Calendar.YEAR) + "  SluttAar = " + this.finishingtime.get(Calendar.YEAR));
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT start, end, date FROM larsfkl_felles.appointment WHERE appointment_id = " + this.appointmentID + ";");
			rs.next();
			Calendar start = db.createCalendarFromSQLTimeAndDate(rs.getString(1), rs.getString(3));
			Calendar end = db.createCalendarFromSQLTimeAndDate(rs.getString(2), rs.getString(3));
			start.set(Calendar.YEAR, year);
			end.set(Calendar.YEAR, year);
			System.out.println("1: " + start.get(Calendar.YEAR) + "  2: " + end.get(Calendar.YEAR));
			setStarttime(start, conn);
			setFinishingtime(end, conn);
			System.out.println("StartAar = " + this.starttime.get(Calendar.YEAR) + "  SluttAar = " + this.finishingtime.get(Calendar.YEAR));
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void setDateOnlyInDB(Calendar cal, Connection conn){
		try{
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT start, end, date FROM larsfkl_felles WHERE appointment_id = " + this.appointmentID + ";");
			rs.next();
			Calendar start = db.createCalendarFromSQLTimeAndDate(rs.getString(1), rs.getString(3));
			Calendar end = db.createCalendarFromSQLTimeAndDate(rs.getString(2), rs.getString(3));
			start.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			start.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			start.set(Calendar.DATE, cal.get(Calendar.DATE));
			end.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			end.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			end.set(Calendar.DATE, cal.get(Calendar.DATE));
			setStarttime(start, conn);
			setFinishingtime(end, conn);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void setStarttime(Calendar stime, Connection conn){
		try {
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT * FROM larsfkl_felles.appointment WHERE appointment_id = " + this.appointmentID + ";");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			
			String start = rs.getString(2);
			String end = rs.getString(3);
			String date = rs.getString(4);
			Calendar endCal = db.createCalendarFromSQLTimeAndDate(end, date);
			String calendar = db.convertCalendarTimeToSQLTime(stime);
			String dato = db.convertCalendarDateToSQLDate(stime);
			long dur = calculateDuration(stime, endCal);
			
			if((endCal.get(Calendar.HOUR_OF_DAY) > stime.get(Calendar.HOUR_OF_DAY)) 
					|| ((endCal.get(Calendar.HOUR_OF_DAY) == stime.get(Calendar.HOUR_OF_DAY)) && (endCal.get(Calendar.MINUTE) > stime.get(Calendar.MINUTE)))
					|| (endCal.get(Calendar.HOUR_OF_DAY) == stime.get(Calendar.HOUR_OF_DAY)) && endCal.get(Calendar.MINUTE) == stime.get(Calendar.MINUTE)){
				db.addToDatabase("update larsfkl_felles.appointment SET start = '" + calendar +"' WHERE appointment_id = " + this.appointmentID + ";", conn);	
				db.addToDatabase("update larsfkl_felles.appointment SET duration = " + dur + " WHERE appointment_id = " + this.appointmentID + ";", conn);
				db.addToDatabase("update larsfkl_felles.appointment SET date = '" + dato + "' Where appointment_id = " + this.appointmentID + ";", conn);
			}
			else System.out.println("Det er ikke mulig Œ sette start-tid til Œ v¾re f¿r slutt-tid.");
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
	
	public void setFinishingtime(Calendar ftime, Connection conn){
		try {
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT * FROM larsfkl_felles.appointment WHERE appointment_id = " + this.appointmentID + ";");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			
			String start = rs.getString(2);
			String end = rs.getString(3);
			String date = rs.getString(4);
			Calendar startCal = db.createCalendarFromSQLTimeAndDate(start, date);
			String calendar = db.convertCalendarTimeToSQLTime(ftime);
			String dato = db.convertCalendarDateToSQLDate(ftime);
			long dur = calculateDuration(startCal, ftime);
			
			if((startCal.get(Calendar.HOUR_OF_DAY) < ftime.get(Calendar.HOUR_OF_DAY)) 
					|| ((startCal.get(Calendar.HOUR_OF_DAY) == ftime.get(Calendar.HOUR_OF_DAY)) && (startCal.get(Calendar.MINUTE) < ftime.get(Calendar.MINUTE)))
					|| (startCal.get(Calendar.HOUR_OF_DAY) == ftime.get(Calendar.HOUR_OF_DAY)) && startCal.get(Calendar.MINUTE) == ftime.get(Calendar.MINUTE)){
				db.addToDatabase("update larsfkl_felles.appointment SET end = '" + calendar +"' WHERE appointment_id = " + this.appointmentID + ";", conn);	
				db.addToDatabase("update larsfkl_felles.appointment SET duration = " + dur + " WHERE appointment_id = " + this.appointmentID + ";", conn);
				db.addToDatabase("update larsfkl_felles.appointment SET date = '" + dato + "' Where appointment_id = " + this.appointmentID + ";", conn);
			}
			else System.out.println("Det er ikke mulig aa sette slutt-tid til aa vaere foer start-tid.");
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
	
	public void setFinishingTime(int dur, Connection conn){

		try {
			conn = db.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT * FROM larsfkl_felles.appointment WHERE appointment_id = " + this.appointmentID + ";");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			
			Calendar startTime = Calendar.getInstance();
			startTime = db.createCalendarFromSQLTimeAndDate(rs.getString(2), rs.getString(3));			
			
			if (dur > 0){
				duration = dur;
				// final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
				//feiling if-setning pga casting
				int t = (int) (starttime.getTimeInMillis()/(60000*60));
				Integer ftime = startTime.get(Calendar.HOUR_OF_DAY);
				Integer fminute = startTime.get(Calendar.MINUTE);
				Integer totaltid = (ftime * 60) + fminute + dur;
				
				Double start = totaltid.doubleValue();
				Double i = start % 1440;
				Double j = i/60;
				Double floorTime = Math.floor(j);
				Double floorMinute = Math.floor((j - floorTime) * 60);
				
				Integer Time = floorTime.intValue();
				Integer Minute = floorMinute.intValue();
				
				
				finishingtime = Calendar.getInstance();
				finishingtime.set(finishingtime.HOUR_OF_DAY, Time);
				finishingtime.set(finishingtime.MINUTE, Minute);
				
				db.addToDatabase("update larsfkl_felles.appointment SET end = '" + db.convertCalendarTimeToSQLTime(finishingtime) +"' WHERE appointment_id = " + this.appointmentID + ";", conn);	
				db.addToDatabase("update larsfkl_felles.appointment SET duration = " + dur + " WHERE appointment_id = " + this.appointmentID + ";", conn);
				db.addToDatabase("update larsfkl_felles.appointment SET date = '" + rs.getString(4) + "' Where appointment_id = " + this.appointmentID + ";", conn);
				conn.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public long calculateDuration(Calendar stime, Calendar ftime) { 
		//stime.getInstance();
		//ftime.getInstance();
//		long milsecs1= stime.getTimeInMillis();
//		long milsecs2 = ftime.getTimeInMillis();
//		long duration = (milsecs2-milsecs1)/(60 * 1000);
		this.starttime.set(Calendar.SECOND, 0);
		this.starttime.set(Calendar.MILLISECOND, 0);
		this.finishingtime.set(Calendar.SECOND, 0);
		this.finishingtime.set(Calendar.MILLISECOND, 0);
		
		//IKKE SLETT SYSOUT-ENE NEDENFOR
//		System.out.print("stime = " + stime.get(Calendar.HOUR_OF_DAY) + ":" + stime.get(Calendar.MINUTE) + "   " + stime.get(Calendar.DATE) 
//				+ "/" + stime.get(Calendar.MONTH) + "-" + stime.get(Calendar.YEAR));
//		System.out.print(" .. ftime = " + ftime.get(Calendar.HOUR_OF_DAY) + ":" + ftime.get(Calendar.MINUTE) + "   " + ftime.get(Calendar.DATE) 
//				+ "/" + ftime.get(Calendar.MONTH) + "-" + ftime.get(Calendar.YEAR));
		long dur = (ftime.getTimeInMillis()-stime.getTimeInMillis())/60000;
//		System.out.println(" ..  Duration: " + dur);
		return  dur; //i minutt
	}

	
	public void setMeetingplace(String meetpl, Connection conn){
		try {
			db.addToDatabase("update larsfkl_felles.appointment SET location = '"+ meetpl + "' WHERE appointment_id = '" + this.appointmentID + "';", conn);
			this.meetingplace = meetpl;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setAlarm(Alarm al){
		this.alarm = al;
	}
	
	public void setDescription(String descr, Connection conn){
		try {
			conn = db.getConnection();
			db.addToDatabase("update larsfkl_felles.appointment SET description = '"+ descr + "' WHERE appointment_id = '" + this.appointmentID + "';", conn);
			this.description = descr; 
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	
//	public static int add1900toCalendarYear(Integer year){
//		return year + 1900;
//	}
//	
//	public static Integer subtract1900fromSQLYear(Integer YEAR){
//		return YEAR - 1900;
//	}
	
	@Override
	public String toString(){
		return "AppointmentID: \t" + this.appointmentID + ",\t Description: " + this.getDescription();
	}
	
}

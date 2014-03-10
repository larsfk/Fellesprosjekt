package Classes;

import java.util.Calendar;
import java.util.ArrayList;

import javax.xml.datatype.Duration;

public class Appointment {
	private int appointmentID = 0;
	private Calendar starttime;
	private Calendar finishingtime;
	private static long duration = 0;
	private String meetingplace = null;
	private String description = null;
	private Calendar alarm;
	private ArrayList<Person> participants = new ArrayList<Person>();

	public Appointment(int appID, Calendar stime, Calendar ftime, String meetpl, String descr, Calendar alarm){
		this.appointmentID=appID;
		setStarttime(stime);
		setFinishingtime(ftime);
		setMeetingplace(meetpl);
		setDescription(descr);
		setDuration(ftime, stime);
		setAlarm(alarm);
	}
	public Appointment(int appID, Calendar stime, long dur, String meetpl, String descr, Calendar alarm){
		this.appointmentID=appID;
		setStarttime(stime);
		setDuration(dur);
		setMeetingplace(meetpl);
		setDescription(descr);
		setAlarm(alarm);
	}
	
	public void addParticipant(Person par){
			participants.add(par);
			/*
			 * 
			 * DATABASE
			 * 
			 * 
			 */
	}
	public void removeParticipant(Person par){
		if (participants.contains(par)){
			participants.remove(par);
		}
		/*
		 * 
		 * DATABASE
		 * 
		 * 
		 */
		System.out.println("hei");
	}
	
//	public void setDuration(long duration) {
//		this.duration = duration;
//	}
	
	public void editAppointment(Calendar stime, Calendar ftime, String meetpl, String descr){
		setStarttime(stime);
		setFinishingtime(ftime);
		setMeetingplace(meetpl);
		setDescription(descr);
		Update.updateAppointment();
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
	
	public Calendar getAlarm(){
		return alarm;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setStarttime(Calendar stime){
		starttime = stime; 
	}
	
	public void setFinishingtime(Calendar ftime){
		finishingtime = ftime;
		setDuration(starttime, ftime);
//		setDuration((finishingtime.getTimeInMillis()-starttime.getTimeInMillis())/60000); //i minutt
//		setDuration((int)(finishingtime.getTime()-starttime.getTime())/(1000 * 60 * 60 * 24));
//		setDuration(Duration dur = new Duration(stime, ftime);
	}
	
	//hvis vi har duration og vil sette finishingtime:
	
	public void setFinishingtime(long duration){
		finishingtime = starttime.add(Calendar.MINUTE, (int) duration);
	}
	
	public static void setDuration(Calendar stime, Calendar ftime) { 
		//stime.getInstance();
		//ftime.getInstance();
	    long milsecs1= stime.getTimeInMillis();
	    long milsecs2 = ftime.getTimeInMillis();
	    duration = (milsecs2-milsecs1)/(60 * 1000);
//		Calendar date = (Calendar) stime.clone();  
//		long duration = 0;  
//		while (date.before(ftime)) {  
//			date.add(Calendar.DAY_OF_MONTH, 1);  
//		    duration++;  
//		}  
//		return duration;  
		}
	
	public void setDuration(long dur) {  
			if (dur > 0){
				duration = dur;
			}
		}  
	
	
//	public void setDuration(long dur){
//		if (dur > 0){
//			duration = dur;
//			//feiling if-setning pga casting
//			finishingtime = Calendar.getInstance();
//			finishingtime.set(finishingtime.HOUR_OF_DAY, starttime.HOUR_OF_DAY);
//			//Hva gjor denne?? 
//		}
//	}
	
	public void setMeetingplace(String meetpl){
		meetingplace = meetpl;
	}
	
	public void setAlarm(Calendar al){
		alarm = al;
	}
	
	public void setDescription(String descr){
		description = descr; 
	}
	
	public void changeStatus(Participant par, boolean ans){
		//par.ans = ans;
	}
	@Override
	public String toString(){
		return "AppointmentID: " + appointmentID + ", Calendar: " + getDate();
	}
	
}

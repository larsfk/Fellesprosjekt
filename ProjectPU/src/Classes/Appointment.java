package Classes;

import java.util.Calendar;
import java.util.ArrayList;

public class Appointment {
	private int appointmentID = 0;
	private Calendar starttime;
	private Calendar finishingtime;
	private long duration = 0;
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
	}
	public Appointment(int appID, Calendar stime, int dur, String meetpl, String descr, Calendar alarm){
		this.appointmentID=appID;
		setStarttime(stime);
		setDuration(dur);
		setMeetingplace(meetpl);
		setDescription(descr);
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
		duration = (finishingtime.getTimeInMillis()-starttime.getTimeInMillis())/60000; //i minutt
	}
	
	public void setDuration(int dur){
		if (dur > 0){
			duration = dur;
			// final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
			//feiling if-setning pga casting
			int t = (int) starttime.getTimeInMillis()/60000;
			finishingtime = Calendar.getInstance();
			finishingtime.set(finishingtime.HOUR_OF_DAY, starttime.HOUR_OF_DAY+t);
		}
	}
	
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

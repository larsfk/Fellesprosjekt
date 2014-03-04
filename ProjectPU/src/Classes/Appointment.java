package Classes;

import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Appointment {
	private int appointmentID = 0;
	private Date date;
	private Date starttime;
	private Date finishingtime;
	private long duration = 0;
	private String meetingplace = null;
	private String description = null;
	private Date alarm;
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private ArrayList<Person> participants = new ArrayList<Person>();
	boolean hidden = false;

	public Appointment(int appID, Date stime, Date ftime, String meetpl, String descr, Date alarm){
		//Appointment.appointmentID = appID;
		setStarttime(stime);
		setFinishingtime(ftime);
		setMeetingplace(meetpl);
		setDescription(descr);
	}
	public Appointment(int appID, Date stime, int dur, String meetpl, String descr, Date alarm){
		//Appointment.appointmentID = appID;
		starttime = stime;
		duration = dur;
		meetingplace = meetpl;
		description = descr;
	}
	
	public void addParticipant(){
		//blabla
	}
	
	public void removeParticipant(){
		//blabla
	}
	
	public void setHidden(boolean hide){
		hidden = hide;
	}
	
	public int getAppointmentID(){
		return appointmentID;
	}
	
	public Date getDate(){
		return date;
	}
	
	public Date getStarttime(){
		return starttime;
	}
	
	public Date getFinishingtime(){
		return finishingtime;
	}
	
	public long getDuration(){
		return duration;
	}
	
	public String getMeetingplace(){
		return meetingplace;
	}
	
	public Date getAlarm(){
		return alarm;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDate(Date day){
		date = day;
	}
	
	public void setStarttime(Date stime){
		starttime = stime; 
	}
	
	public void setFinishingtime(Date ftime){
		finishingtime = ftime;
		duration = (finishingtime.getTime()-starttime.getTime())/60000; //i minutt
	}
	
	public void setDuration(int dur){
		if (dur > 0){
			duration = dur;
			final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
			long t=starttime.getTime();
			finishingtime=new Date(t + (dur * ONE_MINUTE_IN_MILLIS));	
		}
	}
	
	public void setMeetingplace(String meetpl){
		meetingplace = meetpl;
	}
	
	public void setAlarm(Date al){
		alarm = al;
	}
	
	public void setDescription(String descr){
		description = descr; 
	}
	
	public void changeStatus(Participant par, boolean ans){
		par.ans = ans;
	}
	
	
}

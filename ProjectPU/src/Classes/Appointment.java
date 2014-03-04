package Classes;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Appointment {
	int appointmentID = 0;
	Date starttime;
	Date finishingtime;
	int duration = 0;
	String meetingplace = null;
	String description = null;
	Date alarm;
	ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	ArrayList<Person> participants = new ArrayList<Person>();
	boolean hidden = false;

	public Appointment(int appID, Date stime, Date ftime, int dur, String meetpl, String descr, Date alarm){
		//Appointment.appointmentID = appID;
		starttime = stime;
		finishingtime = ftime;
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
	
	public Date getStarttime(){
		return starttime;
	}
	
	public Date getFinishingtime(){
		return finishingtime;
	}
	
	public int getDuration(){
		return duration;
	}
	
	public String getMeetingplace(){
		return meetingplace;
	}
	
	public Date getAlarm(){
		return alarm;
	}
	
	
	public void setStarttime(Date stime){
		starttime = stime; 
	}
	
	public void setFinishingtime(Date ftime){
		finishingtime = ftime;
	}
	
	public void setDuration(int dur){
		duration = dur;
	}
	
	public void setMeetingplace(String meetpl){
		meetingplace = meetpl;
	}
	
	public void setAlarm(Date al){
		alarm = al;
	}
	
	public void changeStatus(Participant par){
		
	}
	
	
}

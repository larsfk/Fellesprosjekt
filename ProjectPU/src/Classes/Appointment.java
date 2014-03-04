package Classes;

import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Appointment {
	private int appointmentID = 0;
	private Date starttime;
	private Date finishingtime;
	private int duration = 0;
	private String meetingplace = null;
	private String description = null;
	private Date alarm;
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private ArrayList<Person> participants = new ArrayList<Person>();
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
		if (dur > 0){
			duration = dur;
			int day = starttime.getDay();
			int hours = starttime.getHours();
			int min = starttime.getMinutes();
			finishingtime.set
			
		}
	}
	
	public void setMeetingplace(String meetpl){
		meetingplace = meetpl;
	}
	
	public void setAlarm(Date al){
		alarm = al;
	}
	
	public void changeStatus(Participant par, boolean ans){
		par.ans = ans;
	}
	
	
}

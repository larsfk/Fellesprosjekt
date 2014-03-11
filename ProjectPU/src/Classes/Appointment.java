package Classes;

import java.util.Calendar;
import java.util.ArrayList;

public class Appointment {
	private int appointmentID = -1;
	private Calendar starttime;
	private Calendar finishingtime;
	private long duration = 0;
	private String meetingplace = null;
	private String description = null;
	private Calendar alarm;
	private Person owner;
	private ArrayList<Person> participants = new ArrayList<Person>();
	private ArrayList<AppointmentToPerson> participantConnect = new ArrayList<AppointmentToPerson>();

	public Appointment(int appID, Calendar stime, Calendar ftime, String meetpl, String descr, Calendar alarm, Person owner){
		if (stime.after(ftime)){
			throw new IllegalArgumentException("Starttime cannot be after finishtime");
		}
		/*
		 * 
		 * DATABASE: IF appID finnes throw new IllegalArgumentException
		 * 
		 */
		this.appointmentID=appID;
		this.owner = owner;
		AppointmentToPerson atp = new AppointmentToPerson(owner,this);
		atp.setOwner(true);
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
	
	public void setDuration(long duration) {
		this.duration = duration;
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
	
	public Person getOwner(){
		return owner;
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
	
	public void setIsGoing(AppointmentToPerson atp, boolean isGoing){ //?
		atp.setIsGoing(isGoing);
	}
	@Override
	public String toString(){
		return "AppointmentID: " + appointmentID + ", Calendar: " + getDate();
	}
	
}

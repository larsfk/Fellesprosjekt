package Classes;

import java.sql.Date;
import java.util.ArrayList;

public class Calendar {
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private final String userEmail;
	public Calendar(String userEmail){
		this.userEmail = userEmail;
	}
	public String getUserEmail(){
		return userEmail;
	}
	
	public ArrayList<Appointment> getAppointments(){
		return appointments;
	}
	public void hideAppointment(Appointment appoint){
		if (appointments.contains(appoint)){
			appoint.setHidden(true);			
		}
	}
	
	public void addAppointment(Appointment appoint){
		if(!appointments.contains(appoint)){
			appointments.add(appoint);
		}
	}

	public void deleteAppointment(Appointment appoint){
		if (appointments.contains(appoint)){
			appointments.remove(appoint);
		}
	}
	
	public void editStarttime(Appointment appoint, Date starttime){
		appoint.setStarttime(starttime);
	}
	
	public void editFinishtime(Appointment appoint, Date finishtime){
		appoint.setFinishingtime(finishtime);
	}
	
	public void editDuration(Appointment appoint, int duration){
		appoint.setDuration(duration);
	}
	
	public void editMeetingplace(Appointment appoint, String meetpl){
		appoint.setMeetingplace(meetpl);
	}
	
	public void editDescription(Appointment appoint, String descr){
		appoint.setDescription(descr);
	}
	
	public void editAlarm(Appointment appoint, Date alarm){
		appoint.alarm = alarm;
	}

}

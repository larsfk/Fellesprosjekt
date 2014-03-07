package Classes;

import java.util.Date;
import java.util.ArrayList;

public class PUCalendar {
	
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private ArrayList<Boolean> isHidden = new ArrayList<Boolean>();
	private ArrayList<Boolean> isParticipating = new ArrayList<Boolean>();
	//private final String userEmail;
	private final Person user;
	
	public void setNotGoing(Appointment appoint){
		if (appointments.contains(appoint)){
			isParticipating.add(appointments.indexOf(appoint), false);
		}
	}
	
	public PUCalendar(Person user){
		this.user = user;
		for (int i = 0;i<isParticipating.size();i++){
			isParticipating.set(i, false);
		}
		for (int i = 0;i<isHidden.size();i++){
			isHidden.set(i, false);
		}
	}
	public String getUserEmail(){
		return user.getEmail();
	}
	
	public ArrayList<Appointment> getAppointments(){
		return appointments;
	}
	public void hideAppointment(Appointment appoint){
		if (appointments.contains(appoint)){
			isHidden.add(appointments.indexOf(appoint), true);			
		}
	}
	
	public void addAppointment(Appointment appoint){
		if(!appointments.contains(appoint)){
			for (int i = 0; i < appointments.size(); i++){
				if (appoint.getStarttime().compareTo(appointments.get(i).getStarttime())<0){
					appointments.add(i,appoint);
				}
			}
		//appoint.addParticipant(par);
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

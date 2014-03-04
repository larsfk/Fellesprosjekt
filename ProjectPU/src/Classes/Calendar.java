package Classes;

import java.sql.Date;
import java.util.ArrayList;

public class Calendar {
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();

	public Calendar(){
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
		appoint.starttime = starttime;
	}
	
	public void editFinishtime(Appointment appoint, Date finishtime){
		appoint.finishingtime = finishtime;
	}
	
	public void editDuration(Appointment appoint, int duration){
		appoint.duration = duration;
	}
	
	public void editMeetingplace(Appointment appoint, String meetpl){
		appoint.meetingplace = meetpl;
	}
	
	public void editDescription(Appointment appoint, String descr){
		appoint.description = descr;
	}
	
	public void editAlarm(Appointment appoint, Date alarm){
		appoint.alarm = alarm;
	}

}

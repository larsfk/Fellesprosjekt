package Classes;

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
	
	public void editAppointment(Appointment appoint){
		
	}

}

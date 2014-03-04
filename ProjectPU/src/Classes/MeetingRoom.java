package Classes;

import java.util.ArrayList;

public class MeetingRoom {
	
	private int ID;
	private ArrayList<Appointment> AppointmentList = new ArrayList<Appointment>();
	
	public MeetingRoom(int ID){
		this.ID = ID;
		this.AppointmentList = null;
	}
	
	public void addAppointment(Appointment Q){
		AppointmentList.add(Q);
	}
	
	public Appointment getAppointment(int position){
		return AppointmentList.get(position);
	}
	
}

package Classes;

import java.sql.Time;

public class Appointment {
	int appointmentID = 0;
	Time starttime; 
	int ftime = 0;

	public Appointment(appID, stime, ftime, dur, meetpl, descr, alarm){
		Appointment.appointmentID = appID;
		Appointment.starttime = setTime(stime); //lurer veldig paa om denne fungerer... Usikker paa hvordan man skriver tiden, DDMMYY HHMMSS?
		Appointment.finishingtime = ftime;
		Appointment.duration = dur;
		Appointment.meetingplace = meetpl;
		
	}

}

package Classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

public class TestMeetingRoom {
	
	Calendar start1 = Calendar.getInstance();
	Calendar finish1 = Calendar.getInstance();
	Calendar alarm1 = Calendar.getInstance();
	Calendar start2 = Calendar.getInstance();
	Calendar finish2 = Calendar.getInstance();
	Calendar alarm2 = Calendar.getInstance();
	Appointment app1 = new Appointment(1, start1, finish1, "kontor 20", "mote", alarm1);
	Appointment app2 = new Appointment(2, start2, finish2, "kontor 20", "mote", alarm2);
	private ArrayList<Appointment> AppointmentList = new ArrayList<Appointment>();
	
	
	public TestMeetingRoom(){
		
		
		start1.set(start1.HOUR_OF_DAY,14);
		start1.set(start1.MINUTE,30);
		finish1.set(finish1.HOUR_OF_DAY, 15);
		finish1.set(finish1.MINUTE, 30);
		alarm1.set(alarm1.HOUR_OF_DAY, 13);
		alarm1.set(alarm1.MINUTE, 45);
		System.out.println("duration: " + app1.getDuration());
		
		start2.set(start2.HOUR_OF_DAY,15);
		start2.set(start2.MINUTE,15);
		
	}

	@Test
	public void testAddAppointment(){
		
	}

}

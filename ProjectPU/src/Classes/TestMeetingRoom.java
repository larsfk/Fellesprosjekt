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
	ArrayList<Appointment> AppointmentList;
	MeetingRoom meetingRooom = new MeetingRoom(1, 10);
	Appointment app1;
	Appointment app2;	


	public TestMeetingRoom(){


		start1.set(start1.HOUR_OF_DAY,14);
		start1.set(start1.MINUTE,30);
		start1.set(start1.DATE,22);
		start1.set(start1.MONTH,4);
		finish1.set(finish1.HOUR_OF_DAY, 15);
		finish1.set(finish1.MINUTE, 30);
		finish1.set(finish1.DATE, 22);
		finish1.set(finish1.MONTH, 4);

		alarm1.set(alarm1.HOUR_OF_DAY, 13);
		alarm1.set(alarm1.MINUTE, 45);

		start2.set(start2.HOUR_OF_DAY,15);
		start2.set(start2.MONTH,10);
		start2.set(start2.DATE,15);
		start2.set(start2.MINUTE,15);
		finish2.set(finish2.HOUR_OF_DAY, 14);
		finish2.set(finish2.MINUTE, 00);
		finish2.set(finish2.DATE, 15);
		finish2.set(finish2.MONTH, 10);

		app1 = new Appointment(1, start1, finish1, "kontor 20", "mote", alarm1);
		app2 = new Appointment(2, start2, finish2, "kontor 20", "mote", alarm2);
		
		System.out.println(app1.getStarttime().MONTH);

//		meetingRooom.addAppointment(app1);
//		meetingRooom.addAppointment(app2);
//		AppointmentList = meetingRooom.getAppointmentList();
//
//		System.out.println(AppointmentList.get(0));
//		System.out.println(app1);
//		System.out.println("duration: " + app1.getDuration());

	}


	@Test
//	public void testAddAppointment(){
//		int length = AppointmentList.size();
//		int count = 0;
//		for(int i = 0; i < length; i++){
//			if((app1.equals(AppointmentList.get(i)) || app2.equals(AppointmentList.get(i))))
//				count ++;
//		}
//		assertTrue(length == count);
//	}

	public void testMeetingRoom(){

	}

}

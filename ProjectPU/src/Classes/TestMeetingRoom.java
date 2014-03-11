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
	Calendar start3 = Calendar.getInstance();
	Calendar finish3 = Calendar.getInstance();
	Calendar epic;
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

		start2.set(start2.HOUR_OF_DAY,15);
		start2.set(start2.MINUTE,15);
		start2.set(start2.DATE,15);
		start2.set(start2.MONTH,10);
		finish2.set(finish2.HOUR_OF_DAY, 18);
		finish2.set(finish2.MINUTE, 00);
		finish2.set(finish2.DATE, 15);
		finish2.set(finish2.MONTH, 10);

		start3.set(start3.HOUR_OF_DAY,14);
		start3.set(start3.MINUTE,30);
		start3.set(start3.DATE,26);
		start3.set(start3.MONTH,4);
		finish3.set(finish3.HOUR_OF_DAY, 15);
		finish3.set(finish3.MINUTE, 30);
		finish3.set(finish3.DATE, 26);
		finish3.set(finish3.MONTH, 4);
		
		alarm1.set(alarm1.HOUR_OF_DAY, 13);
		alarm1.set(alarm1.MINUTE, 45);
		alarm2.set(alarm1.HOUR_OF_DAY, 13);
		alarm2.set(alarm1.MINUTE, 45);
		
		app1 = new Appointment(1, start1, finish1, "kontor 20", "mote", alarm1);
		app2 = new Appointment(2, start2, finish2, "kontor 20", "mote", alarm2);
		
//		System.out.println(app1.getStarttime().get(Calendar.MONTH));
//		System.out.println(app1.getFinishingtime().get(Calendar.HOUR_OF_DAY) + ":" + app1.getFinishingtime().get(Calendar.MINUTE));
//		System.out.println(finish1.get(Calendar.HOUR_OF_DAY));
//		System.out.println(app1.getStarttime().get(Calendar.HOUR_OF_DAY));

		meetingRooom.addAppointment(app1);
		meetingRooom.addAppointment(app2);
		AppointmentList = meetingRooom.getAppointmentList();


	}


	@Test
	public void testAddAppointment(){
		int length = AppointmentList.size();
		int count = 0;
		for(int i = 0; i < length; i++){
			if((app1.equals(AppointmentList.get(i)) || app2.equals(AppointmentList.get(i))))
				count ++;
		}
		assertTrue(length == count);
	}

	@Test
	public void testMeetingRoom(){
		MeetingRoom meetingRoom2 = new MeetingRoom(2, 12);
		assertEquals(2, meetingRoom2.getID());
		assertEquals(12, meetingRoom2.getCapasity());
		assertEquals(meetingRoom2.getAppointmentList().get(0), null);
		assertTrue(meetingRoom2.getAppointmentList().size() == 1);
	}
	
	@Test
	public void testRemoveAppointment(){
		meetingRooom.removeAppointment(app1.getAppointmentID());
		assertTrue(!(AppointmentList.contains(app1)));
	}
	
	@Test
	public void testIsFree(){
		System.out.println(AppointmentList);
		assertTrue(!(meetingRooom.isFree(start1, finish1)));
		assertEquals(true, meetingRooom.isFree(start3, finish3));
	}
}

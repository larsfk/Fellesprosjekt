package Classes;

import java.util.Calendar;

import junit.framework.TestCase;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestResult;

public class TestAppointment extends TestCase {

	Calendar start1 = Calendar.getInstance();
	Calendar finish1 = Calendar.getInstance();
	Alarm alarm1;
	Calendar start2 = Calendar.getInstance();
	Calendar finish2 = Calendar.getInstance();
	Alarm alarm2;
	Appointment app1;
	Appointment app2;
	Person testPerson;
	public static void main(String[] args) {
		TestAppointment ta = new TestAppointment();
		
	}

	public TestAppointment(){
		testPerson = new Person("Odd Nordstoga","Hjemme","919238741","grisen@hyler.no","111111111","griser");
		start1.set(start1.HOUR_OF_DAY,14);
		start1.set(start1.MINUTE,30);
		
		finish1.set(finish1.HOUR_OF_DAY, 15);
		finish1.set(finish1.MINUTE, 30);
		System.out.println(finish1.after(start1));
		
		alarm1 = new Alarm(12, 3, 8, 4, 12, "test");
		
		start2.set(start2.HOUR_OF_DAY,15);
		start2.set(start2.MINUTE,15);
		app1 = new Appointment(start1, finish1, "kontor 20", "mote", alarm1, testPerson);
		app2 = new Appointment(start2, finish2, "kontor 20", "mote", alarm2, testPerson);

	}
	
//	public void testAppointment(){	
//		//System.out.println("start 1: " + start1);
//		//System.out.println("start 2: " + start2);
//		long milsecs1= start1.getTimeInMillis();
//	    long milsecs2 = finish1.getTimeInMillis();
//	    long duration = (milsecs2-milsecs1)/(60 * 1000);
//	    //DETTE FUNKER MEN IKKE I FUNKSJONEN!!!
//		System.out.println(duration);
//	}
	
	//@Test
	public void testMakeAnAppointment(){
		Assert.assertTrue(!app1.equals(null));		
	}
	
	public void testGetStartHour1(){
		Assert.assertEquals(2, start1.get(Calendar.HOUR));
	}
	
	public void testGetStartMinute1(){
		Assert.assertEquals(30, start1.get(Calendar.MINUTE));
	}
	
	public void testGetStartHour2(){
		Assert.assertEquals(3, start2.get(Calendar.HOUR));
	}
	
	public void testGetStartMinute2(){
		Assert.assertEquals(15, start2.get(Calendar.MINUTE));
	}
	
	public void testGetAppointmentID(){
		Assert.assertEquals(1, app1.getAppointmentID());
	}
	
	public void testSetDuration(){
		//ikke lov aa sette negativ varighet
		Assert.assertEquals(60, app1.getDuration());
	}
	
	public void testGetDuration(){
		assertEquals(60, app1.getDuration());
	}
	
	public void testSetDescription(){
		app1.setDescription("hei123");
		Assert.assertEquals("hei123", app1.getDescription());
	}
	
//	public void testSetAlarm(){
//		app1.setAlarm(alarm1);
//		Assert.assertEquals("13.45", ""+alarm1.get(alarm1.HOUR_OF_DAY)+"."+alarm1.get(alarm1.MINUTE));
//	}

	
}




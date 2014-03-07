package Classes;

import java.util.Calendar;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestAppointment extends TestCase {

	Calendar start1 = Calendar.getInstance();
	Calendar finish1 = Calendar.getInstance();
	Calendar alarm1 = Calendar.getInstance();
	Calendar start2 = Calendar.getInstance();
	Calendar finish2 = Calendar.getInstance();
	Calendar alarm2 = Calendar.getInstance();
	public TestAppointment(){
		
		start1.clear();
		start2.clear();
		alarm1.clear();
		start1.set(start1.HOUR_OF_DAY,1);
		start1.set(start1.MINUTE,30);
		
		start2.set(start2.HOUR_OF_DAY,15);
		start2.set(start2.MINUTE,15);
		
		//System.out.println("start 1: " + start1);
		//System.out.println("start 2: " + start2);

	}
	@Test
	public void testMakeAnAppointment(){
		Assert.assertTrue(!app1.equals(null));		
	}
	
	public void testGetStartHour(){
		Assert.assertEquals(1, start1.get(Calendar.HOUR));
	}
	
	public void testGetStartMinute(){
		Assert.assertEquals(30, start1.get(Calendar.MINUTE));
	}
	
	public void testGetAppointmentID(){
		Assert.assertEquals(1, app1.getAppointmentID());
	}
	
	public void testAddAppointment(){
		
	}
	
//	public static void main(String[] args) {
//		TestAppointment test = new TestAppointment();
//		test.makeAnAppointmentTest();
//	}
//	
	Appointment app1 = new Appointment(1, start1, finish1, "kontor 20", "mote", alarm1);
	Appointment app2 = new Appointment(2, start2, finish2, "kontor 20", "mote", alarm2);
	
//    Assert.assertEquals(m12CHF, m12CHF);
//    Assert.assertEquals(m12CHF, new Money(12, "CHF")); // (1)
//    Assert.assertTrue(!m12CHF.equals(m14CHF));
//}
	
}



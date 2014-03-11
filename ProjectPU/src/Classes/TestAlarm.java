package Classes;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class TestAlarm {

	Alarm alarm1;
	Calendar start1 = Calendar.getInstance();
	Calendar finish1 = Calendar.getInstance();
	Appointment app1;
	Calendar date1 = Calendar.getInstance();
	
	public static void main(String[] args) {
		TestAlarm testalarm = new TestAlarm();
	}
	
	public TestAlarm(){
		start1 = 
		app1 = new Appointment(1, start1, finish1, "kontor 20", "mote", alarm1);
		alarm1 = new Alarm(284, 3, 11, 14, 30, "Ha en god dag");
		System.out.println(alarm1);
		//date1.set(Calendar(2013, 3, 11, 14, 30));
		alarm1.setStart(284, 3, 11, 14, 30);
		date1.set(date1.YEAR, 2013);
		date1.set(date1.MONTH, 3);
		date1.set(date1.DAY_OF_MONTH, 11);
		date1.set(date1.HOUR_OF_DAY, 14);
		date1.set(date1.MINUTE, 30);
		app1.setStarttime(date1);
	}
	
	@Test
	
	public void testGetDate(){
		assertEquals(date1, app1.getDate());
	}
	public void testGetDescription(){
		assertEquals("Ha en god dag", alarm1.getDescription());
	}
	
	public void test() {
		fail("Not yet implemented");
	}

}


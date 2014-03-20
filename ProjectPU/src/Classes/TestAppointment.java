package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import junit.framework.TestCase;
import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestResult;

public class TestAppointment extends TestCase {
	
	static Database db = new Database();
	static Person Ivar;
	static Appointment app;
	static Calendar stime = Calendar.getInstance();
	static Calendar ftime = Calendar.getInstance();
	static String meetpl = "NTNU";
	static String descr = "Progging";
	static Alarm alarm = new Alarm(114, 2, 12, 9, 00, "Husk progging");
	static String appID, start, end, description, location, duration, roomID, groupID, owner;
	static Calendar start1 = Calendar.getInstance();
	static Calendar end1 = Calendar.getInstance();
	static Calendar start2 = Calendar.getInstance();
	static Calendar end2 = Calendar.getInstance();
	static Appointment appointment;
	
	public static void main(String[] args){
		setStartTime();
		init();
	}
	
	public static void setStartTime(){
		Connection conn;
		try {
			conn = db.getConnection();
			appointment = db.getAppointment(23, conn);
			stime.set(stime.HOUR_OF_DAY, 10);
			stime.set(stime.MINUTE, 00);
			ftime.set(ftime.HOUR_OF_DAY, 20);
			ftime.set(ftime.MINUTE, 30);
			appointment.setFinishingtime(ftime);
			appointment.setStarttime(stime);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void makePerson(){
		Connection conn;
		try {
			conn = db.getConnection();
			appointment = db.getAppointment(23, conn);
			Ivar = new Person("Ivar", "P15", "12345678", "ivarivar@ivar.no", "111", "Ivar", conn);
			app = new Appointment(stime, ftime, meetpl, descr, alarm, Ivar, null, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void init(){
		try{
			Connection conn = db.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT * FROM larsfkl_felles.appointment WHERE appointment_id = " + 23 + ";");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			
			appID = rs.getString(1);
			start = rs.getString(2);
			end = rs.getString(3);
			description = rs.getString(5);
			location = rs.getString(6);
			duration = rs.getString(7);
			roomID = rs.getString(8);
			groupID = rs.getString(9);
			owner = rs.getString(10);
		
		} catch(SQLException e) {
		e.printStackTrace();
		}
	}
	
	public static void testAppointment(){
		
		Connection conn;
		try {
			conn = db.getConnection();
			setStartTime();
			init();
			Ivar = db.getPersonFromDatabase("ivarivar@ivar.no", conn);
			start1 = db.convertSQLTimeToCalendarTime(start);
			end1 = db.convertSQLTimeToCalendarTime(end);
			
			assertEquals(10, start1.get(Calendar.HOUR_OF_DAY));
			assertEquals(00, start1.get(Calendar.MINUTE));
			assertEquals(20, end1.get(Calendar.HOUR_OF_DAY));
			assertEquals(30, end1.get(Calendar.MINUTE));
			assertEquals(meetpl, location);
			assertEquals(descr, description);
			assertEquals(Ivar.getEmail(), owner);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void testSetFinishingtime(){
		
		setStartTime();
		Connection conn;
		try {
			conn = db.getConnection();
			appointment = db.getAppointment(23, conn);
			
			Calendar newFtime = Calendar.getInstance();
			newFtime.set(newFtime.HOUR_OF_DAY, 18);
			newFtime.set(newFtime.MINUTE, 10);
			appointment.setFinishingtime(newFtime);
			init();
			end1 = db.convertSQLTimeToCalendarTime(end);
			assertEquals(newFtime.get(Calendar.HOUR_OF_DAY), end1.get(Calendar.HOUR_OF_DAY));
			assertEquals(newFtime.get(Calendar.MINUTE), end1.get(Calendar.MINUTE));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void testFinisjingtime(){
		
		setStartTime();
		int newDuration = 45;
		appointment.setFinishingTime(newDuration);
		init();
		int dur = Integer.parseInt(duration);
		assertEquals(newDuration, dur);		
		end1 = db.convertSQLTimeToCalendarTime(end);

		assertEquals(10, end1.get(Calendar.HOUR_OF_DAY));
		assertEquals(45, end1.get(Calendar.MINUTE));
		
		setStartTime();
	}
	
	public static void testSetStartTime(){
		
		setStartTime();
		init();
		
		Connection conn;
		try {
			conn = db.getConnection();
			appointment = db.getAppointment(23, conn);
			
			Calendar newStime = Calendar.getInstance();
			newStime.set(newStime.HOUR_OF_DAY, 18);
			newStime.set(newStime.MINUTE, 10);
			appointment.setStarttime(newStime);
			init();
			start1 = db.convertSQLTimeToCalendarTime(start);
			assertEquals(newStime.get(Calendar.HOUR_OF_DAY), start1.get(Calendar.HOUR_OF_DAY));
			assertEquals(newStime.get(Calendar.MINUTE), start1.get(Calendar.MINUTE));
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void testSetDuration(){
		
		int dur = 300;
		Connection conn;
		try {
			conn = db.getConnection();
			appointment = db.getAppointment(23, conn);
			appointment.setFinishingTime(dur);
			init();
			int dur2 = Integer.parseInt(duration);
			assertEquals(dur, dur2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//
//	public TestAppointment(){
//		testPerson = new Person("Odd Nordstoga","Hjemme","919238741","grisen@hyler.no","111111111","griser");
//		start1.set(start1.HOUR_OF_DAY,14);
//		start1.set(start1.MINUTE,30);
//		
//		finish1.set(finish1.HOUR_OF_DAY, 15);
//		finish1.set(finish1.MINUTE, 30);
//		System.out.println(finish1.after(start1));
//		
//		alarm1 = new Alarm(12, 3, 8, 4, 12, "test");
//		
//		start2.set(start2.HOUR_OF_DAY,15);
//		start2.set(start2.MINUTE,15);
//		
//		try{
//			Connection conn = db.getConnection();
//			app1 = new Appointment(start1, finish1, "kontor 20", "mote", alarm1, testPerson, conn);
//			app2 = new Appointment(start2, finish2, "kontor 20", "mote", alarm2, testPerson, conn);			
//		}
//		
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
//	
////	public void testAppointment(){	
////		//System.out.println("start 1: " + start1);
////		//System.out.println("start 2: " + start2);
////		long milsecs1= start1.getTimeInMillis();
////	    long milsecs2 = finish1.getTimeInMillis();
////	    long duration = (milsecs2-milsecs1)/(60 * 1000);
////	    //DETTE FUNKER MEN IKKE I FUNKSJONEN!!!
////		System.out.println(duration);
////	}
//	
//	//@Test
//	public void testMakeAnAppointment(){
//		Assert.assertTrue(!app1.equals(null));		
//	}
//	
//	public void testGetStartHour1(){
//		Assert.assertEquals(2, start1.get(Calendar.HOUR));
//	}
//	
//	public void testGetStartMinute1(){
//		Assert.assertEquals(30, start1.get(Calendar.MINUTE));
//	}
//	
//	public void testGetStartHour2(){
//		Assert.assertEquals(3, start2.get(Calendar.HOUR));
//	}
//	
//	public void testGetStartMinute2(){
//		Assert.assertEquals(15, start2.get(Calendar.MINUTE));
//	}
//	
//	public void testGetAppointmentID(){
//		Assert.assertEquals(1, app1.getAppointmentID());
//	}
//	
//	public void testSetDuration(){
//		//ikke lov aa sette negativ varighet
//		Assert.assertEquals(60, app1.getDuration());
//	}
//	
//	public void testGetDuration(){
//		assertEquals(60, app1.getDuration());
//	}
//	
//	public void testSetDescription(){
//		app1.setDescription("hei123");
//		Assert.assertEquals("hei123", app1.getDescription());
//	}
//	
//	public void testSetAlarm(){
//		app1.setAlarm(alarm1);
//		Assert.assertEquals("13.45", ""+alarm1.get(alarm1.HOUR_OF_DAY)+"."+alarm1.get(alarm1.MINUTE));
//	}

	
}




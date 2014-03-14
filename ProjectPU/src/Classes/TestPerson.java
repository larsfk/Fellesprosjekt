package Classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import junit.framework.TestCase;


public class TestPerson extends TestCase {

	Database db = new Database();
	Person testPerson;
	Connection conn;
	
	public TestPerson() throws SQLException{
		conn = (Connection) db.getConnection();
		testPerson = db.getPersonFromDatabase("test@email.com", conn);

	}

	//Tester Gettere for Œ se om programmet klarer Œ hente info fra databasen
	public void testGetName(){
		assertEquals("TestPerson", testPerson.getName());
	}
	
	public void testGetOffice(){
		assertEquals("TestOffice",testPerson.getOffice());
	}
	
	public void testGetTlf(){
		assertEquals("99999999",testPerson.getTlf());
	}
	
	public void testGetEmail(){
		assertEquals("test@email.com",testPerson.getEmail());
	}
	
	public void testGetSSN(){
		assertEquals("000000000000",testPerson.getSSN());
	}
	
	public void testGetPassword(){
		assertEquals("testPassword",testPerson.getPassword());
	}
	
	public void testMakeAppointment(){
		Calendar start1 = Calendar.getInstance();
		Calendar finish1 = Calendar.getInstance();
		
		start1.set(start1.HOUR_OF_DAY,14);
		start1.set(start1.MINUTE,30);
		start1.set(start1.YEAR,114);
		
		finish1.set(finish1.HOUR_OF_DAY, 15);
		finish1.set(finish1.MINUTE, 30);
		finish1.set(finish1.YEAR,114);
		
		Alarm alarm1;
		alarm1 = new Alarm(2014, 3, 14, 13, 00, "M¿te hos deg");

		testPerson.makeAppointment(start1, finish1, "hos deg", "Snakke om tull", alarm1);
	}

	
}

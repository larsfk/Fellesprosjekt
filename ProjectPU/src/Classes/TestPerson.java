package Classes;

import java.sql.Connection;
import java.sql.SQLException;

import junit.framework.TestCase;


public class TestPerson extends TestCase {

	Database db = new Database();
	Person testPerson;
	Connection conn;
	
	public TestPerson() throws SQLException{
		conn = (Connection) db.getConnection();
		testPerson = new Person("TestPerson", "TestOffice", "99999999","test@email.com", "000000000000", "testPassword",conn);

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
	

	
}

package Classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;

public class dateComparator implements Comparator<Appointment> {

	public int compare(Appointment app0, Appointment app1) {
		try{
			Database db = new Database();
			Connection conn = db.getConnection();
			return app0.getDate(conn).compareTo(app1.getDate(conn));
		}
		catch (SQLException e){e.printStackTrace(); return (Integer) null;}
	}
	
}

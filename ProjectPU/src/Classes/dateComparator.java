package Classes;

import java.util.Comparator;

public class dateComparator implements Comparator<Appointment> {

	public int compare(Appointment app0, Appointment app1) {
		return app0.getDate().compareTo(app1.getDate());
	}
	
}

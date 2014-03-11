package Classes;

public class AppointmentToPerson {

	//private final String userEmail;
	private final Person user;
	private final Appointment appoint;
	private boolean isGoing = false;
	private boolean isHidden = false;


	public AppointmentToPerson(Person user, Appointment appoint){
		this.user = user;
		this.appoint = appoint;
	}
	public void setNotGoing(){
		isGoing = true;
	}

	public void hideAppointment(){
		isHidden = true;
	}
}
package Classes;

public class AppointmentToPerson {

	//private final String userEmail;
	private final Person user;
	private final Appointment appoint;
	private boolean isGoing = false;
	private boolean isHidden = false;
	private boolean isOwner = false;

	public AppointmentToPerson(Person user, Appointment appoint){
		this.user = user;
		this.appoint = appoint;
		user.addatp(this);
	}
	public void setOwner(boolean isOwner){
		this.isOwner = isOwner;
	}
	public boolean getOwner(){
		return isOwner;
	}
	public void setIsGoing(boolean isGoing){
		this.isGoing = isGoing;
	}

	public void setHidden(boolean isHidden){
		this.isHidden = isHidden;
	}
	public Person getUser() {
		return user;
	}
	public Appointment getAppointment() {
		return appoint;
	}
	public boolean getIsGoing(){
		return isGoing;
	}
	public boolean getIsHidden(){
		return isHidden;
	}
}
package Classes;

public class Update {
	
	Alarm alarm;
	
	public void updateAppointment(Appointment app){
		alarm = new Alarm(app.getStarttime().YEAR, app.getStarttime().MONTH, app.getStarttime().DATE,
				app.getStarttime().HOUR_OF_DAY, app.getStarttime().MINUTE, app.getDescription());
	}

}

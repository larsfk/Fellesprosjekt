package Classes;

import java.util.Date;

public class MainProgramTesting {
	CalendarClient cc = new CalendarClient();
	Calendar cal1 = new Calendar();
	Calendar cal2 = new Calendar();
	Calendar cal3 = new Calendar();
	Date date1 = new Date();
	Date date2 = new Date();
	Date date3 = new Date();
	
	Appointment appoint1; 
			//int appID, Date stime, Date ftime, String meetpl, String descr, Date alarm){
	
	private void run() {
		date2.setHours(17);
		date3.setDate(5);
		appoint1 = new Appointment(1,date1,date2,"Hos Cox","Progging",date3);
		
		cal1.addAppointment(appoint1);
		
		cc.getAr().add(cal1);
		cc.getAr().add(cal2);
		cc.getAr().add(cal3);
	}
	public static void main(String[] args) {
		MainProgramTesting mpt = new MainProgramTesting();
		mpt.run();
	}
}

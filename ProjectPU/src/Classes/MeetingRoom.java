package Classes;

import java.util.ArrayList;
import java.util.Calendar;

public class MeetingRoom {

	private int ID;
	private int Capasity;
	private ArrayList<Appointment> AppointmentList = new ArrayList<Appointment>();

	public MeetingRoom(int ID, int Capasity){
		this.ID = ID;
		this.Capasity = Capasity;
		this.AppointmentList.equals(null);
	}

	public void addAppointment(Appointment Q){
		try{
			if (Q.getStarttime().get(Calendar.YEAR) == -1){
				throw new NullPointerException();
			}
			if (AppointmentList.size() == 0){
				AppointmentList.add(Q);
			}
			else{
				for(int i = 0; i < AppointmentList.size(); i++){
					if (Q.getStarttime().compareTo(AppointmentList.get(i).getStarttime()) < 0){
						AppointmentList.add(i, Q);
					}
				}		
			}
		}

		catch (NullPointerException e){
			System.out.println("AppointmentList har ingen startdato");
			e.printStackTrace();
		}
	}


	public void removeAppointment(int ID){
		for (int i = 0; i < AppointmentList.size(); i++){
			if (AppointmentList.get(i).getAppointmentID() == ID){
				AppointmentList.remove(i);
			}
		}
	}

	public boolean isFree(Calendar start, Calendar end){
		for (int i = 0; i < AppointmentList.size(); i++){
			if ((AppointmentList.get(i).getStarttime().compareTo(start) < 0 && AppointmentList.get(i).getFinishingtime().compareTo(end) > 0)
					|| (AppointmentList.get(i).getStarttime().compareTo(start) > 0 && AppointmentList.get(i).getStarttime().compareTo(end) < 0)
					|| (AppointmentList.get(i).getFinishingtime().compareTo(start) > 0 && AppointmentList.get(i).getFinishingtime().compareTo(end) < 0)
					|| (AppointmentList.get(i).getStarttime().compareTo(start) < 0 && AppointmentList.get(i).getFinishingtime().compareTo(end) < 0)
					|| (AppointmentList.get(i).getStarttime().compareTo(end) > 0 && AppointmentList.get(i).getFinishingtime().compareTo(end) > 0)){
				return false;
			}
		}

		return true;
	}

	public int getCapasity(){
		return Capasity;
	}

	public int getID(){
		return ID;
	}

	public ArrayList<Appointment> getAppointmentList() {
		System.out.println("Appointmentlist hentes");
		return AppointmentList;
	}

}

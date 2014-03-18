package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class MeetingRoom {

	private int ID;
	private int Capasity;
	private ArrayList<Appointment> AppointmentList = new ArrayList<Appointment>();
	private int length;
	Database db = new Database();

	public MeetingRoom(int ID, int Capasity){
		this.ID = ID;
		this.Capasity = Capasity;
		this.AppointmentList.add(0, null);
		length = 0;
	}

	public MeetingRoom(int ID, int Capasity, Connection conn){
		this.ID = ID;
		this.Capasity = Capasity;
		this.AppointmentList.add(0, null);
		length = 0;
		try{
			conn = db.getConnection();
			db.addToDatabase("INSERT INTO larsfkl_felles.meeting_room (room_id, capasity) VALUES (" + ID + " , " + Capasity + ");", conn);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void reserveRoom(Appointment Q){
		try{
			if (Q.getStarttime().get(Calendar.YEAR) == -1){
				throw new NullPointerException();
			}
			if (length == 0){
				AppointmentList.set(0, Q);
				length = 1;
			}
			else{
				AppointmentList.add(Q);	
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
		try {
			Connection conn = db.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT capasity FROM larsfkl_felles.meeting_room where capasity = '" + Capasity + "';");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			String name = rs.getString(1);
			rs.close();
			
			return Capasity;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public int getID(){
		try {
			Connection conn = db.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeQuery("SELECT room_id FROM larsfkl_felles.meeting_room where room_id = '" + ID + "';");
			ResultSet rs = stmt.getResultSet();
			
			rs.next();
			String name = rs.getString(1);
			rs.close();
			
			return ID;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public ArrayList<Appointment> getAppointmentList() {
		return AppointmentList;
	}



}

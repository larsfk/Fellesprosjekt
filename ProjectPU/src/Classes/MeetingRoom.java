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
	Database db = new Database();

	public MeetingRoom(int ID, int Capasity){
		this.ID = ID;
		this.Capasity = Capasity;
		this.AppointmentList.add(0, null);
	}

	public MeetingRoom(int ID, int Capasity, Connection conn){
		this.ID = ID;
		this.Capasity = Capasity;
		this.AppointmentList.add(0, null);
		try{
			conn = db.getConnection();
			db.addToDatabase("INSERT INTO larsfkl_felles.meeting_room (room_id, capasity) VALUES (" + ID + " , " + Capasity + ");", conn);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<Appointment> getAppointmentList(Connection conn){
		this.AppointmentList = db.getListOfAppointmentsInMeetingroom(this, conn);
		return this.AppointmentList;
	}

	public void removeAppointment(int ID){
		for (int i = 0; i < AppointmentList.size(); i++){
			if (AppointmentList.get(i).getAppointmentID() == ID){
				AppointmentList.remove(i);
			}
		}
	}

	public boolean isFree(Calendar start, Calendar end){ //Denne funker ikke
		System.out.println("Sjekker om rommet er ledig mellom " + db.convertCalendarTimeToSQLTime(start) + " og " + db.convertCalendarTimeToSQLTime(end));
		for (int i = 0; i < AppointmentList.size(); i++){
			if ((AppointmentList.get(i).getStarttime().compareTo(start) < 0 && AppointmentList.get(i).getFinishingtime().compareTo(end) > 0)
					|| (AppointmentList.get(i).getStarttime().compareTo(start) > 0 && AppointmentList.get(i).getStarttime().compareTo(end) < 0)
					|| (AppointmentList.get(i).getFinishingtime().compareTo(start) > 0 && AppointmentList.get(i).getFinishingtime().compareTo(end) < 0)
					|| (AppointmentList.get(i).getStarttime().compareTo(start) < 0 && AppointmentList.get(i).getFinishingtime().compareTo(end) < 0)
					|| (AppointmentList.get(i).getStarttime().compareTo(end) > 0 && AppointmentList.get(i).getFinishingtime().compareTo(end) > 0)){
				System.out.println("Rommet er ikke ledig mellom " + db.convertCalendarTimeToSQLTime(start) + " og " + db.convertCalendarTimeToSQLTime(end));
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
			conn.close();
			this.Capasity = Integer.parseInt(name);
			return Capasity;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public Integer getID(){
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
			return null;
		}
	}

	public ArrayList<Appointment> getAppointmentList(){
		Connection conn;
		try {
			conn = this.db.getConnection();
			ArrayList<Appointment> lol = db.getListOfAppointmentsInMeetingroom(this, conn);
			return lol;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}



}

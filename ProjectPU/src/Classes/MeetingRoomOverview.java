package Classes;

import java.util.ArrayList;
import java.util.Calendar;

public class MeetingRoomOverview {

	private ArrayList<MeetingRoom> MeetingRoomList = new ArrayList<MeetingRoom>();

	public MeetingRoomOverview(){
		this.MeetingRoomList = null;

	}
	
	public void addMeetingRoom(MeetingRoom M){
		MeetingRoomList.add(M);
	}
	
	public void removeMeetingRoom(int ID){
		for (int i = 0; i < MeetingRoomList.size(); i++){
			if (MeetingRoomList.get(i).getID() == ID){
				MeetingRoomList.remove(i);
			}
		}
	}
	
	public ArrayList<MeetingRoom> getFreeRooms(Calendar start, Calendar end){
		ArrayList<MeetingRoom> FreeRoomList = new ArrayList<MeetingRoom>();
		for (int i = 0; i < MeetingRoomList.size(); i++){
			MeetingRoom room = MeetingRoomList.get(i);
			if (room.isFree(start, end)){
				FreeRoomList.add(room);
			}
		}
		return FreeRoomList;
	}
	
}

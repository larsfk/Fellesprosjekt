package Classes;

import java.util.ArrayList;
import java.util.Date;

public class MeetingRoomOverview {

	private ArrayList<MeetingRoom> MeetingRoomList = new ArrayList<MeetingRoom>();
	
	public MeetingRoomOverview(){
		MeetingRoomList = null;
	}
	
	public void addMeetingRoom(MeetingRoom M){
		MeetingRoomList.add(M);
	}
	
	public ArrayList<MeetingRoom> getFreeRooms(Date start, Date end){
		
	}
	
}

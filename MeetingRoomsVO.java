package MeetingReservationModel;

public class MeetingRoomsVO {
	int meeting_room_id;
	int meeting_capacity;
	public int getMeeting_room_id() {
		return meeting_room_id;
	}
	public void setMeeting_room_id(int meeting_room_id) {
		this.meeting_room_id = meeting_room_id;
	}
	public int getMeeting_capacity() {
		return meeting_capacity;
	}
	public void setMeeting_capacity(int meeting_caracity) {
		this.meeting_capacity = meeting_caracity;
	}
	public MeetingRoomsVO(int meeting_room_id, int meeting_caracity) {
		super();
		this.meeting_room_id = meeting_room_id;
		this.meeting_capacity = meeting_caracity;
	}
	public MeetingRoomsVO() {
		super();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ȸ�ǽ� ���� [meeting_room_id=").append(meeting_room_id).append(", meeting_caracity=")
				.append(meeting_capacity).append("]");
		return builder.toString();
	}

}

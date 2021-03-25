package MeetingReservationModel;

import java.sql.Date;

public class MeetingVO {
	int meeting_room_id;
	String meeting_name;
	Date meeting_date;
	int meeting_start_time;
	int meeting_end_time;
	int meeting_attendee;
	int meeting_priority;

	public int getMeeting_attendee() {
		return meeting_attendee;
	}
	public void setMeeting_attendee(int meeting_attendee) {
		this.meeting_attendee = meeting_attendee;
	}
	public int getMeeting_priority() {
		return meeting_priority;
	}
	public void setMeeting_priority(int meeting_priority) {
		this.meeting_priority = meeting_priority;
	}
	public int getMeeting_room_id() {
		return meeting_room_id;
	}
	public void setMeeting_room_id(int meeting_room_id) {
		this.meeting_room_id = meeting_room_id;
	}
	public String getMeeting_name() {
		return meeting_name;
	}
	public void setMeeting_name(String meeting_name) {
		this.meeting_name = meeting_name;
	}
	public Date getMeeting_date() {
		return meeting_date;
	}
	public void setMeeting_date(Date meeting_date) {
		this.meeting_date = meeting_date;
	}
	public int getMeeting_start_time() {
		return meeting_start_time;
	}
	public void setMeeting_start_time(int meeting_start_time) {
		this.meeting_start_time = meeting_start_time;
	}
	public int getMeeting_end_time() {
		return meeting_end_time;
	}
	public void setMeeting_end_time(int meeting_end_time) {
		this.meeting_end_time = meeting_end_time;
	}
	public MeetingVO(int meeting_room_id, String meeting_name, Date meeting_date, int meeting_start_time,
			int meeting_end_time, int meeting_attendee, int meeting_priority) {
		super();
		this.meeting_room_id = meeting_room_id;
		this.meeting_name = meeting_name;
		this.meeting_date = meeting_date;
		this.meeting_start_time = meeting_start_time;
		this.meeting_end_time = meeting_end_time;
		this.meeting_attendee = meeting_attendee;
		this.meeting_priority = meeting_priority;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("회의정보  [meeting_room_id=").append(meeting_room_id).append(", meeting_name=")
				.append(meeting_name).append(", meeting_date=").append(meeting_date).append(", meeting_start_time=")
				.append(meeting_start_time).append(", meeting_end_time=").append(meeting_end_time)
				.append(", meeting_attendee=").append(meeting_attendee).append(", meeting_priority=")
				.append(meeting_priority).append("]");
		return builder.toString();
	}
	public MeetingVO() {
		super();
	}
	
	
}

package MeetingReservationModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import MeetingReservationUtil.Util;
import MeetingReservationView.MeetingBookView;
import utill.DBUtill;

public class MeetingDAO {
	public MeetingDAO() {
		super();
	}

	static Connection conn;
	static PreparedStatement ps;
	static Statement st;
	static ResultSet rs;

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Scanner sc = new Scanner(System.in);
	private MeetingVO met = new MeetingVO();

	public void admin_time_management() throws SQLException, ParseException {
		MeetingBookView vi = new MeetingBookView();

		boolean on = true;

		while (on) {
			System.out.println("*******관리자 모드 입니다*******");
			System.out.println("**********메뉴 선택**********");
			System.out.println("1. 회의 삭제");
			System.out.println("2. 회의 정보 수정");
			System.out.println("3. 회의 보기");
			System.out.println("9. 나가기");
			int work = sc.nextInt();

			switch (work) {

			case 1:
				vi.showme(met_delete());
				break;

			case 2:
				vi.showme(met_modify());
				break;

			case 3:
				System.out.println("조회할 회의의 이름을 입력하세요");
				System.out.print("이름 : ");
				met_search(sc.next());

				break;

			case 9:
				on = false;

			default:
				break;
			}
		}

	}

	public void met_search(String name) throws SQLException {
		String sql = "select meeting.meeting_room_id, meeting_name, "
				+ " meeting_start_time, meeting_end_time, meeting_attendee, " + " meeting_capacity, meeting_priority"
				+ " from meeting_rooms join meeting on " + " meeting.meeting_room_id = meeting_rooms.meeting_room_id"
				+ " where meeting.meeting_name = ? ";

		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		rs = ps.executeQuery();
		while (rs.next()) {
			int room_id = rs.getInt(1);
			String room_name = rs.getString(2);
			String s_time = rs.getString(3);
			String e_time = rs.getString(4);
			int a = rs.getInt(5);
			int c = rs.getInt(6);
			int t = rs.getInt(7);
			System.out.println(
					room_id + "\t" + room_name + "\t" + s_time + "\t" + e_time + "\t" + a + "\t" + c + "\t" + t);
		}

		DBUtill.dbClose(rs, ps, conn);
	}

	private int met_modify() throws SQLException, ParseException {
		int result = 0;

		System.out.println("수정할 회의이름을 정확히 입력하세요");
		System.out.print("이름 : ");
		met.setMeeting_name(sc.next());
		System.out.println("회의실을 정해주세요");
		System.out.print("회의실 id : ");
		met.setMeeting_room_id(sc.nextInt());
		System.out.print("회의날짜 yymmdd: ");
		String date = sc.next();
		SimpleDateFormat b = new SimpleDateFormat("yymmdd");
		SimpleDateFormat a = new SimpleDateFormat("yy-mm-dd");
		java.util.Date tempDate = null;
		tempDate = b.parse(date);
		String transDate = a.format(tempDate);
		java.sql.Date d = java.sql.Date.valueOf(transDate);
		met.setMeeting_date(d);
		System.out.print("시작시간 : ");
		met.setMeeting_start_time(sc.nextInt());
		System.out.print("종료시간 : ");
		met.setMeeting_end_time(sc.nextInt());
		System.out.print("참석인원 : ");
		met.setMeeting_attendee(sc.nextInt());
		System.out.print("우선순위: ");
		met.setMeeting_priority(sc.nextInt());

		String sql = "update Meeting" + " set meeting_room_id = ?, " + " meeting_room_date = ?,"
				+ " meeting_start_time = ?," + " meeting_end_time = ?," + " meeting_attendee = ?,"
				+ " meeting_priority = ?" + " where meeting_name = ?";
		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, met.getMeeting_room_id());
		ps.setDate(2, met.getMeeting_date());
		ps.setDouble(3, met.getMeeting_start_time());
		ps.setDouble(4, met.getMeeting_end_time());
		ps.setInt(5, met.getMeeting_attendee());
		ps.setInt(6, met.getMeeting_priority());
		ps.setString(7, met.getMeeting_name());

		result = ps.executeUpdate();
		Util.dbClose(rs, ps, conn);
		return result;
	}

	private int met_delete() throws SQLException {
		int result = 0;
		System.out.print("삭제할 회의 이름 입력 : ");
		String name = sc.next();

		String sql = "delete from Meeting where meeting_name = ?";
		conn = Util.getConnection();

		ps = conn.prepareStatement(sql);
		ps.setString(1, name);

		result = ps.executeUpdate();
		Util.dbClose(rs, ps, conn);
		return result;
	}

}

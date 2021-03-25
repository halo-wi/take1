package MeetingReservationModel;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import MeetingReservationUtil.Util;
import MeetingReservationView.MeetingBookView;
import utill.DBUtill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MeetingRoomsDAO {
	static Connection conn;
	static PreparedStatement ps;
	static Statement st;
	static ResultSet rs;

	public MeetingRoomsDAO() {
		super();
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Scanner sc = new Scanner(System.in);
	private MeetingRoomsVO room = new MeetingRoomsVO();

	public void admin_room_management() throws SQLException {
		MeetingBookView vi = new MeetingBookView();

		boolean on = true;

		while (on) {
			System.out.println("*******관리자 모드 입니다*******");
			System.out.println("**********메뉴 선택**********");
			System.out.println("1. 회의실 등록");
			System.out.println("2. 회의실 삭제");
			System.out.println("3. 회의실 정보 수정");
			System.out.println("4. 회의실 검색");
			System.out.println("5. 회의실 현황");
			System.out.println("9. 나가기");
			int work = sc.nextInt();

			switch (work) {

			case 1:
				vi.showme(room_insert());

				break;

			case 2:
				vi.showme(room_delete());
				break;

			case 3:
				vi.showme(room_modify());
				break;

			case 4:
				System.out.println("조회할 회의실 ID을 입력하세요");
				System.out.print("이름 : ");
				room_search(sc.nextInt());

				break;

			case 5:
				room_Allsearch();

				break;

			case 9:
				on = false;

			default:
				break;
			}
		}

	}

	public static void room_Allsearch() throws SQLException {
		String sql = "select meeting.meeting_room_id, meeting_name, "
				+ " meeting_start_time, meeting_end_time, meeting_attendee, " + " meeting_capacity, meeting_priority"
				+ " from meeting_rooms join meeting on " + " meeting.meeting_room_id = meeting_rooms.meeting_room_id";

		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);

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

	public void room_search(int id) throws SQLException {
		
		String sql = "select * from employees where employee_name = ?";
		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
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

	private int room_modify() throws SQLException {
		int result = 0;

		System.out.println("수정할 회의실 ID를 입력하세요");
		System.out.print("ID : ");
		room.setMeeting_room_id(sc.nextInt());
		System.out.println("수정할 회의실 정원을 입력하세요");
		System.out.print("정원 : ");
		room.setMeeting_capacity(sc.nextInt());

		String sql = "update MeetingRooms " + " set meeting_capacity = ? " + " where meeting_room_id = ?";
		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, room.getMeeting_capacity());
		ps.setInt(2, room.getMeeting_room_id());

		result = ps.executeUpdate();
		Util.dbClose(rs, ps, conn);
		return result;
	}

	private int room_delete() throws SQLException {
		int result = 0;
		System.out.print("삭제할 회의실 ID 입력 : ");
		String id = sc.next();

		String sql = "delete from MeetingRooms where meeting_room_id = ?";
		conn = Util.getConnection();

		ps = conn.prepareStatement(sql);
		ps.setString(1, id);

		result = ps.executeUpdate();
		Util.dbClose(rs, ps, conn);
		return result;
	}

	private int room_insert() throws SQLException {
		System.out.println("회의실 생성 메뉴입니다.");
		System.out.println("회의실 ID를 입력하세요");
		System.out.print("ID : ");
		room.setMeeting_room_id(sc.nextInt());
		System.out.println("회의실 정원을 입력하세요");
		System.out.print("정원 : ");
		room.setMeeting_capacity(sc.nextInt());

		int result = 0;
		String sql = "insert into MeetingRooms values(?,?)";
		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);

		ps.setInt(1, room.getMeeting_room_id());
		ps.setInt(2, room.getMeeting_capacity());
		result = ps.executeUpdate();

		Util.dbClose(null, ps, conn);

		return result;
	}

	public int capacity() throws SQLException {
		
		int num = 0;
		
		Statement st = null;
		ResultSet rs = null;

		String sql = "select max(meeting_capacity) from meeting_rooms";

		Connection conn = Util.getConnection();
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		while (rs.next()) {
			num = rs.getInt(1);
		}

		Util.dbClose(rs, st, conn);

		return num;
	}
}

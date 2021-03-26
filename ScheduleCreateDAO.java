package MeetingReservationModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import MeetingReservationUtil.LoggableStatement;
import MeetingReservationUtil.Util;
import model.BoardVO;
import utill.DBUtill;
import view.BoardView;

public class ScheduleCreateDAO {
	static Connection conn;
	static PreparedStatement ps;
	static Statement st;
	static ResultSet rs;
	static ScheduleCal scc = new ScheduleCal();
	static MeetingRoomsVO room = new MeetingRoomsVO();
	static MeetingRoomsDAO rom = new MeetingRoomsDAO();

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Scanner sc = new Scanner(System.in);

	public static int creat_meeting() throws IOException, SQLException {

		System.out.print("������ ȸ�� �̸��� �Է��ϼ���");
		String name = br.readLine();
		System.out.println("�������� ����� �Է��ϼ���");
		System.out.print("�Է����� ��� ��� (����� ����), �Է¿Ϸ��� ����");
		String emp_ids = br.readLine();
		emp_ids += " ";
		if (emp_ids.length() % 6 != 0) {
			System.out.println("����� �ùٸ��� ����");
			return 0;
		}
		int meeting_num = (emp_ids.length() / 6);
		if (meeting_num > rom.capacity()) {
			System.out.print("�ִ� �ο� �ʰ� ");
			return 0;
		}
		System.out.println("���ϴ� ��¥�� �Է��ϼ���");
		System.out.print("yy/mm/dd");
		String date = br.readLine();

		System.out.println("���ϴ� ���� �ð��� �Է��ϼ���");
		double stime = sc.nextDouble();
		System.out.println("���ϴ� ���� �ð��� �Է��ϼ���");
		double etime = sc.nextDouble();

		for (Integer a : capa_check(meeting_num)) {
			if (occupaied(a, date, stime, etime) == 0) {
				int x = a;
				reservation(a, name, date, stime, etime, meeting_num, emp_ids);
				return 1;
			}
		}
		// if(scc.priority(emp_ids) <) {}
		return 0;

	}

	public static int reservation(Integer a, String name, String date, double stime, double etime, int meeting_num,
			String emp_ids) throws SQLException {

		int result;

		String sql = "insert into meeting values(?,?,?,?,?,?,?)";

		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, a);
		ps.setString(2, name);
		ps.setString(3, date);
		ps.setDouble(4, stime);
		ps.setDouble(5, etime);
		ps.setInt(6, meeting_num);
		ps.setInt(7, scc.priority(emp_ids));
		result = ps.executeUpdate();

		Util.dbClose(null, ps, conn);

		return result;

	}

	public static int occupaied(Integer a, String date, double stime, double etime) throws SQLException {

		int res = 2;

		String sql = "select * from meeting where Meeting_room_id = ? and "
				+ " meeting_room_date = ? and (meeting_start_time between ? and ? )";

		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, a);
		ps.setString(2, date);
		ps.setDouble(3, stime);
		ps.setDouble(4, etime);
		rs = ps.executeQuery();
		res = ps.executeUpdate();

		Util.dbClose(rs, ps, conn);

		return res;

	}

	public static List<Integer> capa_check(int meeting_num) throws SQLException {
		List<Integer> res = new ArrayList<>();

		String sql = "select * from meeting_rooms " + " where meeting_capacity >= ? order by meeting_capacity asc";

		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, meeting_num);
		rs = ps.executeQuery();
		while (rs.next()) {
			res.add(rs.getInt("meeting_room_id"));
		}

		Util.dbClose(rs, st, conn);

		return res;

	}

}

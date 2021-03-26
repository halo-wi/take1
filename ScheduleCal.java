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

import MeetingReservationUtil.LoggableStatement;
import MeetingReservationUtil.Util;
import model.BoardVO;
import view.BoardView;

public class ScheduleCal {
	static Connection conn;
	static PreparedStatement ps;
	static Statement st;
	static ResultSet rs;

	public ScheduleCal() {
		super();
	}

	public String trans(String input) {
		String result = "";

		int a = 0;
		String s = "";
		int count = 0;
		int[] x = new int[6];
		for (int i = 0; i < 15; i++) {
			char b = input.charAt(i);

			if (b == ' ' || b == ':' || b == '~') {
				a = Integer.parseInt(s);
				s = "";
				b = '0';
				x[count] = a;
				count++;
			}
			s += b;
		}

		double startT = 9;
		double endT = 18;

		if (startT <= x[0] + (x[1] / 60)) {
			startT = x[0] + (x[1] / 60);
		}

		if (endT >= x[4] + (x[5] / 60)) {
			endT = x[4] + (x[5] / 60);
		}

		if (startT > endT) {

			return "시간입력 오류";
		}

		else {

		}

		result = "" + startT + " ~ " + endT;

		return result;

	}

	public int emp_num_cal(String emp_ids) {
		int count = 0;
		String s = "";
		String b = "";
		emp_ids += " ";
		String[] x = new String[emp_ids.length() / 6];
		for (int i = 0; i < emp_ids.length(); i++) {
			char a = emp_ids.charAt(i);

			if (a == ' ' || a == ',') {
				b = s;
				s = "";
				a = '\u0000';
				x[count] = b;
				count++;
			}
			s += a;
		}
		return count;

	}

	public List<String> attendee_name(String emp_ids) {

		String s = "";
		String b = "";
		emp_ids += " ";
		List<String> x = new ArrayList<String>();
		for (int i = 0; i < emp_ids.length(); i++) {
			char a = emp_ids.charAt(i);

			if (a == ' ' || a == ',') {
				b = s;
				s = "";
				a = '\u0000';
				x.add(b);
			}
			s += a;
			if(a=='\u0000') s="";
		}
		return x;

	}

	public int priority(String emp_ids) throws SQLException {
		int priority = 9;
		List<Integer> pr = new ArrayList<>();
		List<String> x = attendee_name(emp_ids);
		String s = sy(x);

		String sql = "select employee_priority from employees " + "where employee_id in(?) ";
		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, s);
		rs = ps.executeQuery();
		while (rs.next()) {
			pr.add(rs.getInt(1));
		}
		
		for (Integer a :pr) {
			if(priority <= a) {
				priority = a;
			}
		}
		

		return priority;
	}

	public String sy(List<String> x) {
		String res = "";
		for (String s :x) {
			res += s+"','";
			
		}
		return res+0000;

	}

}

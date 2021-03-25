package MeetingReservationModel;

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
import MeetingReservationUtil.Util;
import MeetingReservationView.MeetingBookView;
import model.BoardVO;
import utill.DBUtill;
import view.BoardView;

public class EmployeesDAO {

	static Connection conn;
	static PreparedStatement ps;
	static Statement st;
	static ResultSet rs;

	public EmployeesDAO() {
		super();
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Scanner sc = new Scanner(System.in);
	private EmployeesVO emp = new EmployeesVO();

	public void admin_emp_management() throws SQLException, IOException {
		MeetingBookView vi = new MeetingBookView();

		boolean on = true;

		while (on) {
			System.out.println("*******������ ��� �Դϴ�*******");
			System.out.println("**********�޴� ����**********");
			System.out.println("1. �ű� ���� ���");
			System.out.println("2. ���� ���� ����");
			System.out.println("3. ���� ���� ����");
			System.out.println("4. ���� ���� �˻�");
			System.out.println("9. ������");
			int work = sc.nextInt();

			switch (work) {

			case 1:
				vi.showme(emp_insert());

				break;

			case 2:
				vi.showme(emp_delete());
				break;

			case 3:
				vi.showme(emp_modify());
				break;

			case 4:
				System.out.println("��ȸ�� ������ �̸��� �Է��ϼ���");
				System.out.print("�̸� : ");
				System.out.println(emp_search(sc.next()));

				break;

			case 9:
				on = false;

			default:
				break;
			}
		}

	}

	private List<EmployeesVO> emp_search(String name) throws SQLException {

		List<EmployeesVO> result = new ArrayList<>();

		String sql = "select * from employees where employee_name = ?";

		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		rs = ps.executeQuery();
		while (rs.next()) {
			emp.setEmployee_id(rs.getString("Employee_id"));
			emp.setEmployee_name(rs.getString("Employee_name"));
			emp.setDepartment_id(rs.getInt("Department_id"));
			emp.setEmployee_position(rs.getString("Employee_position"));
			emp.setEmployee_priority(rs.getInt("Employee_priority"));
			emp.setEmployee_pw(rs.getString("Employee_pw"));

			result.add(emp);
		}
		DBUtill.dbClose(rs, ps, conn);

		return result;

	}

	public int emp_modify() throws SQLException {
		int result = 0;

		System.out.println("������ ������ ����� �Է��ϼ���");
		System.out.print("��� : ");
		emp.setEmployee_id(sc.next());
		System.out.println("������ ������ �̸��� �Է��ϼ���");
		System.out.print("�̸� : ");
		emp.setEmployee_name(sc.next());
		System.out.println("������ ������ ������ �Է��ϼ���");
		System.out.print("���� : ");
		String emp_position = sc.next();
		emp.setEmployee_position(emp_position);
		emp.setEmployee_priority(employee_priority(emp_position));
		System.out.println("������ ������ ��й�ȣ�� �Է��ϼ���");
		System.out.print("��� : ");
		emp.setEmployee_pw(sc.next());
		System.out.println("������ ������ �μ�_id�� �Է��ϼ���");
		System.out.print("��� : ");
		emp.setDepartment_id(sc.nextInt());

		String sql = "update employees " + " set employee_priority = ?, " + " employee_name = ?,"
				+ " employee_position = ?, " + " employee_pw = ?, " + " department_id= ?" + " where employee_id = ?";
		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, emp.getEmployee_priority());
		ps.setString(2, emp.getEmployee_name());
		ps.setString(3, emp.getEmployee_position());
		ps.setString(4, emp.getEmployee_pw());
		ps.setInt(5, emp.getDepartment_id());
		ps.setString(6, emp.getEmployee_id());
		result = ps.executeUpdate();
		Util.dbClose(rs, ps, conn);
		return result;
	}

	private int emp_delete() throws SQLException {
		int result = 0;
		System.out.print("������ ID �Է� : ");
		String id = sc.next();

		String sql = "delete from employees where employee_id = ?";
		conn = Util.getConnection();

		ps = conn.prepareStatement(sql);
		ps.setString(1, id);

		result = ps.executeUpdate();
		Util.dbClose(rs, ps, conn);
		return result;
	}

	public int emp_insert() throws IOException, SQLException {
		System.out.println("���� ID ���� �޴��Դϴ�.");
		System.out.print("�̸� �Է� : ");
		emp.setEmployee_name(br.readLine());
		System.out.print("�� PW �Է� : ");
		emp.setEmployee_pw(br.readLine());
		System.out.print("���� �Է� : ");
		String new_position = br.readLine();
		emp.setEmployee_position(new_position);
		emp.setEmployee_priority(employee_priority(new_position));
		System.out.print("�μ� ID �Է� : ");
		emp.setDepartment_id(sc.nextInt());
		emp.setEmployee_id(emp_id_create());

		int result = 0;
		String sql = "insert into employees values(?,?,?,?,?,?)";
		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, emp.getEmployee_id());
		ps.setInt(2, emp.getEmployee_priority());
		ps.setString(3, emp.getEmployee_name());
		ps.setString(5, emp.getEmployee_pw());
		ps.setString(4, new_position);
		ps.setInt(6, emp.getDepartment_id());

		result = ps.executeUpdate();

		Util.dbClose(null, ps, conn);

		return result;
	}

	public static int emp_login(String id, String pw) {

		int result = 0;

		String sql = "select employee_pw from employees where employee_id = ?";

		conn = Util.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(pw)) {
					result = 1;
				} else {
					System.out.println("��й�ȣ ����");
					result = 0;
				}
			} else {
				System.out.println("ID�� �������� �ʽ��ϴ�");
				result = 0;
			}

			Util.dbClose(ps, conn, rs);
		} catch (SQLException e) {
			System.out.println("�� �� ���� ���� �߻�");
			result = 0;
		}

		return result;

	}

	public int employee_priority(String position) {
		int pri = 9;
		switch (position) {
		case "�Ѱ�����":
			pri = 0;
			break;
		case "�λ���":
			pri = 1;
			break;
		case "����":
			pri = 2;
			break;
		case "�̻�":
			pri = 3;
			break;
		case "�̻纸":
			pri = 4;
			break;
		case "����":
			pri = 5;
			break;
		case "����":
			pri = 6;
			break;
		case "����":
			pri = 7;
			break;
		case "�븮":
			pri = 8;
			break;
		case "���":
			pri = 9;
			break;
		}
		return pri;

	}

	public String emp_id_create() throws SQLException {
		String id = "";
		String s = "";
		Date d = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yy");
		s = transFormat.format(d);
		int a = Integer.parseInt(s);
		if (a <= 60) {
			char c = s.charAt(0);
			c += 49;
			id += "" + c + s.charAt(1);
		}

		String sql = "select employee_id from employees";
		conn = Util.getConnection();
		st = conn.createStatement();
		rs = st.executeQuery(sql);

		boolean duplication = true;
		while (duplication) {
			id += Integer.toString((int) (Math.random() * 1000));
			while (rs.next()) {
				if (!id.equals(emp.getEmployee_id()))
					duplication = false;
			}
		}

		return id;
	}

}

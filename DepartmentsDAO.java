package MeetingReservationModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

import MeetingReservationUtil.Util;
import MeetingReservationView.MeetingBookView;

public class DepartmentsDAO {

	public DepartmentsDAO() {
		super();
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Scanner sc = new Scanner(System.in);
	private DepartmentsVO dept = new DepartmentsVO();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public void admin_dept_management() throws SQLException {
		MeetingBookView vi = new MeetingBookView();

		boolean on = true;

		while (on) {
			System.out.println("*******������ ��� �Դϴ�*******");
			System.out.println("**********�޴� ����**********");
			System.out.println("1. �ű� �μ� ���");
			System.out.println("2. �μ� ���� ����");
			System.out.println("3. �μ� ���� ����");
			System.out.println("9. ������");
			int work = sc.nextInt();

			switch (work) {

			case 1:
				vi.showme(dept_insert());

				break;

			case 2:
				System.out.println("�μ����� ���� �޴��Դϴ�");
				vi.showme(dept_delete());
				break;

			case 3:
				vi.showme(dept_modify());
				break;

			case 9:
				on = false;

			default:
				break;
			}
		}

	}

	public int dept_modify() throws SQLException {
		int result = 0;
		int num = 0;
		System.out.println("������ �μ��� id�� �Է��ϼ���");
		System.out.print("id : ");
		dept.setDepartment_id(sc.nextInt());
		System.out.println("�μ��� ����� �̸��� �Է��ϼ���");
		System.out.print("�μ��̸� : ");
		dept.setDepartment_name(sc.next());

		String sql1 = "select count(employee_id) from employees where department_id = ?";
		String sql2 = "update departments set department_name = ?, department_num = ? where department_id = ?";

		conn = Util.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, dept.getDepartment_id());
		rs = ps.executeQuery();
		while (rs.next()) {
			num = rs.getInt(1);
		}
		;
		ps = conn.prepareStatement(sql2);
		ps.setString(1, dept.getDepartment_name());
		ps.setInt(2, num);
		ps.setInt(3, dept.getDepartment_id());
		result = ps.executeUpdate();
		Util.dbClose(rs, ps, conn);
		return result;

	}

	private int dept_delete() throws SQLException {

		int result = 0;
		System.out.print("������ ID �Է� : ");
		String id = sc.next();

		String sql = "delete from departments where department_id = ?";
		conn = Util.getConnection();

		ps = conn.prepareStatement(sql);
		ps.setString(1, id);

		result = ps.executeUpdate();
		Util.dbClose(rs, ps, conn);
		return result;

	}

	private int dept_insert() throws SQLException {
		System.out.println("�μ� ID ���� �޴��Դϴ�.");
		System.out.print("�̸� �Է� : ");
		dept.setDepartment_id(sc.nextInt());
		System.out.print("�μ� �ο� : ");
		dept.setDepartment_num(sc.nextInt());
		System.out.print("�μ� �̸� : ");
		dept.setDepartment_name(sc.next());

		int result = 0;
		String sql = "insert into departments values(?,?,?)";
		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, dept.getDepartment_id());
		ps.setInt(2, dept.getDepartment_num());
		ps.setString(3, dept.getDepartment_name());

		result = ps.executeUpdate();

		Util.dbClose(null, ps, conn);

		return result;

	}

}

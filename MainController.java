package MeetingReservationController;

import MeetingReservationModel.AdminsDAO;
import MeetingReservationModel.AdminsVO;
import MeetingReservationModel.DepartmentsDAO;
import MeetingReservationModel.DepartmentsVO;
import MeetingReservationModel.EmployeesDAO;
import MeetingReservationModel.EmployeesVO;
import MeetingReservationModel.MeetingRoomsDAO;
import MeetingReservationModel.MeetingRoomsVO;
import MeetingReservationModel.MeetingDAO;
import MeetingReservationModel.MeetingVO;
import MeetingReservationModel.ScheduleCreateDAO;
import MeetingReservationView.MeetingBookView;


import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class MainController {

	public static void main(String[] args) throws SQLException, IOException, ParseException {
		int result = 0;

		MeetingBookView vi = new MeetingBookView();
		Scanner sc = new Scanner(System.in);

		AdminsDAO adminDAO = new AdminsDAO();
		DepartmentsDAO departmentDAO = new DepartmentsDAO();
		EmployeesDAO employeeDAO = new EmployeesDAO();
		MeetingDAO reservationDAO = new MeetingDAO();
		MeetingDAO timeDAO = new MeetingDAO();
		MeetingRoomsDAO roomDAO = new MeetingRoomsDAO();

		AdminsVO admin = null;
		DepartmentsVO dept = null;
		EmployeesVO emp = null;
		MeetingVO met = null;
		MeetingVO time = null;
		MeetingRoomsVO room = null;

		boolean on = true;

		while (on) {
			System.out.println("******ȸ�ǽ� ���� ���α׷�******");
			System.out.println("**********�޴� ����**********");
			System.out.println("1. ���� �α���");
			System.out.println("9. ����");
			System.out.println("0. ������ �α���");
			int work = sc.nextInt();
			if (work == 0) {
				System.out.println("������ �α��� �޴��Դϴ�.");
				System.out.print("������ ID �Է� : ");
				String id;
				id = sc.next();
				System.out.print("������ PW �Է� : ");
				String pw;
				pw = sc.next();
				int i = adminDAO.ad_login(id, pw);
				vi.showLoginResult(i);
				if (i == 1) {
					adminDAO.adminMode();
				}
			}
			if (work == 1) {
				System.out.println("�α��� �޴��Դϴ�.");
				System.out.print("��� �Է� : ");
				String id;
				id = sc.next();
				System.out.print("PW �Է� : ");
				String pw;
				pw = sc.next();
				int i = EmployeesDAO.emp_login(id, pw);
				vi.showLoginResult(i);
				if (i == 1) {
					System.out.println("**********�޴� ����**********");
					System.out.println("1. ȸ�� ������ ����");
					System.out.println("2. �ű� ȸ�� ����");
					int choice = sc.nextInt();
					if (choice == 1)
						MeetingRoomsDAO.room_Allsearch();
					if (choice == 2)
						vi.showme(ScheduleCreateDAO.creat_meeting());
				}
			if (work == 9) System.exit(0);
			}

		}

	}
}

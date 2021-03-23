package MeetingReservationController;

import MeetingReservationModel.AdminsDAO;
import MeetingReservationModel.AdminsVO;
import MeetingReservationModel.DepartmentsDAO;
import MeetingReservationModel.DepartmentsVO;
import MeetingReservationModel.EmployeesDAO;
import MeetingReservationModel.EmployeesVO;
import MeetingReservationModel.MeetingReservationDAO;
import MeetingReservationModel.MeetingReservationVO;
import MeetingReservationModel.MeetingRoomsDAO;
import MeetingReservationModel.MeetingRoomsVO;
import MeetingReservationModel.MeetingTimeDAO;
import MeetingReservationModel.MeetingTimeVO;
import MeetingReservationView.MeetingBookView;
import model.BoardVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MainController {

	public static void main(String[] args) throws SQLException, IOException {
		int result = 0;

		MeetingBookView vi = new MeetingBookView();
		Scanner sc = new Scanner(System.in);

		AdminsDAO adminDAO = new AdminsDAO();
		DepartmentsDAO departmentDAO = new DepartmentsDAO();
		EmployeesDAO employeeDAO = new EmployeesDAO();
		MeetingReservationDAO reservationDAO = new MeetingReservationDAO();
		MeetingTimeDAO timeDAO = new MeetingTimeDAO();
		MeetingRoomsDAO roomDAO = new MeetingRoomsDAO();

		AdminsVO admin = null;
		DepartmentsVO dept = null;
		EmployeesVO emp = null;
		MeetingReservationVO reservation = null;
		MeetingTimeVO time = null;
		MeetingRoomsVO room = null;

		boolean on = true;

		while (on) {
			System.out.println("****회의실 자동 예약 프로그램****");
			System.out.println("**********메뉴 선택**********");
			System.out.println("1. 직원 로그인");
			System.out.println("9. 종료");
			System.out.println("0. 관리자 로그인");
			int work = sc.nextInt();
			if (work == 0) {
				System.out.println("관리자 로그인 메뉴입니다.");
				System.out.print("관리자 ID 입력 : ");
				String id;
				id = sc.next();
				System.out.print("관리자 PW 입력 : ");
				String pw;
				pw = sc.next();
				int i = adminDAO.ad_login(id, pw);
				vi.showLoginResult(i);
				if (i == 1) {
					adminDAO.adminMode();
				}
			}
			if (work == 1) {
				System.out.println("로그인 메뉴입니다.");
				System.out.print("사번 입력 : ");
				String id;
				id = sc.next();
				System.out.print("PW 입력 : ");
				String pw;
				pw = sc.next();
				int i = EmployeesDAO.emp_login(id, pw);
				vi.showLoginResult(i);
				if (i == 1) {
					System.out.println("**********메뉴 선택**********");
					System.out.println("1. 회의 스케쥴 입력");
					System.out.println("2. 신규 회의 생성");
					int choice = sc.nextInt();
					if (choice == 1)
						employeeDAO.schdule();
					if (choice == 2)
						employeeDAO.creat_meeting();
				}
			if (work == 9) System.exit(0);
			}

		}

	}
}

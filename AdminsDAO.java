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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import MeetingReservationUtil.Util;
import MeetingReservationView.MeetingBookView;
import model.BoardVO;
import utill.DBUtill;
import view.BoardView;

public class AdminsDAO {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Scanner sc = new Scanner(System.in);

	ResultSet rs = null;
	PreparedStatement ps = null;
	Connection conn = null;
	Statement st = null;

	private AdminsVO admin = new AdminsVO();

	public AdminsDAO() {

	}

	public int adminMode() throws SQLException, IOException {
		int result = 0;

		MeetingBookView vi = new MeetingBookView();

		AdminsDAO adminDAO = new AdminsDAO();
		AdminsVO admin = null;

		boolean on = true;

		while (on) {
			System.out.println("*******관리자 모드 입니다*******");
			System.out.println("**********메뉴 선택**********");
			System.out.println("1. 부서 테이블 관리");
			System.out.println("2. 임직원 테이블 관리");
			System.out.println("3. 회의실 관리");
			System.out.println("4. 회의실 이용시간 관리");
			System.out.println("5. 회의 생성관리");
			System.out.println("9. 나가기");
			System.out.println("0. 관리자 테이블 관리");
			int work = sc.nextInt();

			switch (work) {

			case 0:
				System.out.println(checkadmin());
				System.out.println("1. 관리자 계정 생성");
				System.out.println("2. 관리자 계정 삭제");
				int choice = sc.nextInt();
				if (choice == 1) {
					if (ad_insert()>=0)
						System.out.println("생성성공");
					else System.out.println("실패");
				}
				if (choice == 2) {
					if (ad_delete()>=0)
						System.out.println("삭제성공");
					else System.out.println("실패");
				}

				break;

			case 1:
				DepartmentsDAO dept = new DepartmentsDAO();
				dept.admin_dept_management();
				break;

			case 2:

				EmployeesDAO emp = new EmployeesDAO();
				emp.admin_emp_management();
				break;

			case 3:
				MeetingRoomsDAO room = new MeetingRoomsDAO();
				room.admin_room_management();
				break;

			case 4:
				MeetingTimeDAO time = new MeetingTimeDAO();
				time.admin_time_management();
				break;

			case 5:
				MeetingReservationDAO reservation = new MeetingReservationDAO();
				reservation.admin_reservation_management();
				break;

			case 9:
				on=false;

			default:
				break;
			}
		}
		return result;

	}

	public int ad_delete() throws SQLException {
		int result = 0;
		System.out.print("삭제할 ID 입력 : ");
		String id = sc.next();
		System.out.print("비밀번호 입력 : ");
		String pw = sc.next();
		int i = ad_login(id, pw);
		
		if (i != 1) return 0;

		String sql = "delete from admins where ad_id = ?";
		conn = Util.getConnection();
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		
		result=ps.executeUpdate();
		Util.dbClose(rs, ps, conn);
		return result;	

	}

	public int ad_login(String id, String pw) {

		int result = 0;

		String sql = "select ad_pw from admins where ad_id = ?";

		conn = Util.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(pw)) {
					result = 1;
				} else {
					System.out.println("비밀번호 오류");
					result = 0;
				}
			} else {
				System.out.println("ID가 존재하지 않습니다");
				result = 0;
			}

			Util.dbClose(ps, conn, rs);
		} catch (SQLException e) {
			System.out.println("알 수 없는 오류 발생");
			result = 0;
		}

		return result;

	}

	
	  public int ad_insert() throws SQLException, IOException {
	  
	  System.out.println("관리자 계정 추가 메뉴입니다."); 
	  System.out.print("새 ID 입력 : ");
	  String new_id = br.readLine();
	  System.out.print("새 PW 입력 : "); 
	  String new_pw = br.readLine();
	  
	  int result = 0; 
	  String sql = "insert into ADMINS values(?,?)"; 
	  conn = Util.getConnection(); 
	  ps = conn.prepareStatement(sql); 
	  ps.setString(1, new_id); 
	  ps.setString(2, new_pw);
	 
	  result = ps.executeUpdate();
	 
	 Util.dbClose(null, ps, conn);
	 
	  return result;
	  
	  }
	 

	private AdminsVO makeadmin(ResultSet rs) throws SQLException {

		admin.setAd_id(rs.getString("AD_ID"));
		admin.setAd_pw(rs.getString("AD_PW"));

		return admin;
	}

	public List<AdminsVO> checkadmin() throws SQLException {
		List<AdminsVO> acc = new ArrayList<AdminsVO>();
		Statement st = null;
		ResultSet rs = null;

		String sql = "select * from ADMINS";

		Connection conn = Util.getConnection();
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		while (rs.next()) {
			admin = makeadmin(rs);
			acc.add(admin);
		}

		DBUtill.dbClose(rs, st, conn);

		return acc;

	}

}

package MeetingReservationModel;

public class DepartmentsVO {
	int department_id;
	int department_num;
	String department_name;
	public DepartmentsVO(int department_id, int department_num, String department_name) {
		super();
		this.department_id = department_id;
		this.department_num = department_num;
		this.department_name = department_name;
	}
	public DepartmentsVO() {
		super();
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public int getDepartment_num() {
		return department_num;
	}
	public void setDepartment_num(int department_num) {
		this.department_num = department_num;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("부서정보[department_id=").append(department_id).append(", department_num=")
				.append(department_num).append(", department_name=").append(department_name).append("]");
		return builder.toString();
	}
	
}

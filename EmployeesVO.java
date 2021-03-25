package MeetingReservationModel;

public class EmployeesVO {
	String employee_id;
	int employee_priority;
	String employee_name;
	String employee_position;
	String employee_pw;
	int department_id;
	public EmployeesVO(String employee_id, int employee_priority, String employee_name, String employee_position,
			String employee_pw, int department_id) {
		super();
		this.employee_id = employee_id;
		this.employee_priority = employee_priority;
		this.employee_name = employee_name;
		this.employee_position = employee_position;
		this.employee_pw = employee_pw;
		this.department_id = department_id;
	}
	public EmployeesVO() {
		super();
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public int getEmployee_priority() {
		return employee_priority;
	}
	public void setEmployee_priority(int employee_priority) {
		this.employee_priority = employee_priority;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getEmployee_position() {
		return employee_position;
	}
	public void setEmployee_position(String employee_position) {
		this.employee_position = employee_position;
	}
	public String getEmployee_pw() {
		return employee_pw;
	}
	public void setEmployee_pw(String employee_pw) {
		this.employee_pw = employee_pw;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("인사정보 [employee_id=").append(employee_id).append(", employee_priority=")
				.append(employee_priority).append(", employee_name=").append(employee_name)
				.append(", employee_position=").append(employee_position).append(", employee_pw=").append(employee_pw)
				.append(", department_id=").append(department_id).append("]");
		return builder.toString();
	}
	
}

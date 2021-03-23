package MeetingReservationModel;

public class AdminsVO {
	String ad_id;
	String ad_pw;
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public String getAd_pw() {
		return ad_pw;
	}
	public void setAd_pw(String ad_pw) {
		this.ad_pw = ad_pw;
	}
	public AdminsVO(String ad_id, String ad_pw) {
		super();
		this.ad_id = ad_id;
		this.ad_pw = ad_pw;
	}
	public AdminsVO() {
		super();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("관리자 정보 [ad_id=").append(ad_id).append(", ad_pw=").append(ad_pw).append("]");
		return builder.toString();
	}

}

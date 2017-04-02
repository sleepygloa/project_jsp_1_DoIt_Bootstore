package mvc.doit.Delivery;

import java.sql.Timestamp;

public class DeliveryDto {
	//배송 테이블은 배송모드만 부여해야하는 상황이 판매신청서 작성하는 페이지에서 있기때문에
	//배송코드를 제외한 모든 column 들은 [notnull] 이 아니다.
	private int d_bdeliverycode; 		// 배송코드 
	private int d_bcode;				// 책코드
	private int d_bdelibery;			// 배송상태
	private String d_bbuyer;			// 구매자 아이디
	private String d_brecipient;		// 받는사람
	private String d_brequested;		// 배송요청사항
	private Timestamp d_bdeldate;		// 주문등록날짜
	
	
	public int getD_bdeliverycode() {
		return d_bdeliverycode;
	}
	public void setD_bdeliverycode(int d_bdeliverycode) {
		this.d_bdeliverycode = d_bdeliverycode;
	}
	public int getD_bcode() {
		return d_bcode;
	}
	public void setD_bcode(int d_bcode) {
		this.d_bcode = d_bcode;
	}
	public int getD_bdelibery() {
		return d_bdelibery;
	}
	public void setD_bdelibery(int d_bdelibery) {
		this.d_bdelibery = d_bdelibery;
	}
	public String getD_bbuyer() {
		return d_bbuyer;
	}
	public void setD_bbuyer(String d_bbuyer) {
		this.d_bbuyer = d_bbuyer;
	}
	public String getD_brecipient() {
		return d_brecipient;
	}
	public void setD_brecipient(String d_brecipient) {
		this.d_brecipient = d_brecipient;
	}
	public String getD_brequested() {
		return d_brequested;
	}
	public void setD_brequested(String d_brequested) {
		this.d_brequested = d_brequested;
	}
	public Timestamp getD_bdeldate() {
		return d_bdeldate;
	}
	public void setD_bdeldate(Timestamp d_bdeldate) {
		this.d_bdeldate = d_bdeldate;
	}
	
	
	
}

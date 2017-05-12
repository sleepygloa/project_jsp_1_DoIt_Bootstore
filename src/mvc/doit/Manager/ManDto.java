package mvc.doit.Manager;

import java.sql.Timestamp;

public class ManDto {
	
	//변수 설정
	
	//dashboard 수익표시
	private int d_seller; //직접판매
	private int d_lib; //도서관 연체료
	private int d_trade; //직거래 / 경매 수수료
	
	//도서관 배송상태 출력
	private int br_no; //도서번호
	private String br_code; //도서코드
	private String br_thumpic; //도서사진
	private String br_name; //도서이름
	private String br_pub;//도서 출판사
	private String br_writer; //저자
	private String br_waiter; //대기자
	private int br_deli;//배송상태
	private Timestamp br_over_date;//대여일자
	
	
	
	
	//setter,getter
	
	//dashboard 수익표시
	public int getD_seller() {
		return d_seller;
	}
	public void setD_seller(int d_seller) {
		this.d_seller = d_seller;
	}
	public int getD_lib() {
		return d_lib;
	}
	public void setD_lib(int d_lib) {
		this.d_lib = d_lib;
	}
	public int getD_trade() {
		return d_trade;
	}
	public void setD_trade(int d_trade) {
		this.d_trade = d_trade;
	}
	
	
	//도서관 배송상태 출력
	public int getBr_no() {
		return br_no;
	}
	public void setBr_no(int br_no) {
		this.br_no = br_no;
	}
	public String getBr_code() {
		return br_code;
	}
	public void setBr_code(String br_code) {
		this.br_code = br_code;
	}
	public String getBr_thumpic() {
		return br_thumpic;
	}
	public void setBr_thumpic(String br_thumpic) {
		this.br_thumpic = br_thumpic;
	}
	public String getBr_name() {
		return br_name;
	}
	public void setBr_name(String br_name) {
		this.br_name = br_name;
	}
	public String getBr_pub() {
		return br_pub;
	}
	public void setBr_pub(String br_pub) {
		this.br_pub = br_pub;
	}
	public String getBr_writer() {
		return br_writer;
	}
	public void setBr_writer(String br_writer) {
		this.br_writer = br_writer;
	}
	public String getBr_waiter() {
		return br_waiter;
	}
	public void setBr_waiter(String br_waiter) {
		this.br_waiter = br_waiter;
	}
	public int getBr_deli() {
		return br_deli;
	}
	public void setBr_deli(int br_deli) {
		this.br_deli = br_deli;
	}
	public Timestamp getBr_over_date() {
		return br_over_date;
	}
	public void setBr_over_date(Timestamp br_over_date) {
		this.br_over_date = br_over_date;
	}
	
	
	
	
	
	
}

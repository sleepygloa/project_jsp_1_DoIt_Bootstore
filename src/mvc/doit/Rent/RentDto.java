package mvc.doit.Rent;

import java.sql.Timestamp;

public class RentDto {
	private int br_no;				//도서번호
	private String br_code;				//도서 고유코드
	private String br_thumpic;	//도서 이미지
	private String br_name;		//도서 이름
	private String br_pub;			//도서 출판사
	private String br_writer;		//도서 저자
	private String br_sname;		//도서 소제목
	private String br_cont;			//도서 소개글
    private int d_bno;				//도서 장르번호
    private int br_don;				//도서 기부자번호
    private int br_admin;			//관리자 번호
    private int br_wait;				//기부대기 상태
    private Timestamp br_date;				//기부 날짜
    
    //대여목록 내용 추가 파트
    private int br_waiter;
    private int br_deli;
    private Timestamp br_over_date;
    
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
	public String getBr_sname() {
		return br_sname;
	}
	public void setBr_sname(String br_sname) {
		this.br_sname = br_sname;
	}
	public String getBr_cont() {
		return br_cont;
	}
	public void setBr_cont(String br_cont) {
		this.br_cont = br_cont;
	}
	public int getD_bno() {
		return d_bno;
	}
	public void setD_bno(int d_bno) {
		this.d_bno = d_bno;
	}
	public int getBr_don() {
		return br_don;
	}
	public void setBr_don(int br_don) {
		this.br_don = br_don;
	}
	public int getBr_admin() {
		return br_admin;
	}
	public void setBr_admin(int br_admin) {
		this.br_admin = br_admin;
	}
	public int getBr_wait() {
		return br_wait;
	}
	public void setBr_wait(int br_wait) {
		this.br_wait = br_wait;
	}
	public Timestamp getBr_date() {
		return br_date;
	}
	public void setBr_date(Timestamp br_date) {
		this.br_date = br_date;
	}
	
	//대여목록 내용 추가 파트
	public int getBr_waiter() {
		return br_waiter;
	}
	public void setBr_waiter(int i) {
		this.br_waiter = i;
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

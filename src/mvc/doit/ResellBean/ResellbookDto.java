package mvc.doit.ResellBean;

import java.sql.Timestamp;

public class ResellbookDto {
	private int rbook_no; 		//게시글번호
	private int rbook_num;		//책코드
	private String rbook_id;	//작성자
	private String rbook_pw;	//비밀번호
	private String rbook_name;	//책이름
	private String rbook_writer;	//책저자
	private String rbook_company;	//출판사
	private int rbook_price;		//책가격
	private String rbook_content;	//게시글내용
	private String rbook_pic;		//책사진
	private Timestamp rbook_reg_date;//작성일자
	private int rbook_readcount;	//조회수
	private int rbook_sell_check;	//구매여부
	private String rbook_subject;	//게시글 글번호
	private int rbook_bgread;		//판매할 책상태
	private int rbook_price2;		//책의 정가
	
//--join(resellreport)--------------------------------------------
	private int report_no;				//신고번호
	private String report_id;			//신고한 아이디
	private Timestamp report_reg_date;	//신고한 시간
	private String report_content;      //신고한 내용
	
//--join(resellintro)----------------------------------------------
	private int rbook_finish_check;		//판매완료 개수 
//--join(resellscrap)-------------------------------------------------------
	private int scrap_no;		//스크랩 번호	
	private String d_id;		//회원 아이디
	private String scrap_reg_date; //스크랩 등록 날짜
	
//resellbook join
	 private int d_no;//작성자 아이디의 회원번호
	 
	
	
	public String getReport_content() {
		return report_content;
	}
	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}
	public String getScrap_reg_date() {
		return scrap_reg_date;
	}
	public void setScrap_reg_date(String scrap_reg_date) {
		this.scrap_reg_date = scrap_reg_date;
	}
	public int getD_no() {
		return d_no;
	}
	public void setD_no(int d_no) {
		this.d_no = d_no;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public int getScrap_no() {
		return scrap_no;
	}
	public void setScrap_no(int scrap_no) {
		this.scrap_no = scrap_no;
	}
	public int getRbook_finish_check() {
		return rbook_finish_check;
	}
	public void setRbook_finish_check(int rbook_finish_check) {
		this.rbook_finish_check = rbook_finish_check;
	}
	public int getReport_no() {
		return report_no;
	}
	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public Timestamp getReport_reg_date() {
		return report_reg_date;
	}
	public void setReport_reg_date(Timestamp report_reg_date) {
		this.report_reg_date = report_reg_date;
	}
	public int getRbook_price2() {
		return rbook_price2;
	}
	public void setRbook_price2(int rbook_price2) {
		this.rbook_price2 = rbook_price2;
	}
	public String getRbook_subject() {
		return rbook_subject;
	}
	public void setRbook_subject(String rbook_subject) {
		this.rbook_subject = rbook_subject;
	}
	public int getRbook_bgread() {
		return rbook_bgread;
	}
	public void setRbook_bgread(int rbook_bgread) {
		this.rbook_bgread = rbook_bgread;
	}
	public int getRbook_no() {
		return rbook_no;
	}
	public void setRbook_no(int rbook_no) {
		this.rbook_no = rbook_no;
	}
	public int getRbook_num() {
		return rbook_num;
	}
	public void setRbook_num(int rbook_num) {
		this.rbook_num = rbook_num;
	}
	
	public String getRbook_id() {
		return rbook_id;
	}
	public void setRbook_id(String rbook_id) {
		this.rbook_id = rbook_id;
	}
	public String getRbook_pw() {
		return rbook_pw;
	}
	public void setRbook_pw(String rbook_pw) {
		this.rbook_pw = rbook_pw;
	}
	public String getRbook_name() {
		return rbook_name;
	}
	public void setRbook_name(String rbook_name) {
		this.rbook_name = rbook_name;
	}
	public String getRbook_writer() {
		return rbook_writer;
	}
	public void setRbook_writer(String rbook_writer) {
		this.rbook_writer = rbook_writer;
	}
	public String getRbook_company() {
		return rbook_company;
	}
	public void setRbook_company(String rbook_company) {
		this.rbook_company = rbook_company;
	}
	public int getRbook_price() {
		return rbook_price;
	}
	public void setRbook_price(int rbook_price) {
		this.rbook_price = rbook_price;
	}
	public String getRbook_content() {
		return rbook_content;
	}
	public void setRbook_content(String rbook_content) {
		this.rbook_content = rbook_content;
	}
	public String getRbook_pic() {
		return rbook_pic;
	}
	public void setRbook_pic(String rbook_pic) {
		this.rbook_pic = rbook_pic;
	}
	public Timestamp getRbook_reg_date() {
		return rbook_reg_date;
	}
	public void setRbook_reg_date(Timestamp rbook_reg_date) {
		this.rbook_reg_date = rbook_reg_date;
	}
	public int getRbook_readcount() {
		return rbook_readcount;
	}
	public void setRbook_readcount(int rbook_readcount) {
		this.rbook_readcount = rbook_readcount;
	}
	public int getRbook_sell_check() {
		return rbook_sell_check;
	}
	public void setRbook_sell_check(int rbook_sell_check) {
		this.rbook_sell_check = rbook_sell_check;
	}
	
}

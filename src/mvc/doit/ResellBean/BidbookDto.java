package mvc.doit.ResellBean;

import java.sql.Timestamp;

public class BidbookDto {

	private int bid_no; 		//게시글번호
	private String bid_id;		//판매자id
	private String bid_name;	//책이름
	private int bid_price1;		//시작가
	private int bid_price2;		//update 된값
	private String bid_pic;		//책사진
	private String bid_subject;	//책제목
	private String bid_content ;	//책소개내용
	private int bid_readcount;	//조회수
	private Timestamp bid_reg_date;//올린일자
	private int bid_count;		//입찰수
	private String bid_last_id;	//마지막입찰자id
	private int bid_finish;	//판매완료여부
	private int bid_time_value;// 경매 시간 설정
	private Timestamp bid_finish_date;// 경매 마감시간
	
	//---join--------------------
	private String d_id;
	private String d_name;
	private String d_phone;
	private String d_mail;
	
	//---join------------------
	private String bid_bid;
	
	
	
	public String getBid_bid() {
		return bid_bid;
	}
	public void setBid_bid(String bid_bid) {
		this.bid_bid = bid_bid;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getD_phone() {
		return d_phone;
	}
	public void setD_phone(String d_phone) {
		this.d_phone = d_phone;
	}
	public String getD_mail() {
		return d_mail;
	}
	public void setD_mail(String d_mail) {
		this.d_mail = d_mail;
	}
	public int getBid_no() {
		return bid_no;
	}
	public void setBid_no(int bid_no) {
		this.bid_no = bid_no;
	}
	public String getBid_id() {
		return bid_id;
	}
	public void setBid_id(String bid_id) {
		this.bid_id = bid_id;
	}
	public String getBid_name() {
		return bid_name;
	}
	public void setBid_name(String bid_name) {
		this.bid_name = bid_name;
	}
	public int getBid_price1() {
		return bid_price1;
	}
	public void setBid_price1(int bid_price1) {
		this.bid_price1 = bid_price1;
	}
	public int getBid_price2() {
		return bid_price2;
	}
	public void setBid_price2(int bid_price2) {
		this.bid_price2 = bid_price2;
	}
	public String getBid_pic() {
		return bid_pic;
	}
	public void setBid_pic(String bid_pic) {
		this.bid_pic = bid_pic;
	}
	public String getBid_subject() {
		return bid_subject;
	}
	public void setBid_subject(String bid_subject) {
		this.bid_subject = bid_subject;
	}
	public String getBid_content() {
		return bid_content;
	}
	public void setBid_content(String bid_content) {
		this.bid_content = bid_content;
	}
	public int getBid_readcount() {
		return bid_readcount;
	}
	public void setBid_readcount(int bid_readcount) {
		this.bid_readcount = bid_readcount;
	}
	public Timestamp getBid_reg_date() {
		return bid_reg_date;
	}
	public void setBid_reg_date(Timestamp bid_reg_date) {
		this.bid_reg_date = bid_reg_date;
	}
	public int getBid_count() {
		return bid_count;
	}
	public void setBid_count(int bid_count) {
		this.bid_count = bid_count;
	}
	public String getBid_last_id() {
		return bid_last_id;
	}
	public void setBid_last_id(String bid_last_id) {
		this.bid_last_id = bid_last_id;
	}
	public int getBid_finish() {
		return bid_finish;
	}
	public void setBid_finish(int bid_finish) {
		this.bid_finish = bid_finish;
	}
	public int getBid_time_value() {
		return bid_time_value;
	}
	public void setBid_time_value(int bid_time_value) {
		this.bid_time_value = bid_time_value;
	}
	public Timestamp getBid_finish_date() {
		return bid_finish_date;
	}
	public void setBid_finish_date(Timestamp bid_finish_date) {
		this.bid_finish_date = bid_finish_date;
	}
	
	
}

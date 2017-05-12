package mvc.doit.Login;

import java.sql.Timestamp;

public class LoginDto {
	
	private int d_no;
	private String d_id;
	private String d_pass;//비번통합
	private String d_pw;
	private String d_name;
	
	private String d_phone; //폰번호 통합
	private String user_phone1;
	private String user_phone2;
	private String user_phone3;
	
	private String d_addr;
	private String d_person;
	
	private String d_mail;	//메일통합
	private String user_mail1;
	private String user_mail2;
	
	private String d_birth; //생년월일 통합
	private String user_birth1;
	private String user_birth2;
	private String user_birth3;
	
	private String d_gender;
	private String d_pic;
	private int d_nom_grade;
	private int d_mech_grade;
	private Timestamp d_date;
	
	private String report_count;
	public String getReport_count() {
	      return report_count;
	   }
	   public void setReport_count(String report_count) {
	      this.report_count = report_count;
	   }
	
	//resellintro join
    private String rbook_intro;      //판매자소개
    private Timestamp rbook_reg_date; //판매자 가입일자
    
//--join(bidbook)--------------------------------------------   
    private int bid_no;
    private String bid_subject;
    private String bid_name;
    private int bid_price2;
    private String bid_last_id;
	
	//-- 직거래 게시판 + 확장팩
	public String getRbook_intro() {
		return rbook_intro;
	}
	public void setRbook_intro(String rbook_intro) {
		this.rbook_intro = rbook_intro;
	}
	public Timestamp getRbook_reg_date() {
		return rbook_reg_date;
	}
	public void setRbook_reg_date(Timestamp rbook_reg_date) {
		this.rbook_reg_date = rbook_reg_date;
	}
	public int getBid_no() {
		return bid_no;
	}
	public void setBid_no(int bid_no) {
		this.bid_no = bid_no;
	}
	public String getBid_subject() {
		return bid_subject;
	}
	public void setBid_subject(String bid_subject) {
		this.bid_subject = bid_subject;
	}
	public String getBid_name() {
		return bid_name;
	}
	public void setBid_name(String bid_name) {
		this.bid_name = bid_name;
	}
	public int getBid_price2() {
		return bid_price2;
	}
	public void setBid_price2(int bid_price2) {
		this.bid_price2 = bid_price2;
	}
	public String getBid_last_id() {
		return bid_last_id;
	}
	public void setBid_last_id(String bid_last_id) {
		this.bid_last_id = bid_last_id;
	}
	
	
	
	//Getter , setter 메서드 
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
	public String getD_pass() {
		return d_pass;
	}
	public void setD_pass(String d_pass) {
		this.d_pass = d_pass;
	}
	public String getD_pw() {
		return d_pw;
	}
	public void setD_pw(String d_pw) {
		this.d_pw = d_pw;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	
//phone-------------------------------------------------	
	public String getD_phone() {
		return user_phone1+"-"+user_phone2+"-"+user_phone3;
	}
	public void setD_phone(String d_phone) {
		this.d_phone = d_phone;
	}
	public String getUser_phone1() {
		return user_phone1;
	}
	public void setUser_phone1(String user_phone1) {
		this.user_phone1 = user_phone1;
	}
	public String getUser_phone2() {
		return user_phone2;
	}
	public void setUser_phone2(String user_phone2) {
		this.user_phone2 = user_phone2;
	}
	public String getUser_phone3() {
		return user_phone3;
	}
	public void setUser_phone3(String user_phone3) {
		this.user_phone3 = user_phone3;
	}
	
	
	public String getD_addr() {
		return d_addr;
	}
	public void setD_addr(String d_addr) {
		this.d_addr = d_addr;
	}
	public String getD_person() {
		return d_person;
	}
	public void setD_person(String d_person) {
		this.d_person = d_person;
	}
	
//mail-------------------------------------------------	
	public String getD_mail() {
		return user_mail1+"@"+user_mail2;
	}
	public void setD_mail(String d_mail) {
		this.d_mail = d_mail;
	}
	public String getUser_mail1() {
		return user_mail1;
	}
	public void setUser_mail1(String user_mail1) {
		this.user_mail1 = user_mail1;
	}
	public String getUser_mail2() {
		return user_mail2;
	}
	public void setUser_mail2(String user_mail2) {
		this.user_mail2 = user_mail2;
	}
	
	
	public String getD_birth() {
		return user_birth1+"년"+user_birth2+"월"+user_birth3+"일";
	} 
	public void setD_birth(String d_birth) {
		this.d_birth = d_birth;
	}
	public String getUser_birth1() {
		return user_birth1;
	}
	public void setUser_birth1(String user_birth1) {
		this.user_birth1 = user_birth1;
	}
	public String getUser_birth2() {
		return user_birth2;
	}
	public void setUser_birth2(String user_birth2) {
		this.user_birth2 = user_birth2;
	}
	public String getUser_birth3() {
		return user_birth3;
	}
	public void setUser_birth3(String user_birth3) {
		this.user_birth3 = user_birth3;
	}
	
	
	public String getD_gender() {
		return d_gender;
	}
	public void setD_gender(String d_gender) {
		this.d_gender = d_gender;
	}
	public String getD_pic() {
		return d_pic;
	}
	public void setD_pic(String d_pic) {
		this.d_pic = d_pic;
	}
	public int getD_nom_grade() {
		return d_nom_grade;
	}
	public void setD_nom_grade(int d_nom_grade) {
		this.d_nom_grade = d_nom_grade;
	}
	public int getD_mech_grade() {
		return d_mech_grade;
	}
	public void setD_mech_grade(int d_mech_grade) {
		this.d_mech_grade = d_mech_grade;
	}
	public Timestamp getD_date() {
		return d_date;
	}
	public void setD_date(Timestamp d_date) {
		this.d_date = d_date;
	}
	
	
	
	
	
	

}

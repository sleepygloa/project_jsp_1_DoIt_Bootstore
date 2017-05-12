package mvc.doit.Customer;

import java.sql.Timestamp;

public class NoticeDto {
	
	private int notice_no;
	private String notice_name;
	private String notice_content;
	private int notice_readcount;
	private Timestamp notice_reg_date;
	private String notice_id;
	
	
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	public String getNotice_name() {
		return notice_name;
	}
	public void setNotice_name(String notice_name) {
		this.notice_name = notice_name;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public int getNotice_readcount() {
		return notice_readcount;
	}
	public void setNotice_readcount(int notice_readcount) {
		this.notice_readcount = notice_readcount;
	}
	public Timestamp getNotice_reg_date() {
		return notice_reg_date;
	}
	public void setNotice_reg_date(Timestamp notice_reg_date) {
		this.notice_reg_date = notice_reg_date;
	}
	public String getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}
	
	
}

package mvc.doit.ChatAction;

import java.sql.Timestamp;

public class ChatDto {
	
	//변수 지정
	private int ch_no;
	private String thum = "ma_img.jpg";
	private String d_id = "";
	private String ch_cont = "";
	private Timestamp ch_date;
	
	//setter getter 
	public int getCh_no() {
		return ch_no;
	}
	public void setCh_no(int ch_no) {
		this.ch_no = ch_no;
	}
	public String getThum() {
		return thum;
	}
	public void setThum(String thum) {
		this.thum = thum;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public String getCh_cont() {
		return ch_cont;
	}
	public void setCh_cont(String ch_cont) {
		this.ch_cont = ch_cont;
	}
	public Timestamp getCh_date() {
		return ch_date;
	}
	public void setCh_date(Timestamp ch_date) {
		this.ch_date = ch_date;
	}
	
	
	
	
}

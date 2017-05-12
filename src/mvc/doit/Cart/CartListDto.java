package mvc.doit.Cart;

import java.sql.Timestamp;

public class CartListDto{
	
	//변수 지정
	private int ca_no;
	private int d_no;
	private String d_sell;
	private String dr_sell;
	private String dr_rent;
	private String d_rent;
	private int d_ovedue;
	private Timestamp ca_date;
	private Timestamp d_ov_date;
	
	//b_rent_over dTo
	private int br_no;
	private String br_code;
	private String br_waiter;
	private int br_deli;
	private Timestamp br_over_date;
	
	
	//setter, getter
	public int getCa_no() {
		return ca_no;
	}
	public void setCa_no(int ca_no) {
		this.ca_no = ca_no;
	}
	public int getD_no() {
		return d_no;
	}
	public void setD_no(int d_no) {
		this.d_no = d_no;
	}
	public String getD_sell() {
		return d_sell;
	}
	public void setD_sell(String d_sell) {
		this.d_sell = d_sell;
	}
	public String getD_rent() {
		return d_rent;
	}
	public void setD_rent(String d_rent) {
		this.d_rent = d_rent;
	}
	public Timestamp getCa_date() {
		return ca_date;
	}
	public void setCa_date(Timestamp ca_date) {
		this.ca_date = ca_date;
	}
	public String getDr_sell() {
		return dr_sell;
	}
	public void setDr_sell(String dr_sell) {
		this.dr_sell = dr_sell;
	}
	public String getDr_rent() {
		return dr_rent;
	}
	public void setDr_rent(String dr_rent) {
		this.dr_rent = dr_rent;
	}
	public int getD_ovedue() {
		return d_ovedue;
	}
	public void setD_ovedue(int d_ovedue) {
		this.d_ovedue = d_ovedue;
	}
	public Timestamp getD_ov_date() {
		return d_ov_date;
	}
	public void setD_ov_date(Timestamp d_ov_date) {
		this.d_ov_date = d_ov_date;
	}
	
	
	//b_rent_over Dto
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

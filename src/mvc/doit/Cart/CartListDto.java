package mvc.doit.Cart;

import java.sql.Timestamp;

public class CartListDto{
	
	//변수 지정
	private int ca_no;
	private int d_no;
	private String d_sell;
	private String d_rent;
	private Timestamp ca_date;
	
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
	
	
}

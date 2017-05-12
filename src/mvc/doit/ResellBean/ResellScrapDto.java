package mvc.doit.ResellBean;

import java.sql.Timestamp;

public class ResellScrapDto {
	private int scrap_no;
	private String d_id;
	private int rbook_no;
	private Timestamp scrap_reg_date;
	
	
	public int getScrap_no() {
		return scrap_no;
	}
	public void setScrap_no(int scrap_no) {
		this.scrap_no = scrap_no;
	}
	public Timestamp getScrap_reg_date() {
		return scrap_reg_date;
	}
	public void setScrap_reg_date(Timestamp scrap_reg_date) {
		this.scrap_reg_date = scrap_reg_date;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public int getRbook_no() {
		return rbook_no;
	}
	public void setRbook_no(int rbook_no) {
		this.rbook_no = rbook_no;
	}
	
	
}

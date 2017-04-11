package mvc.doit.ResellBean;

import java.sql.Timestamp;

public class ResellReportDto {
	private int report_no;
	private int rbook_no;
	private	String report_id;
	private String d_id;
	private Timestamp report_reg_date;
	
	
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public Timestamp getReport_reg_date() {
		return report_reg_date;
	}
	public void setReport_reg_date(Timestamp report_reg_date) {
		this.report_reg_date = report_reg_date;
	}
	public int getReport_no() {
		return report_no;
	}
	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}
	public int getRbook_no() {
		return rbook_no;
	}
	public void setRbook_no(int rbook_no) {
		this.rbook_no = rbook_no;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	
	
}

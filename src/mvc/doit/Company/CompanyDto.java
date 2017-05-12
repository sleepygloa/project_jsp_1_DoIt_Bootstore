package mvc.doit.Company;

import java.sql.Timestamp;

public class CompanyDto {
	private int com_no;
	private String com_writer;
	private String com_addr;
	private Timestamp com_reg_date;
	
	public int getCom_no() {
		return com_no;
	}
	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}
	public String getCom_writer() {
		return com_writer;
	}
	public void setCom_writer(String com_writer) {
		this.com_writer = com_writer;
	}
	public String getCom_addr() {
		return com_addr;
	}
	public void setCom_addr(String com_addr) {
		this.com_addr = com_addr;
	}
	public Timestamp getCom_reg_date() {
		return com_reg_date;
	}
	public void setCom_reg_date(Timestamp com_reg_date) {
		this.com_reg_date = com_reg_date;
	}
	
	

}

package mvc.doit.Online;

import java.sql.Timestamp;

public class OnBookIntroDto {
	 private int d_nno;			
	 private int d_bcode;			//책코드
	 private String d_norder;		//목차
	 private String d_nintro;		//소개글
	 private Timestamp d_ndate;		//등록 날짜
	 
	 
	public int getD_nno() {
		return d_nno;
	}
	public void setD_nno(int d_nno) {
		this.d_nno = d_nno;
	}
	public int getD_bcode() {
		return d_bcode;
	}
	public void setD_bcode(int d_bcode) {
		this.d_bcode = d_bcode;
	}
	public String getD_norder() {
		return d_norder;
	}
	public void setD_norder(String d_norder) {
		this.d_norder = d_norder;
	}
	public String getD_nintro() {
		return d_nintro;
	}
	public void setD_nintro(String d_nintro) {
		this.d_nintro = d_nintro;
	}
	public Timestamp getD_ndate() {
		return d_ndate;
	}
	public void setD_idate(Timestamp d_ndate) {
		this.d_ndate = d_ndate;
	}

	 
	 
}

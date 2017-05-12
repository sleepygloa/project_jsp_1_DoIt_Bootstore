package mvc.doit.Online;

import java.sql.Timestamp;

public class OnInspectionDto {
	 private int d_ino;		
	 private int d_bcode;			//책코드
	 private int d_icode;			//검수코드
	 private int d_iold;			//검수방법(헌상태)
	 private int d_icover;			//검수방법(표지)
	 private int d_ispine;			//검수방법(책등)
	 private int d_ibind;			//검수방법(제본상태)
	 private int d_itotal;			//검수 총점
	 private Timestamp d_idate;		//등록 날짜
	 
	 
	public int getD_ino() {
		return d_ino;
	}
	public void setD_ino(int d_ino) {
		this.d_ino = d_ino;
	}
	public int getD_bcode() {
		return d_bcode;
	}
	public void setD_bcode(int d_bcode) {
		this.d_bcode = d_bcode;
	}
	public int getD_icode() {
		return d_icode;
	}
	public void setD_icode(int d_icode) {
		this.d_icode = d_icode;
	}
	public int getD_iold() {
		return d_iold;
	}
	public void setD_iold(int d_iold) {
		this.d_iold = d_iold;
	}
	public int getD_icover() {
		return d_icover;
	}
	public void setD_icover(int d_icover) {
		this.d_icover = d_icover;
	}
	public int getD_ispine() {
		return d_ispine;
	}
	public void setD_ispine(int d_ispine) {
		this.d_ispine = d_ispine;
	}
	public int getD_ibind() {
		return d_ibind;
	}
	public void setD_ibind(int d_ibind) {
		this.d_ibind = d_ibind;
	}
	public int getD_itotal() {
		return d_iold+d_icover+d_ispine+d_ibind;
	}
	public void setD_itotal(int d_itotal) {
		this.d_itotal = d_itotal;
	}
	public Timestamp getD_idate() {
		return d_idate;
	}
	public void setD_idate(Timestamp d_idate) {
		this.d_idate = d_idate;
	}
	 
	 

}

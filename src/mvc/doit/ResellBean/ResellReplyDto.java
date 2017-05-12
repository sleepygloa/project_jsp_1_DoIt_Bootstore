package mvc.doit.ResellBean;

import java.sql.Timestamp;

public class ResellReplyDto {
	private int rerbook_rnum;			//댓글번호
	private int rerbook_bnum;			//댓글이 달린 글번호
	private String rerbook_writer;		//작성자
	private String rerbook_content;		//내용
	private Timestamp rerbook_reg_date;	//날짜
	private String rerbook_ref;			//그룹번호
	private String rerbook_re_step;		//단계
	private String rerbook_re_level;	//레벨
	public int getRerbook_rnum() {
		return rerbook_rnum;
	}
	public void setRerbook_rnum(int rerbook_rnum) {
		this.rerbook_rnum = rerbook_rnum;
	}
	public int getRerbook_bnum() {
		return rerbook_bnum;
	}
	public void setRerbook_bnum(int rerbook_bnum) {
		this.rerbook_bnum = rerbook_bnum;
	}
	public String getRerbook_writer() {
		return rerbook_writer;
	}
	public void setRerbook_writer(String rerbook_writer) {
		this.rerbook_writer = rerbook_writer;
	}
	public String getRerbook_content() {
		return rerbook_content;
	}
	public void setRerbook_content(String rerbook_content) {
		this.rerbook_content = rerbook_content;
	}
	public Timestamp getRerbook_reg_date() {
		return rerbook_reg_date;
	}
	public void setRerbook_reg_date(Timestamp rerbook_reg_date) {
		this.rerbook_reg_date = rerbook_reg_date;
	}
	public String getRerbook_ref() {
		return rerbook_ref;
	}
	public void setRerbook_ref(String rerbook_ref) {
		this.rerbook_ref = rerbook_ref;
	}
	public String getRerbook_re_step() {
		return rerbook_re_step;
	}
	public void setRerbook_re_step(String rerbook_re_step) {
		this.rerbook_re_step = rerbook_re_step;
	}
	public String getRerbook_re_level() {
		return rerbook_re_level;
	}
	public void setRerbook_re_level(String rerbook_re_level) {
		this.rerbook_re_level = rerbook_re_level;
	}
	
	
	
}

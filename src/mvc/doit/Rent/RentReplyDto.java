package mvc.doit.Rent;

import java.sql.Timestamp;

public class RentReplyDto {
   private int b_no;
   private String b_pic;
   private String b_title;
   private String b_cont;
   private String b_id;
   private int br_no;
   private Timestamp b_date;
   
   public int getB_no() {
      return b_no;
   }
   public void setB_no(int b_no) {
      this.b_no = b_no;
   }
   public String getB_pic() {
      return b_pic;
   }
   public void setB_pic(String b_pic) {
      this.b_pic = b_pic;
   }
   public String getB_title() {
      return b_title;
   }
   public void setB_title(String b_title) {
      this.b_title = b_title;
   }
   public String getB_cont() {
      return b_cont;
   }
   public void setB_cont(String b_cont) {
      this.b_cont = b_cont;
   }
   public String getB_id() {
      return b_id;
   }
   public void setB_id(String b_id) {
      this.b_id = b_id;
   }
   public int getBr_no() {
      return br_no;
   }
   public void setBr_no(int br_no) {
      this.br_no = br_no;
   }
   public Timestamp getB_date() {
      return b_date;
   }
   public void setB_date(Timestamp b_date) {
      this.b_date = b_date;
   }
   

}
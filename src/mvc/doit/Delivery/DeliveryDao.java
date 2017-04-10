package mvc.doit.Delivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.doit.Account.AcDto;
import mvc.doit.Login.LoginDto;
import mvc.doit.Online.OnBookDto;


public class DeliveryDao {

private static DeliveryDao instance = new DeliveryDao();
    
    public static DeliveryDao getInstance() {return instance; }
    
    private DeliveryDao() {}
    
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
      return ds.getConnection();
    }
	
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
	
//-----------배송코드 부여, 판매신청양식-----------배송코드 부여, 판매신청양식-----------배송코드 부여, 판매신청양식-----------배송코드 부여, 판매신청양식-----------배송코드 부여, 판매신청양식 
//판매신청서 작성시, 배송코드만 부여하는 dao------판매신청서 작성시, 배송코드만 부여하는 dao------판매신청서 작성시, 배송코드만 부여하는 dao------    
    
    //회원이 관리자에게 판매할때
    public int setD_bdeliverycode(String d_id, int d_bcode){
    	int d_bdeliverycode = 0;
	      try{
	        conn=getConnection();
	         
	        pstmt = conn.prepareStatement(
"insert into d_bdelivery values("
+ "d_bdeliverycode_seq.NEXTVAL, ?, 0, sysdate, sysdate, sysdate, ?, 'admin' )");
	        pstmt.setInt(1, d_bcode);
	        pstmt.setString(2, d_id);
	        pstmt.executeUpdate();
	        
	        pstmt = conn.prepareStatement(
"select d_bdeliverycode from d_bdelivery where d_bcode = ? and d_bseller = ?"
);
	        pstmt.setInt(1, d_bcode);
	        pstmt.setString(2, d_id);
	        rs = pstmt.executeQuery();
	        if(rs.next()){
	        	DeliveryDto article = new DeliveryDto();
	        	d_bdeliverycode = rs.getInt("d_bdeliverycode");
	        }else{}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt !=null){try{pstmt.close();}catch(SQLException s){}}
			if(conn !=null){try{conn.close();}catch(SQLException s){}}	
		}		 
	      return d_bdeliverycode;
    }
    
  //----------------관리자 구매신청리스트 주문확인-> 구매완료----------------관리자 구매신청리스트 주문확인-> 구매완료---------------관리자 구매신청리스트 주문확인-> 구매완료--------------관리자 구매신청리스트 주문확인-> 구매완료ㄴ
    public void Admin_OnBuyBook_finish(int d_bcode){


    		try{
    			conn = getConnection();
    			pstmt = conn.prepareStatement("update d_bdelivery set d_bdelibery = 21 where d_bcode=?  ");
    			
    			pstmt.setInt(1, d_bcode);

    			pstmt.executeUpdate();
    			    			

    		}catch(Exception e){
    			e.printStackTrace();
    		}finally{
    			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
    			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
    			if(conn != null){ try{conn.close();}catch(SQLException s){}}
    		}
    		
    	}
    
  //----------------관리자 구매신청리스트 구매완료-> 배송시작----------------관리자 구매신청리스트 구매완료-> 배송시작---------------관리자 구매신청리스트 구매완료-> 배송시작--------------관리자 구매신청리스트 구매완료-> 배송시작
    public void Admin_OnBuyBook_delivertStart(int d_bcode){


    		try{
    			conn = getConnection();
    			pstmt = conn.prepareStatement("update d_bdelivery set d_bdelibery = 22 where d_bcode=?  ");
    			
    			pstmt.setInt(1, d_bcode);

    			pstmt.executeUpdate();
    			    			

    		}catch(Exception e){
    			e.printStackTrace();
    		}finally{
    			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
    			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
    			if(conn != null){ try{conn.close();}catch(SQLException s){}}
    		}
    		
    	}
    
  //----------------관리자 구매신청리스트 배송완료----------------관리자 구매신청리스트 배송완료---------------관리자 구매신청리스트 배송완료--------------관리자 구매신청리스트 배송완료
    public void Admin_OnBuyBook_delivertEnd(int d_bcode){


    		try{
    			conn = getConnection();
    			pstmt = conn.prepareStatement("update d_bdelivery set d_bdelibery = 23 where d_bcode=?  ");
    			
    			pstmt.setInt(1, d_bcode);

    			pstmt.executeUpdate();
    			    			

    		}catch(Exception e){
    			e.printStackTrace();
    		}finally{
    			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
    			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
    			if(conn != null){ try{conn.close();}catch(SQLException s){}}
    		}
    		
    	}
    
  //-----------------사용자 취소신청----------------사용자 취소신청----------------사용자 취소신청---------------사용자 취소신청---------------사용자 취소신청
    public void Admin_OnBuyBook_Cancel(int d_bcode){


    		try{
    			conn = getConnection();
    			pstmt = conn.prepareStatement("update d_bdelivery set d_bdelibery = 24 where d_bcode=?  ");
    			
    			pstmt.setInt(1, d_bcode);

    			pstmt.executeUpdate();
    			    			

    		}catch(Exception e){
    			e.printStackTrace();
    		}finally{
    			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
    			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
    			if(conn != null){ try{conn.close();}catch(SQLException s){}}
    		}
    		
    	}
    
    //----------------관리자 취소 승인----------------관리자 취소 승인---------------관리자 취소 승인--------------관리자 취소 승인
    public void Admin_OnBuyBook_CancelCheck(int d_bcode){


    		try{
    			conn = getConnection();
    			pstmt = conn.prepareStatement("update d_bdelivery set d_bdelibery = 25 where d_bcode=?  ");
    			
    			pstmt.setInt(1, d_bcode);

    			pstmt.executeUpdate();
    			    			

    		}catch(Exception e){
    			e.printStackTrace();
    		}finally{
    			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
    			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
    			if(conn != null){ try{conn.close();}catch(SQLException s){}}
    		}
    		
    	}
    
    
    
    
 //----------------------------------------------------------------------------------------------------------
 //---- 회원의 책 판매 관련 배송 Dao ---------
   
  //판매신청서 작성시, 배송코드만 부여하는 dao------판매신청서 작성시, 배송코드만 부여하는 dao------판매신청서 작성시, 배송코드만 부여하는 dao------    
      
      //회원이 관리자에게 판매할때
      public int setD_bUserdeliverycode(String d_id, int d_bcode){
      	int d_bdeliverycode = 0;
  	      try{
  	        conn=getConnection();
  	 
  			pstmt = conn.prepareStatement(
"insert into d_bdelivery values(d_bdeliverycode_seq.NEXTVAL, "+d_bcode+", 0, '"+d_id+"', 'admin1', '회원판매', sysdate)"
//seq, d_bcode, d_bdelivery상태, 배송인, 수령인, 요청사항, 시간  					
  					);
  			pstmt.executeUpdate();
  			

  		}catch(Exception e){
  			e.printStackTrace();
  		}finally{
  			if(pstmt !=null){try{pstmt.close();}catch(SQLException s){}}
  			if(conn !=null){try{conn.close();}catch(SQLException s){}}	
  		}		 
  	      return d_bdeliverycode;
      }
      
    //----------------관리자 구매신청리스트 주문확인-> 구매완료----------------관리자 구매신청리스트 주문확인-> 구매완료---------------관리자 구매신청리스트 주문확인-> 구매완료--------------관리자 구매신청리스트 주문확인-> 구매완료ㄴ
      public void User_SellBook_finish(int d_bcode){


      		try{
      			conn = getConnection();
      			pstmt = conn.prepareStatement("update d_bdelivery set d_bdelibery = 11 where d_bcode=?  ");
      			
      			pstmt.setInt(1, d_bcode);

      			pstmt.executeUpdate();
      			    			

      		}catch(Exception e){
      			e.printStackTrace();
      		}finally{
      			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
      			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
      			if(conn != null){ try{conn.close();}catch(SQLException s){}}
      		}
      		
      	}
      
    //----------------관리자 구매신청리스트 구매완료-> 배송시작----------------관리자 구매신청리스트 구매완료-> 배송시작---------------관리자 구매신청리스트 구매완료-> 배송시작--------------관리자 구매신청리스트 구매완료-> 배송시작
      public void User_SellBook_delivertStart(int d_bcode){


      		try{
      			conn = getConnection();
      			pstmt = conn.prepareStatement("update d_bdelivery set d_bdelibery = 12 where d_bcode=?  ");
      			
      			pstmt.setInt(1, d_bcode);

      			pstmt.executeUpdate();
      			    			

      		}catch(Exception e){
      			e.printStackTrace();
      		}finally{
      			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
      			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
      			if(conn != null){ try{conn.close();}catch(SQLException s){}}
      		}
      		
      	}
      
    //----------------관리자 구매신청리스트 배송완료----------------관리자 구매신청리스트 배송완료---------------관리자 구매신청리스트 배송완료--------------관리자 구매신청리스트 배송완료
      public void User_SellBook_delivertEnd(int d_bcode){


      		try{
      			conn = getConnection();
      			pstmt = conn.prepareStatement("update d_bdelivery set d_bdelibery = 13 where d_bcode=?  ");
      			
      			pstmt.setInt(1, d_bcode);

      			pstmt.executeUpdate();
      			    			

      		}catch(Exception e){
      			e.printStackTrace();
      		}finally{
      			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
      			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
      			if(conn != null){ try{conn.close();}catch(SQLException s){}}
      		}
      		
      	}
      
    //-----------------사용자 취소신청----------------사용자 취소신청----------------사용자 취소신청---------------사용자 취소신청---------------사용자 취소신청
      public void User_SellBook_Cancel(int d_bcode){


      		try{
      			conn = getConnection();
      			pstmt = conn.prepareStatement("update d_bdelivery set d_bdelibery = 14 where d_bcode=?  ");
      			
      			pstmt.setInt(1, d_bcode);

      			pstmt.executeUpdate();
      			    			

      		}catch(Exception e){
      			e.printStackTrace();
      		}finally{
      			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
      			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
      			if(conn != null){ try{conn.close();}catch(SQLException s){}}
      		}
      		
      	}
      
      //----------------관리자 취소 승인----------------관리자 취소 승인---------------관리자 취소 승인--------------관리자 취소 승인
      public void User_SellBook_CancelCheck(int d_bcode){


      		try{
      			conn = getConnection();
      			pstmt = conn.prepareStatement("update d_bdelivery set d_bdelibery = 15 where d_bcode=?  ");
      			
      			pstmt.setInt(1, d_bcode);

      			pstmt.executeUpdate();
      			    			

      		}catch(Exception e){
      			e.printStackTrace();
      		}finally{
      			 if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
      			if(pstmt != null){ try{pstmt.close();}catch(SQLException s){}}
      			if(conn != null){ try{conn.close();}catch(SQLException s){}}
      		}
      		
      	}
    
    
}//

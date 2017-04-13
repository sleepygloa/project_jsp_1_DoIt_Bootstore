package mvc.doit.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;

public class CustomerDao {

		private static CustomerDao instance = new CustomerDao();

	    
	    public static CustomerDao getInstance() {return instance; }
	    
	    private CustomerDao() {}
	    
	    private Connection getConnection() throws Exception {
	      Context initCtx = new InitialContext();
	      Context envCtx = (Context) initCtx.lookup("java:comp/env");
	      DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
	      return ds.getConnection();
	    }
		
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    //s
    
	    
	    //---------------------------관리자가 보는 문의 내역 count ---------------------------------------//
	 public int adminInquireListCount() throws Exception{
		 int x = 0; //책의 수
	    	try{
	    		conn = getConnection();
	    		pstmt = conn.prepareStatement(
	"select count(*) from InquireBoard"
	    									);
	    		rs = pstmt.executeQuery();
	    		if(rs.next()){
	    			x = rs.getInt(1); //카운트 첫번째 행의 값을 출력하여 x에 대입
	    		}
	    		
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
		        if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
		        if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
		        if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
		     }
	    	
	    	return x;
	    }
		 
	   //---------------------------관리자가 보는 문의 내역 count -끝--------------------------------------//
	 
	 
	 
	//---------------------------관리자가 보는 문의 내역 List--------------------------------------//
	 
	 public List<InquireDto> adminInquireList(int startRow, int endRow)  throws Exception{
	     List<InquireDto> articleList = null;
	     try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(
"select c_ino, d_no, c_itype, c_isubject, c_icontent, c_readcount, c_idate,ref,re_step,re_revel, r from " +
"(select c_ino, d_no, c_itype, c_isubject, c_icontent, c_readcount, c_idate,ref,re_step,re_revel, rownum r from " + 
"(select * from InquireBoard order by c_idate desc) order by c_idate desc) where r >= "+startRow+" and r <= " +endRow
	        		);
	       		 
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()){
	           articleList = new ArrayList<InquireDto>();
	           do{
	        	   InquireDto article = new InquireDto();
	        	   article.setC_ino(rs.getInt("c_ino"));
	        	   article.setD_no(rs.getInt("d_no"));
	        	   article.setC_itype(rs.getInt("c_itype"));
	        	   article.setC_isubject(rs.getString("c_isubject"));
	        	   article.setC_icontent(rs.getString("c_icontent"));
	        	   article.setC_readcount(rs.getInt("c_readcount"));
	        	   article.setC_idate(rs.getTimestamp("c_idate"));
	        	   article.setRef(rs.getInt("ref"));
	        	   article.setRe_step(rs.getInt("re_step"));
	        	   article.setRe_revel(rs.getInt("re_revel"));
	              articleList.add(article);
	                          
	           }while(rs.next());
	        }
	     }catch(Exception e){
	        e.printStackTrace();
	     }finally{
	        if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
	        if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
	        if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
	     }
	     return articleList;
	  }   
	 
		//---------------------------관리자가 보는 문의 내역 List 끝-------------------------------------// 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 }
	 


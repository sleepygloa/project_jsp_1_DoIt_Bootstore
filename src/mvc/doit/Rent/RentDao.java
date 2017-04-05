package mvc.doit.Rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.doit.Rent.RentDto;
import mvc.doit.SuperAction.SuperAction;


public class RentDao {
private static RentDao instance = new RentDao();
    
    public static RentDao getInstance() {return instance; }
    
    private RentDao() {}
    
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
      return ds.getConnection();
    }
	
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
  
    //------------------------------------ 고유 코드 임시 생성 코드 -----------------------------------------------------//
    public String code_gen(){
    	//랜덤 숫자 출력
    	double br_double = Math.random();
    	//최대 7자리 정수형 저장
    	int br_int = (int)(br_double * 9999999 ) + 1;
    	
    	String br_code1 = Integer.toString(br_int);//완성된 랜덤 코드 저장
    	
    	return br_code1;
    }
    //------------------------------------ 고유 코드 임시 생성 코드 끝 -----------------------------------------------------//
    
    //--------------------------------------- 도서 고유코드 자동 생성기  -------------------------------------------//
    public String code_generic(String tab_name) throws Exception{
		//결과 저장소
    	String code_re = null;
    	
    	try{
    		RentDao rdd = RentDao.getInstance();
    		String im = rdd.code_gen();
    		
    		conn = getConnection();
    		String sql = "select count(*) from (select br_code from "+tab_name+" where br_code = ?)";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, "B"+im);
    		
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()){
    			int ss = 0;
    			ss = rs.getInt(1);
    			
    			if(ss == 0){
        			code_re = im;
        		}
    			else if(ss != 0){
    	    		im = rdd.code_gen();
    	    	    conn.prepareStatement(sql);
    	    	    pstmt.setString(1, im);
    	    	    rs = pstmt.executeQuery();
    	    	    
    	    	    ss = rs.getInt(1);
        		}
    		}
    
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}

    	return code_re;
    }
    
 	//--------------------------------------- 도서 고유코드 자동 생성기 B 끝 -------------------------------------------//
    
 
    
    //=============대여품목입력=============대여품목입력===========대여품목입력============
    public void insertBook(RentDto dto) throws Exception {
  		
	      try{
	    	  
	        conn=getConnection();
	        String sql = "insert into b_rent values(br_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
	        pstmt = conn.prepareStatement(sql);
	   
	        pstmt.setString(1, dto.getBr_code());
	        pstmt.setString(2, dto.getBr_thumpic());
	        pstmt.setString(3, dto.getBr_name());
	        pstmt.setString(4, dto.getBr_pub());
	        pstmt.setString(5, dto.getBr_writer());
	        pstmt.setString(6, dto.getBr_sname());
	        pstmt.setString(7, dto.getBr_cont());
	        pstmt.setInt(8, dto.getD_bno());
	        pstmt.setInt(9, dto.getBr_don());
	        pstmt.setInt(10, dto.getBr_admin());	         
	        pstmt.setInt(11, dto.getBr_wait());

			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt !=null){try{pstmt.close();}catch(SQLException s){}}
			if(conn !=null){try{conn.close();}catch(SQLException s){}}	
		}		
	      
	}
    //=============대여품목입력=============대여품목입력===========대여품목입력============
    
    
    //---------------------------------------------- 도서 입력시 연체 레코드 자동생성 ------------------------------------------//
    public void insOver(String br_code) throws Exception{
    	
    	try{
    		conn = getConnection();
    		String sql = "insert into b_rent_over values (br_seq.currval,?,',',0,'')";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, br_code); //도서코드 입력
    		
    		pstmt.executeUpdate();
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}
    	
    }
    
    
    //---------------------------------------------- 도서 입력시 연체 레코드 자동생성 끝------------------------------------------//
  
    //--------------------------대여품목리스트-----------대여품목리스트------------------대여품목리스트
	public List getArticles(String sort,int start, int end) throws Exception {	
		List articleList=null;
		
		try {
			conn = getConnection();
					pstmt = conn.prepareStatement(
							//"select * from b_rent order by br_date desc"
							"select br_no,br_code,br_thumpic,br_name,br_pub,br_writer,br_sname,br_cont,d_bno,br_don,br_admin,br_wait,br_date,r "
							+
							"from (select br_no,br_code,br_thumpic,br_name,br_pub,br_writer,br_sname,br_cont,d_bno,br_don,br_admin,br_wait,br_date,rownum r " +
							"from (select br_no,br_code,br_thumpic,br_name,br_pub,br_writer,br_sname,br_cont,d_bno,br_don,br_admin,br_wait,br_date " +
							"from b_rent order by br_no desc) order by br_no desc ) where r >= ? and r <= ? order by "+sort
							);	
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 
					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(end); 
						do{ 
							RentDto book= new RentDto();
							book.setBr_no(Integer.parseInt(rs.getString("br_no")));
							book.setBr_code(rs.getString("br_code"));	    	            	
	    	            	book.setBr_thumpic(rs.getString("br_thumpic"));
	    	            	book.setBr_name(rs.getString("br_name"));
	    	            	book.setBr_pub(rs.getString("br_pub"));
	    	            	book.setBr_writer(rs.getString("br_writer"));
	    	            	book.setBr_sname(rs.getString("br_sname"));
	    	            	book.setBr_cont(rs.getString("br_cont"));
	    	            	book.setD_bno(Integer.parseInt(rs.getString("d_bno")));
	    	            	book.setBr_don(Integer.parseInt(rs.getString("br_don")));
	    	            	book.setBr_admin(Integer.parseInt(rs.getString("br_admin")));
	    	            	book.setBr_wait(Integer.parseInt(rs.getString("br_wait")));
	    	            	book.setBr_date(rs.getTimestamp("br_date"));
							articleList.add(book); 
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return articleList;
	}
  
  
	
	public int getArticleCount() throws Exception {
	
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from b_rent");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; 
	}
	
	 //--------------------------대여품목리스트 끝-----------대여품목리스트 끝------------------대여품목리스트 끝
	
	
	
	
	
	
	
//////*------------------------------------------- 도서 내용 상세보기 내용 파트 ------------------------------------------------------------------------------------------------*////////	
	
	//----------------------------------- 도서 내용 상세보기 [ 내용 출력 ] -----------------------------------------------// 
	public RentDto getDetail(String br_no){
		 RentDto DeDto = new RentDto();//객체 생성
		
		try{
			conn = getConnection();//DB연결
			String sql = "select * from b_rent where br_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, br_no);
			rs = pstmt.executeQuery();//쿼리 실행
			
			if(rs.next()){
				DeDto.setBr_no(rs.getInt("br_no"));
				DeDto.setBr_code(rs.getString("br_code"));
				DeDto.setBr_thumpic(rs.getString("br_thumpic"));
				DeDto.setBr_name(rs.getString("br_name"));
				DeDto.setBr_pub(rs.getString("br_pub"));
				DeDto.setBr_writer(rs.getString("br_writer"));
				DeDto.setBr_sname(rs.getString("br_sname"));
				DeDto.setBr_cont(rs.getString("br_cont"));
				DeDto.setD_bno(rs.getInt("d_bno"));
				DeDto.setBr_don(rs.getInt("br_don"));
				DeDto.setBr_admin(rs.getInt("br_admin"));
				DeDto.setBr_wait(rs.getInt("br_wait"));
				DeDto.setBr_date(rs.getTimestamp("br_date"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return DeDto;
	}
	//----------------------------------- 도서 내용 상세보기 [ 내용 출력 ] 끝-----------------------------------------------// 
	
	
	
	
	
	//----------------------------------- 기부자가 기부한 다른 도서 출력 [ 중앙 ] ---------------------------------------//
	public List getGibu(int mem_no) throws Exception{
		List gibuList = null;
		
		try{
			
			conn = getConnection();
			String sql = "select * from b_rent where br_don = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				gibuList = new ArrayList();
				do{ 
					RentDto book= new RentDto();
					book.setBr_no(Integer.parseInt(rs.getString("br_no")));
					book.setBr_code(rs.getString("br_code"));	    	            	
	            	book.setBr_thumpic(rs.getString("br_thumpic"));
	            	book.setBr_name(rs.getString("br_name"));
	            	book.setBr_pub(rs.getString("br_pub"));
	            	book.setBr_writer(rs.getString("br_writer"));
	            	book.setBr_sname(rs.getString("br_sname"));
	            	book.setBr_cont(rs.getString("br_cont"));
	            	book.setD_bno(Integer.parseInt(rs.getString("d_bno")));
	            	book.setBr_don(Integer.parseInt(rs.getString("br_don")));
	            	book.setBr_admin(Integer.parseInt(rs.getString("br_admin")));
	            	book.setBr_wait(Integer.parseInt(rs.getString("br_wait")));
	            	book.setBr_date(rs.getTimestamp("br_date"));
	            	gibuList.add(book); 
				}while(rs.next());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return gibuList;
	}
	
	
	//----------------------------------- 기부자가 기부한 다른 도서 출력 [ 중앙 ] 끝---------------------------------------//
	
	
	
	
	//----------------------------------- 장르가 같은 추천 도서 출력 [ 우측 ] -----------------------------------------------// 
	public List getJang(int D_bno, String br_no) throws Exception{
		List jangList = null;
		int br_no2 = Integer.parseInt(br_no);
		
		try{
			conn = getConnection();
			String sql = "select * from b_rent where d_bno = ? and br_no != ? order by br_no asc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, D_bno);
			pstmt.setInt(2, br_no2);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				jangList = new ArrayList(); //리스트 객체 생성
				for(int i = 0; i < 4; i++){
					RentDto DeDto = new RentDto();
					DeDto.setBr_no(rs.getInt("br_no"));
					DeDto.setBr_code(rs.getString("br_code"));
					DeDto.setBr_thumpic(rs.getString("br_thumpic"));
					DeDto.setBr_name(rs.getString("br_name"));
					DeDto.setBr_pub(rs.getString("br_pub"));
					DeDto.setBr_writer(rs.getString("br_writer"));
					DeDto.setBr_sname(rs.getString("br_sname"));
					DeDto.setBr_cont(rs.getString("br_cont"));
					DeDto.setD_bno(rs.getInt("d_bno"));
					DeDto.setBr_don(rs.getInt("br_don"));
					DeDto.setBr_admin(rs.getInt("br_admin"));
					DeDto.setBr_wait(rs.getInt("br_wait"));
					DeDto.setBr_date(rs.getTimestamp("br_date"));
					jangList.add(DeDto);
					rs.next();
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return jangList;
	}
	
	
	//----------------------------------- 장르가 같은 추천 도서 출력 [ 우측 ] 끝 -----------------------------------------------// 
	
	
	
	//----------------------------------- 도서 댓글 추가하기 ---------------------------------------------------------------//
	public void insertReply(String br_no, String re_writer, String re_pic, String re_cont, String re_title){
		
		try{
			conn = getConnection();
			String sql = "insert into b_reply values(b_rep.nextval,?,?,?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, re_pic);
			pstmt.setString(2, re_title);
			pstmt.setString(3, re_cont);
			pstmt.setString(4, re_writer);
			pstmt.setString(5, br_no);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
	}
	//----------------------------------- 도서 댓글 추가하기 끝 ---------------------------------------------------------------//
	
	
	
	//----------------------------------- 도서 댓글 출력하기 ---------------------------------------------------------------//
	
	 public List getReply(String br_no, int start, int end){
	      List re_List = null;
	      
	      try{
	         conn = getConnection();
	         String sql ="select b_no,b_pic,b_title,b_cont,b_id,b_date,br_no,r "
	               +
	               "from (select b_no,b_pic,b_title,b_cont,b_id,b_date,br_no,rownum r " +
	               "from (select b_no,b_pic,b_title,b_cont,b_id,b_date,br_no " +
	               "from b_reply order by br_no desc) order by br_no desc ) where br_no = ? and r >= ? and r <= ? order by b_no desc";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, br_no);
	         pstmt.setInt(2, start); 
	         pstmt.setInt(3, end);
	         
	         rs = pstmt.executeQuery();
	         if(rs.next()){
	            re_List = new ArrayList(end);
	            do{ 
	               RentReplyDto dto = new RentReplyDto();
	               dto.setB_no(rs.getInt("b_no"));
	               dto.setB_pic(rs.getString("b_pic"));       
	               dto.setB_title(rs.getString("b_title"));   
	               dto.setB_cont(rs.getString("b_cont"));   
	               dto.setB_id(rs.getString("b_id"));   
	               dto.setB_date(rs.getTimestamp("b_date"));   
	               dto.setBr_no(rs.getInt("br_no"));   
	               re_List.add(dto);
	            }while(rs.next());
	         }
	         
	      }catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	         if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	         if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	      }
	      
	      
	      return re_List;
	   }
	   
	   //----------------------------------- 도서 댓글 출력하기 끝---------------------------------------------------------------//
	   
	   //----------------------------------- 댓글리스트 페이지 번호---------------------------------------------------------------//
	   
	   
	   
	   
	   public int getReplyCount(String br_no) throws Exception {
	      int x= 0;      
	      try {
	         conn=getConnection();
	         pstmt=conn.prepareStatement(
	               "select count(*) from b_reply where br_no = ?"   );
	         pstmt.setString(1,br_no);
	         
	         rs=pstmt.executeQuery();
	         
	         if(rs.next()){
	            x=rs.getInt(1);
	         }         
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	         if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	         if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	      }      
	      return x; 
	   }
	   
	   //----------------------------------- 댓글리스트 페이지 번호 끝---------------------------------------------------------------//   
	
	
	   //----------------------------------- 댓글 삭제 ----------------------------------------------------------------------------------//
	   public void deleteReview(int b_no) throws Exception {

	         try {
	            conn = getConnection();
	            pstmt = conn.prepareStatement(
	                  "delete from b_reply where b_no=?");
	            pstmt.setInt(1, b_no);
	            pstmt.executeUpdate();
	         
	         } catch(Exception ex) {
	            ex.printStackTrace();
	         } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	         }
	         
	      
	         
	      }
	   
	   //----------------------------------- 댓글 삭제 ----------------------------------------------------------------------------------//
	   
	   
	   //----------------------------------- 리스트 검색 내용 파트--------------------------------------------------------------//   
	   
	   //검색 된 내용 출력
	   public List getSearch(int start, int end, String jang, String word) throws Exception {	
			List articleList=null;
			
			try {
				conn = getConnection();
						pstmt = conn.prepareStatement(
								//"select * from b_rent order by br_date desc"
								"select br_no,br_code,br_thumpic,br_name,br_pub,br_writer,br_sname,br_cont,d_bno,br_don,br_admin,br_wait,br_date,r "
								+
								"from (select br_no,br_code,br_thumpic,br_name,br_pub,br_writer,br_sname,br_cont,d_bno,br_don,br_admin,br_wait,br_date,rownum r " +
								"from (select br_no,br_code,br_thumpic,br_name,br_pub,br_writer,br_sname,br_cont,d_bno,br_don,br_admin,br_wait,br_date " +
								"from b_rent where "+jang+" like '%"+word+"%' order by br_no desc) order by br_no desc ) where r >= ? and r <= ? order by br_no"
								);	
						pstmt.setInt(1, start); 
						pstmt.setInt(2, end); 
						rs = pstmt.executeQuery();
						if (rs.next()) {
							articleList = new ArrayList(end); 
							do{ 
								RentDto book= new RentDto();
								book.setBr_no(Integer.parseInt(rs.getString("br_no")));
								book.setBr_code(rs.getString("br_code"));	    	            	
		    	            	book.setBr_thumpic(rs.getString("br_thumpic"));
		    	            	book.setBr_name(rs.getString("br_name"));
		    	            	book.setBr_pub(rs.getString("br_pub"));
		    	            	book.setBr_writer(rs.getString("br_writer"));
		    	            	book.setBr_sname(rs.getString("br_sname"));
		    	            	book.setBr_cont(rs.getString("br_cont"));
		    	            	book.setD_bno(Integer.parseInt(rs.getString("d_bno")));
		    	            	book.setBr_don(Integer.parseInt(rs.getString("br_don")));
		    	            	book.setBr_admin(Integer.parseInt(rs.getString("br_admin")));
		    	            	book.setBr_wait(Integer.parseInt(rs.getString("br_wait")));
		    	            	book.setBr_date(rs.getTimestamp("br_date"));
								articleList.add(book); 
							}while(rs.next());
						}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return articleList;
		}
	  
	  
	   	//검색 내용 갯수 파악
		public int getSearchCount(String gugu,String word) throws Exception {
		
			int x=0;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from b_rent where "+gugu+" like '%"+word+"%'");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					x= rs.getInt(1); 
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return x; 
		}
	   
	
	   //----------------------------------- 리스트 검색 내용 파트 끝--------------------------------------------------------------//   
	
	
}

























package mvc.doit.ResellBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.doit.Login.LoginDto;

public class BidbookDao{

	private static BidbookDao instance = new BidbookDao();
    
    public static BidbookDao getInstance() {return instance; }
    
    private BidbookDao() {}
    
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
      return ds.getConnection();
    }
	
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
	//-------------------------
    //-=------경매 등록--------------------------
    @SuppressWarnings("resource")
	public void insertArticle(BidbookDto bid) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int bid_no = 0;
		int number= 0;
		String sql="";
		int x = 0;
		try {
			conn = getConnection();
			//pstmt = conn.prepareStatement("select max(bid_no) from bidbook ");
			
			//rs = pstmt.executeQuery();
			
			//if(rs.next()){
			//	number=rs.getInt(1)+1;
			//}else{
			//	number=1;
			//}
			
			sql ="insert into bidbook(bid_no,bid_id,bid_name,bid_price1,bid_pic,bid_subject,bid_content,bid_reg_date,bid_time_value)"
					+ " values(bidbook_seq.NEXTVAL,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bid.getBid_id());
			pstmt.setString(2, bid.getBid_name());
			pstmt.setInt(3, bid.getBid_price1());
			pstmt.setString(4, bid.getBid_pic());
			pstmt.setString(5, bid.getBid_subject());
			pstmt.setString(6, bid.getBid_content());
			pstmt.setTimestamp(7, bid.getBid_reg_date());
			pstmt.setInt(8, bid.getBid_time_value());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("select max(bid_no) as bid_no from bidbook");
			rs = pstmt.executeQuery();
			if(rs.next()){
				x=rs.getInt(1);
			}
			bid_no = x;
			
			pstmt = conn.prepareStatement("update bidbook set bid_price2=? where bid_no=?");
			pstmt.setInt(1, bid.getBid_price1());
			pstmt.setInt(2, bid_no);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("update bidbook set bid_finish_date=?+? where bid_no=?");
			pstmt.setTimestamp(1, bid.getBid_reg_date());
			pstmt.setInt(2, bid.getBid_time_value());
			pstmt.setInt(3, bid_no);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
    
  //---------------------전체글개수----------------------------------------------
  	public int getBidCount() throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		int x=0;
  		try {
  			conn = getConnection();
  			pstmt = conn.prepareStatement("select count(*) from bidbook");
  			rs = pstmt.executeQuery();
  			
  			if(rs.next()){
  				x=rs.getInt(1);
  			}
  		} catch (Exception ex) {
  			ex.printStackTrace();
  		}finally{
  			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
  			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  		}
  		return x;
  	}
  	//---------------------전체목록-------------------------------------------
  		public List getBidList(int start ,int end) throws Exception{
  			Connection conn = null;
  			PreparedStatement pstmt = null;
  			ResultSet rs = null;
  			List articleList = null;
  			try {
  				conn = getConnection();
  				pstmt = conn.prepareStatement(
  				"select bid_no,bid_id,bid_name,bid_price1,bid_price2,bid_pic,bid_subject,bid_content,bid_readcount,bid_reg_date,bid_count,bid_last_id,bid_finish,r " +
  				"from(select bid_no,bid_id,bid_name,bid_price1,bid_price2,bid_pic,bid_subject,bid_content,bid_readcount,bid_reg_date,bid_count,bid_last_id,bid_finish,rownum r " +
  				"from(select bid_no,bid_id,bid_name,bid_price1,bid_price2,bid_pic,bid_subject,bid_content,bid_readcount,bid_reg_date,bid_count,bid_last_id,bid_finish " +
  				"from bidbook order by bid_no desc)order by r  asc )  where r >= ? and r <= ?");
  				
  				pstmt.setInt(1, start);
  				pstmt.setInt(2, end);
  				
  				rs = pstmt.executeQuery();
  				if(rs.next()){
  					articleList = new ArrayList(end);
  					do {
  						BidbookDto dto = new BidbookDto();
  						dto.setBid_no(rs.getInt("bid_no"));
  						dto.setBid_id(rs.getString("bid_id"));
  						dto.setBid_name(rs.getString("bid_name"));
  						dto.setBid_price1(rs.getInt("bid_price1"));
  						dto.setBid_price2(rs.getInt("bid_price2"));
  						dto.setBid_pic(rs.getString("bid_pic"));
  						dto.setBid_subject(rs.getString("bid_subject"));
  						dto.setBid_content(rs.getString("bid_content"));
  						dto.setBid_readcount(rs.getInt("bid_readcount"));
  						dto.setBid_reg_date(rs.getTimestamp("bid_reg_date"));
  						dto.setBid_count(rs.getInt("bid_count"));
  						dto.setBid_last_id(rs.getString("bid_last_id"));
  						dto.setBid_finish(rs.getInt("bid_finish"));
  						
  						articleList.add(dto);
  					} while (rs.next());
  				}
  			} catch (Exception ex) {
  				ex.printStackTrace();
  			}finally {
  				if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
  		         if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
  		         if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
  			}
  			return articleList;
  		} 
  		
  	//-------------경매게시글검색개수--------------------------------------------------------------
  		public int getBidSearchCount(String search) throws Exception{
  			Connection conn =null;
  			PreparedStatement pstmt =null;
  			ResultSet rs = null;
  			int x = 0;
  			
  			try {
  				conn = getConnection();
  				pstmt = conn.prepareStatement("select count(*) from bidbook where bid_name like '%"+search+"%'");
  				
  				rs = pstmt.executeQuery();
  				
  				if(rs.next()){
  					x= rs.getInt(1);
  				}
  			} catch (Exception ex) {
  				ex.printStackTrace();
  			}finally {
  				if(rs != null)try {rs.close();} catch (SQLException ex) {}
  				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  			}
  			return x;
  		}
  		
  	//-------------경매목록 검색-------------------------------------------------------------------
  		public List getBidSearch(String search) throws Exception{
  			Connection conn = null;
  			PreparedStatement pstmt =null;
  			ResultSet rs = null;
  			List articleList =null;
  				
  			try{
  				conn =getConnection();
  				pstmt = conn.prepareStatement(
  						"select * from bidbook where bid_name like '%"+search+"%'");
  				rs =pstmt.executeQuery();
  				if(rs.next()){
  					articleList = new ArrayList();
  					do {
  						BidbookDto dto = new BidbookDto();
  						dto.setBid_no(rs.getInt("bid_no"));
  						dto.setBid_id(rs.getString("bid_id"));
  						dto.setBid_name(rs.getString("bid_name"));
  						dto.setBid_price1(rs.getInt("bid_price1"));
  						dto.setBid_price2(rs.getInt("bid_price2"));
  						dto.setBid_pic(rs.getString("bid_pic"));
  						dto.setBid_subject(rs.getString("bid_subject"));
  						dto.setBid_content(rs.getString("bid_content"));
  						dto.setBid_readcount(rs.getInt("bid_readcount"));
  						dto.setBid_reg_date(rs.getTimestamp("bid_reg_date"));
  						dto.setBid_count(rs.getInt("bid_count"));
  						dto.setBid_last_id(rs.getString("bid_last_id"));
  						dto.setBid_finish(rs.getInt("bid_finish"));
  						articleList.add(dto);
  					} while (rs.next());
  				}
  			}catch (Exception ex) {
  				ex.printStackTrace();
  			}finally {
  				if(rs !=null)try{rs.close();}catch(SQLException ex){}
  				if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
  				if(conn !=null)try{conn.close();}catch(SQLException ex){}
  			}
  			return articleList;
  		}
  		
  	//------경매list에서 등급확인------------------------------------------------------------------------------
  		public LoginDto getGrade(String id) {
  				// TODO Auto-generated method stub
  				
  				
  					     Connection conn = null;
  					     PreparedStatement pstmt = null;
  					     ResultSet rs = null;
  					     LoginDto dto=null;
  					     try {
  					         conn = getConnection();
  					            
  					         pstmt = conn.prepareStatement(
  					         	"select * from d_member where d_id = ?");
  					         pstmt.setString(1, id);
  					         rs = pstmt.executeQuery();

  					         if (rs.next()) {
  					         	dto = new LoginDto();
  					         	dto.setD_id(rs.getString("d_id"));
  					         	dto.setD_mech_grade(rs.getInt("d_mech_grade"));
  								    
  					         }
  					         } catch(Exception ex) {
  					            ex.printStackTrace();
  					        } finally {
  					            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
  					            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  					            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  					        }
  					        
  							return dto;
  			
  			}
  	//------------경매목록상세페이지-------------------------------------------------------------------
  		public BidbookDto getBidArticle(int bid_no)throws Exception{
  			Connection conn =null;
  			PreparedStatement pstmt =null;
  			ResultSet rs = null;
  			BidbookDto article = null;
  			
  			try {
  				conn= getConnection();
  				pstmt= conn.prepareStatement(
  						"update bidbook set bid_readcount= bid_readcount+1 where bid_no =?");
  				pstmt.setInt(1, bid_no);
  				pstmt.executeUpdate();
  				
  				pstmt= conn.prepareStatement(
  						"select * from bidbook where bid_no=?");
  				pstmt.setInt(1, bid_no);
  				rs= pstmt.executeQuery();
  				
  				if(rs.next()){
  					article = new BidbookDto();
  					article.setBid_no(rs.getInt("bid_no"));
  					article.setBid_id(rs.getString("bid_id"));
  					article.setBid_name(rs.getString("bid_name"));
  					article.setBid_price1(rs.getInt("bid_price1"));
  					article.setBid_price2(rs.getInt("bid_price2"));
  					article.setBid_pic(rs.getString("bid_pic"));
  					article.setBid_subject(rs.getString("bid_subject"));
  					article.setBid_content(rs.getString("bid_content"));
  					article.setBid_readcount(rs.getInt("bid_readcount"));
  					article.setBid_reg_date(rs.getTimestamp("bid_reg_date"));
  					article.setBid_count(rs.getInt("bid_count"));
  					article.setBid_last_id(rs.getString("bid_last_id"));
  					article.setBid_finish(rs.getInt("bid_finish"));
  					article.setBid_finish_date(rs.getTimestamp("bid_finish_date"));
  				}
  			} catch (Exception ex) {
  				ex.printStackTrace();
  			}finally{
  				if( rs != null ){ try{ rs.close(); }catch( SQLException se ){}};
  	            if( pstmt != null ){ try{ pstmt.close(); }catch( SQLException se ){}};
  	            if( conn != null ){ try{ conn.close(); }catch( SQLException se ){}};
  			}
  			
  			return article;
  		}
  	//----------입찰하기 누르면 그상품에 대해 행해지는 조건들
        public boolean getBidClick(String bid_id, int bid_no)throws Exception{
           Connection conn =null;
           PreparedStatement pstmt =null;
           ResultSet rs = null;
           boolean check= false; //입찰 누르기 전
           
           try {
              conn= getConnection();
              pstmt= conn.prepareStatement(
                    "update bidbook set bid_price2= bid_price2+(bid_price1*0.25), bid_count=bid_count+1, bid_last_id=? where bid_no =?");
              pstmt.setString(1, bid_id);
              pstmt.setInt(2, bid_no);
              pstmt.executeUpdate();
              
              pstmt= conn.prepareStatement(
                    "insert into bidbid(bid_no,bid_bid)"
                          + " values(?,?)");
                    
              pstmt.setInt(1, bid_no);
              pstmt.setString(2, bid_id);
              
              
              pstmt.executeUpdate();
           
           
              check =true;   
              
           } catch (Exception ex) {
              ex.printStackTrace();
           }finally{
              if( rs != null ){ try{ rs.close(); }catch( SQLException se ){}};
                 if( pstmt != null ){ try{ pstmt.close(); }catch( SQLException se ){}};
                 if( conn != null ){ try{ conn.close(); }catch( SQLException se ){}};
           }
           
           return check;
        }
  		
  		//경매시간 종료되면 판매완료 되는 과정
  		public void getFinishArticle(int bid_no)throws Exception{
  			Connection conn =null;
  			PreparedStatement pstmt =null;
  			ResultSet rs = null;
  			
  			
  			try {
  				conn= getConnection();
  				pstmt= conn.prepareStatement(
  						"update bidbook set bid_finish=1 where bid_finish_date<sysdate and bid_no=?");
  				pstmt.setInt(1, bid_no);
  				pstmt.executeUpdate();
  				
  				
  				
  			} catch (Exception ex) {
  				ex.printStackTrace();
  			}finally{
  				if( rs != null ){ try{ rs.close(); }catch( SQLException se ){}};
  	            if( pstmt != null ){ try{ pstmt.close(); }catch( SQLException se ){}};
  	            if( conn != null ){ try{ conn.close(); }catch( SQLException se ){}};
  			}
  			
  		}
  		
//------경매 입찰자에게 판매자정보 개수------------------------------------------------------------------------------------------------------
  		public int getBidSellerCount(String id) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=0;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*)from bidbook where bid_last_id=? and bid_finish=1 "); 
				pstmt.setString(1, id);
				rs = pstmt.executeQuery(); //count 대입, count 가 0 일때는 글이 없는것
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
//------경매 입찰자에게 판매자정보 리스트------------------------------------------------------------------------------------------------------  
  		public List getBidSellerList(String d_id) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList=null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select * from (select a.d_pic,a.d_id,a.d_name,a.d_phone,a.d_mail,b.bid_no,b.bid_subject,b.bid_name,b.bid_price2,"
						+ "b.bid_last_id,b.bid_finish from d_member a, bidbook b where a.d_id=b.bid_id ) where  bid_last_id= ? and bid_finish=1");
		pstmt.setString(1, d_id);
		rs = pstmt.executeQuery();
		if(rs.next()){
			articleList = new ArrayList();
			do {
				LoginDto article = new LoginDto();
				article.setD_pic(rs.getString("d_pic"));
                article.setD_id(rs.getString("d_id"));
                article.setD_name(rs.getString("d_name"));
                
                String pho = rs.getString("d_phone");
                String pho_cut[] = pho.split("-");
                article.setUser_phone1(pho_cut[0]);
                article.setUser_phone2(pho_cut[1]);
                article.setUser_phone3(pho_cut[2]);
                
                
                String e_mai = rs.getString("d_mail");
                String mail_cut[] = e_mai.split(" @ ");
                article.setUser_mail1(mail_cut[0]);
                article.setUser_mail2(mail_cut[1]);
                
                article.setBid_no(rs.getInt("bid_no"));
                article.setBid_subject(rs.getString("bid_subject"));
                article.setBid_name(rs.getString("bid_name"));
                article.setBid_price2(rs.getInt("bid_price2"));
                article.setBid_last_id(rs.getString("bid_last_id"));
                
                
				articleList.add(article);
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
  		
  		
  	//--------------내가쓴 글 개수(경매)------------------------------------------------------------------------------------------
  			public int getMyBidBidListCount(String id) throws Exception {
  				Connection conn = null;
  				PreparedStatement pstmt = null;
  				ResultSet rs = null;
  				int x=0;
  				try {
  					conn = getConnection();
  					pstmt = conn.prepareStatement("select count(*) from bidbook where bid_id=?"); 
  					pstmt.setString(1, id);
  					rs = pstmt.executeQuery(); //count 대입, count 가 0 일때는 글이 없는것
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
  	//---------------------내가쓴 글 목록(경매)-------------------------------------------
  			 public List getMyBidBidList(String d_id, int start,int end) throws Exception {
  						Connection conn = null;
  						PreparedStatement pstmt = null;
  						ResultSet rs = null;
  						List articleList=null;
  						try {
  							conn = getConnection();
  							pstmt = conn.prepareStatement(
"select bid_no,bid_id,bid_name,bid_price1,bid_price2,bid_pic,bid_subject,bid_content,bid_readcount,bid_reg_date,bid_count,bid_last_id,bid_finish,d_id,d_name,d_phone,d_mail " +
"from(select bid_no,bid_id,bid_name,bid_price1,bid_price2,bid_pic,bid_subject,bid_content,bid_readcount,bid_reg_date,bid_count,bid_last_id,bid_finish,d_id,d_name,d_phone,d_mail,r " +
"from(select bid_no,bid_id,bid_name,bid_price1,bid_price2,bid_pic,bid_subject,bid_content,bid_readcount,bid_reg_date,bid_count,bid_last_id,bid_finish,d_id,d_name,d_phone,d_mail,rownum r " +
"from(select a.bid_no,a.bid_id,a.bid_name,a.bid_price1,a.bid_price2,a.bid_pic,a.bid_subject,a.bid_content,a.bid_readcount,a.bid_reg_date,a.bid_count,a.bid_last_id,a.bid_finish,b.d_id,b.d_name,b.d_phone,b.d_mail " +
"from bidbook a, d_member b where a.bid_last_id = b.d_id order by bid_no desc)order by r  asc )where bid_id=?)  where r >= ? and r <= ?");
  					pstmt.setString(1, d_id);
  					pstmt.setInt(2, start);
  					pstmt.setInt(3, end);
  					
  					rs = pstmt.executeQuery();
  					if(rs.next()){
  						articleList = new ArrayList(end);
  						do {
  	  						BidbookDto dto = new BidbookDto();
  	  						dto.setBid_no(rs.getInt("bid_no"));
  	  						dto.setBid_id(rs.getString("bid_id"));
  	  						dto.setBid_name(rs.getString("bid_name"));
  	  						dto.setBid_price1(rs.getInt("bid_price1"));
  	  						dto.setBid_price2(rs.getInt("bid_price2"));
  	  						dto.setBid_pic(rs.getString("bid_pic"));
  	  						dto.setBid_subject(rs.getString("bid_subject"));
  	  						dto.setBid_content(rs.getString("bid_content"));
  	  						dto.setBid_readcount(rs.getInt("bid_readcount"));
  	  						dto.setBid_reg_date(rs.getTimestamp("bid_reg_date"));
  	  						dto.setBid_count(rs.getInt("bid_count"));
  	  						dto.setBid_last_id(rs.getString("bid_last_id"));
  	  						dto.setBid_finish(rs.getInt("bid_finish"));
  	  						dto.setD_id(rs.getString("d_id"));
  	  						dto.setD_name(rs.getString("d_name"));
  	  						dto.setD_phone(rs.getString("d_phone"));
	  						dto.setD_mail(rs.getString("d_mail"));
	  						
  	  						articleList.add(dto);
  	  					} while (rs.next());
  									
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
  			 
//-------------내가쓴글 검색개수(경매)--------------------------------------------------------------
  	  		public int BidBidMySearchCount(String search,String id) throws Exception{
  	  			Connection conn =null;
  	  			PreparedStatement pstmt =null;
  	  			ResultSet rs = null;
  	  			int x = 0;
  	  			
  	  			try {
  	  				conn = getConnection();
  	  				pstmt = conn.prepareStatement(
  	  						"select count(*) from (select *from bidbook where bid_id=? )where bid_name like '%"+search+"%'");
  	  				pstmt.setString(1, id);
  	  				rs = pstmt.executeQuery();
  	  				
  	  				if(rs.next()){
  	  					x= rs.getInt(1);
  	  				}
  	  			} catch (Exception ex) {
  	  				ex.printStackTrace();
  	  			}finally {
  	  				if(rs != null)try {rs.close();} catch (SQLException ex) {}
  	  				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  	  				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  	  			}
  	  			return x;
  	  		}
  	  		
//-------------내가쓴글 검색(경매) -------------------------------------------------------------------
  	  		public List BidBidMySearch(String search,String id, int start, int end) throws Exception{
  	  			Connection conn = null;
  	  			PreparedStatement pstmt =null;
  	  			ResultSet rs = null;
  	  			List articleList =null;
  	  				
  	  			try{
  	  				conn =getConnection();
  	  				pstmt = conn.prepareStatement(
  	  					"select * from (select bid_no,bid_id,bid_name,bid_price1,bid_price2,bid_pic,bid_subject,bid_content,bid_readcount,bid_reg_date,bid_count,bid_last_id,bid_finish,d_id,d_name,d_phone,d_mail " +
  	  						"from(select bid_no,bid_id,bid_name,bid_price1,bid_price2,bid_pic,bid_subject,bid_content,bid_readcount,bid_reg_date,bid_count,bid_last_id,bid_finish,d_id,d_name,d_phone,d_mail,r " +
  	  						"from(select bid_no,bid_id,bid_name,bid_price1,bid_price2,bid_pic,bid_subject,bid_content,bid_readcount,bid_reg_date,bid_count,bid_last_id,bid_finish,d_id,d_name,d_phone,d_mail,rownum r " +
  	  						"from(select a.bid_no,a.bid_id,a.bid_name,a.bid_price1,a.bid_price2,a.bid_pic,a.bid_subject,a.bid_content,a.bid_readcount,a.bid_reg_date,a.bid_count,a.bid_last_id,a.bid_finish,b.d_id,b.d_name,b.d_phone,b.d_mail " +
  	  						"from bidbook a, d_member b where a.bid_last_id = b.d_id order by bid_no desc)order by r  asc )where bid_id=?)  where r >= ? and r <= ?) where bid_name like '%"+search+"%'");
  	  				  				pstmt.setString(1, id);
  	  				  				pstmt.setInt(2, start);
  	  				  				pstmt.setInt(3, end);
  	  				  				
  	  				rs =pstmt.executeQuery();
  	  				if(rs.next()){
  	  					articleList = new ArrayList();
  	  					do {
  	  						BidbookDto dto = new BidbookDto();
  	  						dto.setBid_no(rs.getInt("bid_no"));
  	  						dto.setBid_id(rs.getString("bid_id"));
  	  						dto.setBid_name(rs.getString("bid_name"));
  	  						dto.setBid_price1(rs.getInt("bid_price1"));
  	  						dto.setBid_price2(rs.getInt("bid_price2"));
  	  						dto.setBid_pic(rs.getString("bid_pic"));
  	  						dto.setBid_subject(rs.getString("bid_subject"));
  	  						dto.setBid_reg_date(rs.getTimestamp("bid_reg_date"));
  	  						dto.setBid_last_id(rs.getString("bid_last_id"));
  	  						dto.setBid_finish(rs.getInt("bid_finish"));
  	  						dto.setD_id(rs.getString("d_id"));
	  						dto.setD_name(rs.getString("d_name"));
	  						dto.setD_phone(rs.getString("d_phone"));
	  						dto.setD_mail(rs.getString("d_mail"));
  	  						articleList.add(dto);
  	  					} while (rs.next());
  	  				}
  	  			}catch (Exception ex) {
  	  				ex.printStackTrace();
  	  			}finally {
  	  				if(rs !=null)try{rs.close();}catch(SQLException ex){}
  	  				if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
  	  				if(conn !=null)try{conn.close();}catch(SQLException ex){}
  	  			}
  	  			return articleList;
  	  		}  			 
  	  	//---------------------나의 입찰 글개수----------------------------------------------
  	  	  	public int getMyBidCount(String bid_id) throws Exception{
  	  	  		Connection conn = null;
  	  	  		PreparedStatement pstmt = null;
  	  	  		ResultSet rs = null;
  	  	  		int x=0;
  	  	  		try {
  	  	  			conn = getConnection();
  	  	  			pstmt = conn.prepareStatement("select count(*) from (select distinct * from bidbid where bid_bid=?)");
  	  	  			pstmt.setString(1, bid_id);
  	  	  			rs = pstmt.executeQuery();
  	  	  			
  	  	  			if(rs.next()){
  	  	  				x=rs.getInt(1);
  	  	  			}
  	  	  		} catch (Exception ex) {
  	  	  			ex.printStackTrace();
  	  	  		}finally{
  	  	  			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
  	  	  			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  	  	  			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  	  	  		}
  	  	  		return x;
  	  	  	}
  	  	//---------------------나의 입찰목록-------------------------------------------
  	        public List getMyBidList(String bid_id, int start ,int end) throws Exception{
  	           Connection conn = null;
  	           PreparedStatement pstmt = null;
  	           ResultSet rs = null;
  	           List articleList = null;
  	           try {
  	              conn = getConnection();
  	              pstmt = conn.prepareStatement(
  	              "select distinct bid_pic,bid_no,bid_subject,bid_name,bid_price1,bid_price2,bid_id,bid_finish,bid_reg_date,bid_last_id,bid_bid "
  	              + "from (select distinct bid_pic,bid_no,bid_subject,bid_name,bid_price1,bid_price2,bid_id,bid_finish,bid_reg_date,bid_last_id,bid_bid, r "
  	              + "from (select bid_pic,bid_no,bid_subject,bid_name,bid_price1,bid_price2,bid_id,bid_finish,bid_reg_date,bid_last_id,bid_bid, rownum r "
  	              + "from (select a.bid_pic,a.bid_no,a.bid_subject,a.bid_name,a.bid_price1,a.bid_price2,a.bid_id,a.bid_finish,a.bid_reg_date,a.bid_last_id,b.bid_bid "
  	              + "from bidbook a, bidbid b where a.bid_no=b.bid_no) order by r asc ) where bid_bid=?) where r >= ? and r <= ?");
  	              pstmt.setString(1, bid_id);
  	              pstmt.setInt(2, start);
  	              pstmt.setInt(3, end);
  	              
  	              rs = pstmt.executeQuery();
  	              if(rs.next()){
  	                 articleList = new ArrayList(end);
  	                 do {
  	                    BidbookDto dto = new BidbookDto();
  	                    dto.setBid_no(rs.getInt("bid_no"));
  	                    dto.setBid_id(rs.getString("bid_id"));
  	                    dto.setBid_name(rs.getString("bid_name"));
  	                    dto.setBid_price1(rs.getInt("bid_price1"));
  	                    dto.setBid_price2(rs.getInt("bid_price2"));
  	                    dto.setBid_pic(rs.getString("bid_pic"));
  	                    dto.setBid_subject(rs.getString("bid_subject"));
  	                    dto.setBid_reg_date(rs.getTimestamp("bid_reg_date"));
  	                    dto.setBid_last_id(rs.getString("bid_last_id"));
  	                    dto.setBid_finish(rs.getInt("bid_finish"));
  	                    dto.setBid_bid(rs.getString("bid_bid"));
  	                    
  	                    articleList.add(dto);
  	                 } while (rs.next());
  	              }
  	           } catch (Exception ex) {
  	              ex.printStackTrace();
  	           }finally {
  	              if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
  	                 if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
  	                 if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
  	           }
  	           return articleList;
  	        } 
  	  	//-------------나의 입찰 목록검색개수--------------------------------------------------------------
  	  		public int BidMySearchCount(String search,String id) throws Exception{
  	  			Connection conn =null;
  	  			PreparedStatement pstmt =null;
  	  			ResultSet rs = null;
  	  			int x = 0;
  	  			
  	  			try {
  	  				conn = getConnection();
  	  				pstmt = conn.prepareStatement(
  	  						
  	  						"select count(*) from (select distinct * from (select a.bid_name, b.bid_bid from bidbook a, bidbid b where a.bid_no=b.bid_no) where bid_bid=?)where bid_name like '%"+search+"%'");
  	  				pstmt.setString(1, id);
  	  				rs = pstmt.executeQuery();
  	  				
  	  				if(rs.next()){
  	  					x= rs.getInt(1);
  	  				}
  	  			} catch (Exception ex) {
  	  				ex.printStackTrace();
  	  			}finally {
  	  				if(rs != null)try {rs.close();} catch (SQLException ex) {}
  	  				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  	  				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  	  			}
  	  			return x;
  	  		}
  	  		
  	  	//-------------나의 입찰 목록검색 -------------------------------------------------------------------
  	  		public List BidMySearch(String search,String id, int start, int end) throws Exception{
  	  			Connection conn = null;
  	  			PreparedStatement pstmt =null;
  	  			ResultSet rs = null;
  	  			List articleList =null;
  	  				
  	  			try{
  	  				conn =getConnection();
  	  				pstmt = conn.prepareStatement(
  	  						 "select * from (select distinct bid_pic,bid_no,bid_subject,bid_name,bid_price1,bid_price2,bid_id,bid_finish,bid_reg_date,bid_last_id,bid_bid "
  	  				  				+ "from (select distinct bid_pic,bid_no,bid_subject,bid_name,bid_price1,bid_price2,bid_id,bid_finish,bid_reg_date,bid_last_id,bid_bid, r "
  	  				  				+ "from (select bid_pic,bid_no,bid_subject,bid_name,bid_price1,bid_price2,bid_id,bid_finish,bid_reg_date,bid_last_id,bid_bid, rownum r "
  	  				  				+ "from (select a.bid_pic,a.bid_no,a.bid_subject,a.bid_name,a.bid_price1,a.bid_price2,a.bid_id,a.bid_finish,a.bid_reg_date,a.bid_last_id,b.bid_bid "
  	  				  				+ "from bidbook a, bidbid b where a.bid_no=b.bid_no) order by r asc ) where bid_bid=?) where r >= ? and r <= ?) where bid_name like '%"+search+"%'");
  	  				  				pstmt.setString(1, id);
  	  				  				pstmt.setInt(2, start);
  	  				  				pstmt.setInt(3, end);
  	  				  				
  	  				rs =pstmt.executeQuery();
  	  				if(rs.next()){
  	  					articleList = new ArrayList();
  	  					do {
  	  						BidbookDto dto = new BidbookDto();
  	  						dto.setBid_no(rs.getInt("bid_no"));
  	  						dto.setBid_id(rs.getString("bid_id"));
  	  						dto.setBid_name(rs.getString("bid_name"));
  	  						dto.setBid_price1(rs.getInt("bid_price1"));
  	  						dto.setBid_price2(rs.getInt("bid_price2"));
  	  						dto.setBid_pic(rs.getString("bid_pic"));
  	  						dto.setBid_subject(rs.getString("bid_subject"));
  	  						dto.setBid_reg_date(rs.getTimestamp("bid_reg_date"));
  	  						dto.setBid_last_id(rs.getString("bid_last_id"));
  	  						dto.setBid_finish(rs.getInt("bid_finish"));
  	  						dto.setBid_bid(rs.getString("bid_bid"));
  	  						articleList.add(dto);
  	  					} while (rs.next());
  	  				}
  	  			}catch (Exception ex) {
  	  				ex.printStackTrace();
  	  			}finally {
  	  				if(rs !=null)try{rs.close();}catch(SQLException ex){}
  	  				if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
  	  				if(conn !=null)try{conn.close();}catch(SQLException ex){}
  	  			}
  	  			return articleList;
  	  		}
  	  		
  	  	//------------경매목록상세페이지-------------------------------------------------------------------
  	  		public BidbookDto getBidArticle(int bid_no, String id)throws Exception{
  	  			Connection conn =null;
  	  			PreparedStatement pstmt =null;
  	  			ResultSet rs = null;
  	  			BidbookDto article = null;
  	  			
  	  			try {
  	  				conn= getConnection();
  	  				pstmt= conn.prepareStatement(
  	  						"update bidbook set bid_readcount= bid_readcount+1 where bid_no =?");
  	  				pstmt.setInt(1, bid_no);
  	  				pstmt.executeUpdate();
  	  				
  	  				pstmt= conn.prepareStatement(
  	  						"select * from bidbook where bid_no=?"
  	  						);
  	  				pstmt.setInt(1, bid_no);
  	  				rs= pstmt.executeQuery();
  	  				//해당 아이디에 따른 그의 개인기업, 등급을 나타내기위함
  	  			/*	pstmt= conn.prepareStatement(
  	  						"select * from "
  	  						+ "(select a.d_id, a.d_person, b.rbook_finish_count from d_member a, resellintro b where a.d_id=b.d_id) "
  	  						+ "where d_id=?");
  	  				pstmt.setString(1, id);
  	  				rs= pstmt.executeQuery();*/
  	  				
  	  				
  	  				if(rs.next()){

  	  					article = new BidbookDto();
  						article.setBid_no(rs.getInt("bid_no"));
  	  					article.setBid_id(rs.getString("bid_id"));
  	  					article.setBid_name(rs.getString("bid_name"));
  	  					article.setBid_price1(rs.getInt("bid_price1"));
  	  					article.setBid_price2(rs.getInt("bid_price2"));
  	  					article.setBid_pic(rs.getString("bid_pic"));
  	  					article.setBid_subject(rs.getString("bid_subject"));
  	  					article.setBid_content(rs.getString("bid_content"));
  	  					article.setBid_readcount(rs.getInt("bid_readcount"));
  	  					article.setBid_reg_date(rs.getTimestamp("bid_reg_date"));
  	  					article.setBid_count(rs.getInt("bid_count"));
  	  					article.setBid_last_id(rs.getString("bid_last_id"));
  	  					article.setBid_finish(rs.getInt("bid_finish"));
  	  					article.setBid_finish_date(rs.getTimestamp("bid_finish_date"));
  	  					//article.setD_person(rs.getString("d_person"));
  	  					//article.setRbook_finish_count(rs.getInt("rbook_finish_count"));
  	  				}
  	  			} catch (Exception ex) {
  	  				ex.printStackTrace();
  	  			}finally{
  	  				if( rs != null ){ try{ rs.close(); }catch( SQLException se ){}};
  	  	            if( pstmt != null ){ try{ pstmt.close(); }catch( SQLException se ){}};
  	  	            if( conn != null ){ try{ conn.close(); }catch( SQLException se ){}};
  	  			}
  	  			
  	  			return article;
  	  		}
  	  		
  	  	//-----------해당 아이디에 따른 그의 개인기업, 등급을 나타내기위함
  	  		public BidbookDto getBidGrade(String bid_id)throws Exception{
  	  			Connection conn =null;
  	  			PreparedStatement pstmt =null;
  	  			ResultSet rs = null;
  	  			BidbookDto article1 = null;
  	  			try {
  	  				conn= getConnection();
  	  				pstmt= conn.prepareStatement(
  	  						"select * from "
  	  						+ "(select a.d_id, a.d_no, a.d_person, b.rbook_finish_count from d_member a, resellintro b where a.d_id=b.d_id) "
  	  						+ "where d_id=?");
  	  				pstmt.setString(1, bid_id);
  	  				rs= pstmt.executeQuery();
  	  				
  	  				
  	  				if(rs.next()){
  	  					article1 = new BidbookDto();
  	  					
  	  					article1.setD_no(rs.getInt("d_no"));
  						article1.setD_person(rs.getString("d_person"));
  	  					article1.setRbook_finish_count(rs.getInt("rbook_finish_count"));
  	  				
  	  				}
  	  				
  	  			} catch (Exception ex) {
  	  				ex.printStackTrace();
  	  			}finally{
  	  				if( rs != null ){ try{ rs.close(); }catch( SQLException se ){}};
  	  	            if( pstmt != null ){ try{ pstmt.close(); }catch( SQLException se ){}};
  	  	            if( conn != null ){ try{ conn.close(); }catch( SQLException se ){}};
  	  			}
  	  			
  	  			return article1;
  	  		}
  	  		
  	  	//--------경매 글삭제-----------------------------------------------------------------	
  	  		public void bidDeleteArticle(int bid_no){
  	  			Connection conn= null;
  	  			PreparedStatement pstmt=null;
  	  			ResultSet rs = null;
  	  			
  	  			try {
  	  				conn =getConnection();
  	  				pstmt =conn.prepareStatement("delete from bidbook where bid_no=?");
  	  				pstmt.setInt(1, bid_no);
  	  				pstmt.executeUpdate();
  	  			} catch (Exception ex) {
  	  				ex.printStackTrace();
  	  			}finally {
  	  				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
  	  				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  	  				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  	  			}
  	  		}
  	  		
  	  	//----------경매 글수정 받아오기------------------------------------------------------------------
  	  		public BidbookDto BidModifyArticle(int bid_no) throws Exception{
  	  			Connection conn= null;
  	  			PreparedStatement pstmt = null;
  	  			ResultSet rs =null;
  	  			BidbookDto article =null;
  	  			
  	  			try {
  	  				conn=getConnection();
  	  				pstmt=conn.prepareStatement(
  	  						"select * from bidbook where bid_no=?");
  	  				pstmt.setInt(1, bid_no);
  	  				rs = pstmt.executeQuery();
  	  				if(rs.next()){
  	  					article = new BidbookDto();
  	  					article.setBid_no(rs.getInt("bid_no"));
  	  					article.setBid_id(rs.getString("bid_id"));
  	  					article.setBid_name(rs.getString("bid_name"));
  	  					article.setBid_pic(rs.getString("bid_pic"));
  	  					article.setBid_price1(rs.getInt("bid_price1"));
  	  					article.setBid_subject(rs.getString("bid_subject"));
  	  					article.setBid_content(rs.getString("bid_content"));
  	  				}
  	  			} catch (Exception ex) {
  	  				ex.printStackTrace();
  	  			}finally {
  	  				if(rs !=null)try{rs.close();}catch(SQLException ex){}
  	  				if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
  	  				if(conn !=null)try{conn.close();}catch(SQLException ex){}
  	  			}
  	  			
  	  			return article;
  	  		} 
  	  	//--------경매 글수정-------------------------------------------------------------------
  	  		public void BidModifyArticle(BidbookDto article)throws Exception{
  	  			Connection conn =null;
  	  			PreparedStatement pstmt=null;
  	  			ResultSet rs =null;
  	  			
  	  			try {
  	  				conn = getConnection();
  	  				pstmt =conn.prepareStatement(
  	  				"update bidbook set bid_subject=?,bid_name=?,bid_content=?,bid_pic=? where bid_no=?");
  	  				pstmt.setString(1,article.getBid_subject());
  	  				pstmt.setString(2,article.getBid_name());
  	  				pstmt.setString(3,article.getBid_content());
  	  				pstmt.setString(4,article.getBid_pic());
  	  				pstmt.setInt(5,article.getBid_no());
  	  				
  	  				pstmt.executeUpdate();
  	  				
  	  			} catch (Exception ex) {
  	  				ex.printStackTrace();
  	  			}finally {
  	  				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
  	  				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  	  				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  	  			}
  	  		}

  	  	//---------------------해당 아이디 경매하고있는 전체글개수(글 갯수 제한)----------------------------------------------
  	        public int getBidderCount(String bid_id) throws Exception{
  	           Connection conn = null;
  	           PreparedStatement pstmt = null;
  	           ResultSet rs = null;
  	           int x=0;
  	           try {
  	              conn = getConnection();
  	              pstmt = conn.prepareStatement("select count(*) from bidbook where bid_id=? and bid_finish=0");
  	              pstmt.setString(1, bid_id);
  	              rs = pstmt.executeQuery();
  	              
  	              
  	              if(rs.next()){
  	                 x=rs.getInt(1);
  	              }
  	           } catch (Exception ex) {
  	              ex.printStackTrace();
  	           }finally{
  	              if (rs != null) try { rs.close(); } catch(SQLException ex) {}
  	              if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  	              if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  	           }
  	           return x;
  	        }
  	  		
 //마지막 닫는 괄호
  	}

	
	
	
	



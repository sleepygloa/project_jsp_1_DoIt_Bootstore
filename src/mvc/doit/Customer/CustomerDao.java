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
	
	import mvc.doit.Customer.CustomerDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.Online.OnBookDto;
import mvc.doit.ResellBean.ResellbookDto;
	
	public class CustomerDao {
		private static CustomerDao instance = new CustomerDao();
		
		public static CustomerDao getInstance(){
			return instance;
		}
		private CustomerDao(){}
		
		private Connection getConnection() throws Exception{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
			return ds.getConnection();
		}
		
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

//----------------------FAQ게시글 등록-----------------------------------------------
	    public void insertFaq(FaqDto faq){
	  		
		      try{
		         conn=getConnection();
		         String sql = "insert into d_faq values(d_faq_seq.nextval,?,?,?,sysdate,?)";
		         pstmt = conn.prepareStatement(sql);
		    
		         pstmt.setString(1, faq.getFaq_subject());
		         pstmt.setString(2, faq.getFaq_content());
		         pstmt.setString(3, faq.getFaq_writer());
		         pstmt.setInt(4, faq.getFaq_type());

		 
		        
		        
				pstmt.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(pstmt !=null){try{pstmt.close();}catch(SQLException s){}}
				if(conn !=null){try{conn.close();}catch(SQLException s){}}	
			}		
	    }
//---------------------FAQ전체글개수----------------------------------------------
		public int getFaqCount() throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=0;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from d_faq");
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
		
//---------------------faq전체목록-------------------------------------------
		public List getfaqList(int start,int end,String faq_type) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			try {
				conn = getConnection();
				if(!faq_type.equals("")){
				int faqtype = Integer.parseInt(faq_type);
				pstmt = conn.prepareStatement(
						"select faq_no,faq_subject,faq_content,faq_writer,faq_reg_date,faq_type,r " +
		  				"from(select faq_no,faq_subject,faq_content,faq_writer,faq_reg_date,faq_type,rownum r " +
		  				"from(select faq_no,faq_subject,faq_content,faq_writer,faq_reg_date,faq_type " +
		  				"from d_faq order by faq_no desc)order by r  asc )  where r >= ? and r <= ? and faq_type = ?");
							
						pstmt.setInt(1, start);
						pstmt.setInt(2, end);
						pstmt.setInt(3, faqtype);
				}else{
					pstmt = conn.prepareStatement(
					"select faq_no,faq_subject,faq_content,faq_writer,faq_reg_date,faq_type,r " +
	  				"from(select faq_no,faq_subject,faq_content,faq_writer,faq_reg_date,faq_type,rownum r " +
	  				"from(select faq_no,faq_subject,faq_content,faq_writer,faq_reg_date,faq_type " +
	  				"from d_faq order by faq_no desc)order by r  asc )  where r >= ? and r <= ?");
						
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
				}
				rs = pstmt.executeQuery();
				if(rs.next()){
					articleList = new ArrayList(end);
					do {
						FaqDto dto = new FaqDto();
						dto.setFaq_no(rs.getInt("faq_no"));
						dto.setFaq_subject(rs.getString("faq_subject"));
						dto.setFaq_content(rs.getString("faq_content"));
						dto.setFaq_writer(rs.getString("faq_writer"));
						dto.setFaq_reg_date(rs.getTimestamp("faq_reg_date"));
						dto.setFaq_type(rs.getInt("faq_type"));
					
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
		
//-------------faq검색개수--------------------------------------------------------------
		public int getFaqSearchCount(String searchTitle,String search) throws Exception{
			Connection conn =null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			int x = 0;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*)from d_faq where "+searchTitle+" like '%"+search+"%'");
				
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

//-------------faq목록 검색-------------------------------------------------------------------
	public List getfaqListSearch(int start,int end,String searchTitle, String search) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List articleList =null;
			
		try{
			conn =getConnection();
			pstmt = conn.prepareStatement(
					"select faq_no,faq_subject,faq_content,faq_writer,Faq_reg_date,faq_type,r " +
	"from (select faq_no,faq_subject,faq_content,faq_writer,Faq_reg_date,faq_type,rownum r " +
	"from (select faq_no,faq_subject,faq_content,faq_writer,Faq_reg_date,faq_type " +
	"from d_faq where "+searchTitle+" like '%"+search+"%' order by faq_no desc) order by faq_no desc ) where r >= ? and r <= ? order by faq_no");
			pstmt.setInt(1, start); 
			pstmt.setInt(2, end); 
			rs =pstmt.executeQuery();
			if(rs.next()){
				articleList = new ArrayList();
				do {
					FaqDto dto = new FaqDto();
					dto.setFaq_no(rs.getInt("faq_no"));
					dto.setFaq_subject(rs.getString("faq_subject"));
					dto.setFaq_content(rs.getString("faq_content"));
					dto.setFaq_writer(rs.getString("faq_writer"));
					dto.setFaq_reg_date(rs.getTimestamp("faq_reg_date"));
					dto.setFaq_type(rs.getInt("faq_type"));
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
	
//--------FAQ 글삭제-----------------------------------------------------------------	
		public void faqDeleteArticle(int faq_no){
			Connection conn= null;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			try {
				conn =getConnection();
				pstmt =conn.prepareStatement("delete from d_faq where faq_no=?");
				pstmt.setInt(1, faq_no);
				pstmt.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
		}
		
//------FAQ에서 등급확인------------------------------------------------------------------------------
		public LoginDto getGrade(String id) {
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
		
//----------FAQ 글수정------------------------------------------------------------------
		public FaqDto faqUpdateGetArticle(int faq_no) throws Exception{
			Connection conn= null;
			PreparedStatement pstmt = null;
			ResultSet rs =null;
			FaqDto article =null;
			
			try {
				conn=getConnection();
				pstmt=conn.prepareStatement(
						"select * from d_faq where faq_no=?");
				pstmt.setInt(1, faq_no);
				rs = pstmt.executeQuery();
				if(rs.next()){
					article = new FaqDto();
					article.setFaq_no(rs.getInt("faq_no"));
					article.setFaq_subject(rs.getString("faq_subject"));
					article.setFaq_content(rs.getString("faq_content"));
					article.setFaq_writer(rs.getString("faq_writer"));
					article.setFaq_reg_date(rs.getTimestamp("faq_reg_date"));
					
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
		
		
//--------faq 글수정-------------------------------------------------------------------
		public void faqUpdateArticle(FaqDto article)throws Exception{
			Connection conn =null;
			PreparedStatement pstmt=null;
			ResultSet rs =null;
			
			try {
				conn = getConnection();
				pstmt =conn.prepareStatement(
				"update d_faq set faq_subject=?, faq_content=?where faq_no=?");
				pstmt.setString(1,article.getFaq_subject());
				pstmt.setString(2,article.getFaq_content());
				pstmt.setInt(3,article.getFaq_no());
				
				
				pstmt.executeUpdate();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
		}
		
		//----------------------------------------------------------------- 1:1 시작 ------------------------------------------------------------------------------   
	    
		  //------------------------------------------------1:1문의 신청 (회원이 신청 ,관리자 답변)-------------------------------------------------------------- 
		    public void InquireInsert(InquireDto dto, int d_no, String mem){
		  		
				int c_ino = dto.getC_ino();
				int ref = dto.getRef();
				int re_step = dto.getRe_step();
				int re_level = dto.getRe_level();
				int number = 0;
				String sql ="";
				
				try{
					conn = getConnection();
					pstmt = conn.prepareStatement("select max(c_ino) from InquireBoard");
					rs = pstmt.executeQuery();
					if(rs.next()){
						number = rs.getInt(1)+1;
					}else{
						number = 1;
					}
					
					if( c_ino != 0){
						pstmt = conn.prepareStatement("update InquireBoard set re_step = re_step + 1 where ref = ? and re_step > ?");

						pstmt.setInt(1, ref);
						pstmt.setInt(2, re_step);
						pstmt.executeUpdate();
						re_step = re_step + 1;
						re_level = re_level + 1;
					}else{
						ref = number;
						re_step = 0;
						re_level = 0;
					}
					if(mem.equals("user")){
						pstmt = conn.prepareStatement("insert into InquireBoard(c_ino,d_no,c_itype,c_isubject,c_icontent,c_idate,ref,re_step,re_level)"
								+ " values(InquireBoard_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?)");
		
						pstmt.setInt(1, d_no);
						pstmt.setInt(2, dto.getC_itype());
						pstmt.setString(3, dto.getC_isubject());
						pstmt.setString(4, dto.getC_icontent());
						pstmt.setInt(5, ref);
						pstmt.setInt(6, re_step);
						pstmt.setInt(7, re_level);
						pstmt.executeUpdate();
					}else if(mem.equals("admin")){
						
						pstmt = conn.prepareStatement("insert into InquireBoard(c_ino,d_no,c_itype,c_isubject,c_icontent,c_idate,ref,re_step,re_level,reply)"
								+ " values(InquireBoard_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,?)");
		
						pstmt.setInt(1, dto.getD_no());
						pstmt.setInt(2, dto.getC_itype());
						pstmt.setString(3, dto.getC_isubject());
						pstmt.setString(4, dto.getC_icontent());
						pstmt.setInt(5, ref);
						pstmt.setInt(6, re_step);
						pstmt.setInt(7, re_level);
						pstmt.setInt(8, 1);
						pstmt.executeUpdate();
						
						pstmt = conn.prepareStatement("update InquireBoard set reply=1 where c_ino = ?");
		
						
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if (rs != null) try { rs.close(); } catch(SQLException ex) {}
					if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
					if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					
				}
			}
		    //------------------------------------------------ // 1:1문의 신청 (회원 -> 관리자)- 끝------------------------------------------------------------- 
		    
		    //--------------------------- 사용자 문의 내역 count, 답변 대기상태 ---------------------------------------//
			 public int UserInquireCount(int d_no, int reply) throws Exception{
				 int x = 0; //책의 수
			    	try{
			    		conn = getConnection();
			    
			    		pstmt = conn.prepareStatement("select count(*) from InquireBoard where reply=? and d_no=?");
			    		pstmt.setInt(1, reply);
			    		pstmt.setInt(2, d_no);
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
			 //---------------------------------------  // 사용자 문의 내역 count , 답변 대기상태  끝------------------------------------------------------------//
			 
			//-------------------------------------------사용자 문의 내역 리스트, 답변 대기 0, 답변완료 1,----------------------------------------------------//
			 
			 public List<InquireDto> UserInquireList(int startRow, int endRow, int b_no, int reply)  throws Exception{
			     List<InquireDto> articleList = null;
			     System.out.println(reply);
			     try {
			        conn = getConnection();
			        if(reply == 0){
				        pstmt = conn.prepareStatement("select c_ino, d_no, c_itype, c_isubject, c_icontent, c_readcount, c_idate,ref,re_step,re_level, r from " +
				        							"(select c_ino, d_no, c_itype, c_isubject, c_icontent, c_readcount, c_idate,ref,re_step,re_level, rownum r from " + 
				        							"(select * from InquireBoard where reply=? and d_no=? ) order by c_idate desc) where r >= "+startRow+" and r <= " +endRow);
				        pstmt.setInt(1, reply);
				       	pstmt.setInt(2, b_no);
				        rs = pstmt.executeQuery();
				        
				      
			        }else if(reply ==1){
			        	
			        	pstmt = conn.prepareStatement("select c_ino, d_no, c_itype, c_isubject, c_icontent, c_readcount, c_idate,ref,re_step,re_level, r from " +
								"(select c_ino, d_no, c_itype, c_isubject, c_icontent, c_readcount, c_idate,ref,re_step,re_level, rownum r from " + 
								"(select * from InquireBoard where reply=? and d_no=?  order by ref desc, re_step asc)  order by ref desc, re_step asc ) where r >= "+startRow+" and r <= " +endRow);
			        	
						pstmt.setInt(1, reply);
						pstmt.setInt(2, b_no);
						rs = pstmt.executeQuery();
			        }

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
					        	   article.setRe_level(rs.getInt("re_level"));
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
			//-------------------------------------------// 사용자 문의 내역 리스트, 답변 대기상태!!!! 끝----------------------------------------------------//
			 
			//------------------------------------ 문의 내역 상세보기 -----------------------------
				 
				 public InquireDto getArticle(int c_ino) throws Exception {
						Connection conn = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						InquireDto article=null;
						try {
							conn = getConnection();
							pstmt = conn.prepareStatement("update InquireBoard set c_readcount=c_readcount+1 where c_ino = ?"); 
							pstmt.setInt(1, c_ino);
							pstmt.executeUpdate();
							
							pstmt = conn.prepareStatement("select * from InquireBoard where c_ino=?");
				    		pstmt.setInt(1, c_ino);
				    		
							rs = pstmt.executeQuery();
							if (rs.next()) {
								article = new InquireDto();
								article.setC_ino(c_ino); 
								article.setD_no(rs.getInt("d_no"));
								article.setC_itype(rs.getInt("c_itype"));
								article.setC_isubject(rs.getString("c_isubject"));
								article.setC_icontent(rs.getString("c_icontent"));
								article.setC_readcount(rs.getInt("c_readcount"));
								article.setC_idate(rs.getTimestamp("c_idate"));

								article.setRef(rs.getInt("ref"));
								article.setRe_step(rs.getInt("re_step"));
								article.setRe_level(rs.getInt("re_level"));
								article.setReply(rs.getInt("reply"));

							}
						} catch(Exception ex) {
							ex.printStackTrace();
						} finally {
							if (rs != null) try { rs.close(); } catch(SQLException ex) {}
							if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
							if (conn != null) try { conn.close(); } catch(SQLException ex) {}
						}
						
						return article;
					}	 
			 
		    //---------------------------관리자가 보는 문의 내역 count ---------------------------------------//
		 public int adminInquireListCount(int reply) throws Exception{
			 int x = 0; //책의 수
		    	try{
		    		conn = getConnection();

		    		pstmt = conn.prepareStatement("select count(*) from InquireBoard where reply=?");
		    		pstmt.setInt(1, reply);
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
		 
		 public List<InquireDto> adminInquireList(int startRow, int endRow, int reply)  throws Exception{
		     List<InquireDto> articleList = null;
		     try {
		        conn = getConnection();
		        pstmt = conn.prepareStatement(
	"select c_ino, d_no, c_itype, c_isubject, c_icontent, c_readcount, c_idate,ref,re_step,re_level, r from " +
	"(select c_ino, d_no, c_itype, c_isubject, c_icontent, c_readcount, c_idate,ref,re_step,re_level, rownum r from " + 
	"(select * from InquireBoard where reply=?  order by ref desc, re_step asc ) order by ref desc, re_step asc) where r >= "+startRow+" and r <= " +endRow
		        		);
		       		 
		        pstmt.setInt(1, reply);
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
		        	   article.setRe_level(rs.getInt("re_level"));
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
		 
		 
			//------------------------------------ 관리자 답변, type 불러오기 -----------------------------
				 
				 public InquireDto Replysearch(int c_ino) throws Exception {
					 Connection conn = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						InquireDto article=null;
						try {
							conn = getConnection();

							pstmt = conn.prepareStatement("select * from InquireBoard where c_ino=?");
				    		pstmt.setInt(1, c_ino);
				    		
							rs = pstmt.executeQuery();
							if (rs.next()) {
								article = new InquireDto();
								article.setC_ino(c_ino); 
								article.setD_no(rs.getInt("d_no"));
								article.setC_itype(rs.getInt("c_itype"));
								article.setC_isubject(rs.getString("c_isubject"));
								article.setC_icontent(rs.getString("c_icontent"));
								article.setC_readcount(rs.getInt("c_readcount"));
								article.setC_idate(rs.getTimestamp("c_idate"));

								article.setRef(rs.getInt("ref"));
								article.setRe_step(rs.getInt("re_step"));
								article.setRe_level(rs.getInt("re_level"));
								article.setReply(rs.getInt("reply"));

							}
						} catch(Exception ex) {
							ex.printStackTrace();
						} finally {
							if (rs != null) try { rs.close(); } catch(SQLException ex) {}
							if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
							if (conn != null) try { conn.close(); } catch(SQLException ex) {}
						}
						
						return article;
					}	 
		 
	//--------------------------------------------------- 1:1 끝 -------------------------------------------------------------------
				 
				 
				 
	}
	
	

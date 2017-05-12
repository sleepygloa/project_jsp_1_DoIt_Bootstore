package mvc.doit.ResellBean;

import java.sql.*;
import javax.sql.*;

import mvc.doit.Login.LoginDto;

import javax.naming.*;
import java.util.*; 

public class ResellbookDao {
	private static ResellbookDao instance = new ResellbookDao();
		
	public static ResellbookDao getInstance(){
		return instance;
	}
	private ResellbookDao(){}
	
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
		return ds.getConnection();
	}

//----------------------직거래 판매등록-----------------------------------------------
	public void insertArticle(ResellbookDto resell) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rbook_no = resell.getRbook_no();
		int number= 0;
		String sql="";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select max(rbook_no) from resellbook ");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				number=rs.getInt(1)+1;
			}else{
				number=1;
			}
			
			sql ="insert into resellbook(rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,";
			sql+="rbook_pic,rbook_reg_date,rbook_subject,rbook_bgread,rbook_price2) values(resellbook_seq.NEXTVAL,resellbook2_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, resell.getRbook_id());
			pstmt.setString(2, resell.getRbook_name());
			pstmt.setString(3, resell.getRbook_writer());
			pstmt.setString(4, resell.getRbook_company());
			pstmt.setInt(5, resell.getRbook_price());
			pstmt.setString(6, resell.getRbook_content());
			pstmt.setString(7, resell.getRbook_pic());
			pstmt.setTimestamp(8, resell.getRbook_reg_date());
			pstmt.setString(9, resell.getRbook_subject());
			pstmt.setInt(10, resell.getRbook_bgread());
			pstmt.setInt(11, resell.getRbook_price2());
			//pstmt.setInt(10, resell.getRboook_readcount());
			//pstmt.setInt(11, resell.getRbook_sell_check());
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

//---------------------직거래전체글개수----------------------------------------------
	public int getResellCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from resellbook");
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
	//---------------------직거래전체목록-------------------------------------------
		public List getResellList(int start,int end, String rbook_sell_check) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			try {
				conn = getConnection();
				if(!rbook_sell_check.equals("")){
				int sellcheck = Integer.parseInt(rbook_sell_check);
				pstmt = conn.prepareStatement(
						"select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,"
						+ "rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count,r " +
								"from(select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,"
								+ "rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count,rownum r " +
								"from(select a.rbook_no,a.rbook_num,a.rbook_id,a.rbook_name,a.rbook_writer,a.rbook_company,a.rbook_price,a.rbook_content,a.rbook_pic,"
								+ "a.rbook_reg_date,a.rbook_readcount,a.rbook_sell_check,a.rbook_subject,a.rbook_bgread,a.rbook_price2,b.rbook_finish_count " +
								"from resellbook a, resellintro b where a.rbook_id = b.d_id) order by rbook_no  desc  ) where r >= ? and r <= ? and rbook_sell_check = ? ");
				
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				pstmt.setInt(3, sellcheck);
				}else {
					pstmt = conn.prepareStatement(
							"select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,"
							+ "rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count,r " +
									"from(select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,"
									+ "rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count,rownum r " +
									"from(select a.rbook_no,a.rbook_num,a.rbook_id,a.rbook_name,a.rbook_writer,a.rbook_company,a.rbook_price,a.rbook_content,a.rbook_pic,"
									+ "a.rbook_reg_date,a.rbook_readcount,a.rbook_sell_check,a.rbook_subject,a.rbook_bgread,a.rbook_price2,b.rbook_finish_count " +
									"from resellbook a, resellintro b where a.rbook_id = b.d_id) order by rbook_no  desc  ) where r >= ? and r <= ? ");
					
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
				}
				
				rs = pstmt.executeQuery();
				if(rs.next()){
					articleList = new ArrayList(end);
					do {
						ResellbookDto dto = new ResellbookDto();
						dto.setRbook_no(rs.getInt("rbook_no"));
						dto.setRbook_num(rs.getInt("rbook_num"));
						dto.setRbook_id(rs.getString("rbook_id"));
						dto.setRbook_name(rs.getString("rbook_name"));
						dto.setRbook_writer(rs.getString("rbook_writer"));
						dto.setRbook_company(rs.getString("rbook_company"));
						dto.setRbook_price(rs.getInt("rbook_price"));
						dto.setRbook_content(rs.getString("rbook_content"));
						dto.setRbook_pic(rs.getString("rbook_pic"));
						dto.setRbook_reg_date(rs.getTimestamp("rbook_reg_date"));
						dto.setRbook_readcount(rs.getInt("rbook_readcount"));
						dto.setRbook_sell_check(rs.getInt("rbook_sell_check"));
						dto.setRbook_subject(rs.getString("rbook_subject"));
						dto.setRbook_bgread(rs.getInt("rbook_bgread"));
						dto.setRbook_price2(rs.getInt("rbook_price2"));
						dto.setRbook_finish_count(rs.getInt("rbook_finish_count"));
						
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
	//-------------검색개수--------------------------------------------------------------
		public int getResellSearchCount(String searchTitle,String search) throws Exception{
			Connection conn =null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			int x = 0;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*)from resellbook where "+searchTitle+" like '%"+search+"%'");
				
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

	//-------------직거래목록 검색-------------------------------------------------------------------
	public List getResellSearch(int start,int end,String searchTitle, String search) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List articleList =null;
			
		try{
			conn =getConnection();
			pstmt = conn.prepareStatement(
					"select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,r " +
	"from (select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rownum r " +
	"from (select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2 " +
	"from resellbook where "+searchTitle+" like '%"+search+"%' order by rbook_no desc) order by rbook_no desc ) where r >= ? and r <= ? order by rbook_no");
			pstmt.setInt(1, start); 
			pstmt.setInt(2, end); 
			rs =pstmt.executeQuery();
			if(rs.next()){
				articleList = new ArrayList();
				do {
					ResellbookDto dto = new ResellbookDto();
					dto.setRbook_no(rs.getInt("rbook_no"));
					dto.setRbook_num(rs.getInt("rbook_num"));
					dto.setRbook_id(rs.getString("rbook_id"));
					dto.setRbook_name(rs.getString("rbook_name"));
					dto.setRbook_writer(rs.getString("rbook_writer"));
					dto.setRbook_company(rs.getString("rbook_company"));
					dto.setRbook_price(rs.getInt("rbook_price"));
					dto.setRbook_content(rs.getString("rbook_content"));
					dto.setRbook_pic(rs.getString("rbook_pic"));
					dto.setRbook_reg_date(rs.getTimestamp("rbook_reg_date"));
					dto.setRbook_readcount(rs.getInt("rbook_readcount"));
					dto.setRbook_sell_check(rs.getInt("rbook_sell_check"));
					dto.setRbook_subject(rs.getString("rbook_subject"));
					dto.setRbook_bgread(rs.getInt("rbook_bgread"));
					dto.setRbook_price2(rs.getInt("rbook_price2"));
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
//------------직거래상세페이지-------------------------------------------------------------------
	public ResellbookDto getResllArticle(int rbook_no)throws Exception{
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		ResellbookDto article = null;
		
		try {
			conn= getConnection();
			pstmt= conn.prepareStatement(
					"update resellbook set rbook_readcount= rbook_readcount+1 where rbook_no =?");
			pstmt.setInt(1, rbook_no);
			pstmt.executeUpdate();
			
			pstmt= conn.prepareStatement(
					"select * from resellbook where rbook_no=?");
			pstmt.setInt(1, rbook_no);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				article = new ResellbookDto();
				article.setRbook_no(rs.getInt("rbook_no"));
				article.setRbook_num(rs.getInt("rbook_num"));
				article.setRbook_id(rs.getString("rbook_id"));
				article.setRbook_name(rs.getString("rbook_name"));
				article.setRbook_writer(rs.getString("rbook_writer"));
				article.setRbook_company(rs.getString("rbook_company"));
				article.setRbook_price(rs.getInt("rbook_price"));
				article.setRbook_content(rs.getString("rbook_content"));
				article.setRbook_pic(rs.getString("rbook_pic"));
				article.setRbook_reg_date(rs.getTimestamp("rbook_reg_date"));
				article.setRbook_readcount(rs.getInt("rbook_readcount"));
				article.setRbook_sell_check(rs.getInt("rbook_sell_check"));
				article.setRbook_subject(rs.getString("rbook_subject"));
				article.setRbook_bgread(rs.getInt("rbook_bgread"));
				article.setRbook_price2(rs.getInt("rbook_price2"));
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
//--------직거래 글삭제-----------------------------------------------------------------	
	public void reDeleteArticle(int rbook_no){
		Connection conn= null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			conn =getConnection();
			pstmt =conn.prepareStatement("delete from resellbook where rbook_no=?");
			pstmt.setInt(1, rbook_no);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}

//----------직거래 글수정------------------------------------------------------------------
	public ResellbookDto reUpdateGetArticle(int rbook_no) throws Exception{
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ResellbookDto article =null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(
					"select * from resellbook where rbook_no=?");
			pstmt.setInt(1, rbook_no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				article = new ResellbookDto();
				article.setRbook_no(rs.getInt("rbook_no"));
				article.setRbook_num(rs.getInt("rbook_num"));
				article.setRbook_id(rs.getString("rbook_id"));
				article.setRbook_name(rs.getString("rbook_name"));
				article.setRbook_writer(rs.getString("rbook_writer"));
				article.setRbook_company(rs.getString("rbook_company"));
				article.setRbook_price(rs.getInt("rbook_price"));
				article.setRbook_content(rs.getString("rbook_content"));
				article.setRbook_pic(rs.getString("rbook_pic"));
				article.setRbook_reg_date(rs.getTimestamp("rbook_reg_date"));
				article.setRbook_readcount(rs.getInt("rbook_readcount"));
				article.setRbook_sell_check(rs.getInt("rbook_sell_check"));
				article.setRbook_subject(rs.getString("rbook_subject"));
				article.setRbook_bgread(rs.getInt("rbook_bgread"));
				article.setRbook_price2(rs.getInt("rbook_price2"));
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
//--------직거래 글수정-------------------------------------------------------------------
	public void reUpdateArticle(ResellbookDto article)throws Exception{
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		try {
			conn = getConnection();
			pstmt =conn.prepareStatement(
			"update resellbook set rbook_name=?, rbook_price=?,rbook_writer=?,rbook_company=?,rbook_content=?,rbook_pic=?,"
			+ "rbook_subject=?,rbook_bgread=?,rbook_price2=? where rbook_no=?");
			pstmt.setString(1,article.getRbook_name());
			pstmt.setInt(2,article.getRbook_price());
			pstmt.setString(3,article.getRbook_writer());
			pstmt.setString(4,article.getRbook_company());
			pstmt.setString(5,article.getRbook_content());
			pstmt.setString(6,article.getRbook_pic());
			pstmt.setString(7,article.getRbook_subject());
			pstmt.setInt(8,article.getRbook_bgread());
			pstmt.setInt(9,article.getRbook_price2());
			pstmt.setInt(10,article.getRbook_no());
			
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	//------댓글작성---------------------------------------------------------------------------------
		public void insertReReply(ResellReplyDto rer){
			Connection conn = null;
			PreparedStatement pstmt =null;
			//ResultSet rs= null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"insert into resellreply(rerbook_rnum,rerbook_bnum,rerbook_writer,rerbook_content,rerbook_reg_date) values(resellreply_seq.nextval,?,?,?,sysdate)");
				pstmt.setInt(1, rer.getRerbook_bnum());
				pstmt.setString(2, rer.getRerbook_writer());
				pstmt.setString(3, rer.getRerbook_content());
				pstmt.executeUpdate();
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			
		}
	//-------댓글리스트-----------------------------------------------------------------------	
		public List getReReplyList(int rbook_no) throws Exception{
			Connection conn =null;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			List list = null;
			try {
				conn=getConnection();
				pstmt=conn.prepareStatement(
						"select * from resellreply where rerbook_bnum=? order by rerbook_reg_date asc");
					pstmt.setInt(1, rbook_no);
					rs = pstmt.executeQuery();
					list = new ArrayList();
					if(rs.next()){
						do{
							ResellReplyDto rer = new ResellReplyDto();
							rer.setRerbook_bnum(rs.getInt("rerbook_bnum"));
							rer.setRerbook_rnum(rs.getInt("rerbook_rnum"));
							rer.setRerbook_writer(rs.getString("rerbook_writer"));
							rer.setRerbook_content(rs.getString("rerbook_content"));
							rer.setRerbook_reg_date(rs.getTimestamp("rerbook_reg_date"));
							list.add(rer);
						}while(rs.next());
					}
			} catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return list;
		}
//----------댓글개수-------------------------------------------------------------------------
		public int getReplyCount(int rbook_no)throws Exception{
			Connection conn =null;
			PreparedStatement pstmt =null;
			ResultSet rs= null;
			int x = 0;
			try {
				conn = getConnection();
				pstmt =conn.prepareStatement("select count(*) from resellreply where rerbook_bnum=?");
				pstmt.setInt(1, rbook_no);
				rs = pstmt.executeQuery();
				if(rs.next()){
					x=rs.getInt(1);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			
			return x;
		}
		
	
	
		
//------reList에서 등급확인------------------------------------------------------------------------------
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
		
//------판매자회원정보 리스트------------------------------------------------------------------------------------------------------
        public List getSellerList(int start,int end) throws Exception{
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            List articleList = null;
            try {
               conn = getConnection();
               pstmt = conn.prepareStatement(
                     //"select a.*, b.* from d_member a, resellintro b where a.d_mech_grade=2 and a.d_id= b.d_id order by a.d_id asc");
                     "select d_no,d_id,d_name,d_phone,d_addr,d_mail,d_gender,d_pic,d_mech_grade, rbook_intro,report_count,r from"
                     + "(select d_no,d_id,d_name,d_phone,d_addr,d_mail,d_gender,d_pic,d_mech_grade, rbook_intro,report_count,rownum r from"
                     + "(select a.d_no,a.d_id,a.d_name,a.d_phone,a.d_addr,a.d_mail,a.d_gender,a.d_pic,a.d_mech_grade, b.rbook_intro,b.report_count from "
                     + "d_member a, resellintro b where a.d_mech_grade=2 and a.d_id = b.d_id ) "
                     + "order by d_id asc) where r >= ? and r <= ?");
               pstmt.setInt(1, start);
               pstmt.setInt(2, end);
               
               rs = pstmt.executeQuery();
               if(rs.next()){
                  articleList = new ArrayList(end);
                  do {
                     LoginDto dto = new LoginDto();
                     dto.setD_no(rs.getInt("d_no"));
                     dto.setD_id(rs.getString("d_id"));
                     dto.setD_name(rs.getString("d_name"));
                     dto.setD_phone(rs.getString("d_phone"));
                     dto.setD_addr(rs.getString("d_addr"));
                     dto.setD_mail(rs.getString("d_mail"));
                     dto.setD_gender(rs.getString("d_gender"));
                     dto.setD_pic(rs.getString("d_pic"));
                     dto.setD_mech_grade(rs.getInt("d_mech_grade"));
                     dto.setRbook_intro(rs.getString("rbook_intro"));
                     dto.setReport_count(rs.getString("report_count"));
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

        
        
        
//------판매자회원정보 개수------------------------------------------------------------------------------------------------------
		public int getSellerCount()throws Exception{
					Connection conn =null;
					PreparedStatement pstmt =null;
					ResultSet rs= null;
					int x = 0;
					try {
						conn = getConnection();
						pstmt =conn.prepareStatement("select count(*) from d_member where d_mech_grade=2");
						rs = pstmt.executeQuery();
						if(rs.next()){
							x=rs.getInt(1);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}
					
					return x;
				}
	
//------판매자 회원 검색 개수----------------------------------------------------------------------------------
		public int getSellerSearchCount(String search) throws Exception{
			Connection conn =null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			int x = 0;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*)from d_member where d_mech_grade=2 and d_id like '%"+search+"%'");
				
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
		
//-------------판매자회원 검색-------------------------------------------------------------------
		public List getSellerSearch(String search, int start, int end) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			List articleList =null;
				
			try{
				conn =getConnection();
				pstmt = conn.prepareStatement(
						"select * from (select d_no,d_id,d_name,d_phone,d_addr,d_mail,d_gender,d_pic,d_mech_grade, rbook_intro,r from "
						+ "(select d_no,d_id,d_name,d_phone,d_addr,d_mail,d_gender,d_pic,d_mech_grade, rbook_intro,rownum r from "
						+ "(select a.d_no,a.d_id,a.d_name,a.d_phone,a.d_addr,a.d_mail,a.d_gender,a.d_pic,a.d_mech_grade, b.rbook_intro from "
						+ "d_member a, resellintro b where a.d_mech_grade=2 and a.d_id = b.d_id) "
						+ "order by d_id asc) where r >= ? and r <= ?) where d_id like '%"+search+"%'");
				
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs =pstmt.executeQuery();
				if(rs.next()){
					articleList = new ArrayList();
					do {
						LoginDto dto = new LoginDto();
						dto.setD_no(rs.getInt("d_no"));
						dto.setD_id(rs.getString("d_id"));
						dto.setD_name(rs.getString("d_name"));
						dto.setD_phone(rs.getString("d_phone"));
						dto.setD_addr(rs.getString("d_addr"));
						dto.setD_mail(rs.getString("d_mail"));
						dto.setD_gender(rs.getString("d_gender"));
						dto.setD_pic(rs.getString("d_pic"));
						dto.setD_mech_grade(rs.getInt("d_mech_grade"));
						dto.setRbook_intro(rs.getString("rbook_intro"));
						
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
		//------판매완료 변경-------------------------------------------------------------------------------------------------      
	      public boolean resellFinish(int rbook_no) {
	         Connection conn =null;
	         PreparedStatement pstmt =null;
	         ResultSet rs= null;
	         boolean check = false;   //판매완료전
	                try{
	                   conn = getConnection();
	                   pstmt = conn.prepareStatement(
	                         "update resellbook set rbook_sell_check = 1 where rbook_no=? ");
	                   pstmt.setInt(1, rbook_no);
	                   pstmt.executeUpdate(); 
	              
	                   pstmt = conn.prepareStatement(
	                         "merge into resellintro a using(select  b.rbook_id, b.rbook_no,a.rbook_finish_count,"
	                         + " b.rbook_sell_check from resellintro a, resellbook b where a.d_id=b.rbook_id and "
	                         + "rbook_no =?)b on(a.d_id = b.rbook_id) when matched then update set rbook_finish_count = rbook_finish_count+1");
	                   pstmt.setInt(1, rbook_no);
	                   pstmt.executeUpdate(); 
	                   check = true; //판매완료
	                   
	                   }catch(Exception e){
	                      e.printStackTrace();
	                   }finally{
	                      if(rs != null){try{rs.close();}catch(SQLException s){}}
	                      if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
	                      if(conn != null){try{conn.close();}catch(SQLException s){}}
	                   }
	                   return check;
			      }

		
//------신고하기(한 id당 1회신고가능)-----------------------------------------------------------------------------------------------
		public boolean reReport(String report_id, int rbook_no) {
			Connection conn =null;
			PreparedStatement pstmt =null;
			ResultSet rs= null;
			String dbid="";
			boolean check=false;	
			       try{
			          conn = getConnection();
			          //pstmt = conn.prepareStatement(
			                //"select * from resellreport a, resellbook b where a.rbook_no = b.rbook_no and report_id=?");
			          pstmt = conn.prepareStatement("select * from resellreport where report_id=? and rbook_no=?");
			          pstmt.setString(1, report_id);
			          pstmt.setInt(2, rbook_no);
			          rs = pstmt.executeQuery();
			          if(rs.next()){
			        	  dbid =rs.getString("report_id");
			        	  if(dbid.equals(report_id)){
			        		  check=true;//신고한 아이디가 db에 있으면 false
			        	  }else{
			        		  check=false;//없으면 true
			        	  }
			          }
			     
			          
			          
			          
			          }catch(Exception e){
			             e.printStackTrace();
			          }finally{
			             if(rs != null){try{rs.close();}catch(SQLException s){}}
			             if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
			             if(conn != null){try{conn.close();}catch(SQLException s){}}
			          }
			          return check;
			      }
		//---------신고하기-----------------------------------------------------------------------------
				public void reReportInsert(ResellReportDto report){
					Connection conn =null;
					PreparedStatement pstmt =null;
					ResultSet rs= null;
					try {
						 conn = getConnection();
				          pstmt =conn.prepareStatement("insert into resellreport(report_no,rbook_no,report_id,d_id,report_reg_date,report_content) values(resellreport_seq.NEXTVAL,?,?,?,sysdate,?)");
				          pstmt.setInt(1, report.getRbook_no());
				          pstmt.setString(2, report.getReport_id());
				          pstmt.setString(3, report.getD_id());
				          pstmt.setString(4, report.getReport_content());
				          //pstmt.setTimestamp(4, report.getReport_reg_date());
				          pstmt.executeUpdate();
				          
							
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}
				}
//---------신고카운트-----------------------------------------------------------------------------
				public void reReporCount(String d_id){
					Connection conn =null;
					PreparedStatement pstmt =null;
					ResultSet rs= null;
					try {
						 conn = getConnection();
				          pstmt =conn.prepareStatement("update resellintro set REPORT_COUNT = REPORT_COUNT+1 where d_id=?");
				          pstmt.setString(1, d_id);
				          //pstmt.setTimestamp(4, report.getReport_reg_date());
				          pstmt.executeUpdate();
				          
							
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						if (rs != null) try { rs.close(); } catch(SQLException ex) {}
						if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}
				}
		
//-------신고개수-----------------------------------------------------------------------------------------------		
		public int getReportCount()throws Exception{
			Connection conn =null;
			PreparedStatement pstmt =null;
			ResultSet rs= null;
			int x = 0;
			try {
				conn = getConnection();
				pstmt =conn.prepareStatement("select count(*) from resellreport");
				rs = pstmt.executeQuery();
				if(rs.next()){
					x=rs.getInt(1);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			
			return x;
		}		
		//-------신고리스트(관리자페이지)------------------------------------------------------------------------------------------------------------------------
	      public List getReportList(int start,int end) throws Exception{
	         Connection conn = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         List articleList = null;
	         try {
	            conn = getConnection();
	            pstmt = conn.prepareStatement(
	                  "select  rbook_no,rbook_subject,rbook_id,rbook_readcount,rbook_reg_date, report_no,report_id,report_reg_date,report_content, r from"
	                  + "(select rbook_no,rbook_subject,rbook_id,rbook_readcount,rbook_reg_date, report_no,report_id,report_reg_date,report_content, rownum r from"
	                  + "(select a.rbook_no,a.rbook_subject,a.rbook_id,a.rbook_readcount,a.rbook_reg_date, b.report_no,b.report_id,b.report_reg_date,report_content from "
	                  + "resellbook a, resellreport b where a.rbook_no = b.rbook_no) "
	                  + "order by report_no desc) where r >= ? and r <= ?");
	            pstmt.setInt(1, start);
	            pstmt.setInt(2, end);
	            
	            rs = pstmt.executeQuery();
	            if(rs.next()){
	               articleList = new ArrayList(end);
	               do {
	                  ResellbookDto dto = new ResellbookDto();
	                  dto.setRbook_no(rs.getInt("rbook_no"));
	                  dto.setRbook_subject(rs.getString("rbook_subject"));
	                  dto.setRbook_id(rs.getString("rbook_id"));
	                  dto.setRbook_readcount(rs.getInt("rbook_readcount"));
	                  dto.setRbook_reg_date(rs.getTimestamp("rbook_reg_date"));
	                  dto.setReport_no(rs.getInt("report_no"));
	                  dto.setReport_id(rs.getString("report_id"));
	                  dto.setReport_reg_date(rs.getTimestamp("report_reg_date"));
	                  dto.setReport_content(rs.getString("report_content"));
	                  
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

//--------직거래 게시판 댓글 삭제-----------------------------------------------------------------	
		public void reReplyDeleteArticle(int rerbook_rnum){
			Connection conn= null;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			try {
				conn =getConnection();
				pstmt =conn.prepareStatement("delete from resellreply where rerbook_rnum=?");
				pstmt.setInt(1, rerbook_rnum);
				pstmt.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
		}		

		


//-------판매완료 글-----------------------------------------------------------------------------------------------		
			public ResellintroDto  sellerGrade(String d_id)throws Exception{
				Connection conn =null;
				PreparedStatement pstmt =null;
				ResultSet rs= null;
				ResellintroDto grade= null;
				try {
					conn = getConnection();
					pstmt =conn.prepareStatement("select * from resellintro where d_id=?");
					pstmt.setString(1, d_id);
					rs = pstmt.executeQuery();
					if(rs.next()){
						grade = new ResellintroDto();
						grade.setD_id(rs.getString("d_id"));
						grade.setRbook_finish_count(rs.getInt("rbook_finish_count"));
					}
				
				} catch (Exception ex) {
					ex.printStackTrace();
				}finally {
					if (rs != null) try { rs.close(); } catch(SQLException ex) {}
					if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
					if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				}
				return grade;
			}
//------판매완료개수 list에서 확인------------------------------------------------------------------------------
			public ResellintroDto getFinishWriter(String id) {
				// TODO Auto-generated method stub
				
				
					        Connection conn = null;
					        PreparedStatement pstmt = null;
					        ResultSet rs = null;
					        ResellintroDto finish=null;
					        try {
					            conn = getConnection();
					            
					            pstmt = conn.prepareStatement(
					            	"select * from (select a.d_id, b.rbook_finish_count "
					            	+ "from d_member a, resellintro b where a.d_id=b.d_id) where d_id=?");
					            pstmt.setString(1, id);
					            rs = pstmt.executeQuery();

					            if (rs.next()) {
					            	finish = new ResellintroDto();
					            	finish.setD_id(rs.getString("d_id"));
					            	finish.setRbook_finish_count(rs.getInt("rbook_finish_count"));
								    
								}
					        } catch(Exception ex) {
					            ex.printStackTrace();
					        } finally {
					            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
					            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
					            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					        }
					        
							return finish;
			
			}

			//---------------------나의 작성개수(판매중)----------------------------------------------
		      public int getSellerCount(String rbook_id) throws Exception{
		            Connection conn = null;
		            PreparedStatement pstmt = null;
		            ResultSet rs = null;
		            int x=0;
		            try {
		               conn = getConnection();
		               pstmt = conn.prepareStatement("select count(*) from resellbook where rbook_id=? and rbook_sell_check=0");
		               pstmt.setString(1, rbook_id);
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

			
//------스크랩(한 id당 1글 스크랩가능)-----------------------------------------------------------------------------------------------
				public boolean scrapCheck(String scrap_id, int rbook_no) {
					Connection conn =null;
					PreparedStatement pstmt =null;
					ResultSet rs= null;
					String dbid="";
					boolean check=false;	
					       try{
					          conn = getConnection();
					          //pstmt = conn.prepareStatement(
					                //"select * from resellreport a, resellbook b where a.rbook_no = b.rbook_no and report_id=?");
					          pstmt = conn.prepareStatement("select * from resellscrap where d_id=? and rbook_no=?");
					          pstmt.setString(1, scrap_id);
					          pstmt.setInt(2, rbook_no);
					          rs = pstmt.executeQuery();
					          if(rs.next()){
					        	  dbid =rs.getString("d_id");
					        	  if(dbid.equals(scrap_id)){
					        		  check=true;//신고한 아이디가 db에 있으면 false
					        	  }else{
					        		  check=false;//없으면 true
					        	  }
					          }
					     
					          
					          
					          
					          }catch(Exception e){
					             e.printStackTrace();
					          }finally{
					             if(rs != null){try{rs.close();}catch(SQLException s){}}
					             if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
					             if(conn != null){try{conn.close();}catch(SQLException s){}}
					          }
					          return check;
					      }		
//---------스크랩----------------------------------------------------------------------------------------------------------
				public void insertScrap(ResellScrapDto scrap){
					Connection conn = null;
					PreparedStatement pstmt =null;
					//ResultSet rs= null;
					try {
						conn = getConnection();
						pstmt = conn.prepareStatement(
								"insert into resellscrap(scrap_no,d_id,rbook_no,scrap_reg_date) values(resellscrap_seq.nextval,?,?,sysdate)");
						pstmt.setString(1, scrap.getD_id());
						pstmt.setInt(2, scrap.getRbook_no());
						
						pstmt.executeUpdate();
						
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}finally {
						if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
						if (conn != null) try { conn.close(); } catch(SQLException ex) {}
					}
					
				}	
				
//-------나의스크랩 개수------------------------------------------------------------------------------------------
				public int getMyScrapCount(String id) throws Exception {
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					int x=0;
					try {
						conn = getConnection();
						pstmt = conn.prepareStatement("select count(*) from resellscrap where d_id=?"); 
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
				
//----나의 스크랩 리스트------------------------------------------------------------------------------------------
				public List getMyScrapList(String d_id) throws Exception {
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					List articleList=null;
					try {
						conn = getConnection();
						pstmt = conn.prepareStatement(
								"select * from (select a.rbook_no,a.rbook_subject,a.rbook_price,a.rbook_price2,a.rbook_reg_date,a.rbook_sell_check, "
								+ "b.scrap_no,b.d_id,b.scrap_reg_date from resellbook a, resellscrap b where a.rbook_no=b.rbook_no ) "
								+ "where d_id=? order by rbook_no desc ");
				pstmt.setString(1, d_id);
				rs = pstmt.executeQuery();
				if(rs.next()){
					articleList = new ArrayList();
					do {
						ResellbookDto scrap = new ResellbookDto();
						scrap.setRbook_no(rs.getInt("rbook_no"));
						scrap.setRbook_subject(rs.getString("rbook_subject"));
						scrap.setRbook_price(rs.getInt("rbook_price"));
						scrap.setRbook_price2(rs.getInt("rbook_price2"));
						scrap.setRbook_reg_date(rs.getTimestamp("rbook_reg_date"));
						scrap.setScrap_no(rs.getInt("scrap_no"));
						scrap.setD_id(rs.getString("d_id"));
						scrap.setScrap_reg_date(rs.getString("scrap_reg_date"));
						scrap.setRbook_sell_check(rs.getInt("rbook_sell_check"));
						articleList.add(scrap);
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
				
		/*내가쓴 글 목록(직거래)
		 public List getMyScrapList(String d_id, int start,int end) throws Exception {
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					List articleList=null;
					try {
						conn = getConnection();
						pstmt = conn.prepareStatement(
								//검색 결과 안에서 검색
								"select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count " + 
										"from(select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count,r " +
								"from(select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count,rownum r " +
								"from(select a.rbook_no,a.rbook_num,a.rbook_id,a.rbook_name,a.rbook_writer,a.rbook_company,a.rbook_price,a.rbook_content,a.rbook_pic,a.rbook_reg_date,a.rbook_readcount,a.rbook_sell_check,a.rbook_subject,a.rbook_bgread,a.rbook_price2,b.rbook_finish_count " +
								"from resellbook a, resellintro b where a.rbook_id = b.d_id) order by rbook_no desc) where rbook_id=?)where r >= ? and r <= ?");
				pstmt.setString(1, d_id);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				
				rs = pstmt.executeQuery();
				if(rs.next()){
					articleList = new ArrayList(end);
					do {
						ResellbookDto dto = new ResellbookDto();
						dto.setRbook_no(rs.getInt("rbook_no"));
						dto.setRbook_num(rs.getInt("rbook_num"));
						dto.setRbook_id(rs.getString("rbook_id"));
						dto.setRbook_name(rs.getString("rbook_name"));
						dto.setRbook_writer(rs.getString("rbook_writer"));
						dto.setRbook_company(rs.getString("rbook_company"));
						dto.setRbook_price(rs.getInt("rbook_price"));
						dto.setRbook_content(rs.getString("rbook_content"));
						dto.setRbook_pic(rs.getString("rbook_pic"));
						dto.setRbook_reg_date(rs.getTimestamp("rbook_reg_date"));
						dto.setRbook_readcount(rs.getInt("rbook_readcount"));
						dto.setRbook_sell_check(rs.getInt("rbook_sell_check"));
						dto.setRbook_subject(rs.getString("rbook_subject"));
						dto.setRbook_bgread(rs.getInt("rbook_bgread"));
						dto.setRbook_price2(rs.getInt("rbook_price2"));
						dto.setRbook_finish_check(rs.getInt("rbook_finish_count"));
						
						
						articleList.add(dto);
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
		}*/	

//----------스크랩 삭제----------------------------------------------------------------------------------------------				
	public void myScrapDelete(int scrap_no){
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs= null;
		
		try {
			conn= getConnection();
			pstmt =conn.prepareStatement("delete from resellscrap where scrap_no = ?");
			pstmt.setInt(1, scrap_no);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();} catch (SQLException ex) {}
			if(pstmt!=null)try {pstmt.close();} catch (SQLException ex) {}
			if(conn!=null)try {conn.close();} catch (SQLException ex) {}
		}
		
	}	
	
//신고게시판 검색 갯수 (관리자)------------------------
//-------------검색개수-------------------------------------------
		public int reportSearchCount(String search) throws Exception{
			Connection conn =null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			int x = 0;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from resellreport where d_id like '%"+search+"%'");
				
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
		
//-------------신고리스트 아이디 검색하면 나오는 것들-------------------------------------------------------------------
		public List reportSearch(String search,int start, int end) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List articleList = null;
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(
							"select * from (select  rbook_no,rbook_subject,rbook_id,rbook_readcount,rbook_reg_date, report_no,report_id,report_reg_date,r from "
							+ "(select rbook_no,rbook_subject,rbook_id,rbook_readcount,rbook_reg_date, report_no,report_id,report_reg_date,rownum r from "
							+ "(select a.rbook_no,a.rbook_subject,a.rbook_id,a.rbook_readcount,a.rbook_reg_date, b.report_no,b.report_id,b.report_reg_date from "
							+ "resellbook a, resellreport b where a.rbook_no = b.rbook_no) "
							+ "order by report_no desc) where r >= ? and r <= ?) where rbook_id like '%"+search+"%'");
					
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					
					rs = pstmt.executeQuery();
					if(rs.next()){
						articleList = new ArrayList(end);
						do {
							ResellbookDto dto = new ResellbookDto();
							dto.setRbook_no(rs.getInt("rbook_no"));
							dto.setRbook_subject(rs.getString("rbook_subject"));
							dto.setRbook_id(rs.getString("rbook_id"));
							dto.setRbook_readcount(rs.getInt("rbook_readcount"));
							dto.setRbook_reg_date(rs.getTimestamp("rbook_reg_date"));
							dto.setReport_no(rs.getInt("report_no"));
							dto.setReport_id(rs.getString("report_id"));
							dto.setReport_reg_date(rs.getTimestamp("report_reg_date"));
							
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
		
//--------------내가쓴 글 개수(직거래)------------------------------------------------------------------------------------------
		public int getMyReListCount(String id) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=0;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from resellbook where rbook_id=?"); 
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
//---------------------내가쓴 글 목록(직거래)-------------------------------------------
		 public List getMyReList(String d_id, int start,int end) throws Exception {
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					List articleList=null;
					try {
						conn = getConnection();
						pstmt = conn.prepareStatement(
								//검색 결과 안에서 검색
								"select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count " + 
										"from(select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count,r " +
								"from(select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count,rownum r " +
								"from(select a.rbook_no,a.rbook_num,a.rbook_id,a.rbook_name,a.rbook_writer,a.rbook_company,a.rbook_price,a.rbook_content,a.rbook_pic,a.rbook_reg_date,a.rbook_readcount,a.rbook_sell_check,a.rbook_subject,a.rbook_bgread,a.rbook_price2,b.rbook_finish_count " +
								"from resellbook a, resellintro b where a.rbook_id = b.d_id) order by rbook_no desc) where rbook_id=?)where r >= ? and r <= ?");
				pstmt.setString(1, d_id);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				
				rs = pstmt.executeQuery();
				if(rs.next()){
					articleList = new ArrayList(end);
					do {
						ResellbookDto dto = new ResellbookDto();
						dto.setRbook_no(rs.getInt("rbook_no"));
						dto.setRbook_num(rs.getInt("rbook_num"));
						dto.setRbook_id(rs.getString("rbook_id"));
						dto.setRbook_name(rs.getString("rbook_name"));
						dto.setRbook_writer(rs.getString("rbook_writer"));
						dto.setRbook_company(rs.getString("rbook_company"));
						dto.setRbook_price(rs.getInt("rbook_price"));
						dto.setRbook_content(rs.getString("rbook_content"));
						dto.setRbook_pic(rs.getString("rbook_pic"));
						dto.setRbook_reg_date(rs.getTimestamp("rbook_reg_date"));
						dto.setRbook_readcount(rs.getInt("rbook_readcount"));
						dto.setRbook_sell_check(rs.getInt("rbook_sell_check"));
						dto.setRbook_subject(rs.getString("rbook_subject"));
						dto.setRbook_bgread(rs.getInt("rbook_bgread"));
						dto.setRbook_price2(rs.getInt("rbook_price2"));
						dto.setRbook_finish_count(rs.getInt("rbook_finish_count"));
						
						
						articleList.add(dto);
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
		 
		 
		//-------------내가쓴글 검색개수(경매)--------------------------------------------------------------
	  		public int MyReListSearchCount(String search,String id) throws Exception{
	  			Connection conn =null;
	  			PreparedStatement pstmt =null;
	  			ResultSet rs = null;
	  			int x = 0;
	  			
	  			try {
	  				conn = getConnection();
	  				pstmt = conn.prepareStatement(
	  						"select count(*) from (select *from resellbook where rbook_id=? )where rbook_name like '%"+search+"%'");
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
	  		public List MyReListSearch(String search,String id, int start, int end) throws Exception{
	  			Connection conn = null;
	  			PreparedStatement pstmt =null;
	  			ResultSet rs = null;
	  			List articleList =null;
	  				
	  			try{
	  				conn =getConnection();
	  				pstmt = conn.prepareStatement(

"select * from (select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count " + 
"from(select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count,r " +
"from(select rbook_no,rbook_num,rbook_id,rbook_name,rbook_writer,rbook_company,rbook_price,rbook_content,rbook_pic,rbook_reg_date,rbook_readcount,rbook_sell_check,rbook_subject,rbook_bgread,rbook_price2,rbook_finish_count,rownum r " +
"from(select a.rbook_no,a.rbook_num,a.rbook_id,a.rbook_name,a.rbook_writer,a.rbook_company,a.rbook_price,a.rbook_content,a.rbook_pic,a.rbook_reg_date,a.rbook_readcount,a.rbook_sell_check,a.rbook_subject,a.rbook_bgread,a.rbook_price2,b.rbook_finish_count " +
"from resellbook a, resellintro b where a.rbook_id = b.d_id) order by rbook_no desc) where rbook_id=?)where r >= ? and r <= ?) where rbook_name like '%"+search+"%'");
	  				  				pstmt.setString(1, id);
	  				  				pstmt.setInt(2, start);
	  				  				pstmt.setInt(3, end);
	  				  				
	  				rs =pstmt.executeQuery();
	  				if(rs.next()){
	  					articleList = new ArrayList();
	  					do {
	  						ResellbookDto dto = new ResellbookDto();
							dto.setRbook_no(rs.getInt("rbook_no"));
							dto.setRbook_num(rs.getInt("rbook_num"));
							dto.setRbook_id(rs.getString("rbook_id"));
							dto.setRbook_name(rs.getString("rbook_name"));
							dto.setRbook_writer(rs.getString("rbook_writer"));
							dto.setRbook_company(rs.getString("rbook_company"));
							dto.setRbook_price(rs.getInt("rbook_price"));
							dto.setRbook_content(rs.getString("rbook_content"));
							dto.setRbook_pic(rs.getString("rbook_pic"));
							dto.setRbook_reg_date(rs.getTimestamp("rbook_reg_date"));
							dto.setRbook_readcount(rs.getInt("rbook_readcount"));
							dto.setRbook_sell_check(rs.getInt("rbook_sell_check"));
							dto.setRbook_subject(rs.getString("rbook_subject"));
							dto.setRbook_bgread(rs.getInt("rbook_bgread"));
							dto.setRbook_price2(rs.getInt("rbook_price2"));
							dto.setRbook_finish_count(rs.getInt("rbook_finish_count"));
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
}





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

import mvc.doit.Login.LoginDto;
import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.ResellBean.BidbookDto;

public class NoticeDao {
	
	private static NoticeDao instance= new NoticeDao();
	
	public static NoticeDao getInstance() {return instance; }
    
    private NoticeDao() {}
    
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
      return ds.getConnection();
    }
    //-------시작
    //공지사항 글 insert
    public void insertArticle(NoticeDto notice) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		try {
			conn= getConnection();
			sql ="insert into notice(notice_no,notice_id,notice_name,notice_content,notice_reg_date)"
						+ " values(notice_seq.NEXTVAL,?,?,?,?)";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, notice.getNotice_id());	
			pstmt.setString(2, notice.getNotice_name());
			pstmt.setString(3, notice.getNotice_content());
			pstmt.setTimestamp(4, notice.getNotice_reg_date());
			
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
    
  //---------------------전체공지사항개수----------------------------------------------
  	public int getNoticeCount() throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		int x=0;
  		try {
  			conn = getConnection();
  			pstmt = conn.prepareStatement("select count(*) from notice");
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
  	//---------------------공지사항목록-------------------------------------------
  		public List getNoticeList(int start ,int end) throws Exception{
  			Connection conn = null;
  			PreparedStatement pstmt = null;
  			ResultSet rs = null;
  			List articleList = null;
  			try {
  				conn = getConnection();
  				pstmt = conn.prepareStatement(
  				"select notice_no, notice_name, notice_reg_date, notice_readcount,r " +
  				"from(select notice_no, notice_name, notice_reg_date, notice_readcount,rownum r " +
  				"from(select notice_no, notice_name, notice_reg_date, notice_readcount " +
  				"from notice order by notice_no desc)order by r  asc )  where r >= ? and r <= ?");
  				
  				pstmt.setInt(1, start);
  				pstmt.setInt(2, end);
  				
  				rs = pstmt.executeQuery();
  				if(rs.next()){
  					articleList = new ArrayList(end);
  					do {
  						NoticeDto dto = new NoticeDto();
  						dto.setNotice_no(rs.getInt("notice_no"));
  						dto.setNotice_name(rs.getString("notice_name"));
  						dto.setNotice_reg_date(rs.getTimestamp("notice_reg_date"));
  						dto.setNotice_readcount(rs.getInt("notice_readcount"));
  						
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
  		
  	//-------------공지사항검색개수--------------------------------------------------------------
  		public int getNoticeSearchCount(String search) throws Exception{
  			Connection conn =null;
  			PreparedStatement pstmt =null;
  			ResultSet rs = null;
  			int x = 0;
  			
  			try {
  				conn = getConnection();
  				pstmt = conn.prepareStatement("select count(*) from notice where notice_name like '%"+search+"%'");
  				
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
  		
  	//-------------공지사항 검색-------------------------------------------------------------------
  		public List getNoticeSearch(String search) throws Exception{
  			Connection conn = null;
  			PreparedStatement pstmt =null;
  			ResultSet rs = null;
  			List articleList =null;
  				
  			try{
  				conn =getConnection();
  				pstmt = conn.prepareStatement(
  						"select * from notice where notice_name like '%"+search+"%'");
  				rs =pstmt.executeQuery();
  				if(rs.next()){
  					articleList = new ArrayList();
  					do {
  						NoticeDto dto = new NoticeDto();
  						dto.setNotice_no(rs.getInt("notice_no"));
  						dto.setNotice_name(rs.getString("notice_name"));
  						dto.setNotice_reg_date(rs.getTimestamp("notice_reg_date"));
  						dto.setNotice_readcount(rs.getInt("notice_readcount"));
  						
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
  	//------공지사항list에서 등급확인------------------------------------------------------------------------------
  		public LoginDto getGrade(String notice_id) {
  				// TODO Auto-generated method stub
  				
  				
  					     Connection conn = null;
  					     PreparedStatement pstmt = null;
  					     ResultSet rs = null;
  					     LoginDto dto=null;
  					     try {
  					         conn = getConnection();
  					            
  					         pstmt = conn.prepareStatement(
  					         	"select * from d_member where d_id = ?");
  					         pstmt.setString(1, notice_id);
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
  	//------------공지사항상세페이지-------------------------------------------------------------------
	  		public NoticeDto getNoticeArticle(int notice_no, String notice_id)throws Exception{
	  			Connection conn =null;
	  			PreparedStatement pstmt =null;
	  			ResultSet rs = null;
	  			NoticeDto article = null;
	  			
	  			try {
	  				conn= getConnection();
	  				pstmt= conn.prepareStatement(
	  						"update notice set notice_readcount= notice_readcount+1 where notice_no =?");
	  				pstmt.setInt(1, notice_no);
	  				pstmt.executeUpdate();
	  				
	  				pstmt= conn.prepareStatement(
	  						"select * from notice where notice_no=?"
	  						);
	  				pstmt.setInt(1, notice_no);
	  				rs= pstmt.executeQuery();
	  				
	  				if(rs.next()){

	  					article = new NoticeDto();
						article.setNotice_no(rs.getInt("notice_no"));
	  					article.setNotice_id(rs.getString("notice_id"));
	  					article.setNotice_name(rs.getString("notice_name"));
	  					article.setNotice_content(rs.getString("notice_content"));
	  					article.setNotice_readcount(rs.getInt("notice_readcount"));
	  					article.setNotice_reg_date(rs.getTimestamp("notice_reg_date"));
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
	  	//--------공지사항 글삭제-----------------------------------------------------------------	
  	  		public void noticeDeleteArticle(int notice_no){
  	  			Connection conn= null;
  	  			PreparedStatement pstmt=null;
  	  			ResultSet rs = null;
  	  			
  	  			try {
  	  				conn =getConnection();
  	  				pstmt =conn.prepareStatement("delete from notice where notice_no=?");
  	  				pstmt.setInt(1, notice_no);
  	  				pstmt.executeUpdate();
  	  			} catch (Exception ex) {
  	  				ex.printStackTrace();
  	  			}finally {
  	  				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
  	  				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  	  				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  	  			}
  	  		}
  	  		
  	  	//----------공지사항 글수정 받아오기------------------------------------------------------------------
  	  		public NoticeDto NoticeModifyArticle(int notice_no) throws Exception{
  	  			Connection conn= null;
  	  			PreparedStatement pstmt = null;
  	  			ResultSet rs =null;
  	  			NoticeDto article =null;
  	  			
  	  			try {
  	  				conn=getConnection();
  	  				pstmt=conn.prepareStatement(
  	  						"select * from notice where notice_no=?");
  	  				pstmt.setInt(1, notice_no);
  	  				rs = pstmt.executeQuery();
  	  				if(rs.next()){
  	  					article = new NoticeDto();
  	  					article.setNotice_name(rs.getString("notice_name"));
  	  					article.setNotice_content(rs.getString("notice_content"));
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
  	  	//--------공지사항 글수정-------------------------------------------------------------------
  	  		public void NoticeModifyArticle(NoticeDto article)throws Exception{
  	  			Connection conn =null;
  	  			PreparedStatement pstmt=null;
  	  			ResultSet rs =null;
  	  			
  	  			try {
  	  				conn = getConnection();
  	  				pstmt =conn.prepareStatement(
  	  				"update notice set notice_name=?,notice_content=? where notice_no=?");
  	  				
  	  				pstmt.setString(1,article.getNotice_name());
  	  				pstmt.setString(2,article.getNotice_content());
  	  				pstmt.setInt(3,article.getNotice_no());
  	  				
  	  				pstmt.executeUpdate();
  	  				
  	  			} catch (Exception ex) {
  	  				ex.printStackTrace();
  	  			}finally {
  	  				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
  	  				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
  	  				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
  	  			}
  	  		}
//마지막 괄호
}

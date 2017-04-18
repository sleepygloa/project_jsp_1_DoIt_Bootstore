package mvc.doit.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.doit.Company.CompanyDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.ResellBean.ResellReplyDto;
import mvc.doit.ResellBean.ResellbookDto;

public class CompanyDao {
	private static CompanyDao instance = new CompanyDao();
    
    public static CompanyDao getInstance() {return instance; }
    
    private CompanyDao() {}
    
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
      return ds.getConnection();
    }
//------등급확인------------------------------------------------------------------------------
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
//------주소작성---------------------------------------------------------------------------------
	public void insertCompany(CompanyDto com){
		Connection conn = null;
		PreparedStatement pstmt =null;
		//ResultSet rs= null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"insert into d_company values(d_company_seq.nextval,?,?,sysdate)");
			pstmt.setString(1, com.getCom_writer());
			pstmt.setString(2, com.getCom_addr());
			pstmt.executeUpdate();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
	}

//------등급확인------------------------------------------------------------------------------
		public CompanyDto getCom() {
			// TODO Auto-generated method stub
			
			
				        Connection conn = null;
				        PreparedStatement pstmt = null;
				        ResultSet rs = null;
				        CompanyDto com=null;
				        try {
				            conn = getConnection();
				            
				            pstmt = conn.prepareStatement(
				            	"SELECT * FROM (SELECT * FROM d_company ORDER BY com_reg_date DESC) WHERE ROWNUM = 1");
				            rs = pstmt.executeQuery();

				            if (rs.next()) {
				            	com = new CompanyDto();
				            	com.setCom_addr(rs.getString("com_addr"));
							    
							}
				        } catch(Exception ex) {
				            ex.printStackTrace();
				        } finally {
				            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				        }
				        
						return com;
		
		}
}

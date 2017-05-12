package mvc.doit.Company;

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


public class IntroDao{
	
	private static IntroDao instance = new IntroDao();
    
    public static IntroDao getInstance() {return instance; }
    
    private IntroDao() {}
    
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
      return ds.getConnection();
    }
	
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    //---------------------- 회원중 관리자 (코드10)만 뽑는 Count -------------------------//
    public int getAdminCount() throws Exception{
    	int x = 0;
    	try{
    	conn = getConnection();
    	pstmt = conn.prepareStatement(
    			"select count(*) from d_member where d_mech_grade = "+10
    			);
    	rs = pstmt.executeQuery();
    	if(rs.next()){
    		x = rs.getInt(1);
    	}
    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if( rs != null){ try{rs.close(); }catch(SQLException se){}};
    		if( pstmt != null){ try{pstmt.close(); }catch(SQLException se){}};
    		if( conn != null){ try{conn.close(); }catch(SQLException se){}};
    	}
    	
    	return x;
    }
    //---------------------- 회원중 관리자 (코드10)만 뽑는 Count 끝 -------------------------//
    
    //---------------------- 회원중 관리자 (코드10)만 뽑는 List -------------------------//
    public List<LoginDto> getAdminList() throws Exception{
    	List<LoginDto> adminList = null;
		LoginDto ldto = null;
    	try{
    	conn = getConnection();
    	pstmt = conn.prepareStatement(
"select d_no, d_id, d_name, d_phone, d_addr, d_mail, d_birth, d_gender, d_pic, d_mech_grade, r "+
"from (select d_no, d_id, d_name, d_phone, d_addr, d_mail, d_birth, d_gender, d_pic, d_mech_grade, rownum r " + 
"from (select d_no, d_id, d_name, d_phone, d_addr, d_mail, d_birth, d_gender, d_pic, d_mech_grade " + 
"from d_member where d_mech_grade = 10 order by d_no )) where r >= 1 and r <= 10"
    			);
    	rs = pstmt.executeQuery();
    	if(rs.next()){
    		adminList = new ArrayList<LoginDto>();
    		do{
    		ldto = new LoginDto();
    		ldto.setD_no(rs.getInt("d_no"));
    		ldto.setD_id(rs.getString("d_id"));
    		ldto.setD_name(rs.getString("d_name"));
    		ldto.setD_phone(rs.getString("d_phone"));
    		ldto.setD_addr(rs.getString("d_addr"));
    		ldto.setD_mail(rs.getString("d_mail"));
    		ldto.setD_birth(rs.getString("d_birth"));
    		ldto.setD_gender(rs.getString("d_gender"));
    		ldto.setD_pic(rs.getString("d_pic"));
    		ldto.setD_mech_grade(rs.getInt("d_mech_grade"));
    		
    		adminList.add(ldto);
    		
    		}while(rs.next());
    	}
    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if( rs != null){ try{rs.close(); }catch(SQLException se){}};
    		if( pstmt != null){ try{pstmt.close(); }catch(SQLException se){}};
    		if( conn != null){ try{conn.close(); }catch(SQLException se){}};
    	}
    	
    	return adminList;
    }   
    //---------------------- 회원중 관리자 (코드10)만 뽑는 List 끝 -------------------------//
}

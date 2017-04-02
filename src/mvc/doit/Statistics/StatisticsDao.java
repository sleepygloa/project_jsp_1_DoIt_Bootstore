package mvc.doit.Statistics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.doit.Login.LoginDto;
import mvc.doit.Login.mySellingListDto;




public class StatisticsDao {
	
	private static StatisticsDao instance = new StatisticsDao();
    
    public static StatisticsDao getInstance() {return instance; }
    
    private StatisticsDao() {}
    
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
      return ds.getConnection();
    }
	
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    
//가입자의 연령대 파악
    public List getRegistAvgAge() throws Exception{
    	List arrayList = null;
    	try{
 
    	conn = getConnection();
    	pstmt = conn.prepareStatement("select substr(d_birth,1,4) As d_birth from d_member");
	    rs = pstmt.executeQuery();
    	
    	if(rs.next()){
    		arrayList = new ArrayList();
    		do{
    			StatisticsDto sdto = new StatisticsDto();
//    			sdto.setD_no(rs.getInt("d_no"));
//    			sdto.setD_id(rs.getString("d_id"));
//    			sdto.setD_name(rs.getString("d_name"));
//    			sdto.setD_addr(rs.getString("d_addr"));
    			sdto.setD_birth(rs.getString("d_birth"));
    			arrayList.add(sdto);
    		}while(rs.next());
    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
	        if( rs != null){ try{ rs.close(); }catch(SQLException se){} };
	        if( pstmt != null){ try{ pstmt.close(); }catch(SQLException se){} };
	        if( conn != null){ try{ conn.close(); }catch(SQLException se){} };
	    }
    	
    	return arrayList;
    }
    
 
//메인 메소드 괄호임   
}
    


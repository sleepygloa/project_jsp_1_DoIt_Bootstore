package mvc.doit.ChatAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import mvc.doit.Login.LoginDao;
import mvc.doit.SuperAction.SuperAction;

public class ChatDao implements SuperAction{
	
private static ChatDao instance = new ChatDao();
    
    public static ChatDao getInstance() {return instance; }
    
    private ChatDao() {}
    
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
      return ds.getConnection();
    }
	
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    //////////////////////////////////////// 채팅내용 ///////////////////////////////////////////
    public void insMess(String cont, String name, String pic) throws Exception{
    	
    	try{
    		conn = getConnection();
    		String sql = "insert into chat values(seq_message.nextval, ?, ?, ?, sysdate)";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, pic);
    		pstmt.setString(2, name);
    		pstmt.setString(3, cont);
    		
    		pstmt.executeUpdate();
    		
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		 if(rs != null){try{rs.close();}catch(SQLException s){}}
             if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
             if(conn != null){try{conn.close();}catch(SQLException s){}}
    	}
    	
    }

    //////////////////////////////////////// 채팅삽입 끝 ///////////////////////////////////////////
    public List getChat() throws Exception{
    	List arrayList = null;
    	try{
    		conn = getConnection();
    		String sql = "select * from chat order by ch_no asc";
    		pstmt = conn.prepareStatement(sql);
    		
    		rs = pstmt.executeQuery();
    		if(rs.next()){
    			arrayList = new ArrayList();
    			do{
    				ChatDto cdto = new ChatDto();
    				cdto.setCh_no(rs.getInt("ch_no"));
    				cdto.setThum(rs.getString("ch_thum"));
    				cdto.setD_id(rs.getString("d_id"));
    				cdto.setCh_cont(rs.getString("ch_cont"));
    				cdto.setCh_date(rs.getTimestamp("ch_date"));
    				
    				arrayList.add(cdto);
    			}while(rs.next());
    			
    			
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		 if(rs != null){try{rs.close();}catch(SQLException s){}}
             if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
             if(conn != null){try{conn.close();}catch(SQLException s){}}
    	}
    	
    	return arrayList;
    	
    }
    
    
    
    
    
    
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

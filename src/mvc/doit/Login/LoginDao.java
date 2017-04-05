package mvc.doit.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;


public class LoginDao implements SuperAction{
	
	private static LoginDao instance = new LoginDao();
    
    public static LoginDao getInstance() {return instance; }
    
    private LoginDao() {}
    
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
      return ds.getConnection();
    }
	
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    
	//-----로그인 --------------로그인 --------------로그인 --------------로그인 --------------로그인 --------------로그인 ---------
    public boolean loginCheck(String id, String passwd){
       boolean result =  false; //평소에 로그인 실패, 비로그인상태          
       try{
          conn = getConnection();
          pstmt = conn.prepareStatement(
                "select * from d_member where d_id = ? and d_pass = ?");
          pstmt.setString(1, id);
          pstmt.setString(2, passwd);
          rs = pstmt.executeQuery(); 
             if(rs.next()){
                result = true; //로그인 성공 (DB와 입력 ID,pass일치)
             }
          }catch(Exception e){
             e.printStackTrace();
          }finally{
             if(rs != null){try{rs.close();}catch(SQLException s){}}
             if(pstmt != null){try{pstmt.close();}catch(SQLException s){}}
             if(conn != null){try{conn.close();}catch(SQLException s){}}
          }
          return result;
       }
    
    /*------------------ 로그인 끝-------------------------------------------------------------------------*/

     
    
    /*------------------------------- 회원 가입 ---------------------------------------------------------*/
  	public void insertMember(LoginDto dto){
  		
  	      try{
  	         conn=getConnection();
  	         String sql = "insert into d_member values(d_mem_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
  	         pstmt = conn.prepareStatement(sql);
  	    
  	         pstmt.setString(1, dto.getD_id());
  	         pstmt.setString(2, dto.getD_pass());
  	         pstmt.setString(3, dto.getD_name());
  	         pstmt.setString(4, dto.getD_phone());
  	         pstmt.setString(5, dto.getD_addr());
  	         pstmt.setString(6, dto.getD_person());
  	         pstmt.setString(7, dto.getD_mail());
  	         pstmt.setString(8, dto.getD_birth());
  	         pstmt.setString(9, dto.getD_gender());
  	         pstmt.setString(10, dto.getD_pic());	         
  	         pstmt.setInt(11, dto.getD_nom_grade());
  	         pstmt.setInt(12, dto.getD_mech_grade());
  	        
  			pstmt.executeUpdate();
  			
  		}catch(Exception e){
  			e.printStackTrace();
  		}finally{
  			if(pstmt !=null){try{pstmt.close();}catch(SQLException s){}}
  			if(conn !=null){try{conn.close();}catch(SQLException s){}}	
  		}		
  	      
  	}
    
    /*------------------------------- 회원가입 끝 ------------------------------------------------------*/
    
  	/*------------------------------- 중복확인 ---------------------------------------------------------*/
	public int confirmId(String d_id) 
			throws Exception {
				Connection conn = null;
		        PreparedStatement pstmt = null;
				ResultSet rs= null;
		        String dbpasswd="";
				int x=-1;
		        
				try {
		            conn = getConnection();
		            
		            pstmt = conn.prepareStatement(
		            	"select d_id from d_member where d_id = ?");
		            pstmt.setString(1, d_id);
		            rs= pstmt.executeQuery();

					if(rs.next())
						x= 1; //해당 아이디 있음
					else
						x= -1;//해당 아이디 없음		
		        } catch(Exception ex) {
		            ex.printStackTrace();
		        } finally {
					if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		        }
				return x;
			}	
	
	
	/*------------------------------- 중복 확인 끝 ---------------------------------------------*/
    
	/*------------------------------- 회원정보 - 회원번호로 회원 id 출력 --------------------------------------------*/
	public String getMemNo(int d_no){
		String jo_id = null;
		try{
			conn = getConnection();
			String sql = "select d_id from d_member where d_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, d_no);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				jo_id = rs.getString("d_id");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{ 
			if(conn != null){
				try{conn.close();}catch(SQLException s){}
			}
			if(rs != null){
				try{rs.close();}catch(SQLException s){}
			}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException s){}
			}
		}
		
		return jo_id;
	}
	
	
	/*------------------------------- 회원정보 - 회원번호로 회원 id 출력 끝 --------------------------------------------*/
	
	
	/*------------------------------- 회원정보 출력 --------------------------------------------*/
	public LoginDto getMember(String jo_id) throws Exception{
		LoginDto m_dto = null;
		try{
			conn = getConnection(); //1, 2단계 입력
			String sql6 = "select * from d_member where d_id = ?";
			PreparedStatement pstmt6 = conn.prepareStatement(sql6);
			pstmt6.setString(1, jo_id);
			
			rs = pstmt6.executeQuery(); //result 쿼리 확인
			
			if(rs.next()){
				m_dto = new LoginDto();
				m_dto.setD_no(rs.getInt("d_no"));
				m_dto.setD_id(rs.getString("d_id"));
				m_dto.setD_pass(rs.getString("d_pass"));
				m_dto.setD_name(rs.getString("d_name"));
				
				String pho = rs.getString("d_phone");
				String pho_cut[] = pho.split("-");
				m_dto.setUser_phone1(pho_cut[0]);
				m_dto.setUser_phone2(pho_cut[1]);
				m_dto.setUser_phone3(pho_cut[2]);
				
				m_dto.setD_person(rs.getString("d_person"));
				m_dto.setD_addr(rs.getString("d_addr"));
				
				m_dto.setD_gender(rs.getString("d_gender"));
				
				String bir = rs.getString("d_birth");
				String bir_cut[] = bir.split("년");
				m_dto.setUser_birth1(bir_cut[0]); //년 삽입
				
				String bir2 = bir_cut[1]; 
				String bir_cut2[] = bir2.split("월");
				m_dto.setUser_birth2(bir_cut2[0]); //월 삽입
				
				String bir3 = bir_cut2[1]; 
				String bir_cut3[] = bir3.split("일");
				m_dto.setUser_birth3(bir_cut3[0]); //월 삽입
				
				m_dto.setD_pic(rs.getString("d_pic"));
				
				String e_mai = rs.getString("d_mail");
				String mail_cut[] = e_mai.split(" @ ");
				m_dto.setUser_mail1(mail_cut[0]);
				m_dto.setUser_mail2(mail_cut[1]);
				
				m_dto.setD_date(rs.getTimestamp("d_date"));
				m_dto.setD_nom_grade(rs.getInt("d_nom_grade")); //일반회원등급
				m_dto.setD_mech_grade(rs.getInt("d_mech_grade")); //판매자 여부
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{conn.close();}catch(SQLException s){}
			}
			if(rs != null){
				try{rs.close();}catch(SQLException s){}
			}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException s){}
			}
		}//finally 끝
		
		return m_dto;
	}
	
	
	
	/*------------------------------- 회원정보 출력 끝 --------------------------------------------*/
    
    
    
    /*------------------------------- 회원 정보 수정 ----------------------------------------------------------*/
    public void upDate(LoginDto dto, String id){
    	try{
    		conn = getConnection(); //1,2 단계 컨넥션 풀 메서드 불러오기
			String sql1 = "update d_member set d_pic=?, d_pass=?, d_phone=?, d_birth=?, d_addr=?, d_mail=? where d_id=? ";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1); 
            pstmt1.setString(1, dto.getD_pic());
            pstmt1.setString(2, dto.getD_pass());
            pstmt1.setString(3, dto.getUser_phone1());
            pstmt1.setString(4, dto.getUser_birth1());
            pstmt1.setString(5, dto.getD_addr());
            pstmt1.setString(6, dto.getUser_mail1());
            pstmt1.setString(7, id); //id 구분
            
            pstmt1.executeUpdate();
			

    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if(conn != null){
				try{conn.close();}catch(SQLException s){}
			}
			if(rs != null){
				try{rs.close();}catch(SQLException s){}
			}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException s){}
			}
    	}

    }
    
    
    /*------------------------------- 회원 정보 수정 끝 ----------------------------------------------------------*/
    
    
	
	/*------------------------------- 비밀번호 확인 ----------------------------------------------------------*/
    public boolean checkPw(String pwCheck, String se_pass){
    	boolean pw_re = false;
    	try{
    		conn = getConnection(); //1, 2단계 입력
			String sql6 = "select * from d_member where d_pass = ?";
			PreparedStatement pstmt6 = conn.prepareStatement(sql6);
			pstmt6.setString(1, pwCheck);
			
			rs = pstmt6.executeQuery(); //result 쿼리 확인
    		
			String db_pass = rs.getString("d_pass");
			if(db_pass.equals(se_pass)){
				pw_re = true;
			}
			
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if(conn != null){
				try{conn.close();}catch(SQLException s){}
			}
			if(rs != null){
				try{rs.close();}catch(SQLException s){}
			}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException s){}
			}
    	}
    	
    	return pw_re;
    }
    
    
    /*------------------------------- 비밀번호 확인 끝 ----------------------------------------------------------*/
	
	
	
    
    /*------------------------------- 회원 탈퇴 ----------------------------------------------------------*/
	public int deleteMember(String id, String pw) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        String dbpasswd="";
        int x=-1;
        try {
			conn = getConnection();

            pstmt = conn.prepareStatement(
            	"select d_pass from d_member where d_id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
			if(rs.next()){
				dbpasswd= rs.getString("d_pass"); 
				if(dbpasswd.equals(pw)){
					pstmt = conn.prepareStatement("delete from d_member where d_id=?");
                    pstmt.setString(1, id);
                    pstmt.executeUpdate();
					x= 1; //회원탈퇴 성공
				}else
					x= 0; //비밀번호 틀림
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
    
    /*------------------------------- 회원 탈퇴 끝 ----------------------------------------------------------*/
    
	
	
	//----------------------------------- 아이디 찾기 -----------------------------------------------------------//
	   public LoginDto iddSearch(String d_name, String d_mail) throws Exception{
	      LoginDto idsearch =null;
	       try{
	            conn=getConnection();
	            pstmt=conn.prepareStatement("select * from d_member where d_name=? and d_mail=?");
	                  pstmt.setString(1, d_name);
	                  pstmt.setString(2, d_mail);
	            rs = pstmt.executeQuery();
	            
	            if(rs.next()){      
	               idsearch = new LoginDto();
	               idsearch.setD_id(rs.getString("d_id"));
	            }
	         }catch(Exception ex){
	            ex.printStackTrace();
	         }finally{
	            if(rs!=null) try{rs.close();}catch (SQLException eX) {}
	            if(conn!=null) try{conn.close();}catch (SQLException eX) {}   
	            if(pstmt!=null) try{pstmt.close();}catch (SQLException eX) {}
	         }
	      return idsearch;
	   }
	 //----------------------------------- 아이디 찾기 끝 -----------------------------------------------------------//
	
	
	//------------------------------------------------------ 비밀번호를 찾기 위한 저장 ------------------------------------
	public LoginDto idSearch(String user_name, String D_mail)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		LoginDto idsearch =null;
		try{
			conn=getConnection();
			pstmt=conn.prepareStatement("select * from d_member where D_NAME=? and D_MAIL=?");
					pstmt.setString(1, user_name);
					pstmt.setString(2, D_mail);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				idsearch = new LoginDto();
			idsearch.setD_id(rs.getString("user_id"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch (SQLException eX) {}
			if(conn!=null) try{conn.close();}catch (SQLException eX) {}	
			if(pstmt!=null) try{pstmt.close();}catch (SQLException eX) {}
		}
		return idsearch;
	
	}
	//------------------------------------------------------ 비밀번호를 찾기 위한 저장 끝------------------------------------
	
	
	//------------------------------------------------------비밀번호 찾기-------------------------------------------------------
    public boolean pwCheck(String sear_id, String sear_name, String sear_mail1, String sear_mail2){
    	Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		boolean pass_re = false;
		try{
			conn = getConnection(); //1,2단계 컨넥션 풀 작성
			pstmt = conn.prepareStatement("select d_pass from d_member where d_id =? and d_name =? and d_mail=?");
			pstmt.setString(1, sear_id);
			pstmt.setString(2, sear_name);
			pstmt.setString(3, sear_mail1+" @ "+sear_mail2);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				pass_re = true;
			}else{
				pass_re = false;
			}
			
		 } catch(Exception ex) {
	            ex.printStackTrace();
	     } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	     }
		
		return pass_re;
	}
  //------------------------------------------------------비밀번호 찾기 끝-------------------------------------------------------
    
  //------------------------------------------------------새 비밀번호 입력-------------------------------------------------------    
    public boolean newPw(String d_id, String new_pass){
    	Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		boolean new_pw = false;
		try{
			conn = getConnection();//컨넥션 연결
			pstmt = conn.prepareStatement("update d_member set d_pass = ? where d_id=?");
			pstmt.setString(1, new_pass);
			pstmt.setString(2, d_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				new_pw = true;
			}else{
				new_pw = false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{conn.close();}catch(SQLException s){}
			}
			if(rs != null){
				try{rs.close();}catch(SQLException s){}
			}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException s){}
			}
		}
		
		return new_pw;
    }
    //------------------------------------------------------새 비밀번호 입력 끝-------------------------------------------------------    
	
	
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

package mvc.doit.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import mvc.doit.Cart.CartDao;
import mvc.doit.Cart.CartListDto;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.Manager.ManDao;
import mvc.doit.SuperAction.SuperAction;

public class ManDao implements SuperAction{

private static ManDao instance =  new ManDao();  
	
	public static ManDao getInstance(){ return instance;}
	
	private ManDao(){}
	
	//1, 2단계 설정
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
	    Context envCtx = (Context) initCtx.lookup("java:comp/env");
	    DataSource ds = (DataSource)envCtx.lookup("jdbc/khdb");
	    return ds.getConnection();
	}
		
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//----------------------------------------- dash 가격 불러오기 -----------------------------------------------//
	public ManDto getDashM() throws Exception{
		ManDto mdto = new ManDto();
		
		try{
			conn = getConnection();
			String sql = "select * from doit_aco";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				mdto.setD_seller(rs.getInt("d_seller"));
				mdto.setD_lib(rs.getInt("d_lib"));
				mdto.setD_trade(rs.getInt("d_trade"));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null)try{rs.close();}catch(SQLException ex){}
			if(pstmt != null)try{pstmt.close();}catch(SQLException ex){}
			if(conn != null)try{conn.close();}catch(SQLException ex){}
		}
		
		return mdto;
	}
	
	//----------------------------------------- dash 가격 불러오기 -----------------------------------------------//
	
	
	
	//---------------------------------------------- 연체자 리스트 불러오기 --------------------------------------//
	public List getLibBla(){
		List getList = null;
		//String d_id = "";//회원 아이디 출력

		try{
			conn = getConnection();
			String sql ="select * from d_cart where d_ovdue = 5";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				getList = new ArrayList();
				//LoginDao ldao = LoginDao.getInstance(); //회원 dao 객체화
				do{
					//LoginDto ldto = new LoginDto(); //회원 dto 객체화
					//d_id = ldao.getMemNo(rs.getInt("d_no"));
					//ldto = ldao.getMember(d_id);
					//getList.add(ldto);
					CartListDto cldto= new CartListDto();//카트 내용 dto
					cldto.setCa_no(rs.getInt("ca_no")); //카트 번호
					cldto.setD_no(rs.getInt("d_no")); //회원번호
					cldto.setD_rent(rs.getString("d_rent")); //도서관 장바구니 내용
					cldto.setDr_rent(rs.getString("dr_rent")); //도서 대여 리스트
					cldto.setD_ov_date(rs.getTimestamp("d_ov_date")); //카트 생성일자
					getList.add(cldto);
					
				}while(rs.next());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null)try{rs.close();}catch(SQLException ex){}
			if(pstmt != null)try{pstmt.close();}catch(SQLException ex){}
			if(conn != null)try{conn.close();}catch(SQLException ex){}
		}
		
		
		return getList;
	}
	
	//---------------------------------------------- 연체자 리스트 불러오기 --------------------------------------//
	
	
	//---------------------------------------------- 연체 처리할 도서 리스트 --------------------------------------//
	   public List comDate() throws ParseException{
		   	List getOver = null;
		   
		      SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		      String today = fm.format(new Date());
		      
		      long diffDays = 0; //차이 비교 값
		      
		      try {
		         conn= getConnection();
		         pstmt = conn.prepareStatement("select * from b_rent_over where br_over_date IS NOT NULL");
		         rs = pstmt.executeQuery();
		         
		         if(rs.next()){
		        	getOver = new ArrayList();
		        	do{
		        		Date aa = rs.getDate("br_over_date");
				        Calendar cal = Calendar.getInstance();
				        cal.setTime(aa);
				        String str = fm.format(cal.getTime());
				        Date startDate = fm.parse(str);
				        Date todayDate = fm.parse(today);
				         
				        long diff = todayDate.getTime() - startDate.getTime();
				        diffDays = diff / (24 * 60 * 60 * 1000);
				        
				        if(diffDays > 7L){
				        	CartListDto cldto = new CartListDto();
				        	cldto.setBr_no(rs.getInt("br_no")); //책 seq 번호
				        	cldto.setBr_code(rs.getString("br_code")); //책 고유코드
				        	cldto.setBr_waiter(rs.getString("br_waiter")); //대기자
				        	cldto.setBr_deli(rs.getInt("br_deli")); //배송상태 : 5 ->연체
				        	cldto.setBr_over_date(rs.getTimestamp("br_over_date")); //최초 빌린날짜
				        	getOver.add(cldto);
				        	
				        }else{
				        	continue;
				        }
				        
		        	}while(rs.next());
			        
			     }
			         
		         
		      } catch (Exception e) {
		         e.printStackTrace();
		      }finally{
		         if(rs != null)try{rs.close();}catch(SQLException ex){}
		         if(pstmt != null)try{pstmt.close();}catch(SQLException ex){}
		         if(conn != null)try{conn.close();}catch(SQLException ex){}
		      }
		      return getOver;
		   }
	
	//---------------------------------------------- 연체 처리할 도서 리스트 끝 --------------------------------------//
	
	
	   //-------------------------------------------- 해당 도서 연체 처리 ----------------------------------------------//
	   public void modiLib(String br_code,int gugu) throws Exception{
		   String sql = "";
		   List com = comDate();
		   try{
			   conn = getConnection();
			   if(com != null){//해야할 일이있는가
				   
				   if(gugu == 1){ //전체 업데이트
					   sql = "update b_rent_over set br_deli = 5 where br_over_date IS NOT NULL";
					   pstmt = conn.prepareStatement(sql);
				   }else{ //한개씩 
					   sql = "update b_rent_over set br_deli = 5 where br_code = ? and br_over_date IS NOT NULL ";
					   pstmt = conn.prepareStatement(sql);
					   pstmt.setString(1, br_code);
				   }
				   
				   pstmt.executeUpdate();

			   }

		   }catch(Exception e){
			   e.printStackTrace();
		   }finally{
			   if(rs != null)try{rs.close();}catch(SQLException ex){}
		       if(pstmt != null)try{pstmt.close();}catch(SQLException ex){}
		       if(conn != null)try{conn.close();}catch(SQLException ex){}
		   }

	   }

	   //-------------------------------------------- 해당 도서 연체 처리 끝 ----------------------------------------------//
	   
	   //-------------------------------------------- 도서 연체 처리 후 첫번째 회원 연체 처리 ---------------------------//
	   public void modiPers() throws Exception{
		   String firstWait = "";//첫번째 대기자
		   int firstWa; // 대기자 타입 변경
		   
		   try{
			   conn = getConnection();
			   String sql = "select br_code from b_rent_over where br_deli = 5 ";
			   pstmt = conn.prepareStatement(sql);
			   
			   rs = pstmt.executeQuery();
			   if(rs.next()){
				   CartDao cdao = CartDao.getInstance(); //장바구니 객체
				   do{
					   firstWait = cdao.getFirstM(rs.getString("br_code")); //첫번째 대기자 출력,저장
					   if(!firstWait.equals("")){
						   firstWa = Integer.parseInt(firstWait); //숫자로 변경
						   sql = "update d_cart set d_ovdue = 5 where d_no = ?";
						   pstmt = conn.prepareStatement(sql);
						   pstmt.setInt(1, firstWa);
						   
						   pstmt.executeUpdate();
					   }else{
						   continue;
					   }
					   
					   
				   }while(rs.next());
			   }
			   
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }finally{
			 if(rs != null)try{rs.close();}catch(SQLException ex){}
		     if(pstmt != null)try{pstmt.close();}catch(SQLException ex){}
		     if(conn != null)try{conn.close();}catch(SQLException ex){}
		   }

	   }
	  //-------------------------------------------- 도서 연체 처리 후 첫번째 회원 연체 처리 ---------------------------//
	   
	   
	   //------------------------------------------ 도서관 상품 배송상태에 따른 출력 ------------------------------------//
	   public List getDeliCont(int br_deli) throws Exception{
		   List getDeli = null;
		   try{
			   conn = getConnection();
			   if(br_deli == 10){ //전체 불러오기
				   String sql =  "select b.br_no, b.br_code,  r.br_thumpic, r.br_name, r.br_pub, r.br_writer ,b.br_waiter, b.br_deli, b.br_over_date ";
				   		sql += " from b_rent_over b, b_rent r where r.br_no = b.br_no and b.br_waiter != ','";
				   pstmt = conn.prepareStatement(sql);
			   }else{
				   String sql =  "select b.br_no, b.br_code,  r.br_thumpic, r.br_name, r.br_pub, r.br_writer ,b.br_waiter, b.br_deli, b.br_over_date ";
						   sql +=	" from b_rent_over b, b_rent r where r.br_no = b.br_no and b.br_deli=? and b.br_waiter != ','";
				   pstmt = conn.prepareStatement(sql);
				   pstmt.setInt(1, br_deli);
			   }
			   
			   rs = pstmt.executeQuery();
			   
			   if(rs.next()){
				   getDeli = new ArrayList();
				   do{
					   ManDto mdto = new ManDto();
					   mdto.setBr_no(rs.getInt("br_no"));
					   mdto.setBr_code(rs.getString("br_code"));
					   mdto.setBr_thumpic(rs.getString("br_thumpic"));
					   mdto.setBr_name(rs.getString("br_name"));
					   mdto.setBr_pub(rs.getString("br_pub"));
					   mdto.setBr_writer(rs.getString("br_writer")); //저자
					   mdto.setBr_waiter(rs.getString("br_waiter")); //대기자
					   mdto.setBr_deli(rs.getInt("br_deli"));
					   mdto.setBr_over_date(rs.getTimestamp("br_over_date"));
					   getDeli.add(mdto);
				   }while(rs.next());
			   }
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }finally{
			   if(rs != null)try{rs.close();}catch(SQLException ex){}
			   if(pstmt != null)try{pstmt.close();}catch(SQLException ex){}
			   if(conn != null)try{conn.close();}catch(SQLException ex){}
		   }
		   
		   return getDeli;
	   }
	   
	   //------------------------------------------ 도서관 상품 배송상태에 따른 출력 ------------------------------------//
	   
	   
	   //----------------------------------------- 도서관 상품 배송상태 변경 --------------------------------------------//
	   public void modiDeliv(int br_deli, String br_code){ //해당코드
		   String sql = "";
		   try{
			   conn = getConnection();
			   if(br_deli < 3){ //배송완료를 제외한 나머지
				   if(br_code.equals("")){ //전체처리
					   sql="update b_rent_over set br_deli=? where br_deli=?";
					   pstmt = conn.prepareStatement(sql);
					   pstmt.setInt(1, br_deli+1);
					   pstmt.setInt(2, br_deli);
				   }else{ //개별처리
					   sql="update b_rent_over set br_deli=? where br_code=?";
					   pstmt = conn.prepareStatement(sql);
					   pstmt.setInt(1, br_deli+1);
					   pstmt.setString(2, br_code);
				   }
			   }else if(br_deli == 3){ //배송완료
				   if(br_code.equals("")){ //전체 처리
					   sql = "update b_rent_over set br_deli = 0 where br_deli = ?";
					   pstmt = conn.prepareStatement(sql);
					   pstmt.setInt(1, br_deli);
				   }else{//개별처리
					   sql = "update b_rent_over set br_deli = 0 where br_code = ?";
					   pstmt = conn.prepareStatement(sql);
					   pstmt.setString(1, br_code);
				   }  
			   }
			   pstmt.executeUpdate();
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }finally{
			   if(rs != null)try{rs.close();}catch(SQLException ex){}
			   if(pstmt != null)try{pstmt.close();}catch(SQLException ex){}
			   if(conn != null)try{conn.close();}catch(SQLException ex){}
		   }
		   
	   }
	   //----------------------------------------- 도서관 상품 배송상태 변경 끝 --------------------------------------------//
	   
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}

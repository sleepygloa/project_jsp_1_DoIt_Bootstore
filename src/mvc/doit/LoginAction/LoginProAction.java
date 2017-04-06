package mvc.doit.LoginAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import mvc.doit.SuperAction.SuperAction;
import mvc.doit.Cart.CartDao;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.Online.OnDao;


public class LoginProAction implements SuperAction {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		
//---- 변수 설정 ---------------------------------------------------------------------
		request.setCharacterEncoding("UTF-8");
		String id = null;
		String passwd = null;
		boolean check = false;
	    id = request.getParameter("d_id");
		passwd  = request.getParameter("d_pass");
		
		
//---- 로그인 진행 Dao, 회원 테이블에 아이디와 비밀번호를 비교하여 로그인 할 것인지 확인한다 -----------------		
		LoginDao manager = LoginDao.getInstance();
	    check= manager.loginCheck(id,passwd);
	    
	    if(check==true){
	    	HttpSession session = request.getSession();
	    	session.setAttribute("memId", id);
	    	session.setAttribute("memPass", passwd);
	    	
	    	LoginDto ltt = manager.getMember(id);
	    	session.setAttribute("memNG", ltt.getD_nom_grade()); //회원 일반 등급
	    	session.setAttribute("memMG", ltt.getD_mech_grade()); //회원 판매자 등급
	    	session.setAttribute("memNo", ltt.getD_no()); //회원 번호
	    	session.setAttribute("memAddr", ltt.getD_addr()); //회원주소
	    	session.setAttribute("memPhone", ltt.getD_phone()); //회원전화번호
	    	session.setAttribute("memName", ltt.getD_name()); //회원 이름
	    	session.setAttribute("memPic", ltt.getD_pic()); //회원 썸네일 사진
	    	
	    	
	    	//세션에 장바구니 저장
	    	int br_no = (int)session.getAttribute("memNo");
			String rent = "d_rent"; //도서관 
			String Mech = "d_sell"; //직접판매
			
			CartDao cdao = CartDao.getInstance();
			
			//도서관 내용 저장
			List CartL = cdao.getHeadCart(br_no, rent);
		    session.setAttribute("CartL", CartL);
		    
		    //직접판매 내용 저장
		    List CartP = cdao.getHeadCart(br_no, Mech);
		    session.setAttribute("CartP", CartP);
		    
	    }else{}
	    
	    
	    
//---- 회원이 로그인하였을때, 등급을 확인하여, 등급이 상승되었음을 알려줍니다.--------------------------------------
	    int d_bcode = 0;
	    String Check = "d_id";
	    OnDao dao = OnDao.getInstance();
	    String userGradeCheck = dao.getUserSellPurchaseCountToGrade(d_bcode, id, Check);

	    request.setAttribute("check", check);
	    request.setAttribute("d_id", id);
	    request.setAttribute("d_pass", passwd);
	    request.setAttribute("userGradeCheck", userGradeCheck);

	    
	return "/d_login/loginPro.jsp"; //View 경로
	}
}

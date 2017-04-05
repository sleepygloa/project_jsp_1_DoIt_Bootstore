package mvc.doit.LoginAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import mvc.doit.SuperAction.SuperAction;
import mvc.doit.Cart.CartDao;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;


public class LoginProAction implements SuperAction {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		
//-----------------------------로그인 비즈니스 로직 ------------------------------------------
		
//아이디 값을 받기위해 UTF-8 설정한다.
//받아온 입력된 id, pass를 변수로 지정한다.
//Dao에있는 loginCheck(id,passwd) 에 입력, db와 비교하여 return boolean 으로 받는다.
//true 일 때, 세션등록, 로그인성공
//false일때, 세션X, 로그인실패
		request.setCharacterEncoding("UTF-8");

		String id = null;
		String passwd = null;
		boolean check = false;
		
	    id = request.getParameter("d_id");
		passwd  = request.getParameter("d_pass");
		
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

	    request.setAttribute("check", check);
	    request.setAttribute("d_id", id);
	    request.setAttribute("d_pass", passwd);

	    
	return "/d_login/loginPro.jsp"; //View 경로
	}
}

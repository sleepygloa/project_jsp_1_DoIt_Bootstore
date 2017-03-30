package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import mvc.doit.SuperAction.SuperAction;
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
	    	session.setAttribute("memNG", ltt.getD_nom_grade());
	    	session.setAttribute("memMG", ltt.getD_mech_grade());
	    	session.setAttribute("memNo", ltt.getD_no());
	    	session.setAttribute("memAddr", ltt.getD_addr());
	    }else{}

	    request.setAttribute("check", check);
	    request.setAttribute("d_id", id);
	    request.setAttribute("d_pass", passwd);

	    
	return "/d_login/loginPro.jsp"; //View 경로
	}
}

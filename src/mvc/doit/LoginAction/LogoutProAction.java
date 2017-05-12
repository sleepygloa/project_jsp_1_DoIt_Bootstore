package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.SuperAction.SuperAction;

public class LogoutProAction implements SuperAction {
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		
		
	return "/d_login/logoutPro.jsp"; //View 경로
	}
}
//로그아웃 성공, 모든 세션 종료. 장바구니 세션사용시, 세션에 대한 지정이 필요함
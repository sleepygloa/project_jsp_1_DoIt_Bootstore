package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.SuperAction.SuperAction;


public class LogoutAction implements SuperAction {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		int x = 1;
		
		request.setAttribute("x", x);
		
	return "/d_login/logout.jsp"; //View 경로
	}
}


//헤더페이지에서 로그아웃과 로그아웃확인창을 구분하기 위해서 임의의 변수 x 값을 전달, x에 의미는 없음
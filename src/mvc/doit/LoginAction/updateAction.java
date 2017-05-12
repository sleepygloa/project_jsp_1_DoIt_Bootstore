package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;

public class updateAction implements SuperAction {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		LoginDao log = LoginDao.getInstance();
		LoginDto lto = log.getMember(id); //회원정보 꺼냄

		
		request.setAttribute("lto", lto); //회원정보 저장하여 전송

		
		return "/d_login/my_mody.jsp";
	}
	
	
}

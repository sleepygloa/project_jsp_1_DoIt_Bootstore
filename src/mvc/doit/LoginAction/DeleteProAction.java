package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.SuperAction.SuperAction;
import mvc.doit.Login.LoginDao;

public class DeleteProAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		String pw  = request.getParameter("d_pass");
		
		LoginDao manager = LoginDao.getInstance();
	    int check = manager.deleteMember(id,pw);
		
	    request.setAttribute("check", check);	
		
		if(check==1){ // pw가 정확하게 입력되었을때
			session.invalidate();
	

		
	}
		return "/d_login/deletePro.jsp";

	
	}
}

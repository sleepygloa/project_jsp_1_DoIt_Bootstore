package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.SuperAction.SuperAction;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;

public class PwFindProAction implements SuperAction {
	public String execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
				
		request.setCharacterEncoding("UTF-8"); 
		
		String id = request.getParameter("d_id");
	 	String name = request.getParameter("d_name");
	 	String user_mail1 = request.getParameter("user_mail1");
	 	String user_mail2 = request.getParameter("user_mail2");
		
	 	LoginDao manager = LoginDao.getInstance();		
		boolean pwcheck = manager.pwCheck(id, name, user_mail1, user_mail2);

		request.setAttribute("pwcheck", pwcheck);
		request.setAttribute("id", id);
		return "/d_login/pwFindPro.jsp";
	
		
	}

}

package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.SuperAction.SuperAction;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;

public class IdSearchAction implements SuperAction{
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		
		String user_mail1 = request.getParameter("user_mail1");
		String user_mail2 = request.getParameter("user_mail2");				
		String d_name = request.getParameter("d_name");
	    String d_mail = user_mail1 +" @ "+ user_mail2;
	    
		//String d_mail = request.getParameter("d_mail");

		LoginDao dao = LoginDao .getInstance();
		LoginDto c = dao.iddSearch(d_name, d_mail);


		request.setAttribute("c", c);
		

		
		return "/d_login/my_sub_search.jsp";
	}
}






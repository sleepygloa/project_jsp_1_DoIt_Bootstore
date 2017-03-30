package mvc.doit.LoginAction;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.SuperAction.SuperAction;
import mvc.doit.Login.LoginDao;
 
public class ConfirmIdAction implements SuperAction {
 
 
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 	request.setCharacterEncoding("utf-8");
 
		
		    String d_id = request.getParameter("d_id");
			LoginDao dao = LoginDao.getInstance();
		    int check= dao.confirmId(d_id);
 
		    request.setAttribute("check", check);
		    request.setAttribute("d_id", d_id);
			  
		  
		
		return "/d_login/confirmid.jsp";
	}
 
}


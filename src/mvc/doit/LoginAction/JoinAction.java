package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.SuperAction.SuperAction;


public class JoinAction implements SuperAction{
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return "/d_login/join.jsp";
	}
}
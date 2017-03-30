package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import mvc.doit.SuperAction.SuperAction;
import mvc.doit.Login.LoginDao;

public class PwChangeProAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String new_pass = request.getParameter("d_pass");
		String d_id = request.getParameter("id");
		
		System.out.println(new_pass);
		
		LoginDao manger = LoginDao.getInstance();
		boolean new_pw = manger.newPw(d_id, new_pass);
		
		request.setAttribute("new_pw", new_pw);
		
		
		
		
		
		return "/d_login/pwChangePro.jsp";
	}
}

package mvc.doit.ResellAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.ResellBean.ResellintroDao;
import mvc.doit.SuperAction.SuperAction;

public class CallProAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HttpSession session= request.getSession();
		String d_id = (String)session.getAttribute("memId");
		String rbook_intro= request.getParameter("rbook_intro");
		ResellintroDao dao = ResellintroDao.getInstance();

		ResellintroDao manager = ResellintroDao.getInstance();
		
		boolean c = manager.callForm(d_id,rbook_intro);


	    request.setAttribute("c", c);

	    
	  
		return "/d_resell/callPro.jsp";
	}

}

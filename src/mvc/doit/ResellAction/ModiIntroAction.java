package mvc.doit.ResellAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.ResellBean.ResellintroDao;
import mvc.doit.ResellBean.ResellintroDto;
import mvc.doit.SuperAction.SuperAction;

public class ModiIntroAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request, 
		HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session= request.getSession();
		String d_id = (String)session.getAttribute("memId");

		ResellintroDao dao = ResellintroDao.getInstance();

		ResellintroDao manager = ResellintroDao.getInstance();
		
		ResellintroDto c = manager.getIntro(d_id);
		

	    request.setAttribute("c", c);
	
	    

			
		
		return "/d_resell/modiIntro.jsp";
	}

}

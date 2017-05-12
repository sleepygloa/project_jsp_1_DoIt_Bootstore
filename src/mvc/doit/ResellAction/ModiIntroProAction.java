package mvc.doit.ResellAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import mvc.doit.Login.LoginDto;
import mvc.doit.ResellBean.ResellintroDao;
import mvc.doit.ResellBean.ResellintroDto;
import mvc.doit.SuperAction.SuperAction;

public class ModiIntroProAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session= request.getSession();
		ResellintroDto article =new ResellintroDto();
		article.setD_id(request.getParameter("d_id"));

		article.setRbook_intro(request.getParameter("rbook_intro"));
		String d_id = (String)session.getAttribute("memId");
		String rbook_intro= request.getParameter("rbook_intro");
		article.setD_id(d_id);
		
		ResellintroDao manager = ResellintroDao.getInstance();
		manager.modiIntro(article);


	

	  
		return "/d_resell/modiIntroPro.jsp";
	}

}
	

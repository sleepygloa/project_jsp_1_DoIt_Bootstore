package mvc.doit.AdminAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.ResellBean.ResellintroDao;
import mvc.doit.SuperAction.SuperAction;

public class CallNoProAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	request.setCharacterEncoding("UTF-8");
		
		
		String d_id= request.getParameter("d_id");

		ResellintroDao manager = ResellintroDao.getInstance();
		
		boolean result = manager.callFormNo(d_id);

	    request.setAttribute("result", result);
	    request.setAttribute("d_id", d_id);
		
		
		return "/d_admin/callNoPro.jsp";
	}

}

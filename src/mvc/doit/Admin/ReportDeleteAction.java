package mvc.doit.AdminAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.ResellBean.ResellintroDao;
import mvc.doit.SuperAction.SuperAction;

public class ReportDeleteAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		request.setCharacterEncoding("UTF-8");
		
		
		String rbook_id= request.getParameter("rbook_id");

		ResellintroDao manager = ResellintroDao.getInstance();
		
		boolean result = manager.reportDelete(rbook_id);

	    request.setAttribute("result", result);
	    request.setAttribute("rbook_id", rbook_id);
		
		
		return "/d_admin/reportDelete.jsp";
	}
}

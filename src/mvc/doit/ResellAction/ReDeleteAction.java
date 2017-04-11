package mvc.doit.ResellAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class ReDeleteAction implements SuperAction{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
		int rbook_no = Integer.parseInt(request.getParameter("rbook_no"));
		  String pageNum = request.getParameter("pageNum");
		  
		  request.setAttribute("rbook_no", rbook_no);
		  request.setAttribute("pageNum", pageNum);
		  
		return "/d_resell/reDelete.jsp";
	}
}

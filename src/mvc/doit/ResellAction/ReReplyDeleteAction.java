package mvc.doit.ResellAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class ReReplyDeleteAction implements SuperAction{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		int rbook_no = Integer.parseInt(request.getParameter("rbook_no"));
		String pageNum = request.getParameter("pageNum");
		int rerbook_rnum = Integer.parseInt(request.getParameter("rerbook_rnum"));
		
		ResellbookDao dbPro = ResellbookDao.getInstance();
		dbPro.reReplyDeleteArticle(rerbook_rnum);
		
		request.setAttribute("rbook_no", rbook_no);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("rerbook_rnum", rerbook_rnum);
		
		return "/d_resell/reReplyDelete.jsp";
	}
}

package mvc.doit.ResellAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class ReReportAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		String id= (String)session.getAttribute("memId");
		
		int rbook_no = Integer.parseInt(request.getParameter("rbook_no"));
		String pageNum = request.getParameter("pageNum");
		String report_id2 = request.getParameter("report_id2");
		
		ResellbookDao article = ResellbookDao.getInstance();
		boolean check = article.reReport(id,rbook_no);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("rbook_no", rbook_no);
		request.setAttribute("check", check);
		request.setAttribute("report_id", id);
		request.setAttribute("report_id2", report_id2);
		return "/d_resell/reReport.jsp";
		
	}
}

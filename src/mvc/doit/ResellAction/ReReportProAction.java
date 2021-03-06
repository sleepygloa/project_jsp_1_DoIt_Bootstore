package mvc.doit.ResellAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.ResellBean.ResellReportDto;
import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class ReReportProAction implements SuperAction{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		String report_id2 =request.getParameter("report_id2");
		
		
		ResellReportDto report =  new ResellReportDto();
		report.setRbook_no(Integer.parseInt(request.getParameter("rbook_no")));
		report.setReport_id(request.getParameter("report_id"));
		report.setD_id(request.getParameter("report_id2"));
		report.setReport_content(request.getParameter("report_content"));
		
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("memId");
		//report.setReport_id(id);

		ResellbookDao article =ResellbookDao.getInstance();
		article.reReportInsert(report);
		article.reReporCount(report_id2);
		
		request.setAttribute("rbook_no", report.getRbook_no());
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("report_id2", report_id2);
		
		return "/d_resell/reReportPro.jsp";
		
	}
}

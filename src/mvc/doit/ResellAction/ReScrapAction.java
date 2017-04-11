package mvc.doit.ResellAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.ResellBean.ResellScrapDto;
import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class ReScrapAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		String pageNum = request.getParameter("pageNum");
		int rbook_no =Integer.parseInt(request.getParameter("rbook_no"));
		String scrap_id = request.getParameter("scrap_id");
	
		ResellScrapDto scrap = new ResellScrapDto();
		scrap.setD_id(id);
		scrap.setRbook_no(Integer.parseInt(request.getParameter("rbook_no")));
		
		ResellbookDao dbPro = ResellbookDao.getInstance();
		dbPro.insertScrap(scrap);
		
		
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("rbook_no", rbook_no);
		request.setAttribute("scrap", scrap);
		request.setAttribute("scrap_id", id);
		return "/d_resell/reScrap.jsp";
		
	}

}

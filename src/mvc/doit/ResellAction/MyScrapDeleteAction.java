package mvc.doit.ResellAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class MyScrapDeleteAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int scrap_no = Integer.parseInt(request.getParameter("scrap_no"));
		
		ResellbookDao dbPro = ResellbookDao.getInstance();
		dbPro.myScrapDelete(scrap_no);
		
		request.setAttribute("scrap_no", scrap_no);

		return "/d_resell/myScrapDelete.jsp";
	}
}

package mvc.doit.ResellAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.ResellBean.ResellbookDto;
import mvc.doit.SuperAction.SuperAction;

public class ReUpdateAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		request.setCharacterEncoding("UTF-8");
		
		int rbook_no = Integer.parseInt(request.getParameter("rbook_no"));
		String pageNum =request.getParameter("pageNum");
		
		ResellbookDao dbpro =ResellbookDao.getInstance();
		ResellbookDto article = dbpro.reUpdateGetArticle(rbook_no);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("rbook_no", rbook_no);
		request.setAttribute("article", article );
		return "/d_resell/reUpdate.jsp";
	}

}

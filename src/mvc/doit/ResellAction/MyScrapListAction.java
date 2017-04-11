package mvc.doit.ResellAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class MyScrapListAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		int pageSize = 10;
		
		String pageNum = request.getParameter("pageNum"); //
	    if (pageNum == null) { //Parameter가 null일때 동작
	        pageNum = "1";
	    }
		
	    int currentPage = Integer.parseInt(pageNum);
	    int startRow = (currentPage - 1) * pageSize + 1; //
	    int endRow = currentPage * pageSize; //
	    int count = 0;
	    int number=0;
	    
	    List articleList = null;
	    ResellbookDao dbPro = ResellbookDao.getInstance();
	    
	    HttpSession session =request.getSession();
	    String id =(String)session.getAttribute("memId");
	    count = dbPro.getMyScrapCount(id);
	    
	    if(count > 0){
	    	articleList = dbPro.getMyScrapList(id);
	    }
	    
	    number=count-(currentPage-1)*pageSize;
	    
		request.setAttribute("id",id);
		request.setAttribute("currentPage", currentPage);
        request.setAttribute("startRow", startRow);
        request.setAttribute("endRow", endRow);
        request.setAttribute("count", count);
        request.setAttribute("pageSize", pageSize);
		request.setAttribute("number", number);
        request.setAttribute("articleList", articleList);
	    
		return "/d_resell/myScrapList.jsp";
		
	}
}

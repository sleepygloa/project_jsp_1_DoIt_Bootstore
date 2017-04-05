package mvc.doit.RentAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Rent.RentDao;
import mvc.doit.SuperAction.SuperAction;

public class Rent_wrapAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		request.setCharacterEncoding("UTF-8");	
		String sort = request.getParameter("sort");
			
		String pageNum = request.getParameter("pageNum");//페이지 번호

        if (pageNum == null) {
            pageNum = "1";
        }
        int pageSize = 8;//한 페이지의 글의 개수
        int currentPage = Integer.parseInt(pageNum);
        int startRow = (currentPage - 1) * pageSize + 1;//한 페이지의 시작글 번호
        int endRow = currentPage * pageSize;//한 페이지의 마지막 글번호
        int count = 0;
       
        
        List articleList = null;		
		RentDao dbPro = RentDao.getInstance();
		count = dbPro.getArticleCount();//전체 글의 수
		
		if (count > 0) {
		 articleList = dbPro.getArticles(sort,startRow,endRow);//페이지 글 목록
		}
		
		request.setAttribute("pageNum", new Integer(pageNum));
        request.setAttribute("currentPage", new Integer(currentPage));
	    request.setAttribute("startRow", new Integer(startRow));
	    request.setAttribute("endRow", new Integer(endRow));
	    request.setAttribute("count", new Integer(count));
	    request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("articleList", articleList);
		
		request.setAttribute("sort", sort);
		request.setAttribute("view_type", request.getParameter("view_type"));
		
		return "/d_rent/rent_wrap.jsp";
	}

}

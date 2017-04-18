package mvc.doit.CustomerAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Customer.NoticeDao;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;

public class NoticeListAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
	    String notice_id = (String)session.getAttribute("memId");
		String pageNum = request.getParameter("pageNum");
		  if (pageNum == null) {
		        pageNum = "1";
		    }
		  
		  
		    int pageSize = 10;
		    int currentPage = Integer.parseInt(pageNum);
		    int startRow = (currentPage - 1) * pageSize + 1;
		    int endRow = currentPage * pageSize;
		    int count = 0;
		    int number=0;
		    
		    List articleList = null;

		    NoticeDao article = NoticeDao.getInstance();
		    
		    NoticeDao dao= NoticeDao.getInstance();
		    LoginDto dto=dao.getGrade(notice_id);
		    
		    String search=request.getParameter("search");
		    if(search==null){
		    
		    count = article.getNoticeCount();
		    if (count > 0) {
		        articleList = article.getNoticeList(startRow, endRow);
		    }
		    }else{
		    	count = article.getNoticeSearchCount(search);
		        if (count > 0) {
		            articleList = article.getNoticeSearch(search);
		    	}
		    }
			number=count-(currentPage-1)*pageSize;
			
			request.setAttribute("pageSize", new Integer(pageSize));
			request.setAttribute("currentPage", new Integer(currentPage));
			request.setAttribute("startRow", new Integer(startRow));
			request.setAttribute("endRow", new Integer(endRow));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("articleList", articleList);
			request.setAttribute("dto", dto);
			
		return "/d_customer/noticeList.jsp";
	}

	
}

package mvc.doit.AdminAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.ResellBean.ResellintroDao;
import mvc.doit.SuperAction.SuperAction;

public class CallOkListAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

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

		    ResellintroDao article = ResellintroDao.getInstance();
		    
		    String search=request.getParameter("search");
		    
		    if(search==null){
		    
		    		count = article.getArticleCount();
				    if (count > 0) {
				        articleList = article.getArticles(startRow, endRow);
				    }
			}else{
			    	count = article.getSearchCount(search);
			        if (count > 0) {
			            articleList = article.getSearchArticles(search);
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

			
		

		
		return "/d_admin/callOkList.jsp";
	}

}

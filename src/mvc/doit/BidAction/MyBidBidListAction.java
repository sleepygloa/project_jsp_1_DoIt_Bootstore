package mvc.doit.BidAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class MyBidBidListAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageSize = 10;
		
		String pageNum = request.getParameter("pageNum"); //
		//int bid_no = Integer.parseInt(request.getParameter("pageNum"));
	    if (pageNum == null) { //Parameter가 null일때 동작
	        pageNum = "1";
	    }
		
	    int currentPage = Integer.parseInt(pageNum);
	    int startRow = (currentPage - 1) * pageSize + 1; //
	    int endRow = currentPage * pageSize; //
	    int count = 0;
	    int number=0;
	    
	    List articleList = null;
	    BidbookDao dbPro = BidbookDao.getInstance();
	    
	    HttpSession session =request.getSession();
	    String id =(String)session.getAttribute("memId");
	   
	    
		    
	    
	  //검색
	  		String search = request.getParameter("search");
	  		if(search == null){
	  			//전체내용
	  			 count = dbPro.getMyBidBidListCount(id);
	  			if(count > 0){ //글이 있을때 BidList호출
	  				
	  				articleList = dbPro.getMyBidBidList(id, startRow, endRow);
	  			}
	  		}else{
	  			count = dbPro.BidBidMySearchCount(search,id);
	  			if(count > 0){
			    	articleList = dbPro.BidBidMySearch(search,id,startRow,endRow);
			    }
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
		return "/d_bid/myBidBidList.jsp";
	}

}

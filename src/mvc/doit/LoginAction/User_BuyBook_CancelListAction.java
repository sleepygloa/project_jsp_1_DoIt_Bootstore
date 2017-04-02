package mvc.doit.LoginAction;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class User_BuyBook_CancelListAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	
	 				request.setCharacterEncoding("utf-8");
	 					
	 				 	HttpSession session = request.getSession();
	 					String id = (String)session.getAttribute("memId");
	 					
	 				 	
	 				 // 한 화면에 보여주는 게시글
	 				    int pageSize = 10;
	 				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 날짜 형태 바꿔서 보여줌
	 				
	 				    String pageNum = request.getParameter("pageNum"); //
	 				    if (pageNum == null) { //Parameter가 null일때 동작
	 				        pageNum = "1";
	 				    }
	 			
	 				    // 검색할 범위 지정, 범위 계산 정보
	 				    int currentPage = Integer.parseInt(pageNum);
	 				    int startRow = (currentPage - 1) * pageSize + 1; //
	 				    int endRow = currentPage * pageSize; //
	 				    int count = 0;
	 				    int number=0;
	 				    
	 				    List articleList = null;
	 			      OnDao article = OnDao.getInstance();//DB연동
	 			      count = article.User_BuyBook_CancelFinish_Count(id);//전체 글의 수 
	 			      ///바꾸기
	 			      if(count > 0){
	 			          articleList = article.User_BuyBook_CancelList(startRow,endRow,id);//현재 페이지에 해당하는 글 목록
	 			      }
	 			
	 			      //게시글이 pagesize 보다 클때, 즉 1페이지보다 수량이 많을때 페이지 번호 출력
	 			      if (count > 0) {
	 			          int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
	 			  		 
	 			          int startPage = (int)(currentPage/10)*10+1;
	 			  		int pageBlock=10;
	 			          int endPage = startPage + pageBlock-1;
	 			          if (endPage > pageCount) endPage = pageCount;
	 			          
	 			          request.setAttribute("pageCount", pageCount);
	 			          request.setAttribute("startPage", startPage);
	 			          request.setAttribute("endPage", endPage);
	 			          
	 			      }        
	 			      
	 					number=count-(currentPage-1)*pageSize;//글목록에 표시할 글번호
	 					
	 					
	 					
	 			    request.setAttribute("currentPage", new Integer(currentPage));
	 			    request.setAttribute("startRow", new Integer(startRow));
	 			    request.setAttribute("endRow", new Integer(endRow));
	 			    request.setAttribute("count", count);
	 			    request.setAttribute("pageSize", new Integer(pageSize));
	 				request.setAttribute("number", new Integer(number));
	 			    request.setAttribute("articleList", articleList);
	 			    request.setAttribute("id", id);
	
	return "/d_login/user_BuyBook_CancelList.jsp";
}

}

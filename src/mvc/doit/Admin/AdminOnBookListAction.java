package mvc.doit.Admin;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class AdminOnBookListAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
//        count = article.Admin_SellCount();//전체 글의 수 
        count = article.Admin_OnBookCount();//전체 글의 수

        if(count > 0){
            articleList = article.Admin_OnBookList(startRow,endRow);//현재 페이지에 해당하는 글 목록
        }

		number=count-(currentPage-1)*pageSize;//글목록에 표시할 글번호
		
        request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("count", count);
        request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
        request.setAttribute("articleList", articleList);
		
		

		
	
	
		return "/d_admin/adminOnBookList.jsp";
	}

}

package mvc.doit.RentAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Rent.RentDao;
import mvc.doit.SuperAction.SuperAction;

public class Rent_searchAction implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		//검색조건 - 장르
		String jang = request.getParameter("searchSelect");
		
		//검색조건 - 검색입력 내용
		String word = request.getParameter("searchWord");
		
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
		count = dbPro.getSearchCount(jang,word);//전체 글의 수
		
		
		if (count > 0) {
		 articleList = dbPro.getSearch(startRow,endRow,jang,word);//페이지 글 목록
		}
		
		
		request.setAttribute("pageNum", new Integer(pageNum));
        request.setAttribute("currentPage", new Integer(currentPage));
	    request.setAttribute("startRow", new Integer(startRow));
	    request.setAttribute("endRow", new Integer(endRow));
	    request.setAttribute("count", new Integer(count));
	    request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("articleList", articleList);
		
		
		//불러올 페이지 이름 저장
		request.setAttribute("view_type", "search_re");
		
		//검색 결과 제목 저장
		request.setAttribute("count", count);
		request.setAttribute("word", word);
		request.setAttribute("jang", jang);
		
		return "/d_rent/rent_wrap.jsp";
	}
	
	
	
}

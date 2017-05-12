package mvc.doit.CustomerAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Customer.CustomerDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.ResellBean.ResellintroDto;
import mvc.doit.SuperAction.SuperAction;

public class FaqListProAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String faq_type= request.getParameter("faq_type");
		if(faq_type == null){
			faq_type = "";
		}
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		int pageSize = 10;//한화면 게시물개수
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null){
			pageNum = "1";
		}
		
		
		int currentPage = Integer.parseInt(pageNum); //페이지번호
		int startRow = (currentPage -1) * pageSize +1; 
		int endRow = currentPage * pageSize +1; //마지막페이지 범위값을 계산한정보
		int count = 0;
		int number = 0;
		int sellerCount= 0;
		
		List articleList = null;
		CustomerDao article = CustomerDao.getInstance();
	
		
		//검색
		String searchTitle = request.getParameter("searchTitle");
		String search = request.getParameter("search");
		if(search == null){
			//전체내용
			count = article.getFaqCount();
			if(count > 0){ //글이 있을때 resellList호출
				
				articleList = article.getfaqList(startRow, endRow, faq_type);
			}
		}else{
			count = article.getFaqSearchCount(searchTitle,search);
			if(count >0){
				articleList = article.getfaqListSearch(startRow,endRow,searchTitle,search);
			}
		}
		
		number = count -(currentPage-1)*pageSize;
		
		LoginDto dto=article.getGrade(id);
		
		request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("count", new Integer(count));
        request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		request.setAttribute("id", id);
		request.setAttribute("searchTitle", searchTitle);
		request.setAttribute("search", search);
		request.setAttribute("dto", dto);
		return "/d_customer/faqList.jsp";
	}

}

package mvc.doit.ResellAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.ResellBean.ResellbookDto;
import mvc.doit.ResellBean.ResellintroDao;
import mvc.doit.ResellBean.ResellintroDto;
import mvc.doit.SuperAction.SuperAction;

public class SellerContentAction implements SuperAction{


	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int d_no = Integer.parseInt(request.getParameter("d_no").trim());//해당 글번호
        String rbook_id= request.getParameter("d_id");
		String pageNum = request.getParameter("pageNum");//해당 페이지 번호
        LoginDao dao = LoginDao.getInstance();
        
		
        ResellintroDao dbPro = ResellintroDao.getInstance();//DB처리
        LoginDto article =  dbPro.sellerContent(d_no);//해당 글번호에 대한 해당 레코드,회원정보
        ResellbookDto dto =	dbPro.sellerContentBook(rbook_id);//판매자의 책정보
        
        //해당 뷰에서 사용할 속성
        request.setAttribute("rbook_introno", new Integer(d_no));
        request.setAttribute("pageNum", new Integer(pageNum));
        request.setAttribute("article", article);
        request.setAttribute("dto", dto);

        
		  if (pageNum == null) {
		        pageNum = "1";
		    }
		  
		  
		    int pageSize = 500;
		    int currentPage = Integer.parseInt(pageNum);
		    int startRow = (currentPage - 1) * pageSize + 1;
		    int endRow = currentPage * pageSize;
		    int count = 0;
		    int number=0;
		    
		    List articleList = null;

		    ResellintroDao doit = ResellintroDao.getInstance();
		    
		    //String search=request.getParameter("search");
		    //if(search==null){
		    
		    count = doit.sellerContentBookCount(d_no);
		    if (count > 0) {
		        articleList = dbPro.sellerContentBooks(d_no);
		    }
		    //}
			number=count-(currentPage-1)*pageSize;
			
			request.setAttribute("pageSize", new Integer(pageSize));
			request.setAttribute("currentPage", new Integer(currentPage));
			request.setAttribute("startRow", new Integer(startRow));
			request.setAttribute("endRow", new Integer(endRow));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("articleList", articleList);
        
		return "/d_resell/sellerContent.jsp";
	}
}

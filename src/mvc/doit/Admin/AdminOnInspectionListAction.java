package mvc.doit.Admin;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Delivery.DeliveryDao;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class AdminOnInspectionListAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception{
			
		//---- 변수 설정 ----------------------------------------------
	    int pageSize = 5;
	
	    String pageNum = request.getParameter("pageNum"); //
	    if (pageNum == null) { //Parameter가 null일때 동작
	        pageNum = "1";
	    }
	    int delivery = -1;
	    if(request.getParameter("delivery") != null){
	    	delivery = Integer.parseInt(request.getParameter("delivery"));
	    }
	    int d_bcode = -1;
	    if(request.getParameter("d_bcode") != null){
	    	d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
	    }

	    // 검색할 범위 지정, 범위 계산 정보
	    int currentPage = Integer.parseInt(pageNum);
	    int startRow = (currentPage - 1) * pageSize + 1; //
	    int endRow = currentPage * pageSize; //
	    int count = 0;
	    int number=0;
	    
	    

	    
	    //---- List ---- count:d_sfinish==1 (판매가능한 책 count), list: 3table(d_onBook, d_onSellList, d_bdelivery) 판매가능한(d_sfinish==1) 책 List
	    List articleList = null;
        OnDao article = OnDao.getInstance();//DB연동
        count = article.Admin_InspectionCount();//전체 글의 수 

        if(count > 0){
        		articleList = article.Admin_InspectionList(startRow,endRow, d_bcode);//현재 페이지에 해당하는 글 목록
        }
        
        //---- page size ----
		number=count-(currentPage-1)*pageSize;//글목록에 표시할 글번호
		
		//----
        request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("count", count);
        request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
        request.setAttribute("articleList", articleList);
		
		
		
		
		return "/d_admin/adminOnInspectionList.jsp";
	}

}

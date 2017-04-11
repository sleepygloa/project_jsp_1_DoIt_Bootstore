package mvc.doit.Admin;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Delivery.DeliveryDao;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class AdminOnSellListAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	
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
		// 한 화면에 보여주는 게시글
	    int pageSize = 20;
	    int currentPage = Integer.parseInt(pageNum);
	    int startRow = (currentPage - 1) * pageSize + 1; //
	    int endRow = currentPage * pageSize; //
	    int count = 0;
	    int number=0;
	    
	    //---- 판매신청 확인 dao -----
	    List articleList = null;
        OnDao article = OnDao.getInstance();//DB연동
        
        //---- 조건: 판매신청완료버튼을 눌렀을때, Dao
        String sell_confirm = null;
        if(request.getParameter("sell_confirm") != null){
        	article.Admin_Sell_Confirm(d_bcode);
        }
        
	    //---- 조건: 배송중 -> 배송완료 : 배송현황(d_bdelivery=12)일때,  delivery == 13 --> 배송현황(배송완료 13)으로 table update -----
	    DeliveryDao ddao = DeliveryDao.getInstance();
	    if(delivery == 13){
	    	ddao.User_SellBook_delivertEnd(d_bcode);
	    }	
	    
	    //---- list ----
        count = article.Admin_SellCount();//전체 글의 수 
        if(count > 0){
            articleList = article.Admin_SellList(startRow,endRow);//현재 페이지에 해당하는 글 목록
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


	
	
	return "/d_admin/adminOnSellList.jsp";
}

}

package mvc.doit.LoginAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Account.AcDao;
import mvc.doit.Account.AcDto;
import mvc.doit.Delivery.DeliveryDao;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class MySellListAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
	//---- 변수 설정 --------------------------------------------------
		HttpSession session = request.getSession();
		String d_id = (String)session.getAttribute("memId"); 
		String pageNum = request.getParameter("pageNum");//페이지 번호
        if (pageNum == null) {
            pageNum = "1"; //1페이지당 5권의 책 보여줌
        }
        int pageSize = 50;//한 페이지의 글의 개수 //한번에 판매중인 책의 갯수가 많지 않다고 생각하여 5개로 지정
        int currentPage = Integer.parseInt(pageNum); //페이지번호를 Int 로 저장한 값
        int startRow = (currentPage - 1) * pageSize + 1;//한 페이지의 시작글 번호, 시작 글 번호 1
        int endRow = currentPage * pageSize;//한 페이지의 마지막 글번호, 마지막 등록글번호 5
        int count = 0; //변수 초기화
        int number = 0; //변수 초기화
        
        //---- List 변수 설정 ---------
        int d_sfinish = -1;
        if(request.getParameter("d_sfinish") != null){
        	d_sfinish = Integer.parseInt(request.getParameter("d_sfinish"));
        }
        int delivery = -1;
        if(request.getParameter("delivery") != null){
        	delivery = Integer.parseInt(request.getParameter("delivery"));
        }
	 	int d_bcode = -1;
	 	if(request.getParameter("d_bcode") != null){
	 		d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
	 	}
        
	 	

	 	
        List articleList = null; //리스트 초기화
        OnDao dao = OnDao.getInstance();//DB연동
        
//---- 배송 현황 처리 Dao ----
        //---- 배송관련 
        DeliveryDao ddao = DeliveryDao.getInstance();
        //---- delivery == 2 (배송시작을 눌렀을때), 배송현황 d_bdelivery==12 (배송중)
	 	if(delivery == 2){
	 		ddao.User_SellBook_delivertStart(d_bcode);
	 	}else{}  
        
//---- List 관련 dao count--------------
        if(d_sfinish == -1){
        	count = dao.getD_bmySellingCount(d_id);//전체 글의 수 
        }
//        else if(d_sfinish == 1){
//        	count = dao.getD_bmySellingFinishCount(d_id);//전체 글의 수 
//        }
        
//---- List 관련 dao List----------------
        if(count > 0){
        	if(d_sfinish == -1){
        		articleList = dao.getD_bMySellingList(d_id, startRow, endRow);//현재 페이지에 해당하는 글 목록
        	}
//        	else if(d_sfinish == 1){
//        		 articleList = dao.getD_bMySellingFinishList(d_id, startRow, endRow);//현재 페이지에 해당하는 글 목록
//        	}
 
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
		
		
		
        //해당 뷰에서 사용할 속성
	    request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("count", new Integer(count));
        request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
        request.setAttribute("articleList", articleList);

		
	
		
		return "/d_login/mySellList.jsp";
	}
}

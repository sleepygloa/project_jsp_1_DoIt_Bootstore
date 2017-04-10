package mvc.doit.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Account.AcDao;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class AdminOnPurchasePaymentListAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//------------0. 공통부분 -------------------------------------------------------------		
	String pageNum = request.getParameter("pageNum");//페이지 번호
    if (pageNum == null) {
        pageNum = "1"; //1페이지당 20권의 책 보여줌
    }
    int pageSize = 20;//한 페이지의 글의 개수
    int currentPage = Integer.parseInt(pageNum); //페이지번호를 Int 로 저장한 값
    int startRow = (currentPage - 1) * pageSize + 1;//한 페이지의 시작글 번호, 시작 글 번호 1
    int endRow = currentPage * pageSize;//한 페이지의 마지막 글번호, 마지막 등록글번호 20
    int count = 0; //변수 초기화
    int number = 0; //변수 초기화
	
    
    //------------1. DB 연동 ------------------------------------------------------
    List paymentList = null; //리스트 초기화
    AcDao adao = AcDao.getInstance();//DB연동
    
    //------------2. dao에 필요한 d_no 찾기--------------------------------------------
    if(request.getParameter("pay_send") == null){
    }else{
    	int d_lno = 0;
    	if(request.getParameter("d_lno") != null){
    		d_lno = Integer.parseInt(request.getParameter("d_lno"));
    	}
    	
    	//결제 진행 버튼을 눌렀을때, d_log의 레코드를 update합니다.
    	adao.updateAccountLog(d_lno);
    }
    		
  //--------------2. 전체 리스트  반환 -----------------------------------------------------
    int aoppl = 0;
    if(request.getParameter("aoppl") != null){
    	aoppl = Integer.parseInt(request.getParameter("aoppl"));  
    }

    if(aoppl == 0){
        count = adao.getD_sPayListCount();//전체 글의 수 
        if(count > 0){
        	paymentList = adao.getD_sPayList(startRow, endRow);//현재 페이지에 해당하는 글 목록

    	} 
    }else if(aoppl == 1){
        count = adao.getD_sNoPayListCount();//전체 글의 수 
        if(count > 0){
        	paymentList = adao.getD_sNoPayList(startRow, endRow);//현재 페이지에 해당하는 글 목록

    	}  
    }else if(aoppl == 2){
        count = adao.getD_sPayEndListCount();//전체 글의 수 
        if(count > 0){
        	paymentList = adao.getD_sPayEndList(startRow, endRow);//현재 페이지에 해당하는 글 목록

    	}  
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
        
    	request.setAttribute("aoppl", aoppl);
        
        request.setAttribute("count", count);
        request.setAttribute("paymentList", paymentList);

        request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));     
        
        
        
	return "/d_admin/adminOnPurchasePaymentList.jsp";

	}
}

package mvc.doit.CustomerAction;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Customer.CustomerDao;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;

public class Customer_InquireListAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	request.setCharacterEncoding("utf-8");
	 	
	 	String reply = request.getParameter("reply");
	 	
	 	HttpSession session = request.getSession();
		int br_no = 0;
		
		if(session.getAttribute("memNo") != null){
			br_no = (int)session.getAttribute("memNo");
		}; //회원 번호 
		
		 int pageSize = 10;
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
        CustomerDao dbPro = CustomerDao.getInstance();//DB연동
      
        if(reply.equals("wait")){
        	int re = 0;
        	count = dbPro.adminInquireListCount(re);//전체 글의 수 
	        if (count > 0) {
	            articleList = dbPro.adminInquireList(startRow, endRow,re);//현재 페이지에 해당하는 글 목록
	        }
	       
        }else if(reply.equals("finish")){
        	int re = 1;
        	count = dbPro.adminInquireListCount(re);//전체 글의 수 
	        if (count > 0) {
	            articleList = dbPro.adminInquireList(startRow, endRow,re);//현재 페이지에 해당하는 글 목록
	        }
	        
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
        request.setAttribute("count", count);
	
     	request.setAttribute("reply", reply);
	
	return "/d_customer/customer_InquireList.jsp";
	}

}

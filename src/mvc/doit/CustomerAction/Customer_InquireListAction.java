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
	 	
	 	HttpSession session = request.getSession();
		int br_no = 0;
		if(session.getAttribute("memNo") != null){
			br_no = (int)session.getAttribute("memNo");
		}; //회원 번호 
		
        String pageNum = request.getParameter("pageNum");//페이지 번호

        if (pageNum == null) {
            pageNum = "1";
        }
        int pageSize = 10;//한 페이지의 글의 개수
        int currentPage = Integer.parseInt(pageNum);
        int startRow = (currentPage - 1) * pageSize + 1;//한 페이지의 시작글 번호
        int endRow = currentPage * pageSize;//한 페이지의 마지막 글번호
        int count = 0;
        int number=0;

        List articleList = null;
        CustomerDao dbPro = CustomerDao.getInstance();//DB연동
        count = dbPro.adminInquireListCount();//전체 글의 수 
        
        if (count > 0) {
            articleList = dbPro.adminInquireList(startRow, endRow);//현재 페이지에 해당하는 글 목록
        } else {
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
	
	
	return "/d_customer/customer_InquireList.jsp";
	}

}

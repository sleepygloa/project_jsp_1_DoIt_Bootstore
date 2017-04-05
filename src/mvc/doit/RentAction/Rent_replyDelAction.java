package mvc.doit.RentAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Rent.RentDao;
import mvc.doit.SuperAction.SuperAction;

public class Rent_replyDelAction implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//객체화
		RentDao rdo = RentDao.getInstance();
		
		/* 삭제냐 아니냐 */
		String b_id = request.getParameter("b_id"); //아이디 받음
		String br_no = request.getParameter("br_no");//상세보기 글번호
		String ba_no = request.getParameter("b_no");//댓글 번호 받음
		int b_no = Integer.parseInt(ba_no);
		
	    HttpSession session = request.getSession();
	    String memId = (String)session.getAttribute("memId"); //세션 아이디
	    int memMG = (int)session.getAttribute("memMG"); //관리자 구분
		
		//댓글 삭제
		if(memId.equals(b_id) || memMG == 10){
			rdo.deleteReview(b_no);
		}
		
		
		request.setAttribute("br_no", br_no);
		
		return "/d_rent/detail.do";
	}
}

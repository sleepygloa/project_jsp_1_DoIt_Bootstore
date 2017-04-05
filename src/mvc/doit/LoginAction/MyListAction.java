package mvc.doit.LoginAction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Cart.CartDao;
import mvc.doit.SuperAction.SuperAction;

public class MyListAction implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//현재 날짜 불러오기
		Date date = new Date();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy. MM. dd");
		String das = simpleDate.format(date);
		request.setAttribute("SimpleDate", das);
		
		//구분 값 불러오기
		String col = request.getParameter("cols");
		
		//세션 값 가져오기
		HttpSession session = request.getSession();
		int br_no = (int)session.getAttribute("memNo"); //회원 번호 
		
		//객체 생성
		CartDao cdo = CartDao.getInstance(); //장바구니 객체 생성
		
		//저장할 리스트 객체
		List getE = new ArrayList();
		
		if(col.equals("dr_rent")){
			//----------------------- 도서관 서비스 파트 ----------------------
			//책 정보 추가
			getE = cdo.getHeadCart(br_no,"dr_rent");
		}else{
			//----------------------- 직접판매 주문내역 ----------------------
			
		}
		
		request.setAttribute("getE", getE);
		
		return "/d_login/my_list.jsp";
	}
	
}

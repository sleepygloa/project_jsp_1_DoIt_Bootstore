package mvc.doit.LoginAction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Cart.CartDao;
import mvc.doit.SuperAction.SuperAction;

public class MyListDeliAction1 implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 저장
		String br_code = request.getParameter("br_code");//책코드 불러오기
		HttpSession session = request.getSession();
		int memNo = (int)session.getAttribute("memNo");
		
		//객체 생성
		List getList = null; //리스트 객체 생성
		CartDao cdao = CartDao.getInstance(); //카트 dao 불러옴
		
		
		//해당 책 코드의 상태 불러오기
		getList = cdao.getOver(br_code); //배송상태, 최초 날짜, 대기자명단
		
		//대기자 명단에서 첫번쨰 출력
		String firstMan = cdao.getFirstM(br_code);
		
		//대기자의 첫번쨰 사람 정보 출력
		
		
		//배송상태 저장
		int deli_info1 = (int)getList.get(0);
		
		String deli_info = "";
		
		switch(deli_info1){
			//배송상태가 
			case 0 :
				deli_info ="배송 승인 대기";
				break;
				
			case 1 :
				deli_info ="배송 준비";
				break;
				
			case 2 :
				deli_info ="배송 중";
				break;
				
			case 3 :
				deli_info ="배송 완료";
				break;
			
			//아무것도 없을 때
			default :
				deli_info = "알수 없는 상태";
				break;
		}
		
		//파라미터 저장
		request.setAttribute("deli_info", deli_info); //배송 대기 상태 저장
		request.setAttribute("firstMan", firstMan); //대기자 1번째 회원번호
		request.setAttribute("memNo", memNo); //로그인한 회원의 번호
		
		return "/d_login/my_list_deli1.jsp";
	}

}

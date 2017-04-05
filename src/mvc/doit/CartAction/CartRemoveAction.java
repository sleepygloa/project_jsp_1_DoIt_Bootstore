package mvc.doit.CartAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Cart.CartDao;
import mvc.doit.SuperAction.SuperAction;

public class CartRemoveAction implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//세션 내용 저장
		HttpSession session = request.getSession();
		
		//장바구니 Dao 객체 생성
		CartDao cdao = CartDao.getInstance();
		
		//파라미터 저장
		String br_code = request.getParameter("br_code"); //책 코드 번호
		String col = request.getParameter("cols"); // 삭제할 컬럼 구분
		int br_no = (int)session.getAttribute("memNo"); // 회원번호 출력
		request.setAttribute("cols", col);
		
		//해당 코드 삭제(라고 쓰고 새롭게 업데이트라고 읽는다.)
		cdao.deleteCart(br_no,br_code,col); // 회원번호, 해당 책코드, 구분 컬럼
		
		//새롭게 정보 출력
		List CartList = cdao.getHeadCart(br_no, col);
		request.setAttribute("CartList", CartList);
		
		//세션에 장바구니 새롭게 저장
		if(col.equals("d_rent")){ //도서관 내용 저장
			session.removeAttribute("CartL");
			List CartL = cdao.getHeadCart(br_no, col);
			session.setAttribute("CartL", CartL);
		}else{ //직접판매 저장
			session.removeAttribute("CartP");
			List CartP = cdao.getHeadCart(br_no, col);
			session.setAttribute("CartP", CartP);
		}
		
		
		return "/d_cart/rent_order.jsp";
	}
	
}












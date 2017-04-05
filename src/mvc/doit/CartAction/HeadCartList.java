package mvc.doit.CartAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Cart.CartDao;
import mvc.doit.SuperAction.SuperAction;

public class HeadCartList implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 저장
		HttpSession session = request.getSession();
		int br_no = (int)session.getAttribute("memNo"); 
		String cols = request.getParameter("cols"); //장바구니 구분 column 이름
//회원ID와 접근 경로cols=d_sell로
		
		CartDao cdao = CartDao.getInstance();
		List CartList = cdao.getHeadCart(br_no, cols);
		
		request.setAttribute("CartList", CartList);
		request.setAttribute("cols", cols);
		
		
		
		return "/d_cart/rent_order.jsp";
	}
	
}

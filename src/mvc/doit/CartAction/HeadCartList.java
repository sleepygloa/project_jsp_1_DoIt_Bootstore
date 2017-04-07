package mvc.doit.CartAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Cart.CartDao;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class HeadCartList implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 저장
		HttpSession session = request.getSession();
		int br_no = (int)session.getAttribute("memNo");
		String id = (String)session.getAttribute("memId");
		
		String cols = request.getParameter("cols"); //장바구니 구분 column 이름
		String buy = request.getParameter("buy");
		
		CartDao cdao = CartDao.getInstance();
		List CartList = cdao.getHeadCart(br_no, cols);
		
		
		request.setAttribute("CartList", CartList);
		request.setAttribute("cols", cols);
		request.setAttribute("buy", buy);
		
		
		if(cols.equals("d_sell")){
			if(buy.equals("buy")){
				//받아온 변수 d_bno, d_bcode 중 둘중 하나가 존재 할 때, 변수로 저장한다.
				int d_bno = 0;
				int d_bcode = 0;
				if(request.getParameter("d_bno") == null && request.getParameter("d_bcode") == null){
							
				}else if( request.getParameter("d_bcode") == null && request.getParameter("d_bno") != null){
					d_bno = Integer.parseInt(request.getParameter("d_bno"));
				}else if(request.getParameter("d_bcode") != null){
					d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
				}
			
				 	
				OnDao dao = OnDao.getInstance();
				OnBookDto dto = dao.User_onBuyBook(d_bno, d_bcode);
				
				LoginDao LogDao = LoginDao.getInstance();
				LoginDto LogDto = LogDao.getMember(id);
				
				String user_check = null;
				if(request.getParameter("user_check") != null){
					user_check = request.getParameter("user_check");
				}
				int d_total = cdao.getBookBuyTotal(br_no, cols);
				request.setAttribute("d_total", d_total);
				
				request.setAttribute("dto", dto);
				request.setAttribute("LogDto", LogDto);
				request.setAttribute("user_check", user_check);
				request.setAttribute("id", id);
				
						
			}else if(buy.equals("cart")){
				List CartP = (List) session.getAttribute("CartP");
				
				LoginDao LogDao = LoginDao.getInstance();
				LoginDto LogDto = LogDao.getMember(id);
				
				String user_check = null;
				if(request.getParameter("user_check") != null){
					user_check = request.getParameter("user_check");
				}
				
				int d_total = cdao.getBookBuyTotal(br_no, cols);
				request.setAttribute("d_total", d_total);
				
				request.setAttribute("LogDto", LogDto);
				request.setAttribute("user_check", user_check);
				request.setAttribute("id", id);
			}
		}
		
		
		
		return "/d_cart/rent_order.jsp";
	}
	
}

package mvc.doit.CartAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Account.AcDto;
import mvc.doit.Cart.CartDao;
import mvc.doit.Delivery.DeliveryDto;
import mvc.doit.Login.LoginDto;
import mvc.doit.Online.OnDao;
import mvc.doit.Rent.RentDto;
import mvc.doit.SuperAction.SuperAction;

public class CartPayAction implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//세션 사용
		HttpSession session = request.getSession();
		int br_no = (int)session.getAttribute("memNo"); //로그인 회원번호 불러오기
		List br_code = (List)session.getAttribute("CartL");
		
		String buy = request.getParameter("buy");
		
	
		///////////////////////////////////////////////////////////////////////////////////
		
		//구분 파라미터
		String col = request.getParameter("cols");
		
		//dao 객체화
		CartDao cdo = CartDao.getInstance();

		
		if(col.equals("d_rent")){	
			
			//대여 목록이 5개 이하일때만
			if(cdo.getRentC(br_no) - 1 < 5){	
				
				//장바구니 내용 대여 컬럼으로 이동
				cdo.moveCart(br_no);
				
				//대여한 사람의 장바구니 변경
				//cdo.delCart(br_no, col);
				
				//대기자 순위 추가
				String [] getList = cdo.getBcode(br_no); //회원번호 입력 ->가지고 있는 책 코드 출력
				for(int i = 1; i < getList.length; i++){
					cdo.insPerson(br_no, getList[i]);
				}
				
				
			}
			
			session.removeAttribute("CartL");
			List CartL = cdo.getHeadCart(br_no, col);
			session.setAttribute("CartL", CartL);

		}else if(col.equals("d_sell") && buy.equals("cart") || buy.equals("buy") ){ //직접판매 장바구니에서 구매
			

		 	String d_id = request.getParameter("d_bbuyer");
		 	
		 	DeliveryDto Ddto = new DeliveryDto();
		 	Ddto.setD_bdelibery(20);
		 	Ddto.setD_bbuyer(d_id);
		 	Ddto.setD_brecipient(request.getParameter("d_brecipient"));
		 	Ddto.setD_brequested(request.getParameter("d_brequested"));
		 	Ddto.setD_bgradevalue(Integer.parseInt(request.getParameter("d_bgradevalue")));
		 	
		 	LoginDto LogDto = new LoginDto();
		 	LogDto.setD_addr(request.getParameter("d_addr"));
		 	LogDto.setUser_phone1(request.getParameter("user_phone1"));
		 	LogDto.setUser_phone2(request.getParameter("user_phone2"));
		 	LogDto.setUser_phone3(request.getParameter("user_phone3"));
		 	
		 	AcDto acDto = new AcDto();
		 	acDto.setD_lsender(br_no); 			//보내는 사람
		 	acDto.setD_lreceiver(261);		//받는사람
		 	acDto.setD_ldealmoney(Integer.parseInt(request.getParameter("d_total")));	//거래금액
		 	acDto.setD_ldealtype(0);		//거래 종류
		 	acDto.setD_ldealresult(1);				//거래 결과 0:거래생성, 1:거래완료, 2:거래취소
		 	
		 	
		 	if(buy.equals("cart")){
		 		cdo.moveCart_delivery(br_no, Ddto, LogDto,acDto, d_id);
		 		
		 	}else if(buy.equals("buy")){
		 		
		 		int d_bcode = Integer.parseInt(request.getParameter("d_bcode"));			 	
		 		Ddto.setD_bcode(d_bcode);
		 		
		 		OnDao dao = OnDao.getInstance();
			 	dao.User_onBuyBook_insert(Ddto, LogDto,acDto, d_bcode, d_id);
			 	
		 	}
			session.removeAttribute("CartP");
			List CartP = cdo.getHeadCart(br_no, col);
		    session.setAttribute("CartP", CartP);			
			
		}

		
		
		
		return "/d_login/myInfo.do";
	}

	
	
}

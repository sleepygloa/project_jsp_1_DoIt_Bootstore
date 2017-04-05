package mvc.doit.CartAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Cart.CartDao;
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

		}else{ //직접판매 저장
			session.removeAttribute("CartP");
			List CartP = cdo.getHeadCart(br_no, col);
		    session.setAttribute("CartP", CartP);
		}

		
		
		
		return "/d_login/myInfo.do";
	}

	
	
}

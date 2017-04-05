package mvc.doit.CartAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Cart.CartDao;
import mvc.doit.Cart.CartListDto;
import mvc.doit.SuperAction.SuperAction;

//장바구니 액션
public class CartInsertAction1 implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글인코딩
		request.setCharacterEncoding("UTF-8");
		
		//세션 정보 출력
		HttpSession session = request.getSession();
		int br_no = (int)session.getAttribute("memNo"); //로그인 회원번호 불러오기
		
		
		//장바구니가 온 위치 파악 [ 도서관 / 직접판매 ] - start_cart
		String start_cart = request.getParameter("start_cart");//들어온 위치
		String b_code = request.getParameter("b_code"); //생성된 br_code
		
		String col = null;	//도서관 장바구니 컬럼명
		String srt = ""; //리턴할 url
		String colss = ""; //대여 목록 확인
		
		if(start_cart.equals("lib")){ //도서관일 경우 리스트로 리턴
			srt = "/d_rent/list_cont.do";
			request.setAttribute("view_type", request.getParameter("view_type"));
			col = "d_rent"; 
			colss = "dr_rent";
		}else if(start_cart.equals("lib2")){ //도서 대출 
			srt = "/d_cart/headCartList.do";
			col = "d_rent"; 
			colss = "dr_rent";
		}
		
		CartDao cdo = CartDao.getInstance();
		
		//해당 도서 대기자 명단 : 5명 이하인가
		int personC = cdo.getPerson(b_code);
		request.setAttribute("personC", personC); //대기자 숫자 
		
		
		if(personC < 6){
			
			//장바구니 생성 여부 판단
			boolean check = cdo.checkASD(br_no);
			
			if(!check){ //장바구니 레코드가 없는 경우
				cdo.insASD(br_no);
			}//신규 생성 및 삽입
				
			//각 장바구니 속 갯수 확인 
			int countC = cdo.countCart(br_no,col);
				
			//해당도서가 이미 대여 됬는가 ? 대여목록 확인
			boolean checkRe = cdo.checkCart(br_no,colss,b_code);
				
			//해당도서가 이미 장바구니에 있는가 ? 장바구니 목록 확인
			boolean checkRe2 = cdo.checkCart(br_no, col, b_code);
			
			// 장바구니 속 내용이 5개 이하인가?
			// 대기자 목록이 5명 이하인가?
			// 나의 대여목록에 이미 있는가?
			if(countC < 6 && !checkRe && !checkRe2 && personC < 6){
				cdo.insetCart(br_no,col,b_code);	
			}// -> 장바구니 입력
			
		
		}
		
		
		//세션에 장바구니 새롭게 저장
		if(col.equals("d_rent")){ //도서관 내용 저장
			session.removeAttribute("CartL");
			List CartL = cdo.getHeadCart(br_no, col);
			session.setAttribute("CartL", CartL);
		}else{ //직접판매 저장
			session.removeAttribute("CartP");
			List CartP = cdo.getHeadCart(br_no, col);
		    session.setAttribute("CartP", CartP);
		}

	    
		
		
		return  srt;
	}
	
}

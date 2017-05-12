package mvc.doit.RentAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Rent.RentDao;
import mvc.doit.SuperAction.SuperAction;

public class Rent_insertAction implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//글쓰기 구분
		HttpSession session = request.getSession();
		int gugu = (int)session.getAttribute("memMG");
		
		//테이블 이름 지정
		String tab_name = "b_rent";
		
		//자동 코드 생성
		// -> 코드 나온 내용에 "A,B,C" 를 선택하여 입력
		RentDao rdo = RentDao.getInstance();
		String code = rdo.code_generic(tab_name);
		
		request.setAttribute("br_code", "B"+code);
		request.setAttribute("gugu", gugu);
		
		
		
		return "/d_rent/b_write_gu.jsp";
	}
	
}

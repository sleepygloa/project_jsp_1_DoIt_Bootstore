package mvc.doit.CompanyAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Company.IntroDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;

public class Intro3Action implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		//------------0. 공통부분 -------------------------------------------------------------		
		String pageNum = request.getParameter("pageNum");//페이지 번호
        if (pageNum == null) {
            pageNum = "1"; //1페이지당 20권의 책 보여줌
        }
        int pageSize = 20;//한 페이지의 글의 개수
        int currentPage = Integer.parseInt(pageNum); //페이지번호를 Int 로 저장한 값
        int startRow = (currentPage - 1) * pageSize + 1;//한 페이지의 시작글 번호, 시작 글 번호 1
        int endRow = currentPage * pageSize;//한 페이지의 마지막 글번호, 마지막 등록글번호 20
        int count = 0; //변수 초기화
        int number = 0; //변수 초기화
		
		IntroDao idao = IntroDao.getInstance();
		LoginDto ldto = new LoginDto();
		
		//--------------- 관리자 List -------------------
		List adminList = null;
		
		count = idao.getAdminCount(); //관리자 들의 수
		if(count > 0){
			adminList = idao.getAdminList();
		}
		

		
		
		number=count-(currentPage-1)*pageSize;//글목록에 표시할 글번호
		
        //해당 뷰에서 사용할 속성
	    request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("count", new Integer(count));
        request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
        request.setAttribute("adminList", adminList);
		
		return "/d_company/intro3.jsp";
	}
}

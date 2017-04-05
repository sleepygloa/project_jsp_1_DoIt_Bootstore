package mvc.doit.ManagerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.SuperAction.SuperAction;

public class ManagerDash implements SuperAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//불러올 페이지 파라미터
		String mana_page = request.getParameter("mana_page");
		
		
		
		
		//파라미터 저장 
		request.setAttribute("mana_page", mana_page);
		
		return "/d_manage/manage_dash.jsp";
	}
	
}

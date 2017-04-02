package mvc.doit.OnlineAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class User_OnBuyBookAction  implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	request.setCharacterEncoding("utf-8");
	 	
	 	HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
	 	
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
		
		
		request.setAttribute("dto", dto);
		request.setAttribute("LogDto", LogDto);
		request.setAttribute("id", id);
		request.setAttribute("user_check", user_check);
	
	
	return "/d_online/user_onBuyBook.jsp";
}

}

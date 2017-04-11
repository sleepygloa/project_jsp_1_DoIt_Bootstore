package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;


public class MyPageInfoAction implements SuperAction{
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		String d_id = (String)session.getAttribute("memId"); //세션 저장
		
		LoginDao manager = LoginDao.getInstance();
		LoginDto lto = manager.getMember(d_id); //Dto 저장
	    
	    //어트리뷰트 저장
	    request.setAttribute("lto", lto);
		
		return "/d_login/my_info.jsp";
	}
	
}

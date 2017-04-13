package mvc.doit.CustomerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Customer.CustomerDao;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;

public class Coustomer_InquireAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	request.setCharacterEncoding("utf-8");
	 	
	 	HttpSession session = request.getSession();
		int br_no = (int)session.getAttribute("memNo"); //회원 번호 
		
	 	String inquire = request.getParameter("inquire");
	 	
	 	if(inquire.equals("Inquire")){
	 		
			
			LoginDao LDao = LoginDao.getInstance();
			String m_id = LDao.getMemNo(br_no);
			
			LoginDto LDto = LDao.getMember(m_id);
			
			request.setAttribute("LDto", LDto);
	 		
	 		
	 		
	 	}else if(inquire.equals("inquire_reply")){
	 		
	 		
	 	}
	 	
	 	request.setAttribute("inquire", inquire);

	
	
	return "/d_coustomer/coustomer_Inquire.jsp";
	}

}

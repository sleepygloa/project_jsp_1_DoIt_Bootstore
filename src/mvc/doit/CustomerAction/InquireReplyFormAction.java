package mvc.doit.CustomerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Customer.CustomerDao;
import mvc.doit.Customer.InquireDto;
import mvc.doit.SuperAction.SuperAction;

public class InquireReplyFormAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		String mem = request.getParameter("mem");
		
		int c_ino = Integer.parseInt(request.getParameter("c_ino"));
	    int ref=Integer.parseInt(request.getParameter("ref"));
	    int re_step=Integer.parseInt(request.getParameter("re_step"));
	    int re_level=Integer.parseInt(request.getParameter("re_level"));
	    
		System.out.println(c_ino);

		CustomerDao cdao = CustomerDao.getInstance();
		InquireDto Cdto = cdao.Replysearch(c_ino);


	    request.setAttribute("c_ino", c_ino);
	    request.setAttribute("ref", ref);
	    request.setAttribute("re_step", re_step);
	    request.setAttribute("re_level", re_level);   


        request.setAttribute("id", id);   
        request.setAttribute("mem", mem);   
        request.setAttribute("cdto", Cdto);   

	
	 	return "/d_customer/InquireReplyForm.jsp";
	}	

}

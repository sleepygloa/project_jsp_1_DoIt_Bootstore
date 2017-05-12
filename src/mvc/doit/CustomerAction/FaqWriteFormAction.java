package mvc.doit.CustomerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.SuperAction.SuperAction;

public class FaqWriteFormAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

	 	HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		int faq_no = 0;
		
		request.setAttribute("id", id);
		request.setAttribute("faq_no", new Integer(faq_no));
		return "/d_customer/faqWriteForm.jsp";
	}

}

package mvc.doit.CustomerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.SuperAction.SuperAction;

public class FaqDeleteAction implements SuperAction{
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
		int faq_no = Integer.parseInt(request.getParameter("faq_no"));
		  String pageNum = request.getParameter("pageNum");
		  
		  request.setAttribute("faq_no", faq_no);
		  request.setAttribute("pageNum", pageNum);
		  
		return "/d_customer/faqDelete.jsp";
	}
}
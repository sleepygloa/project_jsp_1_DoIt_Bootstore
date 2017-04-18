package mvc.doit.CustomerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Customer.CustomerDao;
import mvc.doit.Customer.FaqDto;
import mvc.doit.SuperAction.SuperAction;

public class FaqModiAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		request.setCharacterEncoding("UTF-8");
		
		int faq_no = Integer.parseInt(request.getParameter("faq_no"));
		String pageNum =request.getParameter("pageNum");
		
		CustomerDao dbpro =CustomerDao.getInstance();
		FaqDto article = dbpro.faqUpdateGetArticle(faq_no);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("faq_no", faq_no);
		request.setAttribute("article", article );
		return "/d_customer/faqModi.jsp";
	}

}
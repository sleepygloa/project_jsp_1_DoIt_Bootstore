package mvc.doit.CustomerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Customer.CustomerDao;
import mvc.doit.Customer.FaqDto;
import mvc.doit.SuperAction.SuperAction;

public class FaqModiProAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		
		FaqDto article = new FaqDto();
		article.setFaq_no(Integer.parseInt(request.getParameter("faq_no")));
		article.setFaq_subject(request.getParameter("faq_subject"));
		article.setFaq_content(request.getParameter("faq_content"));
		article.setFaq_writer(request.getParameter("faq_writer"));
		
		CustomerDao dbPro = CustomerDao.getInstance();
		dbPro.faqUpdateArticle(article);
		return "/d_customer/faqModiPro.jsp";
	}

}
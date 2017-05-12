package mvc.doit.CustomerAction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import mvc.doit.Customer.CustomerDao;
import mvc.doit.Customer.FaqDto;
import mvc.doit.SuperAction.SuperAction;

public class FaqWriteFormProAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		
		FaqDto faq = new FaqDto();
		faq.setFaq_no(Integer.parseInt(request.getParameter("faq_no")));
		faq.setFaq_subject(request.getParameter("faq_subject"));
		faq.setFaq_content(request.getParameter("faq_content"));
		faq.setFaq_writer(request.getParameter("faq_writer"));
		faq.setFaq_reg_date(new Timestamp(System.currentTimeMillis()));

		faq.setFaq_type(Integer.parseInt(request.getParameter("faq_type")));
		
		
		
		CustomerDao DbPro = CustomerDao.getInstance();
		DbPro.insertFaq(faq);
		return "/d_customer/faqWriteFormPro.jsp";
	}

}

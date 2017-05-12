package mvc.doit.CustomerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Customer.NoticeDao;
import mvc.doit.Customer.NoticeDto;
import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.ResellBean.BidbookDto;
import mvc.doit.SuperAction.SuperAction;

public class NoticeModifyAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		HttpSession session= request.getSession();
		String notice_id = (String)session.getAttribute("memId");
		int notice_no=Integer.parseInt(request.getParameter("notice_no"));
		
		NoticeDao dao = NoticeDao.getInstance();

		NoticeDao manager = NoticeDao.getInstance();
		
		NoticeDto c = manager.NoticeModifyArticle(notice_no);
		

	    request.setAttribute("c", c);
	    request.setAttribute("notice_id", notice_id);
	    request.setAttribute("notice_no", notice_no);
	    
		return "/d_customer/noticeModify.jsp";
	}

}

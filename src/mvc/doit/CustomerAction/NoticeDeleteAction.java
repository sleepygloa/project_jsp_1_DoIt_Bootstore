package mvc.doit.CustomerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Customer.NoticeDao;
import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.SuperAction.SuperAction;

public class NoticeDeleteAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		String pageNum = request.getParameter("pageNum");

		NoticeDao dbPro = NoticeDao.getInstance();
		dbPro.noticeDeleteArticle(notice_no);
		
		request.setAttribute("notice_no", notice_no);
		request.setAttribute("pageNum", pageNum);
		
		return "/d_customer/noticeDelete.jsp";
	}

}

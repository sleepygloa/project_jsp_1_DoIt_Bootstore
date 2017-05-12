package mvc.doit.CustomerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.SuperAction.SuperAction;

public class NoticeWriteFormAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String notice_id=(String)session.getAttribute("memId");
		request.setAttribute("notice_id", notice_id);
		
		
		return "/d_customer/noticeWriteForm.jsp";
	}

}

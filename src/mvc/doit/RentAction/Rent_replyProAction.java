package mvc.doit.RentAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Rent.RentDao;
import mvc.doit.SuperAction.SuperAction;

public class Rent_replyProAction implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String br_no = request.getParameter("br_no");
		String re_writer = request.getParameter("re_writer");
		String re_pic = request.getParameter("re_pic");
		String re_cont = request.getParameter("re_cont");
		String re_title = request.getParameter("re_title");
		
		RentDao rdo = RentDao.getInstance();
		rdo.insertReply(br_no, re_writer, re_pic, re_cont, re_title);
		
		//int re_no = Integer.parseInt(br_no);
		request.setAttribute("br_no", br_no);
		
		
		return "detail.do";
	}
	
}

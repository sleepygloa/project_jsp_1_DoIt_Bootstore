package mvc.doit.ResellAction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.ResellBean.ResellReplyDto;
import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class ReReplyProAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum");
		int rbook_no= Integer.parseInt(request.getParameter("rbook_no"));
		
		
		ResellReplyDto rer= new ResellReplyDto();
		rer.setRerbook_bnum(Integer.parseInt(request.getParameter("rerbook_bnum")));
		rer.setRerbook_writer(request.getParameter("rerbook_writer"));
		rer.setRerbook_content(request.getParameter("rerbook_content"));
		rer.setRerbook_reg_date(new Timestamp(System.currentTimeMillis()));
		//rer.setRerbook_ref(request.getParameter("rerbook_ref"));
		//rer.setRerbook_re_step(request.getParameter("rerbook_re_step"));
		//rer.setRerbook_re_level(request.getParameter("rerbook_re_level"));
		
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("memId");
		rer.setRerbook_writer(id);//현재 로그인 된 아이디가 들어감
		
		ResellbookDao dao = ResellbookDao.getInstance();
		dao.insertReReply(rer);
		
		
		
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("rbook_no", rbook_no);
		
		return "/d_resell/reReplyPro.jsp";
		
	}
}

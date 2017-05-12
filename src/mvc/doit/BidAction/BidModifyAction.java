package mvc.doit.BidAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.ResellBean.BidbookDto;
import mvc.doit.SuperAction.SuperAction;

public class BidModifyAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session= request.getSession();
		String bid_id = (String)session.getAttribute("memId");
		int bid_no=Integer.parseInt(request.getParameter("bid_no"));
		String bid_pic=request.getParameter("bid_pic");
		BidbookDao dao = BidbookDao.getInstance();

		BidbookDao manager = BidbookDao.getInstance();
		
		BidbookDto c = manager.BidModifyArticle(bid_no);
		

	    request.setAttribute("c", c);
	    request.setAttribute("bid_id", bid_id);
	    request.setAttribute("bid_no", bid_no);
	    request.setAttribute("bid_pic", bid_pic);
		return "/d_bid/bidModify.jsp";
	}

}

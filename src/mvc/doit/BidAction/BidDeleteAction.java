package mvc.doit.BidAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.SuperAction.SuperAction;

public class BidDeleteAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int bid_no = Integer.parseInt(request.getParameter("bid_no"));
		String pageNum = request.getParameter("pageNum");

		BidbookDao dbPro = BidbookDao.getInstance();
		dbPro.bidDeleteArticle(bid_no);
		
		request.setAttribute("bid_no", bid_no);
		request.setAttribute("pageNum", pageNum);
		
		return "/d_bid/bidDelete.jsp";
	}

}

package mvc.doit.BidAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.ResellBean.BidbookDto;

import mvc.doit.SuperAction.SuperAction;

public class BidProAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			HttpSession session = request.getSession();
			String bid_id= (String)session.getAttribute("memId");
			
			int bid_no = Integer.parseInt(request.getParameter("bid_no"));//해당책이름
			String pageNum = request.getParameter("pageNum");//해당페이지번호
			
			
			BidbookDao dbPro = BidbookDao.getInstance();//db처리
			boolean check =dbPro.getBidClick(bid_id, bid_no);
			
			
			request.setAttribute("bid_id", bid_id);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("bid_no", bid_no);
			request.setAttribute("check", check);
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			
		}
		
		
		return "/d_bid/bidPro.jsp";
	}

}

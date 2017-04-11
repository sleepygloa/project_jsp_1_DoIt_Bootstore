package mvc.doit.BidAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.SuperAction.SuperAction;

public class BidWriteFormAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("memId");
		request.setAttribute("id", id);
		
		int bid_no = 0;
		try{
			
			//rbook_no=Integer.parseInt(request.getParameter("rbook_no"));
			
		}catch (Exception e){
			//e.printStackTrace();
		}
		
		request.setAttribute("bid_no", new Integer(bid_no));
		
		
		return "/d_bid/bidWriteForm.jsp";
	}

}

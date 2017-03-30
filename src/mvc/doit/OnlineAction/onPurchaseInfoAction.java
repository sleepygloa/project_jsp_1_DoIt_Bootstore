package mvc.doit.OnlineAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.SuperAction.SuperAction;

public class onPurchaseInfoAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		request.setCharacterEncoding("utf-8");

	
	
	return "/d_online/onPurchaseInfo.jsp";
	
	} 
}

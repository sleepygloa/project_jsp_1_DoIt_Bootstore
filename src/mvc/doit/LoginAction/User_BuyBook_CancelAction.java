package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Delivery.DeliveryDao;
import mvc.doit.SuperAction.SuperAction;

public class User_BuyBook_CancelAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	request.setCharacterEncoding("utf-8");
 	
	 	int d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
	 	
	 	DeliveryDao dao = DeliveryDao.getInstance();
	 	dao.Admin_OnBuyBook_Cancel(d_bcode);
	 	

	
	
	return "/d_login/user_BuyBook_Cancel.jsp";
}

}

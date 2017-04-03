package mvc.doit.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Delivery.DeliveryDto;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class AdminOnBuyBookList_DetailAction implements SuperAction {
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 	request.setCharacterEncoding("utf-8");
		 
		 		 		 	HttpSession session = request.getSession();
		 		 			String id = (String)session.getAttribute("memId");
		 		 		 	
		 		 		 	int d_bno = Integer.parseInt(request.getParameter("d_bno"));
		 		 		 	int d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
		 		 		 	
		 		 		 	OnDao dao = OnDao.getInstance();
		 		 			OnBookDto dto = dao.User_onBuyBook(d_bno,d_bcode);
		 		 			DeliveryDto DelDto = dao.User_onBuyBookList_detail(d_bcode);
		 		 			
		 		 			LoginDao LogDao = LoginDao.getInstance();
		 		 			LoginDto LogDto = LogDao.getMember(id);
		 		 			
		 		 			request.setAttribute("dto", dto);
		 		 			request.setAttribute("LogDto", LogDto);
		 		 			request.setAttribute("DelDto", DelDto);
		 		 			request.setAttribute("id", id);

		
		
		 	return "/d_admin/adminOnBuyBookList_detail.jsp";
		}

}

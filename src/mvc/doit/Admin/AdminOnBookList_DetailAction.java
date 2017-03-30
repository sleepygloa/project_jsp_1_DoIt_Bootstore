package mvc.doit.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class AdminOnBookList_DetailAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
		

		OnDao dao = OnDao.getInstance();
		
		
		OnBookDto dto = dao.Admin_OnBook_Detail(d_bcode);
		
		request.setAttribute("d_bcode", d_bcode);
		request.setAttribute("dto", dto);

		
		


		
	
	
		return "/d_admin/adminOnBookList_detail.jsp";
	}


}

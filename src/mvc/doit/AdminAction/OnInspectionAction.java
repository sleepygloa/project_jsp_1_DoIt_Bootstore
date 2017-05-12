package mvc.doit.AdminAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class OnInspectionAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
		
		OnDao dao = OnDao.getInstance();
		
			
		OnBookDto dto = dao.Admin_Inspection(d_bcode);
		
		request.setAttribute("d_bcode", d_bcode);
		request.setAttribute("d_bname", dto.getD_bname());
		request.setAttribute("d_bvalue", dto.getD_bvalue());
		
		

		
	
	
		return "/d_admin/onInspection.jsp";
	}
}

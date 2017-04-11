package mvc.doit.AdminAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.SuperAction.SuperAction;

public class AdminAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	request.setCharacterEncoding("utf-8");

	
	
	return "/d_admin/admin.jsp";
}

}

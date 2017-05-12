package mvc.doit.AdminAction;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class AdminOnBook_DeleteAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
		

		String fileName = request.getParameter("fileName");
		String path = request.getRealPath("save");
		File f = new File(path+"//"+fileName);
		f.delete();
		
		OnDao dao = OnDao.getInstance();
		dao.Admin_OnBook_Delete(d_bcode);

		
		return "/d_admin/adminOnBook_delete.jsp";
	}

}

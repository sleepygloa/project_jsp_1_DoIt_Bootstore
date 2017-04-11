package mvc.doit.ResellAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.SuperAction.SuperAction;

public class ReWriteFormAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse respone)throws Exception{
		
		
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("memId");
		request.setAttribute("id", id);
		
		int rbook_no = 0;
		try{
			
			//rbook_no=Integer.parseInt(request.getParameter("rbook_no"));
			
		}catch (Exception e){
			//e.printStackTrace();
		}
		
		request.setAttribute("rbook_no", new Integer(rbook_no));
		
		
		return "/d_resell/reWriteForm.jsp";
	}

}

package mvc.doit.CompanyAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Company.CompanyDao;
import mvc.doit.Company.CompanyDto;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;

public class DirectionsAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		CompanyDao article = CompanyDao.getInstance();
		
		LoginDto dto=article.getGrade(id);
		CompanyDto com =article.getCom();
		request.setAttribute("id", id);
		request.setAttribute("dto", dto);
		request.setAttribute("com", com);
		return "/d_company/directions.jsp";
	}

}

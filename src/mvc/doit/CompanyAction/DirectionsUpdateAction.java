package mvc.doit.CompanyAction;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.doit.Company.CompanyDao;
import mvc.doit.Company.CompanyDto;
import mvc.doit.SuperAction.SuperAction;

public class DirectionsUpdateAction implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		
		CompanyDto com = new CompanyDto();
		//com.setCom_no(Integer.parseInt(request.getParameter("com_no")));
		com.setCom_writer(request.getParameter("com_writer"));
		com.setCom_addr(request.getParameter("com_addr"));
		com.setCom_reg_date(new Timestamp(System.currentTimeMillis()));
		
		CompanyDao dbPro = CompanyDao.getInstance();
		dbPro.insertCompany(com);
		
		return "/d_company/directionsUpdate.jsp";
	}
	
}

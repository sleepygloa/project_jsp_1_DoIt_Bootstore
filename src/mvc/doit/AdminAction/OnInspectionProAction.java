package mvc.doit.AdminAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Online.OnDao;
import mvc.doit.Online.OnInspectionDto;
import mvc.doit.SuperAction.SuperAction;

public class OnInspectionProAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	
		request.setCharacterEncoding("utf-8"); 
	 	
		//---- 변수 설정 ----
	 	int d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
	 	
	 	OnInspectionDto dto = new OnInspectionDto();
	 	dto.setD_bcode(d_bcode);
	 	dto.setD_iold(Integer.parseInt(request.getParameter("d_iold")));
	 	dto.setD_icover(Integer.parseInt(request.getParameter("d_icover")));
	 	dto.setD_ispine(Integer.parseInt(request.getParameter("d_ispine")));
	 	dto.setD_ibind(Integer.parseInt(request.getParameter("d_ibind")));
	 	
	 	//---- 검수 table 에 검수결과(dto)입력 ----
	 	OnDao dao = OnDao.getInstance();
	 	dao.Admin_Inspection_insert(dto);
	 	
	 	request.setAttribute("d_bcode", d_bcode);

	 	
	
	return "/d_admin/onInspectionPro.jsp";
	}

}

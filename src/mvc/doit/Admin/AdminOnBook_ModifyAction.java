package mvc.doit.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class AdminOnBook_ModifyAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
		
		OnDao dao = OnDao.getInstance();
		OnBookDto dto = dao.Admin_OnBook_Detail(d_bcode);
		
		int d_bgenre = 0;
		if(dto.getD_bgenre().equals("어린이 서적")){
			d_bgenre = 1;
		}else if(dto.getD_bgenre().equals("참고 / 전문서적")){
			d_bgenre = 2;
		}else if(dto.getD_bgenre().equals("소설 / 시 / 에세이")){
			d_bgenre = 3;
		}else if(dto.getD_bgenre().equals("인문학 서적")){
			d_bgenre = 4;
		}else if(dto.getD_bgenre().equals("과학 전문서적")){
			d_bgenre = 5;
		}else if(dto.getD_bgenre().equals("기타 서적")){
			d_bgenre = 6;
		}
		

	 	String d_norder = dto.getD_norder();
	 	d_norder = d_norder.replace("<br/>", "\r\n");
	 	
	 	String d_nintro = dto.getD_nintro();
	 	d_nintro = d_nintro.replace("<br/>", "\r\n");
	 	
	 	request.setAttribute("d_norder", d_norder);
	 	request.setAttribute("d_nintro", d_nintro);
	
		
		
		request.setAttribute("d_bcode", d_bcode);
		request.setAttribute("dto", dto);
		request.setAttribute("d_bgenre", new Integer(d_bgenre));
		

		
	
	
		return "/d_admin/adminOnBook_modify.jsp";
	}

}

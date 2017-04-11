package mvc.doit.ManagerAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Manager.ManDao;
import mvc.doit.Manager.ManDto;
import mvc.doit.Rent.RentDao;
import mvc.doit.ResellBean.ResellintroDao;
import mvc.doit.ResellBean.ResellintroDto;
import mvc.doit.SuperAction.SuperAction;

public class ManPart4 implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//전체 수익
		ManDao mdao = ManDao.getInstance();
		ManDto mdto = new ManDto();
		mdto = mdao.getDashM();
		request.setAttribute("dashM", mdto);
		
		
			
			
		
		return "/d_manage/man_part4.jsp";
	}

}

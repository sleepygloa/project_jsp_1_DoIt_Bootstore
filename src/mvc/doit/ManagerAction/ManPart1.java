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

public class ManPart1 implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//전체 수익
		ManDao mdao = ManDao.getInstance();
		ManDto mdto = new ManDto();
		mdto = mdao.getDashM();
		request.setAttribute("dashM", mdto);
		
		
		
		//불러올 상태 목록구분
		String guga1 = request.getParameter("guga");
		int guga = 10;
		if(guga1 != null){
			guga = Integer.parseInt(guga1);
		}
		
		//상태변경
		String mmod= request.getParameter("mmod");
		if(mmod != null){ //상태변경할건가?
			if(mmod.equals("yes")){//개별 변경인가?
				String br_code = request.getParameter("br_code");
				mdao.modiDeliv(guga, br_code);
			}else{
				mdao.modiDeliv(guga, "");
			}	
		}
		
		//목록 저장
		List DeliCont = mdao.getDeliCont(guga);
		request.setAttribute("DeliCont", DeliCont);
		request.setAttribute("guw", guga); //현재 상태 저장-일괄처리용
		
		
		
			
		
		return "/d_manage/man_part1.jsp";
	}

}

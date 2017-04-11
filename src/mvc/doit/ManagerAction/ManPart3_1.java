package mvc.doit.ManagerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Manager.ManDao;
import mvc.doit.Manager.ManDto;
import mvc.doit.SuperAction.SuperAction;

public class ManPart3_1 implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩 
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 저장
		String br_code = "";
		
		int gugu = Integer.parseInt(request.getParameter("gugu")); //전체(1), 한개 구분값(0) 
		if(gugu != 1 || gugu == 0){ //한개일때
			br_code = request.getParameter("br_code"); //고유코드
		}
		
		//manager 객체화
		ManDao mdao = ManDao.getInstance();
		
		//전체 수익
		ManDto mdto = new ManDto();
		mdto = mdao.getDashM();
		request.setAttribute("dashM", mdto);
		
		//도서 연체처리
		mdao.modiLib(br_code, gugu);
		
		//해당 회원 연체자 처리
		mdao.modiPers();
		
		return "/d_manage/manPart3.do";
	}

}












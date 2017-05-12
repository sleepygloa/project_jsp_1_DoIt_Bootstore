package mvc.doit.ManagerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Manager.ManDao;
import mvc.doit.Manager.ManDto;
import mvc.doit.Online.OnDao;
import mvc.doit.Rent.RentDao;
import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class ManagerDash implements SuperAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//세션 저장
		HttpSession session = request.getSession();
		
		//전체 수익
		ManDao mdao = ManDao.getInstance();
		ManDto mdto = new ManDto();
		mdto = mdao.getDashM();
		request.setAttribute("dashM", mdto);
		//session.setAttribute("dashM", mdto);
		
		//직접판매물
		OnDao odao = OnDao.getInstance();
		int onlinePa = odao.getD_BSellCount();
		request.setAttribute("onlinePa", onlinePa);
		
		//도서관 내용
		RentDao rdao = RentDao.getInstance();
		int r_book1 = rdao.getArticleCount(0); //기증대기
		int r_book2 = rdao.getArticleCount(1); //기증완료
		int r_book3 = rdao.getArticleCount(3); //폐기도서
		request.setAttribute("r_bookCount", r_book1+r_book2+r_book3);
		
		
		//직거래 / 경매 게시판
		ResellbookDao rbdao = ResellbookDao.getInstance();
		BidbookDao biddao = BidbookDao.getInstance();
		int resell = rbdao.getResellCount();
		int resell2 = biddao.getBidCount();
		request.setAttribute("resellPa", resell + resell2);
		
		return "/d_manage/manage_dash.jsp";
	}
	
}

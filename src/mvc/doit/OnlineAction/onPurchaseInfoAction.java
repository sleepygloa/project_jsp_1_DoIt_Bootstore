package mvc.doit.OnlineAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class OnPurchaseInfoAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		request.setCharacterEncoding("utf-8");

		
		int d_bno = 0;
		if(request.getParameter("d_bno") != null){
			d_bno = Integer.parseInt(request.getParameter("d_bno"));
		};

		
		
        List articleList = null; //리스트 초기화
        OnDao dao = OnDao.getInstance();//DB연동
		OnBookDto article = new OnBookDto();
        
		//메인페이지에서 접근한다면 d_bno 로, 상세페이지 List로 접근하여도 d_bno 
		if(d_bno == 0){

		}else{
			article = dao.getOnBookArticle(d_bno);
		} 
			
		request.setAttribute("article", article);
		
	return "/d_online/onPurchaseInfo.jsp";
	
	} 
}

package mvc.doit.OnlineAction;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnBookIntroDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class OnArticleAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response)  throws Exception{
			
			String pagetype = request.getParameter("b"); //회원 구매 페이지에서 접속, 관리자 판매
				pagetype = request.getParameter("s"); //회원 판매 페이지에서 접속, 관리자 구매
				
			OnDao dao = OnDao.getInstance();//DB연동	
							
			int d_bno =  Integer.parseInt(request.getParameter("d_bno")); 
			
			String pageNum = request.getParameter("pageNum");//페이지번호 받기


			OnBookDto article =  dao.getOnBookArticle(d_bno);
			int d_bcode = article.getD_bcode();
			OnBookIntroDto obiDto = dao.Admin_OnBookIntro_load(d_bcode);
		     
			//판매가 리스트 
			List sellList = null;

			if(pagetype != null && pagetype=="buypage"){
		        sellList = dao.getD_bsellvalue(d_bno);//전체 글의 수 
				request.setAttribute("sellList", sellList);		        
			}else{}
			

			request.setAttribute("d_bno", d_bno);				
			
			request.setAttribute("pageNum", pageNum);	       
			request.setAttribute("article", article);
			request.setAttribute("obiDto",obiDto);
			request.setAttribute("pagetype", pagetype);
			request.setAttribute("sellList", sellList);

			   
			return "/d_online/onArticle.jsp";
	}
}


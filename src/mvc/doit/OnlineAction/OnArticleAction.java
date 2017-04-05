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
			
			//----넘어오는 값, Action에서 사용할 변수지정 -------------------------------------------------
			String pagetype = request.getParameter("b"); //회원 구매 페이지에서 접속, 관리자 판매
				pagetype = request.getParameter("s"); //회원 판매 페이지에서 접속, 관리자 구매
			
			int d_bno = 0; //article 번호 받기
			if(request.getParameter("d_bno") != "0"){
				d_bno = Integer.parseInt(request.getParameter("d_bno")); 
			}
			String pageNum = request.getParameter("pageNum");//페이지번호 받기
	        int d_bvalue = 0;
			String d_bname = ""; //변수 초기화
			OnBookDto article = null;
			
	        //-----------------
			OnDao dao = OnDao.getInstance();//DB연동	
			if(d_bno == 0){
			}else{
					article = dao.getOnBookArticle(d_bno);		
			} 
			int d_bcode = article.getD_bcode();
			OnBookIntroDto obiDto = dao.Admin_OnBookIntro_load(d_bcode);//목차, 소개글 불러오기
		     
			//판매가 리스트 
			List sellList = null;

			if(pagetype != null && pagetype=="buypage"){
		        sellList = dao.getD_bsellvalue(d_bno);//전체 글의 수 
				request.setAttribute("sellList", sellList);		        
			}else{}
			
			


			//d_bname으로 금액을 뽑아 등급별 예상금액을 보여주는 Dao-----------------------------------------------------
			d_bname = article.getD_bname();
			d_bvalue =  dao.getFindNameToValue(d_bname);

			int d_bpurchasevalueS = (int)((double)d_bvalue * 0.8)/100 * 100; //정가의 80%가격의 십원단위 까지 버림
			int d_bpurchasevalueA = (int)((double)d_bvalue * 0.6)/100 * 100; //정가의 60%가격의 십원단위 까지 버림
			int d_bpurchasevalueB = (int)((double)d_bvalue * 0.4)/100 * 100; //정가의 40%가격의 십원단위 까지 버림


			request.setAttribute("d_bno", d_bno);				
			request.setAttribute("pageNum", pageNum);	       
			request.setAttribute("article", article);
			request.setAttribute("obiDto",obiDto);
			request.setAttribute("pagetype", pagetype);
			request.setAttribute("sellList", sellList);
			request.setAttribute("d_bpurchasevalueS", d_bpurchasevalueS);
			request.setAttribute("d_bpurchasevalueA", d_bpurchasevalueA);
			request.setAttribute("d_bpurchasevalueB", d_bpurchasevalueB);

			   
			return "/d_online/onArticle.jsp";
	}
}


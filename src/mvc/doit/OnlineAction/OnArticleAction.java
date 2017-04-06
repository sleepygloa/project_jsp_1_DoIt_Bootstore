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
			
	//----0.넘어오는 값, Action에서 사용할 변수지정 -------------------------------------------------
			
			
			int d_bno = 0; //article 번호 받기
			if(request.getParameter("d_bno") != "0"){
				d_bno = Integer.parseInt(request.getParameter("d_bno")); 
			}
			String pageNum = request.getParameter("pageNum");//페이지번호 받기
	        int d_bvalue = 0;
			String d_bname = ""; //변수 초기화
			OnBookDto article = null;
			
    //---- 0. DB연동 ------------------------------------
			OnDao dao = OnDao.getInstance();
			
	//---- 1. 글번호가 있을때, 글정보와 목차 정보를 불러옴----------------------------
			OnBookIntroDto obiDto = null;
			String Check = "";
			if(d_bno == 0){
			}else{
				//지정한 판매가능한 책의 정보를 불러옴
					article = dao.getOnBookArticle(d_bno, Check);
				//지정한 아무 책의 정보나 불러옴
					if(article == null){
						Check = "d_bno";
						article = dao.getOnBookArticle(d_bno, Check);
					}
					int d_bcode = article.getD_bcode();
					obiDto = dao.Admin_OnBookIntro_load(d_bcode);//목차, 소개글 불러오기
			} 
			

	//---- 2. d_bname으로 정가를 검색, 그리고 검수등급별 예상판매가를 보여주는 Dao-----------------------------------------------------
			d_bname = article.getD_bname();
			d_bvalue =  dao.getFindNameToValue(d_bname);

			int d_bpurchasevalueS = (int)((double)d_bvalue * 0.8)/100 * 100; //정가의 80%가격의 십원단위 까지 버림
			int d_bpurchasevalueA = (int)((double)d_bvalue * 0.6)/100 * 100; //정가의 60%가격의 십원단위 까지 버림
			int d_bpurchasevalueB = (int)((double)d_bvalue * 0.4)/100 * 100; //정가의 40%가격의 십원단위 까지 버림

	//----- 3. 글의 정보는 있지만, 판매가능한 책이 없을 때의 정보를 넘겨, 구매하기 선택시 alert을 띄워 사용자에게 알려줍니다. ------------------
			int sellCanCount = dao.getSellCanCount(d_bname);
			

			request.setAttribute("d_bno", d_bno);				
			request.setAttribute("pageNum", pageNum);	       
			request.setAttribute("article", article);
			request.setAttribute("obiDto",obiDto);

			request.setAttribute("d_bpurchasevalueS", d_bpurchasevalueS);
			request.setAttribute("d_bpurchasevalueA", d_bpurchasevalueA);
			request.setAttribute("d_bpurchasevalueB", d_bpurchasevalueB);
			
			request.setAttribute("sellCanCount", sellCanCount);

			   
			return "/d_online/onArticle.jsp";
	}
}


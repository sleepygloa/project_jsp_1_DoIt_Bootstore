package mvc.doit.OnlineAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Login.mySellingListDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class OnSellBookAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		request.setCharacterEncoding("UTF-8");

		
		
//------------0. 공통부분 -------------------------------------------------------------		
		String pageNum = request.getParameter("pageNum");//페이지 번호
        if (pageNum == null) {
            pageNum = "1"; //1페이지당 20권의 책 보여줌
        }
        int pageSize = 20;//한 페이지의 글의 개수
        int currentPage = Integer.parseInt(pageNum); //페이지번호를 Int 로 저장한 값
        int startRow = (currentPage - 1) * pageSize + 1;//한 페이지의 시작글 번호, 시작 글 번호 1
        int endRow = currentPage * pageSize;//한 페이지의 마지막 글번호, 마지막 등록글번호 20
        int count = 0; //변수 초기화
        int number = 0; //변수 초기화
        
//------------1. fillter 정의 -----------------------------------------------------
        int d_bonFillterReturn = 0; //원래페이지로 다시 반환하여 사용할 값        
        String d_bonFillter = null; //Dao에서 사용할 값
        
        if(request.getParameter("d_bonFillter") != null){
        	d_bonFillter = request.getParameter("d_bonFillter");
        	d_bonFillterReturn = Integer.parseInt(request.getParameter("d_bonFillter"));
        	switch(d_bonFillter){
        	//String 저장값은 DB 저장값과 일치.
        	case "0" : d_bonFillter = ""; break;
        	case "01" : d_bonFillter = "소설 / 시 / 에세이"; break;
        	case "02" : d_bonFillter = "참고 / 전문서적"; break;
        	case "03" : d_bonFillter = "어린이 서적"; break;
        	case "04" : d_bonFillter = "인문학 서적"; break;
        	case "05" : d_bonFillter = "과학 전문서적"; break;
        	case "06" : d_bonFillter = "기타 서적"; break;
        	case "07" : d_bonFillter = "아트지"; break;
        	case "08" : d_bonFillter = "레자크지"; break;
        	case "09" : d_bonFillter = "스노우지"; break;
        	case "10" : d_bonFillter = "모조지"; break;
        	case "11" : d_bonFillter = "국내"; break;
        	case "12" : d_bonFillter = "국외"; break;
        	}
        };       

//------------2. 종합검색 정의-------------------------------------------------------------
		String select = null;
		if(request.getParameter("select") != ""){
			select = request.getParameter("select");
		}
		
        List articleList = null; //리스트 초기화
        OnDao article = OnDao.getInstance();//DB연동
        

//  //--------------0. 전체 리스트  반환 test 중복값 제거 -----------------------------------------------------
//        if(d_bonFillter == null && select == null){
//            count = article.getD_BSellCount();//전체 글의 수 
//            if(count>0){
//                articleList = article.getD_BSellList(startRow, endRow);//현재 페이지에 해당하는 글 목록
//            }        
        
        
        
//--------------0. 전체 리스트  반환 -----------------------------------------------------
        if(d_bonFillter == null && select == null){
            count = article.getD_BSellCount();//전체 글의 수 
            if(count>0){
                articleList = article.getD_BSellList(startRow, endRow);//현재 페이지에 해당하는 글 목록
            }
//--------------1. 책 장르 조건으로 리스트  반환----------------------------------------------    	        	
    	}else if(d_bonFillter != null){
            count = article.getD_BSellCount(d_bonFillter ,d_bonFillterReturn);//전체 글의 수 
            if(count>0){
                articleList = article.getD_BSellList(d_bonFillter, d_bonFillterReturn, startRow, endRow);//현재 페이지에 해당하는 글 목록
            }
//-------------2. 종합 검색(text)로 리스트 반환----------------------------------------------
    	}else if(select != null){
            count = article.getD_BSelectCount(select);//전체 글의 수 
            if(count>0){
                articleList = article.getD_BSelectList(select, startRow, endRow);//현재 페이지에 해당하는 글 목록
            }	
    	}        
        
        
        
//-------------3. 오늘 들어온 책 수 ---------------------------------------------------------
        String todayPurchaseCount = "";
        String[] todayPurchaseCountArray = null;
        int digitCheck = 0;
        String Thousand = "0";
        String hundred = "0";
        
        if(article.getD_BTodayCount() == 0){}else{
        	
        	digitCheck += article.getD_BTodayCount();
        	
        	if(digitCheck > 0 || digitCheck < 10){
        		todayPurchaseCount += Thousand + hundred + digitCheck;
        	}else if(digitCheck > 11 || digitCheck < 100){
        		todayPurchaseCount += Thousand + digitCheck;
        	}else{
        		todayPurchaseCount += digitCheck;
        	}
    		todayPurchaseCountArray = todayPurchaseCount.split("(?<=\\G.{" + 1 + "})");
        }
        
        
        
        
        

//--------------1. 평균 구매가 검색 및 반환 --------------------------------------------------------

		number=count-(currentPage-1)*pageSize;//글목록에 표시할 글번호
        //해당 뷰에서 사용할 속성
	    request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("count", new Integer(count));
        request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
        request.setAttribute("articleList", articleList);
        request.setAttribute("d_bonFillterReturn", d_bonFillterReturn);
        request.setAttribute("digitCheck", digitCheck);
        request.setAttribute("todayPurchaseCount", todayPurchaseCount);
        request.setAttribute("todayPurchaseCountArray", todayPurchaseCountArray);
//        request.setAttribute("d_bonFillterGenre", d_bonFillterGenre); //확인용, 주석처리
//        request.setAttribute("d_bonFillterLocation", d_bonFillterLocation); //확인용, 주석처리
//        request.setAttribute("d_bonFillterGenre2", d_bonFillterGenre2);//확인용, 주석처리
//        request.setAttribute("fillterVar", fillterVar); //확인용, 주석처리
//        request.setAttribute("select", select); //확인용, 주석처리
//        request.setAttribute("avgPurchaseValue", avgPurchaseValue);
        
        
        return "/d_online/onSellBook.jsp";//해당 뷰
	}
}

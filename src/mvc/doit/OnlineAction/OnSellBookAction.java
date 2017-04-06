package mvc.doit.OnlineAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Login.mySellingListDto;
import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.Statistics.StatisticsDto;
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
		if(request.getParameter("select") != null){
			select = request.getParameter("select");
		}
		
        List articleList = null; //리스트 초기화
        List avgSellvalue = null;
        OnDao dao = OnDao.getInstance();//DB연동

        String selectBookFullNameReturn = null;
//--------------0. 전체 리스트  반환 -----------------------------------------------------
        if(d_bonFillter == null && select == null){
            count = dao.getD_BSellCount();//전체 글의 수 
            if(count > 0){
                articleList = dao.getD_BSellList(startRow, endRow);//현재 페이지에 해당하는 글 목록
            }
//--------------1. 책 장르 조건으로 리스트  반환----------------------------------------------    	        	
    	}else if(d_bonFillter != null){
            count = dao.getD_BSellCount(d_bonFillter ,d_bonFillterReturn);//전체 글의 수 
            if(count > 0){
                articleList = dao.getD_BSellList(d_bonFillter, d_bonFillterReturn, startRow, endRow);//현재 페이지에 해당하는 글 목록
            }
//-------------2. 종합 검색(text)로 리스트 반환----------------------------------------------
    	}else if(select != null){
            count = dao.getD_BSelectCount(select);//전체 글의 수 
            if(count > 0){
                articleList = dao.getD_BSelectList(select, startRow, endRow);//현재 페이지에 해당하는 글 목록
                //큰제목 이름을 '검색어 + 리스트'로 변경하는 구문, selectBookFullNameReturn 를 반환합니다. 
                selectBookFullNameReturn = dao.findSelectToBookFullName(select);
            }	
    	}        
        
        
        
//-------------3. 오늘 들어온 책 수 ---------------------------------------------------------
        String todayPurchaseCount = "";
        String[] todayPurchaseCountArray = null;
        int digitCheck = 0;
        String Thousand = "0";
        String hundred = "0";
        
        if(dao.getD_BTodayCount() == 0){}else{
        	
        	digitCheck = dao.getD_BTodayCount();
        	
        	if(digitCheck >= 0 && digitCheck < 10){
        		todayPurchaseCount += Thousand + hundred + digitCheck;
        	}else if(digitCheck >= 10 && digitCheck < 100){
        		todayPurchaseCount += Thousand + digitCheck;
        	}else{
        		todayPurchaseCount += digitCheck;
        	}
    		todayPurchaseCountArray = todayPurchaseCount.split("(?<=\\G.{" + 1 + "})");
        }
        



//-------------4. 책  평균가 반환 ----------------------------------------------------------
        //불필요하게 나열될 List가 아닌 책 전체 DB의 책당 평균값을 모두 List
        String getD_bname = null;
        OnBookDto dto = null;
        int d_bavgsellvalue = 0;
        int articleListSize = articleList.size();
     	for(int i = 0; i < articleListSize; i++){
     		dto = ((OnBookDto)articleList.get(i));
     				getD_bname = dto.getD_bname();
     				d_bavgsellvalue = dao.getBookNameToAvgSellValue(getD_bname);
     				dto.setD_bavgsellvalue(d_bavgsellvalue);
     	}
		number=count-(currentPage-1)*pageSize;//글목록에 표시할 글번호
//--------------5. 반환 --------------------------------------------------------


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
        request.setAttribute("selectBookFullNameReturn", selectBookFullNameReturn);

        
        
        
        return "/d_online/onSellBook.jsp";//해당 뷰
	}
}

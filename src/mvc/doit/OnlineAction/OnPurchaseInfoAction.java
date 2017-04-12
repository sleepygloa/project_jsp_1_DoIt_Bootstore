package mvc.doit.OnlineAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class OnPurchaseInfoAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		request.setCharacterEncoding("utf-8");

//---- 0. 공통부분, 변수지정, 변수를 받아옴. -------------------------------------------------------------		
		String pageNum = request.getParameter("pageNum");//페이지 번호
        if (pageNum == null) {
            pageNum = "1"; //1페이지당 10권의 책 보여줌
        }
        int pageSize = 10;//한 페이지의 글의 개수
        int currentPage = Integer.parseInt(pageNum); //페이지번호를 Int 로 저장한 값
        int startRow = (currentPage - 1) * pageSize + 1;//한 페이지의 시작글 번호, 시작 글 번호 1
        int endRow = currentPage * pageSize;//한 페이지의 마지막 글번호, 마지막 등록글번호 10
        int count = 0; //변수 초기화
        int number = 0; //변수 초기화		
		
//---- 1. 변수를 받아옴. d_bno 가 있다면, d_bcode가 있다면 --------------------------------------------------
		String d_bname = "";
		int d_bno = 0;
		if(request.getParameter("d_bno") != null){
			d_bno = Integer.parseInt(request.getParameter("d_bno"));
		};
		int d_bcode = 0;
		if(request.getParameter("d_bcode") != null){
			d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
		}
		
		
        List<OnBookDto> articleList = null; //리스트 초기화
        OnDao dao = OnDao.getInstance();//DB연동
		OnBookDto article = new OnBookDto();

//---- 페이지 접근상황을 설정하는 Dao, d_bno일 때, d_bcode일 때(바로전 페이지는 d_no로 접근했지만, 동일한 d_bname의 list 페이지인 여기에서는 d_bcode로 접근)--------------------------
		String Check = "";
		if(d_bno == 0){
			if(d_bcode == 0){
			}else{
				Check = "d_bcode_oneBook";
				article = dao.getOnBookArticle(d_bcode, Check);	
			}
		}else{
				Check = "d_bno_oneBook";
				article = dao.getOnBookArticle(d_bno, Check);	
		}
		
//---- List를 뽑는 Dao-----------------------------------------------------
		d_bname = article.getD_bname();
		count =  dao.getFindNameToNameCount(d_bname);
		articleList = dao.getFindNameToName(d_bname, startRow, endRow);//책의 이름과 같은 책들을 List 
		
//---- 첫번째 페이지에서 보여줄 dto (화면 아래 List클릭시 보여주는 dto가 아님)-----------------------------------------------
//d_bno로 검색한 첫번째 페이지의 책은 현재 판매가능한 책과 거리가 멀기때문에 다시 검색을 해준다.
		if(request.getParameter("d_bno") != null){
			article = (OnBookDto)articleList.get(0);
		}
		
//---- 최저가 검색 ------------
		int MinD_bsellvalue = dao.getFindNameToMinSellValue(d_bname);
		
//---- 정가대비 할인 되는 비율 ----------		
		int valueToSellvaluePercent = 
(int)(100 - 	((double)article.getD_bsellvalue() / (double)article.getD_bvalue() * 100));
		
//---- 등급적용 판매가 ----------
//회원의 등급을 불러옵니다.
		HttpSession  session = request.getSession();
		String d_id = (String)session.getAttribute("memId");
		int gradeToSellValue = article.getD_bsellvalue();
		int gradeCheck = dao.getIdToGrade(d_id);
				if(		  gradeCheck == 0) {
					 
				}else if (gradeCheck == 1) {
					gradeToSellValue = (int)((double)gradeToSellValue * 0.95);
				}else if (gradeCheck == 2) {
					gradeToSellValue = (int)((double)gradeToSellValue * 0.9);
				}else{}
				
        //해당 뷰에서 사용할 속성
	    request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("count", new Integer(count));
        request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("article", article);
		request.setAttribute("articleList", articleList);
		
		request.setAttribute("MinD_bsellvalue",MinD_bsellvalue);
		request.setAttribute("valueToSellvaluePercent", valueToSellvaluePercent);
		request.setAttribute("gradeToSellValue", gradeToSellValue);
		
	return "/d_online/onPurchaseInfo.jsp";
	
	} 
}

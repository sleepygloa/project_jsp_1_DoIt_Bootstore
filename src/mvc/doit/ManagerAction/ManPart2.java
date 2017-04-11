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

public class ManPart2 implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//전체 수익
		ManDao mdao = ManDao.getInstance();
		ManDto mdto = new ManDto();
		mdto = mdao.getDashM();
		request.setAttribute("dashM", mdto);
		
		
		/*---------------------- 도서기부 승인 요청 대기리스트 -------------------------*/
		RentDao dbPro2 = RentDao.getInstance();//도서관 서비스 dao
		
		//상태 변경 리스트 : 0-전체, 1-승인하기, 3-폐기처분 
		String pack = request.getParameter("pack"); 
		if(pack != null){
			int pack1 = Integer.parseInt(pack);
			if(pack1 == 0){//전체처리인가
				dbPro2.upBook(pack1, "");
			}else{
				String br_code = request.getParameter("br_code");
				dbPro2.upBook(pack1, br_code);
			}
			
		}
		
		//리스트 구분 출력
		String guaa = request.getParameter("gua"); 
		int gua = 0; //대기리스트보기
		if(guaa != null){
			gua = Integer.parseInt(guaa);
		}
		request.setAttribute("gua23", gua); //전체승인구분
		
		
		String pageNum2 = request.getParameter("pageNum2");//페이지 번호
		
        if (pageNum2 == null) {
            pageNum2 = "1";
        }
        int pageSize2 = 8;//한 페이지의 글의 개수
        int currentPage2 = Integer.parseInt(pageNum2);
        int startRow2 = (currentPage2 - 1) * pageSize2 + 1;//한 페이지의 시작글 번호
        int endRow2 = currentPage2 * pageSize2;//한 페이지의 마지막 글번호
        int count2 = 0;
       
        
        List articleList2 = null;		
		count2 = dbPro2.getArticleCount(gua);//전체 글의 수
		
		if (count2 > 0) {
			articleList2 = dbPro2.getArticles2("br_no",gua);//페이지 글 목록
		}
		
		request.setAttribute("pageNum2", new Integer(pageNum2));
        request.setAttribute("currentPage2", new Integer(currentPage2));
	    request.setAttribute("startRow2", new Integer(startRow2));
	    request.setAttribute("endRow2", new Integer(endRow2));
	    request.setAttribute("count2", new Integer(count2));
	    request.setAttribute("pageSize2", new Integer(pageSize2));
		request.setAttribute("articleList2", articleList2);

		
		/*---------------------- 도서기부 승인 요청 대기리스트 끝 -------------------------*/
		
		
		
		
		/*---------------------- 판매자 권한 승인 요청 대기리스트 -------------------------*/
		//전체 or 대기중 구분
		String waitB = request.getParameter("waitB");
		if(waitB == null){
			waitB = "all";
		}
		
		
		String pageNum = request.getParameter("pageNum");
		  if (pageNum == null) {
		        pageNum = "1";
		    }
		  
		  
		    int pageSize = 10;
		    int currentPage = Integer.parseInt(pageNum);
		    int startRow = (currentPage - 1) * pageSize + 1;
		    int endRow = currentPage * pageSize;
		    int count = 0;
		    int number=0;
		    
		    List articleList = null;

		    ResellintroDao article = ResellintroDao.getInstance();
		    
		    String search=request.getParameter("search");
		    
		    //기부 상태 구분 판별
		    if(waitB.equals("all")){//전체보기
		    	if(search==null){
		    		count = article.getArticleCount();
				    if (count > 0) {
				        articleList = article.getArticles(startRow, endRow);
				    }
		    	}else{
			    	count = article.getSearchCount(search);
			        if (count > 0) {
			            articleList = article.getSearchArticles(search);
			    	}
				}
		    }else{//승인대기 상태
		    	if(search==null){
			    	count = article.waitOkCount();
			    	if (count > 0) {
			    		articleList = article.waitOks(startRow, endRow);
			    	}
			    }
		    }
		    
		    
			number=count-(currentPage-1)*pageSize;
			
			request.setAttribute("pageSize", new Integer(pageSize));
			request.setAttribute("currentPage", new Integer(currentPage));
			request.setAttribute("startRow", new Integer(startRow));
			request.setAttribute("endRow", new Integer(endRow));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("articleList", articleList);
			
			
		
		return "/d_manage/man_part2.jsp";
	}

}

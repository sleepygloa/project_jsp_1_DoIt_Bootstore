package mvc.doit.ManagerAction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Manager.ManDao;
import mvc.doit.Manager.ManDto;
import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class ManPart3 implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 저장
		
		//객체화
		ManDao mdao = ManDao.getInstance(); //매니저 Dao 객체화
		
		//전체 수익
		ManDto mdto = new ManDto();
		mdto = mdao.getDashM();
		request.setAttribute("dashM", mdto);
		
		
		//------------ 도서연체자 출력 ------------//
		List blLib = new ArrayList(); //연체자 리스트
		blLib = mdao.getLibBla();
		request.setAttribute("blLib", blLib);
		//연체자 수
		int blLib_si = 0;
		if(blLib == null){
			blLib_si = 0;
		}else{
			blLib_si = blLib.size();
		}
		request.setAttribute("blLib_si", blLib_si);
		
		//----------- 연체 도서리스트 출력 -------//
		List blDos = new ArrayList();
		blDos = mdao.comDate();
		request.setAttribute("blDos", blDos);
		
		
		//----------- 신고자리스트 -------//
		int pageSize = 10;//한화면 게시물개수
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null){
			pageNum = "1";
		}
		
		
		int currentPage = Integer.parseInt(pageNum); //페이지번호
		int startRow = (currentPage -1) * pageSize +1; 
		int endRow = currentPage * pageSize +1; //마지막페이지 범위값을 계산한정보
		int count = 0;
		int number = 0;
		
		List articleList = null;
		ResellbookDao article = ResellbookDao.getInstance();
		
		//LoginDto dto=article.getGrade(id);//회원  등급 확인을 위함
		
		//검색
				String search = request.getParameter("search");
				if(search == null){
					//전체내용
					count = article.getReportCount();
					if(count > 0){ //글이 있을때 resellList호출
						
						articleList = article.getReportList(startRow, endRow);
					}
				}else{
					count = article.reportSearchCount(search);
					if(count >0){
						articleList = article.reportSearch(search,startRow, endRow);
					}
				}
				
				number = count -(currentPage-1)*pageSize;
		
		
		request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("count", new Integer(count));
        request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		
		
		return "/d_manage/man_part3.jsp";
	}

}

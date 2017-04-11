package mvc.doit.AdminAction;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Login.LoginDto;
import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class Admin_ReReportListAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
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
		//request.setAttribute("id", id);
		return "/d_admin/admin_ReReportList.jsp";
	}
}

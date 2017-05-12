package mvc.doit.ResellAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.ResellBean.ResellReplyDto;
import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.ResellBean.ResellbookDto;
import mvc.doit.SuperAction.SuperAction;

public class ReContentAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		try {
			HttpSession session = request.getSession();
			String id= (String)session.getAttribute("memId");
			
			int rbook_no = Integer.parseInt(request.getParameter("rbook_no"));//해당글번호
			String pageNum = request.getParameter("pageNum");//해당페이지번호
			
			
			ResellbookDao dbPro = ResellbookDao.getInstance();//db처리
			ResellbookDto article =dbPro.getResllArticle(rbook_no);//해당 글번호에 대한 해당 레코드
			
			ResellReplyDto rer = new ResellReplyDto();
			request.setAttribute("rer", rer);
			
			int count = 0;
			count = dbPro.getReplyCount(article.getRbook_no());
			request.setAttribute("count", count);
			
			request.setAttribute("rbook_no", rbook_no);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("article", article);
			request.setAttribute("id",id);
			
			List list = dbPro.getReReplyList(article.getRbook_no());
			request.setAttribute("list", list);

			
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			
		}
		return "/d_resell/reContent.jsp";
	}
}

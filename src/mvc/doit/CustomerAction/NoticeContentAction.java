package mvc.doit.CustomerAction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Customer.NoticeDao;
import mvc.doit.Customer.NoticeDto;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;

public class NoticeContentAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
			request.setCharacterEncoding("UTF-8");
		
		try {
			HttpSession session = request.getSession();
			String notice_id= (String)session.getAttribute("memId");
			
			int notice_no = Integer.parseInt(request.getParameter("notice_no"));//해당글번호
			String pageNum = request.getParameter("pageNum");//해당페이지번호
			
			NoticeDao dao= NoticeDao.getInstance();
		    LoginDto dto=dao.getGrade(notice_id);//관리자 등급 확인
		    
			NoticeDao dbPro = NoticeDao.getInstance();//db처리
			NoticeDto article =dbPro.getNoticeArticle(notice_no, notice_id);//해당 글번호에 대한 해당 레코드
			
			request.setAttribute("notice_no", notice_no);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("article", article);
			request.setAttribute("notice_id",notice_id);
			request.setAttribute("dto", dto);
			
			
		
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			
		}
		
		return "/d_customer/noticeContent.jsp";
	}

}

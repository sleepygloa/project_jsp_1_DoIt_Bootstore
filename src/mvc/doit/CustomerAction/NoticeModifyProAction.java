package mvc.doit.CustomerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.doit.Customer.NoticeDao;
import mvc.doit.Customer.NoticeDto;
import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.ResellBean.BidbookDto;
import mvc.doit.SuperAction.SuperAction;

public class NoticeModifyProAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		
		NoticeDto article = new NoticeDto();
		article.setNotice_no(Integer.parseInt(request.getParameter("notice_no")));
		article.setNotice_id(request.getParameter("notice_id"));
		article.setNotice_name(request.getParameter("notice_name"));
		article.setNotice_content(request.getParameter("notice_content"));
		
		NoticeDao dbPro = NoticeDao.getInstance();
		dbPro.NoticeModifyArticle(article);
		
		request.setAttribute("pageNum", pageNum);
		
		return "/d_customer/noticeModifyPro.jsp";
	}

}

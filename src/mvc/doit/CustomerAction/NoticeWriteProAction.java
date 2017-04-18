package mvc.doit.CustomerAction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.doit.Customer.NoticeDao;
import mvc.doit.Customer.NoticeDto;
import mvc.doit.SuperAction.SuperAction;

public class NoticeWriteProAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		NoticeDto notice = new NoticeDto();
		notice.setNotice_id(request.getParameter("notice_id"));
		notice.setNotice_name(request.getParameter("notice_name"));
		notice.setNotice_content(request.getParameter("notice_content"));
		notice.setNotice_reg_date(new Timestamp(System.currentTimeMillis()));
		
		NoticeDao DbPro = NoticeDao.getInstance();
		DbPro.insertArticle(notice);
		return "/d_customer/noticeWritePro.jsp";
	}

}

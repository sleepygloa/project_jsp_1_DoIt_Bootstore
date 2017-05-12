package mvc.doit.ResellAction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.ResellBean.ResellbookDto;
import mvc.doit.SuperAction.SuperAction;

public class ReWriteProAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response )throws Exception{
		request.setCharacterEncoding("utf-8");
		
		String path = request.getRealPath("rbook_pic");
		int size = 1024*1024*5;
		String enc="utf-8";
		DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(request,path,size,enc,df);
		
		ResellbookDto resell = new ResellbookDto();
		resell.setRbook_no(Integer.parseInt(multi.getParameter("rbook_no")));
		resell.setRbook_id(multi.getParameter("rbook_id"));
		resell.setRbook_pw(multi.getParameter("rbook_pw"));
		resell.setRbook_name(multi.getParameter("rbook_name"));
		resell.setRbook_writer(multi.getParameter("rbook_writer"));
		resell.setRbook_company(multi.getParameter("rbook_company"));
		resell.setRbook_price(Integer.parseInt(multi.getParameter("rbook_price")));
		resell.setRbook_content(multi.getParameter("rbook_content"));
		resell.setRbook_pic(multi.getFilesystemName("rbook_pic"));
		resell.setRbook_reg_date(new Timestamp(System.currentTimeMillis()));
		resell.setRbook_subject(multi.getParameter("rbook_subject"));
		resell.setRbook_price2(Integer.parseInt(multi.getParameter("rbook_price2")));
		resell.setRbook_bgread(Integer.parseInt(multi.getParameter("rbook_bgread")));
		//resell.setRboook_readcount(Integer.parseInt(multi.getParameter("rbook_readcount")));
		//resell.setRbook_sell_check(Integer.parseInt(multi.getParameter("rbook_sell_check")));
		
		
		ResellbookDao reDbPro = ResellbookDao.getInstance();
		reDbPro.insertArticle(resell);
		return "/d_resell/reWritePro.jsp";
	}
}

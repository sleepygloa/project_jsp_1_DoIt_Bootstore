package mvc.doit.BidAction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.ResellBean.BidbookDto;
import mvc.doit.SuperAction.SuperAction;

public class BidWriteProAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
request.setCharacterEncoding("utf-8");
		
		String path = request.getRealPath("bid_pic");
		int size = 1024*1024*5;
		String enc="utf-8";
		DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(request,path,size,enc,df);
		
		BidbookDto bid = new BidbookDto();
		bid.setBid_no(Integer.parseInt(multi.getParameter("bid_no")));
		bid.setBid_id(multi.getParameter("bid_id"));
		bid.setBid_name(multi.getParameter("bid_name"));
		bid.setBid_price1(Integer.parseInt(multi.getParameter("bid_price1")));
		bid.setBid_pic(multi.getFilesystemName("bid_pic"));
		bid.setBid_subject(multi.getParameter("bid_subject"));
		bid.setBid_content(multi.getParameter("bid_content"));
		bid.setBid_time_value(Integer.parseInt(multi.getParameter("bid_time_value")));
		bid.setBid_reg_date(new Timestamp(System.currentTimeMillis()));
		bid.setBid_finish_date(new Timestamp(System.currentTimeMillis()));
		
		BidbookDao DbPro = BidbookDao.getInstance();
		DbPro.insertArticle(bid);
		
		return "/d_bid/bidWritePro.jsp";
	}

}

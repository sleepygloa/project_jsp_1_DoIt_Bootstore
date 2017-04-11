package mvc.doit.BidAction;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.ResellBean.BidbookDto;
import mvc.doit.ResellBean.ResellReplyDto;
import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.ResellBean.ResellbookDto;
import mvc.doit.SuperAction.SuperAction;

public class BidContentAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			HttpSession session = request.getSession();
			String bid_id= (String)session.getAttribute("memId");
			
			int bid_no = Integer.parseInt(request.getParameter("bid_no"));//해당글번호
			String pageNum = request.getParameter("pageNum");//해당페이지번호
//			long b= Long.parseLong(request.getParameter("bid_finish_date"));
			
			BidbookDao dbPro = BidbookDao.getInstance();//db처리
			BidbookDto article =dbPro.getBidArticle(bid_no, bid_id);//해당 글번호에 대한 해당 레코드
			BidbookDto article1 =dbPro.getBidGrade(article.getBid_id());
			dbPro.getFinishArticle(bid_no);
			
			String bb= "";
			bb += article.getBid_finish_date(); 
		    java.sql.Timestamp ts = java.sql.Timestamp.valueOf(bb);
		    long b = ts.getTime();
			
			
			long sysdate=System.currentTimeMillis();
			
			long a=(b-sysdate)/1000;
			int c= (int)a;
			
			
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // HH=24h, hh=12h
			String sysdate1 = df.format(sysdate);
			
			
			//DateFormat df1 = new SimpleDateFormat("HH:mm:ss"); // HH=24h, hh=12h
			//String str = df1.format(a);
			
			
			request.setAttribute("bid_no", bid_no);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("article", article);
			request.setAttribute("article1", article1);
			request.setAttribute("sysdate1", sysdate1);
			//request.setAttribute("bid_finish_date", bid_finish_date);
			request.setAttribute("bid_id",bid_id);
			request.setAttribute("c",c);
			
			
		
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			
		}
		
		return "/d_bid/bidContent.jsp";
	}

}

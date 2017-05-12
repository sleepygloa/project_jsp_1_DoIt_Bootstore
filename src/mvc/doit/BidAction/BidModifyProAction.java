package mvc.doit.BidAction;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.ResellBean.BidbookDto;
import mvc.doit.SuperAction.SuperAction;

public class BidModifyProAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String path = request.getRealPath("bid_pic");
		int size =1024*1024*5;
		String enc = "UTF-8";
		DefaultFileRenamePolicy df =new DefaultFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(request,path,size,enc,df);
		
		String pageNum = multi.getParameter("pageNum");
		
		BidbookDto article = new BidbookDto();
		article.setBid_no(Integer.parseInt(multi.getParameter("bid_no")));
		article.setBid_id(multi.getParameter("bid_id"));
		article.setBid_subject(multi.getParameter("bid_subject"));
		article.setBid_name(multi.getParameter("bid_name"));
		article.setBid_content(multi.getParameter("bid_content"));
		
	      if(multi.getParameter("bid_picCheck") != null){
	    	  article.setBid_pic(multi.getParameter("bid_picCheck"));
	          if(multi.getFilesystemName("bid_pic") != null){
	        	  article.setBid_pic(multi.getFilesystemName("bid_pic"));
	          }
	       }else{
	    	   article.setBid_pic(multi.getFilesystemName("bid_pic"));
	       }    
		
		Enumeration<String>fileNames =multi.getFileNames();
		if(fileNames.hasMoreElements()){
			String fileName = fileNames.nextElement();
			String updateFile = multi.getFilesystemName(fileName);
			
		}

		BidbookDao dbPro = BidbookDao.getInstance();
		dbPro.BidModifyArticle(article);
		
		request.setAttribute("pageNum", pageNum);
		
		return "/d_bid/bidModifyPro.jsp";
	}

}

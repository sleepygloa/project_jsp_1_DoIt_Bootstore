package mvc.doit.ResellAction;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.ResellBean.ResellbookDto;
import mvc.doit.SuperAction.SuperAction;

public class ReUpdateProAction implements SuperAction{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		String path = request.getRealPath("rbook_pic");
		int size =1024*1024*5;
		String enc = "UTF-8";
		DefaultFileRenamePolicy df =new DefaultFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(request,path,size,enc,df);
		
		String pageNum = multi.getParameter("pageNum");
		
		ResellbookDto article = new ResellbookDto();
		article.setRbook_no(Integer.parseInt(multi.getParameter("rbook_no")));
		article.setRbook_id(multi.getParameter("rbook_id"));
		article.setRbook_name(multi.getParameter("rbook_name"));
		article.setRbook_price(Integer.parseInt(multi.getParameter("rbook_price")));
		article.setRbook_writer(multi.getParameter("rbook_writer"));
		article.setRbook_company(multi.getParameter("rbook_company"));
		article.setRbook_content(multi.getParameter("rbook_content"));
		
		//String name = multi.getFilesystemName("bid_pic");//기존첨부파일
	      
        if(multi.getParameter("save1") != null){
           article.setRbook_pic(multi.getParameter("save1"));
            if(multi.getFilesystemName("rbook_pic") != null){
               article.setRbook_pic(multi.getFilesystemName("rbook_pic"));
            }
         }else{
            article.setRbook_pic(multi.getFilesystemName("rbook_pic"));
         }    
     
     Enumeration<String>fileNames =multi.getFileNames();
     if(fileNames.hasMoreElements()){
        String fileName = fileNames.nextElement();
        String updateFile = multi.getFilesystemName(fileName);
        
     	
	}
		
		
		
     
		
		/*
		 if(name !=null){//null이 아닐경우 해당 파일이 들어감
				article.setRbook_pic(name);
			}else{//<input type="hidden" name="save1" value="${arcticle.rbook_pic}">
				article.setRbook_pic(multi.getParameter("save1"));
			}
		 */
		
		ResellbookDao dbPro = ResellbookDao.getInstance();
		dbPro.reUpdateArticle(article);
		
		request.setAttribute("pageNum", pageNum);
		return "/d_resell/reUpdatePro.jsp";
	}
}

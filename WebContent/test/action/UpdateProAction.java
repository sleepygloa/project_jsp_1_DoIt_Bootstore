package ch19.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ch12.board.BoardDataBean;
import ch12.board.BoardDBBean;

public class UpdateProAction implements CommandAction {

    public String requestPro( HttpServletRequest request,
        HttpServletResponse response) throws Throwable {

        request.setCharacterEncoding("euc-kr");//한글 인코딩
		String path=request.getRealPath("save");  // 서버상의 업로드 폴더 경로
		int size=1024*1024*5; // 5MB
		String enc = "euc-kr";
		DefaultFileRenamePolicy df = new DefaultFileRenamePolicy(); // 덮어씌우기 방지
		MultipartRequest multi = new MultipartRequest(request,path,size,enc,df);

        String pageNum = request.getParameter("pageNum");

		BoardDataBean article = new BoardDataBean();
        article.setNum(Integer.parseInt(multi.getParameter("num")));
        article.setWriter(multi.getParameter("writer"));
        article.setEmail(multi.getParameter("email"));
        article.setSubject(multi.getParameter("subject"));
        article.setContent(multi.getParameter("content"));
        article.setPasswd(multi.getParameter("passwd"));
        
        String name = multi.getFilesystemName("save");
        if(name != null){
        	article.setSave(name);
        }else{
        	article.setSave(multi.getParameter("save1"));
        }
	    
		BoardDBBean dbPro = BoardDBBean.getInstance();
        int check = dbPro.updateArticle(article);

        request.setAttribute("pageNum", new Integer(pageNum));
        request.setAttribute("check", new Integer(check));

        return "/ch19/updatePro.jsp";
    }
}

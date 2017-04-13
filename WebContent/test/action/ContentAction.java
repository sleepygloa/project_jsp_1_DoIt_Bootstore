package ch19.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ch12.board.BoardDataBean;
import ch12.board.BoardDBBean;

public class ContentAction implements CommandAction {//湲��궡�슜 泥섎━

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
        
        int num = Integer.parseInt(request.getParameter("num"));//�빐�떦 湲�踰덊샇
        String pageNum = request.getParameter("pageNum");//�빐�떦 �럹�씠吏� 踰덊샇

        BoardDBBean dbPro = BoardDBBean.getInstance();//DB泥섎━
        BoardDataBean article =  dbPro.getArticle(num);//�빐�떦 湲�踰덊샇�뿉 ���븳 �빐�떦 �젅肄붾뱶
  
        //�빐�떦 酉곗뿉�꽌 �궗�슜�븷 �냽�꽦
        request.setAttribute("num", new Integer(num));
        request.setAttribute("pageNum", new Integer(pageNum));
        request.setAttribute("article", article);
    
		
        return "/ch19/content.jsp";//�빐�떦 酉�
    }
}

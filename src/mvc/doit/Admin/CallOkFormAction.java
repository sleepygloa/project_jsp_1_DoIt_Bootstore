package mvc.doit.AdminAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.ResellBean.ResellintroDao;
import mvc.doit.ResellBean.ResellintroDto;
import mvc.doit.SuperAction.SuperAction;

public class CallOkFormAction implements SuperAction{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int rbook_introno = Integer.parseInt(request.getParameter("rbook_introno").trim());//해당 글번호
        String pageNum = request.getParameter("pageNum");//해당 페이지 번호

        ResellintroDao dbPro = ResellintroDao.getInstance();//DB처리
        ResellintroDto article =  dbPro.getArticle(rbook_introno);//해당 글번호에 대한 해당 레코드
        
        //해당 뷰에서 사용할 속성
        request.setAttribute("rbook_introno", new Integer(rbook_introno));
        request.setAttribute("pageNum", new Integer(pageNum));
        request.setAttribute("article", article);
        
		return "/d_admin/callOkForm.jsp";
	}

}

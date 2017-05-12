package mvc.doit.CustomerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Customer.CustomerDao;
import mvc.doit.Customer.InquireDto;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;

public class InquireContentAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	request.setCharacterEncoding("utf-8");
	 	
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		String reply = request.getParameter("reply");
		String admin_reply = request.getParameter("admin_reply");
		String list = request.getParameter("list");
		String admin = request.getParameter("admin");
		
		LoginDao ldao = LoginDao.getInstance();
		LoginDto ldto = ldao.getMember(id);
		
		
	 	int c_ino = Integer.parseInt(request.getParameter("c_ino"));
	 	
	 	CustomerDao cdao = CustomerDao.getInstance();
	 	InquireDto idto = cdao.getArticle(c_ino);
	 	
	 	int ctype = idto.getC_itype();
	 	String c_itype = "";
	 	if(ctype == 0){
	 		c_itype = "온라인 중고서점";
	 	}else if(ctype == 1){
	 		c_itype = "도서관";
	 	}else if(ctype == 2){
	 		c_itype = "중고 직거래";
	 	}else if(ctype == 3){
	 		c_itype = "주문/주문변경";
	 	}else if(ctype == 4){
	 		c_itype = "배송";
	 	}else if(ctype == 5){
	 		c_itype = "회원";
	 	}
	 	
	 	  int ref=idto.getRef();
		  int re_step=idto.getRe_step();
		  int re_level=idto.getRe_level();

	  
		  request.setAttribute("c_ino", c_ino);
		  /*	 request.setAttribute("pageNum", pageNum);*/
		  
		  request.setAttribute("ref", ref);
		  request.setAttribute("re_step", re_step);
		  request.setAttribute("re_level", re_level);

	 	
	 	
	 	request.setAttribute("admin", admin);
	 	request.setAttribute("list", list);
	 	request.setAttribute("reply", reply);
	 	request.setAttribute("admin_reply", admin_reply);
	 	request.setAttribute("c_itype", c_itype);
	 	request.setAttribute("ldto", ldto);
	 	request.setAttribute("idto", idto);
	 	

	
	
	 	return "/d_customer/InquireContent.jsp";
	}	

}

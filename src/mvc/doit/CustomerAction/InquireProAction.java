package mvc.doit.CustomerAction;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Customer.CustomerDao;
import mvc.doit.Customer.InquireDto;
import mvc.doit.SuperAction.SuperAction;

public class InquireProAction implements SuperAction {
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 	request.setCharacterEncoding("utf-8");
		 	
			HttpSession session = request.getSession();
			int br_no = (int)session.getAttribute("memNo"); //회원 번호 
			
			String mem = request.getParameter("mem");
			
			int c_ino = Integer.parseInt(request.getParameter("c_ino"));
			
			String inquire = "Inquire_reply";
		 	
		 	InquireDto idto = new InquireDto();
		 	idto.setC_ino(Integer.parseInt(request.getParameter("c_ino")));
		 	if(mem.equals("user")){
		 		idto.setD_no(br_no);
		 	}else if(mem.equals("admin")){
		 		idto.setD_no(Integer.parseInt(request.getParameter("d_no")));
		 	}
		 	
		 	idto.setC_itype(Integer.parseInt(request.getParameter("c_itype")));
		 	idto.setC_isubject(request.getParameter("c_isubject"));
		 	
		 	String c_icontent = request.getParameter("c_icontent");
		 	c_icontent = c_icontent.replace("\r\n", "<br/>"); //엔터 -> 코드 저장
		 	idto.setC_icontent(c_icontent);
		 	
		 	idto.setRef(Integer.parseInt(request.getParameter("ref")));
		 	idto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		 	idto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		 	
		 	
		 	
		 	CustomerDao cdao = CustomerDao.getInstance();
		 	cdao.InquireInsert(idto, br_no,mem);
		 	
		 	
		 	//String re = "/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=wait";
		 	if(mem.equals("user")){
		 	response.sendRedirect("/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=wait");
		 	}else if(mem.equals("admin")){
		 		response.sendRedirect("/DoIt/d_customer/customer_InquireList.do?list=admin&reply=finish");
		 	}
		 	
		 	return "/d_customer/customer_Inquire.jsp";
		}


}

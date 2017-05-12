package mvc.doit.AdminAction;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.SuperAction.SuperAction;

public class OnRegistFormAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception{
			
		//책이름을 기준으로 DB와 비교하여 같은 이름이 있다면, 책의 정보를 불러와 줍니다.
		String d_bname = request.getParameter("d_bname");
		
		//Db등록시 판매자이름을 저장하기 위해 판매자 정보를 받아옵니
		String d_id = request.getParameter("d_id");
		
		//검수코드를 받아와 검수코드를 받아와 검수에 대한 정보를 저장합니다.
		int d_icode = Integer.parseInt(request.getParameter("d_icode"));
		
		
		
		
		return "/d_admin/onRegistForm.jsp";
	}

}

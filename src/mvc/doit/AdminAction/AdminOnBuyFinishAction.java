package mvc.doit.AdminAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Delivery.DeliveryDao;
import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class AdminOnBuyFinishAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	request.setCharacterEncoding("utf-8");
	 	
	 	int d_bcode = Integer.parseInt(request.getParameter("d_bcode"));

	 	int delivery = Integer.parseInt(request.getParameter("delivery"));
	 	
	 	DeliveryDao dao = DeliveryDao.getInstance();
	 	
//---- 배송관련 
	 	int d_bdelivery =-1;
	 	if(delivery == 20){
	 		dao.Admin_OnBuyBook_finish(d_bcode);
	 		d_bdelivery = 20;
	 	}else if(delivery == 21){
	 		dao.Admin_OnBuyBook_delivertStart(d_bcode);
	 		d_bdelivery = 21;
	 	}else if(delivery == 22){
	 		dao.Admin_OnBuyBook_delivertEnd(d_bcode);
	 		d_bdelivery = 22;
	 	}else if(delivery == 24){
	 		dao.Admin_OnBuyBook_CancelCheck(d_bcode);
	 		d_bdelivery = 24;
	 	}
	 	
	 	OnDao odao = OnDao.getInstance();
		//---- 회원의 회원의 등급을 파악하고, 유지 또는 등업 향상을 시킵니다.
	 	String Check = "d_bcode";
	 	String id = null;
	 	String userGradeCheck = odao.getUserSellPurchaseCountToGrade(d_bcode, id, Check); //d_bcode로 방금 등록한 책의 정보를 불러옴
	//책코드 d_bcode로 회원의 아이디를 불러옵니다.
	 	OnBookDto dto = new OnBookDto();

	 	dto = odao.getOnBookArticle(d_bcode, Check);
	 	String d_id = dto.getD_id();
	 	
	 	
	 	
	 	
	 	request.setAttribute("d_bdelivery", d_bdelivery);
	 	request.setAttribute("userGradeCheck", userGradeCheck);
	 	request.setAttribute("d_id", d_id);
	
	 	
	return "/d_admin/adminOnBuyFinish.jsp";
}

}

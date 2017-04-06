package mvc.doit.Admin;

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
	 	if(delivery == 0){
	 		dao.Admin_OnBuyBook_finish(d_bcode);
	 		d_bdelivery = 0;
	 	}else if(delivery == 1){
	 		dao.Admin_OnBuyBook_delivertStart(d_bcode);
	 		d_bdelivery = 1;
	 	}else if(delivery == 2){
	 		dao.Admin_OnBuyBook_delivertEnd(d_bcode);
	 		d_bdelivery = 2;
	 	}else if(delivery == 4){
	 		dao.Admin_OnBuyBook_CancelCheck(d_bcode);
	 		d_bdelivery = 4;
	 	}
	 	
	 	OnDao odao = OnDao.getInstance();
		//---- 회원의 회원의 등급을 파악하고, 유지 또는 등업 향상을 시킵니다.
	 	String Check = "d_bcode";
	 	String id = null;
	 	String userGradeCheck = odao.getUserSellPurchaseCountToGrade(d_bcode, id, Check); //d_bcode로 방금 등록한 책의 정보를 불러옴
	//책코드 d_bcode로 회원의 아이디를 불러옵니다.
	 	OnBookDto dto = new OnBookDto();
	 	Check = "";  //오류가 날씨 전페이지에서 '판매완료버튼'에 회원 아이디 받아올것
	 	dto = odao.getOnBookArticle(d_bcode, Check);
	 	String d_id = dto.getD_id();
	 	
	 	
	 	
	 	
	 	request.setAttribute("d_bdelivery", d_bdelivery);
	 	request.setAttribute("userGradeCheck", userGradeCheck);
	 	request.setAttribute("d_id", d_id);
	
	 	
	return "/d_admin/adminOnBuyFinish.jsp";
}

}

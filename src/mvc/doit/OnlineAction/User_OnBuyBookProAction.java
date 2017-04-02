package mvc.doit.OnlineAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.doit.Delivery.DeliveryDto;
import mvc.doit.Login.LoginDto;
import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class User_OnBuyBookProAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	request.setCharacterEncoding("utf-8");
	 	
	 	int d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
	 	String d_id = request.getParameter("d_id");
	 	
	 	DeliveryDto Ddto = new DeliveryDto();
	 	Ddto.setD_bcode(Integer.parseInt(request.getParameter("d_bcode")));
	 	Ddto.setD_bdelibery(0);
	 	Ddto.setD_bbuyer(request.getParameter("d_id"));
	 	Ddto.setD_brecipient(request.getParameter("d_brecipient"));
	 	Ddto.setD_brequested(request.getParameter("d_brequested"));
	 	
	 	LoginDto LogDto = new LoginDto();
	 	LogDto.setD_addr(request.getParameter("d_addr"));
	 	LogDto.setUser_phone1(request.getParameter("user_phone1"));
	 	LogDto.setUser_phone2(request.getParameter("user_phone2"));
	 	LogDto.setUser_phone3(request.getParameter("user_phone3"));
	 	
	 	OnDao dao = OnDao.getInstance();
	 	dao.User_onBuyBook_insert(Ddto, LogDto, d_bcode, d_id);
	 	return "/d_online/user_onBuyBookPro.jsp";
	}

}

package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Account.AcDao;
import mvc.doit.Account.AcDto;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;


public class MyPageInfoAction implements SuperAction{
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		String d_id = (String)session.getAttribute("memId"); //세션 저장
		
		LoginDao manager = LoginDao.getInstance();
		LoginDto lto = manager.getMember(d_id); //Dto 저장
	    
	//---- 계좌등록 신청요청이 있을 경우 --------

		if(d_id == null){
			return "/d_login/login.jsp";
		}
		int d_no = lto.getD_no();
		AcDto adto = null;
		AcDao adao = AcDao.getInstance();
		
		//---- 기존의 계좌를 불러옵니다.
		adto = adao.getAccount(d_no);

		//---- 잔액만들기 위해 d_log table에서 d_ldealmoney를 합산 합니다.
		int d_lsummoney = 0;		
		d_lsummoney = adao.getAccountSumMoney(d_no);

	    //------------------------------------ 고유 코드 임시 생성 코드 끝 -----------------------------------------------------//
		
	    //어트리뷰트 저장
	    request.setAttribute("lto", lto);
	    request.setAttribute("adto", adto);
	    request.setAttribute("d_no", d_no);
	    request.setAttribute("d_lsummoney", d_lsummoney);
		
		return "/d_login/my_info.jsp";
	}
	
}

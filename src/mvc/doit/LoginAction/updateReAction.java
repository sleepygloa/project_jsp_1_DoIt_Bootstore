package mvc.doit.LoginAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;

public class updateReAction implements SuperAction{
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		//세션 아이디 받기
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		//입력 받은 비밀번호
		String pwCheck = request.getParameter("d_pass");
		
		LoginDao lao = LoginDao.getInstance();
		LoginDto Dto = lao.getMember(id);
		
		//DB 비밀번호 받기
		String se_pass = Dto.getD_pass();
		
		if(se_pass.equals(pwCheck)){
			LoginDto dto = new LoginDto();
			dto.setD_pic(request.getParameter("thumb_pic"));
			dto.setD_pass(request.getParameter("user_pw"));
			dto.setUser_phone1(request.getParameter("user_phone"));
			dto.setUser_birth1(request.getParameter("user_birth"));
			dto.setD_addr(request.getParameter("addr"));
			dto.setUser_mail1(request.getParameter("user_mail"));
			
			lao.upDate(dto,id);
		}
		
		
		return "/d_login/myInfo.do";
	}
	
}

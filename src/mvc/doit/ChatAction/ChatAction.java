package mvc.doit.ChatAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.SuperAction.SuperAction;

public class ChatAction implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 저장
		String cont = request.getParameter("cont"); //입력한 내용
		HttpSession session = request.getSession();
		String memName = (String)session.getAttribute("memName"); //이름
		String memPic = (String)session.getAttribute("memPic"); //사진
		if(memPic == null){
			memPic = "ma_img.jpg";
		}
		
		
		//객체 생성
		ChatDao cdao = ChatDao.getInstance();
		cdao.insMess(cont, memName, memPic);

		return "/d_manage/manager.do";
	}

}

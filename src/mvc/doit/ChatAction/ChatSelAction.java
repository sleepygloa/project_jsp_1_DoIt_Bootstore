package mvc.doit.ChatAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.SuperAction.SuperAction;

public class ChatSelAction implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
	
		//객체 생성
		ChatDao cdao = ChatDao.getInstance();
		
		List chatList = cdao.getChat();
		request.setAttribute("chatList", chatList);
		
		return "/d_manage/chat_cont.jsp";
	}

}

package mvc.doit.RentAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Cart.CartDao;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.Rent.RentDao;
import mvc.doit.Rent.RentDto;
import mvc.doit.SuperAction.SuperAction;

public class Rent_detailAction implements SuperAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");

		/*  도서 번호Parameter  */
		String br_no = request.getParameter("br_no");

		/*  객체화 내용   */
		RentDao rdo = RentDao.getInstance(); //DAO 객체 생성
		LoginDao ldo = LoginDao.getInstance(); //회원 정보 dao객체
		CartDao cdao = CartDao.getInstance(); //장바구니 dao 객체
		
		//상세보기 기본 내용 출력
		RentDto rto = rdo.getDetail(br_no);
		request.setAttribute("rto", rto);
		
		//대기자 숫자 : 사람 수
		int personC = cdao.getPerson(rto.getBr_code());
		request.setAttribute("personC", personC);
		
		//장르번호가 같은 도서 리스트 출력
		List jangList = rdo.getJang(rto.getD_bno(), br_no);
		request.setAttribute("jangList", jangList);
		
		
		//기부자 정보 출력
		String asd = ldo.getMemNo(rto.getBr_don());
		LoginDto login_dto = ldo.getMember(asd);
		if(login_dto == null){
			LoginDto login_dto1 = new LoginDto();
			login_dto1.setD_name("관리자");
			login_dto1.setD_mech_grade(10);
			login_dto1.setUser_phone1("1600");
			login_dto1.setUser_phone2("2222");
			login_dto1.setUser_phone3("[ 관리자 ]");
			request.setAttribute("login_dto1", login_dto1);
		}
		request.setAttribute("login_dto", login_dto);
		

		//기부자가 기부한 다른 도서 출력
		//List gibuList = rdo.getGibu(login_dto.getD_no());
		List gibuList = null;
		if(login_dto != null){
			gibuList = rdo.getGibu(login_dto.getD_no());
		}else if(login_dto == null){
			gibuList = rdo.getGibu(0);
		}
		request.setAttribute("gibuList", gibuList);
		
		
		
		//=====//댓글 불러오기 1========================================= 
		  
	      String pageNum = request.getParameter("pageNum");
	      
	      if(pageNum==null){
	         pageNum="1";
	      }
	      
	      int pageSize = 6; //댓글개수 6개
	      int currentPage = Integer.parseInt(pageNum);
	      int startRow = (currentPage-1)*pageSize+1;
	       int endRow = currentPage * pageSize;
	       int count = 0;
	       
	   //=====//댓글 불러오기 2=========================================   
	      List re_List = null;
	      count = rdo.getReplyCount(br_no);//전체 댓글의 수
	      
	      if (count > 0) {
	         re_List = rdo.getReply(br_no,startRow,endRow);//페이지 글 목록
	      }
	      
	      request.setAttribute("pageNum", new Integer(pageNum));
	      request.setAttribute("currentPage", new Integer(currentPage));
	      request.setAttribute("startRow", new Integer(startRow));
	      request.setAttribute("endRow", new Integer(endRow));
	      request.setAttribute("count", new Integer(count));
	      request.setAttribute("pageSize", new Integer(pageSize));
	      request.setAttribute("re_List", re_List);
	      
	      
		
		return "/d_rent/detail_cont.jsp";
	}//execute 메서드 구현 끝
}

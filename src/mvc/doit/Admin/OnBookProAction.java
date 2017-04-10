package mvc.doit.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.doit.Account.AcDao;
import mvc.doit.Login.LoginDao;
import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnBookIntroDto;
import mvc.doit.Online.OnDao;
import mvc.doit.SuperAction.SuperAction;

public class OnBookProAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
//---- 변수 설정 ----
		String path = request.getRealPath("d_bpic"); 			
		int size = 1024*1024*5;		//5mb
		String enc = "UTF-8";		
		DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();				
		MultipartRequest multi = new MultipartRequest(request, path, size, enc, df);
		//---- d_bcode ----
		int d_bcode = Integer.parseInt(multi.getParameter("d_bcode"));
				
		OnBookDto dto = new OnBookDto();
	 	dto.setD_bname(multi.getParameter("d_bname"));
	 	dto.setD_bgrade(multi.getParameter("d_bgrade"));
	 	dto.setD_bpublisher(multi.getParameter("d_bpublisher"));
	 	dto.setD_bauthor(multi.getParameter("d_bauthor"));
	 	dto.setD_bgenre(multi.getParameter("d_bgenre"));
	 	dto.setD_bgenre2(multi.getParameter("d_bgenre2"));
	 	dto.setD_blocation(multi.getParameter("d_blocation"));
	 	dto.setD_bregistdate(multi.getParameter("d_bregistdate"));
	 	dto.setD_bpic(multi.getParameter("d_bpic"));
	 	dto.setD_bvalue(Integer.parseInt(multi.getParameter("d_bvalue")));
	 	dto.setD_bsellvalue(Integer.parseInt(multi.getParameter("d_bsellvalue")));
	 	dto.setD_bpurchasevalue(Integer.parseInt(multi.getParameter("d_bpurchasevalue")));
	 	dto.setD_icode(Integer.parseInt(multi.getParameter("d_icode")));
	 	//---- d_onBook table에 등록된 책 초기정보에 검수코드를 입력----
	 	OnDao dao = OnDao.getInstance();
	 	dao.Admin_OnBook_Update(dto, d_bcode);
	 	
	//---- 등급 관리---- 상황2개중 1개(책등록, 회원책구매의 배송완료), 회원의 구매와 판매 count를 비교하여, 등급 조정 ------------------------------------
	 	String Check = "d_bcode";
	 	String id = null;
	 	String userGradeCheck = dao.getUserSellPurchaseCountToGrade(d_bcode, id, Check); //d_bcode로 방금 등록한 책의 정보를 불러옴

	 	String d_id = multi.getParameter("d_id");
	//---- 계좌 관리 ----d_log table에  관련코드(d_bcode(원래는 d_bdeliverycode이지만, 회원책판매는 1개씩판매한다는 가정))로 log 레코드 1개를 생성시킵니다.-----------------------------
	 	AcDao adao = AcDao.getInstance();
	 	//결제 결과가 0인 d_log 레코드한줄 추가하는 dao
	 	int d_bsellvalue = Integer.parseInt(multi.getParameter("d_bsellvalue"));
	 	adao.insertAccountLog(d_id, d_bcode, d_bsellvalue);
	 	
	 	request.setAttribute("d_bcode", d_bcode);
	 	request.setAttribute("userGradeCheck",userGradeCheck);
	 	request.setAttribute("d_id", d_id);

		return "/d_admin/onBookPro.jsp";
	}

}

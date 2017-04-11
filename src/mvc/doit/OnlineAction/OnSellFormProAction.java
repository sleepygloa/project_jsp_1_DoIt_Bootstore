package mvc.doit.OnlineAction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.doit.Delivery.DeliveryDao;
import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnBookIntroDto;
import mvc.doit.Online.OnDao;
import mvc.doit.Online.OnSellListDto;
import mvc.doit.SuperAction.SuperAction;

public class OnSellFormProAction implements SuperAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
//1. 변수저장----------------------------------------------------------------------------------		
	 	request.setCharacterEncoding("UTF-8");
	 	
	 	//로그인한 회원의 회원코드를 불러옵니다.
	 	HttpSession session = request.getSession();
	 	String d_id = (String)session.getAttribute("memId");
	 	
	 	
// ---- 변수 설정 ----	 	
 	String path = request.getRealPath("d_bpic"); 			
	int size = 1024*1024*5;		//5mb
	String enc = "UTF-8";		
	DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();				
	MultipartRequest multi = new MultipartRequest(request, path, size, enc, df);
		
		//책정보
	String d_bpic = "";
	String d_bname = multi.getParameter("d_bname");
	String d_bpublisher = multi.getParameter("d_bpublisher");
	String d_bauthor = multi.getParameter("d_bauthor");
	String d_bgenre = multi.getParameter("d_bgenre");
	String d_bgenre2 = multi.getParameter("d_bgenre2");
	String d_blocation = multi.getParameter("d_blocation");
	String d_bregistdate = multi.getParameter("d_bregistdate");
	if(multi.getParameter("d_bpic_re") != null){
		d_bpic = multi.getParameter("d_bpic_re");
	}else{
		d_bpic = multi.getFilesystemName("d_bpic");
	}
	int d_bvalue = Integer.parseInt(multi.getParameter("d_bvalue"));
	Timestamp d_sdate = new Timestamp(System.currentTimeMillis());
		//목차, 설명
 	String d_norder = multi.getParameter("d_norder");
 	d_norder = d_norder.replace("\r\n", "<br/>"); //엔터 -> 코드 저장
 	
 	String d_nintro = multi.getParameter("d_nintro");
 	d_nintro = d_nintro.replace("\r\n", "<br/>"); //엔터 -> 코드 저장
		
 	
//---- 2.Dto 저장 --------------------------------------------------------------------------------------------------- 	
	//dto형으로 d_onBook 책전체 DB에 책 기본정보를 입력, 검수에 대한 정보는 초기값 지정
	OnBookDto onbookdto = new OnBookDto();
	onbookdto.setD_id(d_id);
	onbookdto.setD_bname(d_bname);
	onbookdto.setD_bgrade("c"); //초기화
	onbookdto.setD_bpublisher(d_bpublisher);
	onbookdto.setD_bauthor(d_bauthor);
	onbookdto.setD_bgenre(d_bgenre);
	onbookdto.setD_bgenre2(d_bgenre2);
	onbookdto.setD_blocation(d_blocation);
	onbookdto.setD_bregistdate(d_bregistdate);//초기화
	onbookdto.setD_bpic(d_bpic); //사진
	onbookdto.setD_bcount(1);//초기화
	onbookdto.setD_bvalue(d_bvalue); //가격
	onbookdto.setD_bsellvalue(0); //초기화
	onbookdto.setD_bpurchasevalue(0); //초기화
	onbookdto.setD_icode(0); //초기화
	onbookdto.setD_bgradevalue(0); //초기화
	//onbookintrodto 에도 목차와 설명을 넣어줍니다.
	OnBookIntroDto obiDto = new OnBookIntroDto();
	obiDto.setD_norder(d_norder);
	obiDto.setD_nintro(d_nintro);
	
//3.dao 실행---------------------------------------------------------------------------------------------------	
	//dao 연결
		OnDao dao = OnDao.getInstance();		
		//dto를 이용. 책전체 DB에 레코드 1행 추가, 다시 책코드 반환  //D_bcode를 이용하여 다른 테이블과 연결을 함.
		int d_bcode = dao.setD_bcode(d_id, onbookdto);
		
	//배송 코드 생성
		DeliveryDao ddao = DeliveryDao.getInstance();
		int d_bdeliverycode = ddao.setD_bUserdeliverycode(d_id, d_bcode);
	
	//판매 신청 d_onSellLIst table 생성. 레코드 1행 추가
		OnSellListDto onSellListDto = new OnSellListDto();
		onSellListDto.setD_bcode(d_bcode);
		onSellListDto.setD_id(d_id);
		onSellListDto.setD_bdeliverycode(d_bdeliverycode);
		onSellListDto.setD_sdate(d_sdate);
		
		dao.onSellList(onSellListDto);
		
		//목차, 설명 table 에 dto.d_bcode와 동일한  d_bcode의 레코드1행추가
		dao.Admin_OnBookIntro_insert(obiDto, d_bcode);
	
		request.setAttribute("path", path);
		request.setAttribute("d_bpic", d_bpic);
		
		request.setAttribute("d_norder", d_norder);
		request.setAttribute("obiDto", obiDto);
		
		

	
	return "/d_online/onSellFormPro.jsp";
	}

}

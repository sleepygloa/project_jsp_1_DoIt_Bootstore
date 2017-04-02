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
	 	
	 	String path = request.getRealPath("d_bpic"); 			
		int size = 1024*1024*5;		//5mb
		String enc = "UTF-8";		
		DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();				
		MultipartRequest multi = new MultipartRequest(request, path, size, enc, df);
		

		String d_bpic = "";
	//넘어오는 값들을 여러 곳에 사용하기 위해 변수로 받아줍니다.
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
	//목차, 설명도 변수로 저장
 	String d_norder = multi.getParameter("d_norder");
 	d_norder = d_norder.replace("\r\n", "<br/>");
 	
 	String d_nintro = multi.getParameter("d_nintro");
 	d_nintro = d_nintro.replace("\r\n", "<br/>");
		
	//여기서는 한번에 3개의 테이블을 조정합니다.
	//1.d_onBook 테이블에서 책코드만 부여합니다.
	//2.d_bdelivery 테이블에서 배송코드만 부여합니다.
	//3.d_onSellList 테이블에서 전체리스트를 작성해줍니다.

 	
//2.Dto 저장 --------------------------------------------------------------------------------------------------- 	
	//onbookdto에 넣어 DB에 저장하지만, 검수에 필요한 값과 배송에 필요한 값은 초기화를 시켜줍니다.
	OnBookDto onbookdto = new OnBookDto();
	onbookdto.setD_id(d_id);
	onbookdto.setD_bname(d_bname);
	onbookdto.setD_bgrade("0"); //초기화
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
	onbookdto.setD_bdeliverycode(0); //초기화
	//onbookintrodto 에도 목차와 설명을 넣어줍니다.
	OnBookIntroDto obiDto = new OnBookIntroDto();
	obiDto.setD_norder(d_norder);
	obiDto.setD_nintro(d_nintro);
	
//3.dao 실행---------------------------------------------------------------------------------------------------	
	//dao 연결
		OnDao dao = OnDao.getInstance();		
		//책 전체 DB에 레코드를 추가, 책코드부여와 전달된 값을 삽입하며, 다시 책코드를 반환합니다. //D_bcode를 이용하여 다른 테이블과 연결을 함.
		int d_bcode = dao.setD_bcode(d_id, onbookdto);
		
		//배송코드, 배송테이블에서 세레코드를 만들어야함, 배송코드만 제외한 나머비는 기본값.
		
//		DeliveryDao deliveryManager = DeliveryDao.getInstance();
//		int d_bdeliverycode = deliveryManager.setD_bdeliverycode(d_id, d_bcode);
		
	//(위의)3.d_onSellList 테이블에서 전체리스트를 작성해줍니다.
		OnSellListDto onSellListDto = new OnSellListDto();
		onSellListDto.setD_bcode(d_bcode);
		onSellListDto.setD_id(d_id);
		onSellListDto.setD_bdeliverycode(0);
		onSellListDto.setD_sdate(d_sdate);
		
		dao.onSellList(onSellListDto);
		
		//목차와 설명 테이블의 레코드를 추가하며, no, d_norder, d_nintro, d_bcode, d_ndate를 넣어줍니다.
		dao.Admin_OnBookIntro_insert(obiDto, d_bcode);
	
		request.setAttribute("path", path);
		request.setAttribute("d_bpic", d_bpic);
		
		request.setAttribute("d_norder", d_norder);
		request.setAttribute("obiDto", obiDto);
		
		

	
	return "/d_online/onSellFormPro.jsp";
	}

}

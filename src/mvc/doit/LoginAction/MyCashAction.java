package mvc.doit.LoginAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Account.AcDao;
import mvc.doit.Account.AcDto;
import mvc.doit.Login.LoginDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.SuperAction.SuperAction;

public class MyCashAction implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//캐쉬 내역 세션
		HttpSession session = request.getSession();
		String d_id = (String)session.getAttribute("memId"); //세션 저장
		
		LoginDao manager = LoginDao.getInstance();
		LoginDto ldto = manager.getMember(d_id); //Dto 저장
		request.setAttribute("ldto", ldto); //회원정보 저장

		int d_no = ldto.getD_no();
		AcDao adao = AcDao.getInstance();

		
///------------------------------------------------------------------------- D_cash 입력 , 출금 -------------------------------------------------------------------------------------------//		
		
		//입금 출금을 하기 위해 금액을 받아옴.
		int d_acMyMoney = 0; //입금액, 출금액
		if(request.getParameter("d_acMyMoney") != null){
			d_acMyMoney = Integer.parseInt(request.getParameter("d_acMyMoney"));
		}
		
		int d_acRequest = 0;  //입금,출금 구분
		if(request.getParameter("d_acRequest") != null){
			d_acRequest = Integer.parseInt(request.getParameter("d_acRequest"));
		}
		
		
		//---- 계좌가 없을 경우 받는 요청(1)에 따라 신규 계좌를 생성합니다.
		if(adao.getAccount(d_no) != null){
		/*
			//---- 계좌 등록 요청이 있을 경우 신규 계좌를 만들어줍니다.
			if(d_acRequest == 1){
				adao.insAccount(d_no);//계좌추가, 아이디생성시 만들어주는 코드이지만, 관리를 위해 넣어둠.
			}
			adto = adao.getAccount(d_no); 
		}else{
		*/
			//---- 계좌 충전하기 요청(2)에 따라 계좌에 원하는 수만큼 돈을 증가 시킵니다. 계좌 출금하기 요청(3)에 따라 계좌의 원하는 수만큼 돈을 감소시킵니다.		
			if(d_acRequest == 2 || d_acRequest == 3){
					adao.MyMoneyToAccout(d_acMyMoney, d_no, d_acRequest); //입출금
			}
			
			
		}	
		
///------------------------------------------------------------------------- D_cash 입력 , 출금 끝-------------------------------------------------------------------------------------------//		
		
		
//-------------------------------------------------- 최종적인  dto를 반환합니다. : 자신의 계좌 출력 -------------------------------------------------------//
		AcDto adto = null;
		adto = adao.getAccount(d_no);
		request.setAttribute("adto", adto);
		
//-------------------------------------------------- 최종적인  dto를 반환합니다. : 자신의 계좌 출력 -------------------------------------------------------//
		
		
//------------------------------------------------- 거래 내역을 확인하기 위해 List 구현 합니다 -------------------------------------------------------------------------------------------//
		String pageNum = request.getParameter("pageNum");//페이지 번호
        if (pageNum == null) {
            pageNum = "1"; //1페이지당 20권의 책 보여줌
        }
        
        int pageSize = 10;//한 페이지의 글의 개수
        int currentPage = Integer.parseInt(pageNum); //페이지번호를 Int 로 저장한 값
        int startRow = (currentPage - 1) * pageSize + 1;//한 페이지의 시작글 번호, 시작 글 번호 1
        int endRow = currentPage * pageSize;//한 페이지의 마지막 글번호, 마지막 등록글번호 20
        int count = 0;
        int number = 1;
        
        count = adao.dealSituationCount(d_no); //로그카운트 
		
        //게시글이 pagesize 보다 클때, 즉 1페이지보다 수량이 많을때 페이지 번호 출력
        if (count > 0) {
            int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
    		 
            int startPage = (int)(currentPage/10)*10+1;
    		int pageBlock=10;
            int endPage = startPage + pageBlock-1;
            if (endPage > pageCount) endPage = pageCount;
            
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("startPage", startPage);
            request.setAttribute("endPage", endPage);
            
        }        
        
		number=count-(currentPage-1)*pageSize;//글목록에 표시할 글번호
		request.setAttribute("number", number); //글번호 저장
        
		
		//----------------로그 리스트 저장
		List accountList = null;
		if(count > 0){ 
			String colm = request.getParameter("colm"); //컬럼이름
			if(colm == null){
				colm = "d_lsender";
			}
			
			String gua = request.getParameter("gua");//불러올 구분값
			int guaa = 3;
			if(gua != null){
				guaa = Integer.parseInt(gua);
			}
			
			String inout = request.getParameter("inout"); //입출금 내역
			int inoutt = 1; //입금
			if(inout != null){
				inoutt = Integer.parseInt(inout);
			}
			
			accountList = adao.getLog(d_no, colm, guaa, inoutt ,startRow, endRow);
			
			//계좌 업데이트
			if(d_acRequest == 2){ //입금
				adao.upTMon(d_acMyMoney, d_no);
			}else{
				adao.upTMon(-d_acMyMoney, d_no);
			}
			
		}
		
		
		request.setAttribute("accountList", accountList); //로그 리스트


		
		return "/d_login/my_cash.jsp";
	}
	
}

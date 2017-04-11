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

public class MyAccountListAction implements SuperAction {
	public String execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String d_id = (String)session.getAttribute("memId"); //세션 저장
		
		LoginDao manager = LoginDao.getInstance();
		LoginDto ldto = manager.getMember(d_id); //Dto 저장
		
		
		int d_no = ldto.getD_no();
		AcDto adto = null;
		AcDao adao = AcDao.getInstance();
		
		//입금 출금을 하기 위해 금액을 받아옴.
		int d_acMyMoney = 0;
		int d_acRequest = 0;
		if(request.getParameter("d_acMyMoney") != null){
			d_acMyMoney = Integer.parseInt(request.getParameter("d_acMyMoney"));
		}
		if(request.getParameter("d_acRequest") != null){
			d_acRequest = Integer.parseInt(request.getParameter("d_acRequest"));
		}
		
		
		//---- 계좌가 없을 경우 받는 요청(1)에 따라 신규 계좌를 생성합니다.
		if(adao.getAccount(d_no) == null){
			
			//---- 계좌 등록 요청이 있을 경우 신규 계좌를 만들어줍니다.
			if(d_acRequest == 1){
				adao.insAccount(d_no);

			}
			adto = adao.getAccount(d_no);
		}else{
			//---- 계좌 충전하기 요청(2)에 따라 계좌에 원하는 수만큼 돈을 증가 시킵니다. 계좌 출금하기 요청(3)에 따라 계좌의 원하는 수만큼 돈을 감소시킵니다.		
			if(d_acRequest == 2 || d_acRequest == 3){
					adao.MyMoneyToAccout(d_acMyMoney, d_no, d_acRequest);
			}else{}	
			//---- 최종적인  dto를 반환합니다.
			adto = adao.getAccount(d_no);
		}



		//---- 잔액만들기 위해 d_log table에서 d_ldealmoney를 합산 합니다.
		int d_lsummoney = 0;		
		d_lsummoney = adao.getAccountSumMoney(d_no);
        if(d_lsummoney <= 0){
       	 d_lsummoney = 0;
        }
		
		
		//---- 거래 내역을 확인하기 위해 List 구현 합니다 ----------------------
		List accountList = null;
	

		String pageNum = request.getParameter("pageNum");//페이지 번호
        if (pageNum == null) {
            pageNum = "1"; //1페이지당 20권의 책 보여줌
        }

        int pageSize = 30;//한 페이지의 글의 개수
        int currentPage = Integer.parseInt(pageNum); //페이지번호를 Int 로 저장한 값
        int startRow = (currentPage - 1) * pageSize + 1;//한 페이지의 시작글 번호, 시작 글 번호 1
        int endRow = currentPage * pageSize;//한 페이지의 마지막 글번호, 마지막 등록글번호 20
        int count = 0;
        int number = 1;
        
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
        
        
		count = adao.dealSituationCount(d_no);
		if(count > 0){
			accountList = adao.dealSituation(d_no, startRow, endRow);
		}


	    //어트리뷰트 저장
	    request.setAttribute("ldto", ldto);
	    request.setAttribute("adto", adto);
		request.setAttribute("d_lsummoney", d_lsummoney);
		request.setAttribute("accountList", accountList);
		request.setAttribute("number", number);


		
	return "/d_login/myAccountList.jsp";
	}
}

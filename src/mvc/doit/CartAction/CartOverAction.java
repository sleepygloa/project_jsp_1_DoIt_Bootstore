package mvc.doit.CartAction;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Account.AcDao;
import mvc.doit.Cart.CartDao;
import mvc.doit.Rent.RentDao;
import mvc.doit.SuperAction.SuperAction;

public class CartOverAction implements SuperAction{

   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      //한글 인코딩
      request.setCharacterEncoding("UTF-8");
      
      //파라미터 저장
      String br_code = request.getParameter("br_code"); //해당 책 코드
      HttpSession session = request.getSession();
      int br_no = (int)session.getAttribute("memNo"); //회원번호
      String br_n = Integer.toString(br_no);
      int br_o = Integer.parseInt(request.getParameter("br_no"));
      
      //객체 구성
      CartDao cdao = CartDao.getInstance(); //카트 dao
      RentDao rdao = RentDao.getInstance(); //도서관 서비스 Dao
      AcDao adao = AcDao.getInstance(); //계좌, 로그 Dao
      
      //객체 생성
      List getList = null; //리스트 객체 생성
      
      //해당 책 코드의 상태 불러오기
      getList = cdao.getOver(br_code); //배송상태, 최초 날짜, 대기자명단
      
      //배송상태 저장
      int deli_info1 = (int)getList.get(0);
      
      //대기자 명단에서 첫번쨰 출력
      String firstMan = cdao.getFirstM(br_code);
      
      boolean deli = false;
      deli = cdao.checkDeli(br_no);
      
      //배송중 이전상태 , 첫번째 회원인가
      if(deli_info1 == 5 || deli_info1 < 2 ){
         if(firstMan.equals(br_n)){
            if(deli){      //대
            cdao.OverDue(br_code);//대기자 1번 제거
            cdao.delCode(br_code, br_no);//해당 회원의 해당 책코드 제거
            adao.inLog(br_no, br_o, br_code, 0, 0);
            }else if(!deli){
            	adao.getDate(br_code);   //대여날짜
            	adao.comDate(br_code);   //연체일
                long money=adao.getMoney(br_no, br_code); //연체금액
                int cash=adao.getCash(br_no, br_code);  //연체금액을 뺀 금액
                if(cash>0){
                	adao.getAcco(br_no, br_code);    //관리자 계좌
                	cdao.OverDue(br_code);//대기자 1번 제거
                	cdao.delCode(br_code, br_no);//해당 회원의 해당 책코드 제거
                	adao.inLog(br_no, br_o, br_code, 1, money);
                  }else{
                     System.out.println("돈충전해라");
                  }
            }
         }
      }
      
        

      
      request.setAttribute("cols", "dr_rent");
      
      
      return "/d_login/myList.do";
   }

   
}
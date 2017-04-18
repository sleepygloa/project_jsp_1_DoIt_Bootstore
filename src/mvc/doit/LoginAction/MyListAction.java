package mvc.doit.LoginAction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Cart.CartDao;
import mvc.doit.Login.LoginDto;
import mvc.doit.Online.OnDao;
import mvc.doit.ResellBean.BidbookDao;
import mvc.doit.ResellBean.ResellbookDao;
import mvc.doit.SuperAction.SuperAction;

public class MyListAction implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//현재 날짜 불러오기
		Date date = new Date();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy. MM. dd");
		String das = simpleDate.format(date);
		request.setAttribute("SimpleDate", das);
		
		//구분 값 불러오기
		String col = request.getParameter("cols");
		String srt = ""; //나올 페이지 이름
		
		//세션 값 가져오기
		HttpSession session = request.getSession();
		int br_no = (int)session.getAttribute("memNo"); //회원 번호 
		
		//객체 생성
		CartDao cdo = CartDao.getInstance(); //장바구니 객체 생성

		//저장할 리스트 객체
		List getE = new ArrayList();
		
		if(col.equals("dr_rent")){
			//----------------------- 도서관 서비스 파트 ----------------------
			//책 정보 추가
			getE = cdo.getHeadCart(br_no,"dr_rent");
			request.setAttribute("getE", getE);
			
			srt = "my_list1";
			request.setAttribute("my_li", srt);
		}else if(col.equals("dr_resell")){
			//----------------------- 직거래 게시판 ----------------------
			//관심상품
			int pageSize = 10;
			
			String pageNum = request.getParameter("pageNum"); //
		    if (pageNum == null) { //Parameter가 null일때 동작
		        pageNum = "1";
		    }
			
		    int currentPage = Integer.parseInt(pageNum);
		    int startRow = (currentPage - 1) * pageSize + 1; //
		    int endRow = currentPage * pageSize; //
		    int count = 0;
		    int number=0;
		    
		    List articleList = null;
		    ResellbookDao dbPro = ResellbookDao.getInstance();
		    
		    String id =(String)session.getAttribute("memId");
		    count = dbPro.getMyScrapCount(id);
		    
		    if(count > 0){
		    	articleList = dbPro.getMyScrapList(id);
		    }
		    
		    number=count-(currentPage-1)*pageSize;

			request.setAttribute("id",id);
			request.setAttribute("currentPage", currentPage);
	        request.setAttribute("startRow", startRow);
	        request.setAttribute("endRow", endRow);
	        request.setAttribute("count", count);
	        request.setAttribute("pageSize", pageSize);
			request.setAttribute("number", number);
	        request.setAttribute("articleList", articleList);
	        

	        /////////////////낙찰내용
	        int pageSize1 = 10;
			
			String pageNum1 = request.getParameter("pageNum1"); //
			//int bid_no = Integer.parseInt(request.getParameter("pageNum"));
		    if (pageNum1 == null) { //Parameter가 null일때 동작
		        pageNum1 = "1";
		    }
			
		    int currentPage1 = Integer.parseInt(pageNum1);
		    int startRow1 = (currentPage1 - 1) * pageSize1 + 1; //
		    int endRow1 = currentPage1 * pageSize1; //
		    int count1 = 0;
		    int number1=0;
		    
		    List articleList1 = null;
		    BidbookDao dbPro1 = BidbookDao.getInstance();
		    
		    String id1 =(String)session.getAttribute("memId");
		    count1 = dbPro1.getBidSellerCount(id1);
		    
		    if(count1 > 0){
		    	articleList1 = dbPro1.getBidSellerList(id1);
		    }
		    
		    number1=count1-(currentPage1-1)*pageSize1;
		    
			request.setAttribute("id1",id1);
			request.setAttribute("currentPage1", currentPage1);
	        request.setAttribute("startRow1", startRow1);
	        request.setAttribute("endRow1", endRow1);
	        request.setAttribute("count1", count1);
	        request.setAttribute("pageSize1", pageSize1);
			request.setAttribute("number1", number1);
	        request.setAttribute("articleList1", articleList1);
	        
	        
	        //입찰내용
	        int pageSize2 = 10;//한화면 게시물개수
	        
	        
	        String pageNum2 = request.getParameter("pageNum2");
	        if(pageNum2==null){
	           pageNum2 = "1";
	        }
	        
	        
	        int currentPage2 = Integer.parseInt(pageNum2); //페이지번호
	        int startRow2 = (currentPage2 -1) * pageSize2 +1; 
	        int endRow2 = currentPage2 * pageSize2 +1; //마지막페이지 범위값을 계산한정보
	        int count2 = 0;
	        int number2 = 0;
	        
	        List articleList2 = null;
	        BidbookDao article2 = BidbookDao.getInstance();
	        
	        
	        //검색
	        String search2 = request.getParameter("search2");
	        if(search2 == null){
	           //전체내용
	           count2 = article2.getMyBidCount(id);
	           if(count2 > 0){ //글이 있을때 BidList호출
	              
	              articleList2 = article2.getMyBidList(id, startRow2, endRow2);
	           }
	        }else{
	           count2 = article2.BidMySearchCount(search2,id);
	           if(count2 >0){
	              articleList2 = article2.BidMySearch(search2, id, startRow2, endRow2);
	           }
	        }
	        
	        number2= count2 -(currentPage2-1)*pageSize2;
	        
	        LoginDto dto2=article2.getGrade(id);//회원  등급 확인을 위함
	        
	        request.setAttribute("currentPage2", new Integer(currentPage2));
	        request.setAttribute("startRow2", new Integer(startRow2));
	        request.setAttribute("endRow2", new Integer(endRow2));
	        request.setAttribute("count2", new Integer(count2));
	        request.setAttribute("pageSize2", new Integer(pageSize2));
	        request.setAttribute("number2", new Integer(number2));
	        request.setAttribute("articleList2", articleList2);
	        request.setAttribute("bid_id2", id);
	        request.setAttribute("dto2", dto2);
	        
	        
			srt = "my_list3";
			request.setAttribute("my_li", srt);
			
		//직접판매 배송 조회 리스트
		}else if(col.equals("dr_pan")){
			String id = (String)session.getAttribute("memId");
			
		 	
		 // 한 화면에 보여주는 게시글
		    int pageSize = 10;
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 날짜 형태 바꿔서 보여줌
		
		    String pageNum = request.getParameter("pageNum"); //
		    if (pageNum == null) { //Parameter가 null일때 동작
		        pageNum = "1";
		    }

		    // 검색할 범위 지정, 범위 계산 정보
		    int currentPage = Integer.parseInt(pageNum);
		    int startRow = (currentPage - 1) * pageSize + 1; //
		    int endRow = currentPage * pageSize; //
		    int count = 0;
		    int number=0;
		    
		    List articleList = null;
	        OnDao article = OnDao.getInstance();//DB연동
	        count = article.User_BuyBook_Count(id);//전체 글의 수 

	        if(count > 0){
	            articleList = article.User_BuyBookList(startRow,endRow,id);//현재 페이지에 해당하는 글 목록
	        }

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
			
			
			
	        request.setAttribute("currentPage", new Integer(currentPage));
	        request.setAttribute("startRow", new Integer(startRow));
	        request.setAttribute("endRow", new Integer(endRow));
	        request.setAttribute("count", count);
	        request.setAttribute("pageSize", new Integer(pageSize));
			request.setAttribute("number", new Integer(number));
	        request.setAttribute("articleList", articleList);
	        request.setAttribute("id", id);
	        request.setAttribute("noo", "my_list2_1"); //취소인가 주문인가
	        
	        srt = "my_list2";
			request.setAttribute("my_li", srt);
		
			
		//직접판매 취소 리스트
		}else if(col.equals("dr_cencel")){
			String id = (String)session.getAttribute("memId");
				
			 	
			 // 한 화면에 보여주는 게시글
			    int pageSize = 10;
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 날짜 형태 바꿔서 보여줌
			
			    String pageNum = request.getParameter("pageNum"); //
			    if (pageNum == null) { //Parameter가 null일때 동작
			        pageNum = "1";
			    }
		
			    // 검색할 범위 지정, 범위 계산 정보
			    int currentPage = Integer.parseInt(pageNum);
			    int startRow = (currentPage - 1) * pageSize + 1; //
			    int endRow = currentPage * pageSize; //
			    int count = 0;
			    int number=0;
			    
			    List articleList = null;
		      OnDao article = OnDao.getInstance();//DB연동
		      count = article.User_BuyBook_CancelFinish_Count(id);//전체 글의 수 
		      ///바꾸기
		      if(count > 0){
		          articleList = article.User_BuyBook_CancelList(startRow,endRow,id);//현재 페이지에 해당하는 글 목록
		      }
		
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
				
				
				
		    request.setAttribute("currentPage", new Integer(currentPage));
		    request.setAttribute("startRow", new Integer(startRow));
		    request.setAttribute("endRow", new Integer(endRow));
		    request.setAttribute("count", count);
		    request.setAttribute("pageSize", new Integer(pageSize));
			request.setAttribute("number", new Integer(number));
		    request.setAttribute("articleList", articleList);
		    request.setAttribute("id", id);
		    request.setAttribute("noo", "my_list2_2"); //취소인가 주문인가
		    
		    srt = "my_list2";
			request.setAttribute("my_li", srt);
		}

		
		return "/d_login/my_list.jsp";
	}
	
}








package mvc.doit.ManagerAction;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Manager.ManDao;
import mvc.doit.Manager.ManDto;
import mvc.doit.Online.OnDao;
import mvc.doit.Rent.RentDao;
import mvc.doit.ResellBean.ResellintroDao;
import mvc.doit.ResellBean.ResellintroDto;
import mvc.doit.SuperAction.SuperAction;

public class ManPart1 implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//전체 수익
		ManDao mdao = ManDao.getInstance();
		ManDto mdto = new ManDto();
		mdto = mdao.getDashM();
		request.setAttribute("dashM", mdto);
		
		//세션처리
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		//불러올 상태 목록구분
		String guga1 = request.getParameter("guga");
		int guga = 10;
		if(guga1 != null){
			guga = Integer.parseInt(guga1);
		}
		
		//상태변경
		String mmod= request.getParameter("mmod");
		if(mmod != null){ //상태변경할건가?
			if(mmod.equals("yes")){//개별 변경인가?
				String br_code = request.getParameter("br_code");
				mdao.modiDeliv(guga, br_code);
			}else{
				mdao.modiDeliv(guga, "");
			}	
		}
		
		//목록 저장
		List DeliCont = mdao.getDeliCont(guga);
		request.setAttribute("DeliCont", DeliCont);
		request.setAttribute("guw", guga); //현재 상태 저장-일괄처리용
		
		
		
		/// 온라인 판매
		String cenList = request.getParameter("cenList"); //구매(sell) 취소(cell)
		if(cenList == null){
			cenList = "sell";
		}
		
		if(cenList.equals("sell")){ //구매
			request.setAttribute("man_part_li", "man_part1_1");
			
			//사용자 서비스 - 구매신청, 완료
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
//		        count = article.Admin_SellCount();//전체 글의 수 
		        
		        //구매신청(yyess) , 구매완료(nooo)
		        String ynoo = request.getParameter("ynoo");
		        if(ynoo == null){
		        	ynoo = "yyess";
		        }
		        String sss = "";
		        
		        if(ynoo.equals("yyess")){//구매 신청(yyess)
		        	count = article.Admin_BuyBook_Count();//전체 글의 수 
	 		        ///바꾸기
	 		        if(count > 0){
	 		            articleList = article.Admin_BuyBookList(startRow,endRow);//현재 페이지에 해당하는 글 목록
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
			        sss = "adminOnBuyBookList";//불러올 페이지
			        
		        }else{
		        	count = article.Admin_BuyBook_FinishList_Count();//전체 글의 수 
		 		        ///바꾸기
		 		        if(count > 0){
		 		            articleList = article.Admin_BuyBook_FinishList(startRow,endRow);//현재 페이지에 해당하는 글 목록
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
		 		       sss = "adminOnBuyBookList_Finish"; //불러올 페이지
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
		        
		        request.setAttribute("temp_li2", sss); //취소 불러올 페이지 이름
		        
			
		}else if(cenList.equals("cell")){ //취소
			request.setAttribute("man_part_li", "man_part1_2");
			
			//사용자 서비스 - 취소신청, 완료
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
	        
	        //내용에 따른 리스트 변경
	        //취소신청(yes) 취소완료(no)
			String result = request.getParameter("result");
			if(result == null){
				result = "yes";
			}
			String sss = "";//불러올 페이지 저장
	        ///////////취소 신청 리스트//////////////
	        if(result.equals("yes")){ //취소신청
		        count = article.Admin_BuyBook_CancelList_Count();//전체 글의 수 
		        ///바꾸기
		        if(count > 0){
		            articleList = article.Admin_BuyBook_CancelList(startRow,endRow);//현재 페이지에 해당하는 글 목록
		        }
		        sss = "adminOnBuyList_Cancel";
		    }else{ //취소완료
	        //////취소 완료 리스트
		        count = article.Admin_BuyBook_CancelFinish_Count();//전체 글의 수 
			      ///바꾸기
			      if(count > 0){
			          articleList = article.Admin_BuyBook_CancelFinishList(startRow,endRow);//현재 페이지에 해당하는 글 목록
			      }
			      sss = "adminOnBuyList_CancelFinish";
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
	        
	        request.setAttribute("temp_li", sss); //취소 불러올 페이지 이름
	        
		}
		
		
		
		
		
		
			
		
		return "/d_manage/man_part1.jsp";
	}

}

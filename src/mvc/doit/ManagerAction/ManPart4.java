package mvc.doit.ManagerAction;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.doit.Delivery.DeliveryDao;
import mvc.doit.Manager.ManDao;
import mvc.doit.Manager.ManDto;
import mvc.doit.Online.OnBookDto;
import mvc.doit.Online.OnDao;
import mvc.doit.Rent.RentDao;
import mvc.doit.ResellBean.ResellintroDao;
import mvc.doit.ResellBean.ResellintroDto;
import mvc.doit.SuperAction.SuperAction;

public class ManPart4 implements SuperAction{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//세션처리
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		//전체 수익
		ManDao mdao = ManDao.getInstance();
		ManDto mdto = new ManDto();
		mdto = mdao.getDashM();
		request.setAttribute("dashM", mdto);
		
		//온라인 통계
		
		
		
		////판매신청리스트 //온라인 등록
		String sdfe = request.getParameter("sdfe"); //판매냐 완료냐
		if(sdfe == null){
			sdfe = "pana"; //판매
		}
		String wew = "";
		
		if(sdfe.equals("pana")){
			//판매신청 리스트
				//판매신청리스트 //온라인 등록
				 String pageNum = request.getParameter("pageNum"); //
				    if (pageNum == null) { //Parameter가 null일때 동작
				        pageNum = "1";
				    }
				    int delivery = -1;
				    if(request.getParameter("delivery") != null){
				    	delivery = Integer.parseInt(request.getParameter("delivery"));
				    }
				    int d_bcode = -1;
				    if(request.getParameter("d_bcode") != null){
				    	d_bcode = Integer.parseInt(request.getParameter("d_bcode"));
				    }
				    
				    // 검색할 범위 지정, 범위 계산 정보
					// 한 화면에 보여주는 게시글
				    int pageSize = 20;
				    int currentPage = Integer.parseInt(pageNum);
				    int startRow = (currentPage - 1) * pageSize + 1; //
				    int endRow = currentPage * pageSize; //
				    int count = 0;
				    int number=0;
				    
				    //---- 판매신청 확인 dao -----
				    List articleList = null;
			        OnDao article = OnDao.getInstance();//DB연동
			        
			        //---- 조건: 판매신청완료버튼을 눌렀을때, Dao
			        String sell_confirm = null;
			        if(request.getParameter("sell_confirm") != null){
			        	article.Admin_Sell_Confirm(d_bcode);
			        }
			        
				    //---- 조건: 배송중 -> 배송완료 : 배송현황(d_bdelivery=12)일때,  delivery == 13 --> 배송현황(배송완료 13)으로 table update -----
				    DeliveryDao ddao = DeliveryDao.getInstance();
				    if(delivery == 13){
				    	ddao.User_SellBook_delivertEnd(d_bcode);
				    }	
				    
				    //---- list ----
			        count = article.Admin_SellCount();//전체 글의 수 
			        if(count > 0){
			            articleList = article.Admin_SellList(startRow,endRow);//현재 페이지에 해당하는 글 목록
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
			        
			        wew = "adminOnSellList";
		}else{ 
			//완료리스트
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
		        count = article.Admin_OnBookCount();//전체 글의 수

		        if(count > 0){
		            articleList = article.Admin_OnBookList(startRow,endRow);//현재 페이지에 해당하는 글 목록
		        }

				number=count-(currentPage-1)*pageSize;//글목록에 표시할 글번호
				
		        request.setAttribute("currentPage", new Integer(currentPage));
		        request.setAttribute("startRow", new Integer(startRow));
		        request.setAttribute("endRow", new Integer(endRow));
		        request.setAttribute("count", count);
		        request.setAttribute("pageSize", new Integer(pageSize));
				request.setAttribute("number", new Integer(number));
		        request.setAttribute("articleList", articleList);
		        
		        wew = "adminOnBookList";
		}
		request.setAttribute("paanad", wew); //불러 올 페이지
		
	    
		
		////검수리스트
		
		String gum_li = request.getParameter("gum_li");//리스트냐 글쓰기이냐
		if(gum_li == null){
			gum_li = "list"; //검수리스트
		}
		
		if(gum_li.equals("list")){ //검수리스트일때
			request.setAttribute("gum_list", "adminOnInspectionList");//불러올 페이지 : 리스트
			
			int pageSize2 = 5;
			
		    String pageNum2 = request.getParameter("pageNum2"); //
		    if (pageNum2 == null) { //Parameter가 null일때 동작
		        pageNum2 = "1";
		    }
		    int delivery2 = -1;
		    if(request.getParameter("delivery2") != null){
		    	delivery2 = Integer.parseInt(request.getParameter("delivery2"));
		    }
		    int d_bcode2 = -1;
		    if(request.getParameter("d_bcode2") != null){
		    	d_bcode2 = Integer.parseInt(request.getParameter("d_bcode2"));
		    }
	
		    // 검색할 범위 지정, 범위 계산 정보
		    int currentPage2 = Integer.parseInt(pageNum2);
		    int startRow2 = (currentPage2 - 1) * pageSize2 + 1; //
		    int endRow2 = currentPage2 * pageSize2; //
		    int count2 = 0;
		    int number2=0;
		    
		    
	
		    
		    //---- List ---- count:d_sfinish==1 (판매가능한 책 count), list: 3table(d_onBook, d_onSellList, d_bdelivery) 판매가능한(d_sfinish==1) 책 List
		    List articleList2 = null;
	        OnDao article2 = OnDao.getInstance();//DB연동
	        count2 = article2.Admin_InspectionCount();//전체 글의 수 
	
	        if(count2 > 0){
	        		articleList2 = article2.Admin_InspectionList(startRow2,endRow2, d_bcode2);//현재 페이지에 해당하는 글 목록
	        }
	        
	        //---- page size ----
			number2=count2-(currentPage2-1)*pageSize2;//글목록에 표시할 글번호
			
			//----
	        request.setAttribute("currentPage2", new Integer(currentPage2));
	        request.setAttribute("startRow2", new Integer(startRow2));
	        request.setAttribute("endRow2", new Integer(endRow2));
	        request.setAttribute("count2", count2);
	        request.setAttribute("pageSize2", new Integer(pageSize2));
			request.setAttribute("number2", new Integer(number2));
	        request.setAttribute("articleList2", articleList2);
        
		}else{//검수리스트 체크 목록
			
			int d_bcode2 = Integer.parseInt(request.getParameter("d_bcode2"));
			
			OnDao dao = OnDao.getInstance();
			
				
			OnBookDto dto = dao.Admin_Inspection(d_bcode2);
			
			request.setAttribute("d_bcode", d_bcode2);
			request.setAttribute("d_bname", dto.getD_bname());
			request.setAttribute("d_bvalue", dto.getD_bvalue());
			
			request.setAttribute("gum_list", "onInspection");//불러올 페이지 : 리스트
			
		}
		
			
			
		
		return "/d_manage/man_part4.jsp";
	}

}

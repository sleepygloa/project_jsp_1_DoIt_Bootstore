<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    


    
    
    
    
    
         	
         	
         	<%-- 재고관리1번 도서 검수, 등록, 구매 --%>
         	
         	<%-- 재고관리1번 도서 검수, 등록, 구매 끝 --%>
         	
         	
         	
         	<%-- 재고관리2번 사용자 서비스--%>
         	<article class="artisdl">
         		<div style="overflow:hidden;">
                  <p class="mana_Stitle fl_le">사용자 서비스</p>
                  <div class="fl_ri">
	                  <form action="#" class="search_bar fl_ri"  style="padding:0px; margin:0px;">
						<p>
							<a><input type="text" name="#" placeholder="사용자이름을 검색하세요."/></a>
							<a><button type="submit" >검색</button></a>
						</p>
						</form>
					</div>
               </div>
         		
         		<%-- 1차분류 --%>
         		<ul class="my_list_tab">
					<li class="click_tab1" style="width:50%;"><a href="/DoIt/d_manage/manPart1.do" >사용자 구매(신청,완료)</a></li>
					<li class="click_tab2" style="width:50%;"><a href="/DoIt/d_manage/manPart1.do?cenList=cell" class="ta_1a">사용자 구매 취소(신청,완료)</a></li>
				</ul>
				
				<%-- 2차 분류 --%>
         		<ul class="sort_top re1">
					<li><a class="busw1" href="/DoIt/d_manage/manPart1.do">구매신청 리스트</a></li>
					<li><a class="busw2" href="/DoIt/d_manage/manPart1.do?ynoo=nooo">구매완료 리스트</a></li>
				</ul>
				
				<article class="black_Li10">
					
					<jsp:include page="/d_admin/${ temp_li2 }.jsp"></jsp:include>
					
				</article>
				
         	</article>
         	<%-- 재고관리2번 사용자 서비스 끝--%>
    
    
    
    
    
    
    
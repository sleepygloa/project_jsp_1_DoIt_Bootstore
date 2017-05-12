<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--------------- header include --------------%>
<jsp:include page="ma_header.jsp"></jsp:include>


<%-- 전체 내용 wrap --%>
<section class="mana_wrap_a">

   <%--------------- 사이드 메뉴 include width: height:1537px --------------%>
   <jsp:include page="d_mana_side.jsp"></jsp:include>

   
   <%-- 본문 내용 height:1530px; --%>
   <article class="dash_wrap">   
      
      <%-- 본문 내용 정렬 : width:100%;  --%>
      <article class="dash_wrap_cont">
         
         <%-- 공동 nav 불러오기 --%>
         <jsp:include page="/d_manage/cont_header.jsp"></jsp:include>
         
         
         <%-- section 속에 아티클 단위로 입력해서 넣으시면 됩니다. 사이즈 : 자동 끝--%>
         <section class="warp_cond">
         
         	<%-- 재고상태 관리 제목 --%>
			<article class="part4_title_part">
				<%-- 등록완료 리스트 --%>
				<div class="title_part1">
					<p></p>
					<ul>
						<li><span>등록완료</span></li>
						<li><a>12</a></li>
					</ul>
				</div>
				
				<%-- 구매결제 리스트 --%>
				<div class="title_part2">
					<p></p>
					<ul>
						<li><span>구매결제</span></li>
						<li><a>12</a></li>
					</ul>
				</div>
				
				<%-- 구매완료 리스트 --%>
				<div class="title_part3">
					<p></p>
					<ul>
						<li><span>구매완료</span></li>
						<li><a>12</a></li>
					</ul>
				</div>
				
				<%-- 취소완료 리스트 --%>
				<div class="title_part4">
					<p></p>
					<ul>
						<li><span>구매취소</span></li>
						<li><a>12</a></li>
					</ul>
				</div>
			
			</article>         
         	<%-- 재고상태 관리 제목 끝 --%>
         	
         	
         	<%-- 판매신청리스트 --%>
         	<%-- 책등록완료 리스트 --%>
         	<article class="black_Li101">
         		
         		<div style="overflow:hidden;">
					<p class="mana_Stitle">판매(등록)신청 / 등록완료 리스트</p>
				</div>
						
				<%-- 연체 리스트 탭  --%>
				<ul class="sort_top">
					<li><a class="bl_cu" href="/DoIt/d_manage/manPart4.do">판매신청리스트</a></li>
					<li><a class="bl_cu2" href="/DoIt/d_manage/manPart4.do?sdfe=ress">등록완료리스트</a></li>
				</ul>
         		
         		<jsp:include page="/d_admin/${ paanad }.jsp"></jsp:include>
         		
         	</article>
         	<%-- 판매신청리스트 끝 --%>
         	
         	
         	
         	<%-- 검수등록리스트 --%>
			<article class="gum_list">
			
				<div style="overflow:hidden;">
					<p class="mana_Stitle">검수 리스트(완료시 등록완료)</p>
				</div>
				
				<%-- 연체 리스트 탭  --%>
				<ul class="sort_top">
				</ul>
				
				<jsp:include page="/d_admin/${ gum_list }.jsp"></jsp:include>
				
			</article>
         	<%-- 검수등록리스트 끝 --%>
         	
         	
         	
         	
         	
         
         </section>
         <%-- section 속에 아티클 단위로 입력해서 넣으시면 됩니다. 사이즈 : 자동 끝--%>
         
         
      </article>
         
   </article>

   
</section>


<%--------------- footer include --------------%>
<jsp:include page="ma_footer.jsp"></jsp:include>
         
         
         
         
         
         
         
         
         
         
         
         
         
         
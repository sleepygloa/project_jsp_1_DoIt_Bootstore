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
         	
         	<%-- 도서관 배송 리스트 --%>
         	<article class="lib_deli">
         		
         		<%-- 각 파트 제목 --%>
               <div style="overflow:hidden;">
                  <p class="mana_Stitle fl_le">도서관 도서 배송상태</p>
                  <div class="fl_ri">
	                  <form action="#" class="search_bar fl_ri"  style="padding:0px; margin:0px;">
						<p>
							<a><input type="text" name="#" placeholder="도서이름을 검색하세요."/></a>
							<a><button type="submit" >검색</button></a>
						</p>
						</form>
					</div>
               </div>
               
               <ul class="sort_top">
               		<li><a href="/DoIt/d_manage/manPart1.do?guga=10">전체보기</a></li>
					<li><a href="/DoIt/d_manage/manPart1.do?guga=0">승인대기상태</a></li>
					<li><a href="/DoIt/d_manage/manPart1.do?guga=1">배송준비</a></li>
					<li><a href="/DoIt/d_manage/manPart1.do?guga=2">배송 중</a></li>
					<li><a href="/DoIt/d_manage/manPart1.do?guga=3">배송완료</a></li>
				</ul>
				
				<c:if test="${ guw != 10 }">
					<p class="butts" onclick="window.location='/DoIt/d_manage/manPart1.do?mmod=no&guga=${ guw }'" style="padding:3px 15px; margin-bottom:5px;">일괄처리</p>
         		</c:if>
         		
         		<table class="lib_deli_tab" cellspacing="0">
         			<colgroup>
         				<col width="5%"><col width="8%"><col width="8%"><col width="30%">
         				<col width="18%"><col width="10%"><col width="10%"><col width="10%">
         			</colgroup>
         			<thead>
         				<tr>
         					<th>번호</th>
         					<th>코드</th>
         					<th>이미지</th>
         					<th class="text_left">제목</th><%-- 저자, 출판사 --%>
         					<th class="text_left">대기자</th>
         					<th>배송상태</th>
         					<th>대여일자</th>
         					<th>제어</th>
         				</tr>
         			</thead>
         			<tbody>
         				<c:if test="${ DeliCont == null }">
         					<tr>
         						<td colspan="8">배송요청이 없습니다.</td>
         					</tr>
         				</c:if>
         			
         				<c:if test="${ DeliCont != null }">
         				<c:forEach var="c" items="${ DeliCont }">
         				<tr>
         					<td><p>${ c.getBr_no() }</p></td>
         					<td><p>${ c.getBr_code() }</p></td>
         					
         					<td>
         						<c:if test="${ c.getBr_thumpic() ==null }">
         							<img src="/DoIt/images/ma_img.jpg"/>
         						</c:if>
         						<c:if test="${ c.getBr_thumpic() !=null }">
         							<img src="/DoIt/save/${ c.getBr_thumpic() }"/>
         						</c:if>
         					</td>
         					<td>
         						<a href="/DoIt/d_rent/detail.do?br_no=${ c.getBr_no() }">
	         						<p class="text_left">${ c.getBr_name() }</p>
	         						<p class="text_left"><span>${ c.getBr_writer() }</span> | <span>${ c.getBr_pub() }</span></p>
         						</a>
         					</td>
         					<td><p class="text_left">${ c.getBr_waiter() }</p></td>
         					<td>
         						<p>
         							<c:if test="${ c.getBr_deli()==0 }">
         								승인대기상태
         							</c:if>
         							<c:if test="${ c.getBr_deli()==1 }">
         								배송준비
         							</c:if>
         							<c:if test="${ c.getBr_deli()==2 }">
         								배송중
         							</c:if>
         							<c:if test="${ c.getBr_deli()==3 }">
         								배송완료
         							</c:if>
         						</p>
         					</td>
         					<td>2017-10-10 20:20:20</td>
         					<td><p class="write_bu"><a href="/DoIt/d_manage/manPart1.do?br_code=${ c.getBr_code() }&mmod=yes&guga=${ c.getBr_deli() }">상태변경</a></p></td>
         				</tr>
         				</c:forEach>
         				</c:if>
         			</tbody>
         		</table>
         		
         	</article>
         	<%-- 도서관 배송 리스트 끝--%>
			
			
			<%-- 사용자 취소, 구매 신청 / 완료 --%>
			<%-- man_part1_1 : 구매 신청,완료 리스트 --%>
			<%-- man_part1_2 : 취소 신청, 완료 리스트 --%>
				
			<jsp:include page="/d_manage/${ man_part_li }.jsp"></jsp:include>
				
			
			
         </section>
         <%-- section 속에 아티클 단위로 입력해서 넣으시면 됩니다. 사이즈 : 자동 끝--%>
         
         
      </article>
         
   </article>

   
</section>


<%--------------- footer include --------------%>
<jsp:include page="ma_footer.jsp"></jsp:include>
         
         
         
         
         
         
         
         
         
         
         
         
         
         
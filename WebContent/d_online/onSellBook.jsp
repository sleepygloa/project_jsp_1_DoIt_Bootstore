<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header import --------------------------------------------------------------------------------->
<jsp:include page="/header.jsp" flush="false" /> 

<!-- side menu import --------------------------------------------------------------------------- -->
<jsp:include page="/d_online/onBookFillter.jsp"></jsp:include>
	
	
	
<!-- main contents ------------------------------------------------------------------------------ -->
<article class="my_cont_wrap ">

<!-- 오늘들어온 책의 수 공간 -->
<div class="d-w-80 d-left20 d-border d-center">
	오늘 들어온 책 : 
	<span>
		<c:choose>
		    <c:when test="${todayPurchaseCountArray[0] == null}"><img src="/DoIt/images/0.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 0}"><img src="/DoIt/images/0.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 1}"><img src="/DoIt/images/1.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 2}"><img src="/DoIt/images/2.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 3}"><img src="/DoIt/images/3.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 4}"><img src="/DoIt/images/4.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 5}"><img src="/DoIt/images/5.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 6}"><img src="/DoIt/images/6.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 7}"><img src="/DoIt/images/7.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 8}"><img src="/DoIt/images/8.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 9}"><img src="/DoIt/images/9.png" width="100px" /></c:when>
		</c:choose>
		<c:choose>
			<c:when test="${todayPurchaseCountArray[0] == null}"><img src="/DoIt/images/0.png" width="100px" /></c:when>   
			<c:when test="${todayPurchaseCountArray[1] == 0}"><img src="/DoIt/images/0.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 1}"><img src="/DoIt/images/1.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 2}"><img src="/DoIt/images/2.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 3}"><img src="/DoIt/images/3.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 4}"><img src="/DoIt/images/4.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 5}"><img src="/DoIt/images/5.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 6}"><img src="/DoIt/images/6.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 7}"><img src="/DoIt/images/7.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 8}"><img src="/DoIt/images/8.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 9}"><img src="/DoIt/images/9.png" width="100px" /></c:when>
		</c:choose>					
		<c:choose>
			<c:when test="${todayPurchaseCountArray[0] == null}"><img src="/DoIt/images/0.png" width="100px" /></c:when>   
			<c:when test="${todayPurchaseCountArray[2] == 0}"><img src="/DoIt/images/0.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 1}"><img src="/DoIt/images/1.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 2}"><img src="/DoIt/images/2.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 3}"><img src="/DoIt/images/3.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 4}"><img src="/DoIt/images/4.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 5}"><img src="/DoIt/images/5.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 6}"><img src="/DoIt/images/6.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 7}"><img src="/DoIt/images/7.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 8}"><img src="/DoIt/images/8.png" width="100px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 9}"><img src="/DoIt/images/9.png" width="100px" /></c:when>
		</c:choose>	
	</span> 권
</div>

<div class="d-space10"></div>

<!-- 1. 검색 박스와 새 책을 파는 button (2. list)--------------------------------------------------------- -->	
<div class="grid-box d-center d-margin">
	
	<!-- page message text----- -->
<div class="alert alert-info"><small>*. 판매 하실 때엔 먼저 책을 검색하여 판매하실 책을 선택 해 주세요.</small></div>

	<!-- select box ----------- -->
	<div class="grid-w2 d-center">
		<form action="/DoIt/d_online/onSellBook.do" method="get">
			<input type="text" class="form-control d-center" id="inputSuccess1" name="select" placeholder="판매하실 책 이름을 검색해주세요" />
			<input class="btn btn-default grid-w2-btn"  type="submit" value="검색" />
		</form>
	</div>

	<!-- 신규 책 판매, 클릭시 판매신청서 페이지로 이동------------------------------------------------------------------------ -->
	<div class="grid-w2" >
		<button type="button" class="btn btn-default btn-lg-t btn-block"
		onclick="window.location='/DoIt/d_online/onSellForm.do?d_bno=0&s=sellpage'">
			 <span class="btn-lg-t big-font25">신규 책 판매</span>
		 </button>
	</div>
	
</div>	<!-- page message text-----  끝 --->


<!-- 줄 및 위아래 공백 -->
<div class="d-space50"></div>



<div class="rl">
	<!-- 리스트 큰제목 (검색에 따라 제목이 바뀜)----------------------------------------------------------------------------------- -->
	<div class="d-in d-left10">
		<c:choose>   
			<c:when test="${d_bonFillterReturn == 0}"> <span class="big-font30 d-bold">종합 서적 리스트</span></c:when>
			<c:when test="${d_bonFillterReturn == 01}"><span class="big-font30 d-bold">소설 / 시 / 에세이</span></c:when>	
			<c:when test="${d_bonFillterReturn == 02}"><span class="big-font30 d-bold">참고 / 전문서적</span></c:when>
			<c:when test="${d_bonFillterReturn == 03}"><span class="big-font30 d-bold">어린이 서적</span></c:when>
			<c:when test="${d_bonFillterReturn == 04}"><span class="big-font30 d-bold">인문학 서적</span></c:when>
			<c:when test="${d_bonFillterReturn == 05}"><span class="big-font30 d-bold">과학 전문서적</span></c:when>
			<c:when test="${d_bonFillterReturn == 06}"><span class="big-font30 d-bold">기타 서적</span></c:when>
			<c:when test="${d_bonFillterReturn == 07}"><span class="big-font30 d-bold">아트지</span></c:when>
			<c:when test="${d_bonFillterReturn == 08}"><span class="big-font30 d-bold">레자크지</span></c:when>
			<c:when test="${d_bonFillterReturn == 09}"><span class="big-font30 d-bold">스노우지</span></c:when>
			<c:when test="${d_bonFillterReturn == 10}"><span class="big-font30 d-bold">모조지</span></c:when>
			<c:when test="${d_bonFillterReturn == 11}"><span class="big-font30 d-bold">국내도서</span></c:when>
			<c:when test="${d_bonFillterReturn == 12}"><span class="big-font30 d-bold">국외도서</span></c:when>
		</c:choose>		
	</div>

	<!-- ------순번 및 전체선택, 장바구니, (구매하기 )-------------버튼 -->
		<div class="d-in d-right10">
<%-- 			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1"></c:forEach> --%>
				<a href="/DoIt/d_online/onSellBook.do?pageNum=1">${20}↑</a>
				<a href="/DoIt/d_online/onSellBook.do?pageNum=2">${40}↑</a>
				<a href="/DoIt/d_online/onSellBook.do?pageNum=3">${60}↑</a>
				<a href="/DoIt/d_online/onSellBook.do?pageNum=4">${80}↑</a>
				<a href="/DoIt/d_online/onSellBook.do?pageNum=5">${100}↑</a>
		</div>
</div>







	
<!-- 줄 --><div class="d-space10"></div><hr />

<!-- -------등록된 책이 없을 때----------------------------- -->
<c:if test="${count==0}">
	<div class="d-center">
		보유한 책이 없습니다! <span class="code">도서관서비스</span>와 <span class="code">직접거래서비스</span>를 이용해주세요!!
	</div>
</c:if>

<!-- 사이공백 -->
<div class="rl"></div>

<!-- -------등록된 책이 있을 때----------------------------- -->
<c:if test="${count!=0}">
<table style="width:100%" border="1 solid black">	
		<tr>
			<td class="d-center">순번</td>
			<td class="d-center">책 이미지</td>
			<td class="d-center">내용</td>
		</tr>	

	<c:forEach var="article" begin="0" end="${articleList.size()}" step="1" items="${articleList}">	

		<tr>
			<td width="80px" class="d-center" > <!--  책이미지와 셀의 높이가 10px 커야함. thumbnail 적용 여백 -->
				<span class="big-font20">
					<c:out value="${number}"/>
					<c:set var="number" value="${number - 1}"/>
				</span>
			</td>
			<td width="100px"  height="160px">
 				<c:if test="${article.d_bpic == null}">
					<img src="/DoIt/images/ma_img.jpg"  width="100px" height="150px" class="thumbnail" />
				</c:if>
				<c:if test="${article.d_bpic != null}">
					<img src=
					"\DoIt\d_bpic/${article.d_bpic}" width="100px" height="150px"  class="thumbnail" />
				</c:if>
			</td>
			<td width="800px" class="d-w80 ">
<!--  책제목을 눌렀을 때 상세 페이지로 이동 ------------------------------------------------------------------------------ -->
 				<span class="big-font35 d-bold d-l-padding10">
 					<a href="/DoIt/d_online/onArticle.do?d_bno=${article.d_bno}&s=sellpage">${article.d_bname}</a>
 				</span><br /><br />
				저자 : ${article.d_bauthor} | 출판사 : ${article.d_bpublisher} | 출간날짜 : ${article.d_bregistdate}<br /><br />
				평균 구매금액 : <span class="code">${article.d_bpurchasevalue}</span> 원 <span class="small70"> → [책상태에 따라 달라집니다.] </span> 
			</td>
		</tr>
	 
	</c:forEach>

</table>
</c:if>	

</article>	

<!-- footer import -->
<jsp:include page="/footer.jsp" flush="false" /> 
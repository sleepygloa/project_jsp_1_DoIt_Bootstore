<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header import --------------------------------------------------------------------------------->
<jsp:include page="/header.jsp" flush="false" /> 

<!-- side menu import --------------------------------------------------------------------------- -->
<jsp:include page="/d_online/onBookFillter.jsp"></jsp:include>
	
	
	
<!-- main contents ------------------------------------------------------------------------------ -->
<article class="my_cont_wrap ">

<div class="d-space10"></div>

<!-- 1. 검색 박스와 새 책을 파는 button (2. list)--------------------------------------------------------- -->	
<div class="grid-box d-center d-margin">

	<!-- 1. 새책을 파는 btn  -->
<div class="jumbotron">

		이 책을 갖고 있나요?&nbsp;&nbsp;&nbsp;&nbsp;
		<button class="btn btn-default grid-w1-btn-50" 
		onclick="window.location='/DoIt/d_online/onSellForm.do?d_bno=0'">책 판매</button>

</div>
<hr />
<div class="d-space20"></div>
	<!-- 1. 새책을 파는 btn --끝-- -- -->	

	<!-- 2. 검색  ----------- -->
	<div class="grid-w2 d-center">
		<form action="/DoIt/d_online/onSellBook.do" method="get">
			<input type="text" class="form-control d-center" id="inputSuccess1" name="select" placeholder="판매하실 책 이름을 검색해주세요" />
			<input class="btn btn-default grid-w2-btn"  type="submit" value="검색" />
		</form>
	</div>
	<!-- 2. 검색 -- 끝  ----------- -->

</div>	<!-- page message text-----  끝 --->


<!-- 줄 및 위아래 공백 -->
<div class="d-space50"></div>
<!-- 1. 검색 박스와 새 책을 파는 button --끝-- (2. list)--------------------------------------------------------- -->



<!-- 2. list 1) 큰제목  ---------------------------------------------------------------------------------- -->
<div class="rl">
	<!-- 리스트 큰제목 (좌측 sidenav의 선택에 따라  제목이 바뀜) -->
	<div class="d-in d-left10">
		<c:if test="${selectBookFullNameReturn == null}">
		<c:choose>   
			<c:when test="${d_bonFillterReturn == 0}"> <span class="big-font30 d-bold">종합 서적 리스트</span></c:when>
			<c:when test="${d_bonFillterReturn == 01}"><span class="big-font30 d-bold">소설 / 시 / 에세이 리스트</span></c:when>	
			<c:when test="${d_bonFillterReturn == 02}"><span class="big-font30 d-bold">참고 / 전문서적 리스트</span></c:when>
			<c:when test="${d_bonFillterReturn == 03}"><span class="big-font30 d-bold">어린이 서적 리스트</span></c:when>
			<c:when test="${d_bonFillterReturn == 04}"><span class="big-font30 d-bold">인문학 서적 리스트</span></c:when>
			<c:when test="${d_bonFillterReturn == 05}"><span class="big-font30 d-bold">과학 전문서적 리스트</span></c:when>
			<c:when test="${d_bonFillterReturn == 06}"><span class="big-font30 d-bold">기타 서적 리스트</span></c:when>
			<c:when test="${d_bonFillterReturn == 07}"><span class="big-font30 d-bold">아트지 리스트</span></c:when>
			<c:when test="${d_bonFillterReturn == 08}"><span class="big-font30 d-bold">레자크지 리스트</span></c:when>
			<c:when test="${d_bonFillterReturn == 09}"><span class="big-font30 d-bold">스노우지 리스트</span></c:when>
			<c:when test="${d_bonFillterReturn == 10}"><span class="big-font30 d-bold">모조지 리스트</span></c:when>
			<c:when test="${d_bonFillterReturn == 11}"><span class="big-font30 d-bold">국내도서 리스트</span></c:when>
			<c:when test="${d_bonFillterReturn == 12}"><span class="big-font30 d-bold">국외도서 리스트</span></c:when>
			
		</c:choose>		
		</c:if>
		<c:if test="${selectBookFullNameReturn != null}">
			<span class="big-font30 d-bold">'${selectBookFullNameReturn}' 리스트</span>
		</c:if>
	</div>

	<!-- ------ List 페이지 버튼  -->
		<div class="d-in d-right10">
				<a href="/DoIt/d_online/onSellBook.do?pageNum=1">${20}↑</a>
				<a href="/DoIt/d_online/onSellBook.do?pageNum=2">${40}↑</a>
				<a href="/DoIt/d_online/onSellBook.do?pageNum=3">${60}↑</a>
				<a href="/DoIt/d_online/onSellBook.do?pageNum=4">${80}↑</a>
				<a href="/DoIt/d_online/onSellBook.do?pageNum=5">${100}↑</a>
		</div>
</div>


<!-- 줄 --><div class="d-space10"></div><hr />


<!-- 2. list 의 2) list ----------------------------------------------------------------------------------- -->
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

	<c:forEach var="article"  items="${articleList}">	

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
 					<a href="/DoIt/d_online/onArticle.do?d_bno=${article.d_bno}">${article.d_bname}</a>
 				</span><br /><br />
				저자 : ${article.d_bauthor} | 출판사 : ${article.d_bpublisher} | 출간날짜 : ${article.d_bregistdate}<br /><br />
				평균 구매금액 : <span class="code">${article.d_bavgsellvalue}</span> 원 <span class="small70"> → [책상태에 따라 달라집니다.] </span> 
			</td>
		</tr>
	 
	</c:forEach>

</table>
</c:if>	
<!-- 2. list 의 2) list -- 끝 ----------------------------------------------------------------------------------- -->
</article>	

<!-- footer import -->
<jsp:include page="/footer.jsp" flush="false" /> 
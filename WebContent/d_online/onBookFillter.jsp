<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<article id="my_side">
<!-- 오늘들어온 책의 수 공간 -->
	<center>
	오늘 들어온 책 <br />

	<span>
		<c:choose>
		    <c:when test="${todayPurchaseCountArray[0] == null}"><img src="/DoIt/images/0.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 0}"><img src="/DoIt/images/0.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 1}"><img src="/DoIt/images/1.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 2}"><img src="/DoIt/images/2.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 3}"><img src="/DoIt/images/3.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 4}"><img src="/DoIt/images/4.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 5}"><img src="/DoIt/images/5.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 6}"><img src="/DoIt/images/6.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 7}"><img src="/DoIt/images/7.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 8}"><img src="/DoIt/images/8.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[0] == 9}"><img src="/DoIt/images/9.png" width="30px" /></c:when>
		</c:choose>
		<c:choose>
			<c:when test="${todayPurchaseCountArray[0] == null}"><img src="/DoIt/images/0.png" width="30px" /></c:when>   
			<c:when test="${todayPurchaseCountArray[1] == 0}"><img src="/DoIt/images/0.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 1}"><img src="/DoIt/images/1.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 2}"><img src="/DoIt/images/2.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 3}"><img src="/DoIt/images/3.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 4}"><img src="/DoIt/images/4.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 5}"><img src="/DoIt/images/5.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 6}"><img src="/DoIt/images/6.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 7}"><img src="/DoIt/images/7.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 8}"><img src="/DoIt/images/8.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[1] == 9}"><img src="/DoIt/images/9.png" width="30px" /></c:when>
		</c:choose>					
		<c:choose>
			<c:when test="${todayPurchaseCountArray[0] == null}"><img src="/DoIt/images/0.png" width="30px" /></c:when>   
			<c:when test="${todayPurchaseCountArray[2] == 0}"><img src="/DoIt/images/0.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 1}"><img src="/DoIt/images/1.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 2}"><img src="/DoIt/images/2.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 3}"><img src="/DoIt/images/3.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 4}"><img src="/DoIt/images/4.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 5}"><img src="/DoIt/images/5.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 6}"><img src="/DoIt/images/6.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 7}"><img src="/DoIt/images/7.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 8}"><img src="/DoIt/images/8.png" width="30px" /></c:when>
			<c:when test="${todayPurchaseCountArray[2] == 9}"><img src="/DoIt/images/9.png" width="30px" /></c:when>
		</c:choose>	
	</span>
	</center>


	<p class="side_top">온라인 중고서점</p>
	<ul class="my_side_ul">
<!-- for switch tag -------------------------------------------------------------------------------------------------- -->		
		<li class="bgc"><a class="fillter-a" href="/DoIt/d_online/onSellBook.do?">전체보기</a></li>
		<li><a href="/DoIt/d_online/onSellBook.do?d_bonFillter=01">소설 / 시 / 에세이</a></li>
		<li><a href="/DoIt/d_online/onSellBook.do?d_bonFillter=02">참고 / 전문서적</a></li>
		<li><a href="/DoIt/d_online/onSellBook.do?d_bonFillter=03">어린이 서적</a></li>
		<li><a href="/DoIt/d_online/onSellBook.do?d_bonFillter=04">인문학 서적</a></li>
		<li><a href="/DoIt/d_online/onSellBook.do?d_bonFillter=05">과학 전문서적</a></li>
		<li><a href="/DoIt/d_online/onSellBook.do?d_bonFillter=06">기타 서적</a></li>
	</ul>
	<ul class="my_side_ul">	
		<li><a href="/DoIt/d_online/onSellBook.do?d_bonFillter=07">아트지</a></li>
		<li><a href="/DoIt/d_online/onSellBook.do?d_bonFillter=08">레자크지</a></li>
		<li><a href="/DoIt/d_online/onSellBook.do?d_bonFillter=09">스노우지</a></li>
		<li><a href="/DoIt/d_online/onSellBook.do?d_bonFillter=10">모조지</a></li>
	</ul>
	<ul class="my_side_ul">
		<li><a href="/DoIt/d_online/onSellBook.do?d_bonFillter=11">국내도서</a></li>
		<li><a href="/DoIt/d_online/onSellBook.do?d_bonFillter=12">국외도서</a></li>
	</ul>

</article>



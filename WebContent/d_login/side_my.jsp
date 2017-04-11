<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<article id="my_side">
	<p class="side_top">마이페이지</p>
	<ul class="my_side_ul">
		<li><a href="/DoIt/d_login/myInfo.do">내 정보 보기</a></li>
		<li><a href="/DoIt/d_login/update.do">회원정보 수정</a></li>
		<li><a href="/DoIt/d_login/myCash.do">Å Cash 충전 및 내역</a></li>
		<li><a href="/DoIt/d_login/myList.do?cols=dr_rent">나의 주문 목록</a></li>
		<li><a href="/DoIt/d_login/delete.do">회원탈퇴</a></li>
		
		<ul class="my_side_ul">
			<li><a href="/DoIt/d_login/mySellList.do">판매 중인 책 조회</a></li>
		</ul>	
		<ul class="my_side_ul">
			<li><a href="/DoIt/d_login/user_BuyBookList.do">주문/배송 조회</a></li>
			<li><a href="/DoIt/d_login/user_BuyBook_CancelList.do">취소처리현황</a></li>
		</ul>
		
		<%-- 직거래-  판매자만 --%>
		<c:if test="${ 1 < sessionScope.memMG }">
			<li style="border-top:solid 2px black"><a href="/DoIt/d_resell/myReList.do">중고 직거래 리스트(판)</a></li>
			<li><a href="/DoIt/d_resell/modiIntro.do">판매자 소개글 변경(판)</a></li>
			<li><a href="/DoIt/d_bid/myBidBidList.do">경매내역(판)</a></li>
		</c:if>
	</ul>
</article>

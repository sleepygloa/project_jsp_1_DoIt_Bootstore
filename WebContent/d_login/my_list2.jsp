<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 
				
				<ul class="my_list_tab">
					<li class="click_tab2"><a href="/DoIt/d_login/myList.do?cols=dr_rent" class="ta_1a">나의 대여목록</a></li>
					<li class="click_tab1"><a href="/DoIt/d_login/myList.do?cols=dr_pan" class="ta_1a">주문/배송/취소 조회</a></li>
					<li class="click_tab2"><a href="/DoIt/d_login/myList.do?cols=dr_resell" class="ta_1a">관심상품 / 경매 내역</a></li>
				</ul>
				
				<%-- 본문 ---%>
				
				<%-- 직접판매 탭 --%>
				<ul class="sort_top">
					<li><a class="busw1" href="/DoIt/d_login/myList.do?cols=dr_pan">배송 / 주문 조회</a></li>
					<li><a class="busw2" href="/DoIt/d_login/myList.do?cols=dr_cencel">취소처리현황</a></li>
				</ul>
				
				
				<%-- 직접판매 내용 --%>
				<article class="list_cont_taa2">
				
					<%-- 결과에 따른 내용물 Include --%>
					<jsp:include page="/d_login/${noo}.jsp"></jsp:include>
					
				</article>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
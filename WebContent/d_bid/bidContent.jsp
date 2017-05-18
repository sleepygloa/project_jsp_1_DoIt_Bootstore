<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" type="text/css"  href="/DoIt/css/resell.css?ver=2323">
<script type="text/javascript" src="/DoIt/js/script.js"></script>

<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>


	<article class="my_cont_wrap2">
	<%-- 입힐 내용 --%>
		<header class="auc_cont_he">
		
	<%-- 사진 --%>
	<form action="/DoIt/d_bid/bidPro.do" method="post">
	<input type="hidden" name="bid_id" value="${bid_id}" />
	<input type="hidden" name="bid_no" value="${bid_no}" />
	<article class="left_auc_img fl_le">
	
		<p style="height:412px; overflow:hidden; border:solid 1px #eaeaea; box-sizing:border-box">
		<c:if test="${article.bid_pic == null}">
			<img src="/DoIt/images/null.PNG">
	   		
		</c:if>
		<c:if test="${article.bid_pic != null }">
	   		<img src="/DoIt/bid_pic/${article.bid_pic}">
	   		
		</c:if>
		</p>
		<%-- 판매자 버튼 제어 --%>
		<ul class="auc_con_but2">
		<c:if test="${bid_id.equals(article.bid_id) }">
			<li><a href="/DoIt/d_bid/bidModify.do?bid_no=${article.bid_no}&pageNum=${pageNum}">글수정</a></li>
			<li><a href="/DoIt/d_bid/bidDelete.do?bid_no=${article.bid_no}&pageNum=${pageNum}">글삭제</a></li>
		</c:if>
		<c:if test="${article.bid_finish == 0}">
			<li><a style="margin:0px;">판매중</a></li>
		</c:if>
		<c:if test="${article.bid_finish == 1}">
			<li><a style="margin:0px;">판매완료</a></li>
		</c:if>	
		</ul>
	</article>
	
	<%-- 내용 1 --%>
	<article class="right_auc_cont fl_le">
		<%-- 제목 --%>
		<ul class="auc_con_title1">
			<li>${article.bid_subject }</li>
			<li>조회수: ${article.bid_readcount}, 입찰수: ${article.bid_count}</li>
		</ul>
	
	<%-- 구매가 --%>
		<ul class="auc_con_title2 span_width">
			<li><span>도서명</span> ${article.bid_name} </li>
			<li><span>시작가</span> <strong>${article.bid_price1}</strong> 원</li>
			<li><span>현재가</span> <strong>${article.bid_price2}</strong> 원</li>
		</ul>
		
	<%-- 입찰시간 --%>
		<ul class="auc_con_time span_width">
			<li><span>경매종료시간</span> <a>${article.bid_finish_date}</a></li>
			<li><span>경매현재시간</span> <a>${sysdate1}</a></li>
			<c:if test="${article.bid_finish==0 }">
			<li><span>경매남은시간</span> <a>
				<fmt:formatNumber value="${(c/3600)%48-(((c/3600)%48)%1)}" pattern="0" />시간	
				<fmt:formatNumber value="${(c/60)%60-(((c/60)%60)%1)}" pattern="0" />분
				<fmt:formatNumber value="${c%60}" pattern="0" />초
			</a></li>
			</c:if>
			<c:if test="${article.bid_finish==1 }">
			<li><span>경매상태</span> <a>[ 낙찰된 상품 ]</a></li>
			<c:if test="${bid_id.equals(article.bid_id) }">
			<li><span>낙찰자 id</span> <a>${article.bid_last_id}</a></li>
			</c:if>
			</c:if>
		</ul>
		
	<%-- 버튼 제어 --%>
		<ul class="auc_con_but1">
		<c:if test="${bid_id==null}">
		<li>
		<input class="Bid_button" type="button" value="입찰하기" onclick="bidClick1()" />
		</li>
			
		</c:if>
		<c:if test="${bid_id!=null}">
			<c:if test="${article.bid_finish==0 }">
			<li>
				<c:if test="${!bid_id.equals(article.bid_id) }">
					<c:if test="${!bid_id.equals(article.bid_last_id) }">
						<input class="Bid_button" type="submit" value="입찰하기"/>
					</c:if>
					<c:if test="${bid_id.equals(article.bid_last_id) }">
						<input class="Bid_button" type="button" value="입찰하기" onclick="bidClick()"/>
					
					</c:if>
				</c:if>
			</li>
			</c:if>
		</c:if>
		<c:if test="${article.bid_finish==1 }">
			<li><a>낙찰된 상품</a></li>
		</c:if>
			<li><a href="/DoIt/d_bid/bidList.do">뒤로가기</a></li>
		</ul>
	</article>
		
	<%-- 판매자 정보 --%>
	<article class="fl_ri right_auc_pan">
		<p class="pan_title1" style="border-bottom:solid 2px #cfcfcf;">판매자 정보</p>
		<ul class="pan_ul pan_pan1 fl_ri">
			<li><p>${article.bid_id}</p></li>
			<li><p>구분 :
			<c:if test="${article1.d_person==0 }">개인회원</c:if>
			<c:if test="${article1.d_person==1 }">기업회원</c:if>
			</p>
			<p>등급 : 
<<<<<<< HEAD
			<c:if test="${article1.rbook_finish_count <=3}">씨앗등급</c:if>
=======
			<c:if test="${0== article1.rbook_finish_count  && article1.rbook_finish_count <=3}">씨앗등급</c:if>
>>>>>>> 06e83cdc0212d155692e1e75dda189dd861591c1
			<c:if test="${4<= article1.rbook_finish_count  && article1.rbook_finish_count <=7}">새싹등급</c:if>
			<c:if test="${8<= article1.rbook_finish_count  && article1.rbook_finish_count <=11}">실버등급</c:if>
			<c:if test="${12<= article1.rbook_finish_count }">골드등급</c:if>
			<br/>(등급은 직거래판매자 등급과 동일합니다.)</p></li>
		</ul>
		
		<p class="pan_title1"/>
				<ul class="pan_ul pan_pan2 fl_ri">
					<li class="text_center">
						<p><a href="/DoIt/d_resell/sellerContent.do?d_no=${article1.d_no}&pageNum=${pageNum}">판매자 정보 바로가기</a></p>
						
					</li>
				</ul>
				
		
	</article>
	</form>		
</header>
		
	<%-- 상세보기 내용 --%>
		<section class="auc_cont_sec">
		
			<p><img src="/DoIt/images/sang_gu.JPG" style="width:100%;"/>
			${article.bid_content}</p>
		
		</section>
		
		
	
	</article>   
	

<jsp:include page="/footer.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css"  href="/DoIt/css/online_admin.css?a=1">

<jsp:include page="/header.jsp" />

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_login/side_my.jsp" />

<article class="my_cont_wrap">
	
	
	<%-- 본문 제목 --%>
	<p class="my_title">
		마이 페이지
	</p>
	<p class="my_sub_title">
		판매중인 책 내용 상세페이지 입니다.
		<span>해당도서의 도서 내용을 출력합니다. 확인을 누를시 리스트로 이동합니다.</span>
	</p>
				

				
	<%-- 본문 내용 -------------------%>
	
	<%-- 도서 내용  ------------%>	
	<%-- 이미지 --%>
	<article class="fl_le det_pan_del1">
		<p>
			<c:if test="${dto.d_bpic == null}">
				<img src="/DoIt/images/ex_do.jpg" >
			</c:if>
			<c:if test="${dto.d_bpic != null}">
				<img src="/DoIt/d_bpic/${dto.d_bpic}" >
			</c:if>
		</p>
	</article>
	
	<%-- 도서 내용 --%>
	<article class="fl_ri det_pan_del2">
		<ul class="pan_deliu1">
			<li><span>책이름</span>${dto.d_bname}</li>
			<li><span>저자</span>${dto.d_bauthor}</li>
			<li><span>출판사</span>${dto.d_bpublisher}</li>
		</ul>
		
		<ul class="pan_deliu2">
			<li><span>장르</span>${dto.d_bgenre}</li>
			<li><span>종류</span>${dto.d_bgenre2}</li>
			<li><span>국내 / 외</span>${dto.d_blocation}</li>
			<li><span>출간날짜</span>${dto.d_bregistdate}</li>
		</ul>
		
		<ul class="pan_deliu3">
			<li><span>정가</span><a>${dto.d_bvalue}</a> 원</li>
			<li><span>판매가</span><a>${dto.d_bsellvalue}</a> 원</li>
			<li><span>결제금액</span><a class="co_red">${dto.d_bsellvalue}</a> 원</li>
		</ul>
	
	</article>
	
	
	<%-- 배송 정보 -------------%>		
	<article class="det_pan_del3">
		<p class="mana_Stitle fl_le">배송정보</p>
		
		<table class="lib_deli_tab" cellspacing="0">
			<colgroup>
				<col width="10%"><col width="10%"><col width="35%">
				<col width="15%"><col width="35%">
			</colgroup>
			<thead>
				<tr>
					<th>주문인</th>
					<th>받으시는 분</th>
					<th>주소</th>
					<th>휴대전화번호</th>
					<th>배송요청사항</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${id}</td>			
					<td>${DelDto.d_brecipient}</td>
					<td>${LogDto.d_addr}</td>
					<td>
						${LogDto.getUser_phone1()} - 
						${LogDto.getUser_phone2()} -
						${LogDto.getUser_phone3()}
					</td>
					<td>${DelDto.d_brequested}</td>
				</tr>
			</tbody>		
		</table>
		
		<%-- <p>결제 버튼을 누르면 결제가 완료됩니다.</p> 뒤로 이동--%>		
		<p class="button_cell">
			<a href="javascript:history.go(-1)" class="sub_button">확인</a>
		</p>
		
	</article>

				
			<!-- 	<section id="detail_btn_box">
					<input type="submit" value="수정 " class="detail_btn_modi">
					<input type="button" value="취소"  
					onclick="window.location='/DoIt/d_online/onSellBook.do'"  class="detail_btn_del">
				</section> -->

				

<!-- footer import -->
<%@include file="../footer.jsp" %>
		






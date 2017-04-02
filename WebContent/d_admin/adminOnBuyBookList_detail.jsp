<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css"  href="/DoIt/css/online_admin.css?a=1">

<jsp:include page="/header.jsp" />

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_admin/adminSide.jsp" />

<article class="my_cont_wrap">
	<form action="/DoIt/d_admin/adminOnBuyBookList.do" method="post">
			<div>
				<p id="OnBook_detail_title">${dto.d_bname}</p>
				<p class="detail_line"></p>
				
				<article id="detail_con">
					<section id="detail_left">
						<div class="detail_img">
							<img src="/DoIt/d_bpic/${dto.d_bpic}" alt="" class="">
							<input type="hidden" name="d_bpic" value="${dto.d_bpic}">
						</div>
						<!-- <p class="detail_left_line"></p> -->
						
					
					</section>
					<section id="user_buybook">
							<p class="detail_right_txt">책 정보</p>
							<div class="detail_right_txtBox">
									<p><span class="txtBox_spanTxt_1">주 문 인</span>  :   <span class="txtBox_spanTxt_2">${DelDto.d_bbuyer}</span></p>
									<p><span class="txtBox_spanTxt_1">책 이 름</span>  :   <span class="txtBox_spanTxt_2">${dto.d_bname}</span></p>
									<input type="hidden" name="d_bcode" value="${dto.d_bcode}">
									<p><span class="txtBox_spanTxt_1">저&nbsp;&nbsp;&nbsp; 자</span>  :   <span class="txtBox_spanTxt_2">${dto.d_bauthor}</span></p>
									<p><span class="txtBox_spanTxt_1">출 판 사</span> :   <span class="txtBox_spanTxt_2">${dto.d_bpublisher}</span></p>
									<p><span class="txtBox_spanTxt_1">장&nbsp;&nbsp;&nbsp; 르</span> :    <span class="txtBox_spanTxt_2">${dto.d_bgenre}</span></p>
									<p><span class="txtBox_spanTxt_1">종&nbsp;&nbsp;&nbsp; 류</span> :    <span class="txtBox_spanTxt_2">${dto.d_bgenre2}</span></p>
									<p><span class="txtBox_spanTxt_1">국 내/외</span> : <span class="txtBox_spanTxt_2">${dto.d_blocation}</span></p>
									<p><span class="txtBox_spanTxt_1">출간날짜</span> : <span class="txtBox_spanTxt_2"> ${dto.d_bregistdate}</span></p>
									<br/>
									<p><span class="txtBox_spanTxt_1">정&nbsp;&nbsp;&nbsp; 가</span> : <span class="txtBox_spanTxt_2">${dto.d_bvalue}</span> \</p>
									<p><span class="txtBox_spanTxt_1">판 매 가</span> : <span class="txtBox_spanTxt_2">${dto.d_bsellvalue}</span> \</p>
									<p><span class="txtBox_spanTxt_1">결제금액</span> : <span class="txtBox_spanTxt_2">${dto.d_bsellvalue}</span> \</p>
									
							</div>
							
					</section>
					</article>
				</div>
				
			<!-- 	<section id="detail_btn_box">
					<input type="submit" value="수정 " class="detail_btn_modi">
					<input type="button" value="취소"  
					onclick="window.location='/DoIt/d_online/onSellBook.do'"  class="detail_btn_del">
				</section> -->
				
				<p id="delivery_line"></p>
				
				<section id="user_delivery">
					<p id="user_delivery_title">배송정보</p>
					<table>
						<tr>
							<td>주문인</td>
							<td>${DelDto.d_bbuyer}</td>
							
						</tr>
						<tr>
							<td>받으시는 분</td>
							<td>${DelDto.d_brecipient}</td>
						</tr>
						<tr>
							<td>주소</td>
							<td>${LogDto.d_addr}</td>
						</tr>
						<tr>
							<td>휴대전화번호</td>
							<td>
								${LogDto.getUser_phone1()} - 
								${LogDto.getUser_phone2()} -
								${LogDto.getUser_phone3()}
							</td>
						</tr>
						<tr>
							<td>배송요청사항</td>
							<td>${DelDto.d_brequested}</td>
						</tr>
						
					</table>
					
					<p>결제 버튼을 누르면 결제가 완료됩니다.</p>					
					<input type="submit" value="확인">
				</section>
				
		</form>

</article>

<!-- footer import -->
<%@include file="../footer.jsp" %>
		






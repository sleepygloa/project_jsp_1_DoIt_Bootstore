<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css"  href="/DoIt/css/online_admin.css?a=1">

<jsp:include page="/header.jsp" />

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_admin/adminSide.jsp" />

<article class="my_cont_wrap">
	<form action="/DoIt/d_online/user_onBuyBookPro.do" method="post">
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
				<br />
				
				
				<input class="btn btn-default d-w-30" type="button" value="장바구니" />
			
				<div class="d-space100"></div>
				
				
				<c:if test="${user_check == null}">
					<input class="btn btn-default" type="button" value="배송정보가 회원 정보와 동일"
						onclick="window.location='/DoIt/d_online/user_onBuyBook.do?d_bcode=${dto.d_bcode}&user_check=yes'" />
				</c:if>
				<c:if test="${user_check != null}">
					<input class="btn btn-default btn-checked" type="button"  value="배송정보가 회원 정보와 동일" 
						onclick="window.location='/DoIt/d_online/user_onBuyBook.do?d_bcode=${dto.d_bcode}&user_check=yes'" />
				</c:if>
				<section id="user_delivery">
					<p id="user_delivery_title">배송정보 입력</p>
					<table>
						<tr>
							<td>주문인</td>
							<td>${id}</td>
							<input type="hidden" name="d_id" value="${id}" />
						</tr>
						<tr>
							<td>받으시는 분</td>
							<td><input type="text" name="d_brecipient"></td>
						</tr>
						<tr>
							<td>주소</td>
							<c:if test="${user_check == null}">
								<td><input type="text" name="d_addr" ></td>
							</c:if>
							<c:if test="${user_check != null}">
								<td><input type="text" name="d_addr" value="${LogDto.d_addr}"></td>
							</c:if>
						</tr>
						<tr>
							<td>휴대전화번호</td>
							<td>
							<c:if test="${user_check == null}">
								<input type="text" name="user_phone1" maxlength="3" /> - 
								<input type="text" name="user_phone2" maxlength="4" /> -
								<input type="text" name="user_phone3" maxlength="4" />
							</c:if>
							<c:if test="${user_check != null}">
								<input type="text" name="user_phone1" maxlength="3" value="${LogDto.getUser_phone1()}" /> - 
								<input type="text" name="user_phone2" maxlength="4" value="${LogDto.getUser_phone2()}"/> -
								<input type="text" name="user_phone3" maxlength="4" value="${LogDto.getUser_phone3()}" />
							</c:if>
							</td>
						</tr>
						<tr>
							<td>배송요청사항</td>
							<td><input type="text" name="d_brequested"></td>
						</tr>
						
					</table>
					
					<p>결제 버튼을 누르면 결제가 완료됩니다.</p>					
					<input class="btn btn-default" type="submit" value="결제하기">
				</section>
				
		</form>

</article>

<!-- footer import -->
<jsp:include page="/footer.jsp" />
		






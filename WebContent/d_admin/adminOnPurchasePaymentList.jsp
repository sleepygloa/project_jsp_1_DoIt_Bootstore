<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_admin/adminSide.jsp" />
${count}==${aoppl}
<article class="my_cont_wrap">
	<p class="my_title">
		계좌 관리
	</p>
	<p class="my_sub_title">
		관리자가 구매한 책들의 결제 정보 List를 보고, 상태를 변경 할 수 있습니다.
	</p>	
	<p class="L_title">
		<a class="d-left">구매한 책 결제진행 List</a>
	</p>
	<div class="d-right"><small style="color:gray">	

			<button class="btn btn-default"
				onclick="window.location='/DoIt/d_admin/adminOnPurchasePaymentList.do'" >전체보기</button>
			<input type="button" class="btn btn-default" 
				onclick="window.location='/DoIt/d_admin/adminOnPurchasePaymentList.do?aoppl=1'" value="결제대기만" />
			<button class="btn btn-default"
				onclick="window.location='/DoIt/d_admin/adminOnPurchasePaymentList.do?aoppl=2'">결제완료만</button>
			<button class="btn btn-default"
				onclick="window.location='/DoIt/d_admin/adminOnPurchasePaymentList.do?aoppl=3'">결제취소만</button>

	</small></div>
	
	<table class="info_ta2 d-center" cellspacing="0">
			<thead>
				<tr>
					<td>코드</td>
					<td>대상회원</td>
					<td>결제타입</td>
					<td>결제금액</td>
					<td>신청시간</td>
					<td>현황</td>
					
				</tr>
			</thead>
		<c:forEach var="payment"  items="${paymentList}">
			<tbody>
				<tr>
					<td>${payment.d_lcode}</td>
					<td>${payment.d_lreceiver}</td>	
					<td>
						<c:if test="${payment.d_ldealtype == 1}">입금</c:if>
						<c:if test="${payment.d_ldealtype == 2}">출금</c:if>
						<c:if test="${payment.d_ldealtype == 3}">무통장</c:if>
						<c:if test="${payment.d_ldealtype == 3}">계좌이체</c:if>
						<c:if test="${payment.d_ldealtype == 4}">신용카드</c:if>
						<c:if test="${payment.d_ldealtype == 5}">연체료</c:if>
					</td>
					<td>${payment.d_ldealmoney}원</td>
					<td>${payment.d_ldateS}</td>
					<td>
					<c:if test="${payment.d_ldealresult == 0}">
						<button class="btn btn-default"
						onclick="window.location='/DoIt/d_admin/adminOnPurchasePaymentList.do?pay_send=1&d_lno=${payment.d_lno}'" >결제 대기</button>				
					</c:if>
					<c:if test="${payment.d_ldealresult == 1}">
						<button class="btn btn-default" >결제 완료</button>				
					</c:if>
					<c:if test="${payment.d_ldealresult == 2}">
						<button class="btn btn-default" >결제 취소</button>				
					</c:if>					
					</td>
				</tr>
			</tbody>
		</c:forEach>
	
	</table>
</article>


<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>
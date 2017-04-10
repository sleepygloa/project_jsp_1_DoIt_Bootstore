<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_login/side_my.jsp"></jsp:include>

<script>
function acc_err(){
	alert('이미 계좌가 있습니다. 계좌는 개인당 1개의 계좌만 생성할 수 있습니다.');
	history.back;
}

function MyMoneyCheck(userinput){
	if (userinput.d_acMyMoney.value == "") {
	    alert("금액을 채워주세요.");
	}
	if (userinput.d_acMyMoney.value > d_lsummoney.value){
		alert("잔액보다 큰 금액을 출금 할 수 없습니다.");
	}
    history.go(-1);
}
</script>

<article class="my_cont_wrap">
	<p class="my_title">
		계좌 관리
	</p>
	<p class="my_sub_title">
		계좌 관리 페이지에서는 계좌 생성, D-Cash 입금, 출금, 거래 내역 검색을 이용하실 수 있습니다.
	</p>	
	<p class="L_title">
		<a class="d-left">${ldto.d_id}님 D-Cash 계좌 현황</a>
	</p>
	<div class="d-right"><small style="color:gray">	
			. 버튼클릭시 DoIt 가상계좌 D-cash가 자동 생성됩니다.</small>&nbsp;&nbsp;
 		<c:if test="${adto == null}">
			<button class="btn btn-default"
				onclick="window.location='/DoIt/d_login/myAccountList.do?d_acRequest=1'" >신규 계좌 등록</button>
		</c:if> 
		<c:if test="${adto != null}">
			<button class="btn btn-default" onclick="javascript:acc_err()">신규 계좌 등록</button>
		</c:if>  
			<form action="/DoIt/d_login/myAccountList.do?d_acRequest=2" method="post">
				<input type="text" name="d_acMyMoney" placeholder="금액을 입력하세요"  />
				<input type="submit"  value="충전하기" class="btn btn-default" onclick="MyMoneyCheck(this.form)" />
			</form>	
			<form action="/DoIt/d_login/myAccountList.do?d_acRequest=3" method="post">
				<input type="text" name="d_acMyMoney" placeholder="금액을 입력하세요"  />
				<input type="submit"  value="출금하기" class="btn btn-default" onclick="MyMoneyCheck(this.form)" />
			</form>			

	</div>
	<table class="info_ta2" cellspacing="0">
		<tbody>
			<tr>
				<td>계좌번호</td>
				<td>${adto.d_acnum}</td>
			</tr>
			<tr>
				<td>잔액</td>
				<td>
					<c:if test="${d_lsummoney != 0}">${d_lsummoney} 원</c:if>
					<c:if test="${d_lsummoney == 0}">0 원</c:if>
					<c:set var="d_lsummoney" value="${d_lsummoney}" />
				</td>
			</tr>
		</tbody>	
		
	</table>

	<p class="L_title">
		계좌 거래 내역
	</p>
	<table class="info_ta2 small-font8 d-center" cellspacing="0">
		<tbody>
			<tr>
				<td>연번</td>
				<td>거래날짜</td>
				<td>거래방법</td>
				<td>거래대상코드</td>
				<td>입금된 금액</td>
				<td>현재 잔액</td>
				<td>관련코드</td>
			</tr>
		</tbody>
		<c:forEach var="account" begin="0" end="${accountList.size()}"  items="${accountList}">
			<tbody>
				<tr>
					<td>
						<c:out value="${number}"/>
						<c:set var="number" value="${number+1}"/>
											</td>
					<td>${account.d_ldateS}</td>
					<td>
						<c:if test="${account.d_ldealtype == 1}">입금</c:if>
						<c:if test="${account.d_ldealtype == 2}">출금</c:if>
						<c:if test="${account.d_ldealtype == 3}">무통장</c:if>
						<c:if test="${account.d_ldealtype == 3}">계좌이체</c:if>
						<c:if test="${account.d_ldealtype == 4}">신용카드</c:if>
						<c:if test="${account.d_ldealtype == 5}">연체료</c:if>
					</td>
					<td>${account.d_lsender}</td>
					<td>${account.d_ldealmoney}원</td>
					<td>${account.listD_lsummoney}원</td>
					<td>${account.d_lcode}</td>
				</tr>
			</tbody>
		</c:forEach>
		
	</table>
	
	<!-- 페이지 번호 ------------------------------------------------------------------------ -->	


	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<a href="/DoIt/d_login/myaccountlist.do?pageNum=${i}">[${i}]</a>
	</c:forEach>
	

	
</article>
















<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>



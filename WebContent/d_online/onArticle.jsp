<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header import -->
<jsp:include page="/header.jsp" flush="false" /> 

<%--------------- 사이드 메뉴 include --------------%>
<%-- <jsp:include page="/d_online/onBookSide.jsp"></jsp:include> --%>

<form action ="/DoIt/d_online/onPurchaseInfo.do?d_bno=${d_bno}" method="post">
	<table border="1 solid black" width="1000px">
		<tr>
			<td colspan="2" width="1000px" height="80px">
				<span class="big-font35 d-bold d-left20">
					&nbsp;&nbsp;${article.d_bname}
				</span>
			</td>
		</tr>		
		<tr>
			<td width="300px">
				<img src="\DoIt\d_bpic/${article.d_bpic}" width="300px" />
			</td>
			<td>
				정가 : ${article.d_bvalue}<br />
				판매가 : ${article.d_bsellvalue}<br /> 
				배송료 : 무료<br />
				수령 예상일 : 0<br />
				주문 수량 : 1개<br />
			</td>
		</tr>
		<tr>
			<td colspan="2"><div class="jumbotron">
				<span>이 책을 갖고 있다면?</span><br />
				<div><input class="ghost-btn" type="button" value="책  판매"
				onclick="window.location='/DoIt/d_online/onSellForm.do?d_bno=${d_bno}&s=sellpage'" /></div>
				</div></td>
		</tr>
		<tr>
			<td class="d-center" colspan="2">
				<input class="btn btn-default d-w-30" type="button" value="장바구니" />
				<input class="btn btn-default d-w-30" type="submit" value="구매하기" />
				<input class="btn btn-default d-w-30" type="button" value="취소"
					onclick="window.location='/DoIt/d_online/onSellBook.do'" /> 
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td colspan="2"><p class="big-font30 d-left20">목차</p>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				${obiDto.d_norder}
			</td>
		</tr>
		<tr>
			<td colspan="2"><p class="big-font30 d-left20">내용</p>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				${obiDto.d_nintro}
			</td>
		</tr>		


		
		
	</table>



</form>








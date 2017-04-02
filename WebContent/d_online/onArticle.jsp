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
				<table class="d-margin"  width="80%">
					<tr>
						<td>정가 : ${article.d_bvalue}</td>
					</tr>
					<tr>
						<td>판매가 : ${article.d_bsellvalue}</td>
					</tr>
					<tr>
						<td>배송료 : 무료</td>
					</tr>
					<tr>
						<td>수령 예상일 :   (배송일로부터 3일이내)</td>
					</tr>										
				</table>				
				<br />

				<table class="d-center" border="1px solid black" width="80%">
					<tr>
						<td colspan="3">*.알라딘에 팔기 예상가</td>
					</tr>
					<tr>
						<td width="30%">최상</td>
						<td width="30%">상</td>
						<td width="30%">중</td>
					</tr>
					<tr>
						<td>${d_bpurchasevalueS} 원</td>
						<td>${d_bpurchasevalueA} 원</td>
						<td>${d_bpurchasevalueB} 원	</td>
					</tr>
				</table>
				
				
				
				
				
				
				
				
			</td>
		</tr>
		<tr>
			<td>
				<span>이 책을 갖고 있다면?</span>
			</td>
			<td>
				<input class="btn btn-default d-w-30" type="button" value="책  판매"
				onclick="window.location='/DoIt/d_online/onSellForm.do?d_bno=${d_bno}&s=sellpage'" />
			</td>
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
				<table width="90%" class="d-margin">
					<tr><td>${obiDto.d_norder}</td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2"><p class="big-font30 d-left20">내용</p>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<table width="90%" class="d-margin">
					<tr><td>${obiDto.d_nintro}</td></tr>
				</table>
			</td>
		</tr>		


		
		
	</table>



</form>








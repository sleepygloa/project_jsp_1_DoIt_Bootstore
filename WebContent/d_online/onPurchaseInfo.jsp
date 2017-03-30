<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header import -->
<jsp:include page="/header.jsp" flush="false" /> 

<%--------------- 사이드 메뉴 include --------------%>
<%-- <jsp:include page="/d_online/onBookSide.jsp"></jsp:include> --%>


<!-- 1. 선택된 책에 대한 상세 내용이 이 페이지에서 해결된다. -->
<form action ="/DoIt/d_login/myPurchaseInfo.jsp?d_bno=${d_bno}" method="post">
	<table border="1 solid black" width="1000px">
		<tr>
			<td colspan="2" width="1000px" height="80px">
				<span class="big-font35 d-bold d-left20">
					&nbsp;&nbsp;${article.d_bname}
				</span>
			</td>
		</tr>		
		<tr>
			<td width="300px" rowspan="2">
				<img src="\DoIt\d_bpic/${article.d_bpic}" width="300px" />
			</td>
			<td>
				작가:${article.d_bauthor}<br />
				출판사:${article.d_bpublisher}<br />
				장르:${article.d_bgenre}<br />
				종류:${article.d_bgenre2}<br />
				국내외:${article.d_blocation}<br />
				책등급:${article.d_bgrade}<br />
			</td>

		</tr>
		<tr>
			<td>
				새상품 정보 : (s등급 책 보여주기)
				정가 : ${article.d_bvalue}<br />
				판매가 : ${article.d_bsellvalue} (정가대비 얼마할인)<br /> 
				마일리지 : 결제 Db의 결제 금액의 10% 적립 <br />
				<br />
				-중고 상품 최저가 //알라딘 중고 // 회원 중고<br />
				--<br />
				<br />
				두잇에 팔기 예상가<br />
				s   /a   b /<br />
				0 / 0 / 0<br />
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

	</table>



</form>


<!-- 2. 이 페이지의 책과 관련된 책의 list가 정렬된다. -->
<table>
	<tr>
		<td>상품 정보, 등급  배송비  판매자 , 버튼</td>
	</tr>
	반복구문.
	<tr>
		<td></td>
	</tr>
</table>




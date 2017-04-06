<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header import -->
<jsp:include page="/header.jsp" flush="false" /> 

<%--------------- 사이드 메뉴 include --------------%>
<%-- <jsp:include page="/d_online/onBookSide.jsp"></jsp:include> --%>


<script>
function sellCanCountCheck(){
		alert("판매 가능한 책이없습니다. 대여 또는 직거래를 이용하시거나 사이트를 이용하시고 싶은분은 관리자에게 문의를 주세요")
		history.back();
}
</script>

<form action ="/DoIt/d_online/onPurchaseInfo.do?d_bcode=${article.d_bcode}" method="post">
	<table class="table-border" width="1000px">
		<tr>
			<td  colspan="2" width="1000px" height="80px" style="background-color:#e5e5e5; ">
				<span class="big-font35 d-bold d-left20" >
					&nbsp;&nbsp;${article.d_bname}
				</span>
			</td>
		</tr>		
		<tr>
			<td width="300px">
				<img src="\DoIt\d_bpic/${article.d_bpic}" width="300px" />
			</td>
			<td>
				<table class="d-margin"  width="80%" style="top:0px">
					<tr>
						<td>	
						<span class="big-font20">			
				작가 : ${article.d_bauthor}<br />
				출판사 : ${article.d_bpublisher}<br />
				장르 : ${article.d_bgenre}<br />
				종류 : ${article.d_bgenre2}<br />
				국내외 : ${article.d_blocation}<br />
				정가  :  ${article.d_bvalue} 원<br />
						</span>
						</td>
					</tr>										
				</table>				
				
				<div class="d-space20"></div>
			

				<table class="d-center big-font20" width="80%" style="border:1px solid gray">
					<tr >
						<td colspan="3"  class=" big-font30" style="background-color:#e5e5e5; ">
							<code>DoIT</code><small>에 팔기 예상가</small>
						</td>
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
				
				<div class="d-space20"></div>
				
				<table class="d-center" width="100%">
					<tr>
						<td class="d-w-33 d-margin" style="bottom:0px;">				
							<input class="btn btn-default d-w-100" type="button" value="책  판매"
								onclick="window.location='/DoIt/d_online/onSellForm.do?d_bno=${d_bno}'" /></td>

						<td class="d-w-33 d-margin" style="bottom:0px;">
							<c:if test="${sellCanCount == 0}">
								<input class="btn btn-default d-w-100" type="submit" value="구매하기(${sellCanCount})"
									onclick="javascript:sellCanCountCheck()" />
							</c:if>
							<c:if test="${sellCanCount != 0}">
								<input class="btn btn-default d-w-100" type="submit" value="구매하기(${sellCanCount})" />
							</c:if>

						</td>
						<td class="d-w-33 d-margin" style="bottom:0px;"><input class="btn btn-default d-w-100" type="button" value="취소"
								onclick="window.location='/DoIt/d_online/onSellBook.do'" /> </td>
					</tr>
				</table>				
		
			</td>
		</tr>
		<tr>
			<td>
				<div class="d-space20"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2"  style="background-color:#e5e5e5"><p class="big-font30 d-left20">목차</p>
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
			<td colspan="2"  style="background-color:#e5e5e5"><p class="big-font30 d-left20">내용</p>
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








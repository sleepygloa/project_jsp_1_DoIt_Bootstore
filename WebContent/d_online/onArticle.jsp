<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header import -->
<jsp:include page="/header.jsp" flush="false" /> 

<%--------------- 사이드 메뉴 include --------------%>
<%-- <jsp:include page="/d_online/onBookSide.jsp"></jsp:include> --%>

<form action ="/DoIt/d_online/onSellForm.do?d_bno=${d_bno}" method="post">
	<table border="1 solid black" width="1000px">
		<tr>
			<td colspan="2" width="1000px" height="100px">
				<span class="big-font35 d-bold d-left20">
					&nbsp;&nbsp;${article.d_bname}
				</span>
			</td>
		</tr>		
		<tr>
			<td colspan="2" width="1000px" height="80px">
				<span class="big-font20 d-left20">
					&nbsp;&nbsp;&nbsp;&nbsp;${article.d_bauthor} | ${article.d_bpublisher} | ${article.d_bdate} 
				</span> 
			</td>
		</tr>			
		
		
		<tr>
			<td width="300px">
<!-- 이미지 가 확실해질때 이미지 삽입은 지워주고 tablib 구문은 남겨둡니다 ------------------------------------------- -->
				<img src="\DoIt\d_bpic/${article.d_bpic}" width="300px" />
			
			</td>
			<td class="#" >
				<table class="d-center" style="width:500px" border="1 solid red">
					<tr>
						<td width="150px;">정가</td>
						<td>-------------------------&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${article.d_bvalue} 원</td>
											</tr>
				</table>
<!-- 회원의 책 판매 페이지에서 접근 했을 경우 보여지는 양식 ----------------------------------------------------- -->
				<c:if test="${pagetype !=null && pagetype == 'sellpage' }">
				<table class="d-center" style="width:500px" border="1 solid red">
					<tr>
						<td width="150px;">평균 구매가</td>
						<td>-------------------------&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${article.d_bpurchasevalue} 원</td>
						<input type="hidden" name="sellpage" value="sellpage" />
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
				</c:if>
<!-- 회원의 책 구매 페이지에서 접근 했을 경우 보여지는 양식 ----------------------------------------------------- -->				
				<c:if test="${pagetype !=null && pagetype == 'buypage' }">
				<table class="d-center" style="width:500px" border="1 solid red">
					<input type="hidden" name="buypage" value="buypage" />
					<for:each var="value" start="1" end="sellList.size()" step="1" items="sellList">
					<tr>
						<td width="150px;">평균 판매가</td>
						<td>-------------------------&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${value} 원</td>
					</tr>
					</for:each>				
					<tr>
						<td class="d-center" colspan="2">
							<input class="btn btn-default d-w-30" type="button" value="장바구니" />
							<input class="btn btn-default d-w-30" type="submit" value="구매하기" />
							<input class="btn btn-default d-w-30" type="button" value="취소"
								onclick="window.location='/DoIt/d_online/onSellBook.do'" /> 
						</td>
					</tr>

				</table>
				</c:if>
		
			</td>
		</tr>
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








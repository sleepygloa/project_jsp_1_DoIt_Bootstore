<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css"  href="/DoIt/css/online.css">

<!-- header import -->
<jsp:include page="/header.jsp" />

<jsp:include page="/d_customer/side_coustomer.jsp"></jsp:include>

<!-- content -->
<article class="my_cont_wrap">
	<p class="my_title">
		관리자용 문의 내역 전체 리스트
	</p>

	<p class="my_sub_title">
		문의 내역에 답변을 달기 위해 모든 문의의 리스트를 보여줍니다.
	</p>	
	<p class="L_title">
		<a class="d-left"></a>
	</p>
<!--------------------------------- 회원의 판매중인 List ------------------------------------->
	<table class="info_ta2 d-center" cellspacing="0">
		<thead>
			<tr>
				<td>문의 번호</td>
				<td>분류</td>
				<td>책 제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회</td>

			</tr>
		</thead>	
		<tbody>

		<!-- -------판매중인 책이 없을때----------------------------- -->
			<c:if test="${count==0}">
				<tr>
					<td colspan="7">문의 내역이 없습니다.</td>
				</tr>
			</c:if>
		<!-- ------판매신청중이 책이 있을 때----------------------------- -->	
			<c:if test="${count > 0}">	
			<form action="#" method="post">
				<c:forEach var="article"  items="${articleList}">
					<tr>
						<td>${article.c_ino}</td>
						<td>${article.c_itype}</td>
						<td>${article.c_isubject}</td>
						<td>${article.d_no}</td>
						<td>${article.c_idate}</td>
						<td>${article.c_readcount}</td>
			
					</tr>
				</c:forEach>
		 	</form>
			</c:if>
		</tbody>
	</table>			
			${count}
			

	
<!-- 페이지 번호 ------------------------------------------------------------------------ -->	
<center>
	<c:if test="${startPage>10}">
		<a href="/DoIt/d_login/mySellingList.do?pageNum=${startPage-5}" >[이전]</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<a href="/DoIt/d_login/mySellingList.do?pageNum=${i}">[${i}]</a>
	</c:forEach>
	
	
	<c:if test="${endPage<pageCount}">
		<a href="/jsp/board/list.nhn?pageNum=${startPage+5}">[다음]</a>
	</c:if>
</center>



</article>
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>




		
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css"  href="/DoIt/css/online.css">
<link rel="stylesheet" type="text/css" href="/jsp/s_board/css/style.css"/>
<!-- header import -->
<%@include file="../header.jsp" %>
	
	<p> 회원 판매 신청 리스트</p>
	
	<!-- -------등록된 책이 없을때----------------------------- -->
	<c:if test="${count==0}">
	<div class="d-center">
		<p>판매 신청 리스트가 없습니다.</p>
	</div>
	</c:if>
	<div class="rl"></div>

	
	<c:if test="${count != 0}">
	<table width="100%" border="1 solid black">	
	

		<c:forEach var="article"  items="${articleList}">	

				<tr>
					<td height="200px" > <!--  책이미지와 셀의 높이가 10px 커야함. thumbnail 적용 여백 -->
						<span class="big-font20">
							<c:out value="${number}"/>
							<c:set var="number" value="${number - 1}"/>
						</span>
					</td>
					<td height="200px" width="150px">
		 				<c:if test="${article.d_bpic == null}">
							<img src="../images/but-book.jpg" height="190px;" class="thumbnail" />
						</c:if>
						<c:if test="${article.d_bpic != null}">
							<img src="../images/ma_img.jpg" height="190px;" class="thumbnail" />
						</c:if>
					</td>
					<td height="200px" class="d-w80 ">
		<!--  책제목을 눌렀을 때 상세 페이지로 이동 ------------------------------------------------------------------------------ -->
		 				회원 아이디:${id} | 책 이름: ${article.d_bname}  |  저자 : ${article.d_bauthor} | 출판사 : ${article.d_bpublisher} | <br /><br />
						 
					</td>
				</tr>

		
		 
		</c:forEach>

	</table>
	</c:if>	
	
	<c:if test="${count > 0}">
	   <c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
	   <c:set var="pageBlock" value="${10}"/>
	   <fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
	   <c:set var="startPage" value="${result * 10 + 1}" />
	   <c:set var="endPage" value="${startPage + pageBlock-1}"/>
	   <c:if test="${endPage > pageCount}">
	        <c:set var="endPage" value="${pageCount}"/>
	   </c:if> 
	          
	   <c:if test="${startPage > 10}">
	        <a href="/DoIt/d_admin/onSellList_user.do?pageNum=${startPage - 10 }">[이전]</a>
	   </c:if>
	
	   <c:forEach var="i" begin="${startPage}" end="${endPage}">
	       <a href="/DoIt/d_admin/onSellList_user.do?pageNum=${i}">[${i}]</a>
	   </c:forEach>
	
	   <c:if test="${endPage < pageCount}">
	        <a href="/DoIt/d_admin/onSellList_user.do?pageNum=${startPage + 10}">[다음]</a>
	   </c:if>
	</c:if>




		
<!-- footer import -->
<%@include file="../footer.jsp" %>
		
		
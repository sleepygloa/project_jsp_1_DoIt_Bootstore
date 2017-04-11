<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="/DoIt/css/resell.css">

<!-- header import -->
<%@include file="/header.jsp" %>


<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_resell/side_resell.jsp"></jsp:include>
	
	
	<article class="my_cont_wrap">
	<%----------------- 판매자 정보 게시판---------------------------------------%>	
		
		<p class="my_title">판매자 회원 소개</p>
		<p class="my_sub_title"></p>
		<p>글개수:${count}<p>
		
		<c:if test="${count == 0 }">
			<p>회원정보가 없습니다</p>
		</c:if>
		<c:if test="${count > 0 }">
			
			<article class="rent_thum">
				<c:forEach var="article" items="${articleList}">
					
						<div class="thum_li">
							
								<p>
									<a href="/DoIt/d_resell/sellerContent.do?d_no=${article.d_no}&pageNum=${currentPage}">
									<c:if test="${article.d_pic == null }">
										<img src="/DoIt/images/ma_img.jpg" class="sellerPic">
									</c:if>
									<c:if test="${article.d_pic !=null }">
										<img src="/DoIt/save/${article.d_pic}" class="sellerPic">
									</c:if>
									</a>
								</p>
								<span class="seller_text">판매자 아이디 : </span><span class="seller_id co_red">${article.d_id}</span><br/>
								<span class="seller_text">판매자 소개 : </span><span class="seller_text">${article.rbook_intro}</span><br/>
								<span class="seller_text">신고당한 횟수 : </span><span class="seller_text">${article.report_count}</span><br/>
								<p></p>
							
							<p class="thum_bu">
								<a href="/DoIt/d_resell/sellerContent.do?d_no=${article.d_no}&pageNum=${currentPage}">상세보기</a>
							</p>
						</div>
					
				</c:forEach>
			</article>
			
		</c:if>
		
		
		<form action="/DoIt/d_resell/sellerList.do" class="search_bar">
			<p>
	   			<a><input type="text" name="search"/></a>
	   			<a><button type="submit" >검색</button></a>
   			</p>
   		</form>
   		
		
		<%-- 페이지 네이션 --%> 
		<c:if test="${count > 0}">
		<p class="num_tag">
		   <c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
		   <c:set var="pageBlock" value="${10}"/>
		   <fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
		   <c:set var="startPage" value="${result * 10 + 1}" />
		   <c:set var="endPage" value="${startPage + pageBlock-1}"/>
		   <c:if test="${endPage > pageCount}">
		        <c:set var="endPage" value="${pageCount}"/>
		   </c:if> 
		   
		         
		   <c:if test="${startPage > 10}">
		        <a href="/DoIt/d_resell/reList.do?pageNum=${startPage - 10 }">[ 이전 ]</a>
		   </c:if>

			   <c:forEach var="i" begin="${startPage}" end="${endPage}">
			       <a href="/DoIt/d_resell/reList.do?pageNum=${i}">[ ${i} ]</a>
			   </c:forEach> 
			
		   <c:if test="${endPage < pageCount}">
		        <a href="/DoIt/d_resell/reList.do?pageNum=${startPage + 10}">[ 다음 ]</a>
		   </c:if>
		   
		  </p>
		</c:if>
  		 
  		
   
   
   		
		
		
	</article>
<!-- footer import  -->
<%@include file="../footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.text.SimpleDateFormat" %>

<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="/DoIt/js/script.js"></script>
<script type="text/javascript" src="/DoIt/js/faq.js"></script>	

	<!-- header import -->
	<jsp:include page="/header.jsp"></jsp:include>
		
	<%--------------- 사이드 메뉴 include --------------%>
	<jsp:include page="/d_customer/side_customer.jsp"></jsp:include>
		

		<!-------------------- 게시판리스트 ------------------------------------ -->
		<article class="my_cont_wrap">
			
			<p class="my_title">FAQ</p>
			<p class="my_sub_title">
				FAQ
				
			</p>
			
			<%-- 글개수--%>
			<ul class="sort_top">
				<li><a>글목록(전체글:${count})</a></li>
				
				<li class="rig1"  class="showAll">
					<p><a class="showAll">답변 모두 여닫기</a></p>
				</li>
				<li class="rig1">
					<p><a href="/DoIt/d_customer/faqList.do">전체보기</a></p>
					<p><a href="/DoIt/d_customer/faqList.do?faq_type=0">중고서점</a></p>
					<p><a href="/DoIt/d_customer/faqList.do?faq_type=1">DOIT도서관</a></p>
					<p><a href="/DoIt/d_customer/faqList.do?faq_type=2">중고직거래/경매</a></p>
				</li>
			</ul>
			
			<%-- 게시글이 없을 경우 --%>
			<c:if test="${count == 0}">
				<table width="100%" cellspacing="0" class="rent_list">
					<tr>
						<td colspan="4">게시판에 저장된 글이 없습니다.</td>
					</tr>
				</table>
			</c:if>
			
			
			
			<%-- 게시글이 있을 경우 --%>
			<c:if test="${count > 0}">
			
					<ul class="faqBody">
					<c:forEach var="article" items="${articleList}">
						
							<%-- 번호 --%>
							
							<li class="faq">
								
								
			 						<p class="q">
			 							<img src="/DoIt/images/faq_1.jpg">
			 							<a>
			 							<c:if test="${article.faq_type==0}">[
			 								온라인중고서점
			 							]</c:if>
			 							<c:if test="${article.faq_type==1}">[
			 								DOIT도서관
			 							]</c:if>
			 							<c:if test="${article.faq_type==2}">[
			 								중고직거래/경매
			 							]</c:if>${article.faq_subject}</a>
			 							
			 							
			 							
			 							
			 						</p>
			 						<p class="a" style="display: none;" >
			 							<img src="/DoIt/images/faq_2.png">
			 						
			 							
			 							
			 							${article.faq_content}
			 							
			 							<c:if test="${dto.d_mech_grade == 10 }">
			 							<br/>
			 								<a class="faq_btn" href="/DoIt/d_customer/faqDelete.do?faq_no=${article.faq_no }&pageNum=${currentPage}">
				 								삭제
				 							</a>
			 								<a class="faq_btn" href="/DoIt/d_customer/faqModi.do?faq_no=${article.faq_no }&pageNum=${currentPage}">
			 									수정
				 							</a>
				 							
			 							</c:if>
			 							
			 						</p>
			 					
			 				</li>
			 			
			 			
			 			
					 </c:forEach>
			 		</ul>
			 
			 </c:if>
			 
			 	<form id="reList_wbtn">
			 		
			    	<c:if test="${sessionScope.memId != null && dto.d_mech_grade==10}">
			 		<input class="faq_Wbtn" type="button" onclick="document.location.href='/DoIt/d_customer/faqWriteForm.do'"  value="글쓰기"/>
			 		</c:if>
			 	</form>
			 	
			 <%-- 검색 입력 창 --%>
			<div class="faq_search">
			<form action="/DoIt/d_customer/faqList.do" method="POST" class="search_bar">
				<p>
					<a class="select_box">
						<select name="searchTitle" >
							<option value="faq_subject" >제목</option>
							<option value="faq_content" >내용</option>
						</select>
					</a>
					<a><input type="text" name="search"/></a>
					<a><button type="submit" >검색</button></a>
				</p>
			</form>
			</div>
			
			<%-- 페이지네이션 --%>
		  	<c:if test="${count > 0}">
			   <c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
			   <c:set var="pageBlock" value="${10}"/>
			   <fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
			   <c:set var="startPage" value="${result * 10 + 1}" />
			   <c:set var="endPage" value="${startPage + pageBlock-1}"/>
			   <c:if test="${endPage > pageCount}">
			        <c:set var="endPage" value="${pageCount}"/>
			   </c:if> 
			   
			   
			 <p class="num_tag">         
			   <c:if test="${startPage > 10}">
			        <a href="/DoIt/d_customer/faqList.do?pageNum=${startPage - 10 }">[ 이전 ]</a>
			   </c:if>

				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="/DoIt/d_customer/faqList.do?pageNum=${i}">[ ${i} ]</a>
				</c:forEach>
				
			   	<c:if test="${endPage < pageCount}">
			        <a href="/DoIt/d_customer/faqList.do?pageNum=${startPage + 10}">[ 다음 ]</a>
			   	</c:if>
			  </p> 
			  
			</c:if>
		</article>
		
		
		
	<!-- header import -->
	<jsp:include page="/footer.jsp"></jsp:include>
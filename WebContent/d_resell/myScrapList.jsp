<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.text.SimpleDateFormat" %>

<script type="text/javascript" src="/DoIt/js/script.js"></script>	

		<!-- header import -->
		<jsp:include page="/header.jsp"></jsp:include>
		
		
		<%--------------- 사이드 메뉴 include --------------%>
		<jsp:include page="/d_login/side_my.jsp"></jsp:include>
		
		
		<article class="my_cont_wrap">
			<%----------------- 마이스크랩 리스트---------------------------------------%>
			<p class="my_title">나의 관심상품</p>
			<p class="my_sub_title">
				관심상품 목록 입니다. 
				<span></span>
			</p>
			<ul class="sort_top">
				<li><a>글목록(전체글:${count})</a></li>
			</ul>
		
		

		<%-- 게시글이 없을 경우 --%>
			<c:if test="${count == 0}">
				<table width="100%" cellspacing="0" class="rent_list">
					<tr>
						<td colspan="4">나의 스크랩에 저장된 글이 없습니다.</td>
					</tr>
				</table>
			</c:if>
		
		<c:if test="${count > 0}">
		<table id="scrap">
					<tr>
		  				<td class="scrap_text1">스크랩번호</td>
		  				 <td class="scrap_text2">글번호</td>
		  				 <td class="scrap_text3">제목</td>
		  				 <td class="scrap_text4">판매가격(할인율)</td>
		  				 <td class="scrap_text5">판매여부</td>
		  				 <td class="scrap_text6">등록일</td>
		  			</tr>
		  <c:forEach var="article" items="${articleList}">
		  	
		  		
		  			
		  			
		  			<tr>
		  				<td class="scrap_text1">
							<c:out value="${number}"/>
		    				<c:set var="number" value="${number-1}"/>
						</td>
		  				 <td class="scrap_text2">${article.rbook_no }</td>
		  				 
		  				 <td class="scrap_text3">
		  				 	<a href="/DoIt/d_resell/reContent.do?rbook_no=${article.rbook_no }&pageNum=${currentPage}">
		  				 	${article.rbook_subject }</a></td>
		  				 <td class="scrap_text4">${article.rbook_price }(${article.rbook_price2 - article.rbook_price}할인)</td>
		  				 <td class="scrap_text5">
							<c:if test="${article.rbook_sell_check == 0}">
								<span>판매중</span>
							</c:if>
							<c:if test="${article.rbook_sell_check == 1}">
								<span>판매완료</span>
							</c:if>
						</td >
		  				 <td class="scrap_text6">${article.scrap_reg_date }</td>
		  				 <td class="">
		  				 	<a class="scrap_btn" href="/DoIt/d_resell/myScrapDelete.do?scrap_no=${article.scrap_no }">
		  					 	삭제
		  					</a>
		  				</td>
		  			</tr>
		  			
		  			
		  		
		  	
		  	
		  
		 
		  </c:forEach>
		  </table>
  		</c:if>

		</article>
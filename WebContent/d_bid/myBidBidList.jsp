<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_login/side_my.jsp"></jsp:include>
    <article class="my_cont_wrap">
    
    	<p class="my_title">나의경매 목록</p>
			<p class="my_sub_title">
				나의경매 목록 입니다. 개인 등급과 권한을 확인하세요.
				<span></span>
			</p>
			<ul class="sort_top">
				<li><a>글목록(전체글:${count})</a></li>
			</ul>
	

		<%-- 게시글이 없을 경우 --%>
			<c:if test="${count == 0}">
				<table width="100%" cellspacing="0" class="rent_list">
					<tr>
						<td colspan="4">나의 저장목록에 저장된 글이 없습니다.</td>
					</tr>
				</table>
			</c:if>
		
		<%-- 게시글이 있을 경우 --%>
			<c:if test="${count > 0}">
			<table class="rent_list" cellspacing="0">
				<tbody>
				  <c:forEach var="article" items="${articleList}">
				  	<tr style="overflow:hidden;">
				  		<%-- 번호 --%>
							<td style="position:relative;">
								<c:out value="${number}"/>
			    				<c:set var="number" value="${number-1}"/>
			    				
			    				<%-- 판매 완료 되었을 경우 --%>
								<c:if test="${article.bid_finish == 1}">
									<div id="finish_box">
										<div class="finish_box2">
											<p class="finish_text">
												<a href="/DoIt/d_bid/bidContent.do?bid_no=${article.bid_no }&pageNum=${currentPage}">
													낙찰
												</a>
											</p>
										</div>
									</div>
								</c:if>
			    			</td>
			    			
			    			<%-- 사진 --%>
							<td>
								<c:if test="${article.bid_pic == null}">
	   								<p><img src="/DoIt/images/mess_x.png" ></p>
				    			</c:if>
				    			<c:if test="${article.bid_pic != null}">
				    				<p>
		   								<a href="/DoIt/d_bid/bidContent.do?bid_no=${article.bid_no}&pageNum=${currentPage}">
		   									<img src="/DoIt/bid_pic/${article.bid_pic}" >
		   								</a>
		   							</p>
				    			</c:if>
							</td>
							
							<%-- 내용 --%>
							
							<td>
								<a href="/DoIt/d_resell/reContent.do?rbook_no=${article.bid_no }&pageNum=${currentPage}">
								
									<p>제목:${article.bid_subject}(${article.bid_name})</p>
									<p>
										<span>시작가: ${article.bid_price1} </span>
					    				<span>현재진행가: ${article.bid_price2}</span>
									</p>
									<p>판매자 : ${article.bid_id} 
					    		 	</p>
					    		 	<p>판매여부 : 
						    			<c:if test="${article.bid_finish == 0}">
											<span>입찰중</span>
										</c:if>
										<c:if test="${article.bid_finish == 1}">
											<span>낙찰</span>
										</c:if>
									</p>
									<p>등록일 : ${article.bid_reg_date}</p>
									<p>마지막 입찰자 : ${article.bid_last_id }</p>
									
								</a>
							</td>
							
							<%-- 스크랩 버튼 --%>
							<td>
								<c:if test="${article.bid_finish == 1}">
									<div id="relist_box3">
							    		<p><strong>입찰자 정보</strong></p>
										<p>아이디: ${article.d_id }</p>
										<p>이름: ${article.d_name }</p>
										<p>전화번호: ${article.d_phone }</p>
							    		<p>메일: ${article.d_mail }</p>
							    	</div>
							    </c:if>
							</td>
				  	</tr>
				 
				  
				  </c:forEach>
				 </tbody>
				</table>
  		</c:if>
  		
  		<%-- 검색 입력 창 --%>
   		<form action="/DoIt/d_bid/myBidBidList.do" class="search_bar">
   			<p>
	   			<a><input type="text" name="search" placeholder="책 제목을 검색하시오."/></a>
	   			<a><button type="submit" >검색</button></a>
   			</p>
   		</form>
   		
  			
		    
  		<%-- 댓글이 있을 경우 페이지네이션 --%>
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
		        <a href="/DoIt/d_bid/bidList.do?pageNum=${startPage - 10 }">[ 이전 ]</a>
		   </c:if>
		   
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
			    <a href="/DoIt/d_bid/bidList.do?pageNum=${i}">[ ${i} ]</a>
			</c:forEach>
			
		   <c:if test="${endPage < pageCount}">
		        <a href="/DoIt/d_bid/bidList.do?pageNum=${startPage + 10}">[ 다음 ]</a>
		   </c:if>
		   </p>
		</c:if>
  		
  		

	</article>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.text.SimpleDateFormat" %>
<link rel="stylesheet" type="text/css" href="/DoIt/css/resell.css">
<script type="text/javascript" src="/DoIt/js/script.js"></script>	

		<!-- header import -->
		<%@include file="/header.jsp" %>
		<%--------------- 사이드 메뉴 include --------------%>
		<jsp:include page="/d_login/side_my.jsp"></jsp:include>
		<article class="my_cont_wrap">	
		
		<!-------------------- 게시판리스트 ------------------------------------ -->
		<p id="reList_title">내가 입찰한 목록</p>
		<p id="reList_line"></p>
		<p>입찰목록(갯수:${count})</p>
		<table>
		  <tr>
		    <td align="right">
		    	
		    </td>
		  </tr>
		</table>

		<c:if test="${count == 0}">
			<table width="700" border ="1" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">
						입찰기록이 없습니다.
					</td>
				</tr>
			</table>
		</c:if>
		
		<c:if test="${count > 0}">
		
		
		  <c:forEach var="article" items="${articleList}">
		  <div id="relist_wrap">
		  		<div id="relist_box1">
		  		<c:out value="${number}"/>
		    	<c:set var="number" value="${number-1}"/>
		    	</div>
		    	<div id="relist_box2">
		    		<div class="relist_box2_img">
		    			<c:if test="${article.bid_pic == null}">
   							<img src="/DoIt/images/mess_x.png" height="190px">
		    			</c:if>
		    			<c:if test="${article.bid_pic != null }">
   							<a href="/DoIt/d_bid/bidContent.do?bid_no=${article.bid_no }&pageNum=${currentPage}">
   							<img src="/DoIt/bid_pic/${article.bid_pic}" height="190px">
		    			</c:if>
		    		</div>
		    		<div class="relist_box2_text">
			    		<p><a href="/DoIt/d_bid/bidContent.do?bid_no=${article.bid_no}&bid_finish_date=${article.bid_finish_date}&pageNum=${currentPage}">
			    		제목:${article.bid_subject} <br/>
			    		책명:${article.bid_name}</a></p>
			    		
			    		<span>시작가: ${article.bid_price1} </span>
			    		<span>현재진행가: ${article.bid_price2}</span> 
			    		
			    		<p>판매자 : ${article.bid_id}</p>
			    		<span>판매여부 : 
			    			<c:if test="${article.bid_finish == 0}">
								<span>판매중</span>
							</c:if>
							<c:if test="${article.bid_finish == 1}">
								<span>판매완료</span>
							</c:if>
						</span>
						<span> | 등록일 : ${article.bid_reg_date}<br/>
						| 마지막 입찰자:${article.bid_last_id }
						</span>
					</div>
		    	</div>
		    	<div id="relist_box3">
		    		<input type="button" value="버튼버튼"/>
		    	</div>
		    	
		    	
		  </div>
		  
		  </c:forEach>
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
		   
		 <div id="reList_paging">         
		   <c:if test="${startPage > 10}">
		        <a href="/DoIt/d_bid/bidList.do?pageNum=${startPage - 10 }">[이전]</a>
		   </c:if>
		   
			
			
			   <c:forEach var="i" begin="${startPage}" end="${endPage}">
			       <a href="/DoIt/d_bid/bidList.do?pageNum=${i}">[${i}]</a>
			   </c:forEach>
			
		   <c:if test="${endPage < pageCount}">
		        <a href="/DoIt/d_bid/bidList.do?pageNum=${startPage + 10}">[다음]</a>
		   </c:if>
		  </div> 
		</c:if>
  		
  		
   
   
   		<form action="/DoIt/d_bid/myBidList.do" id="reList_search">
   			<input type="text" name="search" placeholder="책 제목을 검색하시오."/>
   			<input type="submit" value="검색"/>
   		</form>
		 </article>
		<!-- footer import  -->
<%@include file="../footer.jsp" %>		
		
		
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.text.SimpleDateFormat" %>


<script type="text/javascript" src="/DoIt/js/script.js"></script>	

		<!-- header import -->
		<jsp:include page="/header.jsp"></jsp:include>
		
		
			<%--------------- 사이드 메뉴 include --------------%>
			<jsp:include page="/d_resell/side_resell.jsp"></jsp:include>
			<article class="my_cont_wrap">	
			
			<!-------------------- 게시판리스트 ------------------------------------ -->
			<%-- 제목 --%>
			<p class="my_title">중고 경매</p>
			<p class="my_sub_title">
				중고 경매 상품입니다. 입찰을 통해 구매하실 수 있습니다.
			</p>

			<%-- 개인 판매자 정보 --%>
			<ul class="sort_top">
				<li><a>글목록(전체글:${count})</a></li>
			</ul>
			

			<%-- 내용이 없을 때 --%>
			<c:if test="${count == 0}">
				<table cellspacing="0" class="">
					<tr>
						<td align="center">
							게시판에 저장된 글이 없습니다.
						</td>
					</tr>
				</table>
			</c:if>
		
			<%-- 내용이 한개 이상일 때 --%>
			<c:if test="${count > 0}">
				
				<%-- 썸네일 스타일 3개 = 1줄 --%>
				<article class="auction_wrap">
					<c:forEach var="article" items="${articleList}">
					<%-- 1개의 정보 --%>
					<ul class="auction_cont">
						<%-- 이미지 --%>
						<li>
							<%-- 번호 --%>
							<p class="abso_1">
								<c:out value="${number}"/>
					    		<c:set var="number" value="${number-1}"/>
							</p>
							<%-- 이미지 링크 --%>
							<c:if test="${article.bid_pic == null}">
		   						<p>
		   							<a href="/DoIt/d_bid/bidContent.do?bid_no=${article.bid_no }&pageNum=${currentPage}">
		   							
		   							<img src="/DoIt/images/null.PNG"></a></p>
				    		</c:if>
				    		<c:if test="${article.bid_pic != null }">
		   						<p><a href="/DoIt/d_bid/bidContent.do?bid_no=${article.bid_no }&pageNum=${currentPage}">
		   						<img src="/DoIt/bid_pic/${article.bid_pic}"></a></p>
				    		</c:if>
				    		<%-- 경매 상태 확인 --%>
				    		<p class="abso_2">
				    			<c:if test="${article.bid_finish == 0}">
									<span>판매중</span>
								</c:if>
								<c:if test="${article.bid_finish == 1}">
									<span>판매완료</span>
								</c:if>
				    		</p>
						</li>
						
						<%-- 내용 1--%>
						<li class="auc_part1">
							<p>제목 : ${article.bid_subject}</p>
							<p>책명 : ${article.bid_name}</p>
						</li>
						
						<%-- 내용 2--%>
						<li class="auc_part2">
							<p><span>현재가</span><strong>${article.bid_price2}</strong> 원</p>
							<p><span>시작가</span><a>${article.bid_price1} 원</a></p>
							<p>
								<span>등록일</span>
								<a><img src="/DoIt/images/time_put.png" class="s_imag">${article.bid_reg_date}</a>
							</p>
						</li>
						
						<%-- 등록한 사람 --%>
						<li class="auc_part3">
							<p><img src="/DoIt/images/pan_man.png" class="s_imag"><span>${article.bid_id}</span></p>
						</li>
					</ul>
					<%-- 1개의 정보 끝 --%>
					</c:forEach>
				</article>
				<%-- 썸네일 스타일 3개 = 1줄 끝 --%>
			
	  		</c:if>
  		
  			
  			<%-- 글쓰기 버튼 --%>
  			<form id="reList_wbtn">
		    	<c:if test="${sessionScope.memId == null }">
		    			<input type="button" onclick="reList_click()" value="글쓰기"/>
		    	</c:if>
		    	<%-- <c:if test="${sessionScope.memId != null && ${article.d_mech_grade == 2 }">--%>
		    	<c:if test="${sessionScope.memId != null}">
		    		<c:if test="${dto.d_mech_grade == 2 }">
		    			<input type="button" onclick="document.location.href='/DoIt/d_bid/bidWriteForm.do'"  value="글쓰기"/>
		    		</c:if>
		    		<c:if test="${dto.d_mech_grade == 0}">
		    			 <input type="button" onclick="bidList_click2()"  value="글쓰기"/>
		    		</c:if>
		    		<c:if test="${dto.d_mech_grade == 1}">
		    			 <input type="button" onclick="bidList_click3()"  value="글쓰기"/>
		    		</c:if>
		    	</c:if>
		    </form>
		    
		    <%-- 검색 --%>
	   		<form action="/DoIt/d_bid/bidList.do" class="search_bar">
	   			<p>
		   			<input type="text" name="search"/>
		   			<button type="submit">검색</button>
	   			</p>
	   		</form>
  			
  			<%-- 페이지 네이션 --%>
  			<p class="num_tag">
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
					<a href="/DoIt/d_bid/bidList.do?pageNum=${startPage - 10 }">[ 이전 ]</a>
				</c:if>
				   	
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="/DoIt/d_bid/bidList.do?pageNum=${i}">[ ${i} ]</a>
				</c:forEach>
					
				<c:if test="${endPage < pageCount}">
				    <a href="/DoIt/d_bid/bidList.do?pageNum=${startPage + 10}">[ 다음 ]</a>
				</c:if> 
			</c:if>
  			</p>
  		
   			
   		
   		
		 </article>
		 
		 
<!-- footer import  -->
<jsp:include page="/footer.jsp"></jsp:include>	
		
		
		
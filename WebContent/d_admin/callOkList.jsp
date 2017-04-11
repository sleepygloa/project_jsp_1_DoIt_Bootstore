<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<input type="hidden" name="d_id" value="${d_id}" />

	<p class="mana_Stitle">
		판매자신청List [ 갯수 : ${count} ]
	</p>
	
		<%-- 판매자를 신청한 사람이 없을경우 --%>
		<c:if test="${count == 0 }">
			<table class="board_wrap">
				<tr>
		    		<td colspan="3">
		    			판매자 신청한 사람이 없습니다.
		    		</td>
		   		 </tr>
			</table>
		</c:if>
		
		<%-- 판매자를 신청한 사람이 있을경우 --%>
		<c:if test="${count > 0 }">
	
			<table class="board_wrap" cellspacing="0">
				<colgroup>
					<col width="10%"><col width="50%"><col width="20%"><col width="20%">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>판매자 소개</th>
						<th>신청자 ID</th>
						<th>승인상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="article" items="${articleList}">
						<tr>
							<%-- 번호 --%>
							<td>
								<c:out value="${number}" />
			    				<c:set var="number" value="${number-1 }" />
			    			</td>
			    			
			    			<%-- 판매자 소개글 --%>
			    			<td>
			    				<a href="/DoIt/d_admin/callOkForm.do?rbook_introno=${article.rbook_introno}&pageNum=${currentPage}">
			     					 ${article.rbook_intro}
			     				</a>
			    			</td>
			    			
			    			<%-- 아이디 --%>
			    			<td>
			    				<a href="/DoIt/d_admin/callOkForm.do?rbook_introno=${article.rbook_introno}&pageNum=${currentPage}">
			      					${article.d_id}
			      				</a>
			    			</td>	
			    			
			    			<%-- 승인상태 --%>
			    			<td>
			    				<a>
			    					<c:if test="${article.d_mech_grade==1 }">승인대기중</c:if>
			    					<c:if test="${article.d_mech_grade==2 }">승인</c:if>
			    				</a>
			    			</td>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
		
		</c:if>
		
		
		<%-- 이동 버튼 --%>
		<p class="write_bu">
			<button onclick="window.location='/DoIt/d_admin/waitOkForm.do'" >승인 대기중 상태</button>
		</p>
	
	
	
	
		<%-- 아이디 검색 --%>
		<form action="/DoIt/d_admin/callOkList.do" class="search_bar">
			<p>
				<a><input type="text" name="search" placeholder="아이디를 검색하시오."/></a>
				<a><button type="submit" >검색</button></a>
			</p>
		</form>
		
		
		<%-- 페이지 네이션 --%>
		<c:if test="${count>0 }">
		<p class="num_tag">
			<c:set var="pageCount" 
			value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1) }" />
		   <c:set var="pageBlock" value="${10}"/>
		   <fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
		   <c:set var="startPage" value="${result * 10 + 1}" />
		   <c:set var="endPage" value="${startPage + pageBlock-1}"/>
		        <c:if test="${endPage > pageCount}">
			        <c:set var="endPage" value="${pageCount}"/>
			   </c:if>
			   
		        <c:if test="${ startPage>10 }">
		        	<a href="/DoIt/d_admin/callOkList.do?pageNum=${startPage - 10 }">[ 이전 ]</a>
				</c:if>
				<c:forEach var="i" begin="${ startPage}" end="${endPage}">
		        	<a href="/DoIt/d_admin/callOkList.do?pageNum=${ i }">[ ${ i } ]</a>
				</c:forEach>
				<c:if test="${endPage < pageCount }">
		        	<a href="/DoIt/d_admin/callOkList.do?pageNum=${ startPage + 10 }">[ 다음 ]</a>
				</c:if>
				
			</p>
			</c:if>








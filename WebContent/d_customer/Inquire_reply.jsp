<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
	<div class="tabWrap">
		<ul class="inquire_tabs">
			<li class="inquire_tab2"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire&mem=user">문의하기</a></li>
			<li class="inquire_tab1"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=wait">문의내역</a></li>
		</ul>
	</div>
	<div  class="reply_tabWrap">
		<ul class="reply_tabs">
			<c:if test="${reply.equals('wait')}">
				<li class="reply_tab1"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=wait">문의한 내역</a></li>
				<li class="reply_tab2"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=finish">답변완료내역</a></li>
			</c:if>
			<c:if test="${reply.equals('finish')}">
				<li class="reply_tab2"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=wait">문의한 내역</a></li>
				<li class="reply_tab1"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=finish">답변완료내역</a></li>
			</c:if>
		</ul>
	</div>

	<!--------------------------------- 회원의 판매중인 List ------------------------------------->
	<table class="inquire_table d-center" cellspacing="0">
		<thead>
			<tr class="inquire_tr">
				<td>번호</td>
				<td>분류</td>
				<td>답변여부</td> 
				<td>책 제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회</td>

			</tr>
		</thead>	
		<tbody>

		<!-- -------판매중인 책이 없을때----------------------------- -->
			<c:if test="${count == 0}">
				<tr>
					<td colspan="7">문의 내역이 없습니다.</td>
				</tr>
			</c:if>
		<!-- ------판매신청중이 책이 있을 때----------------------------- -->	
			<c:if test="${count > 0}">	
				<c:forEach var="article"  items="${articleList}">
					<tr>
						<td>${number}</td>
						<td>
							<c:if test="${article.c_itype == 0}">
								온라인 중고서점
							</c:if>
							<c:if test="${article.c_itype == 1}">
								도서관		
							</c:if>
							<c:if test="${article.c_itype == 2}">
								중고 직거래
							</c:if>
							<c:if test="${article.c_itype == 3}">
								주문/ 주문 변경
							</c:if>
							<c:if test="${article.c_itype == 4}">
								배송
							</c:if>
							<c:if test="${article.c_itype == 5}">
								회원
							</c:if>
						</td>
						<td>
							<c:if test="${reply.equals('wait')}">
								답변대기중
							</c:if>
							<c:if test="${reply.equals('finish')}">
								답변완료
							</c:if>
						</td>
					
						<td>
							<c:if test="${reply.equals('finish')}">
								 <c:if test="${article.re_level > 0}">
								  	<img src="images/level.gif" width="${5 * article.re_level}" height="16">
								    <img src="images/re.gif">
								 </c:if>
							 </c:if>
							 
							<a href="/DoIt/d_customer/InquireContent.do?c_ino=${article.c_ino}&reply=${reply}">${article.c_isubject}</a>
						</td>
						<td>${article.d_no}</td>
						<td>${article.c_idate}</td>
						<td>${article.c_readcount}</td>
			
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>			
	
	<div class="inquire_pageNum">
	<c:if test="${count > 0}">
			<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
			<c:set var="pageBlock" value="${10}"/>
			<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
			<c:set var="startPage" value="${result * 10 + 1}" />
			<c:set var="endPage" value="${startPage + pageBlock-1}"/>
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}"/>
			</c:if> 
		     
		    <c:if test="${reply.equals('wait')}">
				<c:if test="${startPage > 10}">
					<a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=wait&pageNum=${startPage - 10 }">[이전]</a>
				</c:if>
			
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=wait&pageNum=${i}">[${i}]</a>
				</c:forEach>
			
				<c:if test="${endPage < pageCount}">
					<a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=wait&pageNum=${startPage + 10}">[다음]</a>
				</c:if>
			</c:if>
		    <c:if test="${reply.equals('finish')}">
				<c:if test="${startPage > 10}">
					<a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=finish&pageNum=${startPage - 10 }">[이전]</a>
				</c:if>
			
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=finish&pageNum=${i}">[${i}]</a>
				</c:forEach>
			
				<c:if test="${endPage < pageCount}">
					<a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=finish&pageNum=${startPage + 10}">[다음]</a>
				</c:if>
			</c:if>			
		</c:if>
		</div>
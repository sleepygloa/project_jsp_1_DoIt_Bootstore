<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


		<!-- -------등록된 책이 없을때----------------------------- -->
		<c:if test="${count==0}">
			<table class="pan_list1">	
				<tr>
					<td colspan="4">구매 신청 리스트가 없습니다.</td>
				</tr>
			</table>
		</c:if>

		<c:if test="${count > 0}">
		
		<table class="pan_list1">	
			<c:forEach var="article"  items="${articleList}">	
					<tr>
						<%-- 책 번호 --%>
						<td > 
							<span class="big-font20">
								<c:out value="${number}"/>
								<c:set var="number" value="${number - 1}"/>
							</span>
						</td>
						
						<%-- 책 사진 --%>
						<td>
			 				<c:if test="${article.d_bpic == null}">
								<p>사진 정보가 없습니다.</p>
							</c:if>
							<c:if test="${article.d_bpic != null}">
								<img src="/DoIt/d_bpic/${article.d_bpic}"/>
							</c:if>
						</td>
						
						<%-- 책내용 --%>
						<td>
							<ul class="text_left list_con3">
								<li>주문인 : ${article.d_bbuyer}</li>
								<li>책 이름 : <span class="co_red">${article.d_bname}</span></li>
								<li>저자 : ${article.d_bauthor} | 출판사 : ${article.d_bpublisher} </li>
								<li>구매 신청 날짜 : ${article.d_bdeldate} </li>
								<li><input type="button" value="상세보기" class="AdminList_btn" onclick="window.location='/DoIt/d_admin/adminOnBuyBookList_detail.do?d_bno=${article.d_bno}&d_bcode=${article.d_bcode}'"/></li>
							</ul>
						</td>
						
						<%-- 책 제어 --%>
						<td> 
							<c:if test="${article.d_bdelibery == 20}">
								<input type="button" value="주문확인" 
								onclick="window.location='/DoIt/d_admin/adminOnBuyFinish.do?d_bcode=${article.d_bcode}&delivery=20'"/>
							</c:if>
							<c:if test="${article.d_bdelibery == 21}">
								<p>구매 완료.</p>
								<p>현재 배송 준비중</p>
								<p><input type="button" value="배송시작" 
								onclick="window.location='/DoIt/d_admin/adminOnBuyFinish.do?d_bcode=${article.d_bcode}&delivery=21'"/></p>
							</c:if>
							<c:if test="${article.d_bdelibery == 22}">
								<p>배송중</p>
								<p><input type="button" value="배송완료" 
								onclick="window.location='/DoIt/d_admin/adminOnBuyFinish.do?d_bcode=${article.d_bcode}&delivery=22'" /></p>
							</c:if>
							<c:if test="${article.d_bdelibery == 25}">
								<p>취소 완료</p>
							</c:if>
						</td>
					</tr>	
	
			
			</c:forEach>
			
		</table>
		</c:if>	
		
		
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
					<a href="/DoIt/d_manage/manPart1.do?pageNum=${startPage - 10 }">[ 이전 ]</a>
				</c:if>
			
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="/DoIt/d_manage/manPart1.do?pageNum=${i}">[ ${i} ]</a>
				</c:forEach>
			
				<c:if test="${endPage < pageCount}">
					<a href="/DoIt/d_manage/manPart1.do?pageNum=${startPage + 10}">[ 다음 ]</a>
				</c:if>
			</c:if>
		</p>

		


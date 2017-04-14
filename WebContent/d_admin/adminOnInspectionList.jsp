<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	
	<!-- -------등록된 책이 없을때----------------------------- -->
	<c:if test="${count2==0}">
		<table class="pan_list1">	
			<tr>
				<td colspan="4">판매 신청 리스트가 없습니다.</td>
			</tr>
		</table>
	</c:if>
	
	
	<!-- -------등록된 책이 있을때----------------------------- -->
	<c:if test="${count2 > 0}">
	<table class="pan_list1">	
	<c:forEach var="article2"  items="${articleList2}">	
		<%-- <form action="/DoIt/d_admin/onInspection.do" method="post">--%>
		<tr>
			<%-- 번호 --%>
			<td> 
				<span>
					<c:out value="${number2}"/>
					<c:set var="number2" value="${number2 - 1}"/>
				</span>
			</td>
			
			<%-- 사진 --%>
			<td>
 				<c:if test="${article2.d_bpic == null}">
					<p>사진 정보가 없습니다.</p>
				</c:if>
				<c:if test="${article2.d_bpic != null}">
					<img src="/DoIt/d_bpic/${article2.d_bpic}"  />
				</c:if>
			</td>
			
			<%-- 내용 --%>
			<td>
				<ul class="text_left list_con3">
					<li>책 이름 : ${article2.d_bname}</li>
					<li>회원 아이디 : ${article2.d_id} </li>
					<li>저자 : ${article2.d_bauthor} | 출판사 : ${article2.d_bpublisher}</li>
					<li>판매신청 날짜 : ${article2.d_bdate}</li>
					<%-- <input type="hidden" name="d_bcode" value="${article.d_bcode}">--%>
				</ul>		
			</td>
			
			<%-- 검수제어 --%>
			<td> 
				<c:if test="${article2.d_sfinish == 1}">
		 			<c:if test="${article2.d_bdelibery == 13}" >
						<input type="submit" value="검수하기" 
						onclick="window.location='/DoIt/d_manage/manPart4.do?d_bcode2=${article2.d_bcode}&gum_li=write' "/> 
						<%--<input type="hidden" name="d_bcode" value="${article.d_bcode}" />--%>
		 			</c:if>
				</c:if>
			
			</td>

		</tr>	
	</c:forEach>
		

	</table>
	</c:if>
	
	
	
	<%-- 페이지 네이션 --%>
	<p class="num_tag">
		<c:if test="${count2 > 0}">
				<c:set var="pageCount2" value="${count2 / pageSize2 + ( count2 % pageSize2 == 0 ? 0 : 1)}"/>
				<c:set var="pageBlock2" value="${10}"/>
				<fmt:parseNumber var="result2" value="${currentPage2 / 10}" integerOnly="true" />
				<c:set var="startPage2" value="${result2 * 10 + 1}" />
				<c:set var="endPage2" value="${startPage2 + pageBlock2-1}"/>
				<c:if test="${endPage2 > pageCount2}">
					<c:set var="endPage2" value="${pageCount2}"/>
				</c:if> 
			          
				<c:if test="${startPage2 > 10}">
					<a href="/DoIt/d_admin/adminOnInspectionList.do?pageNum2=${startPage2 - 10 }">[ 이전 ]</a>
				</c:if>
			
				<c:forEach var="i" begin="${startPage2}" end="${endPage2}">
					<a href="/DoIt/d_admin/adminOnInspectionList.do?pageNum2=${i}">[ ${i} ]</a>
				</c:forEach>
			
				<c:if test="${endPage2 < pageCount2}">
					<a href="/DoIt/d_admin/adminOnInspectionList.do?pageNum2=${startPage2 + 10}">[ 다음 ]</a>
				</c:if>
			</c:if>
		</p>
		

		
		
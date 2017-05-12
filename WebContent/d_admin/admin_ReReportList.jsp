<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


					<%-- 각 파트 제목 --%>
						<div style="overflow:hidden;">
							<p class="mana_Stitle">신고받은 판매자 리스트</p>
							<%-- 아이디 검색 --%>
							<form action="/DoIt/d_manage/manPart3.do" class="search_bar fl_ri"  style="padding:0px; margin:0px;">
								<p>
						   			<a><input type="text" name="search" placeholder="판매자를 검색하세요."/></a>
						   			<a><button type="submit" >검색</button></a>
					   			</p>
					   		</form>
						</div>
						
						<%-- 연체 리스트 탭  --%>
						<ul class="sort_top">

						</ul>
		
		<%-- 신고정보가 없을 때 --%>
		<c:if test="${count == 0 }">
			<p>신고 정보가 없습니다</p>
		</c:if>
		
		
		<%-- 신고정보가 있을 때 --%>
		<c:if test="${count > 0 }">
			
				<c:forEach var="report" items="${articleList}">
					<div id="report_list_wrap">
					
							<a href="/DoIt/d_admin/reportDelete.do?rbook_id=${report.rbook_id}" class="fl_ri wid_he">판매자권한 박탈</a>
							<div class="subs_tit">
								<p>신고 번호	: ${report.report_no}</p>
								<p>신고자 id	: <a style="color:blue">${report.report_id }</a></p>
								<p>신고 날짜	: ${report.report_reg_date}</p>
								<p>신고 내용    : ${report.report_content }</p>
								<p>신고 대상글</p>
							</div>
							
							<table class="report_tab" cellspacing="0">
							<colgroup>
								<col width="6%"><col width="40%"><col width="15%">
								<col width="10%"><col width="19%">
							</colgroup>
								<thead>
									<tr>
										<th>글번호</th>
										<th>글제목</th>
										<th>작성자</th>
										<th>조회수</th>
										<th>작성일</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${report.rbook_no}</td>
										<td><a href="/DoIt/d_resell/reContent.do?rbook_no=${report.rbook_no }&pageNum=${currentPage}">${report.rbook_subject}</a></td>
										<td class="subs_tit"><span>${report.rbook_id}</span></td>
										<td>${report.rbook_readcount}</td>
										<td>${report.rbook_reg_date}</td>	
									</tr>
								</tbody>
							</table>
						
						</div>	

				</c:forEach>

		</c:if>
		
		
		
		
		
		<%-- 페이지 네이션 --%>
		<c:if test="${count > 0}">
			<p class="num_tag" style="padding:0px;">
			   <c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
			   <c:set var="pageBlock" value="${10}"/>
			   <fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
			   <c:set var="startPage" value="${result * 10 + 1}" />
			   <c:set var="endPage" value="${startPage + pageBlock-1}"/>
			   <c:if test="${endPage > pageCount}">
			        <c:set var="endPage" value="${pageCount}"/>
			   </c:if> 
			          
			   <c:if test="${startPage > 10}">
			        <a href="/DoIt/d_manage/manPart3.do?pageNum=${startPage - 10 }">[ 이전 ]</a>
			   </c:if>
			
			   <c:forEach var="i" begin="${startPage}" end="${endPage}">
			       <a href="/DoIt/d_manage/manPart3.do?pageNum=${i}">[ ${i} ]</a>
			   </c:forEach>
			
			   <c:if test="${endPage < pageCount}">
			        <a href="/DoIt/d_manage/manPart3.do?pageNum=${startPage + 10}">[ 다음 ]</a>
			   </c:if>
		   </p>
		</c:if>






 
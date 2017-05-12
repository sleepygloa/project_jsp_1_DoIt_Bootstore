<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css"  href="/DoIt/css/resell.css">

	<!-- header import -->
	<jsp:include page="/header.jsp"></jsp:include>
		
	<%--------------- 사이드 메뉴 include --------------%>
	<jsp:include page="/d_customer/side_customer.jsp"></jsp:include>

	<article class="my_cont_wrap">
	
	<p class="my_title">공지사항</p>
	<p class="my_sub_title"></p>
			
	<form action="/DoIt/d_customer/noticeWriteForm.do" method="post" >
 		<input type="hidden" name="d_id" value="${d_id}" />

			<%-- 글개수--%>
			<ul class="sort_top">
				<li><a>글목록(전체글:${count})</a></li>
			</ul>
	
		<table class="list_con_wrap2" cellspacing="0">
               <colgroup>
                  <col width="10%"><col width="50%"><col width="30%"><col width="10%">
                 
               </colgroup>
                  <thead>
                     <tr>
                        <th>번호</th>
                        <th>공지사항제목</th>
                        <th>작성일</th>
                        <th>조회수</th>
                     </tr>
                  </thead>
                  <tbody>
                  	<c:if test="${ count == 0 }">
                  		<tr>
                  			<td colspan="12">내용이 없습니다.</td>
                  		</tr>
                  	</c:if>
                  	
                  	<c:if test="${ count>0 }">
                     <c:forEach var="article" items="${ articleList}">
                     <tr>
                        <td><a><c:out value="${number}"/>
					    	<c:set var="number" value="${number-1}"/></a>
					    </td>
                        <td><a href="/DoIt/d_customer/noticeContent.do?notice_no=${article.notice_no}&pageNum=${currentPage}">
      						${article.notice_name}</a>
      					</td>
                        <td><a>${article.notice_reg_date}</a></td>
                        <td><a>${article.notice_readcount}</a></td>
                        
                     </tr>
                     </c:forEach>
                     </c:if>
                  </tbody>
            </table>
            
		
	
		<input type="button" value="리스트로" onclick="window.location='/DoIt/d_customer/noticeList.do'" />
			<c:if test="${dto.d_mech_grade==10}">
				<input type="submit" value="글쓰기"  />
			</c:if>
		</form>
		
		<%-- 검색바 --%>
		<form action="/DoIt/d_customer/noticeList.do" class="search_bar" method="POST">
			<p>
				<a><input type="text" name="search" placeholder="제목을 검색하시오." /></a>
				<a><button type="submit" >검색</button></a>
			</p>
		</form>
		
		<%-- 페이지 네이션 --%>
		<c:if test="${count>0 }">
		<p class="num_tag">
			<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1) }" />
	   		<c:set var="pageBlock" value="${10}"/>
	   		<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
	   		<c:set var="startPage" value="${result * 10 + 1}" />
	   		<c:set var="endPage" value="${startPage + pageBlock-1}"/>
	        <c:if test="${endPage > pageCount}">
		        <c:set var="endPage" value="${pageCount}"/>
		   	</c:if>
	        <c:if test="${ startPage>10 }">
	        
	        <a href="/DoIt/d_customer/noticeList.do?pageNum=${startPage - 10 }">[ 이전 ]</a>
			</c:if>
			
			<c:forEach var="i" begin="${ startPage}" end="${endPage}">
	        	<a href="/DoIt/d_customer/noticeList.do?pageNum=${ i }">[ ${ i } ]</a>
			</c:forEach>
			<c:if test="${endPage < pageCount }">
	        	<a href="/DoIt/d_customer/noticeList.do?pageNum=${ startPage + 10 }">[ 다음 ]</a>
			</c:if>
		</p>
		</c:if>
		
		
</article>   

<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>
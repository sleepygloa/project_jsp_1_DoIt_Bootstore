<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- rent_side include --------------%>
<jsp:include page="rent_side.jsp"></jsp:include>



	<%----------- 본문 시작 --------------------%>
	
	<article class="my_cont_wrap">
		<p class="my_title">
			DOIT 도서관 서비스
		</p>
		<p class="my_sub_title">
			Doit 도서관에서는 3가지 서비스를 제공하고 있습니다. [ 도서 대출, 도서 기부 신청, 반납 신청 ]
			<span>도서목록 : 리스트 스타일과 썸네일 스타일을 모두 제공하고 있으므로, 편하신 방법으로 보시기 바랍니다. </span>
		</p>

		<%-- 정렬 순서 지정 --%>
		<ul class="sort_top">
			<li><a href="/DoIt/d_rent/list_cont.do?view_type=${ view_type }&sort=br_no">번호순</a></li>
			<li><a href="/DoIt/d_rent/list_cont.do?view_type=${ view_type }&sort=br_name">이름순</a></li>
			<li><a href="/DoIt/d_rent/list_cont.do?view_type=${ view_type }&sort=d_bno">장르순</a></li>
			<li><a href="/DoIt/d_rent/list_cont.do?view_type=${ view_type }&sort=br_date">등록일순</a></li>
			<li class="rig1">
				<p><a href="/DoIt/d_rent/list_cont.do?view_type=list_cont"><img src="/DoIt/images/list_bu.jpg" /></a></p>
				<p><a href="/DoIt/d_rent/list_cont.do?view_type=thum_cont"><img src="/DoIt/images/thum_bu.jpg" /></a></p>
			</li>
		</ul>
		
		<%-- 리스트 테이블 --%>
		
			
			<%-- 페이지 템플릿화 --%>
			<jsp:include page="${ view_type }.jsp"></jsp:include>
		
		
		
		<c:if test="${ sessionScope.memMG == 10 }">
			<p class="write_bu">
				<button type="button" onclick="window.location='/DoIt/d_rent/b_write.do?gugu=1' ">글쓰기</button>
			</p>
		</c:if>
		
		<%-- 검색 폼 --%>
		<form action="/DoIt/d_rent/b_search.do" method="POST" class="search_bar">
			<p>
				<a class="select_box">
					<select name="searchSelect" >
						<option value="br_name" >제목</option>
						<option value="br_writer" >저자</option>
						<option value="br_pub" >출판사</option>
						<option value="br_cont" >내용</option>
					</select>
				</a>
				<a><input type="text" name="searchWord" class="" /></a>
				<a><button type="submit" >검색</button></a>
			</p>
		</form>
		
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
	                 <a href="list_cont.do?view_type=${ view_type }&pageNum=${startPage - 10 }">[이전]</a>
	            </c:if>
	         
	            <c:forEach var="i" begin="${startPage}" end="${endPage}">
	                <a href="list_cont.do?view_type=${ view_type }&pageNum=${i}">[ ${i} ]</a>
	            </c:forEach>
	         
	            <c:if test="${endPage < pageCount}">
	                 <a href="list_cont.do?view_type=${ view_type }&pageNum=${startPage + 10}">[다음]</a>
			   </c:if>
			</c:if>
			
		</p>
		
	</article>
	
	<br>





<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>		
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<<<<<<< HEAD
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
=======
    
>>>>>>> 06e83cdc0212d155692e1e75dda189dd861591c1
    
    
<article id="my_side">
	<p class="side_top">DOIT 도서관</p>
	<ul class="my_side_ul">
		<li><a href="/DoIt/d_rent/list_cont.do?view_type=list_cont">DoIt 도서관</a></li>
<<<<<<< HEAD
		<li>
			<c:if test="${sessionScope.memId != null }">
				<a href="/DoIt/d_login/myList.do?cols=dr_rent">도서 반납 신청</a>
			</c:if>
			<c:if test="${sessionScope.memId == null }">
				<a href="#">도서 반납 신청</a>
			</c:if>
		</li>
=======
		<li><a href="#">도서 반납 신청</a></li>
>>>>>>> 06e83cdc0212d155692e1e75dda189dd861591c1
		<li><a href="/DoIt/d_rent/b_write.do">도서 기증 신청</a></li>
	</ul>
</article>
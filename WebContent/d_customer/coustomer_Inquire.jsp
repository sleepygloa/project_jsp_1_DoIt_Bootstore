<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="/DoIt/css/coustomer.css">

<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>
	
<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_coustomer/side_coustomer.jsp" />

	<article class="my_cont_wrap">
		<p id="Inquire_title">1 : 1 문의 신청</p>
		<p></p>


		<jsp:include page="/d_coustomer/${inquire}.jsp"></jsp:include>
	</article>
		
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>
	
	
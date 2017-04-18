<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<script type="text/javascript" src="/DoIt/js/Inquire.js"></script>
<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_customer/side_customer.jsp" />


	<article class="my_cont_wrap">

	<p class="my_title">1 : 1 문의 신청</p>
	<p class="my_sub_title"> </p>
			
	<jsp:include page="/d_customer/${inquire}.jsp"></jsp:include>
	</article>
		
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>
	
	
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css"  href="/DoIt/css/resell.css">

<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>
<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_resell/side_resell.jsp"></jsp:include>
<article class="my_cont_wrap">
	<form method="post" name="reDelete" action="reDeletePro.do?rbook_no=${rbook_no}&pageNum=${pageNum}">
		<div>
			<p>삭제 하시겠습니까?</p>
			<input type="submit" value="삭제" />
			<input type="button" value="취소" onclick="javascript:history.back(1);">
			<!-- input type="button" value="취소" onclick="'/DoIt/d_resell/reList.do?pageNum=${pageNum}'"/-->
		</div>
	</form>
</article>   
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>
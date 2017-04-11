<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${check == true}">	
	<script>
		alert("판매완료로 변경되었습니다");
		
		//window.location="/DoIt/d_resell/callOkList.do";
	</script>
	<meta http-equiv='refresh' content='0;url=/DoIt/d_resell/reContent.do?rbook_no=${rbook_no}&pageNum=${pageNum}'>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	alert("신고가 완료 되었습니다");
	window.location="/DoIt/d_resell/reContent.do?rbook_no=${rbook_no}&pageNum=${pageNum}&report_id2=${report_id2}";
</script>


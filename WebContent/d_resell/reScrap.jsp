<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- c:redirect uri="reList.do?&pageNum=${pageNum}"/--%>
<script>
	alert("스크랩이 완료 되었습니다");
	window.location="/DoIt/d_resell/reList.do?&pageNum=${pageNum}";
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	 <c:if test="${result == true }">
	    <script>
			alert("삭제되었습니다.");
			window.location="/DoIt/d_manage/manPart3.do";
		</script>
	</c:if>
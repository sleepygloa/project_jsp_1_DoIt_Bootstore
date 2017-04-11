<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" name="d_id" value="${d_id}" />
<c:if test="${ c == true }">
	<script>
		alert("판매자권한이 신청되었습니다.");
		window.location="/DoIt/d_login/login.do";
	</script>
</c:if>

<c:if test="${ c == false }">
	<script>
		alert("권한변경에 실패하였습니다.");
		history.go(-1);
	</script>

</c:if>
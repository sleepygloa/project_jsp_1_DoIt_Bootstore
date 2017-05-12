<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<c:if test="${check == 1 }">
			<script>
				alert("탈퇴 되었습니다..!");
				window.location="/DoIt/d_login/login.do";
			</script>
		</c:if>
		<c:if test="${check == 0 }">
			<script>
				alert("pw를 확인하세요...!");
				history.go(-1);
			</script>
			
		</c:if>

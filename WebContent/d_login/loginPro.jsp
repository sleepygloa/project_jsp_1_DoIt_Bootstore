<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<!-- ------------------------------controller-------------------------------------- -->
<!-- ----check == true 로그인 성공 ------------------------- -->
<c:if test="${check == true}" >
	<meta http-equiv="Refresh" content="0;url=/DoIt/d_login/login.do" >

</c:if>
<!-- ----check == false 로그인 실패 ---------다시 header 페이지로----------- -->
<c:if test="${check == false}" >
	<script>
		alert("아이디와 비밀번호가 맞지않습니다. 다시 확인해주세요.");
	    history.go(-1);
	</script>
</c:if>
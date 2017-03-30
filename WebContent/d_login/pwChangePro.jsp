<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/header.jsp" flush="false" /> 

	
	<c:if test="${new_pw == true }">
			<script type="text/javascript">
				alert("비밀번호가 변경되었습니다");
				window.location="/DoIt/d_login/login.do";
			</script>
	</c:if>
	<c:if test="${new_pw == false }">
			<script type="text/javascript">
				alert("입력에 실패하였습니다.");
				history.go(-1);
			</script>
	</c:if>

<jsp:include page="/footer.jsp" flush="false" /> 
			
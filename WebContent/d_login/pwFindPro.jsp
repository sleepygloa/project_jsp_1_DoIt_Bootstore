<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- header import -->
<jsp:include page="/header.jsp" flush="false" /> 

	<form action="/DoIt/d_login/pwChangePro.do" onsubmit="return pw_ChangeCheck();" method="post" name="pwChangeForm">
		<c:if test="${pwcheck == true}">
			아이디 : ${id}<br/>
			<input type="password" placeholder=" 새 비밀번호" name=d_pass><br/>
			<input type="password" placeholder=" 새 비밀번호 확인" name="d_pass_check"> <br/>
			<input type="hidden" name="id" value="${id}"/>
		</c:if>
	
			<input type = "submit" value = "비밀번호 변경">
		
		<c:if test="${pwcheck == false}">	
				<script>
					alert("아이디 또는 이름 및 이메일이 틀렸습니다.");
					window.location="/DoIt/d_login/pwFind.do";
				</script>
		</c:if>
	</form>
	
<jsp:include page="/footer.jsp" flush="false" /> 
	
	
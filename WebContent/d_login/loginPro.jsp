<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


${d_id}${d_pass}${check}

<!-- ------------------------------controller-------------------------------------- -->



<!-- ----로그인 성공 -------------------------------------------------- -->
	<c:if test="${userGradeCheck == '11'}">
		<script>
			alert("회원의 등급이 '책에 관심이 있는 책벌레 등급'으로 상승하였습니다. 책 구매시 5% 할인 혜택을 받으실 수 있습니다.")
		</script>
	</c:if>
	<c:if test="${userGradeCheck == '22'}">
		<script>
			alert("회원의 등급이 '책 좀 읽는 책벌레 등급'으로 상승하였습니다. 책 구매시 10% 할인 혜택을 받으실 수 있습니다.")
		</script>
	</c:if>


<!-- ----check == false 로그인 실패 ---------다시 header 페이지로----------- -->
<c:if test="${check == false}" >
	<script>
		alert("아이디와 비밀번호가 맞지않습니다. 다시 확인해주세요.");
	    history.go(-1);
	</script>
</c:if>




<!-- ----check == true 로그인 성공 ------------------------- -->
<meta http-equiv="Refresh" content="1;url=/DoIt/d_login/login.do" >
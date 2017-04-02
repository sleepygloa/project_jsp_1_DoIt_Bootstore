<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:if test="${d_bdelivery == 0 }">
		<script>
			alert("구매가 완료되었습니다.");
			window.location="/DoIt/d_admin/adminOnBuyBookList.do";
		</script>
	</c:if>
	
	<c:if test="${d_bdelivery == 1 }">
		<script>
		alert("배송이 시작되었습니다.");
		window.location="/DoIt/d_admin/adminOnBuyBookList.do";
	</script>
	</c:if>
	
	
	<c:if test="${d_bdelivery == 2 }">
		<script>
			alert("배송이 완료되었습니다.");
			window.location="/DoIt/d_admin/adminOnBuyBookList.do";
		</script>
	</c:if>
	
	<c:if test="${d_bdelivery == 4 }">
		<script>
			alert("취소 완료되었습니다.");
			window.location="/DoIt/d_admin/adminOnBuyBookList.do";
		</script>
	</c:if>
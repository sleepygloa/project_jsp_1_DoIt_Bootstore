<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${userGradeCheck == '01'}" >

	<script>
	alert("${d_id}님께서 '책에 관심이 많은 책벌레 등급 1'로 등급이 올랐습니다. 축하메세지를 전달해주세요!");
	window.location="/DoIt/d_admin/adminOnInspectionList.do";
	</script>

</c:if>


<c:if test="${userGradeCheck == '12'}" >

	<script>
	alert("${d_id}님께서 '책좀 읽는  책벌레 등급2'로 등급이 올랐습니다. 축하메세지를 전달해주세요!");
	window.location="/DoIt/d_admin/adminOnInspectionList.do";
	</script>

</c:if> 


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
	
	 <c:if test="${userGradeCheck == '00' || userGradeCheck == '11' || userGradeCheck == '22'}" >

	<script>
	alert("책이 등록되었습니다.");
	window.location="/DoIt/d_admin/adminOnInspectionList.do";
	</script>

</c:if>



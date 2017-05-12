<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${userGradeCheck == '11'}" >

	<script>
	alert("${d_id}님께서 '책에 관심이 많은 책벌레 등급 1'로 등급이 올랐습니다. 축하메세지를 전달해주세요!");
	window.location="/DoIt/d_admin/adminOnInspectionList.do";
	</script>

</c:if>

<c:if test="${userGradeCheck == '22'}" >

	<script>
	alert("${d_id}님께서 '책좀 읽는  책벌레 등급2'로 등급이 올랐습니다. 축하메세지를 전달해주세요!");
	window.location="/DoIt/d_admin/adminOnInspectionList.do";
	</script>

</c:if> 

<c:if test="${d_bdelivery == 20 }">
	<script>
		alert("구매가 완료되었습니다.");
		window.location="/DoIt/d_manage/manPart1.do";
	</script>
</c:if>

<c:if test="${d_bdelivery == 21 }">
	<script>
		alert("배송이 시작되었습니다.");
		window.location="/DoIt/d_manage/manPart1.do";
	</script>
</c:if>


<c:if test="${d_bdelivery == 22 }">
	<script>
		alert("배송이 완료되었습니다.");
		window.location="/DoIt/d_manage/manPart1.do";
	</script>
</c:if>

<c:if test="${d_bdelivery == 24 }">
	<script>
		alert("취소 완료되었습니다.");
		window.location="/DoIt/d_admin/adminOnSellList.do?cenList=cell&result=non";
	</script>
</c:if>
	




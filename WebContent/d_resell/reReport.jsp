<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${check == false}">
	<%--script>
		window.location="/DoIt/d_resell/reReportPro.do?rbook_no=${rbook_no}&pageNum=${pageNum}&report_id=${report_id}&report_id2=${report_id2}";
	</script--%>
	<script type="text/javascript">
	var Del = confirm("해당 게시글을 신고 하시겠습니까?");
		if (Del == true)
	{
		//alert("신고가 완료 되었습니다");
		window.location="/DoIt/d_resell/reReportPro.do?rbook_no=${rbook_no}&pageNum=${pageNum}&report_id=${report_id}&report_id2=${report_id2}";
	} else {
	    alert("취소 되었습니다.");
	    history.go(-1);
	        }
		
	</script>
	
</c:if>



<c:if test="${check == true}">
	<script>
		alert("이미 신고 하셨습니다");
		window.location="/DoIt/d_resell/reContent.do?rbook_no=${rbook_no}&pageNum=${pageNum}";
	</script>
</c:if>
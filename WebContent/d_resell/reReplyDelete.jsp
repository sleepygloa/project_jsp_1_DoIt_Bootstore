<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var Del = confirm("삭제 하시겠습니까?")
		if (Del == true)
	{
		alert("삭제되었습니다");
		window.location="/DoIt/d_resell/reContent.do?rbook_no=${rbook_no}&pageNum=${pageNum}";
	} else {
	    alert("취소 되었습니다.");
	    history.go(-1);
	        }
		
</script>
<%-- c:redirect url="/d_resell/reContent.do?rbook_no=${rbook_no}&pageNum=${pageNum}"/--%>


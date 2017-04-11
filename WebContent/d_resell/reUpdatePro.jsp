<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script language="JavaScript">
		alert("글 수정이 완료 되었습니다");
</script>
<!-- c:redirect url="/DoIt/d_resell/reList.do?pageNum=${pageNum}"-->
<meta http-equiv="Refresh" content="0;url=/DoIt/d_resell/reList.do?pageNum=${pageNum}">
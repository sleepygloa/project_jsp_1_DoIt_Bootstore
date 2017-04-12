<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" name="bid_id" value="${bid_id}" />
<input type="hidden" name="bid_no" value="${bid_no}" />

	
<c:if test="${check==true}">
	<script>
		alert("입찰 완료했습니다.");
		
		window.location="/DoIt/d_bid/bidContent.do?bid_no=${bid_no}&pageNum=${pageNum}";
	</script>
</c:if>

<c:if test="${check==false}">
	<script>
		alert("입찰 할수 없습니다.");
		history.go(-1);
	</script>

</c:if>

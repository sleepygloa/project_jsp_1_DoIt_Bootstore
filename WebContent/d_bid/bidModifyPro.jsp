<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input type="hidden" name="bid_no" value="${bid_no}" />

	<script>
		alert("경매 정보가 변경되었습니다.");
		window.location="/DoIt/d_bid/bidList.do";
	</script>

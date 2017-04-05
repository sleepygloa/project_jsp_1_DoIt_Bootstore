<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css"  href="/DoIt/css/online.css">

<!-- header import -->
<%@include file="../header.jsp" %>
<%--
  <jsp:include page="/d_online/onSide.jsp"></jsp:include>
<article class="my_cont_wrap">		
 --%>

<!--------------------------------- 검수양식 ------------------------------------->	

<p id="onForm_title">중고책 품질 체크(검수) </p>
<p id="onForm_line"></p>

<form action="/DoIt/d_admin/onInspectionPro.do" method="post" name="" id="onForm_wrap">

	<table id="onForm_con">
		<input type="hidden" name="d_bcode" value="${d_bcode}"/>
		${d_bcode}
		<tr>
			<td class="onForm_left">책 이 름</td>
			<td class="onForm_right">
				${d_bname}
			</td>
		</tr>
		<tr>
			<td class="onForm_left">가격 (정가)</td>
			<td class="onForm_right">
				${d_bvalue}
			</td>
		</tr>
		<tr>
			<td class="onForm_left" rowspan="4">책 품질 판정</td>
			<td class="onForm_right">
				<span class="onIns_quality_check">헌 상태</span>
				<label> <input type="radio" name="d_iold" value="0" class="" checked> 최상</label>
				<label> <input type="radio" name="d_iold" value="1" class=""> 상</label>
				<label> <input type="radio" name="d_iold" value="2" class=""> 중</label>
				<label> <input type="radio" name="d_iold" value="3" class=""> 매입불가</label>
				
			</td>
		</tr>
		<tr>
			
			<td class="onForm_right">
				<span class="onIns_quality_check">표  지</span>
				 <label><input type="radio" name="d_icover" value="0" class="" checked> 최상</label>
				 <label><input type="radio" name="d_icover" value="1" class=""> 상</label>
				 <label><input type="radio" name="d_icover" value="2" class=""> 중</label>
				 <label><input type="radio" name="d_icover" value="3" class=""> 매입불가</label>
				
			</td>
		</tr>
		<tr>
			
			<td class="onForm_right">
				<span class="onIns_quality_check">책   등</span>
				<label> <input type="radio" name="d_ispine" value="0" class="" checked> 최상</label>
				<label> <input type="radio" name="d_ispine" value="1" class=""> 상</label>
				<label> <input type="radio" name="d_ispine" value="2" class=""> 중</label>
				<label> <input type="radio" name="d_ispine" value="3" class=""> 매입불가</label>
			</td>
		</tr>
		<tr>
			 
			<td class="onForm_right">
				<span class="onIns_quality_check">제본상태</span>
				<label> <input type="radio" name="d_ibind" value="0" class="" checked> 최상</label>
				<label> <input type="radio" name="d_ibind" value="1" class=""> 상</label>
				<label> <input type="radio" name="d_ibind" value="2" class=""> 중</label>
				<label> <input type="radio" name="d_ibind" value="3" class=""> 매입불가</label>
			</td>
		</tr>
		
	</table>
	<img alt="품질판정리스트" src="/DoIt/images/Inspection.PNG" class="onIns_img">
	
	<p><input type="submit" value="검수 등록" class="onForm_btn"></p>

</form>


<%-->
</article>
 --%>




		
<!-- footer import -->
<jsp:include page="/footer.jsp" />
		
		
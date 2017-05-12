<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css"  href="/DoIt/css/online.css">

<!-- header import -->
<%@include file="../header.jsp" %>

  <jsp:include page="/d_online/onSide.jsp"></jsp:include>
<article class="my_cont_wrap">		


<!--------------------------------- 검수양식 ------------------------------------->	

<p id="onForm_title">중고책 품질 체크(검수) </p>
<p id="onForm_line"></p>
<form action="" method="post" enctype="multipart/form-data" name="onSellForm" id="onForm_wrap">
	<table id="onForm_con">
		<input type="hidden" name="d_bcode" value="책코드">
		<tr>
			<td class="onForm_left">책 이 름</td>
			<td class="onForm_right">
				<input type="text" name="d_bname" class="input_box">
			</td>
		</tr>
		<input type="hidden" name="d_bpublisher" value="출판사">
		<input type="hidden" name="d_bauthor" value="저자">
		<input type="hidden" name="d_bgenre" value="장르">
		<input type="hidden" name="d_bgenre2" value="종류">
		<input type="hidden" name="d_blocation" value="국/내외">
		<input type="hidden" name="d_bpic" value="책사진">
		<tr>
			<td class="onForm_left">가격 (정가)</td>
			<td class="onForm_right">
				<input type="text" name="d_bvalue" class="input_box">
			</td>
		</tr>
		<tr>
			<td class="onForm_left" rowspan="4">책 품질 판정</td>
			<td class="onForm_right">
				<span class="onIns_quality_check">헌 상태</span>
				 <input type="radio" name="iold" value="0" class=""> 최상
				 <input type="radio" name="iold" value="1" class=""> 상
				 <input type="radio" name="iold" value="2" class=""> 중
				 <input type="radio" name="iold" value="3" class=""> 매입불가
				
			</td>
		</tr>
		<tr>
			
			<td class="onForm_right">
				<span class="onIns_quality_check">표  지</span>
				 <input type="radio" name="icover" value="0" class=""> 최상
				 <input type="radio" name="icover" value="1" class=""> 상
				 <input type="radio" name="icover" value="2" class=""> 중
				 <input type="radio" name="icover" value="3" class=""> 매입불가
				
			</td>
		</tr>
		<tr>
			
			<td class="onForm_right">
				<span class="onIns_quality_check">책   등</span>
				 <input type="radio" name="ispine" value="0" class=""> 최상
				 <input type="radio" name="ispine" value="1" class=""> 상
				 <input type="radio" name="ispine" value="2" class=""> 중
				 <input type="radio" name="ispine" value="3" class=""> 매입불가
				
			</td>
		</tr>
		<tr>
			 
			<td class="onForm_right">
				<span class="onIns_quality_check">제본상태</span>
				 <input type="radio" name="ibind" value="" class=""> 최상
				 <input type="radio" name="ibind" value="" class=""> 상
				 <input type="radio" name="ibind" value="" class=""> 중
				 <input type="radio" name="ibind" value="" class=""> 매입불가
			</td>
		</tr>
		
	</table>
	<img alt="품질판정리스트" src="/DoIt/images/Inspection.PNG" class="onIns_img">
	
	<p><input type="submit" value="검수 등록" class="onForm_btn"></p>

</form>
</article>








		
<!-- footer import -->
<%@include file="../footer.jsp" %>
		
		
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>
	
<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_customer/side_customer.jsp" />

<article class="my_cont_wrap">
	<p class="my_title">
		관리자용 문의 내역 전체 리스트
	</p>

	<p class="my_sub_title">
		문의 내역에 답변을 달기 위해 모든 문의의 리스트를 보여줍니다.
	</p>	
	<p class="L_title">
		<a class="d-left"></a>
	</p>

	<div class="tabWrap">
		<ul class="inquire_tabs">
			<li class="inquire_tab1"><a href="/DoIt/d_customer/customer_InquireList.do?list=admin&reply=wait">문의한 내역</a></li>
			<li class="inquire_tab2"><a href="/DoIt/d_customer/customer_InquireList.do?list=admin&reply=finish">답변완료 내역</a></li>
		</ul>
	</div>


<form action="/DoIt/d_customer/InquirePro.do"  method="post" name="Inquire_reply" onsubmit="return Inquire_reply()"> 

<input type="hidden" name="c_ino" value="${c_ino}">
<input type="hidden" name="c_itype" value="${cdto.c_itype}">
<input type="hidden" name="ref" value="${ref}">
<input type="hidden" name="re_step" value="${re_step}">
<input type="hidden" name="re_level" value="${re_level}"> 
<input type="hidden" name="mem" value="${mem}"> 

<div class="b_inquire">
	<table>
	   <tr>
		    <td class="Inquire_left">이 름</td>
		    <td class="Inquire_right"><p>${id}</p></td>
	 	</tr>
	  <tr>
	    <td class="Inquire_left">제 목</td>
	    <td class="Inquire_right">
	    <c:if test="${num != 0 }">
	    	<p>[답변]</p>
			<input type="hidden" name="c_isubject" value="[답변]">
		</c:if>
		</td>
		
	  </tr>
		
		<tr>
			<td class="Inquire_left">내 용</td>
			<td class="Inquire_right">
				<p><textarea name="c_icontent"></textarea></p> 
			</td>
		</tr>
	</table> 
	<div class="Inquire_btn">
			  <input type="submit" value="글쓰기" class="re_button" />  
			  <input type="reset" value="다시작성" class="re_button"/>
			  <input type="button" value="목록보기" class="re_button" OnClick="window.location='/DoIt/d_customer/customer_InquireList.do?reply=wait'">
	</div>

 
</div>
</form>      

<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>
	
	
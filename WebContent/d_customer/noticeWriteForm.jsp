<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/DoIt/css/resell.css">

<script type="text/javascript" src="/DoIt/js/script.js?=uiyu1"></script>
	

<!-- header import -->
<%@include file="/header.jsp" %>
<%--------------- 사이드 메뉴 include --------------%>
	<jsp:include page="/d_customer/side_customer.jsp"></jsp:include>
	<article class="my_cont_wrap">	

		<p id="reWrite_title">공지사항 글쓰기</p>
		<p id="reWrite_line"></p>
		<form method="post" action="/DoIt/d_customer/noticeWritePro.do" name="noticeWriteform" onsubmit="return noticewriteSave()">
			<!-- 폼태그 내용이 다넘어감 -->
						
			<table id="reWrite_con">
			   <tr>
			    <td>
				    <!-- a href="list.seri"> 글목록</a--> 
			   </td>
			   </tr>
			   <tr>
			    <td class="reWrite_left">작성자</td>
			    <td class="reWrite_right">
					 ${notice_id}
			       <input type="hidden" size="10" maxlength="10" name="notice_id" value="${notice_id}">
			    </td>
			     
			  </tr>
			  <tr>
			    <td class="reWrite_left">공지사항제목</td>
			    <td class="reWrite_right"><input type="text" name="notice_name" class="input_box"></td>
			    
			  </tr> 
			  <tr>
			    <td class="reWrite_left">공지사항내용</td>
			    <td class="reWrite_right">
			     <textarea name="notice_content" rows="13" cols="40"></textarea> </td>
			  </tr>
			  
			 
			<tr>      
			 <td class=""> 
			 
			  <input type="submit" value="작성하기" class="reWrite_btn">  
			  <!-- input type="reset" value="다시작성" class="reWrite_btn" -->
			  <input type="button" value="목록보기" OnClick="window.location='/DoIt/d_customer/noticeList.do'" >
			</td>
			</tr></table>    
			   
			</form>
			</article> 
  
   
		 
		<!-- footer import  -->
		
		

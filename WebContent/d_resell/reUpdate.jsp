<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/DoIt/css/resell.css">

	

		<!-- header import -->
		<%@include file="/header.jsp" %>
		<%--------------- 사이드 메뉴 include --------------%>
		<jsp:include page="/d_resell/side_resell.jsp"></jsp:include>
		<article class="my_cont_wrap">	
		<!------------------ 직거래게시판 ---------------------------------------------------->
		<p id="reWrite_title">직거래 게시판(상품등록)</p>
		<p id="reWrite_line"></p>
		<form method="post" name="reUpdate" action="/DoIt/d_resell/reUpdatePro.do" onsubmit="return writeSave()" enctype="multipart/form-data" id="reWrite_wrap">
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
					 ${article.rbook_id}
			       <input type="hidden" size="10" maxlength="10" name="rbook_id" value="${article.rbook_id}">
			    	<input type="hidden" name="rbook_no" value="${article.rbook_no}">
					<input type="hidden" name="pageNum" value="${pageNum}">
			    </td>
			     
			  </tr>
			  <tr>
			    <td class="reWrite_left">상품제목(책이름)</td>
			    <td class="reWrite_right"><input type="text" name="rbook_name" class="input_box" value="${article.rbook_name}"></td>
			    
			  </tr>
			  <tr>
			    <td class="reWrite_left">책가격</td>
			    <td class="reWrite_right">
			       <input type="text"name="rbook_price" class="input_box" value="${article.rbook_price }"></td>
			  </tr>
			  <tr>
			    <td class="reWrite_left">저자</td>
			    <td class="reWrite_right">
			      <input type="text" name="rbook_writer" class="input_box" value="${article.rbook_writer}"></td>
			  </tr>
			   <tr>
			    <td class="reWrite_left">출판사</td>
			    <td  class="reWrite_right" >
			     <input type="text" name="rbook_company" class="input_box" value="${article.rbook_company}"></td>
			  </tr>
			  <tr>
			    <td class="reWrite_left">게시글내용</td>
			    <td class="reWrite_right">
			     <textarea name="rbook_content" rows="13" cols="40" >${article.rbook_content}</textarea> </td>
			  </tr>
			  <tr>
			  	<td>
			  	<c:if test="${article.rbook_pic == null}" >
              		<input type="file" name="rbook_pic" class="input_filebox" />
             	 </c:if>
             	 <c:if test="${article.rbook_pic != null}" >
			  		<td class="reWrite_left">책사진</td>
			  		<td class="reWrite_right">
			  			<img src="/DoIt/rbook_pic/${article.rbook_pic}"width="150px" /> <br />
			  			<input type="file" name="rbook_pic" class="input_filebox" />
			  			<input type="hidden" name="save1" value="${article.rbook_pic}">
				   	 <!-- hidden으로 올라있는 첨부파일 을 보냄 -->
				 </c:if>   
			  	</td>
			  </tr>
                   
			
			  
			<tr>      
			 <td class=""> 
			 
			  <input type="submit" value="글수정" class="reWrite_btn">  
			  <!-- input type="reset" value="다시작성" class="reWrite_btn" -->
			  <input type="button" value="목록보기" OnClick="window.location='/DoIt/d_resell/reList.do?pageNum=${pageNum}'" class="reWrite_btn">
			</td>
			</tr></table>    
			   
			</form>
			</article> 
  
   
		 
		<!-- footer import  -->
		
		

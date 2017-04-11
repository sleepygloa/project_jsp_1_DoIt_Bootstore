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
		<form method="post" name="reWriteform" action="/DoIt/d_bid/bidWritePro.do" onsubmit="return writeSave()" enctype="multipart/form-data" id="reWrite_wrap">
			<!-- 폼태그 내용이 다넘어감 -->
			<input type="hidden" name="bid_no" value="${bid_no}">
			
			
			<table id="reWrite_con">
			   <tr>
			    <td>
				    <!-- a href="list.seri"> 글목록</a--> 
			   </td>
			   </tr>
			   <tr>
			    <td class="reWrite_left">작성자</td>
			    <td class="reWrite_right">
					 ${id}
			       <input type="hidden" size="10" maxlength="10" name="bid_id" value="${id}">
			    </td>
			     
			  </tr>
			  <tr>
			    <td class="reWrite_left">경매글제목</td>
			    <td class="reWrite_right"><input type="text" name="bid_subject" class`="input_box"></td>
			    
			  </tr> 
			  <tr>
			    <td class="reWrite_left">상품제목(책이름)</td>
			    <td class="reWrite_right"><input type="text" name="bid_name" class="input_box"></td>
			    
			  </tr>
			  <tr>
			    <td class="reWrite_left">경매 시작가</td>
			    <td class="reWrite_right">
			       <input type="text"name="bid_price1" class="input_box"  value='숫자만 입력하세요' onfocus="this.value=''"></td>
			  </tr>
			 
			  <tr>
			    <td class="reWrite_left">게시글내용</td>
			    <td class="reWrite_right">
			     <textarea name="bid_content" rows="13" cols="40"></textarea> </td>
			  </tr>
			  <tr>
			  	<td class="reWrite_left">책사진</td>
			  	<td class="reWrite_right">
			  		<input type="file" name="bid_pic" class="input_filebox"/>
			  	</td>
			  </tr>
			  <tr>
			  	<td class="reWrite_left">경매 시간 설정(따로 선택이 없을시 1일로 설정됩니다.)</td>
			  	<td class="reWrite_right">
			  		
			  		1일<input type="radio" name="bid_time_value" value="1"/>
			  		2일<input type="radio" name="bid_time_value" value="2"/>
			  	</td>
			  </tr>
			  
			 
			<tr>      
			 <td class=""> 
			 
			  <input type="submit" value="글쓰기" class="reWrite_btn">  
			  <!-- input type="reset" value="다시작성" class="reWrite_btn" -->
			  <input type="button" value="목록보기" OnClick="window.location='/DoIt/d_bid/bidList.do'" class="reWrite_btn">
			</td>
			</tr></table>    
			   
			</form>
			</article> 
  
   
		 
		<!-- footer import  -->
		
		

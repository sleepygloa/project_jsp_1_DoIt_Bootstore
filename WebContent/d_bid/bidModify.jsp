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
		<p id="reWrite_title">경매 게시판(수정페이지)</p>
		<p id="reWrite_line"></p>
		<form method="post" name="reWriteform" action="/DoIt/d_bid/bidModifyPro.do" onsubmit="return writeSave()" enctype="multipart/form-data" id="reWrite_wrap">
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
					 ${bid_id}
			       <input type="hidden" size="10" maxlength="10" name="bid_id" value="${bid_id}">
			    </td>
			     
			  </tr>
			  <tr>
			    <td class="reWrite_left">경매글제목</td>
			    
			    <td class="reWrite_right">
			    	<input type="text" name="bid_subject" class="input_box" value="${c.bid_subject}">
			    </td>
			    
			  </tr> 
			  <tr>
			    <td class="reWrite_left">상품제목(책이름)</td>
			    <td class="reWrite_right">
			    <input type="text" name="bid_name" class="input_box" value="${c.bid_name}">
			    </td>
			    
			  </tr>
			  <tr>
			    <td class="reWrite_left">경매 시작가<br/>(경매시작가는 변경할수 없습니다.)</td>
			    <td class="reWrite_right">${c.bid_price1 }</td>
			  </tr>
			 
			  <tr>
			    <td class="reWrite_left">게시글내용</td>
			    <td class="reWrite_right">
			     <textarea name="bid_content" rows="13" cols="40">${c.bid_content}</textarea> </td>
			  </tr>
			  <tr>
			  	<td class="reWrite_left">책사진</td>
			  	
			  	<td class="reWrite_right">
			  	<c:if test="${c.bid_pic == null}" >
			  	<input type="file" name="bid_pic" class="input_filebox" />
			  	</c:if>
			  		<c:if test="${c.bid_pic != null}" >
			  			<img src="/DoIt/bid_pic/${c.bid_pic}"width="150px" /> <br />
			  			<input type="file" name="bid_pic" class="input_filebox" />
			  			<input type="hidden" name="bid_picCheck" value="${c.bid_pic}" />
			  	
                	</c:if>
			  		
			  	</td>
			  
			  	
			  </tr>
			  <tr>
			  	<td class="reWrite_left">경매시간</td>
			  	<td class="reWrite_right">경매시간은 변경이 불가합니다.
			  		
			  		
			  	</td>
			  </tr>
			  
			 
			<tr>      
			 <td class=""> 
			 
			  <input type="submit" value="수정하기" class="reWrite_btn">  
			  <!-- input type="reset" value="다시작성" class="reWrite_btn" -->
			  <input type="button" value="목록보기" OnClick="window.location='/DoIt/d_bid/bidList.do'" class="reWrite_btn">
			</td>
			</tr></table>    
			   
			</form>
			</article> 
  
   
		 
		<!-- footer import  -->
		
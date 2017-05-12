<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/DoIt/css/resell.css">
<script type="text/javascript" src="/DoIt/js/script.js"></script>	
	

	<!-- header import -->
	<%@include file="/header.jsp" %>
		
	<%--------------- 사이드 메뉴 include --------------%>
	<jsp:include page="/d_resell/side_resell.jsp"></jsp:include>
		
		<!------------------ 직거래게시판 ---------------------------------------------------->
		<article class="my_cont_wrap">	
		
		<p class="my_title">중고 직거래(상품등록)</p>
		<p class="my_sub_title"></p>
		
		<p class="write_tit">
			DOIT 중고직거래 입니다. 게시물을 올려주세요.
			<a>중고 직거래에 <span>허위매물</span>을 올릴 경우 제재를 받을 수 있습니다.</a>
		</p>
		
		<form method="post" name="reWriteform" action="/DoIt/d_resell/reWritePro.do" onsubmit="return writeSave()" enctype="multipart/form-data" id="reWrite_wrap">
			<!-- 폼태그 내용이 다넘어감 -->
			<input type="hidden" name="rbook_no" value="${rbook_no}">
			
			
			<table class="join_01_ta" cellspacing="0">
				<tr>
					<td><!-- a href="list.seri"> 글목록</a--> </td>
				</tr>
			  	<tr>
			    	<td >작성자</td>
			    	<td > 
			    		${id} <input type="hidden" size="10" maxlength="10" name="rbook_id" value="${id}"> 
			    		<p class="sub_con">작성자 자동입력</p>
			    	</td> 
			  	</tr>
			  	<tr>
			    	<td >글제목</td>
			    	<td >
			    		<input type="text" name="rbook_subject" class ="input_01">
			    		<p class="sub_con">판매할 글 제목을 입력하세요. (책이름 X)</p>
			    	</td>
			 	 </tr> 
			  	<tr>
			   		<td >상품제목(책이름)</td>
			    	<td >
			    		<input type="text" name="rbook_name" class="input_01">
			    		<p class="sub_con">판매할 도서의 이름을 입력하세요.</p>
			    	</td>
			  	</tr>
			  	<tr>
			    	<td >판매할 가격</td>
			    	<td >
			    		<input type="text"name="rbook_price" class="input_01"  placeholder="숫자만 입력하세요." onfocus="this.value=''">
			    		<p class="sub_con">정가가 아닌 판매하실 금액을 입력하세요.</p>
			    	</td>
			  	</tr>
			  	<tr>
			    	<td >책의 정가</td>
			    	<td >
			    		<input type="text"name="rbook_price2" class="input_01"  placeholder="숫자만 입력하세요." onfocus="this.value=''">
			    		<p class="sub_con">새 책 가격을 입력하세요. ( 할인율이 표시됩니다. )</p>
			    	</td>
			  	</tr> 
			  	<tr>
			    	<td >저자</td>
			    	<td >
			    		<input type="text" name="rbook_writer" class="input_01">
			    		<p class="sub_con">저자를 입력하세요.</p>
			    	</td>
			  	</tr>
			   	<tr>
			    	<td >출판사</td>
			    	<td   >
			     		<input type="text" name="rbook_company" class="input_01">
			     		<p class="sub_con">출판사를 입력하세요.</p>
			     	</td>
			 	</tr>
			  	<tr>
			    	<td >게시글내용</td>
			    	<td >
			    		<textarea name="rbook_content" rows="10" cols="70"></textarea> 
			    		<p class="sub_con">게시글은 설명을 쓰되 제한적인 단어는 제재를 받을 수 있습니다.</p>
			    	</td>
				</tr>
			  	<tr>
			  		<td >책사진</td>
			  		<td >
			  			<input type="file" name="rbook_pic" class="input_filebox"/>
			  			<p class="sub_con">도서의 사진은 정확하게 올려주세요.</p>
			  		</td>
			  	</tr>
			  	<tr>
					<td >판매 할 책 상태</td>
					<td >
						최상급<input type="radio" name="rbook_bgread" value="10" class="">
						중급<input type="radio" name="rbook_bgread" value="20" class="">
						하급<input type="radio" name="rbook_bgread" value="30" class="">
						<p class="sub_con">도서의 상태를 정확하게 입력해주세요.. 허위매물일 경우 제재를 받을 수 있습니다.</p>
					</td>
				</tr> 
				
			 </table>
			
			<p class="button_cell">
				<input type="submit" value="글쓰기" class="sub_button">  
				<!-- input type="reset" value="다시작성" class="reWrite_btn" -->
				<input type="button" value="목록보기" OnClick="window.location='/DoIt/d_resell/reList.do'" class="re_button">
			</p>
			 
			    
			   
		</form>
	</article> 
  
   
		 
	<!-- footer import  -->
	<%@include file="/footer.jsp" %>
		

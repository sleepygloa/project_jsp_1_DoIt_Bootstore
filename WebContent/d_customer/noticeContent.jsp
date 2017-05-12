
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/DoIt/css/resell.css">

	

<!-- header import -->
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- 사이드 메뉴 include --------------%>
	<jsp:include page="/d_customer/side_customer.jsp"></jsp:include>
	<article class="my_cont_wrap" style="margin-bottom:250px;">	

		<p class="my_title">공지사항 상세보기</p>
		<p class="my_sub_title"></p>
			<!-- 폼태그 내용이 다넘어감 -->
						
			<table id="reWrite_con">
			   <tr>
			    <td class="reWrite_left">작성자</td>
			    <td class="reWrite_right">
					 ${article.notice_id}
			       <input type="hidden" size="10" maxlength="10" name="notice_id" value="${notice_id}">
			    </td>
			     
			  </tr>
			  <tr>
			    <td class="reWrite_left">공지사항제목</td>
			    <td class="reWrite_right">${article.notice_name }</td>
			    
			  </tr> 
			 
			  <tr>
			    <td class="reWrite_left">공지사항내용</td>
			    <td class="reWrite_right">
			    ${article.notice_content } </td>
			  </tr>
			  
			 
			<tr>      
			 <td class=""> 
			 
			  
			  <input type="button" value="목록보기" OnClick="window.location='/DoIt/d_customer/noticeList.do'" >
			</td>
			</tr></table>    
				<c:if test="${dto.d_mech_grade==10 }">
					<input type="button" value="글수정" OnClick="window.location='/DoIt/d_customer/noticeModify.do?notice_no=${article.notice_no}&pageNum=${pageNum}">
					<input type="button" value="글삭제" OnClick="window.location='/DoIt/d_customer/noticeDelete.do?notice_no=${article.notice_no}&pageNum=${pageNum}">
				</c:if>
			</form>
			</article> 
  
   
		 
		<!-- footer import  -->
		<jsp:include page="/footer.jsp"></jsp:include>






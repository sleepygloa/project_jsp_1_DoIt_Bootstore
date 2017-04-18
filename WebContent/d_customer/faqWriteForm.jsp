<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<link rel="stylesheet" type="text/css"  href="/DoIt/css/resell.css">
<script type="text/javascript" src="/DoIt/js/faq.js?ver=11"></script>	
<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_customer/side_customer.jsp"></jsp:include>

<article class="my_cont_wrap">

   <p class="my_title">faq</p>
   <p class="my_sub_title">
      <span></span>
   </p>
   <p class="write_tit"></p>
   
   
   <form name="faqWriteform" method="post" action="/DoIt/d_customer/faqWriteFormPro.do" id="reWrite_wrap" onsubmit="return faqSave()">
   <input type="hidden" name="faq_no" value="${faq_no}" />
   
       <table class="join_01_ta" cellspacing="0">
       <colgroup>
          <col width="30%"><col width="70%">
       </colgroup>
          <tr>
	    		<td class="reWrite_left">작성자</td>
	    		<td class="reWrite_right">${id}
	    		<input type="hidden" size="10" maxlength="10" name="faq_writer" value="${id}" /></td>
	    	</tr>
          <tr>
             <td class="reWrite_left">제목</td>
             <td class="reWrite_right"><input type="text" name="faq_subject"/></td>
          </tr>
          <tr>
             <td class="reWrite_left"> <span class="co_red"> * </span>내용</td>
             <td class="reWrite_right"> 
             <textarea name="faq_content" rows="10" cols="50" ></textarea> </td>
          </tr>
          <tr>
          	<td class="reWrite_left">분류</td>
            <td class="reWrite_right">
             			온라인 중고 서점<input type="radio" name="faq_type" value="0" class="">
						DOIT 도서관<input type="radio" name="faq_type" value="1" class="">
						중고 직거래/경매<input type="radio" name="faq_type" value="2" class="">
						<p class="sub_con">분류 타입을 선택해 주세요</p>
			</td>
					
						
					
				</tr> 
       </table>
       
       
       <p class="button_cell">
          <input type="submit" value="작성완료" class="sub_button">
       </p>
       
       </form>
     </article>   
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>

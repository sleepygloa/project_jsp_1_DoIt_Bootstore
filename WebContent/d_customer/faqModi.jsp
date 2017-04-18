<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<link rel="stylesheet" type="text/css"  href="/DoIt/css/resell.css">

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
   
   
   <form name="faqModi" method="post" action="/DoIt/d_customer/faqModiPro.do" id="reWrite_wrap">
   <input type="hidden" name="faq_no" value="${faq_no}" />
   
       <table class="join_01_ta" cellspacing="0">
       <colgroup>
          <col width="30%"><col width="70%">
       </colgroup>
          <tr>
	    		<td class="reWrite_left">작성자</td>
	    		<td class="reWrite_right">${article.faq_writer}
	    		<input type="hidden" size="10" maxlength="10" name="faq_writer" value="${id}" />
	    		<input type="hidden" size="10" maxlength="10" name="pageNum" value="${pageNum}" /></td>
	    		
	    	</tr>
          <tr>
             <td class="reWrite_left">제목</td>
             <td class="reWrite_right"><input type="text" name="faq_subject" value="${article.faq_subject}"/></td>
          </tr>
          <tr>
             <td class="reWrite_left"> <span class="co_red"> * </span>내용</td>
             <td class="reWrite_right"> 
             <textarea name="faq_content" rows="10" cols="50" >${article.faq_content}</textarea> </td>
          </tr>
       </table>
       
       
       <p class="button_cell">
          <input type="submit" value="작성완료" class="sub_button">
       </p>
       
       </form>
     </article>   
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>

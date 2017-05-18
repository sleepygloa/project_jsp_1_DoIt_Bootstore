<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.memId==null}">
<script>
alert("로그인후 이용해주세요");
history.go(-1);
</script></c:if>

<c:if test="${c.d_mech_grade==2}">

<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>
<%--------------- 사이드 메뉴 include --------------%>
<<<<<<< HEAD
<jsp:include page="/d_login/side_my.jsp"></jsp:include>
=======
<jsp:include page="/d_resell/side_resell.jsp"></jsp:include>
>>>>>>> 06e83cdc0212d155692e1e75dda189dd861591c1

<article class="my_cont_wrap">
<p class="my_title">판매자 소개글 변경</p>
	<p class="my_sub_title">
		<span>소개글은 권한 승인, 직거래 매물 등록등을 나누는 기준이 되므로 상세히 입력해주세요.</span>
	</p>
	<p class="write_tit"></p>
	
	
<form name="reWriteform" method="post" action="/DoIt/d_resell/modiIntroPro.do" id="reWrite_wrap">
    
    <input type="hidden" name="rbook_introno" value="${rbook_introno}" />
    <table class="join_01_ta">
    	<tr>
    		<td class="reWrite_left">신청자</td>
    		<td class="reWrite_right">${c.d_id}
    		<input type="hidden" size="10" maxlength="10" name="d_id" value="${d_id }" /></td>
    	</tr>
    	<tr>
    		<td class="reWrite_left">회원번호</td>
    		<td class="reWrite_right">${c.d_no}</td>
    	</tr>
    	<tr>
    		<td class="reWrite_left"><span class="co_red"> * </span>판매자 소개글</td>
    		<td class="reWrite_right"> 
    		
    		<textarea name="rbook_intro" rows="10" cols="50">${c.rbook_intro}</textarea> </td>
    	</tr>

    </table>
    
   		 <p class="button_cell">
	    	<input type="submit" value="신청하기" class="sub_button">
	    	<button class="re_button"
	    			onclick="window.location='/DoIt/d_login/myinfo.do'" >취소하기</button>
	    </p>
    
    </form>
</article>
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>
</c:if>



<c:if test="${c.d_mech_grade!=2}">
<script>
	alert("판매자가 아닙니다.");
	history.go(-1);
</script>
</c:if>
    

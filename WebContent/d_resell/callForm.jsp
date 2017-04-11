<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.memId==null}">
<script>
alert("로그인후 이용해주세요");
history.go(-1);
</script></c:if>

<c:if test="${c.d_mech_grade==0}">
<link rel="stylesheet" type="text/css"  href="/DoIt/css/resell.css">

<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_resell/side_resell.jsp"></jsp:include>

<article class="my_cont_wrap">

	<p class="my_title">판매자 권한 신청</p>
	<p class="my_sub_title">
		<span>권한신청은 최대 1주일의 시간이 걸릴수 있습니다. 개별 공지가 없으니 마이페이지를 확인하세요.</span>
	</p>
	<p class="write_tit"></p>
	
	
	<form name="reWriteform" method="post" action="/DoIt/d_resell/callPro.do" id="reWrite_wrap">
	<input type="hidden" name="rbook_introno" value="${rbook_introno}" />
	
	    <table class="join_01_ta" cellspacing="0">
	    <colgroup>
	    	<col width="30%"><col width="70%">
	    </colgroup>
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
	    		<td class="reWrite_left"> <span class="co_red"> * </span>판매자 소개글</td>
	    		<td class="reWrite_right"> 
	    		<textarea name="rbook_intro" rows="10" cols="50" placeholder="내용을 입력하세요. 미입력시 승인이 불가합니다."></textarea> </td>
	    	</tr>
	    </table>
	    
	    
	    <p class="button_cell">
	    	<input type="submit" value="신청하기" class="sub_button">
	    	<button class="re_button"
	    			onclick="window.location='/DoIt/d_resell/reList.do'" >취소하기</button>
	    </p>
	    
	    </form>
     </article>   
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>
</c:if>



<c:if test="${c.d_mech_grade==1}">
<script>
	alert("판매자 신청중입니다.");
	history.go(-1);
</script>
</c:if>
    
<c:if test="${c.d_mech_grade==2}">
<script>
	alert("이미 판매자 입니다.");
	history.go(-1);
</script>
</c:if>
    
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- header import -->
<jsp:include page="/header.jsp" flush="false" /> 


<div class="login_lay_wrap">
	<section class="login_wrap">
	<h1>로그인 section</h1>
	
		<header class="login_head">
		<h2>로그인 header : 제목</h2>
			<p class="login_title">Logout</p>
		</header><!-- 로그인 제목 끝 -->
		
		<section class="login_sec">
		<h2>로그인 몸체</h2>
			<article>
			<h3>로그인 몸체 내용</h3>
			
				<form action="/DoIt/d_login/loginPro.do" method="post" name="Login_form" id="Login_form">
				<input type="hidden" value="login" />
				<h4>로그아웃 폼</h4>
<!-- ---------------------------------로그아웃 재확인------------------------------------------------------ -->

	로그 아웃 하시겠습니까?<br />
	<input type="button" name="logout" value="로그아웃" 
		onclick="window.location='/DoIt/d_login/logoutPro.do'"/> <!-- 로그아웃 성공  -> header 페이지로(비로그인) -->
	<input type="button" name="cancel" value="취소" 
		onclick="window.location='/DoIt/d_login/login.do'" /> <!-- 로그아웃 취소 -> header 페이지로(로그인상태) -->
				</form><!-- 로그인 form 끝 -->
				
			</article><!--  로그인 아티클 끝 -->
		</section><!-- 로그인 몸체 section 끝 -->
		

	    
	   
			
	</section>
</div>
<!-- footer import  -->
<jsp:include page="/footer.jsp" flush="false" /> 


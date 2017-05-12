<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

	

		<!-- header import -->
		<%@include file="/header.jsp" %>
		
		
		<div class="login_lay_wrap">
			<section class="login_wrap">
			<h1>로그인 section</h1>
			
				<header class="login_head">
				<h2>로그인 header : 제목</h2>
					<p class="login_title">Login</p>
				</header><!-- 로그인 제목 끝 -->
				
				<section class="login_sec">
				<h2>로그인 몸체</h2>
					<article>
					<h3>로그인 몸체 내용</h3>
						<form action="/DoIt/d_login/loginPro.do" method="POST" name="Login_form" id="Login_form" onsubmit="return Login_check()">
						<input type="hidden" value="login" />
						<h4>로그인 폼</h4>
							<p class="login_title1">아이디</p>
							<p class="login_con"><input type="text" name="d_id" id="user_id" placeholder="아이디를 입력하세요" /></p>
							<p class="login_title1">비밀번호</p>
							<p class="login_con"><input type="password" name="d_pass" id="user_pass" placeholder="비밀번호를 입력하세요." /></p>
							<p class="login_sub"><button type="submit">로그인</button></p>
						</form><!-- 로그인 form 끝 -->
						
					</article><!--  로그인 아티클 끝 -->
				</section><!-- 로그인 몸체 section 끝 -->
				
				<footer class="login_foot ov_hi">
				<h2>로그인 footer</h2>
					<ul class="login_foot_ul fl_le">
						<c:if test="${ sessionScope.memId != null }">
							<li><a href="#" onclick="window.lacation=se_al()">회원가입</a></li>
						</c:if>
						<c:if test="${ sessionScope.memId == null }" >
							<li><a href="/DoIt/d_login/join.do">회원가입</a></li>
						</c:if>
						<li><a href="/DoIt/d_login/pwFind.do">ID / PW 찾기</a></li>
					</ul>
			    </footer><!-- 로그인 링크 끝(회원가입, ID,PASS ) -->
					
			</section>
		</div>
		<!-- footer import  -->
		
		

		
		
		

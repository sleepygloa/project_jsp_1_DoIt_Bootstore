<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		
		<%-- header, footer  --%>
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/he_foot.css?ver=1">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/reset.css">
		
		<%-- 회원관리 css 삽입 --%>
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/join.css">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/Login.css">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/side_menu.css">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/my_page.css">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/online.css">




		<link rel="stylesheet" type="text/css"  href="/DoIt/css/bootstrap-ko.css">
		
		
		<%-- 스크립트, Jquery  삽입 --%>
		<script type="text/javascript" src="/DoIt/js/Join_Login.js" ></script>
		<script type="text/javascript" src="/DoIt/js/jquery-3.1.1.js"></script>
		<script type="text/javascript" src="/DoIt/js/jquery-3.1.1.min.js"></script>
		
		<script type="text/javascript">
			
			//header에 필요한 Jquery 코드
			$(document).ready(function(){
				$("#login_al").click(function(){
					alert("로그인을 누름");
				})
				
				$(".l_list_ma").on("mouseenter", function(){
					$(".s_list_con").slideUp().stop();
					$(".s_list_con").slideDown();
				})
				$(".l_list").on("mouseleave", function(){
					$(".s_list_con").slideDown().stop();
					$(".s_list_con").slideUp();
				})
				
				
			});

		
		
			function go_back(){
				window.history.back();
			}
			
			//세션 있다는 알림
			function se_al(){
				alert("이미 로그인이 되어있습니다.");
				window.loaction="#";
			}
			
			//로그인이 필요하다는 알림
			function board_se(){
				alert("로그인이 필요합니다.");
				window.location = "/jsp/login_board/mem_login/login.jsp";
			}
			
			//관리자 등급이 아니라는 알림
			function board_se2(){
				alert("관리자등급이 필요합니다.");
				history.go(-1);
			}
			
			
			//--------------------------------아이디와 비밀번호를 입력창에 입력했는지 확인--------------------------------
		       function Login_check(){
		         if(!document.Login_form.d_id.value){
		           alert("아이디를 입력하지 않으셨습니다.");
		           document.Login_form.d_id.focus();
		           return false;
		         }
		         if(!document.Login_form.d_pass.value){
		           alert("비밀번호를 입력하지 않으셨습니다.");
		           document.Login_form.d_pass.focus();
		           return false;
		         }
		       }	

		</script>
		
		<title>회원 커뮤니티 게시판</title>
	</head>
	
	<body>
		
	
		<header id="common_header">
		
			<header class="top_gnb">
				<div class="gnb_con">
					<ul class="top_div fl_ri">
						<c:if test="${ sessionScope.memId == null }">
							<li><a href="/DoIt/d_login/login.do">로그인</a></li>
							<li><a href="/DoIt/d_login/join.do">회원가입</a></li>
						</c:if>
						<c:if test="${ sessionScope.memId != null }">
							<li><a>${ sessionScope.memId } 님 안녕하세요.</a></li>
							<li><a href="/DoIt/d_login/logout.do">로그아웃</a></li>
							<li><a href="/DoIt/d_login/myInfo.do">마이페이지</a></li>
							<li><a href="#">장바구니</a></li>
						</c:if>
						<c:if test="${ sessionScope.memId != null && sessionScope.memMG == 2 }">
							<li><a href="/DoIt/d_admin/admin.do">관리자 페이지</a></li>
						</c:if>
					</ul>
				</div>
			</header><%-- top_gnb 끝 --%>

 			<section class="l_list" >
					<ul class="l_list_ma">

						<li><a href="#">회사소개</a></li>
						<li><a href="/DoIt/d_online/on.jsp">온라인중고서점</a></li>
						<li><a href="#">DOIT 도서관</a></li>
						<li><a href="#">직거래게시판</a></li>
						<li><a href="#">고객센터</a></li>
					</ul>
					
					<div class="s_list_con">

						<ul>
							<li><a href="#">인사말</a></li>
							<li><a href="#">조직도</a></li>
							<li><a href="#">C.I 소개</a></li>
							<li><a href="#">시설현황</a></li>
							<li><a href="#">찾아오시는 길</a></li>
						</ul>
						<ul>
<!-- 							
							<li><a href="#">어린이 서적</a></li>
							<li><a href="#">참고 / 전문서적</a></li>
							<li><a href="#">소설 / 시 / 에세이</a></li>
							<li><a href="#">인문학 서적</a></li>
							<li><a href="#">과학 전문서적</a></li>
							<li><a href="#">기타 서적 </a></li>
							 -->
							<li><a href="#">온라인 판매</a></li>
							<li><a href="#">온라인 구매</a></li>
						</ul>
						<ul>
							<li><a href="#">DoIt 도서관</a></li>
							<li><a href="#">도서 기증신청</a></li>
							<li><a href="#">도서 반납신청</a></li>
						</ul>
						<ul>
							<li><a href="#">판매자 회원 소개</a></li>
							<li><a href="#">판매자 권한 신청</a></li>
							<li><a href="#">직거래 게시판</a></li>
						</ul>
						<ul>
							<li><a href="#">공지사항</a></li>
							<li><a href="#">Q & A 게시판</a></li>
							<li><a href="#">1 : 1 문의 신청</a></li>
						</ul>
					</div>
			</section><!--  대메뉴 끝 -->  
			
			
			
		</header>
		
		<div style="height:200px; width:100%;"></div>
		
		<section id="mypage_wrap">
		
		
		
		
		
		
		
		
		
		

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		
		<%-- header, footer  --%>
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/he_foot.css?ver=1">
<!-- <link rel="stylesheet" type="text/css"  href="/DoIt/css/he_foot.css"> 윤수 장바구니 20170331 버전-->
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/reset.css">
		
		<%-- 회원관리 css 삽입 --%>
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/join.css">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/Login.css">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/side_menu.css">
		<!-- <link rel="stylesheet" type="text/css"  href="/DoIt/css/my_page.css"> 선향  20170403 버전-->
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/my_page.css?ver=2">		
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/online.css">

		<%-- 도서관 css삽입 --%>
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/d_rent.css">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/deli_order.css">

		<%-- 온라인 서점, 직접 판매 css 삽입 선호 --%>
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

				
				//도서관 상세보기 파트 구분
				$("#de_cont").click(function(){
					$(".det_bot").css("display","block");
					$(".det_bot2").css("display","none");
					$(".det_bot3").css("display","none");
					$("#re_table").css("display","none");
				})
				$("#de_review").click(function(){
					$(".det_bot").css("display","none");
					$(".det_bot2").css("display","block");
					$(".det_bot3").css("display","none");
					$("#re_table").css("display","none");
				})
				$("#de_return").click(function(){
					$(".det_bot").css("display","none");
					$(".det_bot2").css("display","none");
					$(".det_bot3").css("display","block");
					$("#re_table").css("display","none");
				})
				
				
				//도서관 상세보기 - 기부자가 같은 도서 추천
				$(".gi_prev").click(function(){
					$(".list_book_cont").animate({
						left : 0
					});
				})
				
				$(".gi_next").click(function(){
					$(".list_book_cont").animate({
						left : -964
					});
				})
				
				
				//장바구니 제어
				//장바구니 구분 불러오기
				$(".cart_title").click(function(){
					$(".cart_title_cont").slideToggle();
				})
				
				//보여줄 장바구니 선택
				$("#libr").click(function(){
					$(".cart_lib").css("display","block");
					$(".cart_pan").css("display","none");
				})
				$("#mechn").click(function(){
					$(".cart_lib").css("display","none");
					$(".cart_pan").css("display","block");
				})
				
				//장바구니 내용(show, hide)
				$(".jang_conb").on("mouseenter",function(){
					$(this).children().eq(1).css("display","block");
				})
				$(".jang_conb").on("mouseleave",function(){
					$(this).children().eq(1).css("display","none");
				})				
				
			});

		
			//현재 가지고 있지 않습니다.
			function not_deli(){
				alert("거래 정지 되었거나 현재 거래중인 회원이 아닙니다.");
			}
			
			//한단계 뒤로 이동
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
				window.location = "/DoIt/d_login/login.do";
			}
			
			//리뷰 글쓰기 열림 제어
			function review_go(){
				document.getElementById("re_table").style.display="block";
				document.getElementById("re_table").focus();
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
							<li><a href="/DoIt/d_cart/headCartList.do?cols=d_rent">장바구니</a></li>
						</c:if>
						<c:if test="${ sessionScope.memId != null && sessionScope.memMG == 0 }">
							<li><a href="/DoIt/d_admin/admin.do">관리자 페이지</a></li>
						</c:if>
					</ul>
				</div>
			</header><%-- top_gnb 끝 --%>

 			<section class="l_list" >
					<ul class="l_list_ma">
						<li><p style="border:solid 1px red; width:220px; height:80px;">로고 삽입</p></li>
						<li><a href="#">회사소개</a></li>
						<li><a href="/DoIt/d_online/onSellBook.do">온라인중고서점</a></li>
						<li><a href="/DoIt/d_rent/list_cont.do?view_type=list_cont">DOIT 도서관</a></li>
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
							<li><a></a></li>	
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
		
		
		<jsp:include page="/d_cart/cart_part.jsp"></jsp:include>		
		
		<section id="mypage_wrap">
		
		
		
		
		
		
		
		
		
		

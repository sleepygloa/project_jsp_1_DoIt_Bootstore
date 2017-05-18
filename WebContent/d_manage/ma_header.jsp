<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<%-- header, footer  --%>
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/he_foot.css">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/reset.css">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/list.css">
		
		<%-- 회원관리 css 삽입 --%>
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/join.css">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/Login.css">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/side_menu.css">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/my_page.css">
		
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/d_rent.css">
		
		<%-- 관리자페이지 css --%>
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/manage.css?ver=6">
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/manage5.css">
		
		<%-- 직거래 css --%>
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/resell.css">
		
		<%-- 도서관 서비스 --%>
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/deli_order.css">
		
		<%-- 스크립트, Jquery  삽입 --%>
		<script type="text/javascript" src="/DoIt/js/jquery-3.1.1.js"></script>
		<script type="text/javascript" src="/DoIt/js/jquery-3.1.1.min.js"></script>

		
		<script type="text/javascript">
		$(document).ready(function(){
		
			$(".info2_bottom").click(function(){
				$(this).css("background","#e7ebee");
			})
			
			//메세지창 제어
			$(".mess_but").click(function(){
<<<<<<< HEAD
				$(".mess_cont").toggle();
			})
			
			$(".x_but").click(function(){
				$(".mess_cont").toggle();
=======
				$(".mess_cont").css("display","block");
			})
			
			$(".x_but").click(function(){
				$(".mess_cont").css("display","none");
>>>>>>> 06e83cdc0212d155692e1e75dda189dd861591c1
			})
			
			//이동 링크 제어
			$(".system_sel").click(function(){
				$(".mana_op").slideDown(350);
			})

			$(".sung_navi img").click(function(){
				$(".mana_op").slideUp(350);
			})
		
			//연체 리스트 출력
			$(".bl_cu").click(function(){
				$(".ef1").css("display","block");
				$(".ef2").css("display","none");
			})
			
			$(".bl_cu2").click(function(){
				$(".ef2").css("display","");
				$(".ef1").css("display","none");
			})
			
			
		});
		
		</script>
		
		<title>관리자 페이지</title>
	</head>
	
	<body>   
	
	

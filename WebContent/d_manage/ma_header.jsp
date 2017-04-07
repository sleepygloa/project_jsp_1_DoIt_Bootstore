<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
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
		
		<%-- 관리자페이지 css --%>
		<link rel="stylesheet" type="text/css"  href="/DoIt/css/manage.css">
		
		
		<%-- 스크립트, Jquery  삽입 --%>
		<script type="text/javascript" src="/DoIt/js/jquery-3.1.1.js"></script>
		<script type="text/javascript" src="/DoIt/js/jquery-3.1.1.min.js"></script>
		
		<script type="text/javascript">
		$(document).ready(function(){
		
			$(".info2_bottom").click(function(){
				$(this).css("background","#e7ebee");
			})
		
		});
		
		</script>
		
		<title>관리자 페이지</title>
	</head>
	
	<body>   
	
	

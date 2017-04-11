<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<%--------------- header include --------------%>
	<jsp:include page="/header.jsp"></jsp:include>
	
	<%--------------- 사이드 메뉴 include --------------%>
	<jsp:include page="/d_login/side_my.jsp"></jsp:include>
	
	
	
		<%-- 본문시작 --%>
			<%-- 본문내용 --%>
			<article class="my_cont_wrap">
				<p class="my_title">
					마이 페이지
				</p>
				<p class="my_sub_title">
					마이페이지에서는 회원정보 조회, 수정, 탈퇴를 할 수 있으며 구매내역을 확인하실 수 있습니다.
					<span>회원정보 수정 : 회원정보에서 아이디는 수정 하실 수 없습니다.</span>
					<span>회원 탈퇴 : 회원탈퇴 이후에는 로그인 하실 수 없습니다.</span>
				</p>
				

				<%-- 회원정보 조회 테이블 상단 --%>
				<p class="L_title">
					<a>나의 대여 / 구매 목록</a>
				</p>
				<p class="my_sub_title" style="border:none; padding:0px; margin-top:10px;">
					<span>* 대여 / 구매 목록 중 원하시는 버튼을 눌러주세요.</span>
				</p>

				<jsp:include page="/d_login/${ my_li }.jsp"></jsp:include>
				
			</article>
	
			
		
	<%--------------- footer include --------------%>
	<jsp:include page="/footer.jsp"></jsp:include>
	
	
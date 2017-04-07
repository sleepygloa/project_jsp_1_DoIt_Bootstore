<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    



	<%------- 관리자페이지 사이드 메뉴 ---------%>
	<article class="mana_side">
		
		<%------- 사이드 메뉴 상단 로고 ----%>
		<header>
			<p>로고자리</p>
			<p>로고2 자리</p>
		</header>
		
		
		<%------- 사이드 메뉴 관리자 정보 ----%>
		<section>
		
			<%-- 정보 상단 --%>
			<article class="ma_side_info1">
				<ul class="info1_top">
					<li><div class="thumb_ma"><img src="/DoIt/images/ma_img.jpg" /></div></li>
					<li><a>2017. 03. 14 (화)</a></li>
					<li>
						<a>직급 : [ 관리자 ]</a>
						<a>이름 : [ 이윤수 ]</a>
					</li>
				</ul>
			</article>
			
			<%-- 정보 중단 --%>
			<article class="ma_side_info2">
				<ul class="info2_bottom">
					<li><p class="dis"></p> <a href="#"><img src="/DoIt/images/all_crew_icon.png" /><span>회원 관리</span></a></li> <%-- 회원관리 --%>
					<li><p class="dis"></p> <a href="#"><img src="/DoIt/images/dash_icon.png" /> <span>판매 상품 관리</span></a></li> <%-- 상품 등록, 검수 --%>
					<li><p class="dis"></p> <a href="#"><img src="/DoIt/images/dash_icon.png" /> <span>승인 요청관리</span></a></li> <%-- 기증도서 승인, 판매자 신청 승인 --%>
					<li><p class="dis"></p> <a href="#"><img src="/DoIt/images/dash_icon.png" /> <span>연체자 블랙리스트</span></a></li> <%-- 연체자 리스트 및 판매취소자 리스트 --%>
					<li><p class="dis"></p> <a href="#"><img src="/DoIt/images/stock_icon.png" /><span>도서 재고관리</span></a></li> <%-- 전체 도서 리스트, 폐기처분 --%>
					<li><p class="dis"></p> <a href="#"><img src="/DoIt/images/dash_icon.png" /> <span>Total Dashboard</span></a></li> <%-- 종합현황판 : 매출액, 잘팔리는 도서, 블랙리스트, 일정.. --%>
				</ul>
			</article>
			
			<%------- 관리자 목록  ----%>
			<article>
				<p>
					<a><img src="" /></a>
					<a>Managers</a>
				</p>
				<p>
					<a>Name</a>
					<a>on</a>
				</p>
				
			</article>
			
		</section>
		
		
		<footer>
			<%-- 로그아웃 버튼 --%>
			<p>
				<a><img src=""/></a>
				<a>Log Out</a>
			</p>
			
			<%-- 카피라이트  --%>
			<ul>
				<li><a>Copyright ⓒ 2017 <span>DOITBOOK</span></a></li>
				<li><a>Design by Mr.Lee</a></li>
			</ul>
		</footer>
	
	</article>









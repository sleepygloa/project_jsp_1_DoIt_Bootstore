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
						<a>직급 : [ DOIT 관리자 ]</a>
						<a>이름 : [ 이윤수 ]</a>
					</li>
				</ul>
			</article>
			
			
			<%-- 정보 중단 --%>
			<article class="sec_art_1">
				<ul>
					<li><a href="#"><p></p><span>회원관리</span></a></li>				<%-- 회원관리 --%>
					<li><a href="#"><p></p><span>승인 요청 관리</span></a></li>		<%-- 기증도서 승인, 판매자 신청 승인 --%>
					<li><a href="#"><p></p><span>연체자 블랙리스트</span></a></li>	<%-- 연체자 리스트 및 판매취소자 리스트 --%>
					<li><a href="#"><p></p><span>도서 재고관리</span></a></li>			<%-- 전체 도서 리스트, 폐기처분,상품 등록, 검수 --%>
					<li><a href="#"><p></p><span>Total Dashboard</span></a></li>		<%-- 종합현황판 : 매출액, 잘팔리는 도서, 블랙리스트, 일정.. --%>
				</ul>
			</article>
			
			
			
			<%------- 관리자 목록  ----%>
			<article class="mana_mem">
				<p>
					<a class="fl_le" style="margin-right:16px;"><img src="/DoIt/images/mess.png" /></a>
					<a class="fl_le">Messenger</a>
				</p>
				<p>
					<a class="fl_le">Name</a>
					<a class="fl_ri">on</a>
				</p>
				
				<p class="list_condd">
					<a class="fl_le">[ Lee ]</a>
					<a class="fl_ri">Log On</a>
				</p>
				
				<%------------ 메세지 창------------------%>
				<%-- 버튼 제어 --%>
				<a class="mess_but"></a>
					
				<%-- 내용물 파트 --%>
				<div class="mess_cont">
					<%-- 메세지 창 제목 --%>
					<header class="mess_he">
						<p class="fl_le">
							<a ><img src="">DOIT Talking Table ..!!</a>
							<a >2017. 04. 02 (월)</a>
						</p >
						<p class="fl_ri x_but"></p>
					</header>
					
					<%-- 메세지  내용 파트 --%>
					<section class="mess_sec">
						<article class="mess_wrapa">
							<p class="mess_conw">
								<a><img src="/DoIt/images/ma_img.jpg"></a>
								<a>이윤수<br><span>안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요</span></a>
							</p>
						</article>
					</section>
					
					<%-- 메세지 창 입력 폼 --%>
					<footer class="mess_foot">
						<form action="#" method="POST" >
							<input type="text" name="mess_con" class="">
							<button type="submit">전송</button>
						</form>
					</footer>
				</div>
				
				
			</article>
			
		</section>
		
		
		<footer>
			<%-- 로그아웃 버튼 --%>
			<p class="log_out_b" onclick="window.location='/DoIt/d_login/logout.do' ">
				<a><img src="/DoIt/images/logout.png"/></a>
				<a>Log Out</a>
			</p>
			
			<%-- 카피라이트  --%>
			<ul class="copy_ri2">
				<li><a>Copyright ⓒ 2017 <span>DOITBOOK</span></a></li>
				<li><a>Design by Mr.Lee</a></li>
			</ul>
		</footer>
	
	</article>









<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



	<%------- 관리자페이지 사이드 메뉴 ---------%>
	<article class="mana_side">
		
		<%------- 사이드 메뉴 상단 로고 ----%>
		<header style="padding-left:10px; padding-top:5px; background:#202126">
			<p style="width:100%; "><a style="font-size:2em"><span style="font-size:1.8em; color:#fdcd21">Do</span> Doit Book</a></p>
			<p style="width:100%; "><img src="/DoIt/images/24hour.png"/></p>
		</header>
		
		
		<%------- 사이드 메뉴 관리자 정보 ----%>
		<section>
		
			<%-- 정보 상단 --%>
			<article class="ma_side_info1">
				<ul class="info1_top">
					<li><div class="thumb_ma"><img src="/DoIt/images/ma_img.jpg" /></div>
					</li>
					<li><a>2017. 03. 14 (화)</a></li>
					<li>
						<a>직급 : [
						<c:if test="${ sessionScope.memMG == 10 }">
							 DOIT 관리자 
						</c:if>
						]</a>
						<a>이름 : [ ${ sessionScope.memName } ]</a>
					</li>
				</ul>
			</article>
			
			
			<%-- 정보 중단 --%>
			<article class="sec_art_1">
				<ul>
					<li><a href="/DoIt/d_manage/manPart1.do"><p></p><span>회원관리</span></a></li>				<%-- 회원관리 --%>
					<li><a href="/DoIt/d_manage/manPart2.do"><p></p><span>승인 요청 관리</span></a></li>		<%-- 기증도서 승인, 판매자 신청 승인 --%>
					<li><a href="/DoIt/d_manage/manPart3.do"><p></p><span>연체자 블랙리스트</span></a></li>	<%-- 연체자 리스트 및 판매취소자 리스트 --%>
					<li><a href="/DoIt/d_manage/manPart4.do"><p></p><span>도서 재고관리</span></a></li>			<%-- 전체 도서 리스트, 폐기처분,상품 등록, 검수 --%>
					<li><a href="/DoIt/d_manage/manager.do"><p></p><span>Total Dashboard</span></a></li>		<%-- 종합현황판 : 매출액, 잘팔리는 도서, 블랙리스트, 일정.. --%>
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
				
				<%-- ajax 메세지 --%>
				<script type="text/javascript">

				// Ajax 작성 : null 에러 발생
				function chatIns() {
					var messCont = $("#chat_cont").val();
				    $.ajax({
				        type: "POST",
				        url: "/DoIt/d_manage/chat.do?cont="+messCont,
				        success: function(data) {
							// DB 처리 성공 시 수행 내용 작성
				            chatSel(); //불러오기 실행
				            $("#chat_cont").val("");
				            $("#chat_cont").focus();
				        },
				        error: function(){
							// 실패 시 수행 내용
							alert("불러오기를 실패하였습니다.");
				            return false;
				        }
				        
				    });
				}
				
				function chatSel(){
					 $.ajax({
					        type: "POST",
					        url: "/DoIt/d_manage/chatSel.do",
					        success: function(data) {
								// DB 처리 성공 시 수행 내용 작성
					            $(".mess_sec").html(data);
								
					          	//아래로 위치 이동
								var SBot =  $(".mess_sec").children().length * 100;
								console.log(SBot);
								$(".mess_sec").scrollTop(SBot);
								
					        },
					        error: function(){
								// 실패 시 수행 내용
								alert("불러오기를 실패하였습니다.");
					            return false;
					        }
					        
					    });
				}
				
				
				
				$(document).ready(function(){
					
					setInterval("chatSel()", 5000); // 매 5000ms(5초)가 지날 때마다 chatSel() 함수를 실행합니다.
					
				})

				</script>
				
				
				<%-- 버튼 제어 --%>
				<a class="mess_but" onclick="chatSel()"></a>
					
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
						
					</section>
					
					<%-- 메세지 창 입력 폼 --%>
					<footer class="mess_foot">
						<input type="text" name="mess_con" id="chat_cont">
						<button type="submit" id="chat_sub" onclick="chatIns()">전송</button>
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









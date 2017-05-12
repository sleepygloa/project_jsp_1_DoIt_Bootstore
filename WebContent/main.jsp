<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" ></jsp:include>

<script>
	$(document).ready(function(){
		
		//새로운 class 부여
		$("#mypage_wrap").addClass("main_section");
		
		//위의 넓이조절
		$(".wa").css("height","119px");
		
		//묶고있는 section 태그 제거
		$("#mypage_wrap").removeAttr("id");
		
		//움직이는 비주얼
		$(".visu_bu1").on("click",function(){
			$(".visu_wrap").css("transition","all 1.2s ease 0s"); 
			$(".visu_wrap").css("left","0px"); 
		})
		$(".visu_bu2").on("click",function(){
			$(".visu_wrap").css("transition","all 1.2s ease 0s"); 
			$(".visu_wrap").css("left","-100%");
		})
		$(".visu_bu3").on("click",function(){
			$(".visu_wrap").css("transition","all 1.2s ease 0s");
			$(".visu_wrap").css("left","-200%");
		})
		
		//자동 비주얼 슬라이드
		//auto_slide(0);
		function auto_slide(a){ 
			setInterval(function(){ //5초
				a += -100;
				$(".visu_wrap").css("transition","all 1.2s ease 0s");
				$(".visu_wrap").css("left",a+"%");
				if(a == -200){
					a=100
				}
				//console.log(a);
			}, 8000)
			
		}
		
		//비주얼 슬라이드 자동 정지 플레이
		$(".visu_pla").on("click",function(){
			auto_slide(0);
		})
		//$(".visu_pau").on("click",function(){
			//clearInterval(auto_slide(0));
		//})
		
		getPart1(01);
		
		//온라인 판매 불러 상세보기 
		$("#part1_cont").on("mouseenter",".chu_part1",function(){	
			$(this).find(".chu_part1_cont").stop();
			$(this).find(".chu_part1_cont").animate({
				bottom:0
			});
		})

		$("#part1_cont").on("mouseleave",".chu_part1_cont",function(){		
			$(this).stop();
			$(this).animate({
				bottom:-75
			});
		})
		
		//온라인 판매(4) 추천도서2 제어버튼
		$(".chu_part1_1_wrap").on("mouseenter",function(){
			$(".part1_1_pre").css("display","block");
			$(".part1_1_nex").css("display","block");
		})
		
		$(".chu_part1_1_wrap").on("mouseleave",function(){
			$(".part1_1_pre").css("display","none");
			$(".part1_1_nex").css("display","none");
		})
		
		$(".part1_1_pre").on("mouseenter",function(){
			$(this).css("background-position","0% 99%");
		})
		$(".part1_1_pre").on("mouseleave",function(){
			$(this).css("background-position","0% 0%");
		})
		$(".part1_1_nex").on("mouseenter",function(){
			$(this).css("background-position","100% 99%");
		})
		$(".part1_1_nex").on("mouseleave",function(){
			$(this).css("background-position","100% 0%");
		})
		
		//온라인 판매 추천 도서
		$(".part1_1_pre").on("click",function(){
			li1();
		})
		$(".part1_1_nex").on("click",function(){
			li2();
		})
		
		function li1(){
			$(".chu_part1_1").css("transition","all 1.2s ease 0s");
			$(".chu_part1_1").css("left","0px");
		}
		function li2(){
			$(".chu_part1_1").css("transition","all 1.2s ease 0s");
			$(".chu_part1_1").css("left","-100%");
		}
		setInterval(function(){
			li1();
		},4500)
		
		setInterval(function(){
			li2();
		},9000)
		
		
		
		//직거래 타이틀 이동
		$(".left_bid_ti1").on("mouseenter",function(){
			li3();
		})
		$(".left_bid_ti2").on("mouseenter",function(){
			li4();
		})
		
		function li3(){
			$(".left_bid_c p").css("transition","all 1.2s ease 0s");
			$(".left_bid_c p").css("top","0");
			$(".llllkj1").css("background","#7f7665");
		}
		function li4(){
			$(".left_bid_c p").css("transition","all 1.2s ease 0s");
			$(".left_bid_c p").css("top","-100%");
			$(".llllkj1").css("background","#5d0008");
		}
		setInterval(function(){
			li3();
		},5000)
		setInterval(function(){
			li4();
		},10000)
		
	})
	
		//온라인 판매 불러올 ajax
		function getPart1(mea){
			$.ajax({
		        type: "POST",
		        url: "/DoIt/d_online/onSellBook2.do?d_bonFillter=0"+mea,
		        success: function(data) {
					// DB 처리 성공 시 수행 내용 작성
					$("#part1_cont").html(data);
		        },
		        error: function(){
					// 실패 시 수행 내용
					alert("불러오기를 실패하였습니다.");
		            return false;
		        }
		        
		    });
			
		}
	
</script>



<%-- 비주얼 파트 --%>
<article class="visu_wrap_wrap">
	<%-- 실제들어가는 비주얼 부분--%>
	<ul class="visu_wrap">
		<li><a href="/DoIt/d_online/onSellBook.do"><img src="/DoIt/images/visu2.png" /></a></li>
		<li><a href="/DoIt/d_rent/list_cont.do?view_type=list_cont"><img src="/DoIt/images/visu3.png" /></a></li>
		<li><a href="/DoIt/d_resell/reList.do"><img src="/DoIt/images/visu4.png" /></a></li>
	</ul>
	
	<%-- 제어 버튼 --%>
	<div class="visu_but_wrap">
		<ul class="visu_but">
			<li><p class="visu_bu1"></p></li>
			<li><p class="visu_bu2"></p></li>
			<li><p class="visu_bu3"></p></li>
			<li><a class="visu_pla"><img src="/DoIt/images/play.png" /></a></li>
			<li><a class="visu_pau"><img src="/DoIt/images/pause.png" /></a></li>
		</ul>
	</div>
	
</article>


<%-- 온라인 판매 추천 --%>
<article class="online_chuchu">
	<%-- 가운데 정렬 div --%>
	<div class="mar_au">

		<%-- 상위 도서 추천 왼쪽 --%>
		<article class="left_online fl_le">
			<%-- 온라인 판매 제목 및 구분파트 --%>
			<header>
				<p class="sl_titl fl_le">온라인 판매</p>
				
				<ul class="ssll_titl fl_ri">
					<li><a href="javascript:getPart1(01)">소설 / 시 / 에세이</a><span>|</span></li>
					<li><a href="javascript:getPart1(02)">참고서적</a><span>|</span></li>
					<li><a href="javascript:getPart1(03)">어린이서적</a><span>|</span></li>
					<li><a href="javascript:getPart1(04)">인문학서적</a><span>|</span></li>
					<li><a href="javascript:getPart1(05)">과학전문서적</a><span>|</span></li>
				</ul>
			</header>
			
			<%-- 불러올 내용 --%>
			<section id="part1_cont">
				
			</section>
		
		</article>
		
		<%-- 추천 안내 오른쪽--%>
		<article class="right_online fl_ri">
			<p class="sl_titl fl_le" style="margin-bottom:20px;">추천도서</p>
			
			<div class="chu_part1_1_wrap">
				
				<%-- 슬라이드 내용 --%>
				<div class="chu_part1_1">
					<p class="fl_le">
						<a href="/DoIt/d_online/onSellBook.do"><img src="/DoIt/images/part1_1_1.jpg"/></a>
					</p>
					<p class="fl_le">
						<a href="/DoIt/d_online/onSellBook.do"><img src="/DoIt/images/part1_1_2.jpg"/></a>
					</p>
				</div>
				<%-- 슬라이드 내용 끝--%>
				
				<p class="part1_1_pre"></p>
				<p class="part1_1_nex"></p>
			</div>
		</article>
	</div>
	
</article>
<%-- 온라인 판매 추천 끝--%>


<%-- 직거래 게시판 안내 --%>
<article class="bid_wrap">
	
	<%-- 배경지정 --%>
	<div class="llllkj1"></div>
	<div class="llllkj2"></div>
	
	<%-- 가운데 자리 정해줄 위치 --%>
	<article class="bid_con_wrap">
	
		<%-- 왼쪽 직거래 게시판 안내 --%>
		<article class="left_bid">
			<%-- 선택할 제목 태그 --%>
			<ul class="left_bid_title">
				<li class="left_bid_ti1"><p>온라인 도서 경매<br/>경매를 통해 저렴하게!</p></li>
				<li class="left_bid_ti2"><p>경매 서비스로<br/>입찰과 낙찰!</p></li>
				<li class="left_bid_ti1"><p>온라인 도서 경매<br/>경매를 통해 저렴하게!</p></li>
				<li class="left_bid_ti2"><p>경매 서비스로<br/>입찰과 낙찰!</p></li>
			</ul>
				
			<%-- 내용 파트 --%>
			<div class="left_bid_c">
				<%-- 내용파트 내용 --%>
				<p>
					<a href=""><img src="/DoIt/images/bid_co.jpg" /></a>
				</p>
			</div>
			
		</article>
		
		<%-- 오른쪽 직거래 판매자 소개 --%>
		<%-- 오른쪽 바탕 --%>
		<article class="right_bid_wrap">
			<%-- 왼쪽 정렬 --%>
			<div class="right_bid_cont">
				<img src="/DoIt/images/ad_worldroaming.jpg" />
			</div>
		</article>
	
	</article>

</article>


<%-- 도서관 서비스 및 오늘 들어온 책 --%>
<article class="lib_all_wrap">
	
	<%-- 오늘 들어온 책 표시 --%>
	<div class="lib_left1 fl_le">
			
		<p class="lib_left_title">Today's Books</p>
			
		<p class="left_title_img">
			<c:choose>
			    <c:when test="${todayPurchaseCountArray[0] == null}"><img src="/DoIt/images/0.png" width="35px" /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 0}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 1}"><img src="/DoIt/images/1.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 2}"><img src="/DoIt/images/2.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 3}"><img src="/DoIt/images/3.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 4}"><img src="/DoIt/images/4.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 5}"><img src="/DoIt/images/5.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 6}"><img src="/DoIt/images/6.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 7}"><img src="/DoIt/images/7.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 8}"><img src="/DoIt/images/8.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 9}"><img src="/DoIt/images/9.png" width="35px"  /></c:when>
			</c:choose>
			<c:choose>
				<c:when test="${todayPurchaseCountArray[1] == null}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>   
				<c:when test="${todayPurchaseCountArray[1] == 0}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 1}"><img src="/DoIt/images/1.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 2}"><img src="/DoIt/images/2.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 3}"><img src="/DoIt/images/3.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 4}"><img src="/DoIt/images/4.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 5}"><img src="/DoIt/images/5.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 6}"><img src="/DoIt/images/6.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 7}"><img src="/DoIt/images/7.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 8}"><img src="/DoIt/images/8.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 9}"><img src="/DoIt/images/9.png" width="35px"  /></c:when>
			</c:choose>					
			<c:choose>
				<c:when test="${todayPurchaseCountArray[2] == null}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>   
				<c:when test="${todayPurchaseCountArray[2] == 0}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 1}"><img src="/DoIt/images/1.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 2}"><img src="/DoIt/images/2.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 3}"><img src="/DoIt/images/3.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 4}"><img src="/DoIt/images/4.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 5}"><img src="/DoIt/images/5.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 6}"><img src="/DoIt/images/6.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 7}"><img src="/DoIt/images/7.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 8}"><img src="/DoIt/images/8.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 9}"><img src="/DoIt/images/9.png" width="35px"  /></c:when>
			</c:choose>	
			<span> , </span>
			<c:choose>
			    <c:when test="${todayPurchaseCountArray[0] == null}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 0}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 1}"><img src="/DoIt/images/1.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 2}"><img src="/DoIt/images/2.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 3}"><img src="/DoIt/images/3.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 4}"><img src="/DoIt/images/4.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 5}"><img src="/DoIt/images/5.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 6}"><img src="/DoIt/images/6.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 7}"><img src="/DoIt/images/7.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 8}"><img src="/DoIt/images/8.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 9}"><img src="/DoIt/images/9.png" width="35px"  /></c:when>
			</c:choose>
			<c:choose>
				<c:when test="${todayPurchaseCountArray[1] == null}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>   
				<c:when test="${todayPurchaseCountArray[1] == 0}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 1}"><img src="/DoIt/images/1.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 2}"><img src="/DoIt/images/2.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 3}"><img src="/DoIt/images/3.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 4}"><img src="/DoIt/images/4.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 5}"><img src="/DoIt/images/5.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 6}"><img src="/DoIt/images/6.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 7}"><img src="/DoIt/images/7.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 8}"><img src="/DoIt/images/8.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 9}"><img src="/DoIt/images/9.png" width="35px"  /></c:when>
			</c:choose>					
			<c:choose>
				<c:when test="${todayPurchaseCountArray[2] == null}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>   
				<c:when test="${todayPurchaseCountArray[2] == 0}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 1}"><img src="/DoIt/images/1.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 2}"><img src="/DoIt/images/2.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 3}"><img src="/DoIt/images/3.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 4}"><img src="/DoIt/images/4.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 5}"><img src="/DoIt/images/5.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 6}"><img src="/DoIt/images/6.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 7}"><img src="/DoIt/images/7.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 8}"><img src="/DoIt/images/8.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 9}"><img src="/DoIt/images/9.png" width="35px"  /></c:when>
			</c:choose>	
			<span> , </span>
			<c:choose>
			    <c:when test="${todayPurchaseCountArray[0] == null}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 0}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 1}"><img src="/DoIt/images/1.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 2}"><img src="/DoIt/images/2.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 3}"><img src="/DoIt/images/3.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 4}"><img src="/DoIt/images/4.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 5}"><img src="/DoIt/images/5.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 6}"><img src="/DoIt/images/6.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 7}"><img src="/DoIt/images/7.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 8}"><img src="/DoIt/images/8.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[0] == 9}"><img src="/DoIt/images/9.png" width="35px"  /></c:when>
			</c:choose>
			<c:choose>
				<c:when test="${todayPurchaseCountArray[1] == null}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>   
				<c:when test="${todayPurchaseCountArray[1] == 0}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 1}"><img src="/DoIt/images/1.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 2}"><img src="/DoIt/images/2.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 3}"><img src="/DoIt/images/3.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 4}"><img src="/DoIt/images/4.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 5}"><img src="/DoIt/images/5.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 6}"><img src="/DoIt/images/6.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 7}"><img src="/DoIt/images/7.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 8}"><img src="/DoIt/images/8.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[1] == 9}"><img src="/DoIt/images/9.png" width="35px"  /></c:when>
			</c:choose>					
			<c:choose>
				<c:when test="${todayPurchaseCountArray[2] == null}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>   
				<c:when test="${todayPurchaseCountArray[2] == 0}"><img src="/DoIt/images/0.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 1}"><img src="/DoIt/images/1.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 2}"><img src="/DoIt/images/2.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 3}"><img src="/DoIt/images/3.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 4}"><img src="/DoIt/images/4.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 5}"><img src="/DoIt/images/5.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 6}"><img src="/DoIt/images/6.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 7}"><img src="/DoIt/images/7.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 8}"><img src="/DoIt/images/8.png" width="35px"  /></c:when>
				<c:when test="${todayPurchaseCountArray[2] == 9}"><img src="/DoIt/images/9.png" width="35px"  /></c:when>
			</c:choose>	
			<span> 권</span>
		</p>
		
		<%-- 목업 안내 --%>
		<p class="mockup">
			<img src="/DoIt/images/mock.png" />
		</p>
		
	</div>
	
	<%-- 도서관 서비스 안내 이미지 --%>
	<div class="lib_right1 fl_le">
		<p style="width:100%; height:280px"><iframe width="100%" height="280px" src="https://www.youtube.com/embed/16iiNF23Z_0?ecver=1&amp;autoplay=1&version=3&loop=1&playlist=AaBbCcDd123
		&controls=0&showinfo=0&rel=0" frameborder="0" ></iframe></p>
		<p><img src="/DoIt/images/bimil.PNG" style="width:100%; height:320px;"/></p>
	</div>

</article>



<%-- 고객센터 안내 --%>
<article class="doit_go">
	<p><a href="/DoIt/d_customer/faqList.do"></a></p>
</article>

<jsp:include page="footer.jsp" ></jsp:include>





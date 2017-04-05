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
					<a>나의 대여 목록</a>
				</p>
				<p class="my_sub_title" style="border:none; padding:0px; margin-top:10px;">
					<span>* 대여도서에 대한 배송 조회입니다.</span>
				</p>
				
				<ul class="my_list_tab23">
					<li><a href="#">배송 조회 ▼</a></li>
					<li><a></a></li>
				</ul>

				<%-- 이용안내 --%>
				<p class="deli_title">
					<img src="/DoIt/images/icon_bullet2.jpg" />
					<a>소개</a>
				</p>
				<ul class="deli_info1">
					<li><a>DoIt 서비스는 CJ대한통운과 협약을 맺어(?) 안정적으로 도서를 대여 / 구매 할 수 있습니다.</a></li>
					<li><a>배송단계는 4단계로 이루어지며, 모든단계를 거쳐 도착하는데 2~3일이 소요됩니다.</a></li>
				</ul>
				
				<%-- 배송단계 --%>
				<p class="deli_title">
					<img src="/DoIt/images/icon_bullet2.jpg" />
					<a>배송단계</a>
				</p>
				<div class="deli_info_wrap">
					<ul class="deli_info2">
						<li><img src="/DoIt/images/wait.png" /></li>
						<li></li>
						<li>배송 승인 대기</li>
						<li>
							<p>회원에게 대여(반납)를 해줄것인지 관리자가 확인중인 상태입니다.</p>
						</li>
					</ul>
					<ul class="deli_info2">
						<li><img src="/DoIt/images/manage.png" /></li>
						<li><img src="/DoIt/images/arrowa1.png" /></li>
						<li>배송 준비</li>
						<li>
							<p>CJ대한통운에게 배송(반납)을 요청하고 있는 단계입니다.</p>
						</li>
					</ul>
					<ul class="deli_info2">
						<li><img src="/DoIt/images/deli.png" /></li>
						<li><img src="/DoIt/images/arrowa1.png" /></li>
						<li>배송중</li>
						<li>
							<p>CJ대한통운에서 해당 회원에게 배송(반납)중입니다.</p>
						</li>
					</ul>
					<ul class="deli_info2">
						<li><img src="/DoIt/images/result.png" /></li>
						<li><img src="/DoIt/images/arrowa1.png" /></li>
						<li>배송완료</li>
						<li>
							<p>해당 회원에게 DOIT도서를 배송(반납)완료 하였습니다.</p>
						</li>
					</ul>
				</div>
				<%-- 4단계 끝 --%>
				
				<%-- 반납 안내 --%>
				<ul class="deli_info1">
					<li><a>반납신청은 도서관리스트에서 하거나 아래의 버튼을 눌러 진행 해주세요.</a></li>
				</ul>
				
				
				<%-- 현재 배송되고 있는 사람 정보 --%>
				<p class="deli_title">
					<img src="/DoIt/images/icon_bullet2.jpg" />
					<a>현재 배송(반납) 상태</a>
				</p>
				<ul class="deli_info1">
					<li>
						<a>현재 배송(반납) 상태 : [ <span style="color:red">${ deli_info }</span> ]</a>
					</li>
					<li>
						<a>현재 거래 중인 회원번호 : [ <span style="color:red">${ firstMan }</span> ]</a>
					</li>
				</ul>
				
				<%-- 문의 안내 --%>
				<p class="deli_title">
					<img src="/DoIt/images/icon_bullet2.jpg" />
					<a>문의</a>
				</p>
				<ul class="deli_info1">
					<li><a>문의 사항은 1600 - 5252 로 연락 주시거나 고객센터에 1:1문의를 이용해주세요.</a></li>
				</ul>
				
				
				<p class="line_wrap"></p>
				
				<c:if test="${ memNo == firstMan }">
					<p class="re_deli_but">
						<a href="#">반납 신청하기</a>
					</p>
				</c:if>
				<c:if test="${ memNo != firstMan }">
					<p class="re_deli_but">
						<a onclick="not_deli()">반납 신청하기</a>
					</p>
				</c:if>
				
				
				
			</article>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
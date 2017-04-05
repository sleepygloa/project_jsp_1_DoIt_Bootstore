<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="/header.jsp"></jsp:include>


		<section class="deli_order">
		<h1> 주문 결제 창</h1>
		
			<header class="deli_order_he">
			<h2> 주문결제 header nav</h2>
				<article class="order_le fl_le">
				<h3> 주문결제 header 제목</h3>
					<p><img src="/DoIt/images/deli_icon.png"/><span> 주문하기</span></p>
					<p><img src="/DoIt/images/24hour_he.png"/><span>24 Hours / 7 Days</span></p>
				</article><!-- 제목 박스 왼쪽 끝-->
		
				<article class="order_ri fl_ri">
				<h3> 주문결제 header nav 아이콘</h3>
					<a><img src="/DoIt/images/del_order_icon1.png"/></a>
					<a><img src="/DoIt/images/del_order_icon2.png"/></a>
					<a><img src="/DoIt/images/del_order_icon3_3.png"/></a>
				</article><!-- 제목 박스 오른쪽 끝-->
			</header><!-- 제목 박스 끝-->
			
			
			
			
			<section class="del_order_sec_re">
			<h2> 주문완료 안내</h2>
				<article class="del_ord_par1">
				<h3> 주문완료 내용1</h3>
					<header class="del_ord_title">
					<h4> 주문완료 제목</h4>
						<p>주문하신 상품의 결제가 <span>완료</span>되었습니다.</p>
						<p>주문하신 상품은 맥라이더가 안전하고 신속하게 배달할 예정입니다. 잠시만 기다려 주세요.</p>
					</header><!--del_ord_title 끝-->
		
					<section class="del_ord_cont">
					<h4> 주문완료 내용</h4>
						<table cellspacing="0">
							<colgroup>
							<col width="3.5%"><col width="6.5%">
							</colgroup>
							<tbody>
							<tr>
								<td class="bo_to">주문자 이름</td>
								<td class="bo_to"><?=$_SESSION['user_name']; ?></td>
							</tr>
							<tr>
								<td>전화번호</td>
								<td><?=$_SESSION['user_phone']; ?></td>
							</tr>
							<tr>
								<td>주소입력</td>
								<td><?=$_SESSION['user_addr']; ?></td>
							</tr>
							<tr>
								<td class="bo_bo">배송 요청사항</td>
								<td class="bo_bo"></td>
							</tr>
							</tbody>
						</table>
					</section><!--del_ord_cont 끝-->
		
				</article><!--주문완료 상단 완료-->
		
			</section><!--del_order_sec_re 끝-->
		
			<footer class="del_order_sec_foo">
			<h2> 주문완료 안내2</h2>
				<table cellspacing="0">
					<colgroup>
					<col width="2.5%"><col width="2.5%"><col width="2.5%"><col width="2.5%">
					</colgroup>
					<thead>
						<tr>
							<th>주문금액</th>
							<th>주문상품</th>
							<th>할인금액</th>
							<th>총 주문금액</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><span id="choi_price">0</span> 원</td>
							<td><span>0,0,0,0</span> 번</td>
							<td><span>0</span> 원</td>
							<td><span id="choi_price2"></span> 원</td>
						</tr>
					</tbody>
				</table>
			</footer><!--del_order_sec_foo 끝-->
		
			<aside class="main_bu">
			<h4>메인으로 가기</h4>
				<a onclick="#">메인으로</a>
			</aside>
		
		</section><!--deli_order 전체 끝-->

    
    
    
<jsp:include page="/footer.jsp"></jsp:include>   
    
    
    
    
    
    
    

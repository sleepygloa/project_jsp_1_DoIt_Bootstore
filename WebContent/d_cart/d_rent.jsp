<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 도서관 홍보 동영상 --%>

						
		
				<%--  주문결제 오른쪽 --%>
				<article class="del_order_ri fl_ri">
				<h3> 주문결제 오른쪽 part</h3>	
					<header class="deli_menu_title">
					<h4>결제창 제목</h4>
						<p>총 상품 결제 금액</p>
					</header><%--deli_menu_gel 결제 창 제목 끝--%>
							
					<section class="deli_menu_gel">
					<h4>결제창 내용</h4>
						
						<article class="deli_menu_gel_con box_sizing" style="padding-bottom:0px">
						<h5>홍보 이미지</h5>	
							<img src="/DoIt/images/bookO.jpg" style="width:100%;"/>
						</article>
		
					</section><%--deli_menu_gel 결제 창 위쪽 끝--%>
		
					<footer class="deli_menu_bang box_sizing">
						<header class="menu_bang_he">
							<a>결제수단 선택</a>
							<select class="box_sizing" id="gyul">
								<option value="card" name="card">도서관 서비스는 결제가 필요없습니다.</option>
							</select>
							<p>
								<a>
										대여시간은 7일, 연체시에 연체료가 
										부과. <br>확인 및 대여 진행에 동의합니다.
										<br>(전자상거래법 제8조 2항)<br><br>
										▶  대여하기를 눌러주세요.
								</a>
							</p>
						</header><%--deli_menu_gel 결제 창 아래쪽 01 끝--%>
		
						<footer class="menu_bang_foot">
							<a id="rider_jumoon" href="/DoIt/d_cart/payshot.do?cols=d_rent">대여하기</a>
						</footer><%--deli_menu_gel 결제 창 아래쪽 02 끝--%>
					</footer>
		
				</article><%-- 주문결제 오른쪽 part 끝--%>









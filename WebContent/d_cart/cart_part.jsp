<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
		<%-- 장바구니 전체 --%>
		<div id="cart_wrap" >
			<%-- 장바구니 본문  --%>
			<div class="cart_start">
				<%-- 우측 정렬 wrap --%>
				<div class="ri_wrap">
					<%-- 장바구니 구분 탭 --%>
					<header class="cart_tab">
						<p class="cart_title">장바구니 ▼</p>
						<ul class="cart_title_cont">
							<li><a id="libr">DOIT 도서관</a></li>
							<li><a id="mechn">직접 판매물</a></li>
						</ul>
					</header>
					<%-- 장바구니 구분 탭 끝 --%>
					
					<%-- 장바구니 본문 --%>
					<section class="cart_sec">
						<%-- 도서관 장바구니 --%>
						<article class="cart_lib">
							<p class="cart_title">도서관</p>
							<p class="cart_link"><a href="/DoIt/d_rent/list_cont.do?view_type=list_cont">도서관 List ▶</a></p>
							<ul class="cart_lib_list">
								<li>
									
									<c:forEach var="ca" items="${ sessionScope.CartL }">
									<a href="/DoIt/d_rent/detail.do?br_no=${ ca.getBr_no() }" class="jang_conb">
										<span><img src="/DoIt/save/${ ca.getBr_thumpic() }" /></span>
										<span class="jang_cona">
											<p>${ ca.getBr_name() }</p> <br>
											<span style="font-size:18px; color:#3DB7CC">무료</span>
										</span>
									</a>
									</c:forEach>
									
								</li>
							</ul>
						</article>
						<%-- 도서관 장바구니 끝--%>
						
						
						<%-- 직접판매 장바구니 --%>
						<article class="cart_pan" style="display:none">
							<p class="cart_title">직접판매도서</p>
							<p class="cart_link"><a href="#">직접판매 List ▶</a></p>
							<ul class="cart_lib_list">
								<li>
									
									<c:forEach var="ca" items="${ sessionScope.CartP }">
									<a href="#" class="jang_conb">
										<span><img src="/DoIt/save/${ ca.getBr_thumpic() }" /> 책사진</span>
										<span class="jang_cona">
											<p>${ ca.getBr_name() }책 이름</p> <br>
											<span style="font-size:18px; color:#3DB7CC">가격 표시</span>
										</span>
									</a>
									</c:forEach>
									
								</li>
							</ul>
						</article>
						<%-- 직접판매 장바구니 끝--%>
						
					</section>
					<%-- 장바구니 본문 끝 --%>
				</div>
				<%-- 우측 정렬 --%>
			</div>
			<%-- 장바구니 본문 내용 끝 --%>
			
		</div>
		<%-- 장바구니 전체 끝 --%>
		
		
		
		
		
		
		
		
		
		
		
		
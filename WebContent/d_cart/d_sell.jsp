<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				<%--  주문결제 오른쪽 --%>
				<article class="del_order_ri fl_ri">
				<h3> 주문결제 오른쪽 part</h3>	
					<header class="deli_menu_title">
					<h4>결제창 제목</h4>
						<p>총 상품 결제 금액</p>
					</header><%--deli_menu_gel 결제 창 제목 끝--%>
							
					<section class="deli_menu_gel">
					<h4>결제창 내용</h4>
						
						<article class="deli_menu_gel_con box_sizing">
						<h5>결제창 메뉴 지정 내용</h5>	
							<p><a>총 상품금액</a>
							<span id="det_total"> 
								<c:if test="${buy == 'cart'}">
									${d_total}
									<input type="hidden" name="d_total" value="${d_total}">
								</c:if>
								<c:if test="${buy == 'buy'}">
									${dto.d_bsellvalue}
									<input type="hidden" name="d_total" value="${dto.d_bsellvalue}">									
								</c:if>				
							</span></p>
							<p><a>멤버십 할인금액</a><span>-0원</span></p>
							<p><a>쿠폰 할인금액</a><span>-0원</span></p>
							<p><a>문화상품권</a><span>-0원</span></p>
							<p><a>추가 결제 비용</a><span id="plu">+0원</span></p>
						</article>
		
					</section><%--deli_menu_gel 결제 창 위쪽 끝--%>
		
					<footer class="deli_menu_bang box_sizing">
						<header class="menu_bang_he">
							<a>결제수단 선택</a>
							<select class="box_sizing" id="gyul">
								<option value="cash" name="cash">현금결제</option>
							</select>
							<p>
								<a>
										위 상품의 판매조건을 명확히 확인
										하였으며, <br>구매 진행에 동의합니다.
										(전자상거래법 제8조 2항)<br><br>
										▶  주문하기를 눌러주세요.
								</a>
							</p>
						</header><%--deli_menu_gel 결제 창 아래쪽 01 끝--%>
		
						<footer class="menu_bang_foot">
							<input id="rider_jumoon" type="submit" value="주문하기">
							<%--<a id="rider_jumoon" href="/DoIt/d_cart/payshot.do?cols=d_sell&buy=cart">주문하기</a> --%>
						</footer><%--deli_menu_gel 결제 창 아래쪽 02 끝--%>
					</footer>
				</form>
		
				</article><%-- 주문결제 오른쪽 part 끝--%>
				
				
				
				
				
				
				
				
				
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
				</article><%-- 제목 박스 왼쪽 끝--%>
		
				<article class="order_ri fl_ri">
				<h3> 주문결제 header nav 아이콘</h3>
					<a><img src="/DoIt/images/del_order_icon1.png"/></a>
					<a><img src="/DoIt/images/del_order_icon2_2.png"/></a>
					<a><img src="/DoIt/images/del_order_icon3.png"/></a>
				</article><%-- 제목 박스 오른쪽 끝--%>
			</header><%-- 제목 박스 끝--%>
			
		
			<section class="deli_order_sec"> 
			<h2> 주문결제 section 전체</h2>
		
				<article class="del_order_le fl_le">
				<h3> 주문결제 왼쪽part</h3>
					<header class="order_le_hed">
						<table cellspacing="0" style="width:100%;" class="jjang_list">
							<h4>주문내용</h4>
							<colgroup>
							<col width="10%"><col width="15%"><col width="30%" ><col width="20%"><col width="20%"><col width="5%">
							</colgroup>
								<thead>
									<tr>
										<th scope="col">도서 코드</th>
										<th scope="col">도서 이미지</th>
										<th scope="col">도서명</th>
										<th scope="col">저자</th>
										<th scope="col">출판사</th>
										<th scope="col">삭제</th>
									</tr>
								</thead>
								<tbody id="results">
								
										<%-- 장바구니 내용 출력 --%>
										<c:forEach var="ca" items ="${ CartList }">
											<tr>
												<td>${ ca.getBr_code() }</td>
												<td><img src="/DoIt/save/${ ca.getBr_thumpic() }" /></td>
												<td><a href="/DoIt/d_rent/detail.do?br_no=${ ca.getBr_no() }">${ ca.getBr_name() }</a></td>
												<td>${ ca.getBr_writer() }</td>
												<td>${ ca.getBr_pub() }</td>
												<td><a href="/DoIt/d_cart/removeCart.do?br_code=${ ca.getBr_code() }&cols=d_rent" class="x_button"></a></td>
											</tr>
										</c:forEach>
	
								</tbody>
						</table>
		
						<a class="men_add fl_ri" href="/DoIt/d_cart/headCartList.do?cols=d_rent" >도서관서비스</a>
						<a class="men_add fl_ri"  href="/DoIt/d_cart/headCartList.do?cols=d_sell" >직접판매도서</a>
						
					</header><%-- 주문결제 목록 끝--%>
		
					<section class="order_le_sect">
						<p class="fl_le"><img src="/DoIt/images/nukkim.png" style="width:22px; height:22px;"/></p>
						<ul class="fl_le">
							<li>주문시 유의사항</li>
							<li>도서관 서비스는 수수료가 면제 됩니다.</li>
							<li>도서 주문은 <span>배송준비, 배송중</span> 경우만 취소가 가능합니다.</li>
							<li>도서 주문 완료시 반품, 취소처리가 불가능합니다.</li>
							<li>배송 기간은 날씨여부와 기타 사항에 따라 변경 될 수도 있습니다.</li>
							<li>[ 주문 판매 ] 배송에는 수수료가 붙을 수 있습니다.</li>
							<li>(보다 자세한 사항은 전화 <span>"1600-5252"</span> 로 문의 주시기 바랍니다)</li>
						</ul>
					</section><%-- 주문결제 주의 끝--%>
					
					
					
					<%-- 주문 배송정보 입력 --%>
					<footer class="order_le_foot">
						<p>배송정보</p>
						<p class="tab_con_ord">
							<a class="ord_juso">배송지 입력</a>
						</p>
						
						<table class="or_ju1" cellspacing="0">
						<colgroup>
						<col width="20%"><col width="80%">
						</colgroup>
							<tbody>
								<tr>
									<td>수령인 이름</td>
									<td>${ sessionScope.memName }<input type="hidden" name="order_name" value="${ sessionScope.memName }" /></td>
								</tr>
								<tr>
									<td>휴대전화</td>
									<td>${ sessionScope.memPhone }<input type="hidden" name="" value="${ sessionScope.memPhone }" /></td>
								</tr>
								<tr>
									<td>주소입력</td>
									<td><input type="text" value="${ sessionScope.memAddr }" name="jumoon_juso" class="input_033"/></td>
								</tr>
								<tr>
									<td></td>
									<td><input type="text"  name="" placeholder="추가 주소를 입력해 주세요." class="input_033"/></td>
								</tr>
								<tr>
									<td>배송 요청사항</td>
									<td><input type="text"  name="" class="input_033"/></td>
								</tr>
							</tbody>
						</table>
						
					</footer>
		
				</article><%-- 주문결제 왼쪽part--%>
				
				<jsp:include page="/d_cart/${ cols }.jsp"></jsp:include>

			</section><%-- 내용 전체 끝--%>
		
		
		
		</section><%-- 주문결제 전체 끝--%>


<jsp:include page="/footer.jsp"></jsp:include>











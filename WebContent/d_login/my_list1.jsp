<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
				<%-- 도서관 대여 목록 --%>
				
				<ul class="my_list_tab">
					<li class="click_tab1"><a href="/DoIt/d_login/myList.do?cols=dr_rent" class="ta_1a">나의 대여목록</a></li>
					<li class="click_tab2"><a href="#" class="ta_1a">나의 구매목록</a></li>
					<li class="click_tab2"><a href="/DoIt/d_login/myList.do?cols=dr_resell" class="ta_1a">관심상품 / 경매 내역</a></li>
				</ul>
				
					<%-- 제목 div --%>
					<div class="list_taa">
						<p class="fl_le">오늘 날짜 : ${ SimpleDate }</p>
					</div>
					
					<div class="list_cont_taa">
						<c:forEach var="c" items="${ getE }">
						<table class="my_list_conte" cellspacing="0">
							<colgroup>
								<col width="20%"><col width="50%"><col width="30%">
							</colgroup>
							<tbody>
								<tr>
									<td><a class="dasdff"><img src="/DoIt/save/${ c.getBr_thumpic() }" class="list_con_img"/></a></td>
									<td>
										<a href="/DoIt/d_rent/detail.do?br_no=${ c.getBr_no() }">
											<ul class="list_con1">
												<li>${ c.getBr_code() }</li>
												<li>${ c.getBr_name() }</li>
												<li>${ c.getBr_sname() }</li>
												<li>대기 순위 : <span style="color:red">${ c.getBr_waiter() }</span></li>
												<li>${ c.getBr_writer() } | ${ c.getBr_pub() }</li>
											</ul>
										</a>
									</td>
									<td>
										<ul class="list_con2">
											<li>최초 대여 날짜</li>
											<li>Date : ${ c.getBr_over_date() }</li>
											<li><a href="/DoIt/d_login/myDeli1.do?br_code=${ c.getBr_code() }" class="aqe_but">배송 조회</a></li>
											<li><a href="/DoIt/d_cart/overdue.do?br_code=${ c.getBr_code() }&cols=dr_rent" class="aqe_but">반납 신청</a></li>
										</ul>
									</td>	
								</tr>
							
							</tbody>
						</table>
						</c:forEach>
						
					</div><%-- 반복되는 내용 끝 --%>
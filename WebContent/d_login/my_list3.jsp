<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    			
    			<script>
    				$(document).ready(function(){
    					
    					$(".busw1").on("click",function(){
    						$(".ddas1").css("display","block");
    						$(".ddas2").css("display","none");
    						$(".ddas3").css("display","none");
    					})
    					
    					$(".busw2").on("click",function(){
    						$(".ddas1").css("display","none");
    						$(".ddas2").css("display","block");
    						$(".ddas3").css("display","none");
    					})
    					
    					$(".busw3").on("click",function(){
    						$(".ddas1").css("display","none");
    						$(".ddas2").css("display","none");
    						$(".ddas3").css("display","block");
    					})
    					
    				})
    			
    			</script>
				
				
				<ul class="my_list_tab">
					<li class="click_tab2"><a href="/DoIt/d_login/myList.do?cols=dr_rent" class="ta_1a">나의 대여목록</a></li>
					<li class="click_tab2"><a href="/DoIt/d_login/myList.do?cols=dr_pan" class="ta_1a">주문/배송/취소 조회</a></li>
					<li class="click_tab1"><a href="/DoIt/d_login/myList.do?cols=dr_resell" class="ta_1a">관심상품 / 경매 내역</a></li>
				</ul>
				
    			
				<%-- 직거래 게시판 --%>
				<ul class="sort_top">
					<li><a class="busw1">관심상품(직)</a></li>
					<li><a class="busw2">낙찰 리스트 [ 경매내역 ]</a></li>
					<li><a class="busw3">입찰 리스트 [ 경매내역 ]</a></li>
				</ul>
				
					<%-- 제목 div --%>
					
					
					<div class="list_cont_taa2">
						
						
						<%-- 관심상품리스트 --%>
						<div class="ddas1">
							<c:if test="${count == 0}">
								<table width="100%" cellspacing="0" class="">
									<tr>
										<td colspan="4">저장된 관심상품이없습니다. </td>
									</tr>
								</table>
							</c:if>
							
							<c:if test="${count > 0}">
							<c:forEach var="article" items="${articleList}">
							<div class="border_land"><%-- 외부선 --%>
								
								<%-- 파트 제목 --%>
								<div class="list_taa_2">
									<p class="fl_le">오늘 날짜 : <span class="bold">${ SimpleDate }</span> | </p>
									<p class="fl_le">등록일 : <span class="bold">${article.scrap_reg_date }</span> | </p>
									<p class="fl_le">할인율 : <span class="co_red bold">${article.rbook_price } 원</span></p>
								</div>
								
								<%-- 파트 내용 --%>
								<table cellspacing="0" class="ban_list">
									<colgroup>
										<col width="60%"><col width="20%"><col width="20%">
									</colgroup>
									<tbody>
										<tr>
											<td>
												<p class="bold">
													<c:out value="${number}"/>
									    			<c:set var="number" value="${number-1}"/>
													<a href="/DoIt/d_resell/reContent.do?rbook_no=${article.rbook_no }&pageNum=${currentPage}">. ${article.rbook_subject }</a>
												</p>
												<p class="font_s1">스크랩한 글번호 : ${article.rbook_no }</p>
											</td>
											<td class="text_center boder_le_ri">
												<p>1개</p>
												<p class="bold">${article.rbook_price2 - article.rbook_price}원 할인</p>
											</td>
											<td class="text_center">
												<p class="bold fl_le">
													<c:if test="${article.rbook_sell_check == 0}">
														<span>판매중</span>
													</c:if>
													<c:if test="${article.rbook_sell_check == 1}">
														<span>판매완료</span>
													</c:if>
												</p>
												<p class="fl_le del_but">
													<a href="/DoIt/d_resell/myScrapDelete.do?scrap_no=${article.scrap_no }">내용삭제</a>
												</p>
											</td>
										</tr>
									</tbody>
									
								</table>
								</div>
								</c:forEach>
							</c:if>
						</div>
						<%-- 관심상품 끝 --%>	
						
						
						<%-- 낙찰리스트 --%>
						<div class="ddas2">
							
							<%-- 게시글이 없을 경우 --%>
							<c:if test="${count1 == 0}">
								<table width="100%" cellspacing="0" class="rent_list">
									<tr>
										<td colspan="4">낙찰된 정보가 없습니다.</td>
									</tr>
								</table>
							</c:if>
							
							<%-- 게시글이 있을 경우 --%>
							<c:if test="${count1 > 0}">
							<c:forEach var="article1" items="${articleList1}">
							<div class="border_land"><%-- 외부선 --%>
								
								<%-- 파트 제목 --%>
								<div class="list_taa_2">
									<p class="fl_le">오늘 날짜 : <span class="bold">${ SimpleDate }</span> | </p>
									<p class="fl_le">당시 가격 : <span class="co_red bold">${article1.bid_price2 } 원</span></p>
								</div>
								
								<%-- 파트 내용 --%>
								<table cellspacing="0" class="ban_list">
									<colgroup>
										<col width="10%"><col width="40%"><col width="20%"><col width="30%">
									</colgroup>
									<tbody>
										<tr>
											<td>
												<p class="bold">
													<c:out value="${number1}"/>
							    					<c:set var="number" value="${number1-1}"/>
												</p>
											</td>
											<td>
												<p class="bold">${article1.bid_subject}</p>
												<p class="font_s1">${article1.bid_name }</p>
											</td>
											<td class="text_center">
												<p><img src="/DoIt/bid_pic/${article1.d_pic}" ></p>
											</td>
											<td>
												<p class="bold">아이디	:${article1.d_id}</p>
							  					<p>이름	:${article1.d_name}</p>
							  					<p class="co_red">번호	:${article1.d_phone }</p>
							  					<p>이메일	:${article1.d_mail}</p>
											</td>
										</tr>
									</tbody>
									
								</table>
								</div>
								</c:forEach>
										
					  		</c:if>
							
						</div>
						<%-- 낙찰리스트 끝 --%>
						
						
						<%-- 입찰리스트 끝 --%>
						<div class="ddas3">
							내용없음 삽입 요망
							
						</div>
						<%-- 입찰리스트 끝 --%>
						
						
					</div><%-- 반복되는 내용 끝 --%>
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
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
					<li><a class="busw3" >입찰 리스트 [ 경매내역 ]</a></li>
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
						
							<!-------------------- 게시판리스트 ------------------------------------ -->
							<%-- <p>입찰목록(갯수:${count2})</p> --%>
						
							<c:if test="${count2 == 0}">
								<table cellspacing="0">
									<tr>
										<td align="center">
											입찰기록이 없습니다.
										</td>
									</tr>
								</table>
							</c:if>
								
							<%-- 게시글이 있을 경우 --%>
							<c:if test="${count2 > 0}">
							<c:forEach var="article2" items="${articleList2}">
							<div class="border_land"><%-- 외부선 --%>
								
								<%-- 파트 제목 --%>
								<div class="list_taa_2">
									<p class="fl_le">오늘 날짜 : <span class="bold">${ SimpleDate }</span> | </p>
									<p class="fl_le">마지막 입찰자 : <span class="co_red">${article2.bid_last_id }</span>님</p>
								</div>
								
								
								<%-- 파트 내용 --%>
								<table cellspacing="0" class="ban_list">
									<colgroup>
										<col width="10%"><col width="40%"><col width="20%"><col width="30%">
									</colgroup>
									<tbody>
										<tr>
											<%-- 번호 --%>
											<td>
												<p class="bold">
													<c:out value="${number2}"/>
								    				<c:set var="number" value="${number2-1}"/>
												</p>
											</td>
											
											<%-- 제목 내용 --%>
											<td>
												<p class="bold">
													<a href="/DoIt/d_bid/bidContent.do?bid_no=${article2.bid_no}&bid_finish_date=${article2.bid_finish_date}&pageNum2=${currentPage2}">
									    				${article2.bid_subject}
									    			</a>
												</p>
												<p class="font_s1"> [ (책)${article2.bid_name} ]</p>
												<p class="font_s1">등록일 : ${article2.bid_reg_date} </p>
											</td>
											
											<%-- 이미지 --%>
											<td class="text_center">
												<p>
													<c:if test="${article2.bid_pic == null}">
							   							<img src="/DoIt/images/ma_img.png" />
									    			</c:if>
									    			<c:if test="${article2.bid_pic != null }">
							   							<a href="/DoIt/d_bid/bidContent.do?bid_no=${article2.bid_no }&pageNum2=${currentPage2}">
							   							<img src="/DoIt/bid_pic/${article2.bid_pic}" /></a>
									    			</c:if>
												</p>
											</td>
											
											<%--  --%>
											<td>
												<p class="bold">판매자 : ${article2.bid_id}</p>
							  					<p>시작가 : ${article2.bid_price1} 원</p>
							  					<p class="">현재진행가 : <span class="co_red">${article2.bid_price2}</span> 원</p>
							  					<p>
							  						<c:if test="${article2.bid_finish == 0}">
														<span>판매중</span>
													</c:if>
													<c:if test="${article2.bid_finish == 1}">
														<span>판매완료</span>
													</c:if>
							  					</p>
											</td>
										</tr>
									</tbody>
									
								</table>
								</div>
								</c:forEach>
										
					  		</c:if>
								
								    
								
						   		<form action="/DoIt/d_login/myList.do?cols=dr_resell" id="reList_search" class="search_bar">
						   			<p>
							   			<input type="text" name="search2" placeholder="책 제목을 검색하시오."/>
							   			<button type="submit" >검색</button>
						   			</p>
						   		</form>
						   		
						  		
							  	<c:if test="${count > 0}">
							  	<p class="num_tag"> 
								   <c:set var="pageCount2" value="${count2 / pageSize2 + ( count2 % pageSize2 == 0 ? 0 : 1)}"/>
								   <c:set var="pageBlock2" value="${10}"/>
								   <fmt:parseNumber var="result2" value="${currentPage2 / 10}" integerOnly="true" />
								   <c:set var="startPage2" value="${result2 * 10 + 1}" />
								   <c:set var="endPage2" value="${startPage2 + pageBlock2 -1}"/>
								   <c:if test="${endPage2 > pageCount2}">
								        <c:set var="endPage2" value="${pageCount2}"/>
								   </c:if> 
								   
								         
								   	<c:if test="${startPage2 > 10}"> 
								        <a href="/DoIt/d_bid/bidList.do?pageNum2=${startPage2 - 10 }">[ 이전 ]</a>
								   	</c:if>
								   
									<c:forEach var="i" begin="${startPage2}" end="${endPage2}">
										<a href="/DoIt/d_bid/bidList.do?pageNum2=${i}">[ ${i} ]</a>
									</c:forEach>
									
								   	<c:if test="${endPage < pageCount}">
								        <a href="/DoIt/d_bid/bidList.do?pageNum2=${startPage2 + 10}">[ 다음 ]</a>
								   	</c:if> 
								</p>
								</c:if>
						  		
							
						</div>
						<%-- 입찰리스트 끝 --%>
						
						
						
						
					</div><%-- 반복되는 내용 끝 --%>
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
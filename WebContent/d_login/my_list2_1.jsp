<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<%-- 주문 배송 리스트 ---------------------------------------%>
					
						<%-- 제목 파트 --%>
						<div class="list_taa_2">
							<p class="fl_le"><span class="bold">오늘 날짜 : ${ SimpleDate } </span> | </p>
							<p class="fl_le"><span class="co_red">${id} </span>님의 주문 배송 조회</p>
						</div>
						
						<!-- -------등록된 책이 없을때----------------------------- -->
						<c:if test="${count==0}">
							<table id="AdminList_Wrap">	
								<tr class="AdminList_tr">
									<td class="AdminList_num Admin_txtCen"><p class="Admin_RightLine">번호</p></td>
									<td class="AdminList_pic Admin_txtCen"><p class="Admin_RightLine">책 사진</p></td>
									<td class="AdminList_con Admin_txtCen">책 내용</td>
								</tr>
								<tr>
									<td class="Admin_txtCen" colspan="3"><br/>구매 or 취소 리스트가 없습니다.</td>
								</tr>
							</table>
						</c:if>
						
						<!-- -------등록된 책이 있을때----------------------------- -->
						<c:if test="${count > 0}">
							<table class="pan_list1" cellspacing="0">
								<colgroup>
									<col width="6%"><col width="14%"><col width="65%"><col width="15%">
								</colgroup>
								<tbody>
									<c:forEach var="article"  items="${articleList}">
									<tr>
										<%-- 번호 --%>
										<td>
											<span>
												<c:out value="${number}"/>
												<c:set var="number" value="${number - 1}"/>
											</span>
										</td>
										
										<%-- 이미지 --%>
										<td>
											<c:if test="${article.d_bpic == null}">
												<p><img src="/DoIt/images/ex_do.jpg" /></p>
											</c:if>
											<c:if test="${article.d_bpic != null}">
												<p><img src="/DoIt/d_bpic/${article.d_bpic}" /></p>
											</c:if>
										</td>
										
										<%-- 책내용 --%>
										<td class="text_left">
											<ul class="text_left list_con3">
												<li>책 이름 : ${article.d_bname} </li>
												<li>저자 : ${article.d_bauthor} |  출판사 : ${article.d_bpublisher} </li>
												<li class="font_s1">구매 날짜 : ${article.d_bdeldate} </li>
												<li><input type="button" value="상세보기" class="AdminList_btn" onclick="window.location='/DoIt/d_login/user_BuyBookList_detail.do?d_bno=${article.d_bno}&d_bcode=${article.d_bcode}'"/></li>
											</ul>
										</td>
										
										<%-- 취소 신청 --%>
										<td>
											<c:if test="${article.d_bdelibery == 20}">
												<a href="/DoIt/d_login/user_BuyBook_Cancel.do?d_bno=${article.d_bno}&d_bcode=${article.d_bcode}" class="aqe_but">취소신청</a>
											</c:if>
											<c:if test="${article.d_bdelibery == 21}">
												<p>배송준비중</p>
											</c:if>
											<c:if test="${article.d_bdelibery == 22}">
												<p>배송 중</p>
											</c:if>
											<c:if test="${article.d_bdelibery == 23}">
												<p>배송완료</p>
											</c:if>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							
						</c:if>
						
						<%-- 페이지 네이션 --%>
						<p class="num_tag">
							<c:if test="${count > 0}">
								<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
								<c:set var="pageBlock" value="${10}"/>
								<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
								<c:set var="startPage" value="${result * 10 + 1}" />
								<c:set var="endPage" value="${startPage + pageBlock-1}"/>
								<c:if test="${endPage > pageCount}">
									<c:set var="endPage" value="${pageCount}"/>
								</c:if> 
									
								<c:if test="${startPage > 10}">
								<a href="/DoIt/d_login/myList.do?cols=dr_pan&pageNum=${startPage - 10 }">[ 이전 ]</a>
								</c:if>
							
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<a href="/DoIt/d_login/myList.do?cols=dr_pan&pageNum=${i}">[ ${i} ]</a>
								</c:forEach>
							
								<c:if test="${endPage < pageCount}">
									<a href="/DoIt/d_login/myList.do?cols=dr_pan&pageNum=${startPage + 10}">[ 다음 ]</a>
								</c:if>
							</c:if>
						
						</p>
					
					<%-- 주문 배송 리스트 끝---------------------------------------%>
					
					
					
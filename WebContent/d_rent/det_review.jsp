<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

					
					
					<%-- 리뷰 글쓰기 버튼 및 제목 --%>
					<div class="title_but">
						<p class="fl_le le_ti">이 도서에 대한 리뷰</p>
						
						<c:if test="${ sessionScope.memId == null }">
							<p class="fl_ri ri_ti" onclick="board_se()">리뷰 쓰기</p>
						</c:if>
						<c:if test="${ sessionScope.memId != null }" >
							<p class="fl_ri ri_ti" onclick="review_go()">리뷰 쓰기</p>
						</c:if>
					</div>
					
					
					<form action="/DoIt/d_rent/replyPro.do" method="POST" onsubmit="#" >
						<%-- 글번호를 re_no라는 이름으로 전달 댓글에 들어가는 글번호 --%>
						<input type="hidden" name="br_no" value="${ rto.getBr_no() }" />
						<input type="hidden" name="re_writer" value="${ sessionScope.memId }" />
						<input type="hidden" name="re_pic" value="${ sessionScope.memPic }" />
						<table id="re_table" cellspacing="0">
							<colgroup>
								<col width="60%"><col width="20%"><col width="20%">
							</colgroup>
							<tbody>
								<tr>
									<td colspan="2"><input type="text" name="re_title" class="input_r" placeholder="제목을 입력하세요..."/></td>
									<td>작성 유의사항</td>
								</tr>
								<tr>
									<td colspan="2">
										<textarea placeholder="리뷰를 작성하세요..." name="re_cont" rows="5" cols="90" maxlength="120"></textarea>
									</td>
									<td>
										<p class="write_bu2"><button type="submit" class="">댓글등록</button></p>
									</td>
									
								</tr>
								<tr>
									<td><input type="checkbox" />트위터 <input type="checkbox" />페이스북 </td>
									<td>280byte (한글 140자 이내)</td>
									<td></td>
								</tr>
	
							</tbody>
							
						</table>
					</form>
					
					<%-- 리뷰 전체 리스트 --%>
					<table cellspacing="0" class="review_list">
					<colgroup>
						<col width="10%"><col width="85%"><col width="5%">
					</colgroup>
						<tbody>
							<c:forEach var="cc" items="${ re_List }">
							<tr>
								<td><img src="/DoIt/save/${ cc.b_pic }" /></td>
								<td>
									<ul class="review_cont">
										<li>${ cc.b_title }</li>
										<li>${ cc.b_cont }</li>
										<li><span>${ cc.b_id }</span> | ${ cc.b_date }</li>
									</ul>
								</td>
								<td>
									<c:if test="${ sessionScope.memId != null && sessionScope.memId == cc.b_id }">
										<a class="x_button" 
											onclick="window.location='/DoIt/d_rent/replyDel.do?br_no=${ rto.getBr_no() }&b_no=${ cc.b_no }&b_id=${ cc.b_id }'">
										</a>
									</c:if>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					
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
			                 <a href="detail.do?br_no=${rto.getBr_no() }&pageNum=${startPage - 10 }">[이전]</a>
			            </c:if>
			         
			            <c:forEach var="i" begin="${startPage}" end="${endPage}">
			                <a href="detail.do?br_no=${rto.getBr_no() }&pageNum=${i}">[ ${i} ]</a>
			            </c:forEach>
			         
			            <c:if test="${endPage < pageCount}">
			                 <a href="detail.do?br_no=${rto.getBr_no() }&pageNum=${startPage + 10}">[다음]</a>
			            </c:if>
         			</c:if>
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
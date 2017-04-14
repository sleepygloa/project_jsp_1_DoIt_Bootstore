<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css"  href="/DoIt/css/online.css">

<!-- header import -->
<jsp:include page="/header.jsp" />

<jsp:include page="/d_login/side_my.jsp"></jsp:include>

<!-- content -->
<article class="my_cont_wrap">
	<p class="my_title">
		판매신청 리스트
	</p>

	<p class="my_sub_title">
		회원이 책을 판매하기 위해 신청한 책들을 보기 위한 페이지 입니다.<br />
		<span>(관) 은 관리자가 설정하고 있는 상태입니다.</span>
	</p>	
	
	<p class="L_title">
		<a class="d-left"></a>
	</p>
	
	
	<!--------------------------------- 회원의 판매중인 List ------------------------------------->
	
	<div class="list_cont_taa2">
			
	<%-- 판매중인 상품이 없을경우 --%>
	<c:if test="${count == 0}">
		<table width="100%" cellspacing="0" class="pan_list1">
			<tr>
				<td colspan="4">판매중인 상품이없습니다. </td>
			</tr>
		</table>
	</c:if>
		
	<%--  --%>
							
	<c:if test="${count > 0}">
				
		<%-- 파트 제목 --%>
			<div class="list_taa">
				<span class="co_red">${ sessionScope.memId }</span> 님의 판매신청 내역입니다. 상태를 확인해주세요.
			</div>
			
			<c:forEach var="article" items="${articleList}">							
			<%-- 파트 내용 --%>
			<table cellspacing="0" class="pan_list1">
				<colgroup>
					<col width="6%"><col width="14%"><col width="65%"><col width="15%">
				</colgroup>
				<tbody>
					<tr>
						<td>
							<span>${article.d_sno}</span>
						</td>
						<%-- 사진 --%>
						<td>
							<input type="hidden" name="d_bno" value="${article.d_bno}">
							<input type="hidden" name="d_bcode" value="${article.d_bcode}">
								<c:if test="${article.d_bpic == null}">
									<p>사진 정보가 없습니다.</p>
								</c:if>
								<c:if test="${article.d_bpic != null}">
									<p><img src="/DoIt/d_bpic/${article.d_bpic}" /></p>
								</c:if>
						</td>
						
						<%-- 번호, 내용  --%>
						<td>
							<ul class="text_left list_con3">
								<li>책 이름 : ${article.d_bname}</li>
								<li>저자 : ${article.d_bauthor} | 출판사 : ${article.d_bpublisher}</li>
								<li>판매 일자 : ${article.d_sdateS}</li>
							</ul>
						</td>
						
						<%-- 날짜, 상태표시 --%>
						<td>
								<c:if test="${article.d_sfinish == 0}">
									<p style="margin-bottom:10px;">판매 신청중....</p>
									<p>잠시만 기다려주세요.....</p>
									<%--<input type="button" value="판매신청중" class="btn btn-default"/>--%>
								</c:if>
								<c:if test="${article.d_sfinish == 1}">
									<c:if test="${article.d_bdelibery == 10}">
										<p style="margin-bottom:10px;">판매신청 완료</p>
										<input type="button" value="배송 시작" 
										onclick="window.location='/DoIt/d_login/mySellList.do?d_bcode=${article.d_bcode}&delivery=2' "/>
										<%--<input type="hidden" name="d_bcode" value="${article.d_bcode}" />--%>
										<%--<input type="hidden" name="delivery" value="2" />--%>
									</c:if>							
									<c:if test="${article.d_bdelibery == 12}">
										<p style="margin-bottom:10px;">배송중</p>
										<p>회원 ---> 관리자</p>
										<%-- <input type="button" value="배송중"/>--%>
									</c:if>
									<c:if test="${article.d_bdelibery == 13}">
										<c:if test="${article.d_ldealresult == 0}">
											<p style="margin-bottom:10px;">(관)배송 완료</p>
											<p>(관)결제 대기중</p>
											<%-- <input type="button" value="배송 완료"/>--%>
											<%-- <input type="button" value="결제 대기중"/>--%>
										</c:if>
									</c:if>
								</c:if>	
								<c:if test="${article.d_sfinish == 2}">
	<%-- 									<c:if test="${article.d_ldealresult == 1}"> --%>
									<p>(관)배송 완료</p>
									<p>(관)결제 완료</p>
									<%--<input type="button" value="배송 완료"/>--%>		
									<%--<input type="button" value="결제 완료"/>--%>		
	<%-- 									</c:if>		 --%>							
								</c:if>
						</td>
										
					</tr>
				</tbody>
									
			</table>
			</c:forEach>
			
		</c:if>
		<%-- 관심상품 끝 --%>	
	
		<!-- 페이지 번호 ------------------------------------------------------------------------ -->	
		<p class="num_tag">
			<c:if test="${startPage>10}">
				<a href="/DoIt/d_login/mySellList.do?pageNum=${startPage-5}" >[ 이전 ]</a>
			</c:if>
			
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<a href="/DoIt/d_login/mySellList.do?pageNum=${i}">[ ${i} ]</a>
			</c:forEach>
			
			
			<c:if test="${endPage<pageCount}">
				<a href="/DoIt/d_login/mySellList.do?pageNum=${startPage+5}">[ 다음 ]</a>
			</c:if>
		</p>

	</div>
	


</article>




<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>




		
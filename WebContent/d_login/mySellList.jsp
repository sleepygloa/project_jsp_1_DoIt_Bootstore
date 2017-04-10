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
		번호는 신청번호코드를 나타냅니다.
	</p>	
	<p class="L_title">
		<a class="d-left"></a>
	</p>
<!--------------------------------- 회원의 판매중인 List ------------------------------------->
	<table class="info_ta2 d-center" cellspacing="0">
		<thead>
			<tr>
				<td>코드</td>
				<td>사진</td>
				<td>책 내용</td>
				<td>신청 시간</td>
				<td>현황</td>
			</tr>
		</thead>	
		<tbody>

			<!-- -------판매중인 책이 없을때----------------------------- -->
				<c:if test="${count==0}">
					<tr>
						<td colspan="5">판매 신청 중인 책 리스트가 없습니다.</td>
					</tr>
				</c:if>
			<!-- ------판매신청중이 책이 있을 때----------------------------- -->	
				<c:if test="${count > 0}">	
				<form action="/DoIt/d_login/mySellList.do" method="post">
				<c:forEach var="article"  items="${articleList}">
					<tr>
						<td>${article.d_sno}</td>
						<td>
			 				<c:if test="${article.d_bpic == null}">
								<p>사진 정보가 없습니다.</p>
							</c:if>
							<c:if test="${article.d_bpic != null}">
								<img src="/DoIt/d_bpic/${article.d_bpic}" height="80px;" />
							</c:if>
						</td>
						<td>
							<div class="small-font8 d-left">
								<input type="hidden" name="d_bno" value="${article.d_bno}">
				 				책 이름 &nbsp;&nbsp;&nbsp;
				 				: ${article.d_bname} <br/>
				 				저자  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				:${article.d_bauthor} <br/>
				 				출판사 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				:${article.d_bpublisher} <br/>
								신청 일시 : ${article.d_sdateS}
				 				<input type="hidden" name="d_bcode" value="${article.d_bcode}">
							 </div>
						</td>	
						<td><span class="small-font8">${article.d_sdateS}</span></td>
						<td> 
							<c:if test="${article.d_sfinish == 0}">
								<input type="button" value="판매신청중" class="btn btn-default"/>
							</c:if>
							<c:if test="${article.d_sfinish == 1}">
								<c:if test="${article.d_bdelibery == 0}">
									<input type="button" value="판매신청 완료" class="btn btn-default"/><br />
									<input type="submit" value="배송 시작" class="btn btn-default"/>
									<input type="hidden" name="d_bcode" value="${article.d_bcode}" />
									<input type="hidden" name="delivery" value="2" />
								</c:if>							
								<c:if test="${article.d_bdelibery == 12}">
									<input type="button" value="배송중" class="btn btn-default"/>
								</c:if>
								<c:if test="${article.d_bdelibery == 13}">
									<c:if test="${article.d_ldealresult == 0}">
										<input type="button" value="배송 완료" class="btn btn-default"/><br />
										<input type="button" value="결제 대기중" class="btn btn-default"/>
									</c:if>
								</c:if>
							</c:if>	
							<c:if test="${article.d_sfinish == 2}">
<%-- 									<c:if test="${article.d_ldealresult == 1}"> --%>
								<input type="button" value="배송 완료" class="btn btn-default"/><br />
								<input type="button" value="결제 완료" class="btn btn-default"/>
<%-- 									</c:if>		 --%>							
							</c:if>

						</td>									
					</tr>
				</c:forEach>
			 	</form>
				</c:if>

		</tbody>


	
			

		</table>			
			
			

	
<!-- 페이지 번호 ------------------------------------------------------------------------ -->	
<center>
	<c:if test="${startPage>10}">
		<a href="/DoIt/d_login/mySellingList.do?pageNum=${startPage-5}" >[이전]</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<a href="/DoIt/d_login/mySellingList.do?pageNum=${i}">[${i}]</a>
	</c:forEach>
	
	
	<c:if test="${endPage<pageCount}">
		<a href="/jsp/board/list.nhn?pageNum=${startPage+5}">[다음]</a>
	</c:if>
</center>



</article>
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>




		
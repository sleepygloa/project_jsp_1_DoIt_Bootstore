<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--------------- header include --------------%>
<jsp:include page="ma_header.jsp"></jsp:include>


<%-- 전체 내용 wrap --%>
<section class="mana_wrap_a">

	<%--------------- 사이드 메뉴 include width: height:1537px --------------%>
	<jsp:include page="d_mana_side.jsp"></jsp:include>

	
	<%-- 본문 내용 height:1530px; --%>
	<article class="dash_wrap">	
		
		<%-- 본문 내용 정렬 : width:100%;  --%>
		<article class="dash_wrap_cont">
			
			<%-- 공동 nav 불러오기 --%>
			<jsp:include page="/d_manage/cont_header.jsp"></jsp:include>
			
			<%-- 연체자 리스트 및 연체 처리 --%>
			<script type="text/javascript">		
					//도서 연체 처리 ajax
					function modiDLib() {//전체(1), 한개(0) 구분값
						    $.ajax({
						        type: "POST",
						        url: "/DoIt/d_manage/overDue12.do?gugu=1",
						        success: function(data) {
									alert("연체처리에 성공하였습니다.");
									//부분새로고침
									window.location.reload(true);
						        },
						        error: function(){
									// 실패 시 수행 내용
									alert("연체처리에 실패 하였습니다.");
						            return false;
						        }
						        
						    });
					}
					
					//개별 도서 연체 처리 ajax
					function modiDLib2(code) {//전체(1), 한개(0) 구분값
						alert("dsafdsf");
						    $.ajax({
						        type: "POST",
						        url: "/DoIt/d_manage/overDue12.do?gugu=0&br_code="+code,
						        success: function(data) {
									alert("개별 연체처리에 성공하였습니다.");
									//부분새로고침
									window.location.reload(true);
						        },
						        error: function(){
									// 실패 시 수행 내용
									alert("개별 연체처리에 실패 하였습니다.");
						            return false;
						        }
						        
						    });
					}
	
			</script>
			
			
			<%-- manage_ part3  --%>
			<%-- section 속에 아티클 단위로 입력해서 넣으시면 됩니다. 사이즈 : 자동 --%>
			<section class="warp_cond">
				
				<%-- 블랙리스트 내용 전체 끝 --%>
				<article class="art_warp1">

					<article class="black_Li1">
						<%-- 각 파트 제목 --%>
						<div style="overflow:hidden;">
							<p class="mana_Stitle">도서 연체자 리스트</p>
							<p class="butts" onclick="modiDLib()">전체 연체처리</p>
						</div>
						
						<%-- 연체 리스트 탭  --%>
						<ul class="sort_top">
							<li><a class="bl_cu">연체자 리스트</a></li>
							<li><a class="bl_cu2">연체 도서리스트</a></li>
						</ul>
						
						<%-- 연체자 리스트 --%>
						<table class="ta_listaaa ef1" cellspacing="0">
						<colgroup>
							<col width="6%"><col width="6%"><col width="24%">
							<col width="24%"><col width="12%">
						</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>회원번호</th>
									<th>장바구니</th>
									<th>대여목록</th>
									<th>생성일자</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${ blLib == null }">
									<tr>
										<td colspan="5">연체자가 업습니다.</td>
									</tr>
								</c:if>
								<c:if test="${ blLib != null }">
									<c:forEach var="c" items="${ blLib }">
									<tr>	
										<%-- 카트 번호 --%>
										<td>
											<a>${ c.getCa_no() }</a>
										</td>
									
										<%-- 회원 번호 --%>
										<td style="position:relative;">
											<a>${ c.getD_no() }</a>
							    		</td>
							    			
							    		<%-- 도서관 장바구니 목록 --%>
										<td>
											<a>${ c.getD_rent() }</a>
										</td>
											
										<%-- 도서관 대여 목록 --%>
										<td>
											<a>${ c.getDr_rent() }</a>
										</td>
											
										<%-- 도서관 장바구니 생성 일자 --%>
										<td>
											<a>${ c.getD_ov_date() }</a>
										</td>
	
									</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>  		
						
						<%-- 연체 도서 리스트 --%>
						<table class="ta_listaaa ef2" cellspacing="0">
						<colgroup>
							<col width="10%"><col width="20%"><col width="25%">
							<col width="10%"><col width="20%"><col width="15%">
						</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>고유코드</th>
									<th>대기자</th>
									<th>연체상태</th>
									<th>빌린날짜</th>
									<th>연체처리</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="c" items="${ blDos }">
								<tr>	
									<%-- 카트 번호 --%>
									<td>
										<a>${ c.getBr_no() }</a>
									</td>
								
									<%-- 회원 번호 --%>
									<td style="position:relative;">
										<a class="dasdf">${ c.getBr_code() }</a>
						    		</td>
						    			
						    		<%-- 도서관 장바구니 목록 --%>
									<td>
										<a>${ c.getBr_waiter() }</a>
									</td>
										
									<%-- 도서관 대여 목록 --%>
									<td>
										<c:if test="${ c.getBr_deli()==5 }">
											<a>연체 변경 완료(Yes)</a>
										</c:if>
										<c:if test="${ c.getBr_deli() !=5 }">
											<a>연체 변경 안됨(No)</a>
										</c:if>
									</td>
										
									<%-- 도서관 장바구니 생성 일자 --%>
									<td>
										<a>${ c.getBr_over_date() }</a>
									</td>
									
									<td>
										<a href="javascript:modiDLib2( '${ c.getBr_code() }' )" >연체처리</a>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>  		
						
						
					</article>
					<%-- 연체자 리스트 및 연체 처리 끝--%>
									
					<%-- 도서관 서비스 내용 출력 --%>
					<article class="black_Li2">
						<%-- 각 파트 제목 --%>
						<div class="Li2_dash">
							<p Style="color:#202126">연체 / 신고 블랙 리스트</p>
							<p style="font-weight:bold">연체자와 신고받은 판매자 리스트</p>
						</div>

						<%-- 도서관 서비스 Dash1 --%>
						<div class="Li2_dash">
							<p Style="color:red">${ blLib_si }</p>
							<p>Delinquent</p>
						</div>
						
						<%-- 도서관 서비스 Dash1 --%>
						<div class="Li2_dash">
							<p Style="color:#019a93">\ ${ dashM.getD_lib() }</p>
							<p>Total Fee</p>
						</div>
						
					</article>
					
					
<%------------------------------------------------------------------------------- 구분  --------------------------------------------------------------------%>
					<%-- 신고 리스트 --%>
					<article class="black_Li3">
						
						<jsp:include page="/d_admin/admin_ReReportList.jsp"></jsp:include>
						
					</article>
					
					<%-- 신고 리스트 끝 --%>
					
		
				</article>
				<%-- 블랙리스트 내용 전체 끝 --%>
			
			</section>
			<%-- section 속에 아티클 단위로 입력해서 넣으시면 됩니다. 사이즈 : 자동 끝--%>
			
			
			
			
			
		</article>
			
	</article>

	
</section>


<%--------------- footer include --------------%>
<jsp:include page="ma_footer.jsp"></jsp:include>








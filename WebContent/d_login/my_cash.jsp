<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<%--------------- header include --------------%>
	<jsp:include page="/header.jsp"></jsp:include>
	
	<%--------------- 사이드 메뉴 include --------------%>
	<jsp:include page="/d_login/side_my.jsp"></jsp:include>
	
	
	
		<%-- 본문시작 --%>
			<%-- 본문내용 --%>
			<article class="my_cont_wrap">
				<p class="my_title">
					마이 페이지
				</p>
				<p class="my_sub_title">
					마이페이지에서는 회원정보 조회, 수정, 탈퇴를 할 수 있으며 구매내역을 확인하실 수 있습니다.
					<span>회원정보 수정 : 회원정보에서 아이디는 수정 하실 수 없습니다.</span>
					<span>회원 탈퇴 : 회원탈퇴 이후에는 로그인 하실 수 없습니다.</span>
				</p>
				

				<%-- 회원정보 조회 테이블 상단 --%>
				<p class="L_title">
					<a>Å Cash 충전 및 내역</a>
				</p>
				<p class="my_sub_title" style="border:none; padding:0px; margin-top:10px;">
					<span>* DOIT의 모든 서비스(수수료,판매,구매,연체료..)는 캐쉬를 통하여 이루어집니다. 모자라지 않게 충전해주세요.</span>
				</p>

				
				<%-- 본문내용 --%>

				<%-- 현재 충전량 및 가지고 있는 Cash --%>
				<table class="cash_tab" cellspacing="0">
					<colgroup>
						<col width="25%"><col width="25%">
						<col width="25%"><col width="25%">
					</colgroup>
					<thead>
						<tr>
							<th>계좌번호</th>
							<th>사용가능 Å캐쉬</th>
							<th>보관중인 Å캐쉬</th>
							<th>총 보유 Å캐쉬</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${adto.d_acnum}</td>
							<td><span class="co_red">${adto.d_cash}</span> 원</td>
							<td>0 원</td>
							<td><span class="co_red">${adto.d_cash}</span> 원</td>
						</tr>	
					</tbody>
					
				</table>
				
				
				<%-- 캐쉬 충전 및 반환 --%>
				<p class="mid_title"><span>캐쉬충전 및 반환</span></p>
				
				<div class="">
					<form action="" method="POST">
						<table class="join_01_ta" cellspacing="0">
							<colgroup>
								<col width="">
							</colgroup>
							
							<tbody>
								<%-- 소개 --%>
								<tr>
									<td>
										<p>D - Cash란 ? </p>
									</td>
									<td>
										<p style="font-size:0.9em">DOIT BOOK의 모든 서비스를 이용하기 위해 필요한 온라인 머니를 말합니다.</p>
										<p class="sub_con">* 회원님의 d-cash는 차후 출금 가능합니다.</p>
									</td>
								</tr>
								
								<%-- 현재 잔액 --%>
								<tr>
									<td>
										<p>${ sessionScope.memName }님의 잔액</p>
									</td>
									<td>
										<p class="co_red">${adto.d_cash}<span class="sub_con">원 남았습니다.</span></p>
									</td>
								</tr>
								
								<%-- 충전할 금액 --%>
								<tr>
									<td>
										<p>전환하실 금액</p>
									</td>
									<td>
										<p>
											<input type="text" name ="d_acMyMoney" class="input_02" style="text-align:right; color:#3DB7CC; padding-right:5px;" placeholder="숫자만 입력해주세요."/>
											 <span class="sub_con">원</span>
										</p>
									</td>
								</tr>
								
								<%-- 전환할 종류 --%>
								<tr>
									<td>
										<p>충전, 반환</p>
									</td>
									<td>
										<p>
											<a><input type="radio" name ="d_acRequest" value="2" checked/><span>충전</span></a>
											<a><input type="radio" name ="d_acRequest" value="3"/><span>반환</span></a>
										</p>
									</td>
								</tr>
								
							</tbody>
						</table>
						
						<%-- 서브밋 버튼 --%>
						<p>
							<button type="submit" class="men_add" style=" margin-left:0px;">전환하기</button>
						</p>
					</form>
				</div>
				<%-- 캐쉬 충전 및 반환 끝--%>
				
				
				
				<p style="margin:50px 0px 20px 0px; font-size:0.9em; font-weight:bold">* 나의 Å Cash 사용 내역입니다.</p>
				
				<ul class="sort_top">
					<li><a href="/DoIt/d_login/myCash.do?inout=1">입금내역</a></li>
					<li><a href="/DoIt/d_login/myCash.do?inout=2">출금내역</a></li>
					<li><a href="/DoIt/d_login/myCash.do?gua=1">온라인판매</a></li>
					<li><a href="/DoIt/d_login/myCash.do?gua=0">도서관서비스</a></li>
					<li><a href="/DoIt/d_login/myCash.do?gua=2">도서직거래</a></li>
				</ul>
				
				<div Style="width:100%; height:600px; margin-bottom:150px; overflow:scroll;">
					<table cellspacing="0" class="cash_tab2">
						<colgroup>
							<col width="15%"><col width="8%"><col width="8%">
							<col width="12%"><col width="12%"><col width="10%">
							<col width="10%">
						</colgroup>
						<thead>
							<tr>
								<th>사용일자</th>
								<th>보낸사람</th>
								<th>받는사람</th>
								<th>사용서비스</th>
								<th>상세내역</th>
								<th>거래금액</th>
								<th>거래 후 잔액</th>
							</tr>
						</thead>
						<tbody>
						
						<%-- 내용이 없을경우 --%>
						<c:if test="${ accountList == null }">
							<tr>
								<td colspan="7">사용 내역이 없습니다...</td>
							</tr>
						</c:if>
						
						<%-- 내용이 있을경우 --%>
						<c:if test="${ accountList != null }">
							<c:forEach var="account"  items="${accountList}">
								<tr>
									<td>${account.d_ldateS }</td>
									<td>${account.d_lsender}</td>
									<td>${account.d_lreceiver}</td>
									<td>
										<c:if test="${account.d_ldivision == 0}">
											도서관서비스
										</c:if>
										<c:if test="${account.d_ldivision == 1}">
											온라인판매
										</c:if>
										<c:if test="${account.d_ldivision == 2}">
											도서직거래
										</c:if>
									</td>
									<td>
										<c:if test="${account.d_ldealtype == 1}"> 입금 </c:if><%-- 넣고 뺴고 --%>
										<c:if test="${account.d_ldealtype == 2}"> 출금 </c:if>
										<c:if test="${account.d_ldealtype == 3}"> 계좌이체(받음) </c:if> <%-- 거래할때 --%>
										<c:if test="${account.d_ldealtype == 4}"> 계좌이체(보냄) </c:if>
									</td>
									<td>${account.d_ldealmoney} 원</td>
									<td><span class="co_red">${account.d_ltomoney}</span> 원</td>
								</tr>	
							</c:forEach>
						</c:if>
							
						</tbody>
					</table>
				</div>
			</article>
	
			
		
	<%--------------- footer include --------------%>
	<jsp:include page="/footer.jsp"></jsp:include>
	
	
	
	
	
	
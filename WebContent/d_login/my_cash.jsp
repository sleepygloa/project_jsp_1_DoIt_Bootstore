<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    
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
							<td>4546844-5846845-6543432</td>
							<td>16,000 원</td>
							<td>0 원</td>
							<td>16,000 원</td>
						</tr>	
					</tbody>
					
				</table>
				
				<p style="margin:50px 0px 20px 0px; font-size:0.9em; font-weight:bold">* 나의 Å Cash 사용 내역입니다.</p>
				
				<div Style="width:100%; height:600px; margin-bottom:150px; overflow:scroll;">
					<table cellspacing="0" class="cash_tab2">
						<colgroup>
							<col width="15%"><col width="5%"><col width="30%">
							<col width="10%"><col width="10%">
						</colgroup>
						<thead>
							<tr>
								<th>사용일자</th>
								<th>구분</th>
								<th>상세내역(사용 서비스)</th>
								<th>거래금액</th>
								<th>거래 후 잔액</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>2017-04-10 20:20:20</td>
								<td><a>충전</a></td>
								<td>도서구매서비스</td>
								<td>35,000</td>
								<td>16,000</td>
							</tr>	
						</tbody>
					</table>
				</div>
			</article>
	
			
		
	<%--------------- footer include --------------%>
	<jsp:include page="/footer.jsp"></jsp:include>
	
	
	
	
	
	
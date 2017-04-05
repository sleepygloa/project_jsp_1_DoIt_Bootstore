<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
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
					<a>나의 대여 / 구매 목록</a>
				</p>
				<p class="my_sub_title" style="border:none; padding:0px; margin-top:10px;">
					<span>* 대여 / 구매 목록 중 원하시는 버튼을 눌러주세요.</span>
				</p>
				
				<ul class="my_list_tab">
					<li><a href="/DoIt/d_login/myList.do?cols=dr_rent">나의 대여목록</a></li>
					<li><a href="#">나의 구매목록</a></li>
					<li><a href="#">나의 입찰내용</a></li>
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
										<li><a href="#" class="aqe_but">반납 신청</a></li>
									</ul>
								</td>	
							</tr>
						
						</tbody>
					</table>
					</c:forEach>
					
				</div><%-- 반복되는 내용 끝 --%>
				
				
			</article>
	
			
		
	<%--------------- footer include --------------%>
	<jsp:include page="/footer.jsp"></jsp:include>
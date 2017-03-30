<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_login/side_my.jsp"></jsp:include>



	
	
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
					<a>필수 입력 정보</a>
				</p>
				
				<div style="width:100%; overflow:hidden; border-top:solid 2px #3DB7CC; margin-top:10px;">
					<p class="back_img">
							<img src="/jsp/login_board/images/thumb2.png" style="width:100%; height:120%;"/>
					</p>
						
					<table class="info_ta1" cellspacing="0">
						<tbody>
							<tr>
								<td>회원번호</td>
								<td>${ lto.getD_no() }</td>
								<td>회원등급</td>
								<td>${ lto.getD_nom_grade() }</td>
							</tr>
							<tr>
								<td>판매자 권한</td>
								<c:if test="${ lto.getD_mech_grade() == 0 }">
									<td>판매자가 아닙니다.</td>
								</c:if>
								<c:if test="${ lto.getD_mech_grade() == 1 }">
									<td>판매자 승인을 받았습니다.</td>
								</c:if>
								
								<td>관리자 등급</td>
								<c:if test="${ lto.getD_mech_grade() != 2 }">
									<td >관리자 권한이 없습니다.</td>
								</c:if>
								<c:if test="${ lto.getD_mech_grade() == 2 }">
									<td >관리자 권한이 있습니다.</td>
								</c:if>
							</tr>
							<tr>
								<td>가입일자</td>
								<td colspan="3">${ lto.getD_date() }</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				
				<%-- 회원정보 자동 입력 정보 출력 --%>
				<table cellspacing="0" class="info_ta2" style="margin:0px;">
					<tbody>
						<tr>
							<td>아이디</td>
							<td>${ lto.getD_id() }</td>
						</tr>
						<tr>
							<td>이름</td>
							<td>${ lto.getD_name() }</td>
						</tr>
						<tr>
							<td>전화번호</td>
							<td>${ lto.getD_phone() }</td>
						</tr>
						<tr>
							<td>회원구분</td>
							<c:if test="${ lto.getD_person() == 0 }">
								<td ><input type="radio" checked="checked">개인회원</td>
							</c:if>
							<c:if test="${ lto.getD_person() == 1 }">
								<td ><input type="radio" checked="checked">기업회원</td>
							</c:if>
						</tr>
						<tr>
							<td>주소</td>
							<td>${ lto.getD_addr() }</td>
						</tr>
					</tbody>
				</table>
				
				
				
				<%-- 회원정보 조회 테이블 중단 --%>
				<p class="L_title">
					<a>선택 입력 정보</a>
				</p>
				<table class="info_ta2" cellspacing="0">
					<tbody>
						<tr>
							<td>이메일</td>
							<td>${ lto.getD_mail() }</td>
						</tr>
						<tr>
							<td>생년월일</td>
							<td>${ lto.getD_birth() }</td>
						</tr>
						<tr>
							<td>성별</td>
							<td >${ lto.getD_gender() }</td>
						</tr>
					</tbody>
				</table>
				
				<p class="button_cell">
					<button type="button" class="re_button" onclick="go_back()">뒤로가기</button>
				</p>
	
	
			</article>





<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>















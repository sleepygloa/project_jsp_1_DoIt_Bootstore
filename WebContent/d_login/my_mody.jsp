<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
			
				<p class="up_title">
					<a>회원 정보 수정 입니다.</a>
					<a>* 회원 정보를 변경하시면 다시 로그인하셔야 합니다.</a>
				</p>
				
				<form action="/DoIt/d_login/updatePro.do" method="POST" enctype="multipart/form-data">
					<%-- 회원정보 수정 페이지 --%>
					<table class="join_01_ta up_ta" cellspacing="0">
						<tr>
							<td>썸네일 사진</td>
							<td>
								<p><input type="file" name="thum_pic"/>
								<p class="sub_con"> * 회원님의 사진을 썸네일로 저장합니다.</p>
							</td>
						</tr>
						<tr>
							<td>아이디</td>
							<td>${ lto.getD_id() }</td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password"  name="user_pw"  value="${ lto.getD_pass() }" class="input_01"></td>
						</tr>
						<tr>
							<td>이름</td>
							<td>${ lto.getD_name() }</td>
						</tr>
						<tr>
							<td>전화번호</td>
							<td>
								<input type="text" name="user_phone1"  maxlength="3" value="${ lto.getUser_phone1() }" class="input_02"/> - 
								<input type="text" name="user_phone2" value="${ lto.getUser_phone2() }" maxlength="4" class="input_02"/> -
								<input type="text" name="user_phone3" value="${ lto.getUser_phone3() }" maxlength="4"  class="input_02"/>
							</td>
						</tr>
						<tr>
							<td>생년 월일</td>
							<td>
								<input type="text" name="user_birth1" value="${ lto.getUser_birth1() }" maxlength="4" class="input_011" /> 년 
								<input type="text" name="user_birth2" value="${ lto.getUser_birth2() }" maxlength="4" class="input_011" /> 월
								<input type="text" name="user_birth3" value="${ lto.getUser_birth3() }" maxlength="4" class="input_011" /> 일 
							</td>
						</tr>
						<tr>
							<td>성별</td>
							<td>${ lto.getD_gender() }</td>
						</tr>
						<tr>
							<td>주소</td>
							<td><input type="text" name="addr" value="${ lto.getD_addr() }" class="input_03" /></td>
						</tr>
						<tr>
							<td>이메일</td>
							<td>
								<input type="text" value="${ lto.getUser_mail1() }" name="user_mail1" class="input_01" /> @ 
								<select name="user_mail2" class="input_04">
									<option value="naver.com" selected>naver.com</option>
									<option value="hanmail.net">hanmail.net</option>
									<option value="gmail.com">gmail.com</option>
								</select>
							 </td>
						</tr>
						<tr>
							<td>가입일자</td>
							<td>${ lto.getD_date() }</td>
						</tr>
						
					</table>
					
					<p class="button_cell">
						<button type="submit" class="sub_button">수정하기</button>
						<button type="reset" class="re_button">취소하기</button>	
					</p>
					
				</form>
				
				
		</article>





<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>







    

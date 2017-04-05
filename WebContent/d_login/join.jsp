<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>

    <script type="text/javascript" src="/DoIt/js/script.js"></script>
   


		<!-- header import -->
		<jsp:include page="/header.jsp" />
		
		
	
		<section class="join_wrap">
			
			<section class="join_sec">
			<h1>회원가입 전체 내용</h1>
				<form action="/DoIt/d_login/joinPro.do" method="POST"  id="join_form" name="join_form" 	onsubmit="return Join_check()" enctype="multipart/form-data">
				<h2>회원가입 폼</h2>
					<p class="La_title">회원가입</p>
					
					<p class="mid_title">
						<span>필수사항 입력</span>
						<span>필수 입력 사항이므로 반드시 입력해 주세요.</span>
					</p>
				
					<table width="100%;" cellspacing="0" class="join_01_ta">
					<h3>회원가입 테이블 01</h3>			
						<tbody>
						<h4>회원가입 Tbody</h4>
							<tr>
								<td><span class="co_red">*</span> 아이디</td>
								<td>
									<input type="text" name="d_id" id="jo_user_id" minlength="6" maxlength="14" class="input_01"/>
									<p class="sub_con"> * 6 ~ 14자리 사이의 영문, 숫자로 입력해주세요.</p>
									<input class="input_01" type="button" value="중복확인" name="confirm_id" OnClick="openConfirmid(this.form)" />
								</td>							
							</tr>
							<tr>
								<td><span class="co_red">*</span> 비밀번호</td>
								<td>
									<input type="password" name="d_pass" id="jo_user_pw" class="input_01"/>
									<p class="sub_con">  * 10~14자리의 영문 및 숫자로 조합해주세요. 연속된 문자/숫자가 3자리 이상 계속해 들어가는 비밀번호는 
										사용하실 수 없습니다. (예, 111, abc, 123등)</p>
								</td>	
							</tr>
							<tr>
								<td>비밀번호 확인</td>
								<td>
									<input type="password" name=d_pass2 id="jo_user_pw2" placeholder="비밀번호를 다시 입력하세요."  class="input_01"/>
									<p class="sub_con"> * 비밀번호 확인을 위해 비밀번호를 한번 더 입력해 주세요.</p>
								</td>	
							</tr>
							<tr>
								<td><span class="co_red">*</span> 이름</td>
								<td>
									<input type="text" name="d_name" id="jo_user_name"  class="input_01"/>
									<p class="sub_con"> * 본인의 이름을 입력해주세요. </p>
								</td>	
							</tr>
							<tr>
								<td><span class="co_red">*</span> 전화번호</td>
								<td>
									<input type="text" name="user_phone1" id="jo_user_phone1" value="010"maxlength="3"  class="input_02"/> -
									<input type="text" name="user_phone2" id="jo_user_phone2" maxlength="4" class="input_02"/> -
									<input type="text" name="user_phone3" id="jo_user_phone3" maxlength="4"  class="input_02"/>
									<p class="sub_con"> * "-" 를 제외하고 정확한 전화번호를 입력해주세요. </p>
								</td>	
							</tr>
							<tr>
								<td><span class="co_red">*</span> 주소</td>
								<td>
									<input type="text" name="d_addr" id="jo_user_addr" class="input_03"/>
									<p class="sub_con"> * 주소는 보안을 위해 "동/면" 까지만 입력해 주세요.</p>
								</td>
							</tr>
							<tr>
								<td><span class="co_red">*</span> 개인 / 기업 회원 구분</td>
								<td>
									<a>
										<input type="radio" name="d_person" value="0" checked="checked"> 개인회원
									</a>
									<a>
										<input type="radio" name="d_person" value="1"> 기업회원
									</a>
								</td>
							</tr>
							
						</tbody>
					</table><!-- 회원가입 form 입력 1 끝 -->
					
					<p class="mid_title">
						<span>추가사항 입력</span>
						<span>추가사항은 되도록이면 입력해주세요.</span>
					</p>
					
					<table width="100%;" cellspacing="0" class="join_01_ta">
					<h3>회원가입 테이블 02</h3>			
						<tbody>
						<h4>회원가입 Tbody</h4>
							<tr>
								<td>이메일</td>
								<td>
									<input type="text" name="user_mail1" id="jo_user_email1" class="input_04"/> @
									<select id="jo_user_email2" name="user_mail2" class="input_04">
										<option value="naver.com" selected>naver.com</option>
										<option value="hanmail.net">hanmail.net</option>
										<option value="gmail.com">gmail.com</option>
									</select>
									<p class="sub_con"> * 이메일은 3가지 중 한가지만 선택해주세요.</p>
								</td>
							</tr>
							
							<tr>
								<td>생년월일</td>
								<td>
									<select id="jo_user_birth1" name="user_birth1" class="input_02">
										<option>2015</option>
										<option>2014</option>
										<option>2013</option>
										<option>2012</option>
										<option>2011</option>
										<option>2010</option>
										<option>2009</option>
										<option>2008</option>
										<option>2007</option>
										<option>2006</option>
										<option>2005</option>
										<option>2004</option>
										<option>2003</option>
										<option>2002</option>
										<option>2001</option>
										<option>2000</option>
										<option>1999</option>
										<option>1998</option>
										<option>1997</option>
										<option>1996</option>
										<option>1995</option>
										<option>1994</option>
										<option selected>1993</option>
										<option>1992</option>
										<option>1991</option>
										<option>1990</option>
										<option>1989</option>
										<option>1988</option>
										<option>1987</option>
										<option>1986</option>
										<option>1985</option>
										<option>1984</option>
										<option>1983</option>
										<option>1982</option>
										<option>1981</option>
										<option>1980</option>
										<option>1979</option>
										<option>1978</option>
										
									</select>
									<select id="jo_user_birth2" name="user_birth2" class="input_02">
										<option>12</option>
										<option>11</option>
										<option>10</option>
										<option>09</option>
										<option>08</option>
										<option>07</option>
										<option>06</option>
										<option>05</option>
										<option>04</option>
										<option selected>03</option>
										<option>02</option>
										<option>01</option>
									</select>
									<select id="jo_user_birth3" name="user_birth3" class="input_02"> 
										<option>10</option>
										<option selected>09</option>
										<option>08</option>
										<option>07</option>
										<option>06</option>
										<option>05</option>
										<option>04</option>
										<option>03</option>
										<option>02</option>
										<option>01</option>
									</select>
								</td>	
							</tr>
							<tr>
								<td>성별</td>
								<td>
									남자 <input type="radio" value="man" name="d_gender" id="sex_type1" checked="checked"/>
									여자 <input type="radio" value="woman" name="d_gender" id="sex_type2"/>
								</td>							
							</tr>
							<tr>
								<td>회원 사진 등록</td>
								<td>
									<p><input type="file" name="d_pic"></p>
									<p class="sub_con">회원님의 사진을 등록해주세요...</p>
								</td>
							</tr>
							
						</tbody>
					
					</table><!-- 회원가입 form 입력 2 끝 -->
					
					<p class="button_cell">
						<input type="submit" class="sub_button" value="회원가입"/>
						<button type="reset" class="re_button">취소</button>
					</p>
					
					
				</form><!-- 회원 가입 form 끝 -->
			</section><!-- 회원가입 form section 끝 -->
			
		</section>
		
		<!-- footer import  -->
		<footer>
		
		</footer>
		











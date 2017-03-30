<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_login/sub_my.jsp"></jsp:include>



	
	
	<%-- 본문내용 --%>
	<article class="my_cont_wrap">
		<p class="my_title">
			마이페이지 서브
		</p>
		<p class="my_sub_title">
			마이페이지 서브에서는 로그인, 회원가입, 아이디 / 비밀번호 찾기를 할 수 있습니다.
			<span>로그인, 회원가입 : 해당페이지로 이동합니다.</span>
			<span>아이디 / 비밀번호 찾기 : 원하시는 내용을 입력하여 확인을 눌러주세요.</span>
		</p>

		<table cellspacing="0" class="search_wrap">
			<%-- 한줄 구분 --%>
			<tr>
				<%-- 아이디 찾기  --%>
				<td class="sear_t" style="width:45%">
					<div class="top_po">
						<p class="sear_title">아이디 찾기</p>
						<p class="sear_title2">가입시 입력한 이름, 메일주소를 정확하게 입력해주세요.</p>
					</div>
					
					<div class="bot_po">
						<form action="/DoIt/d_login/idSearch.do" method="POST">
							<table cellspacing="0" class="sear_id_ta">
								<tr>
									<td>이름</td>
									<td><input type="text" name="d_name" class="inp_name"></td>
								</tr>
								<tr>
									<td>이메일</td>
									<td>
										<input type="text" name="user_mail1" class="sear_mail1"/> @ 
										<select name="user_mail2" class="input_ses" >
											<option>naver.com</option>
											<option>hanmail.com</option>
											<option>gmail.com</option>
										</select>
									</td>
								</tr>
							</table>
							
							<button type="submit" class="sub_button po_bot" style="margin-top:75px;">확인</button>
						</form>
					</div>
				</td>
				
				
				
				<%-- 비밀번호 찾기 --%>
				<td class="sear_t" style="padding-left:7%; border-left:dashed 1px #eaeaea; width:55%;">
					<div class="top_po">
						<p class="sear_title">비밀번호 찾기</p>
						<p class="sear_title2">가입시 입력한 이름, 메일주소, 아이디를 정확하게 입력해주세요.</p>
					</div>
					
					<div class="bot_po">
						<form action="/DoIt/d_login/pwFindPro.do" onsubmit="return pw_check();" method="post" name="pwForm">
							<table cellspacing="0" class="sear_id_ta">
								<tr>
									<td>아이디</td>
									<td><input type="text" name="d_id" class="inp_name"/></td>
								</tr>
								<tr>
									<td>이름</td>
									<td><input type="text" name="d_name" class="inp_name" /></td>
								</tr>
								<tr>
									<td>이메일</td>
									<td>
										<input type="text" name="user_mail1" class="sear_mail1"/> @ 
										<select name="user_mail2" class="input_ses">
											<option>naver.com</option>
											<option>hanmail.com</option>
											<option>gmail.com</option>
										</select>
									</td>
								</tr>
							</table>
							
							<button type="submit" class="sub_button po_bot">확인</button>
						</form>
						</div>
				</td>
				
				
			</tr>
		</table>

		<c:if test="${c!=null}">
			<p>찾으시는 아이디(id)는 ${c.d_id} 입니다.</p>
			<input type="button" value="로그인화면으로" onclick="window.location='login.do'"/>
		</c:if>
		
		<c:if test="${c ==null}">
			<p>아이디를 찾을 수 없습니다.</p>
			<p>이름과 이메일 입력을 확인하세요.</p>
		</c:if>

	</article>
	





<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>

















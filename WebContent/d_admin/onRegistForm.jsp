<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--------------- header include --------------%>
<jsp:include page="/header.jsp" />

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_online/onBookSide.jsp" />

<section class="join_wrap">
	
<section class="join_sec">

	<form action="/DoIt/d_login/joinPro.do" method="POST"  id="join_form" name="join_form" 	onsubmit="return Join_check()" enctype="multipart/form-data">
		<p class="La_title">책 DB 등록</p>

		<%--><input type="d_bgrade" name="d_pass" id="jo_user_pw" class="input_01"/>--%>	
		<table width="100%" cellspacing="0" class="join_01_ta">
			<tbody>
				<tr>
					<td><span class="co_red">*</span> 책 이름</td>
					<td>
					</td>							
				</tr>
					<input type="hidden" name="d_bcode" value="책코드" />
				<tr>
					<td><span class="co_red">*</span>출판사</td>
					<td>
					
						
					</td>	
				</tr>
				<tr>
					<td><span class="co_red">*</span>저자</td>
					<td>
						<input type="d_bgrade" name="d_pass" id="jo_user_pw" class="input_01"/>
						
					</td>	
				</tr>
				<tr>
					<td><span class="co_red">*</span>장르</td>
					<td>
						<input type="d_bgrade" name="d_pass" id="jo_user_pw" class="input_01"/>
						
					</td>	
				</tr>
				<tr>
					<td><span class="co_red">*</span>종류</td>
					<td>
						<input type="d_bgrade" name="d_pass" id="jo_user_pw" class="input_01"/>
						
					</td>	
				</tr>
				<tr>
					<td><span class="co_red">*</span>국내외</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td><span class="co_red">*</span>출간 날짜</td>
					<td>
						
					</td>
				</tr>
				
				<tr>
					<td><span class="co_red">*</span>책 메인사진</td>
					<td>
						<input type="file" name="d_bpic" />
					</td>
				</tr>
	
		
			</tbody>
		</table><!-- 회원가입 form 입력 1 끝 -->

<!--  검수 정보 테이블 ------------------------------------------------------- -->		
		<p class="mid_title">
			<span>검수 정보</span>
		</p>
					
		<table width="100%;" cellspacing="0" class="join_01_ta">
			<tbody>
				<tr>
					<td>검수코드</td>
					<td>
						<span> </span>
					</td>
				</tr>
				<tr>
					<td><span class="co_red">*</span>검수 총점</td>
					<td>
						<input type="d_bgrade" name="d_pass" id="jo_user_pw" class="input_01"/>
						<p class="sub_con">검수 점수 - 0 : 최상, 1 ~ 4 : 상, 5 ~ 8 : 중, 9 ~ 12 : 매입불가</p>
					</td>	
				</tr>
					<tr>
					<td><span class="co_red">*</span>책 등급</td>
					<td>
						<input type="d_bgrade" name="d_pass" id="jo_user_pw" class="input_01"/>
						<p class="sub_con">책 등급은 0~3 // S:최상급, A:상급, B:중급, C:매입불가</p>
					</td>	
				</tr>
				<tr>
					<td><span class="co_red">*</span>정가</td>
					<td>
						<input type="text" name="d_bvalue" />
					</td>
					<p class="sub_con"> * 공백없이, (,)없이 입력</p>	
				</tr>
				<tr>
					<td>구매가</td>
					<td>
						<span> </span>
					</td>
				</tr>
				<tr>
					<td>판매가</td>
					<td>
						<span></span>
					</td>
				</tr>				
				

				<tr>
					<td>판매자 정보</td>
					<td>
						
					</td>							
				</tr>
				<tr>
					<td>검수일시</td>
					<td>
						
						<p class="sub_con"> *. 나타나는 시각은 등록이 성공했을 때와 약간의 차이가 있을 수 있습니다.</p>
					</td>
				</tr>
				
			</tbody>
		
		</table><!-- 회원가입 form 입력 2 끝 -->
		
		<p class="button_cell">
			<input type="submit" class="sub_button" value="책 DB 등록"/>
			<button type="reset" class="re_button">취소</button>
		</p>
		
		
	</form><!-- 회원 가입 form 끝 -->
</section><!-- 회원가입 form section 끝 -->
	
</section>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- rent_side include --------------%>
<jsp:include page="rent_side.jsp"></jsp:include>



	<%----------- 본문 시작 --------------------%>

		<%-- 관리자 도서글 작성 --%>
		<article class="my_cont_wrap">
		
			<%-- 글쓰기 제목 --%>
			<p class="my_title">
				DOIT 도서관 서비스
			</p>
			<p class="my_sub_title">
				Doit 도서관에서는 3가지 서비스를 제공하고 있습니다. [ 도서 대출, 도서 기부 신청, 반납 신청 ]
				<span>도서목록 : 리스트 스타일과 썸네일 스타일을 모두 제공하고 있으므로, 편하신 방법으로 보시기 바랍니다. </span>
			</p>
			
			
			<p class="write_tit">
				DoIt 도서 등록 Part 입니다.
				<a>도서등록을 위한 입력창으로 현재 접근자는 
				<span>
					<c:if test="${ sessionScope.memId != null }">
						<c:if test="${ gugu != 10 }"> 회원 </c:if>
						<c:if test="${ gugu == 10 }"> 관리자 </c:if>
					</c:if>
					<c:if test="${ sessionScope.memId == null }">
						비회원
					</c:if>
				</span> 
				입니다.</a>
			</p>
			
			<form action="/DoIt/d_rent/b_writePro.do"  method="POST" enctype="multipart/form-data">
			<input type="hidden" name="br_code" value="${ br_code }" />
			<c:if test="${ gugu != 10 && sessionScope.memId != null }">
				<input type="hidden" name="br_don" value="${ sessionScope.memNo }" alt="기부자 번호"/>
			</c:if>
			<c:if test="${ gugu == 10 }">
				<input type="radio" name="br_don" value="${ sessionScope.memNo }" alt="기부자 등록" checked="checked"/>기부자로 등록
				<input type="radio" name="br_don" value="0" alt="관리자 등록" />관리자로 등록
			</c:if>
				<table width="100%;" cellspacing="0" class="join_01_ta">
					<h3>회원가입 테이블 01</h3>			
						<tbody>
						<h4>회원가입 Tbody</h4>
							<tr>
								<td>도서 고유코드 [ 자동 ]</td>
								<td>
									${ br_code } 
									<p class="sub_con">자동생성된 코드</p>
								</td>
							</tr>
							<tr>
								<td> 도서 이미지</td>
								<td>
									<input type="file" name="save"  />
									<p class="sub_con"> * 도서의 이미지를 넣어주세요..</p>
								</td>							
							</tr>
							<tr>
								<td><span class="co_red">*</span> 도서 이름</td>
								<td>
									<input type="text" name="br_name" id="jo_user_pw" maxlength="25"  class="input_01"/>
									<p class="sub_con">  * 공백을 포함하지 않은 도서의 정확한 이름을 넣어주세요.</p>
								</td>	
							</tr>
							<tr>
								<td><span class="co_red">*</span> 출판사</td>
								<td>
									<input type="text" name="br_pub" id="jo_user_name"  class="input_01"/>  출판사
									<p class="sub_con"> * 도서를 출판한 출판사를 입력해 주세요.</p>
								</td>	
							</tr>
							<tr>
								<td><span class="co_red">*</span> 도서 저자</td>
								<td>
									<input type="text" name="br_writer" id="jo_user_name"  class="input_01"/>
									<p class="sub_con"> * 도서를 지은 지은이(저자)의 이름을 입력해주세요. </p>
								</td>	
							</tr>
							<tr>
								<td> 도서 소제목</td>
								<td>
									<input type="text" name="br_sname" id="jo_user_addr" class="input_03"/>
									<p class="sub_con"> * 도서의 소제목 혹은 짧은 소개를 입력하세요.</p>
								</td>
							</tr>
							<tr>
								<td> 도서 내용글</td>
								<td>
									<input type="text" name="br_cont" id="jo_user_addr" class="input_03" />
									<p class="sub_con"> * 도서의 내용글을 입력해주세요.</p>
								</td>
							</tr>
							<tr>
								<td><span class="co_red">*</span> 도서 장르 선택</td>
								<td>
									<select id="jo_user_email2" name="d_bno" class="input_04">
										<option value="1" >소설 / 시 / 에세이</option>
										<option value="2" >참고 / 전문서적</option>
										<option value="3" >어린이 서적</option>
										<option value="4" >인문학 서적</option>
										<option value="5" >과학전문 서적</option>
										<option value="6" selected>기타 서적</option>
									</select>
									<p class="sub_con"> * 도서의 장르를 선택하세요.</p>
								</td>
							</tr>
							<tr>
								<td><span class="co_red">*</span> 도서 관리자 번호</td>
								<td>
									<input type="radio" name="br_admin" value="0" checked="checked"> 총 관리자
									<c:if test="${ gugu == 10 }">
										<input type="radio" name="br_admin" value="${ sessionScope.memNo }" > 로그인 관리자 
										<p class="sub_con"> * 총관리자 : 최고 관리자(0번 저장) / 로그인 관리자 : 로그인된 회원번호 저장</p>
									</c:if>
									<c:if test="${ gugu != 10 }">
										<p class="sub_con"> * 총관리자 : 최고 관리자(0번 저장)</p>
									</c:if>
								</td>
							</tr>
							<tr>
								<td> 도서 대기 상태</td>
								<c:if test="${ gugu == 10 }">
									<td>
										<input type="radio" name="br_wait" value="1" checked="checked"> 대기하지 않음 
										<p class="sub_con"> * 관리자는 검수 후 등록하기 때문에 따로 대기하지 않음. -> 자동 [ 1 ]등록</p>
									</td>
								</c:if>
								<c:if test="${ gugu!=10 }">
									<td>
										<input type="radio" name="br_wait" value="0" checked="checked"> 등록을 대기합니다.
										<p class="sub_con"> * 일반기부자는 등록순서를 따라야 하므로 대기상태로 기부완료가 됩니다.. -> 자동 [ 0 ] 등록</p>
									</td>
								</c:if>
							</tr>
							
						</tbody>
					</table><!-- 회원가입 form 입력 1 끝 -->
					
					<p class="button_cell">
						<c:if test="${ sessionScope.memId != null }">
							<input type="submit" class="sub_button" value="도서등록"/>
						</c:if>
						<button type="reset" class="re_button" onclick="history.go(-1)">취소</button>
					</p>
			</form>
			
			
			
		</article>
		
		
		
		
		
		
		
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>	
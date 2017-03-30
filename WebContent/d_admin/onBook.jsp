<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--------------- header include --------------%>
<jsp:include page="/header.jsp" />

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_admin/adminSide.jsp" />

<article class="my_cont_wrap">
	<section class="join_wrap">
		
	<section class="join_sec">
	
		<form action="/DoIt/d_admin/onBookPro.do" method="POST"  id="join_form" name="join_form" onsubmit="" enctype="multipart/form-data">
			<p class="La_title">책 DB 등록</p>
	
			<%--><input type="d_bgrade" name="d_pass" id="jo_user_pw" class="input_01"/>--%>	
			<table width="100%" cellspacing="0" class="join_01_ta">
				<tbody>
					<tr>
						<td><span class="co_red">*</span> 책 코드</td>
						<c:if test="${dto.d_bcode != null}">
							<td><span>${dto.d_bcode}</span></td>
							<input type="hidden" name="d_bcode" value="${dto.d_bcode}"/>
						</c:if>							
					</tr>
					
					<tr>
						<td><span class="co_red">*</span> 책 이름</td>
						<c:if test="${dto.d_bname != null}">
							<td><span>${dto.d_bname}</span></td>
							<input type="hidden" name="d_bname" value="${dto.d_bname}"/>
						</c:if>
						<c:if test="${dto.d_bname == null}">
							<td><input type="text" name="d_bname" id="jo_user_pw" class="input_01"/></td>
						</c:if>								
					</tr>
					<tr>
						<td><span class="co_red">*</span> 출판사</td>
						<c:if test="${dto.d_bpublisher != null}">
							<td><span>${dto.d_bpublisher}</span></td>
							<input type="hidden" name="d_bpublisher" value="${dto.d_bpublisher}"/>
						</c:if>
						<c:if test="${dto.d_bpublisher == null}">
							<td><input type="text" name="d_bpublisher" id="jo_user_pw" class="input_01"/></td>
						</c:if>								
					</tr>
					<tr>
						<td><span class="co_red">*</span> 저자</td>
						<c:if test="${dto.d_bauthor != null}">
							<td><span>${dto.d_bauthor}</span></td>
							<input type="hidden" name="d_bauthor" value="${dto.d_bauthor}"/>
						</c:if>
						<c:if test="${dto.d_bauthor == null}">
							<td><input type="text" name="d_bauthor" id="jo_user_pw" class="input_01"/></td>
						</c:if>								
					</tr>
					<tr>
						<td><span class="co_red">*</span> 장르</td>
						<c:if test="${dto.d_bgenre != null}">
							<td><span>${dto.d_bgenre}</span></td>
							<input type="hidden" name="d_bgenre" value="${dto.d_bgenre}"/>
						</c:if>
						<c:if test="${dto.d_bgenre == null}">
							<td>
								<select name="d_bgenre">
									<option value="어린이 서적" checked>어린이 서적</option>
									<option value="참고 / 전문서적">참고 / 전문서적</option>
									<option value="소설 / 시 / 에세이">소설 / 시 / 에세이</option>
									<option value="인문학 서적">인문학 서적</option>
									<option value="과학 전문서적">과학 전문서적</option>
									<option value="기타 서적">기타 서적</option>
								</select>
							</td>
						</c:if>								
					</tr>
					<tr>
						<td><span class="co_red">*</span> 종류</td>
						<c:if test="${dto.d_bgenre2 != null}">
							<td><span>${dto.d_bgenre2}</span></td>
							<input type="hidden" name="d_bgenre2" value="${dto.d_bgenre2}"/>
						</c:if>
						<c:if test="${dto.d_bgenre2 == null}">
							<td>
								<select name="d_bgenre2">
									<option value="아트지 " checked>아트지</option>
									<option value="레자크지 ">레자크지</option>
									<option value="스노우지">스노우지</option>
									<option value="모조지">모조지</option>
								</select>
							</td>
						</c:if>								
					</tr>
					<tr>
						<td><span class="co_red">*</span> 국내외</td>
						<c:if test="${dto.d_blocation != null}">
							<td><span>${dto.d_blocation}</span></td>
							<input type="hidden" name="d_blocation" value="${dto.d_blocation}"/>
						</c:if>
						<c:if test="${dto.d_blocation == null}">
							<td>
								<select name="d_blocation">
									<option value="국내">국내</option>
									<option value="국외">국외</option>
								</select>
							</td>
						</c:if>								
					</tr>
					<tr>
						<td><span class="co_red">*</span> 출간 날짜</td>
						<c:if test="${dto.d_bregistdate != null}">
							<td><span>${dto.d_bregistdate}</span></td>
							<input type="hidden" name="d_bregistdate" value="${dto.d_bregistdate}"/>
						</c:if>
						<c:if test="${dto.d_bregistdate == null}">
							<td><input type="text" name="d_bregistdate" id="jo_user_pw" class="input_01"/></td>
						</c:if>								
					</tr>
					<tr>
						<td><span class="co_red">*</span>책 메인사진</td>
						<c:if test="${dto.d_bpic != null}">
							<td><span><img src="/DoIt/d_bpic/${dto.d_bpic}" /></span></td>
							<input type="hidden" name="d_bpic" value="${dto.d_bpic}"/>
						</c:if>
						<c:if test="${dto.d_bpic == null}">
							<td>
							사진이 들어가지 않아 막아놈.
							
							
							<!-- <input type="file" name="d_bpic" class="input_filebox"> --></td>
						</c:if>
					</tr>
					
					<tr>
						<td><span class="co_red">*</span>책 목차</td>
						<td>
							<c:if test="${obiDto == null}">						
								<textarea name="d_norder" id="jo_user_pw" class="input_01" style="width:500px; height:100px;"></textarea>
							</c:if>
							<c:if test="${obiDto != null}">
								<input type="hidden" name="d_norder"  />
								<small>${obiDto.d_norder}</small>
							</c:if>
						</td>
					</tr>
					
					<tr>
						<td><span class="co_red">*</span>책 소개글</td>
						<td>
							<c:if test="${obiDto == null}">
								<textarea name="d_nintro" id="jo_user_pw" class="input_01" style="width:500px; height:100px;"></textarea>
							</c:if>
							<c:if test="${obiDto != null}">
								<input type="hidden" name="d_nintro"  />
								<small>${obiDto.d_nintro}</small>
							</c:if>							
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
						<td><span class="co_red">*</span> 검수코드</td>
						<c:if test="${dto.d_icode != null}">
							<td><span>${dto.d_icode}</span></td>
							<input type="hidden" name=d_icode value="${dto.d_icode}"/>
						</c:if>							
					</tr>	
					<tr>
						<td><span class="co_red">*</span>검수 총점</td>
						<td>
							<p class="sub_con">검수 점수 - 0 : 최상, 1 ~ 4 : 상, 5 ~ 8 : 중, 9 ~ 12 : 매입불가</p>
							<p><span >${dto.d_itotal}</span> 점</p>
							<input type="hidden" name="d_itotal" value="${dto.d_itotal}"/>
						</td>
					</tr>
					<tr>
						<td><span class="co_red">*</span> 책 등급</td>
						<c:if test="${d_bgrade == null}">
							<td>
							<p class="sub_con">책 등급 -  S : 최상 , A : 상 , B : 중 , C : 매입불가</p>
							<input type="text" name="d_bgrade" id="jo_user_pw" class="input_01"/>
							</td>
						</c:if>	
						<c:if test="${d_bgrade != null}"><!-- 테스트용 배포시 삭제 -->
							<td>
							<p class="sub_con">책 등급 -  S : 최상 , A : 상 , B : 중 , C : 매입불가</p>
							<span>${d_bgrade}</span>
							<input type="hidden" name="d_bgrade" id="jo_user_pw" class="input_01" value="${d_bgrade}"/>
							</td>
						</c:if>
<%-- 						<c:if test="${dto.d_bgrade == null}">
							<td>
							<p class="sub_con">책 등급 -  S : 최상 , A : 상 , B : 중 , C : 매입불가</p>
							<input type="text" name="d_bgrade" id="jo_user_pw" class="input_01"/>
							</td>
						</c:if>	
						<c:if test="${dto.d_bgrade != null}"><!-- 테스트용 배포시 삭제 -->
							<td>
							<p class="sub_con">책 등급 -  S : 최상 , A : 상 , B : 중 , C : 매입불가</p>
							<span>${d_bgrade}</span>
							<input type="hidden" name="d_bgrade" id="jo_user_pw" class="input_01" value="${d_bgrade}"/>
							</td>
						</c:if>	  원본임. 테스트용을 사용하고 배포시 테스트용을 삭제. 테스트용은 등급, 판매가, 구매가 계산식으로 지정--%>																				
					</tr>
					<tr>
						<td><span class="co_red">*</span> 정가</td>
						<c:if test="${dto.d_bvalue != null}">
							<td><span>${dto.d_bvalue}</span></td>
							<input type="hidden" name="d_bvalue" value="${dto.d_bvalue}"/>
						</c:if>
						<c:if test="${dto.d_bvalue == null}">
							<td><input type="text" name="d_bvalue" id="jo_user_pw" class="input_01"/></td>
							
						</c:if>								
					</tr>
					<tr>
						<td><span class="co_red">*</span> 구매가</td>
							<c:if test="${d_bpurchasevalue == 0}">
								<td>
									<input type="text" name="d_bpurchasevalue" id="jo_user_pw" class="input_01"/> 원
								</td>
							</c:if>		
							<c:if test="${d_bpurchasevalue == 1}"><!-- 검수결과과 매입불가일때, 가격정보 오류 발생 방지 및 표시 -->
								<td>
									매입불가<input type="hidden" name="d_bpurchasevalue" id="jo_user_pw" class="input_01" value="1"/>
								</td>
							</c:if>		
							<c:if test="${d_bpurchasevalue != 0}"><!-- 테스트용 배포시 삭제 -->
								<td>
									${d_bpurchasevalue}
									<input type="hidden" name="d_bpurchasevalue" id="jo_user_pw" class="input_01" value="${d_bpurchasevalue}"/> 원
								</td>
							</c:if>	
<%-- 							<c:if test="${dto.d_bpurchasevalue == 0}">
								<td>
									<input type="text" name="d_bpurchasevalue" id="jo_user_pw" class="input_01"/> 원
								</td>
							</c:if>		
							<c:if test="${dto.d_bpurchasevalue == 1}"><!-- 검수결과과 매입불가일때, 가격정보 오류 발생 방지 및 표시 -->
								<td>
									매입불가<input type="hidden" name="d_bpurchasevalue" id="jo_user_pw" class="input_01" value="1"/>
								</td>
							</c:if>		
							<c:if test="${dto.d_bpurchasevalue != 0}"><!-- 테스트용 배포시 삭제 -->
								<td>
									${d_bpurchasevalue}
									<input type="hidden" name="d_bpurchasevalue" id="jo_user_pw" class="input_01" value="${d_bpurchasevalue}"/> 원
								</td>
							</c:if>	원본임. 테스트용을 사용하고 배포시 테스트용을 삭제. 테스트용은 등급, 판매가, 구매가 계산식으로 지정-	 --%>											
					</tr>
					<tr>
						<td><span class="co_red">*</span> 판매가</td>
							<c:if test="${d_bsellvalue == 0}">
								<td>
									<input type="text" name="d_bsellvalue" id="jo_user_pw" class="input_01"/> 원
								</td>
							</c:if>	
							<c:if test="${d_bsellvalue == 1}"><!-- 검수결과과 매입불가일때, 가격정보 오류 발생 방지 및 표시 -->
								<td>
									매입불가<input type="hidden" name="d_bpurchasevalue" id="jo_user_pw" class="input_01" value="1"/>
								</td>
							</c:if>								
							<c:if test="${d_bsellvalue != 0}"><!-- 테스트용 배포시 삭제 -->
								<td>
									${d_bsellvalue}
									<input type="hidden" name="d_bsellvalue" id="jo_user_pw" class="input_01" value="${d_bsellvalue}"/> 원
								</td>
							</c:if>
<%-- 							<c:if test="${dto.d_bsellvalue == 0}">
								<td>
									<input type="text" name="d_bsellvalue" id="jo_user_pw" class="input_01"/> 원
								</td>
							</c:if>	
							<c:if test="${dto.d_bsellvalue == 1}"><!-- 검수결과과 매입불가일때, 가격정보 오류 발생 방지 및 표시 -->
								<td>
									매입불가<input type="hidden" name="d_bpurchasevalue" id="jo_user_pw" class="input_01" value="1"/>
								</td>
							</c:if>								
							<c:if test="${dto.d_bsellvalue != 0}"><!-- 테스트용 배포시 삭제 -->
								<td>
									${d_bsellvalue}
									<input type="hidden" name="d_bsellvalue" id="jo_user_pw" class="input_01" value="${d_bsellvalue}"/> 원
								</td>
							</c:if>	원본임. 테스트용을 사용하고 배포시 테스트용을 삭제. 테스트용은 등급, 판매가, 구매가 계산식으로 지정-	 --%>																					
					</tr>	
					<tr>
						<td><span class="co_red">*</span> 판매자 아이디</td>
						<c:if test="${dto.d_id != null}">
							<td><span>${dto.d_id}</span></td>
							<input type="hidden" name="d_id" value="${dto.d_id}"/>
						</c:if>							
					</tr>	
					<tr>
						<td><span class="co_red">*</span> 검수일시</td>
						<c:if test="${dto.d_idate != null}">
							<td><span>${dto.d_idate}</span></td>
							<input type="hidden" name="d_idate" value="${dto.d_idate}"/>
						</c:if>							
					</tr>		
	
					
				</tbody>
			
			</table><!-- 회원가입 form 입력 2 끝 -->
			
			<p class="button_cell">
				<input type="submit" class="sub_button" value="책 DB 등록"/>
				<button type="reset" class="re_button">취소</button>
			</p>
			
			
		</form><!-- 책 등록 form 끝 -->
	</section><!-- 책 등록 form section 끝 -->
		
	</section>
</article>
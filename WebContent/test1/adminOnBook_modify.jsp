<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css"  href="/DoIt/css/online_admin.css">    
    
<%--------------- header include --------------%>
<jsp:include page="/header.jsp" />

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_admin/adminSide.jsp" />
<article class="my_cont_wrap">
	<section class="join_wrap">
		
	<section class="join_sec">
	
		<form action="/DoIt/d_admin/adminOnBook_modifyPro.do" method="POST"  id="join_form" name="join_form" onsubmit="" enctype="multipart/form-data">
			<p class="La_title">책 등록</p>
	
			<%--><input type="d_bgrade" name="d_pass" id="jo_user_pw" class="input_01"/>--%>	
			<table width="100%" cellspacing="0" class="join_01_ta">
				<tbody>
					<tr>
						<td><span class="co_red">*</span> 책 코드</td>
							<td>
								<span>${dto.d_bcode}</span>
								<input type="hidden" name="d_bcode" value="${dto.d_bcode}"/>
							</td>
					</tr>
					
					<tr>
						<td><span class="co_red">*</span> 책 이름</td>
							<td><input type="text" name="d_bname"  value="${dto.d_bname}" id="jo_user_pw" class="input_01"/></td>					
					</tr>
					<tr>
						<td><span class="co_red">*</span> 출판사</td>
						<td><input type="text" name="d_bpublisher" value="${dto.d_bpublisher}" id="jo_user_pw" class="input_01"/></td>
	
								
					</tr>
					<tr>
						<td><span class="co_red">*</span> 저자</td>
							<td><input type="text" name="d_bauthor" value="${dto.d_bauthor}" id="jo_user_pw" class="input_01"/></td>					
					</tr>
					<tr>
						<td><span class="co_red">*</span> 장르</td>

							<td>						
								<select name="d_bgenre" class="input_04">
									<option value="어린이 서적" <c:if test="${dto.d_bgenre.equals('어린이 서적')}"> selected </c:if>  >어린이 서적</option>
									<option value="참고 / 전문서적" <c:if test="${dto.d_bgenre.equals('참고 / 전문서적')}">selected</c:if>>참고 / 전문서적</option>
									<option value="소설 / 시 / 에세이" <c:if test="${dto.d_bgenre.equals('소설 / 시 / 에세이')}">selected</c:if> >소설 / 시 / 에세이</option>
									<option value="인문학 서적" <c:if test="${dto.d_bgenre.equals('인문학 서적')}">selected</c:if>>인문학 서적</option>
									<option value="과학 전문서적" <c:if test="${dto.d_bgenre.equals('과학 전문서적')}">selected</c:if>>과학 전문서적</option>
									<option value="기타 서적" <c:if test="${dto.d_bgenre.equals('기타 서적')}">selected</c:if>>기타 서적</option>
								</select>
							</td>						
					</tr>
					<tr>
						<td><span class="co_red">*</span> 종류</td>
						<td>
							<select name="d_bgenre2" class="input_04">
									<option value="아트지" <c:if test="${dto.d_bgenre2.equals('아트지')}"> selected </c:if>>아트지</option>
									<option value="레자크지" <c:if test="${dto.d_bgenre2.equals('레자크지')}"> selected </c:if>>레자크지</option>
									<option value="스노우지" <c:if test="${dto.d_bgenre2.equals('스노우지')}"> selected </c:if>>스노우지</option>
									<option value="모조지" <c:if test="${dto.d_bgenre2.equals('모조지')}"> selected </c:if>>모조지</option>
							</select>
						</td>
					
					</tr>
					<tr>
						<td><span class="co_red">*</span> 국내외</td>
							<td>
								<select name="d_blocation" class="input_04">
									<option value="국내" <c:if test="${dto.d_blocation.equals('국내')}"> selected </c:if>>국내</option>
									<option value="국외" <c:if test="${dto.d_blocation.equals('국외')}"> selected </c:if>>국외</option>
								</select>							
							</td>					
					</tr>
					<tr>
						<td><span class="co_red">*</span> 출간 날짜</td>
						<td><input type="text" name="d_bregistdate" value="${dto.d_bregistdate}" id="jo_user_pw" class="input_01"/></td>
						
					</tr>
					<tr>
						<td><span class="co_red">*</span>책 메인사진</td>
						<td>
							<c:if test="${dto.d_bpic == null}" >
								사진이 없습니다.<br />
								<input type="file" name="d_bpic" class="input_filebox">
							</c:if>
							<c:if test="${dto.d_bpic != null}" >
								<img src="\DoIt\d_bpic/${dto.d_bpic}" width="150px" /><br />
								<input type="file" name="d_bpic" class="input_filebox">
								<input type="hidden" name="d_bpicCheck" value="${dto.d_bpic}" />
							</c:if>
						</td>
					</tr>					
					<tr>
						<td><span class="co_red">*</span>책 목차</td>
						<td><textarea name="d_norder" id="jo_user_pw" class="input_01" style="width:500px; height:100px;">${d_norder}</textarea></td>
						
					</tr>
					
					<tr>
						<td><span class="co_red">*</span>책 소개글</td>
						<td><textarea name="d_nintro"  id="jo_user_pw" class="input_01" style="width:500px; height:100px;">${d_nintro}</textarea></td>
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
							<td><span>${dto.d_icode}</span></td>	
							<input type="hidden" name="d_icode" value="${dto.d_icode}">					
					</tr>	
					<tr>
						<td><span class="co_red">*</span>검수 총점</td>
						<td>
							<p class="sub_con">검수 점수 - 0 : 최상, 1 ~ 4 : 상, 5 ~ 8 : 중, 9 ~ 12 : 매입불가</p>
							<p>현재 검수 총점 <span >${dto.d_itotal}</span> 점</p>
							
						</td>
					</tr>
					<tr>
						<td><span class="co_red">*</span> 책 등급</td>
						
							<td>
							<p class="sub_con">책 등급 -  S : 최상 , A : 상 , B : 중 , C : 매입불가</p>
							<input type="text" name="d_bgrade" value="${dto.d_bgrade}" id="jo_user_pw" class="input_01"/>
							</td>
							
					</tr>
					<tr>
						<td><span class="co_red" >*</span> 책 품질 판정</td>
						<td class="onBook_modify_Ins">
							<br/>
							<span class="onIns_quality_check">헌 상태</span>
<!-- 							<script type="text/javascript">
							
								$(document).ready(function() {
										
								});

							</script> -->
							
							 <c:set var="d_iold" value="${dto.d_iold}"/>
							 <input type="radio" name="d_iold" value="0" <c:if test="${d_iold=='0'}">checked</c:if> class=""   > 최상
							 <input type="radio" name="d_iold" value="1" <c:if test="${d_iold=='1'}">checked</c:if> class=""> 상
							 <input type="radio" name="d_iold" value="2" <c:if test="${d_iold=='2'}">checked</c:if> class=""> 중
							 <input type="radio" name="d_iold" value="3" <c:if test="${d_iold=='3'}">checked</c:if> class=""> 매입불가
							 <br/><br/>
							 <c:set var="d_icover" value="${dto.d_icover}"/>
							 <span class="onIns_quality_check">표  지</span>
							 <input type="radio" name="d_icover" value="0" <c:if test="${d_icover=='0'}">checked</c:if> class=""> 최상
							 <input type="radio" name="d_icover" value="1" <c:if test="${d_icover=='1'}">checked</c:if> class=""> 상
							 <input type="radio" name="d_icover" value="2" <c:if test="${d_icover=='2'}">checked</c:if> class=""> 중
							 <input type="radio" name="d_icover" value="3" <c:if test="${d_icover=='3'}">checked</c:if> class=""> 매입불가
							 <br/><br/>
							 <c:set var="d_ispine" value="${dto.d_ispine}"/>
							 <span class="onIns_quality_check">책   등</span>
							 <input type="radio" name="d_ispine" value="0" <c:if test="${d_ispine=='0'}">checked</c:if> class=""> 최상
							 <input type="radio" name="d_ispine" value="1" <c:if test="${d_ispine=='1'}">checked</c:if> class=""> 상
							 <input type="radio" name="d_ispine" value="2" <c:if test="${d_ispine=='2'}">checked</c:if> class=""> 중
							 <input type="radio" name="d_ispine" value="3" <c:if test="${d_ispine=='3'}">checked</c:if> class=""> 매입불가
							 <br/><br/>
							 <c:set var="d_ibind" value="${dto.d_ibind}"/>
							 <span class="onIns_quality_check">제본상태</span>
							 <input type="radio" name="d_ibind" value="0" <c:if test="${d_ibind=='0'}">checked</c:if> class=""> 최상
							 <input type="radio" name="d_ibind" value="1" <c:if test="${d_ibind=='1'}">checked</c:if> class=""> 상
							 <input type="radio" name="d_ibind" value="2" <c:if test="${d_ibind=='2'}">checked</c:if> class=""> 중
							 <input type="radio" name="d_ibind" value="3" <c:if test="${d_ibind=='3'}">checked</c:if> class=""> 매입불가
							 <br/>
							 &nbsp;
	
							
						</td>
					</tr>
					<tr>
						<td><span class="co_red">*</span> 정가</td>
						<td>
							<input type="text" name="d_bvalue" value="${dto.d_bvalue}" id="jo_user_pw" class="input_01"/> \
						</td>
													
					</tr>
					<tr>
						<td><span class="co_red">*</span> 구매가</td>
								<td>
									<input type="text" name="d_bpurchasevalue" value="${dto.d_bpurchasevalue}" id="jo_user_pw" class="input_01"/> \
								</td>							
					</tr>
					<tr>
						<td><span class="co_red">*</span> 판매가</td>
						<td>
							<input type="text" name="d_bsellvalue" value="${dto.d_bsellvalue}" id="jo_user_pw" class="input_01"/> \
						</td>
														
					</tr>	
					<tr>
						<td><span class="co_red">*</span> 판매자 아이디</td>
							<td>
								<span>${dto.d_id}</span>
								<input type="hidden" name="d_id" value="${dto.d_id}"/>	
							</td>				
					</tr>	
					<tr>
						<td><span class="co_red">*</span> 검수일시</td>
							<td>
								<span>${dto.d_idate}</span>
								<input type="hidden" name="d_idate" value="${dto.d_idate}"/>
							</td>
						
					</tr>		
	
					
				</tbody>
			
			</table><!-- 회원가입 form 입력 2 끝 -->
			
			<p class="button_cell">
				<input type="submit" class="sub_button" value="수정"/>
				<button type="reset" class="re_button" 
				onclick="window.location='/DoIt/d_admin/adminOnBookList.do'">취소</button>
			</p>
			
			
		</form><!-- 책 등록 form 끝 -->
	</section><!-- 책 등록 form section 끝 -->
		
	</section>
</article>

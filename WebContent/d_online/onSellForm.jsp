<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!-- header import -->
<jsp:include page="/header.jsp" flush="false" /> 

<jsp:include page="/d_online/onSide.jsp"></jsp:include>

<article class="my_cont_wrap">		



<div class="rl alert alert-info"><small>*. 책 판매를 신청하는 페이지입니다. </small></div>
<!--------------------------------- 판매양식 ------------------------------------->	
<p id="onForm_title" class="big-font35 d-blod">책 판매 신청서 </p>
<hr />
<p id="onForm_line"></p>

<!-- 새로고침하였을 때 오류가 나지 않기 위해 책 코드 번호를 계속 주고 받음.------------------------------------- -->
<!-- Dao 에서 받은 임의의 책 정보를 다시 저장하여 input hidden name으로 재전송 합니다.(DB의 내용을 사용하지 않기 위해서) -->

<input type="hidden" name="${d_bno}" value="${d_bno}" />
<form action="/DoIt/d_online/onSellFormPro.do" method="post" enctype="multipart/form-data" name="onSellForm" id="onForm_wrap">
	<table id="onForm_con" style="border:1px solid black; width:100%;" >


		<tr>
			<c:if test="${dto.d_bpic != null}">
				<td class="onForm_left1 d-center" width="200px" height="50px" >
					책사진
				</td>
				<td>
					<img src="\DoIt\d_bpic/${dto.d_bpic}" width="120" />
					<input type="hidden" name="d_bpic_re" value="${dto.d_bpic}" />
					
				</td>
			</c:if>
		</tr>
		<tr>
			<td class="onForm_left1 d-center" width="200px" height="50px">회원 아이디</td>
			<td class="onForm_right" width="550px">&nbsp;&nbsp;${id}</td>
			<input type="hidden" name="${id}" value="${id}" />
		</tr>
		

		<tr>
			<td class="onForm_left1 d-center" height="30px" >책 이 름</td>
			<td class="onForm_right">
				<c:if test="${dto.d_bname != null}">&nbsp;&nbsp;${dto.d_bname}
					<input type="hidden" name="d_bname" value="${dto.d_bname}" />
				</c:if>
				<c:if test="${dto.d_bname == null}">
					<input type="text" name="d_bname" class="input_box">
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="onForm_left1 d-center"  height="30px">출 판 사</td>
			<td class="onForm_right">
				<c:if test="${dto.d_bpublisher != null}">${dto.d_bpublisher}</c:if>
					<input type="hidden" name="d_bpublisher" value="${dto.d_bpublisher}" />
				<c:if test="${dto.d_bpublisher == null}">
					<input type="text" name="d_bpublisher" class="input_box">
				</c:if>			
			</td>
		</tr>
		<tr>
			<td class="onForm_left1 d-center"  height="30px">저 자</td>
			<td class="onForm_right">
				<c:if test="${dto.d_bauthor != null}">${dto.d_bauthor}
					<input type="hidden" name="d_bauthor" value="${dto.d_bauthor}" />
				</c:if>
				<c:if test="${dto.d_bauthor == null}">
					<input type="text" name="d_bauthor" class="input_box">
				</c:if>			
			</td>
		</tr>
		<tr>
			<td class="onForm_left1 d-center"  height="30px">장 르</td>
			<td class="onForm_right">
				<c:if test="${dto.d_bgenre != null}">${dto.d_bgenre}
					<input type="hidden" name="d_bgenre" value="${dto.d_bgenre}" />
				</c:if>
				<c:if test="${dto.d_bgenre == null}">
					<select name="d_bgenre">
						<option value="소설 / 시 / 에세이" checked>소설 / 시 / 에세이</option>
						<option value="참고 / 전문서적">참고 / 전문서적</option>
						<option value="어린이 서적">어린이 서적</option>
						<option value="인문학 서적">인문학 서적</option>
						<option value="과학 전문서적">과학 전문서적</option>
						<option value="기타 서적">기타 서적</option>
					</select>
				</c:if>						
			</td>
		</tr>
		<tr>

			<td class="onForm_left1 d-center" height="30px">&nbsp;&nbsp;종 류</td>
			<td class="onForm_right">
				<c:if test="${dto.d_bgenre2 != null}">${dto.d_bgenre2}
					<input type="hidden" name="d_bgenre2" value="${dto.d_bgenre2}" />
				</c:if>
				<c:if test="${dto.d_bgenre2 == null}">
					<select name="d_bgenre2">
						<option value="아트지" checked>아트지</option>
						<option value="레자크지">레자크지</option>
						<option value="스노우지">스노우지</option>
						<option value="모조지">모조지</option>
					</select>
				</c:if>		

			</td>
		</tr>
		<tr>
			
			<td class="onForm_left1 d-center" height="30px">국내/외</td>
			<td class="onForm_right">
				<c:if test="${dto.d_blocation != null}">&nbsp;&nbsp;${dto.d_blocation}
					<input type="hidden" name="d_blocation" value="${dto.d_blocation}" />
				</c:if>
				<c:if test="${dto.d_blocation == null}">
					<select name="d_blocation">
						<option value="국내">국내</option>
						<option value="국외">국외</option>
					</select>
				</c:if>		
			</td>
		</tr>
		<tr>
			<td class="onForm_left1 d-center" height="30px">책 출간 날짜</td>
			<td class="onForm_right">
				<p >* ex) 2017-03-23</p>
				<c:if test="${dto.d_bregistdate != null}">&nbsp;&nbsp;${dto.d_bregistdate} 원
					<input type="hidden" name="d_bregistdate" value="${dto.d_bregistdate}" />
					
				</c:if>
				<c:if test="${dto.d_bregistdate == null}">
					<input type="text" name="d_bregistdate" class="input_box">
				</c:if>	
			</td>
		</tr> 
		
 		<tr>
			<td class="onForm_left1 d-center" height="30px">가격 (정가)</td>
			<td class="onForm_right">
				<c:if test="${dto.d_bvalue != null}">&nbsp;&nbsp;${dto.d_bvalue} 원
					<input type="hidden" name="d_bvalue" value="${dto.d_bvalue}" />
					
				</c:if>
				<c:if test="${dto.d_bvalue == null}">
					<input type="text" name="d_bvalue" class="input_box">
				</c:if>	
			</td>
		</tr> 
		<tr>
			<td class="onForm_left1 d-center"><span class="co_red">*</span>책 목차</td>
			<td class="onForm_right">

 				<c:if test="${obiDto.d_norder == null}"> 
					<textarea name="d_norder" id="jo_user_pw" class="input_01" style="width:400px; height:100px;" ></textarea>
 				</c:if> 
 				<c:if test="${obiDto.d_norder != null}">
					<input type="hidden" name="d_norder" value="${d_norder}" />
					${d_norder}
				</c:if> 

			</td>
		</tr>
		
		<tr>
			<td class="onForm_left1 d-center"><span class="co_red" >*</span>책 소개글</td>
			<td class="onForm_right">
				<div>
				<c:if test="${obiDto.d_nintro == null}">
					<textarea name="d_nintro" id="jo_user_pw" class="input_01" style="width:400px; height:100px;" ></textarea>
				</c:if>
				<c:if test="${obiDto.d_nintro != null}">
					<input type="hidden" name="d_nintro" value="${d_nintro}" />
					${d_nintro}
				</c:if>
				</div>							
			</td>
		</tr>


<!-- 책 링크를 타고 신청서를 작성 할 때에는 Form 왼쪽부분에 DB에서 불러온 책사진을 꺼내어주고/ 새로운 책을 신청 할 땐,사진이없고, 첨부할 수 있는 양식을 보여줍니다. -->		
		<c:if test="${dto.d_bpic == null}">
 		<tr>
			<td class="onForm_left1 d-center" height="30px">책 사진 등록</td>
			<td class="onForm_right">
				<input type="file" name="d_bpic" class="input_filebox">
			</td>
		</tr>
		</c:if>
	</table>
	<br />
	<p class="small">
		*. 책 링크를 선택하여 판매하실 때엔, 기본적인 정보가 제공됩니다.<br />
		*. 가격에 대한 정보는 <span class="code">검수</span>가 끝난다음 알 수 있습니다.<br />
		*. 판매 신청 후에는 <span class="code">마이 페이지</span>에서 진행상황을 확인해 주세요<br />
		*. 또, <span class="code">사이트에서 추천하는 배송 방법</span>을 이용하면 검수가 더욱 빠르게 진행 될 수 있습니다.<br />
		*. 검수가 완료되면, 자동으로 <span class="code">문자통보</span>를 해드리며, 회원님의 <span class="code">D-cash</span>로 돈이 입금됩니다.<br />
	
	</p>
	<br /><br />
	<p><input type="submit" value="신청하기" class="btn btn-default d-w-20"></p>


</form>
</article>









<!-- footer import -->
<jsp:include page="/footer.jsp" />
		
		
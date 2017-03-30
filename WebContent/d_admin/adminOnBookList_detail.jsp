<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css"  href="/DoIt/css/online_admin.css">


<!-- header import -->
<%@include file="/header.jsp" %>
<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_admin/adminSide.jsp" />

	<article class="my_cont_wrap">
		<form action="/DoIt/d_admin/adminOnBook_modify.do" method="post">
			<div>
				<p id="OnBook_detail_title">${dto.d_bname}</p>
				<p class="detail_line"></p>
				
				<article id="detail_con">
					<section id="detail_left">
						<div class="detail_img">
							<img src="/DoIt/d_bpic/${dto.d_bpic}" alt="" class="">
						</div>
						<!-- <p class="detail_left_line"></p> -->
						
					
					</section>
					<section id="detail_right">
							<p class="detail_right_txt">책 정보</p>
							<div class="detail_right_txtBox">
								<div class="detail_right_txtBox_L">
									<p><span class="txtBox_spanTxt_1">책 코 드</span>  :   <span class="txtBox_spanTxt_2">${dto.d_bcode}</span></p>
									<input type="hidden" name="d_bcode" value="${dto.d_bcode}">
									<p><span class="txtBox_spanTxt_1">저&nbsp;&nbsp;&nbsp; 자</span>  :   <span class="txtBox_spanTxt_2">${dto.d_bauthor}</span></p>
									<p><span class="txtBox_spanTxt_1">출 판 사</span> :   <span class="txtBox_spanTxt_2">${dto.d_bpublisher}</span></p>
									<p><span class="txtBox_spanTxt_1">장&nbsp;&nbsp;&nbsp; 르</span> :    <span class="txtBox_spanTxt_2">${dto.d_bgenre}</span></p>
									<p><span class="txtBox_spanTxt_1">종&nbsp;&nbsp;&nbsp; 류</span> :    <span class="txtBox_spanTxt_2">${dto.d_bgenre2}</span></p>
									<p><span class="txtBox_spanTxt_1">국 내/외</span> : <span class="txtBox_spanTxt_2">${dto.d_blocation}</span></p>
									<p><span class="txtBox_spanTxt_1">출간날짜</span> : <span class="txtBox_spanTxt_2"> ${dto.d_bregistdate}</span></p>
								</div>
								<div class="detail_right_txtBox_R">
									<p><span class="txtBox_spanTxt_1">정&nbsp;&nbsp;&nbsp; 가</span> : <span class="txtBox_spanTxt_2">${dto.d_bvalue}</span> \</p>
									<p><span class="txtBox_spanTxt_1">구 입 가</span> : <span class="txtBox_spanTxt_2">${dto.d_bpurchasevalue}</span> \</p>
									<p><span class="txtBox_spanTxt_1">판 매 가</span> : <span class="txtBox_spanTxt_2">${dto.d_bsellvalue}</span> \</p>
								</div>
							</div>
							<p class="detail_line_2"></p>
							
							<p class="detail_right_txt2 ">검수 정보</p>
							<div class="detail_right_txtBox">
								<p><span class="txtBox_spanTxt_1">검수코드</span> : <span class="txtBox_spanTxt_2">${dto.d_icode}</span> </p>
								<p><span class="txtBox_spanTxt_1">책 등 급</span> : <span class="txtBox_spanTxt_2">${dto.d_bgrade}</span> </p>
								<p class="txtBox_spanTxt_3 "><span >검수항목</span></p>
									<div class="detail_right_txtBox_2">
										<p><span class="txtBox_spanTxt_4" >1. 헌 상 태</span> : <span class="txtBox_spanTxt_2">${dto.d_iold} </span> 점 </p>
										<p><span class="txtBox_spanTxt_4">2. 표     지</span> : <span class="txtBox_spanTxt_2"> ${dto.d_icover} </span> 점</p>
										<p><span class="txtBox_spanTxt_4">3. 책     등</span> : <span class="txtBox_spanTxt_2" >${dto.d_ispine} </span> 점</p>
										<p><span class="txtBox_spanTxt_4">4. 제본상태</span> : <span class="txtBox_spanTxt_2">${dto.d_ibind} </span> 점</p>
									</div>
								<p><span class="txtBox_spanTxt_1">검수 총점</span> : <span class="txtBox_spanTxt_2">${dto.d_itotal} </span> 점</p>
								<p><span class="txtBox_spanTxt_5">검수 등록 날짜</span> : <span>${dto.d_idate} </span> </p>
							</div>
						
					</section>
					</article>
				</div>
				
				<section id="detail_btn_box">
					<input type="submit" value="수정 " class="detail_btn_modi">
					<input type="button" value="삭제"  
					onclick="window.location='/DoIt/d_admin/adminOnBook_delete.do?d_bcode=${dto.d_bcode}'"  class="detail_btn_del">
				</section>
				
				<p class="detail_line_2"></p>
				
				<section id="detail_order_wrap">
					<p id="detail_order_title">목차</p>
					<p>${dto.d_norder}</p>
				</section>
				<p class="detail_line_3"></p>
				<section id="detail_intro_wrap">
					<p id="detail_intro_title">책 소개</p>
					<p>${dto.d_nintro}</p>
				</section>
				
		</form>
			
	</article>



	
	
	
	
	
	
	
	
	
	
	
	
	
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>		

		
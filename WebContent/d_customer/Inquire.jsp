<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	
	<div class="tabWrap">
		<ul class="inquire_tabs">
			<li class="inquire_tab1"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire&mem=user">문의하기</a></li>
			<li class="inquire_tab2"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=wait">문의내역</a></li>
		</ul>
	</div>
	<form action="/DoIt/d_customer/InquirePro.do" method="post" name="Inquire" onsubmit="return InquireSave()">	
		<input type="hidden" name="c_ino" value="${c_ino}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="re_step" value="${re_step}">
		<input type="hidden" name="re_level" value="${re_level}"> 
		<input type="hidden" name="mem" value="user"> 
		
		<section id="b_coustomer">
			<p class="Inquire_subtitle">고객정보</p>
			<table>
				<tr>
					<td class="Inquire_left"><p>이름</p></td>
					<td class="Inquire_right"><p>${LDto.d_name}</p></td>
				</tr>
				<tr>
					<td class="Inquire_left"><p>E-Mail</p></td>
					<td class="Inquire_right"><p>${LDto.d_mail}</p></td>
				</tr>
				<tr>
					<td class="Inquire_left"><p>연락처</p></td>
					<td class="Inquire_right"><p>${LDto.d_phone}</p></td>
				</tr>
			
			</table>
		</section>
		
		<section class="b_inquire">
			<p class="Inquire_subtitle">문의정보</p>
			<table>
				<tr>
					<td class="Inquire_left"><p>유형</p></td>
					<td class="Inquire_right">
						<p>
							<select  name="c_itype" class="Inquire_select">
								<option value="0" selected="selected">온라인 중고서점</option>
								<option value="1">도서관</option>
								<option value="2">중고 직거래</option>
								<option value="3">주문/주문변경</option>
								<option value="4">배송</option>
								<option value="5">회원</option>
								
								
							</select>
						</p>
					</td>
				</tr>
				<tr>
					<td class="Inquire_left"><p>제목</p></td>
					<td class="Inquire_right">
						<p><input type="text" name="c_isubject" class="c_isubject"/></p>
					</td>
				</tr>
				<tr class="Inquire_content">
					<td class="Inquire_left"><p>내용</p></td>
					<td class="Inquire_right"><p><textarea name="c_icontent"></textarea></p></td>
				</tr>
			
			</table>
		</section>
		<p class="Inquire_btn">
				
			<input type="reset" value="취소하기" class="re_button">
			<input type="submit" value="보내기"  class="re_button">
		</p>
	</form>

		
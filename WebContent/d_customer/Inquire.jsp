<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<ul>
		<li><a href="/DoIt/d_coustomer/coustomer_Inquire.do?inquire=Inquire">문의하기</a></li>
		<li><a href="/DoIt/d_coustomer/coustomer_Inquire.do?inquire=Inquire_reply">문의내역</a></li>
	</ul>
	<form>		
		<section id="b_coustomer">
			<p class="Inquire_subtitle">고객정보</p>
			<table>
				<tr>
					<td class="Inquire_left">이름</td>
					<td>${LDto.d_name}</td>
				</tr>
				<tr>
					<td class="Inquire_left">E-Mail</td>
					<td>${LDto.d_mail}</td>
				</tr>
				<tr>
					<td class="Inquire_left">연락처</td>
					<td>${LDto.d_phone}</td>
				</tr>
			
			</table>
		</section>
		
		<section id="b_inquire">
			<p class="Inquire_subtitle">문의정보</p>
			<table>
				<tr>
					<td class="Inquire_left">유형</td>
					<td>
						<select  name="c_itype">
							<option value="0" selected="selected">온라인 중고서점</option>
							<option value="1">도서관</option>
							<option value="2">중고 직거래</option>
							<option value="3">주문/주문변경</option>
							<option value="4">배송</option>
							<option value="5">회원</option>
							
							
						</select>
					</td>
				</tr>
				<tr>
					<td class="Inquire_left">제목</td>
					<td>
						<input type="text" name="c_isubject"/>
					</td>
				</tr>
				<tr>
					<td class="Inquire_left">내용</td>
					<td><textarea name="c_icontent"></textarea></td>
				</tr>
			
			</table>
		</section>
		
		<input type="button" value="취소하기" onclick="">
		<input type="submit" value="보내기">
		
	</form>

		
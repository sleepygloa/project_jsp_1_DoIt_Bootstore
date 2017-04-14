<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form action="/DoIt/d_admin/onInspectionPro.do" method="GET" >
<input type="hidden" name="d_bcode" value="${d_bcode}"/>

	<p class="mana_Stitle">책코드 : <span class="co_red">${d_bcode}</span></p>
	
	<table class="gum_write_tab" cellspacing="0">
	<colgroup>
		<col width="15%"><col width="10%"><col width="75%">
	</colgroup>
	<thead>
		<tr>
			<th>대분류</th>
			<th>소분류</th>
			<th>내용</th>
		</tr>
	</thead>
	<tbody>

		<tr>
			<td class="gum_write_tab_1">책이름</td>
			<td class="gum_write_tab_2">이름</td>
			<td>${d_bname}</td>
		</tr>
		
		<tr>
			<td class="gum_write_tab_1">가격(정가)</td>
			<td class="gum_write_tab_2">가격</td>
			<td><a class="co_red bold" style="font-size:1.1em">${d_bvalue}</a>원</td>
		</tr>
		
		<tr>
			<td rowspan="4" class="gum_write_tab_1">책 품질 판정</td>
			<td class="gum_write_tab_2">헌 상태</td>
			<td>
				<label> <input type="radio" name="d_iold" value="0" class="" checked> 최상</label>
				<label> <input type="radio" name="d_iold" value="1" class=""> 상</label>
				<label> <input type="radio" name="d_iold" value="2" class=""> 중</label>
				<label> <input type="radio" name="d_iold" value="3" class=""> 매입불가</label>
			</td>
		</tr>
		
		<tr>
			<td class="gum_write_tab_2">표 지</td>
			<td>
				 <label><input type="radio" name="d_icover" value="0" class="" checked> 최상</label>
				 <label><input type="radio" name="d_icover" value="1" class=""> 상</label>
				 <label><input type="radio" name="d_icover" value="2" class=""> 중</label>
				 <label><input type="radio" name="d_icover" value="3" class=""> 매입불가</label>
				
			</td>
		</tr>
		
		<tr>
			<td class="gum_write_tab_2">책등</td>
			<td>
				<label> <input type="radio" name="d_ispine" value="0" class="" checked> 최상</label>
				<label> <input type="radio" name="d_ispine" value="1" class=""> 상</label>
				<label> <input type="radio" name="d_ispine" value="2" class=""> 중</label>
				<label> <input type="radio" name="d_ispine" value="3" class=""> 매입불가</label>
			</td>
		</tr>
		
		<tr>
			<td class="gum_write_tab_2">제본상태</td>
			<td>
				<label> <input type="radio" name="d_ibind" value="0" class="" checked> 최상</label>
				<label> <input type="radio" name="d_ibind" value="1" class=""> 상</label>
				<label> <input type="radio" name="d_ibind" value="2" class=""> 중</label>
				<label> <input type="radio" name="d_ibind" value="3" class=""> 매입불가</label>
			</td>
		</tr>
		
		</tbody>
	</table>
	
	<img alt="품질판정리스트" src="/DoIt/images/Inspection.PNG" class="onIns_img" >
	
	<p class="button_cell">
		<button class="sub_button" type="submit">검수 등록</button>
		<button class="re_button" onclick="javascript:history.go(-1)">뒤로 가기</button>
	</p>

</form>


		
		
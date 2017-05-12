<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css"  href="/DoIt/css/resell.css">


<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_admin/side_admin.jsp"></jsp:include>



	<%-- 본문 내용 --%>
	<article class="my_cont_wrap"> 
	
		<p class="my_title">
			판매자 승인
		</p>
		
		<p class="my_sub_title">
			판매자등급을 신청한 회원입니다.
			<span>승인하거나 거절 또는 강등할 수 있습니다.</span>
		</p>
		
		<p class="L_title">
			판매자 승인 or 거절
		</p>
		
		
		<%-- 본문 내용 --%>
		<table class="list_con_wrap" cellspacing="0">
			<colgroup>
				<col width="25%"><col width="25%"><col width="25%"><col width="25%">
			</colgroup>
			<thead>
				<tr>
					<th>신청자</th>
					<th>${article.d_id}</th>
					<th>작성일</th>
					<th>${article.rbook_reg_date}</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>내용</td>
					<td colspan="3" style="height:300px;">${article.rbook_intro}</td>
				</tr>
				<tr>
					<td>승인여부</td>
					<td colspan="3">
							<c:if test="${article.d_mech_grade==1  }">
				  				<a type="button"  onclick="window.location='/DoIt/d_admin/callOkPro.do?d_id=${article.d_id}'" class="men_add fl_le">승인</a>
				  			</c:if>
				  			<a onclick="window.location='/DoIt/d_admin/callNoPro.do?d_id=${article.d_id}'" class="men_add fl_le">거절 or 강등</a>
					</td>
				</tr>
			</tbody>
		
		</table>	
	
		<%-- 이동 버튼 --%>
		<p class="write_bu">
			<a onclick="window.location='/DoIt/d_admin/callOkList.do'">전체보기</a>
			<a onclick="window.location='/DoIt/d_admin/waitOkForm.do'" >승인 대기중 상태</a>
		</p>
		
	</article>   
     
     
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>







    
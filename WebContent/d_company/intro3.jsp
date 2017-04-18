<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header import -->
<jsp:include page="/header.jsp" />

	<jsp:include page="/d_company/side_company.jsp"></jsp:include>

<!-- content -->
<article class="my_cont_wrap">
<article class="bon_cont fl_ri">
		<h3>DoIt 조직도 본문</h3>
			<header class="sub_title">
				<p>DOITBOOK 조직도 </p>
				<p>DOITBOOK의 조직도 입니다.</p>
			</header>

			<section class="jozic_sec">
				<article class="jozic_title">
					<p><img src="/DoIt/images/Organization_chart.png"></p>
				</article>

				<article class="jozic_info1">
					<p><img src="/DoIt/images/title_dot.png" width="12px">CEO</p>
					<ul>
						<li>DoIt 업무 총괄</li>
						<li>경영지원팀, 기획전략팀, 영업팀 총괄</li>
						<li>연락처</li>
					</ul>

					<table class="jozic_table">
					<colgroup>
					<col width="2%"><col width="1%"><col width="3%"><col width="4%">
					</colgroup>
						<thead>
							<tr>
								<th scope="col">성명</th>
								<th scope="col">직책</th>
								<th scope="col">전화번호</th>
								<th scope="col">이메일</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>이윤수</td>
								<td>회장</td>
								<td>010-2740-7156</td>
								<td>cjsrnrdmfh1@naver.com</td>
							</tr>
						</tbody>
					</table>

						<p><img src="/DoIt/images/title_dot.png" width="12px">관리자</p>
					<ul>
						<li>각 부서 업무 총괄</li>
						<li>본부내 팀간 실적 총괄</li>
						<li>고객 요청 처리 대응</li>
					</ul>

					<table class="jozic_table">
					<colgroup>
					<col width="2%"><col width="1%"><col width="3%"><col width="4%">
					</colgroup>
						<thead>
							<tr>
								<th scope="col">성명</th>
								<th scope="col">직책</th>
								<th scope="col">전화번호</th>
								<th scope="col">이메일</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="adminList" items="${adminList}">
							<tr>
							
		 						<td>${adminList.d_name}</td>
								<td>관리자</td>
								<td>${adminList.d_phone}</td>
								<td>${adminList.d_mail}</td> 
							</tr>
						</c:forEach>
						</tbody>
					</table>
					

					<div style="height:150px; width:100%;">

					</div>
				</article>

			</section>

		</article>
</article>



<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>




		
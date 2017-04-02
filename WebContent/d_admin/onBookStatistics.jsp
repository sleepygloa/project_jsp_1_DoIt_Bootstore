<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--------------- header include --------------%>
<jsp:include page="/header.jsp" />

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_admin/adminSide.jsp" />

<article class="my_cont_wrap ">


<table border="1px solid black" class="d-center d-margin" width="700px">
	<tr>
		<td colspan="6">DoIt 도서 서비스의 연령별 전체 이용현황(가입현황) </td>
	</tr>
	<tr>
		<td colspan="6"></td>
	</tr>
	
	<tr>
		<td width="100px" rowspan="6">
			<c:if test="${avgage10 == 0}"></c:if>
			<c:if test="${avgage10 > 0 && avgage10 < 10}"><small>${avgage10}명</small><br /><img src="/DoIt/images/graph1.png" /></c:if>
			<c:if test="${avgage10 > 10 && avgage10 < 20}"><small>${avgage10}명</small><br /><img src="/DoIt/images/graph2.png" /></c:if>
			<c:if test="${avgage10 > 20 && avgage10 < 30}"><small>${avgage10}명</small><br /><img src="/DoIt/images/graph3.png" /></c:if>
			<c:if test="${avgage10 > 30 && avgage10 < 40}"><small>${avgage10}명</small><br /><img src="/DoIt/images/graph4.png" /></c:if>
			<c:if test="${avgage10 > 40 && avgage10 < 50}"><small>${avgage10}명</small><br /><img src="/DoIt/images/graph5.png" /></c:if>
			<c:if test="${avgage10 > 50 && avgage10 < 60}"><small>${avgage10}명</small><br /><img src="/DoIt/images/graph6.png" /></c:if>
		</td>
		<td width="100px" rowspan="6">
			<c:if test="${avgage20 == 0}"></c:if>
			<c:if test="${avgage20 > 0 && avgage20 < 10}"><small>${avgage20}명</small><br /><img src="/DoIt/images/graph1.png" /></c:if>
			<c:if test="${avgage20 > 10 && avgage20 < 20}"><small>${avgage20}명</small><br /><img src="/DoIt/images/graph2.png" /></c:if>
			<c:if test="${avgage20 > 20 && avgage20 < 30}"><small>${avgage20}명</small><br /><img src="/DoIt/images/graph3.png" /></c:if>
			<c:if test="${avgage20 > 30 && avgage20 < 40}"><small>${avgage20}명</small><br /><img src="/DoIt/images/graph4.png" /></c:if>
			<c:if test="${avgage20 > 40 && avgage20 < 50}"><small>${avgage20}명</small><br /><img src="/DoIt/images/graph5.png" /></c:if>
			<c:if test="${avgage20 > 50 && avgage20 < 60}"><small>${avgage20}명</small><br /><img src="/DoIt/images/graph6.png" /></c:if>
		</td>
		<td width="100px" rowspan="6">
			<c:if test="${avgage30 == 0}"></c:if>
			<c:if test="${avgage30 > 0 && avgage30 < 10}"><small>${avgage30}명</small><br /><img src="/DoIt/images/graph1.png" /></c:if>
			<c:if test="${avgage30 > 10 && avgage30 < 20}"><small>${avgage30}명</small><br /><img src="/DoIt/images/graph2.png" /></c:if>
			<c:if test="${avgage30 > 20 && avgage30 < 30}"><small>${avgage30}명</small><br /><img src="/DoIt/images/graph3.png" /></c:if>
			<c:if test="${avgage30 > 30 && avgage30 < 40}"><small>${avgage30}명</small><br /><img src="/DoIt/images/graph4.png" /></c:if>
			<c:if test="${avgage30 > 40 && avgage30 < 50}"><small>${avgage30}명</small><br /><img src="/DoIt/images/graph5.png" /></c:if>
			<c:if test="${avgage30 > 50 && avgage30 < 60}"><small>${avgage30}명</small><br /><img src="/DoIt/images/graph6.png" /></c:if>
		</td>
		<td width="100px" rowspan="6">
			<c:if test="${avgage40 == 0}"></c:if>
			<c:if test="${avgage40 > 0 && avgage40 < 10}"><small>${avgage40}명</small><br /><img src="/DoIt/images/graph1.png" /></c:if>
			<c:if test="${avgage40 > 10 && avgage40 < 20}"><small>${avgage40}명</small><br /><img src="/DoIt/images/graph2.png" /></c:if>
			<c:if test="${avgage40 > 20 && avgage40 < 30}"><small>${avgage40}명</small><br /><img src="/DoIt/images/graph3.png" /></c:if>
			<c:if test="${avgage40 > 30 && avgage40 < 40}"><small>${avgage40}명</small><br /><img src="/DoIt/images/graph4.png" /></c:if>
			<c:if test="${avgage40 > 40 && avgage40 < 50}"><small>${avgage40}명</small><br /><img src="/DoIt/images/graph5.png" /></c:if>
			<c:if test="${avgage40 > 50 && avgage40 < 60}"><small>${avgage40}명</small><br /><img src="/DoIt/images/graph6.png" /></c:if>			
		</td>
		<td width="100px" rowspan="6">
			<c:if test="${avgage50 == 0}"></c:if>
			<c:if test="${avgage50 > 0 && avgage50 < 10}"><small>${avgage50}명</small><br /><img src="/DoIt/images/graph1.png" /></c:if>
			<c:if test="${avgage50 > 10 && avgage50 < 20}"><small>${avgage50}명</small><br /><img src="/DoIt/images/graph2.png" /></c:if>
			<c:if test="${avgage50 > 20 && avgage50 < 30}"><small>${avgage50}명</small><br /><img src="/DoIt/images/graph3.png" /></c:if>
			<c:if test="${avgage50 > 30 && avgage50 < 40}"><small>${avgage50}명</small><br /><img src="/DoIt/images/graph4.png" /></c:if>
			<c:if test="${avgage50 > 40 && avgage50 < 50}"><small>${avgage50}명</small><br /><img src="/DoIt/images/graph5.png" /></c:if>
			<c:if test="${avgage50 > 50 && avgage50 < 60}"><small>${avgage50}명</small><br /><img src="/DoIt/images/graph6.png" /></c:if>			
		</td>
		<td width="100px" rowspan="6">
			<c:if test="${avgage60 == 0}"></c:if>
			<c:if test="${avgage60 > 0 && avgage60 < 10}"><small>${avgage60}명</small><br /><img src="/DoIt/images/graph1.png" /></c:if>
			<c:if test="${avgage60 > 10 && avgage60 < 20}"><small>${avgage60}명</small><br /><img src="/DoIt/images/graph2.png" /></c:if>
			<c:if test="${avgage60 > 20 && avgage60 < 30}"><small>${avgage60}명</small><br /><img src="/DoIt/images/graph3.png" /></c:if>
			<c:if test="${avgage60 > 30 && avgage60 < 40}"><small>${avgage60}명</small><br /><img src="/DoIt/images/graph4.png" /></c:if>
			<c:if test="${avgage60 > 40 && avgage60 < 50}"><small>${avgage60}명</small><br /><img src="/DoIt/images/graph5.png" /></c:if>
			<c:if test="${avgage60 > 50 && avgage60 < 60}"><small>${avgage60}명</small><br /><img src="/DoIt/images/graph6.png" /></c:if>					
		</td>
	</tr>

</table>
<table border="1px solid black" class="d-center d-margin" width="700px">
	<tr>
		<td>10대</td>
		<td>20대</td>
		<td>30대</td>
		<td>40대</td>
		<td>50대</td>
		<td>60대</td>
	</tr>
</table>

</article>



<!-- footer import -->
<jsp:include page="/footer.jsp" />
		
		
		
		
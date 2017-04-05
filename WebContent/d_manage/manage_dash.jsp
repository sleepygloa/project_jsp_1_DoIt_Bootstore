<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--------------- header include --------------%>
<jsp:include page="ma_header.jsp"></jsp:include>


<%-- 전체 내용 wrap --%>
<section class="mana_wrap_a">

	<%--------------- 사이드 메뉴 include width: height:1537px --------------%>
	<jsp:include page="d_mana_side.jsp"></jsp:include>

	
	<%-- 본문 내용 height:1530px; --%>
	<article class="dash_wrap">	
		
		<%-- 본문 내용 정렬 : width:100%;  --%>
		<article class="dash_wrap_cont">
			
			<%-- 공동 nav 불러오기 --%>
			<jsp:include page="/d_manage/cont_header.jsp"></jsp:include>
			
			
			<%-- 본문 내용 불러오기 --%>
			<jsp:include page="/d_manage/${ mana_page }.jsp" ></jsp:include>
			
			
		</article>
			
	</article>

	
</section>


<%--------------- footer include --------------%>
<jsp:include page="ma_footer.jsp"></jsp:include>

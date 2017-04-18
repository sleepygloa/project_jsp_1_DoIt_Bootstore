<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<script type="text/javascript" src="/DoIt/js/Inquire.js"></script>
<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_customer/side_customer.jsp" />


	<article class="my_cont_wrap">
	
	<c:if test="${!admin.equals('admin')}">
		<p class="my_title">1 : 1 문의 신청</p>
		<p class="my_sub_title"> </p>
	</c:if>
	
	<c:if test="${admin.equals('admin')}">
		<p class="my_title">
			관리자용 문의 내역 전체 리스트
		</p>
	
		<p class="my_sub_title">
			문의 내역에 답변을 달기 위해 모든 문의의 리스트를 보여줍니다.
		</p>	
		<p class="L_title">
			<a class="d-left"></a>
		</p>
	</c:if>
			
	
		<div class="tabWrap">
			<ul class="inquire_tabs">
				<c:if test="${!admin.equals('admin')}">
					<li class="inquire_tab2"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire&mem=user">문의하기</a></li>
					<li class="inquire_tab1"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=wait">문의내역</a></li>
				</c:if>
				<c:if test="${admin.equals('admin') && admin_reply.equals('wait')}">
					<li class="inquire_tab1"><a href="/DoIt/d_customer/customer_InquireList.do?list=admin&reply=wait">문의한 내역</a></li>
					<li class="inquire_tab2"><a href="/DoIt/d_customer/customer_InquireList.do?list=admin&reply=finish">답변완료 내역</a></li>
		
				</c:if>
				<c:if test="${admin.equals('admin') && admin_reply.equals('finish')}">
					<li class="inquire_tab2"><a href="/DoIt/d_customer/customer_InquireList.do?list=admin&reply=wait">문의한 내역</a></li>
					<li class="inquire_tab1"><a href="/DoIt/d_customer/customer_InquireList.do?list=admin&reply=finish">답변완료 내역</a></li>
		
				</c:if>
			</ul>
		</div>
	
		<div  class="reply_tabWrap">
			<ul class="reply_tabs">
				<c:if test="${reply.equals('wait')}">
					<li class="reply_tab1"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=wait">문의한 내역</a></li>
					<li class="reply_tab2"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=finish">답변완료내역</a></li>
				</c:if>
				<c:if test="${reply.equals('finish')}">
					<li class="reply_tab2"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=wait">문의한 내역</a></li>
					<li class="reply_tab1"><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=finish">답변완료내역</a></li>
				</c:if>
			</ul>
		</div>
		
		<table class="inquireCon_table">
			<tr  class="inquireCon_tr1">
				<td class="inquireCon_left">제목</td>
				<td class="inquireCon_right " colspan="3">${idto.c_isubject}</td>
			</tr>
			<tr class="inquireCon_tr1">
				<td class="inquireCon_left ">작성자</td>
				<td class="inquireCon_right2  ">${ldto.d_name}</td>
				
				<td class="inquireCon_left">문의한 날</td>
				<td class="inquireCon_right ">${idto.c_idate}</td>
			</tr>
		
			<tr class="inquireCon_tr1">
				<td class="inquireCon_left">문의 분류</td>
				<td class="inquireCon_right " colspan="3">${c_itype}</td>
			</tr>
	
			<tr class="inquireCon_tr2">
				<td class="inquireCon_left">내용</td>
				<td  class="inquireCon_right "colspan="3">${idto.c_icontent}</td>
			</tr>
			</table>
		
			<div class="inquireCon_btn">
				<c:if test="${ sessionScope.memId != null && sessionScope.memMG == 10 && admin_reply =='wait' }">
						<button class="re_button" onclick="window.location='/DoIt/d_customer/InquireReplyForm.do?c_ino=${c_ino}&ref=${ref}&re_step=${re_step}&re_level=${re_level}&mem=admin'">답변쓰기</button>
				</c:if>
	

				<c:if test="${reply != null }">
						<button class="re_button" onclick="window.location='/DoIt/d_customer/customer_Inquire.do?inquire=Inquire_reply&reply=${reply}'">목록</button>
				</c:if>
				<c:if test="${admin =='admin'}">
					<c:if test="${admin_reply =='wait'}">
						<button class="re_button" onclick="window.location='/DoIt/d_customer/customer_InquireList.do?reply=wait'">목록</button>
					</c:if>
					<c:if test="${admin_reply =='finish'}">
						<button class="re_button" onclick="window.location='/DoIt/d_customer/customer_InquireList.do?reply=finish'">목록</button>
					</c:if>
				</c:if>
			</div>
		
		
	</article>
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>
	
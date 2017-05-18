<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<article id="my_side">
	<p class="side_top">고객센터</p>
	<ul class="my_side_ul">
		<li><a href="/DoIt/d_customer/noticeList.do">공지사항</a></li>
		<li><a href="/DoIt/d_customer/faqList.do">F A Q</a></li>
		<%-- <li><a href="/DoIt/d_login/myList.do?cols=dr_rent">1 : 1문의 신청</a></li>--%>
<<<<<<< HEAD
		<li>
			<c:if test="${ sessionScope.memId !=null }">
				<a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire&mem=user">1 : 1 문의 신청</a>
			</c:if>
			<c:if test="${sessionScope.memId ==null }">
				<a href="javascript:board_se()">1 : 1 문의 신청</a>
			</c:if>
		</li>
=======
		<li><a href="/DoIt/d_customer/customer_Inquire.do?inquire=Inquire&mem=user">1 : 1 문의 신청</a></li>
>>>>>>> 06e83cdc0212d155692e1e75dda189dd861591c1
		<li><a href="/DoIt/d_customer/customer_InquireList.do?reply=wait">관리자용 문의 리스트</a></li>
	</ul>			

</article>

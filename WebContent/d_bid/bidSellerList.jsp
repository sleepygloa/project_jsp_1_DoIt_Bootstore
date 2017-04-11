<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_login/side_my.jsp"></jsp:include>
    <article class="my_cont_wrap">
    	<p class="my_title">나의 낙찰정보</p>
			<p class="my_sub_title">
			</p>
			
			<ul class="sort_top">
				<li><a>글목록(전체글:${count})</a></li>
			</ul>
			
    	<%-- 게시글이 없을 경우 --%>
			<c:if test="${count == 0}">
				<table width="100%" cellspacing="0" class="rent_list">
					<tr>
						<td colspan="4">낙찰된 정보가 없습니다.</td>
					</tr>
				</table>
			</c:if>
		
		<c:if test="${count > 0}">
			<table>
					<tr>
		  				<td>글번호</td>
		  				<td>글제목</td>
		  				<td>책이름</td>
		  				<td>입찰가격</td>
		  				<td>판매자정보</td>
		  			</tr>
				<c:forEach var="article" items="${articleList}">
		  			
		  			<tr>
		  				<td>
		  					<c:out value="${number}"/>
		    				<c:set var="number" value="${number-1}"/>
		  				</td>
		  				<td>
		  					${article.bid_subject}
		  				</td>
		  				<td>
		  					${article.bid_name }
		  				</td>
		  				<td>
		  					${article.bid_price2 }
		  				</td>
		  				<td>
		  					<div>
		  						<p><img src="/DoIt/bid_pic/${article.d_pic}" ></p>
		  					</div>
		  					<div>
		  						<p>아이디	:${article.d_id}</p>
		  						<p>이름	:${article.d_name}</p>
		  						<p>번호	:${article.d_phone }</p>
		  						<p>이메일	:${article.d_mail}</p>
		  					</div>
		  				</td>
		  			</tr>
		 		 </c:forEach>
			</table>
		  
		
  		</c:if>
    
    </article>
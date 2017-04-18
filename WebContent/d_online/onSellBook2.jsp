<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:forEach var="article"  items="${articleList}" begin="0" end="3">	
		<%-- 불러올내용1가지 --%>
				<article class="chu_part1">
					
					<ul class="Top_banner">
						<li>TOP</li>
						<li>1</li>
					</ul>
					
					<p class="chu_part1_img">
						<c:if test="${article.d_bpic == null}">
							<img src="/DoIt/images/ex_do.jpg">
						</c:if>
						<c:if test="${article.d_bpic != null}">
							<img src="/DoIt/d_bpic/${article.d_bpic}" />
						</c:if>
					</p>
						
					<ul class="chu_part1_cont">
						<li><p>${article.d_bname}</p></li>
						<li><p>저자 : ${article.d_bauthor}</p></li>
						<li><span class="co_red">${article.d_bavgsellvalue}  원</span> </li>
						<li class="sang_button"><a href="/DoIt/d_online/onArticle.do?d_bno=${article.d_bno}">상세보기</a></li>
					</ul>	
				</article>
	</c:forEach>
	


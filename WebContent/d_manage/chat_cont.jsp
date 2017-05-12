<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<c:forEach var="c" items="${ chatList }">
		<article class="mess_wrapa">				          
			<section class="mess_conw">
				<p class="me1"><img src="/DoIt/images/${ c.getThum() }"></p>
				<div class="me2">
					<p>${ c.getD_id() }</p>
					<ul>
						<li class="chat_cont">${ c.getCh_cont() }</li>
						<li class="chat_date">${ c.getCh_date() }</li>
					</ul>
				</div>
			</section>
		</article>  
	</c:forEach>





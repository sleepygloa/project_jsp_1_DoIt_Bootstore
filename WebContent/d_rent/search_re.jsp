<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
    <p class="sear_title">
    	검색하신 분류는 <span>${ jang }</span>, 내용은 "<a>${ word }</a> " 이며, 결과 개수는 <span>${ count }</span>개 입니다.
    </p>
    
	<table class="rent_list" cellspacing="0">
			
			<tbody>
				
				<c:forEach var="c" items="${ articleList }" >
				
				<tr>
					<td>
						<p>${ c.getBr_no() }</p>
					</td>
					<td>
						<p><img src="/DoIt/save/${ c.getBr_thumpic() }" /></p>
					</td>
					<td>
						<a href="/DoIt/d_rent/detail.do?br_no=${ c.getBr_no() }">
							<p>전문서적${ c.getD_bno() }</p>
							<p>${ c.getBr_name() }</p>
							<p>${ c.getBr_sname() }</p>
							<p>${ c.getBr_writer() } (지은이) | [ ${ c.getBr_pub() } ]출판</p>
							<p>${ c.getBr_cont() }</p>
							<p>${ c.getBr_date() }</p>
						</a>
					</td>
					<td>
						<a href="#">장바구니</a>
						<a href="#">도서대출</a>
					</td>
				</tr>
				
				</c:forEach>
				
				
			</tbody>
			
			
			</table>
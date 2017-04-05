<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


			
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
				<p>전문서적${ c.getD_bno() } | ${ c.getBr_code() }</p>
				<p>${ c.getBr_name() }</p>
				<p>${ c.getBr_sname() }</p>
				<p>${ c.getBr_writer() } (지은이) | [ ${ c.getBr_pub() } ]출판</p>
				<p>${ c.getBr_cont() }</p>
				<p>${ c.getBr_date() }</p>
			</a>
		</td>
		<td>
			<a href="/DoIt/d_cart/insertCart.do?start_cart=lib&b_code=${ c.getBr_code() }&view_type=list_cont">장바구니</a>
			<a href="/DoIt/d_cart/insertCart.do?start_cart=lib2&b_code=${ c.getBr_code() }&cols=d_rent">도서대출</a>
		</td>
	</tr>
	
	</c:forEach>
	
	
</tbody>


</table>
			
			
			
			
			
			
			
			
			
			
			
			
			
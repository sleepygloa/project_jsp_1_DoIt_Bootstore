<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


		<%-- 썸네일 스타일 리스트 --%>
		<article class="rent_thum" >

				
				<c:forEach var="c" items="${ articleList }" >
						<div class="thum_li">
							<a href="/DoIt/d_rent/detail.do?br_no=${ c.getBr_no() }">
								<p><img src="/DoIt/save/${ c.getBr_thumpic() }" /></p>
								<p>전문서적 ${ c.getD_bno() } | ${ c.getBr_code() }</p>
								<p>${ c.getBr_name() }</p>
								<p>${ c.getBr_writer() } (지은이) | [ ${ c.getBr_pub() } ]출판</p>
								<p>${ c.getBr_cont() }</p>
								<p>${ c.getBr_date() }</p>
							</a>
							<p class="thum_bu">
								<a href="#">도서대출</a>
								<a href="/DoIt/d_cart/insertCart.do?start_cart=lib&b_code=${ c.getBr_code() }&view_type=thum_cont">장바구니</a>
							</p>
						</div>

					</c:forEach>
		</article>
				
				

			
			
			
			
			
			
			
			
			
			
    

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/DoIt/css/resell.css?">


<!-- header import -->
<jsp:include page="/header.jsp"></jsp:include>
	
<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_resell/side_resell.jsp"></jsp:include>



	<article class="my_cont_wrap">
		
		<p class="my_title">
			판매자 정보
		</p>
		
		<p class="my_sub_title">
			${article.d_id}님의 판매자 정보입니다. 반드시 확인하고 거래를 진행해주세요.
			<span>판매자의 정보를 확인하고 안전한 거래를 진행 할 수 있습니다.</span>
		</p>
		
		
		<%-- 본문 내용 --%>
		<form action="/DoIt/d_resell/sellerList.do" method="post">
		<input type="hidden" name="d_bcode" value="${article.d_no}">
		
			<header class="det_top">
				<%-- 좌측 사진 --%>
				<ul class="det_top_le">
					<li>
						<c:if test="${article.d_pic==null}">
							<a><img src="/DoIt/images/ma_img.jpg" alt=""></a>
	    				</c:if>
	    				<c:if test="${article.d_pic!=null}">
	    					<a><img src="/DoIt/rbook_pic/${article.d_pic}" alt=""></a>	
	    				</c:if>
					</li>
				</ul>
				
				<%-- 우측 내용 --%>
				<ul class="det_top_ri">
					<li>
						<p>판매자 번호 :  ${article.d_no}</p>
						<p>이름 : ${article.d_name }</p>
						<p>판매자 번호 : <span>${article.d_phone }</span></p>
						<p>판매자 주소 : ${article.d_addr }</p>
					</li>
					<li>
						<div class="ri_cont_c1">
							<p>판매자 성별 : ${article.d_gender}</p>
							<p>판매자 메일 : ${article.d_mail}</p>
						</div>
						<div class="ri_cont_c2">
							<p>가입일자 :  ${article.rbook_reg_date }</p>
						</div>
					</li>
					<li class="basong">
						<p>소개글 :  ${article.rbook_intro}</p>
					</li>
				</ul>	
				<ul class="gu_bu">
					<li>
						<input type="submit" value="리스트보기 " class="detail_btn_modi">
					</li>
				</ul>
					
			</header>
			
			
			<%-- 판매중인 다른 책 --%>
			<section class="det_sec">
				<p class="title_cc">
					${article.d_id}님의 판매중인 책 정보 [ 판매중인 책 갯수:${count } ]
				</p>
				
				<%-- 리스트 목록 --%>
				<div class="seller_cont_wrap">
					<c:forEach var="articleList" items="${articleList}">
					<ul>
						<li>
							<p>
								<c:if test="${articleList.rbook_pic==null}">
									<a href="/DoIt/d_resell/reContent.do?rbook_no=${articleList.rbook_no}&pageNum=${currentPage}">
										<img src="/DoIt/images/ma_img.jpg" alt="" >
				    				</a>
				    			</c:if>
				    			<c:if test="${articleList.rbook_pic!=null}">${articleList.rbook_pic}
				    				<a href="/DoIt/d_resell/reContent.do?rbook_no=${articleList.rbook_no}&pageNum=${currentPage}">
				    					<img src="/DoIt/rbook_pic/${articleList.rbook_pic}" alt="" >
				    				</a>
				    			</c:if>
							</p>
						</li>
						<li><p>${articleList.rbook_name}</p></li>
						<li>
							<p>
								<span>${articleList.rbook_price}</span> |
								<span style="color:red"><fmt:formatNumber value="${(articleList.rbook_price2-articleList.rbook_price)/articleList.rbook_price2*100}" pattern="#,##" /></span>%할인
							</p>
						</li>
						<li>
							<p>책상태 :
								<c:if test="${articleList.rbook_bgread==10}"> 최상급</c:if>
								<c:if test="${articleList.rbook_bgread==20}"> 중급</c:if>
								<c:if test="${articleList.rbook_bgread==30}"> 하급</c:if>
							</p>
						</li>
					</ul>
					</c:forEach>
				</div>
	
			</section>
			

		</form>
			
	</article>

	
<!-- footer import  -->
<jsp:include page="/footer.jsp"></jsp:include>




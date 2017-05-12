<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>




	<%----------- 상세보기 시작 --------------------%>
	<article class="my_cont_wrap2">
		<p class="my_title">
			DOIT 도서관 서비스
		</p>
		<p class="my_sub_title">
			<span>도서목록 : 리스트 스타일과 썸네일 스타일을 모두 제공하고 있으므로, 편하신 방법으로 보시기 바랍니다. </span>
		</p>
		
		
		<%------------------ 상단 책 상세 정보 ------------------------%>
		<header class="det_top">
			<%-- 좌측 사진 정보 --%>
			<ul class="det_top_le">
				<li><a href="#"><img src="/DoIt/save/${ rto.getBr_thumpic() }" /></a></li>
				<li><a href="#">크게 보기   |   미리보기</a></li>
				<li><a href="#">도서 재고 ⁿ 도서 위치</a></li>
				<li>
					<p>이 책의 다른 상품 정보</p>
					<p>해당 상품이 없습니다.</p>
				</li>
			</ul>
			
			
			<%-- 우측 도서 정보 --%>
			<ul class="det_top_ri">
				<li>
					<p>전문서적${ rto.getD_bno() }</p>
					<p>${ rto.getBr_name() }</p>
					<p>${ rto.getBr_writer() } (지은이) | ${ rto.getBr_pub() } | ${ rto.getBr_date() } 등록 됨</p>
					<p><img src=""/> 리뷰 <span>${ count }</span>개</p>
					<p>국내 도서 주간베스트 <span>95</span> 위 | 전문서적 <span>95</span> 위</p>	
				</li>
				
				<li>
					<div class="ri_cont_c1">
						<p class="title_f">도서구분 : 도서관 서비스</p>
						<p>판매가 : <span>판매하지 않는 상품입니다.</span></p>
						<p>도서 분류 코드 : ${ rto.getBr_code() }</p>
						<p>도서 현재 대기 순위 : <span>${ personC }</span></p>
						<c:if test="${ personC == 0 }">
							<p>현재 도서를 바로 빌릴 수 있습니다.(0순위)</p>
						</c:if>
						<c:if test="${ personC != 0 && personC < 5 }">
							<p>바로 대여받을 수 없습니다. 대기 순위를 확인하세요.(0순위 부터 시작)</p>
						</c:if>
						<c:if test="${ personC >= 5 }">
							<p>더이상 대여순위를 늘릴 수 없습니다. 대기해 주세요..</p>
						</c:if>
						<p>도서 연체 상태 : 연체상태 아님</p>
						<p>도서 관리자 번호 : 12</p>
					</div>
					
					<div class="ri_cont_c2">
						<p class="title_f">기부자 정보</p>
						<c:if test="${ login_dto != null }">
							<p class="c2_le"><img src="/DoIt/save/${ login_dto.getD_pic() }" /></p>
							<div class="c2_ri">
								<c:if test="${ login_dto.getD_mech_grade() != 10 }">
									<p>기부자 구분 : 개인회원 </p>
								</c:if>
								<c:if test="${ login_dto.getD_mech_grade() == 10 }">
									<p>기부자 구분 : 관리자 등록 </p>
								</c:if>
								<p>기부자 이름 :  ${ login_dto.getD_name() }</p>
								<p>기부자 전화번호 : <br/>${ login_dto.getD_phone() }</p>
							</div>
						</c:if>
						<c:if test="${ login_dto == null }">
							<p class="c2_le"><img src="/DoIt/images/ma_img.jpg" /></p>
							<div class="c2_ri">
								<c:if test="${ login_dto1.getD_mech_grade() != 10 }">
									<p>기부자 구분 : 개인회원 </p>
								</c:if>
								<c:if test="${ login_dto1.getD_mech_grade() == 10 }">
									<p>기부자 구분 : 관리자 등록 </p>
								</c:if>
								<p>기부자 이름 :  ${ login_dto1.getD_name() }</p>
								<p>기부자 전화번호 : <br/>${ login_dto1.getD_phone() }</p>
							</div>
						</c:if>
					</div>
					
				</li>
				
				<li class="basong">
					<p>배송비  : 무료</p>
					<p>* 배송비는 무료이지만, 연체시 연체료를 내야합니다.</p>
					<p class="basong2">
						<a>도착 예정일 :   </a>
						<a> 
							<span>서울 특별시 강남구 역삼동 KH Academy 기준</span>
							<span><span style="diplay:inline-block; color:#3774B1; font-weight:bold">대여일로 부터 2일 이내 발송</span></span>
						</a>
					</p>
					<p>직접 수령 : 도서관에서 직접수령 가능합니다.</p>
				</li>
				
			</ul>
			
			<ul class="gu_bu">
				<li><a href="#">도서대여 하기</a></li>
				<li><a href="#">장바구니 넣기</a></li>
			</ul>
			
			
		</header>
		
		<section class="det_sec">
			<article class="sang_gu">
				<img src="/DoIt/images/sang_gu.JPG" />
			</article>	
			
			<%-- 기부자가 같은 사람의 다른 책 --%>
			<p class="title_cc">이책을 기부한 사람의 다른 기부도서 목록</p>
			<article class="gi_list_book">
				<%-- 도서 목록 전체 마스크 --%>
					
					<%-- 이전 버튼 --%>
					<p class="gi_prev"><img src="/DoIt/images/btn_prev.gif" /></p>
					
					<%-- 도서 목록 내용 마스크 --%>
					<div class="list_book_wrap">
					
						<%-- 도서의 실제 목록 --%>
						<div class="list_book_cont">
							
							<c:forEach var="qw"  items="${ gibuList }">
								<%-- 도서 내용 한가지 --%>
								<ul class="book_cont_ul">
									<li><a href="/DoIt/d_rent/detail.do?br_no=${ qw.getBr_no() }"><img src="/DoIt/save/${ qw.getBr_thumpic() }" /></a></li>
									<li>${ qw.getBr_name() }</li>
									<c:if test="${ login_dto == null }">
										<li>${ login_dto1.getD_name() }</li>
									</c:if>
									<c:if test="${ login_dto != null }">
										<li>${ login_dto.getD_name() }</li>
									</c:if>
								</ul>
							</c:forEach>

						</div>
					</div>
					
					<%-- 다음 버튼 --%>
					<p class="gi_next"><img src="/DoIt/images/btn_next.gif" /></p>
			</article>		
			
			<%-- 책 정보 --%>
			<article class="book_cont_wrap">
				<%-- 좌측 책 정보 출력 --%>
				<article class="book_cont1">
					<%-- 도서 상단 탭 --%>
					<ul class="top_tab">
						<li><a>도서 정보</a></li>
						<li><a>도서 리뷰</a></li>
						<li><a>반품 / 교환 / 품절안내</a></li>
					</ul>

					
					<%-- 도서 이벤트 내용  --%>
					<p class="mid_title">
						<a>이 책의 이벤트</a>
						<span>해외 주문 / 바로 드림 / 제휴사 주문 / 업체 배송건의 경우 1+1 증정상품이 발송되지 않습니다.</span>
					</p>
					
					<ul class="b_event">
						<li><a href="#"><img src="/DoIt/images/bnEB_31.jpg" /></a></li>
						<li>
							<p>한국의 1000원짜리 땅부자들<br>+ 책속 강연회 초대권</p>
							<p>2017. 03. 16 ~ 2017. 05. 31</p>
						</li>
						<li><a href="#" ><img src="/DoIt/images/bnV_04.jpg" /></a></li>
						<li>
							<p>이달의 추천도서 구매시 <br>+ 페이퍼 펜트레이 Set 증정</p>
							<p>2017. 03. 16 ~ 2017. 05. 31</p>
						</li>
					</ul>
					<ul class="b_event">
						<li><a href="" ><img src="/DoIt/images/bnEB_15.jpg" /></a></li>
						<li>
							<p>2017해커스매거진  <br>+ 스펙3종 무료제공 이벤트</p>
							<p>2017. 03. 16 ~ 2017. 05. 31</p>
						</li>
						<li><a href="" ><img src="/DoIt/images/bnF_w04.jpg" /></a></li>
						<li>
							<p>취업 교재 구매시 사은품  <br>+ 7종 증정(선택가능)</p>
							<p>2017. 03. 16 ~ 2017. 05. 31</p>
						</li>
					</ul>
					
					<%-- 도서 상단 탭 --%>
					<ul class="top_tab">
						<li><a id="de_cont">도서 정보</a></li>
						<li><a id="de_review">도서 리뷰</a></li>
						<li><a id="de_return">반품 / 교환 / 품절안내</a></li>
					</ul>
					
					<article class="det_bot">
						<%-- 도서 내용 or 리뷰 or 반품  include --%>
						<jsp:include page="det_cont.jsp"></jsp:include>
					</article>
					
					<article class="det_bot2">
						<%-- 도서 내용 or 리뷰 or 반품  include --%>
						<jsp:include page="det_review.jsp" ></jsp:include>
					</article>
					
					<article class="det_bot3">
						<%-- 도서 내용 or 리뷰 or 반품  include --%>
						<jsp:include page="det_cont.jsp" ></jsp:include>
					</article>

					
					
				</article>
				
				<%-- 우측 다른 책 추천 파트 --%>
				<article class="book_cont2">
					<%-- 우측 책 이벤트 사진 --%>
					<header class="book_ri_he">
						<img src="/DoIt/images/sdddddd.jpg" />
					</header>
					
					<%-- 우측 책 다른 책 추천 --%>
					<section>
						<%-- 우측 다른책 추천1 --%>
						<article class="book_ri_1">
							<p class="title_cc2">장르가 같은 추천도서</p>
							
								<c:forEach var="aa" items="${ jangList }">
								<ul class="book1">
									<a href="/DoIt/d_rent/detail.do?br_no=${ aa.br_no }">
										<li><img src="/DoIt/save/${ aa.br_thumpic }"/></li>
										<p>
											<li>${ aa.br_name }</li>
											<li>${ aa.br_writer }</li>
											<li>전공서적 ${ aa.d_bno }</li>
										<p>
									</a>
								</ul>
								</c:forEach>
							
						</article>
						
						<%-- 우측 다른책 추천2 --%>
						
						
					</section>
				</article>
			</article>
			
			
		</section>
		
	</article>
	
	
	
	
	
	
	
	
	
	
	
	
	
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>		
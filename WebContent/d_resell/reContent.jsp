<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script type="text/javascript" src="/DoIt/js/script.js"></script>

<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>


<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_resell/side_resell.jsp"></jsp:include>


	<%-- 본문 처리 내용 --%>
	<article class="my_cont_wrap">
	
		<p class="my_title">
			중고 직거래
		</p>
		
		<p class="my_sub_title"></p>	
		
		
		
		<%-- 상단 제목 내용 wrap --%>
		<div class="cont_title_wrap">
			
			<div class="title_but">	
				<c:if test="${ sessionScope.memId == null }">
					<p class="fl_ri ri_ti" onclick="board_se()">신고하기</p>
				</c:if>
				<c:if test="${ sessionScope.memId != null }" >
					<p class="fl_ri ri_ti" onclick="review_go()">신고하기</p>
				</c:if>
			</div>
					
			<%-- 신고하기 입력 폼 --%>		
			<form action="/DoIt/d_resell/reReport.do" method="POST" >
			<input type="hidden" name="rbook_no" value="${article.rbook_no }"/>	
			<input type="hidden" name="report_id2" value="${article.rbook_id }"/>	
				<table id="re_table" cellspacing="0">
					<colgroup>
						<col width="60%"><col width="20%"><col width="20%">
					</colgroup>
					<tbody>
						<tr>
							<td colspan="2">
								<textarea name="report_content" placeholder="신고사유를 입력하세요..." rows="5" cols="90" maxlength="120"></textarea>
							</td>
							<td>
								<p class="write_bu2"><button type="submit" >신고하기</button></p>
							</td>	
						</tr>
						<tr>
							<td></td>
							<td>280byte (한글 140자 이내)</td>
							<td></td>
						</tr>
	
					</tbody>
							
				</table>
			</form>
			
			<%-- 좌측 사진 내용 --%>
			<div id="reContent_boxleft">
				<c:if test="${article.rbook_pic == null}">
		   			<img src="/DoIt/images/ma_img.jpg">
				</c:if>
				<c:if test="${article.rbook_pic != null }">
		   			<img src="/DoIt/rbook_pic/${article.rbook_pic}">
				</c:if>
			</div>
			
			
			<%-- 우측 제목 입력 --%>
			<div id="reContent_boxright">
				<ul class="top_con1">
					<li>
						<c:if test="${article.rbook_sell_check == 0}">
							<span class="reContent_sell">판매중</span>
						</c:if>
						<c:if test="${article.rbook_sell_check == 1}">
							<span class="reContent_sell">판매완료</span>
						</c:if>				
					</li>
					<li><a>장르번호 : ${article.rbook_subject }</a></li>
					<li><p>[ 직거래 상품 ] ${article.rbook_name}</p></li>
					<li><span>저자 : ${article.rbook_writer} | 출판사 : ${article.rbook_company} </span></li>
					<li>댓글개수 : <span>${count}</span></li>
					<li class="reCoon"><img src="/DoIt/images/reCon.PNG" ></li>
				</ul>
			</div>
			
			<div id="reContent_boxright2">
				<ul class="cont_top2">
					<li><a>정가 : </a><span style="text-decoration:line-through; color:#5CD1E5; font-size:17px;">${article.rbook_price2}</span></li>
					<li>
						<a>판매가 : </a><span class="content_price">${article.rbook_price}</span>
						<span><fmt:formatNumber value="${(article.rbook_price2-article.rbook_price)/article.rbook_price2*100}" pattern="#,##" />% ↓
						${article.rbook_price2 - article.rbook_price}원 할인</span>
					</li>
					<li>
						<%--(정가-판매가)/정가*100=할인율 --%>
						<%-- <p class="content_bgread">제품 상태 : ${article.rbook_bgread}</p>--%>
						<c:if test="${article.rbook_bgread==10 }">
							<p class="content_bgread"><a>제품상태 : </a><span>최상급</span></p>
						</c:if>
						<c:if test="${article.rbook_bgread==20 }">
							<p class="content_bgread"><a>제품상태 : </a><span>중급</span></p>
						</c:if>
						<c:if test="${article.rbook_bgread==30 }">
							<p class="content_bgread"><a>제품상태 : </a><span>하급</span></p>
						</c:if>
					</li>
					<li><a>판매자 : </a><span>${article.rbook_id}</span></li>
					<li><a>거래방법 : </a><span>택배거래</span></li>
					<li><a>배송방법 : </a><span>판매자와 직접 연락하세요.</span></li>
				</ul>
				
				<p class="warning"> 
					DOIT에 등록된 판매 물품과 내용은 개별 판매자가 등록한 것으로서, <br>
					DOIT은 등록을 위한 시스템만 제공하며 내용에 대한 일체의 책임을 지지 않습니다. 
				</p>

				
				</div>
				
				
				<div class="admin_butt">
					<c:if test="${id.equals(article.rbook_id) || articlea.d_mech_grade == 10}">
						<input class="reConten_btn" type="button" value="글수정" 
						onclick="document.location.href='/DoIt/d_resell/reUpdate.do?rbook_no=${article.rbook_no}&pageNum=${pageNum}'"/>
						<input class="reConten_btn" type="button" value="글삭제" 
						onclick="document.location.href='/DoIt/d_resell/reDelete.do?rbook_no=${article.rbook_no}&pageNum=${pageNum}'"/>
						<c:if test="${article.rbook_sell_check==0 }">
							<input class="reConten_btn" type="button" value="판매완료" 
						onclick="document.location.href='/DoIt/d_resell/reFinishPro.do?rbook_no=${article.rbook_no}&pageNum=${pageNum}'"/>
						</c:if>	
					</c:if>		
					<c:if test="${!id.equals(article.rbook_id) || articlea.d_mech_grade != 10 || article.rbook_sell_check==0 }">
						<p>
							■ 직접거래시 아래 사항에 유의해 주세요.
						</p>
						<p>
							불확실한 판매자(본인 미인증, 해외 IP..)의 물건은 구매하지 말아주세요.<br>
							판매자와의 연락은 메신저보다는 전화, 메일을 이용하시고 개인정보 유출에 주의하세요. <br>
							계좌이체 시 선입금을 유도할 경우 안전한 거래인지 다시 확인해주세요.
						</p>
					</c:if>
				</div>
				
		</div>
			
			
			
			
	    <div id="reContent_boxbottom">
	    	<p class="content_title">상세정보</p>
	    	<p class="content_more">${article.rbook_content}</p>
	    </div>
	    
	    
		<section class="">
			<p style="margin-bottom:10px;">댓글 : ${count} 개</p>
			
			<table class="review_list">
			<colgroup>
				<col width="15%"><col width="75%"><col width="10%">
			</colgroup>
				<tbody>
				<c:forEach var="rer" items="${list}">		
					<tr>
						<td>
							<p><img src="/DoIt/images/ma_img.jpg" ></p>
						</td>
						<td>
							
							<p>${rer.rerbook_writer}</p>
							<p>${rer.rerbook_content}</p>
							<p>${rer.rerbook_reg_date}</p>
						</td>
						<td>
							<c:if test="${id.equals(rer.rerbook_writer)}">
								<a onclick="document.location.href='/DoIt/d_resell/reReplyDelete.do?rerbook_rnum=${rer.rerbook_rnum}&rbook_no=${article.rbook_no}&pageNum=${pageNum}'"
								class="x_button" ></a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="center">
				<form action="reReplyPro.do" >
					<c:if test="${article.rbook_sell_check == 0}">
						<textarea rows="2" cols="50" name="rerbook_content" class="reply_textarea"></textarea>
						<input type="hidden" name="rerbook_bnum" value="${article.rbook_no }"/>
						<input type="hidden" name="pageNum" value="${pageNum}"/>
						<input type="hidden" name="rbook_no" value="${article.rbook_no}"/>
						<c:if test="${id !=null}">
							<input class="reply_btn" type="submit" value="입력"/>
						</c:if>
						<c:if test="${id ==null}">
							<input class="reply_btn" type="button" onclick="document.location.href='/DoIt/d_login/login.do'" value="로그인 후 이용가능"/>
						</c:if>
					</c:if>
					<c:if test="${article.rbook_sell_check != 0}">
						판매완료되었습니다.
					</c:if>
				</form>
			</div>

		</section>
		
		
	</article>   
	
	
	
	
	
	
	
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>
 
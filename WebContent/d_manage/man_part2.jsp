<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--------------- header include --------------%>
<jsp:include page="ma_header.jsp"></jsp:include>


<%-- 전체 내용 wrap --%>
<section class="mana_wrap_a">

   <%--------------- 사이드 메뉴 include width: height:1537px --------------%>
   <jsp:include page="d_mana_side.jsp"></jsp:include>

   
   <%-- 본문 내용 height:1530px; --%>
   <article class="dash_wrap">   
      
      <%-- 본문 내용 정렬 : width:100%;  --%>
      <article class="dash_wrap_cont">
         
         <%-- 공동 nav 불러오기 --%>
         <jsp:include page="/d_manage/cont_header.jsp"></jsp:include>
         
         
         <%-- section 속에 아티클 단위로 입력해서 넣으시면 됩니다. 사이즈 : 자동 끝--%>
         <section class="warp_cond">
         
            <%-- 승인 대기 중인 기증 도서 --%>
            <article class="black_Li3" style="height:400px; margin-top:0px;">
               
               <%-- 각 파트 제목 --%>
               <div style="overflow:hidden;">
                  <p class="mana_Stitle">기부도서 대기 리스트</p>
                  <c:if test="${ gua23 == 0 }">
                  	<p class="butts" onclick="/DoIt/d_manage/manPart2.do?pack=0">전체 승인</p>
                  </c:if>
               </div>
               
               <ul class="sort_top">
					<li><a href="/DoIt/d_manage/manPart2.do">도서대기상태</a></li>
					<li><a href="/DoIt/d_manage/manPart2.do?gua=1">도서승인완료</a></li>
					<li><a href="/DoIt/d_manage/manPart2.do?gua=3">폐기도서목록</a></li>
				</ul>
               
               <%-- 연체자 리스트 --%>
               <table class="list_con_wrap2" cellspacing="0">
               <colgroup>
                  <col width="2%"><col width="3%"><col width="3%"><col width="4%">
                  <col width="10%"><col width="4%"><col width="4%"><col width="2%"><col width="3%">
                  <col width="4%"><col width="4%"><col width="5%">
               </colgroup>
                  <thead>
                     <tr>
                        <th>번호</th>
                        <th>고유코드</th>
                        <th>이미지</th>
                        <th>도서 이름</th>
                        <th class="Left_ali">소제목</th>
                        <th>출판사</th>
                        <th>저자</th>
                        <th>장르</th>
                        <th>기부자</th>
                        <th>기부날짜</th>
                        <th>기부상태</th>
                        <th>제어</th>
                     </tr>
                  </thead>
                  <tbody>
                  	<c:if test="${ articleList2 == null }">
                  		<tr>
                  			<td colspan="12">내용이 없습니다.</td>
                  		</tr>
                  	</c:if>
                  	
                  	<c:if test="${ articleList2 != null }">
                     <c:forEach var="c2" items="${ articleList2 }">
                     <tr>
                        <td><a>${ c2.br_no}</a></td>
                        <td><a>${ c2.br_code }</a></td>
                        <td><a><img src="/DoIt/images/ma_img.jpg" /></a></td>
                        <td><a>${ c2.br_name }</a></td>
                        <td class="Left_ali"><a>${ c2.br_sname }</a></td>
                        <td><a>${ c2.br_pub }</a></td>
                        <td><a>${ c2.br_writer}</a></td>
                        <td><a>${ c2.d_bno }</a></td>
                        <td><a>${ c2.br_don }</a></td>
                        <td><a>${ c2.br_date }</a></td>
                        
                        <c:if test="${ c2.br_wait == 0 }" >
                           <td><a>기부 대기중</a></td>
                        </c:if>
                        <c:if test="${ c2.br_wait == 1 }" >
                           <td><a>기부 완료</a></td>
                        </c:if>
                         <c:if test="${ c2.br_wait == 3 }" >
                           <td><a>폐기처리됨</a></td>
                        </c:if>
                       
                        <td>
                        	<p class="write_bu">
                        	<c:if test="${ c2.br_wait == 0 }"><%-- 승인대기상태일때 --%>
                        		<a href="/DoIt/d_manage/manPart2.do?pack=1&br_code=${ c2.br_code }">승인완료</a>
                        	</c:if>
                        	<c:if test="${ c2.br_wait == 1 }"><%-- 승인대기상태일때 --%>
                        		<a href="/DoIt/d_manage/manPart2.do?pack=3&br_code=${ c2.br_code }">폐기처리</a>
                        	</c:if>
                        	<c:if test="${ c2.br_wait == 3 }"><%-- 승인대기상태일때 --%>
                        		<a>폐기상태</a>
                        	</c:if>
                        	</p>
                        </td>
                     </tr>
                     </c:forEach>
                     </c:if>
                  </tbody>
            </table>
            
            
            <%-- 페이지 네이션 --%>
		      <c:if test="${count2>0 }">
		      <p class="num_tag">
		         <c:set var="pageCount2" 
		         value="${count2 / pageSize2 + ( count2 % pageSize2 == 0 ? 0 : 1) }" />
		         <c:set var="pageBlock" value="${10}"/>
		         <fmt:parseNumber var="result" value="${currentPage2 / 10}" integerOnly="true" />
		         <c:set var="startPage" value="${result * 10 + 1}" />
		         <c:set var="endPage" value="${startPage + pageBlock-1}"/>
		              <c:if test="${endPage > pageCount2}">
		                 <c:set var="endPage" value="${pageCount2}"/>
		            </c:if>
		            
		              <c:if test="${ startPage>10 }">
		                 <a href="/DoIt/d_manage/manPart2.do?pageNum2=${startPage2 - 10 }">[ 이전 ]</a>
		            </c:if>
		            <c:forEach var="i" begin="${ startPage}" end="${endPage}">
		                 <a href="/DoIt/d_manage/manPart2.do?pageNum2=${i}">[ ${ i } ]</a>
		            </c:forEach>
		            <c:if test="${endPage < pageCount }">
		                 <a href="/DoIt/d_manage/manPart2.do?pageNum2=${startPage2 + 10}">[ 다음 ]</a>
		            </c:if>
		            
		         </p>
		         </c:if>
            </article>
            <%-- 승인 대기 중인 기증 도서 --%>
            

            
            <%-- 판매자 권한 승인 요청 대기 --%>
            <article class="black_Li3 asd234" style="height:700px;">
               
               <jsp:include page="/d_admin/waitOkForm.jsp"></jsp:include>
               
            </article>
            
         </section>
         <%-- section 속에 아티클 단위로 입력해서 넣으시면 됩니다. 사이즈 : 자동 끝--%>
         
         
      </article>
         
   </article>

   
</section>


<%--------------- footer include --------------%>
<jsp:include page="ma_footer.jsp"></jsp:include>

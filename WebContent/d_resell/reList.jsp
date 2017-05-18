<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.text.SimpleDateFormat" %>


<script type="text/javascript" src="/DoIt/js/script.js"></script>	

	<!-- header import -->
	<jsp:include page="/header.jsp"></jsp:include>
		
	<%--------------- 사이드 메뉴 include --------------%>
	<jsp:include page="/d_resell/side_resell.jsp"></jsp:include>
		

		<!-------------------- 게시판리스트 ------------------------------------ -->
		<article class="my_cont_wrap">	
		
			
			<p class="my_title">중고 직거래</p>
			<p class="my_sub_title">
				중고 직거래 게시판 입니다.
			</p>
		
		
			<%-- 개인 판매자 정보 --%>
			<ul class="sort_top">
				<li><a>글목록(전체글:${count})</a></li>
				<li class="rig1">
					<p><a href="/DoIt/d_resell/reList.do">전체보기</a></p>
					<p><a href="/DoIt/d_resell/reList.do?rbook_sell_check=0">판매중</a></p>
					<p><a href="/DoIt/d_resell/reList.do?rbook_sell_check=1">판매완료</a></p>
				</li>
			</ul>
			
			
			<%-- 게시글이 없을 경우 --%>
			<c:if test="${count == 0}">
				<table width="100%" cellspacing="0" class="rent_list">
					<tr>
						<td colspan="4">게시판에 저장된 글이 없습니다.</td>
					</tr>
				</table>
			</c:if>
			
			
			<%-- 게시글이 있을 경우 --%>
			<c:if test="${count > 0}">
			<table class="rent_list" cellspacing="0">
				<tbody>
					<c:forEach var="article" items="${articleList}">
						<tr style="overflow:hidden;">
							
							<%-- 번호 --%>
							<td style="position:relative;">
								<c:out value="${number}"/>
			    				<c:set var="number" value="${number-1}"/>
			    				
			    				<%-- 판매 완료 되었을 경우 --%>
								<c:if test="${article.rbook_sell_check == 1}">
									<div id="finish_box">
										<div class="finish_box2">
											<a href="/DoIt/d_resell/reContent.do?rbook_no=${article.rbook_no }&pageNum=${currentPage}">
												<p class="finish_text">판매완료</p>
											</a>
										</div>
									</div>
								</c:if>
								
			    			</td>
			    			
			    			<%-- 사진 --%>
							<td>
								<c:if test="${article.rbook_pic == null}">
	   								<p><img src="/DoIt/images/mess_x.png" ></p>
				    			</c:if>
				    			<c:if test="${article.rbook_pic != null }">
		   							<!-- <a href="/jsp/save/${article.rbook_pic }"><font color="red">${resell.rbook_pic }</font></a> -->
		   							<a href="/DoIt/d_resell/reContent.do?rbook_no=${article.rbook_no }&pageNum=${currentPage}">
		   								<p><img src="/DoIt/rbook_pic/${article.rbook_pic}" ></p>
		   							</a>
				    			</c:if>
							</td>
							
							<%-- 내용 --%>
							
							<td>
								<a href="/DoIt/d_resell/reContent.do?rbook_no=${article.rbook_no }&pageNum=${currentPage}">
									<p></p>
									<p style="color:#3774B1">${article.rbook_subject}(${article.rbook_name})</p>
									<p>${article.rbook_company} | ${article.rbook_writer }</p>
									<p>
										<span>정가 : ${article.rbook_price2} --> 판매가 : ${article.rbook_price} </span>
							    		<span>할인가 : ${article.rbook_price2 - article.rbook_price}원</span> 
							    		<span>
											[<fmt:formatNumber value="${(article.rbook_price2-article.rbook_price)/article.rbook_price2*100}" pattern="#,##" />%할인]		    		
							    		</span>
									</p>
									<p>판매자 : ${article.rbook_id} [  
<<<<<<< HEAD
							    		<c:if test="${article.rbook_finish_count <=3}">
=======
							    		<c:if test="${0<= article.rbook_finish_count  && article.rbook_finish_count <=3}">
>>>>>>> 06e83cdc0212d155692e1e75dda189dd861591c1
							    		 	씨앗등급
							    		</c:if>
							    		<c:if test="${4<= article.rbook_finish_count  && article.rbook_finish_count <=7}">
							    		 	새싹등급
							    		</c:if>
							    		<c:if test="${8<= article.rbook_finish_count  && article.rbook_finish_count <=11}">
							    		 	실버등급
							    		</c:if>
							    		<c:if test="${12<= article.rbook_finish_count }">
							    		 	골드등급
							    		</c:if>
							    		 ]
					    		 	</p>
					    		 	<p>판매여부 : 
						    			<c:if test="${article.rbook_sell_check == 0}">
											<span>판매중</span>
										</c:if>
										<c:if test="${article.rbook_sell_check == 1}">
											<span>판매완료</span>
										</c:if>
									</p>
									<p>등록일 : ${article.rbook_reg_date}</p>
									
								</a>
							</td>
							
							<%-- 스크랩 버튼 --%>
							<td>
								<form action="reScarp.do">
					    			<%--input type="hidden" name="rbook_no" value="${article.rbook_no}"/--%>
					    			<%--input type="hidden" name="pageNum" value="${currentPage}"/ --%>
					    			<c:if test="${sessionScope.memId != null }">
					    				
					    				<c:if test="${!id.equals(article.rbook_id)}">
					    					<a onclick="document.location.href='/DoIt/d_resell/reScrap.do?rbook_no=${article.rbook_no}&pageNum=${currentPage}&scrap_id=${id}'">
					    						관심상품담기
					    					</a>
					    				</c:if>
					    			</c:if>
					    			<c:if test="${sessionScope.memId == null }">
					    				<a onclick="reList_scrap()">관심상품담기</a>
					    				
					    			</c:if>
					    		</form>

							</td>
							
						</tr>
						
						
						
					</c:forEach>
				</tbody>
			</table>  		
	  		
	  		</c:if>
	  		
	  		
	  		
	  		<%-- 글쓰기 버튼 권한... --%>
	  			<form id="reList_wbtn">
	  			
			    	<c:if test="${sessionScope.memId == null }">
			    			<input type="button" onclick="reList_click()" value="글쓰기"/>
			    	</c:if>
			    	<%-- <c:if test="${sessionScope.memId != null && ${article.d_mech_grade == 2 }">--%>
			    	<c:if test="${sessionScope.memId != null}">
			    		<c:if test="${dto.d_mech_grade == 2 }">
			    			<c:if test="${0<= finish.rbook_finish_count  && finish.rbook_finish_count <=3}">
<<<<<<< HEAD
			    				<c:if test="${4<=sellerCount }">
=======
			    				<c:if test="${4<sellerCount }">
>>>>>>> 06e83cdc0212d155692e1e75dda189dd861591c1
			    					<input type="button" onclick="reList_WriterClick()"  value="글쓰기"/>
			    				</c:if>
			    				<c:if test="${sellerCount<=3 }">
			    					<input type="button" onclick="document.location.href='/DoIt/d_resell/reWriteForm.do'"  value="글쓰기"/>
			    				</c:if>
			    			</c:if>
			    			<c:if test="${4<= finish.rbook_finish_count  && finish.rbook_finish_count <=7}">
				    		 	<c:if test="${7<sellerCount }">
			    					<input type="button" onclick="reList_WriterClick()"  value="글쓰기"/>
			    				</c:if>
			    				<c:if test="${sellerCount<=6 }">
			    					<input type="button" onclick="document.location.href='/DoIt/d_resell/reWriteForm.do'"  value="글쓰기"/>
			    				</c:if>
				    		</c:if>
				    		<c:if test="${8<= finish.rbook_finish_count  && finish.rbook_finish_count <=11}">
			    				<c:if test="${10<sellerCount }">
			    					<input type="button" onclick="reList_WriterClick()"  value="글쓰기"/>
			    				</c:if>
			    				<c:if test="${sellerCount<=9 }">
			    					<input type="button" onclick="document.location.href='/DoIt/d_resell/reWriteForm.do'"  value="글쓰기"/>
			    				</c:if>
			    			</c:if>
			    			<c:if test="${12<= finish.rbook_finish_count}">
				    		 	<c:if test="${16<sellerCount }">
			    					<input type="button" onclick="reList_WriterClick()"  value="글쓰기"/>
			    				</c:if>
			    				<c:if test="${sellerCount<=15 }">
			    					<input type="button" onclick="document.location.href='/DoIt/d_resell/reWriteForm.do'"  value="글쓰기"/>
			    				</c:if>
				    		</c:if>
			    			
			    		</c:if>
			    		<c:if test="${dto.d_mech_grade == 0}">
			    			 <input type="button" onclick="reList_click2()"  value="글쓰기"/>
			    		</c:if>
			    		<c:if test="${dto.d_mech_grade == 1}">
			    			 <input type="button" onclick="reList_click3()"  value="글쓰기"/>
			    		</c:if>
			    		
			    	</c:if>
			    </form>
			    
			    
			<%-- 검색 입력 창 --%>
			<form action="/DoIt/d_resell/reList.do" method="POST" class="search_bar">
				<p>
					<a class="select_box">
						<select name="searchTitle" >
							<option value="rbook_name" >책제목</option>
							<option value="rbook_writer" >저자</option>
							<option value="rbook_company" >출판사</option>
						</select>
					</a>
					<a><input type="text" name="search"/></a>
					<a><button type="submit" >검색</button></a>
				</p>
			</form>
	   		
				
	  		<%-- 댓글이 있을 경우 페이지네이션 --%>
		  	<c:if test="${count > 0}">
			   <c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
			   <c:set var="pageBlock" value="${10}"/>
			   <fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
			   <c:set var="startPage" value="${result * 10 + 1}" />
			   <c:set var="endPage" value="${startPage + pageBlock-1}"/>
			   <c:if test="${endPage > pageCount}">
			        <c:set var="endPage" value="${pageCount}"/>
			   </c:if> 
			   
			   
			 <p class="num_tag">         
			   <c:if test="${startPage > 10}">
			        <a href="/DoIt/d_resell/reList.do?pageNum=${startPage - 10 }">[ 이전 ]</a>
			   </c:if>

				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="/DoIt/d_resell/reList.do?pageNum=${i}">[ ${i} ]</a>
				</c:forEach>
				
			   	<c:if test="${endPage < pageCount}">
			        <a href="/DoIt/d_resell/reList.do?pageNum=${startPage + 10}">[ 다음 ]</a>
			   	</c:if>
			  </p> 
			  
			</c:if>
			
			

	   		
		 </article>
		 
		 
		 
		 
	<!-- footer import  -->
	<jsp:include page="/footer.jsp"></jsp:include>	
		
		
		
		
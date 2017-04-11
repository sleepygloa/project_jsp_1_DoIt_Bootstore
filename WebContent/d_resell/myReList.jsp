<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_login/side_my.jsp"></jsp:include>
    <article class="my_cont_wrap">
    
    	<p class="my_title">나의직거래 목록</p>
			<p class="my_sub_title">
				직거래 목록입니다. 개인 등급과 권한을 확인하세요.
				<span></span>
			</p>
		
		
			<ul class="sort_top">
				<li><a>글목록(전체글:${count})</a></li>
			</ul>
			
			
			<%-- 게시글이 없을 경우 --%>
			<c:if test="${count == 0}">
				<table width="100%" cellspacing="0" class="rent_list">
					<tr>
						<td colspan="4">저장된 글이 없습니다.</td>
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
								
									<p>${article.rbook_subject}(${article.rbook_name})</p>
									<p>${article.rbook_company} | ${article.rbook_writer }</p>
									<p>
										<span>정가 : ${article.rbook_price2} --> 판매가 : ${article.rbook_price} </span>
							    		<span>할인가 : ${article.rbook_price2 - article.rbook_price}원</span> 
							    		<span>
											[<fmt:formatNumber value="${(article.rbook_price2-article.rbook_price)/article.rbook_price2*100}" pattern="#,##" />%할인]		    		
							    		</span>
									</p>
									<p>판매자 : ${article.rbook_id} [  
							    		<c:if test="${0<= article.rbook_finish_check  && article.rbook_finish_check <=3}">
							    		 	씨앗등급
							    		</c:if>
							    		<c:if test="${4<= article.rbook_finish_check  && article.rbook_finish_check <=7}">
							    		 	새싹등급
							    		</c:if>
							    		<c:if test="${8<= article.rbook_finish_check  && article.rbook_finish_check <=11}">
							    		 	실버등급
							    		</c:if>
							    		<c:if test="${12<= article.rbook_finish_check  && article.rbook_finish_check <=15}">
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
			    				<c:if test="${4<sellerCount }">
			    					<input type="button" onclick="reList_WriterClick()"  value="글쓰기"/>
			    				</c:if>
			    				<c:if test="${sellerCount<=3 }">
			    					<input type="button" onclick="document.location.href='/DoIt/d_resell/reWriteForm.do'"  value="글쓰기"/>
			    				</c:if>
			    			</c:if>
			    			<c:if test="${4<= article.rbook_finish_check  && article.rbook_finish_check <=7}">
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
			    			<c:if test="${12<= article.rbook_finish_check  && article.rbook_finish_check <=15}">
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
			<form action="/DoIt/d_resell/myReList.do" class="search_bar">
				<p>	
		   			<a><input type="text" name="search" /></a>
		   			<a><button type="submit">검색</button></a>
		   		</p>
	   		</form>
	   		
				
	  		<%--페이지네이션 --%>
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
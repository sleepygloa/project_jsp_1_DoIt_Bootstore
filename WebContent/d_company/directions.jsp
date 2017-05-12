<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.text.SimpleDateFormat" %>

<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="/DoIt/js/script.js"></script>
<script type="text/javascript" src="/DoIt/js/faq.js"></script>	
<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=3165679047f1165b60b89fae318b1210&libraries=services"></script>

	<!-- header import -->
	<jsp:include page="/header.jsp"></jsp:include>
		
	<%--------------- 사이드 메뉴 include --------------%>
	<jsp:include page="/d_company/side_company.jsp"></jsp:include>
		

		<!-------------------- 게시판리스트 ------------------------------------ -->
		<article class="my_cont_wrap">
			
			<p class="my_title">오시는길</p>
			<p class="my_sub_title">
				DOITBOOK의 위치 입니다.
			</p>
			
			
			
			<div id="map" style="width:800px;height:350px;"></div>

			
			<script>
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				    mapOption = {
				        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
				        level: 3 // 지도의 확대 레벨
				    };  
				
				// 지도를 생성합니다    
				var map = new daum.maps.Map(mapContainer, mapOption); 
				
				// 주소-좌표 변환 객체를 생성합니다
				var geocoder = new daum.maps.services.Geocoder();
				
				// 주소로 좌표를 검색합니다
				geocoder.addr2coord('${com.com_addr}', function(status, result) {
				
				    // 정상적으로 검색이 완료됐으면 
				     if (status === daum.maps.services.Status.OK) {
				
				        var coords = new daum.maps.LatLng(result.addr[0].lat, result.addr[0].lng);
				
				        // 결과값으로 받은 위치를 마커로 표시합니다
				        var marker = new daum.maps.Marker({
				            map: map,
				            position: coords
				        });
				
				        // 인포윈도우로 장소에 대한 설명을 표시합니다
				        var infowindow = new daum.maps.InfoWindow({
				            content: '<div style="width:150px;text-align:center;padding:6px 0;">DoItBook</div>'
				        });
				        infowindow.open(map, marker);
				
				        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				        map.setCenter(coords);
				    } 
				});    
			</script>
			<div>
				<p class="addr_title">주소</p>
				<div class="map_addr">
					<p>
						<span>Address</span>
						${com.com_addr}
					</p>
					
				</div>
				<div class="map_addr2">
					<c:if test="${dto.d_mech_grade==10 }">
						<div>
							<p class="fl_ri ri_ti" onclick="review_go()">주소 수정 하기</p>
						</div>
						<form action="/DoIt/d_company/directionsUpdate.do" method="POST" >
						<input type="hidden" name="com_writer" value="${id}"/>	
							<table id="re_table" cellspacing="0">
								<colgroup>
									<col width="60%"><col width="20%"><col width="20%">
								</colgroup>
								<tbody>
									<tr>
										<td colspan="2">
											<textarea name="com_addr" placeholder="주소를 입력하세요" rows="2" cols="90" maxlength="120"></textarea>
										</td>
										<td>
											<p class="write_bu2"><button type="submit" >수정하기</button></p>
										</td>	
									</tr>
								</tbody>
							</table>
						</form>
					</c:if>
				</div>
			</div>
			
		
			
		</article>
		
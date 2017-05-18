<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--------------- header include --------------%>
<jsp:include page="ma_header.jsp"></jsp:include>

<%-- chart --%>
<link rel="stylesheet" href="/DoIt/css/Nwagon.css" type="text/css">
<script src="/DoIt/js/Nwagon.js"></script>

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
			
			
				<%-- 본문 내용 --%>
				
				<%-- 세일즈 차트 --%>
				<article class="dash_graph">	
				<h4>total dashboard section graph contents</h4>
		
					<article class="fl_le graph_le">
						<article class="graph_le_con">
							<div class="gr_le_title">
								<p class="fl_le">Total Sales Grah</p>
								<div class="fl_ri">
									<p><a class=""></a><a>merchant</a></p>
									<p><a class=""></a><a>late fee</a></p>
								</div>
							</div><!--title 끝-->
		
							<div id="myfirstchart"></div><!--gr_le_bon 끝-->
								<%-- part1 --%>
								<script>
									//line chart - 매출 현황
									var options = {
											'legend':{
												names: ['08-12', '08-19', '08-26', '09-02', '09-09', '09-16', '9-23']
													},
											'dataset':{
												title:'Playing time per day', 
												values: [[77,47], [76,53], [49,62], [58,86], [48, 85], [56, 48], [56, 48]],
												colorset: ['#DC143C','#FF8C00'],
												fields:['Merchant', 'Late fee']
											},
											'chartDiv' : 'myfirstchart',
											'chartType' : 'line',
											'leftOffsetValue': 50,
											'bottomOffsetValue': 60,
											'chartSize' : {width:930, height:450},
											'minValue' : 0,
											'maxValue' : 100,
											'increment' : 10
										};
					
										Nwagon.chart(options);
									
								</script>
						</article><!--graph_le_con 끝-->
		
					</article><!--graph_le 끝-->
		
					<article class="fl_ri graph_ri">
						<section class="graph_ri_con">
							<header class="gr_ri_he">
								<p>\ ${ dashM.getD_seller() + dashM.getD_lib() + dashM.getD_trade() }</p>
								<p>Total Sales</p>
							</header><!--graph_ri header 끝-->
		
							<section class="gr_ri_sec">
								<article class="gr_top">
									<p>\ ${ dashM.getD_seller() }</p>
									<p class="imsa"><a class="imsa2"><img src="/DoIt/images/ERP_02.png" style="width:100%; height:100%;"/></a></p>
									<p>Merchant Sales</p>
								</article>
		
								<article class="gr_bot">
									<p>\ ${ dashM.getD_lib() }</p>
									<p class="imsa"><a class="imsa2"><img src="/DoIt/images/ERP_02.png" style="width:100%; height:100%;"/></a></p>
									<p>Late Fee Sales</p>
								</article>
							
							</section><!--graph_ri section 끝-->
		
							<footer class="gr_ri_foot">
								<p class="print_gr">Graph Print</p>
							</footer><!--graph_ri footer 끝-->
						</section><!--gr_ri_con 끝-->
					</article><!--graph_ri 끝-->
		
				</article><!--dash graph 끝-->
			
			</article>
			
			
			<%-- 기획 제작 만족도 --%>
			<article class="dash_graph2">
				
				<%-- 왼쪽 - 파트 구분 안내 --%>
				<div class="fl_le chart_044">
					<p class="mana_Stitle" style="width:100%;">보유 도서수</p>
					<div id="chart044" class="fl_le"></div>
					<ul class="chart0444 fl_ri" style="text-align:right;">
<<<<<<< HEAD
						<li><a>${ onlinePa } 권</a></li>
						<li><a>${ r_bookCount } 권</a></li>
						<li><a>${ resellPa } 개</a></li>
=======
						<li><a> 권</a></li>
						<li><a>${ r_bookCount } 권</a></li>
						<li><a> 권</a></li>
>>>>>>> 06e83cdc0212d155692e1e75dda189dd861591c1
					</ul>
					<script>
						var options = {
							'dataset': {
								title: 'Web accessibility status',
								values:[18, 12, 3],
								colorset: ['#2BC8C9', '#FF8C00', '#DC143C'],
								fields: ['직접판매', '도서관',  'Doit옥션'] 
							},
							'donut_width' : 30, 
							'core_circle_radius':30,
							'chartDiv': 'chart044',
							'chartType': 'donut',
							'chartSize': {width:280, height:160}
						};
				
						Nwagon.chart(options);
					</script>
				</div>	
				
				<%-- 오른쪽 - 제작 과정 비율 --%>
				<div class="fl_le chart_045">
					<p class="mana_Stitle"  style="width:100%;">제작 비율 / 만족도</p>
					<div id="chart045" class="fl_le"></div>
					<ul class="chart0445 fl_le">
						<li><a>15%</a><span>[ 100% 완료 / 95% 만족 ]</span></li>
						<li><a>15%</a><span>[ 80% 완료 / 70% 만족 ]</span></li>
						<li><a>20%</a><span>[ 85% 완료 / 85% 만족 ]</span></li>
						<li><a>50%</a><span>[ 90% 완료 / 95% 만족 ]</span></li>
					</ul>
					<script>
						var options = {
							'dataset': {
								title: 'Web accessibility status',
								values:[15, 15, 20, 50],
								colorset: ['#2BC8C9', '#FF8C00', '#DC143C','#2EB400'],
								fields: ['UX기획', 'UI',  '마크업', '개발'] 
							},
							'donut_width' : 30, 
							'core_circle_radius':30,
							'chartDiv': 'chart045',
							'chartType': 'donut',
							'chartSize': {width:300, height:160}
						};
				
						Nwagon.chart(options);
					</script>
				</div>
				
			</article>
			
			
			<%-- 일정관리 --%>
			<article class="dash_graph3">
				<%-- 전체 일정 관리 --%>
				<div class="graph54 fl_le">
					<p class="dash_title0">Total Plan</p>
					<ul class="tab_title">
						<li class="fl_le">Weekend Schedule</li>
						<li class="fl_ri">4월</li>
						<li class="fl_ri">3월</li>
					</ul>
					
				</div>
				
				<%-- 달력.. 가능하면 java로 짜야하는데.. --%>
				<div class="graph55 fl_ri">
					<img src="/DoIt/images/cal.png" style="width:100%; height:425px"/>
				</div>
			
			</article>
			

		</article>

</section>


<%--------------- footer include --------------%>
<jsp:include page="ma_footer.jsp"></jsp:include>

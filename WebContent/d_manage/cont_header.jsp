<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 
		<%-- 공용 nav --%>
		<header class="dash_head">
					<h3>total dashboard head</h3>

						<article class="dash_nav_wrap">
							<article class="dash_nav">
							<h4>dash_nav_top</h4>
								<p style="border:none;">00:00<span>PM</span></p>
								<p>4<span>Apr</span><span>2017</span></p>
								<p class="system_sel">
									<img src="/DoIt/images/company.png" style="width:23px; height:23px; margin-right:12px; vertical-align:middle;">
									<a>두잇두잇 츄우 관리 ▼</a>
								</p>
									<p class="mana_op">
										<a class="sung_navi">
											<span>Navigation Bar Link</span>
											<img src="/DoIt/images/mess_x.png" style="width:30px; height:30px; cursor:pointer"/>
										</a>
										<a href="/DoIt/main.do">User Web Main</a>
									</p>
							</article><!--dash_nav-->



							<article class="dash_nav2">
							<h4>dash_nav_bottom</h4>
								<article>
									<div>
										<p>Total Dashboard</p>
										<p>DOIT Project DashBoard - Management System</p>
									</div>
									<div >
										<p class="graph_img" style="margin:0px;">
											<a>${ dashM.getD_seller() }<span>merchant</span></a>
											<a class="graph_img2"><img src="/DoIt/images/ERP_01.png" style="width:100%; height:100%;"/></a>
										</p>

										<p class="graph_img">
											<a>${ dashM.getD_lib() }<span>late fee</span></a>
											<a class="graph_img2"><img src="/DoIt/images/ERP_01.png" style="width:100%; height:100%;"/></a>
										</p>

										<p class="graph_img">
											<a style="color:#019a93; font-weight:bold">\ ${ dashM.getD_seller() + dashM.getD_lib() + dashM.getD_trade() }<span>Sales</span></a>
											<a class="graph_img2"><img src="/DoIt/images/ERP_01.png" style="width:100%; height:100%;"/></a>
										</p>
									</div>

									<div>
										<p class="print_to box-sizing">Page Print</p>
									</div>
								</article>
							</article>

						</article>

					</header>







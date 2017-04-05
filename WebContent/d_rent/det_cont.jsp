<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

					

			<%-- 도서 상세보기 내용 파트 --%>
			<p class="mid_title">
				<a>책 구성 요소</a>
				<span></span>
			</p>

			<%-- 도서 간략한 정보 --%>
			<table class="sim_tab">
				<tbody>
					<tr>
						<td>ISBN</td>
						<td>9788969650771(8969650776)</td>
					</tr>
					<tr>
						<td>쪽수</td>
						<td>각 도서별 상이... : 전문서적 / 소설 / 어린이 ...</td>
					</tr>
					<tr>
						<td>크기</td>
						<td>189 * 258 * 30 mm /1422g <button type="button">판형안내</button></td>
					</tr>
					
				</tbody>
			</table>
			
			
			<%-- 도서 상세보기 내용 파트 --%>
			<p class="mid_title">
				<a>저자 소개</a>
				<span></span>
			</p>
			
			<p class="writer_con">
				저자 : ${ rto.getBr_writer() }
			</p>
			
			
			<%-- 도서 상세보기 내용 파트 --%>
			<p class="mid_title">
				<a>책소개</a>
				<span></span>
			</p>

			<div class="so_part">
				<%-- 소제목 분류 --%>
				<p class="so_title">${ rto.getBr_sname() }</p>
				
				<ul class="so_cont">
					<li>NCS 베스트셀러 1위! </li>
					<li>최신기출유형부터 실전모의고사까지 한 권으로 완벽하게 대비하는 NCS 통합 기본서! </li>
				</ul>
			</div>
			
			<div class="so_part">
				<%-- 소제목 분류 --%>
				<p class="so_title">책의 내용 글 </p>
				
				<ul class="so_cont">
					<li>
						${ rto.getBr_cont() }
					  </li>
				</ul>
			</div>
			
			<div class="so_part">
				<%-- 소제목 분류 --%>
				<p class="so_title">책이 속한 분야</p>
				
				<ul class="so_cont">
					<li>- 분야 : ${ rto.getD_bno() }</li>
					<li>- 구성 : 이론+문제+해설 </li>
					<li>- 특징 : </li>
					<li>① 최신 공기업 채용 기출문제를 분석 </li>
					<li>② NCS 핵심 압축정리 + 시크릿 노트 수록</li>
				</ul>
			</div>



















<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css"  href="/DoIt/css/online.css">

<!-- header import -->
<%@include file="../header.jsp" %>

<jsp:include page="/d_login/side_my.jsp"></jsp:include>

<article class="my_cont_wrap">

   <div class="rl alert alert-info"><small>*. 판매신청완료된 책  리스트를 보는 페이지 입니다. 관리자가 회원의 신청에 대한 수락이 했을 때 이페이지에서 볼 수 있습니다.</small></div>
   
   <div class="rl alert alert-erorr"><small>*. 회원님께서는  <span class="code">배송</span>을 진행하시면 되며, 이 페이지에서 배송 현황을 체크하 실 수 있습니다. </small></div>


<!--------------------------------- 회원의 판매중인 List ------------------------------------->
   <table style="width:100%" border="1 solid black">   
      <tr style="background-color:#bebebe">
         <td height="20px" width="100px" class="d-center"> <!--  책이미지와 셀의 높이가 10px 커야함. thumbnail 적용 여백 -->
            <span class="big-font20">신청 번호
            </span>
         </td>
         <td height="20px" width="150px" class="d-center">
            <span class="big-font20">책 사진</span>
         </td>
         <td height="20px"  class="d-w80 d-center">
            <span class="big-font20">책 정보</span>
         </td>
         <td height="20px"  class="d-w80 d-center" width="130px">
            <span class="big-font20">신청일시</span>
         </td>
      </tr>
   </table>
   <!-- -------판매신청 완료된 책이 없을때----------------------------- -->
<c:if test="${count==0}">
   <div class="d-center">
      현재 판매중인 책이 없습니다! 
   </div>
   <div class="rl"></div>
</c:if>
   <!-- ------판매신청완료된 책이 있을 때----------------------------- -->
    <c:if test="${count!=0}">
   <table style="width:100%" border="1 solid black">   
      <c:forEach var="article" begin="0" end="${articleList.size()}" step="1" items="${articleList}">   

            <tr>
               <td height="20px" width="100px" class="d-center"> <!--  책이미지와 셀의 높이가 10px 커야함. thumbnail 적용 여백 -->
                  <span class="big-font20">
                  ${article.d_sno}
                  </span>
               </td>
               <td height="200px" width="150px">
                    <c:if test="${article.d_bpic == null}">
                     <center>사진정보가 없습니다</center>
                  </c:if>
                  <c:if test="${article.d_bpic != null}">
                     <img src="/DoIt/d_bpic/${article.d_bpic}" height="190px;" class="thumbnail" />
                  </c:if> 
               </td>
               <td height="200px" class="d-w80 ">
      <!--  책제목을 눌렀을 때 상세 페이지로 이동 ------------------------------------------------------------------------------ -->
                   <span class="big-font35 d-bold">
                      ${article.d_bname}
                   </span><br /><br />
                  저자 : ${article.d_bauthor} | 출판사 : ${article.d_bpublisher} <br /><br />
                  정가 : <span class="code">${article.d_bvalue}</span> 원 <span class="small70"> → [검수결과에 따라 달라집니다.] </span><br />
                  판매금액 : -- 원 <span class="small70"> → [검수결과에 따라 달라집니다.] </span> 
               </td>
               <td height="20px"  class="d-w80 d-center" width="130px">
                  <span class="big-font20">${article.d_sdate}</span>
               </td>
            </tr>

      
       
      </c:forEach>

   </table>
   </c:if>    

<!-- 페이지 번호 ------------------------------------------------------------------------ -->   
<center>
   <c:if test="${startrow>10}">
      <a href="/jsp/board/list.nhn?pageNum=${startrow-10}" >[이전]</a>
   </c:if>
   
   <c:forEach var="i" begin="${startrow}" end="${endrow}" step="1">
      <a href="/jsp/board/list.nhn?pageNum=${i}">[${i}]</a>
   </c:forEach>
   
   
   <c:if test="${endrow<pageCount}">
      <a href="/jsp/board/list.nhn?pageNum=${startrow+10}">[다음]</a>
   </c:if>
</center>



</article>
<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>




      
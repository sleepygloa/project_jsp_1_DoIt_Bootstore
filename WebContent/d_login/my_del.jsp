<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%--------------- header include --------------%>
<jsp:include page="/header.jsp"></jsp:include>

<%--------------- 사이드 메뉴 include --------------%>
<jsp:include page="/d_login/side_my.jsp"></jsp:include>

<script type="text/javascript">
function checkIt(){
    if(!document.deleteForm.d_pass.value){
      alert("비밀번호를 입력하세요!");
      document.deleteForm.d_pass.focus();
      return false;
    };
</script>

	
	
			<%-- 본문내용 --%>
			<article class="my_cont_wrap">
				<p class="my_title">
					마이 페이지
				</p>
				<p class="my_sub_title">
					마이페이지에서는 회원정보 조회, 수정, 탈퇴를 할 수 있으며 구매내역을 확인하실 수 있습니다.
					<span>회원정보 수정 : 회원정보에서 아이디는 수정 하실 수 없습니다.</span>
					<span>회원 탈퇴 : 회원탈퇴 이후에는 로그인 하실 수 없습니다.</span>
				</p>
			

				<form action="/DoIt/d_login/deletePro.do" method="POST" >
					<ul class="del_cl">
						<li>
							<a style="font-size:1.1em">비밀번호 입력</a>
							<a style="color:red; font-size:0.9em">* 주의 : 회원 탈퇴 후 로그인이 불가능합니다.</a>
						</li>
						<li><a><input type="password" name="d_pass" placeholder="현재 비밀번호를 입력하세요."/></a></li>
					</ul>
					
					<p class="button_cell">
						<button type="submit" class="re_button">회원 탈퇴</button>
					</p>
				</form>
				
				
			</article>





<%--------------- footer include --------------%>
<jsp:include page="/footer.jsp"></jsp:include>				
				
				
				
				
				
				
				
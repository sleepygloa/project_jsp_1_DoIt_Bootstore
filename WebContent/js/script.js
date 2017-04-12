function checkIt() {
        var userinput = eval("document.userinput");
        if(!userinput.d_id.value) {
            alert("ID를 입력하세요");
            return false;
        }
        
    }
 
    // 아이디 중복 여부를 판단
 function openConfirmid(userinput) {
        // 아이디를 입력했는지 검사
        if (userinput.d_id.value == "") {
            alert("아이디를 입력하세요");
            return;
        }
        // url과 사용자 입력 id를 조합합니다.
        url = "/DoIt/d_login/confirmid.do?d_id="+userinput.d_id.value ;
        
        // 새로운 윈도우를 엽니다.
        open(url, "confirm",  "toolbar=no, location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300, height=200");
    }
 
 function setid(){		
 		//opener.document.userinput.d_id.value="${d_id}";
		self.close();
}
 

 //------------------------------------------------- 경매 / 직거래 ---------------------------------------------------------------//
 function  reList_click() {
	 		alert("글쓰기는 로그인 후 이용 가능합니다");
	 		location.href="/DoIt/d_login/login.do";
}
 function reList_click2() {
		alert("판매자만 글을 작성할 수 있습니다");
		location.href="/DoIt/d_resell/reList.do";
}
 function reList_click3() {
		alert("판매자 승인 신청중입니다 마이페이지를 확인해 주세요");
		location.href="/DoIt/d_resell/reList.do";
}
 
 
function reReplyDelete1() {
		if(confirm("정말 삭제하시겠습니까??") == true){
			location.href="/DoIt/d_resell/reReplyDelete.do?rerbook_rnum=${rer.rerbook_rnum}&rbook_no=${article.rbook_no}&pageNum=${pageNum}";
		}else{
			return;
		}
		
} 
 

function reReplyDelete() {
    var Del = confirm("삭제 하시겠습니까?")
        if (Del == true)
        {
        alert("삭제 되었습니다.")
            location.href="http://naver.com"
        } else {
            alert("취소 되었습니다.")
                }
}

function  reList_scrap() {
		alert("로그인 후 이용 가능합니다");
		location.href="/DoIt/d_login/login.do";
}


//-------글쓰기유효성-------------
function writeSave(){
	
	if(document.reWriteform.rbook_subject.value==""){
	  alert("제목을 입력하세요.");
	  document.reWriteform.rbook_subject.focus();
	  return false;
	}
	if(document.reWriteform.rbook_price.value==""){
		  alert("판매할 가격을 입력하세요.");
		  document.reWriteform.rbook_price.focus();
		  return false;
	}
	if(document.reWriteform.rbook_price2.value==""){
		  alert("정가를 입력하세요.");
		  document.reWriteform.rbook_price2.focus();
		  return false;
	}
	if(document.reWriteform.rbook_writer.value==""){
		  alert("저자를 입력하세요.");
		  document.reWriteform.rbook_writer.focus();
		  return false;
	}
	if(document.reWriteform.rbook_company.value==""){
		  alert("출판사를 입력하세요.");
		  document.reWriteform.rbook_company.focus();
		  return false;
	}
	if(document.reWriteform.rbook_content.value==""){
		  alert("내용을 입력하세요.");
		  document.reWriteform.rbook_content.focus();
		  return false;
	}
	if(document.reWriteform.rbook_pic.value==""){
		  alert("사진을 입력하세요.");
		  document.reWriteform.rbook_pic.focus();
		  return false;
	}
	if(document.reWriteform.rbook_bgread.value==""){
		  alert("책의 상태를 입력하세요.");
		  return false;
	}
 }


//--------글쓰기제한

function  reList_WriterClick() {
		alert("더이상 글을 작성 하실 수 없습니다");
}


//---------------bid---------
function  bidClick() {
    alert("중복입찰은 불가합니다.");
    
}
function  bidClick1() {
 alert("로그인후 이용해주세요.");
 location.href="/DoIt/d_login/login.do";
}
function bidList_click2() {
 alert("판매자만 글을 작성할 수 있습니다");
 location.href="/DoIt/d_bid/bidList.do";
}
function bidList_click3() {
 alert("판매자 승인 신청중입니다 마이페이지를 확인해 주세요");
 location.href="/DoIt/d_bid/bidList.do";
}
 
 
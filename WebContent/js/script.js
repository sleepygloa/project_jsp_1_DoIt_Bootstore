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
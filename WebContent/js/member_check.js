function pw_check(){
    var id = document.pwForm.d_id.value;
    var name = document.pwForm.d_name.value;  
    var email = document.pwForm.user_mail1.value;  

    if(id == ""){
    	alert("아이디를 입력하세요!!");
    	document.pwForm.id.focus(); // 해당 위치에 커서를 이동
    	return false; 	// 1차 유효성검사,  남아있는 동작을 하지 않음, 이동해야 하지만 이동하지 않음
	}

    if(name == ""){
    	alert("이름을 입력해주세요 !");
    	document.pwForm.name.focus();
    	return false; 	// 필수항목에 이용
   	}    	
    
    if(email == ""){
    	alert("이메일을 입력하세요!");
    	document.pwForm.email.focus();
    	return false; 	// 필수항목에 이용

    }
}

function pw_ChangeCheck(){
	var pw = document.pwChangeForm.d_pass.value;
	var pw2 =document.pwChangeForm.d_pass_check.value;
	
	if(pw == ""){
		alert("비빌번호를 입력하세요!");
		document.pwChangeForm.d_pass.focus(); // 해당 위치에 커서를 이동
    	return false; 
	}
	if(pw2 == ""){
		alert("비빌번호를 입력하세요!");
		document.pwChangeForm.d_pass_check.focus(); // 해당 위치에 커서를 이동
    	return false; 
	}
	if(pw != pw2){
		alert("비밀번호가 일치하지 않습니다!!");
		document.pwChangeForm.d_pass_check.focus();
		return false; 
	}
}

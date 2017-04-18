function InquireSave(){
	
	if(document.Inquire.c_isubject.value==""){
	  alert("제목을 입력하세요.");
	  document.Inquire.c_isubject.focus();
	  return false;
	}
	if(document.Inquire.c_icontent.value==""){
		alert("내용을 입력하세요.");
		document.Inquire.c_icontent.focus();
		return false;
	}
	
 }

function Inquire_reply(){
	
	if(document.Inquire_reply.c_isubject.value==""){
	  alert("제목을 입력하세요.");
	  document.Inquire_reply.c_isubject.focus();
	  return false;
	}
	if(document.Inquire_reply.c_icontent.value==""){
		alert("내용을 입력하세요.");
		document.Inquire_reply.c_icontent.focus();
		return false;
	}
	
 }
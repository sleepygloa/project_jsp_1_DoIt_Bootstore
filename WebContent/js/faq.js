
//---faq 트리구조---------------------------------------------//
jQuery(function($){
	// Frequently Asked Question
	var faq = $('.faq');
	faq.addClass('hide');
	faq.find('.a').hide();
	//article.eq(0).removeClass('hide');
	//article.eq(0).find('.a').show();
	$('.faq>.q>a').click(function(){
		var myArticle = $(this).parents('.faq:first');
		if(myArticle.hasClass('hide')){
			faq.addClass('hide').removeClass('show');
			faq.find('.a').slideUp(100);
			myArticle.removeClass('hide').addClass('show');
			myArticle.find('.a').slideDown(100);
		} else {
			myArticle.removeClass('show').addClass('hide');
			myArticle.find('.a').slideUp(100);
		}
		return false;
	});
	
	$('.showAll').click(function(){
		var hidden = $('.faq.hide').length;
		if(hidden > 0){
			faq.removeClass('hide').addClass('show');
			faq.find('.a').slideDown(100);
		} else {
			faq.removeClass('show').addClass('hide');
			faq.find('.a').slideUp(100);
		}
	});
});

//------faq 유효성------------------------------------------//

function faqSave(){
	
	if(document.faqWriteform.faq_subject.value==""){
	  alert("제목을 입력하세요.");
	  document.faqWriteform.faq_subject.focus();
	  return false;
	}
	if(document.faqWriteform.faq_content.value==""){
		  alert("내용을 입력하세요.");
		  document.faqWriteform.faq_content.focus();
		  return false;
	}
	
 }
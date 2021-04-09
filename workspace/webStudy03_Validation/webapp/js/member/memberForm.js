/**
 * 
 */
$.generateMessage = function(message) {
	let messageTag = $("<span>").text(message ? message : "").addClass(
			"message").addClass("error");
	return messageTag;
}
let idTag = $("[name='mem_id']").on("change", function() {// 값 입력 후 focus를
															// 이동했을 때
	idCheckBtn.trigger("click");// 이벤트 강제 발생
});
let idCheckBtn = $("#idCheck").on("click", function() {
	memberForm.data("idcheck", "FAIL");
	idTag.next(".message:first").remove();
	let mem_id = idTag.val();
	$.ajax({
		url : "idCheck.do",
		method : "post",
		data : {
			id : mem_id
		},
		dataType : "json", // accept 요청 헤더와 contents타입이 결정됨
		success : function(resp) {
			memberForm.data("idcheck", resp.result);// Map타입
			if (resp.result != "OK") {
				// <span>태그를 새로 만드는 코드
				let messageTag = $.generateMessage("아이디 중복");
				idTag.after(messageTag);
				idTag.focus();
			}
		},
		error : function(xhr, error, msg) {
			console.log(xhr);
			console.log(error);
			console.log(msg);
		}
	});
});
let memberForm = $("#memberForm").on("submit", function() {
	let checked = $(this).data("idcheck") == "OK";
	if (!checked) {
		let messageTag = idTag.next(".message:first");
		if (!messageTag || messageTag.length == 0) {
			messageTag = $.generateMessage();
		}
		messageTag.text("아이디 중복 체크 하세요.");
		idTag.after(messageTag);
	}
	return checked;
	// return false;//이벤트를 중단시킴
});
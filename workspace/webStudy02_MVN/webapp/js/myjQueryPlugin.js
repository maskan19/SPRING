/**
 * 
 */

$.test = function() {
	alert("TEST");
}
$.fn.test2 = function() {
	alert("TEST2" + this.attr("method"));
	console.log(this)
	return this;
}

$.fn.formToAjax = function(param) {
	this.on("submit", function(event) {
		event.preventDefault(); // 이벤트의 기능을 막음
		
		let url = this.action;
		let method = this.method;
		let inputs = $(this).find(":input");
		let data = {}
		$(inputs).each(function(index, input) {
			let name = $(this).attr("name");
			let value = $(this).val();
			data[name] = value;
			console.log(data);
		});

		$.ajax({
			// url : "", 생략하면 같은 주소가 쓰인다
			url : url,
			method : method,
			data : data,
			dataType : param.dataType,
			success : param.success,
			error : function(xhr, error, msg) {
				console.log(xhr, error, msg);
			}
		});

		return false; // 섭밋취소
	});
}
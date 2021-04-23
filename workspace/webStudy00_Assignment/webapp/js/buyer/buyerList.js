/**
 * 
 */
let listBody = $("#listBody").on(
		"click",
		"tr",
		function() {
			let buyer = $(this).data("buyer");
			location.href = $.getContextPath() + "/buyer/buyerView.do?what="
					+ buyer.buyer_id;
		});

let pagingArea = $("#pagingArea");

let searchForm = $("#searchForm")
		.on("change", ":input[name]", function() {
			// this.submit()해버리면 input을 submit하는거임
			searchForm.submit();
		})
		.ajaxForm(
				{
					dataType : "json",
					beforeSubmit : function() {
						searchForm.find("[name='page']").val("");
					},
					success : function(resp) {
						listBody.empty();
						pagingArea.empty();
						let trTags = [];
						if (resp.dataList) {
							$(resp.dataList)
									.each(
											function(idx, buyer) {
												console.log(buyer.rnum)
												let tr = $("<tr>")
														.append(
																$("<td>")
																		.text(
																				buyer.rnum),
																$("<td>")
																		.text(
																				buyer.buyer_id),
																$("<td>")
																		.text(
																				buyer.buyer_name),
																$("<td>")
																		.text(
																				buyer.buyer_bankname))
														.data("buyer", buyer)
														.css("cursor",
																"pointer");
												trTags.push(tr);
											});
						} else {
							trTags.push($("<tr>").html(
									$("<td>").attr("colspan", "3")));
						}

						listBody.html(trTags);
						pagingArea.html(resp.pagingHTML);
					},
					error : function(xhr, resp, error) {
						console.log(xhr);
					}
				});

searchForm.submit();

$("#pagingArea").on("click", "a", function(event) {
	event.preventDefault();
	let page = $(this).data("page");
	if (page) {
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
	}
	return false;
});

$("#newform").on("click", function() {
	location.href = $.getContextPath() + "/buyer/buyerInsert.do";
});
/**
 * 
 */
	let listBody = $("#listBody").on("click", "tr" ,function(){
		let prod = $(this).data("prod");
		//	location.href="prodView.do?what=" + prod.prod_id; //상대 경로
		location.href=$.getContextPath()+"/prod/prodView.do?what="+prod.prod_id;// 절대 경로
	});
	
	let pagingArea = $("#pagingArea");
	let prod_buyerTag =	$("[name='prod_buyer']");
	$("[name='prod_lgu']").on("change", function(){
		let selectedLgu = $(this).val();
		if(selectedLgu){
			prod_buyerTag.find("option").hide();
			prod_buyerTag.find("option."+selectedLgu).show();
		}else{
			prod_buyerTag.find("option").show();
		}
		prod_buyerTag.find("option:first").show();
	});
	let searchForm = $("#searchForm").on("change", ":input[name]", function(){
		//this.submit();//form tag가 아니라 input tag가 된다.
		searchForm.submit();
	}).ajaxForm({
		dataType:"json"
		, beforeSubmit:function(){
			searchForm.find("[name='page']").val("");	
		}, success:function(resp){ //pagingVO
			listBody.empty();
			pagingArea.empty();
			let trTags = [];
			if(resp.dataList){
				$(resp.dataList).each(function(idx, prod){
					let tr = $("<tr>").append(
								$("<td>").text(prod.rnum)
								, $("<td>").text(prod.prod_id)
								, $("<td>").text(prod.lprod_nm)
								, $("<td>").text(prod.prod_name)
								, $("<td>").text(prod.buyer.buyer_name)
								, $("<td>").text(prod.prod_cost)
								, $("<td>").text(prod.prod_price)
								, $("<td>").text(prod.prod_mileage)
							).data("prod", prod).css("cursor", "pointer");
					trTags.push(tr);
				});
			}else{
				trTags.push( 
					$("<tr>").html(
						$("<td>").attr("colspan", "8")		
					)
				);
			}
			listBody.html(trTags);
			pagingArea.html( resp.pagingHTML );
		}, error:function(xhr, resp, error){
			console.log(xhr);
		}
	}); //취소후 비동기로 전환
	
	searchForm.submit();
	
	 $("#pagingArea").on("click", "a", function(event){
	      event.preventDefault();
	      let page = $(this).data("page");
	      if(page){
	         searchForm.find("[name='page']").val(page);
	         searchForm.submit();
	      }
	      return false;
	   });
	   
	   $("#newBtn").on("click", function(){
	      location.href = $.getContextPath() + "/prod/prodInsert.do";
	   });
	   

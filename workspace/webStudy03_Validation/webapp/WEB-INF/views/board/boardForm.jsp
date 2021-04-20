<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/js/ckeditor/ckeditor.js"></script>
<style type="text/css">
<jsp:include page="/includee/customcss.css" />
</style>
</head>
<body>
<form method="post" enctype="multipart/form-data">
	<input type="hidden" name="bo_no" value="${board.bo_no }" />
	<input type="hidden" name="bo_type" value="${board.bo_type }">
	<input type="hidden" name="bo_parent" value="${board.bo_parent }" />
<table class="table table-bordered">
	<tr>
		<th>제목</th>
		<td>
			<div class="input-group">
				<input class="form-control col-9 mr-3" type="text" name="bo_title" required
				value="${board.bo_title }" />
				<div class="form-check col-2">
					<input class="form-check-input" type="checkbox" 
						id="bo_sec" name="bo_sec" value="Y" 
						${board.bo_sec eq 'Y' ? 'checked':'' }
					/>
					<label class="form-check-label" for="bo_sec">
						비밀글
					</label>
				</div>
				<span class="error">${errors.bo_title }</span>
			</div>
		</td>
	</tr>
	
	<c:if test="${board.bo_type eq 'BOARD'}">
	<tr>
		<th>작성자</th>
		<td><input class="form-control" type="text" name="bo_writer" required
			value="${board.bo_writer }" />
			<span class="error">${errors.bo_writer }</span>
		</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input class="form-control" type="text" name="bo_pass" required />
			<span class="error">${errors.bo_pass }</span>
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<div class="form-inline fileArea">
<!-- 				<p> -->
				<%-- 
				<c:forEach items="${board.attatchList }"  var="attatch">
					<input class="form-control"  type="text" name="+" value="${attatch.att_filename }" alt="${attatch.att_no }">
					<span class="minusBtn btn btn-secondary">-</span>
				</c:forEach>
				
					<input class="form-control mr-2" type="file" name="bo_files">
					<span class="plusBtn btn btn-secondary mr-2">+</span>
					 --%>
					
					
					
					
					<c:forEach items="${board.attatchList }"  var="attatch">
                    <div class="tel__input">
                      <label>기존 첨부 파일</label>
<!--                       <input type="file" class="files" name="removeList" accept="image/*"/> -->
                      <input class="filepath" name="removeList" alt="${attatch.att_no }" value="${attatch.att_filename }">
                      <button type="button" class="deleter">- 첨부파일 삭제</button>
                    </div>
                    	</c:forEach>
					
                    <div class="tel__input">
                      <label for="file1">파일 첨부하기</label>
                      <input type="file" class="files" id="file1" accept="image/*" name="bo_files" />
                      <input class="filepath" id="filepath1" value="파일을 선택하세요.">
                      <button type="button" class="adder">+ 첨부파일 추가</button>
                    </div>
                    <input type="hidden" name="post_cont" id="post_cont">
<!-- 				</p> -->
					
					
					
					
					
			</div>
		</td>
	</tr>
	</c:if>
	
	<tr>
		<th>내용</th>
		<td>
			<textarea class="form-control" rows="5" cols="100" name="bo_content" id="bo_content">${board.bo_content }</textarea>
			<span class="error">${errors.bo_content }</span>
		</td>
	</tr>
	<tr>
		<th>댓글</th>
		<td>
		<c:forEach items="${board.replyList }" var="reply">
			<textarea class="form-control" rows="5" cols="100" name="reply" id="bo_reply">${reply.rep_content}</textarea>
		</c:forEach>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div class="d-flex justify-content-center">
				<button type="submit" class="btn btn-success mr-2">저장</button>
				<button type="reset" class="btn btn-warning mr-2">취소</button>
				<button type="button" class="goBtn btn btn-primary mr-2" 	data-gopage="<c:url value='/board/boardList.do'/>">목록으로</button>
			</div>
		</td>
	</tr>
	
</table>
</form>
<script type="text/javascript">
	CKEDITOR.replace("bo_content", {
		filebrowserImageUploadUrl : '${cPath}/board/boardImage.do?type=Images'
	});
	
// 	$(".fileArea").on("click", ".plusBtn" ,function(){
// 		let iBtn = $(this).prev().val("").clone();
// 		let pBtn = $(this).val("").clone();
// 		$(this).parents(".fileArea p").append(iBtn);
// 		$(this).parents(".fileArea p").append(pBtn);
// // 		let source = $(this).parents("p:first");
// // 		let clone = source.clone();
// // 		clone.find("input").val("");
// // 		$(this).parents(".fileArea:first").append(clone);
// 	});
	
// 	$(".fileArea").on("click", ".minusBtn" ,function(){
// 		let altVal = $(this).prev().attr("alt");
// 		console.log(altVal);
// 		$(this).prev().remove();
// 		$(this).remove();
// 		$( "<input>",{type:'text'}).appendTo( ".fileArea" ).val(altVal).attr("name", "removeList");
// 	});
	
	$(".goBtn").on("click", function(){
		let url = $(this).data("gopage");
		if(url)
			location.href = url;
	})
		
	
	//라벨 클릭
	$('.fileArea').on('change', '.tel__input', function() {
		let cur = $("input[type='file']", this).val();
		console.log(cur);
		if(cur!=""){
				$(".filepath", this).val(cur);
		}else
				$(".filepath", this).val("파일을 선택하세요.");
	});
	//버튼 추가
	$(".fileArea").on("click", ".adder", function() {
		let bt_class = $(this).attr("class"); //adder
		console.log(bt_class);
		let input_id = Number($(this).siblings("label").next().attr("id").substring(4))+1 //1
		let code = '<div class="tel__input">';
		code += '<label for="file' + input_id + '">파일 첨부하기</label>';
		code += '<input type="file" class="files" name="bo_files" id="file' + input_id + '" accept="image/*"';
		code += 'onchange="setThumbnail(event);" />';
		code += '<input class="filepath" id="filepath' + input_id +'"value="파일을 선택하세요.">';
		code += '<button type="button" class="adder">+ 첨부파일 추가</button>';
		code += '</div>';
		$(this).html("- 첨부파일 삭제");
		$(this).attr("class", "deleter");
		$(".tel__input").last().after(code);
	});
		
	$(".fileArea").on("click", ".deleter", function() {
// 		let bt_class = $(this).attr("class"); //deleter
// 		let get_id = 'file' + $(this).siblings("label").next().attr("id").substring(4) //1
		console.log($(this));
		$(this).parents(".tel__input").remove();
		let altVal = $(this).prev().attr("alt");
		$( "<input>",{type:'text'}).appendTo( ".fileArea" ).val(altVal).attr("name", "removeList");
	});
		
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>
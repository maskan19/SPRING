<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form modelAttribute="board" id="boardForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="bo_no" value="${board.bo_no }" />
	<input type="hidden" name="bo_type" value="${board.bo_type }">
	<input type="hidden" name="bo_parent" value="${board.bo_parent }" />
<table class="table table-bordered">
	<tr>
		<th>제목</th>
		<td>
			<div class="input-group">
				<input class="form-control col-9 mr-3" type="text" name="bo_title" 
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
				<form:errors path="bo_title" 
						element="span" cssClass="error" />
			</div>
		</td>
	</tr>
	<c:if test="${board.bo_type eq 'BOARD' }">
		<tr>
			<th>작성자</th>
			<td><input class="form-control" type="text" name="bo_writer" 
				value="${board.bo_writer }" />
				<form:errors path="bo_writer" 
						element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input class="form-control" type="text" name="bo_pass"  />
				<form:errors path="bo_pass" 
					element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th>기존파일</th>
			<td>
				<c:if test="${not empty board.attatchList }">
					<c:forEach items="${board.attatchList }" var="attatch">
						<span data-attno="${attatch.att_no }">
							${attatch.att_filename }
							<span class="delBtn btn btn-danger">-</span>
						</span>
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<div class="form-inline fileArea">
					<p>
						<input class="form-control" type="file" name="bo_files">
						<span class="plusBtn btn btn-secondary">+</span>
					</p>
				</div>
			</td>
		</tr>
	</c:if>
	<tr>
		<th>내용</th>
		<td>
			<textarea class="form-control" rows="5" cols="100" name="bo_content" id="bo_content">${board.bo_content }</textarea>
			<form:errors path="bo_content" 
				element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div class="d-flex justify-content-center">
				<button type="submit" class="btn btn-success mr-2">저장</button>
				<button type="reset" class="btn btn-warning mr-2">취소</button>
				<button type="button" class="goBtn btn btn-primary mr-2"
					data-gopage="<c:url value='/board/boardList.do'/>"
				>목록으로</button>
			</div>
		</td>
	</tr>
</table>
</form:form>
<script type="text/javascript">
	CKEDITOR.replace("bo_content", {
		filebrowserImageUploadUrl : '${cPath}/board/boardImage.do?type=Images'
	});
	$(".fileArea").on("click", ".plusBtn" ,function(){
		let source = $(this).parents("p:first");
		let clone = source.clone();
		clone.find("input").val("");
		$(this).parents(".fileArea:first").append(clone);
	});
	let boardForm = $("#boardForm");
	$(".delBtn").on("click", function(){
		let fileSpan = $(this).parents("span:first");
		let delAttNo = fileSpan.data("attno");
		let newInput = $("<input>").attr({
							"type":"text"
							, "name":"delAttNos"
						}).val(delAttNo);
		
		boardForm.append(newInput);
		fileSpan.hide();
	});
	
	
	$(".goBtn").on("click", function(){
		let url = $(this).data("gopage");
		if(url)
			location.href = url;
	});
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th>게시판종류</th>
			<td>${board.bo_type}</td>
		</tr>
		<tr>
			<th>글번호</th>
			<td>${board.bo_no}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.bo_title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.bo_writer}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.bo_date}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.bo_hit}</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>${board.bo_rec}</td>
		</tr>
		<tr>
			<th>신고수</th>
			<td>${board.bo_rep}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:if test="${not empty board.attatchList }">
					<c:forEach items="${board.attatchList }" var="attatch">
						<c:url value="/board/download.do" var="downloadURL">
		                     <c:param name="what" value="${attatch.att_no }"></c:param>
		                  </c:url>
	                  <a href="${downloadURL }"><span>${attatch.att_filename }</span></a><br>
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.bo_content}</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:url value="/board/boardList.do" var="listURL" />
				<button class="goBtn btn btn-primary" type="button" 
					data-gopage="${listURL }">전체글 목록</button>
					
				<button class="goBtn btn btn-primary" type="button" 
					data-gopage="${cPath }/board/noticeList.do">공지글 목록</button>
					
				<c:url value="/board/boardInsert.do" var="insertURL">
				<c:param name="parent" value="${board.bo_no }"></c:param>
				</c:url> 
				<button class="goBtn btn btn-info" type="button" 
					data-gopage="${insertURL}">답글쓰기</button>
				
				<c:url value="/board/boardUpdate.do" var="updateURL">
					<c:param name="what" value="${board.bo_no }"></c:param>
				</c:url>
				<button class="goBtn btn btn-info" type="button" 
					data-gopage="${updateURL}">수정하기</button>
				
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#staticBackdrop">
				  삭제하기
				</button>
				
         </td>
      </tr>
   </table>

	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">정말로 삭제하시겠습니까?</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	       <form method="post" name="pwform" action="${cPath }/board/boardDelete.do?what=${board.bo_no }">
	      <div class="modal-body">
	      <div class="input-group flex-nowrap">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="addon-wrapping">비밀번호 입력</span>
			  </div>
			  <input type="text" class="form-control" name="bo_pass" placeholder="Password" aria-label="Username" aria-describedby="addon-wrapping">
			  <input type="hidden" name="bo_no" value="${board.bo_no }"/>
			</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
	        <button type="submit" class="btn btn-primary" >삭제</button>
	      </div>
	       </form>
	    </div>
	  </div>
	</div>

	<script type="text/javascript">
		$(".goBtn").on("click", function(){
			let url = $(this).data("gopage");
			if(url)
				location.href = url;
		});
// 		$(".pwSubmit").on("click", function(){
// 			let url = $(this).data("gopage");
// 			if(url)
// 				location.href = url;
// 		});
		
		
		
		
		</script>
		   <jsp:include page="/includee/postScript.jsp" />
		</body>
		</html>
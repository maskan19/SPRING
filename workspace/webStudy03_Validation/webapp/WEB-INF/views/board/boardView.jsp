<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회</title>
</head>
<body>
	<h4>게시글 조회 페이지</h4>
	<c:choose>
		<c:when test="${not empty board }">

			<table>
				<tr>
					<th>글번호</th>
					<td>${board.bo_no }</td>
				</tr>
				<tr>
					<th>글제목</th>
					<td>${board.bo_title }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${board.bo_writer }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${board.bo_date }</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${board.bo_hit }</td>
				</tr>
				<tr>
					<th>추천수</th>
					<td>${board.bo_rec }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${board.bo_content }</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><c:forEach var="attach" items="${board.attatchList }">
							<tr>
								<th>파일 명</th>
								<td>${attach.att_filename }</td>
							</tr>
							<tr>
								<th>다운로드 수</th>
								<td>${attach.att_downcount }</td>
							</tr>
						</c:forEach></td>
				</tr>
				<tr>
					<th>댓글</th>
					<td><c:forEach var="reply" items="${board.replyList }">
							<tr>
								<th>댓글 작성자</th>
								<td>${reply.rep_writer }</td>
							</tr>
							<tr>
								<th>댓글 내용</th>
								<td>${reply.rep_content }</td>
							</tr>
						</c:forEach></td>
				</tr>
			</table>
			
		</c:when>
		<c:when test="${ board.bo_sec eq 'Y'}">
		
		해당 게시글은 비밀글입니다.
		
		</c:when>
		<c:otherwise>
		
		해당 게시글은 없는 게시글 입니다.
		
		</c:otherwise>
	</c:choose>

</body>
</html>
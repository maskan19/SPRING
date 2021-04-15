<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<form id="searchForm">
		<select name="buyer_lgu">
			<option value>상품분류</option>
			<c:forEach var="lprod" items="${lprodList}">
				<option value="${lprod.lprod_gu}">${lprod.lprod_nm}</option>
			</c:forEach>
		</select> 
		
		<input type="button" value="신규등록" id="newBtn" />
	</form>
	<table>
		<thead>
			<tr>
				<th>No.</th>
				<th>상품분류명</th>
				<th>거래처명</th>
				<th>거래 은행</th>
				<th>예금주</th>
				<th>거래처 주소</th>
				<th>연락처</th>
				<th>담당자</th>
			</tr>
		</thead>
		<tbody id="listBody">

		</tbody>
		<tfoot>
			<tr>
				<td colspan="8" id="pagingArea"></td>
			</tr>
		</tfoot>
	</table>
	<script type="text/javascript" src="${cPath }/js/prod/prodList.js"></script>
</body>
</html>
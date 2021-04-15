<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>거래처 상세 보기</title>
</head>
<body>
<table>
<tr><th>거래처 분류</th><td>${buyer.buyer_lgu}</td></tr>
<tr><th>거래처 아이디</th><td>${buyer.buyer_id}</td></tr>
<tr><th>거래처 명</th><td>${buyer.buyer_name}</td></tr>
<tr><th>거래처 담당자</th><td>${buyer.buyer_charger}</td></tr>
<tr><th>거래처 메일</th><td>${buyer.buyer_mail}</td></tr>
<tr><th>거래처 팩스</th><td>${buyer.buyer_fax}</td></tr>
<tr><th>거래처 전화번호</th><td>${buyer.buyer_comtel}</td></tr>
<tr><th>거래 은행</th><td>${buyer.buyer_bank}</td></tr>
<tr><th>거래 계좌</th><td>${buyer.buyer_bankno}</td></tr>
<tr><th>거래 계좌 예금주</th><td>${buyer.buyer_bankname}</td></tr>
<tr><th>거래처 우편번호</th><td>${buyer.buyer_zip}</td></tr>
<tr><th>거래처 주소1</th><td>${buyer.buyer_add2}</td></tr>
<tr><th>거래처 주소2</th><td>${buyer.buyer_add1}</td></tr>
<tr><td>
<button type="button" class="controlBtn" id="modifyBtn">수정</button>
<button type="button" class="controlBtn" id="deleteBtn">삭제</button>
</td></tr>
</table>

<form id="deleteForm" action="${cPath }/buyer/buyerDelete.do"
		method="post">
		<input type="hidden" name="password" />
</form>
</body>
</html>
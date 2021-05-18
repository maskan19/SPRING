<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
<header>
<ul>
<li>회원관리</li>
<li>자유게시판</li>
<li>자료실</li>
</ul>
<input type="text">Search
</header>

<span class="heading">회원 리스트</span> <span>회원관리 / 목록</span>
<button type="button">회원등록</button> <select><option>검색구분</option></select><input type="text" value="검색어를 입력하세요">

<table>
<tr>
<th>순번</th>
<th>아이디</th>
<th>패스워드</th>
<th>이메일</th>
<th>전화번호</th>
</tr>
<tr>
<td>순번</td>
<td>아이디</td>
<td>패스워드</td>
<td>이메일</td>
<td>전화번호</td>
</tr>

</table>




<nav aria-label="Page navigation example">
  <ul class="pagination">
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>













<script>
        function popup(){
            var url = "registForm.jsp";
            var name = "인트풋";
            var option = "width = 500, height = 500, top = 100, left = 200"
            window.open(url, name, option);
        }

        function openPopup(){
            window.open("registForm.html", "new", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=700, height=700, left=0, top=0" );
        }
    </script>

    </script>






<footer>
Copyright 2021 AdminLTE.io.All rights reserved.
</footer>





</body>
</html>
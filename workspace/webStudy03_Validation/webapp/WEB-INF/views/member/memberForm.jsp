<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<style>
.error {
   color: red;
}
</style>
<%
   String message = (String)request.getAttribute("message");
   if(message != null && !message.isEmpty()){
      %>
   <script>
      alert("<%=message %>");
   </script>
      <%
   }
%>
</head>
<body>
   <h4>가입양식</h4>
   <form method="post" id="memberForm" enctype="multipart/form-data">
      <table>
      <c:if test="${command ne 'update'}">
       <!-- if문 비교해서보여줘야 함 -->
         <tr>
            <th>회원아이디</th>
            <td>
               <input type="text" name="mem_id"  value="${requestScope.member.mem_id}" />
               <span class="error">${requestScope.errors.mem_id }</span>
               <button type="button" id="idCheck">아이디중복체크</button>
            </td>
         </tr>
      </c:if>
         <!-- 여기까지 -->
         <tr>
            <th>비밀번호</th>
            <td><input type="text" name="mem_pass" />
            <span class="error">${errors.mem_pass}</span></td>
         </tr>
         <tr>
            <th>이름</th>
            <td><input type="text" name="mem_name" value="${member.mem_name}"/>
            <span class="error">${errors.mem_name}</span></td>
         </tr>
         <tr>
            <th>프로필</th>
            <td><input type="file" name="mem_image" accept="image/*"/>
            <span class="error">${errors.mem_name}</span></td>
         </tr>
         <tr>
            <th>주민번호1</th>
            <td><input type="text" name="mem_regno1" value="${member.mem_regno1}" />
            <span class="error">${errors.mem_regno1}</span></td>
         </tr>
         <tr>
            <th>주민번호2</th>
            <td><input type="text" name="mem_regno2" value="${member.mem_regno2}" />
            <span class="error">${errors.mem_regno2}</span></td>
         </tr>
         <tr>
            <th>생일</th>
            <td><input type="date" name="mem_bir" value="${member.mem_bir}" />
            <span class="error">${errors.mem_bir}</span></td>
         </tr>
         <tr>
            <th>우편번호</th>
            <td><input type="text" name="mem_zip" value="${member.mem_zip}" />
            <span class="error">${errors.mem_zip}</span></td>
         </tr>
         <tr>
            <th>주소1</th>
            <td><input type="text" name="mem_add1" value="${member.mem_add1}" />
            <span class="error">${errors.mem_add1}</span></td>
         </tr>
         <tr>
            <th>주소2</th>
            <td><input type="text" name="mem_add2" value="${member.mem_add2}" />
            <span class="error">${errors.mem_add2}</span></td>
         </tr>
         <tr>
            <th>집전번</th>
            <td><input type="text" name="mem_hometel" value="${member.mem_hometel}" />
            <span class="error">${errors.mem_hometel}</span></td>
         </tr>
         <tr>
            <th>회사전번</th>
            <td><input type="text" name="mem_comtel" value="${member.mem_comtel}" />
            <span class="error">${errors.mem_comtel}</span></td>
         </tr>
         <tr>
            <th>휴대폰</th>
            <td><input type="text" name="mem_hp" value="${member.mem_hp}" />
            <span class="error">${errors.mem_hp}</span></td>
         </tr>
         <tr>
            <th>이메일</th>
            <td><input type="text" name="mem_mail" value="${member.mem_mail}" />
            <span class="error">${errors.mem_mail}</span></td>
         </tr>
         <tr>
            <th>직업</th>
            <td><input type="text" name="mem_job" value="${member.mem_job}" />
            <span class="error">${errors.mem_job}</span></td>
         </tr>
         <tr>
            <th>취미</th>
            <td><input type="text" name="mem_like" value="${member.mem_like}" />
            <span class="error">${errors.mem_like}</span></td>
         </tr>
         <tr>
            <th>기념일</th>
            <td><input type="text" name="mem_memorial" value="${member.mem_memorial}" />
            <span class="error">${errors.mem_memorial}</span></td>
         </tr>
         <tr>
            <th>기념일자</th>
            <td><input type="date" name="mem_memorialday" value="${member.mem_memorialday}" />
            <span class="error">${errors.mem_memorialday}</span></td>
         </tr>
         <tr>
            <th>마일리지</th>
            <td>3000</td>
         </tr>
         
         <tr>
            <td colspan="2"><input type="submit" value="저장" /></td>
         </tr>
      </table>
   </form>
   <c:if test="${command ne 'update'}">
   <script src="${cPath }/js/member/memberForm.js"></script>
   </c:if>
   
   
</body>
</html>
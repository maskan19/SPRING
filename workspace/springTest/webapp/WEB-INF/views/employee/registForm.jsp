<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인풋</title>
</head>
<body>

회원 등록

<div>
<div class="profile__img__div"></div>
<file></file><button type="button">업로드</button>
</div>

<div>
<form:form commandName="emp" method="post" id="empForm" enctype="multipart/form-data">
<table>
	<tr>
		<th>
			<span class="essential">*</span>아이디
		</th>
		<td>
			<input type="text" name = "employee_id" value="#{emp.employee_id }" placeholder="13글자 영문자, 숫자 조합">
		</td>
		<td>
			<form:errors path="employee_id" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>
			<span class="essential">*</span>패스워드
		</th>
		<td>
			<input type="text" name = "employee_pwd" value="#{emp.employee_pwd }" placeholder="13글자 영문자, 숫자, 특수문자 조합">
		</td>
		<td>
			<form:errors path="employee_pwd" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>
			<span class="essential">*</span>이름
		</th>
		<td>
			<input type="text" name = "employee_name" value="#{emp.employee_name }"  placeholder="이름을 입력하세요">
		</td>
		<td>
			<form:errors path="employee_name" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
	<th>권한</th><td><select><option>사용자</option></select></td></tr>
	<tr>
		<th>이메일</th>
		<td>
			<input type="email" name = "employee_email" value="#{emp.employee_email }"  placeholder="example@naver.com">
		</td>
		<td>
			<form:errors path="employee_email" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th>전화번호</th><td><select><option>-선택-</option></select>-<input type="number" >-<input type="number" ></td>
		<td>
			<form:errors path="employee_id" element="span" cssClass="error" />
		</td>
	</tr>
</table>

<input type="submit" value="가입하기"> <input type="reset" value="취소">

</form:form>

</div>



</body>
</html>
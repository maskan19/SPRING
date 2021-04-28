<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<style type="text/css">
.error {
	color: red;
}
</style>
	<h4>가입양식</h4>
	<form:form commandName="member" method="post" id="memberForm" enctype="multipart/form-data">
		<table>
			<c:if test='${"update" ne command }'>
				<tr>
					<th>회원아이디</th>
					<td><input type="text" name="mem_id" 
						value="${member.mem_id }" />
						<form:errors path="mem_id" element="span" cssClass="error" />
						<button type="button" id="idCheck">아이디중복체크</button>
					</td>
				</tr>
			</c:if>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="mem_pass"  />
				<form:errors path="mem_pass" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="mem_name" 
					value="${member.mem_name }" />
				<form:errors path="mem_name" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>프로필</th>
				<td>
					<input type="file" name="mem_image" accept="image/*"/>
				</td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" name="mem_regno1"
					value="${member.mem_regno1 }" />
				<form:errors path="mem_regno1" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" name="mem_regno2"
					value="${member.mem_regno2 }" />
				<form:errors path="mem_regno2" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" name="mem_bir"
					value="${member.mem_bir }" />
				<form:errors path="mem_bir" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="mem_zip" 
					value="${member.mem_zip }" />
				<form:errors path="mem_zip" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" name="mem_add1" 
					value="${member.mem_add1 }" />
				<form:errors path="mem_add1" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" name="mem_add2" 
					value="${member.mem_add2 }" />
				<form:errors path="mem_add2" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>집전번</th>
				<td><input type="text" name="mem_hometel"
					value="${member.mem_hometel }" />
				<form:errors path="mem_hometel" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>회사전번</th>
				<td><input type="text" name="mem_comtel"
					value="${member.mem_comtel }" />
				<form:errors path="mem_comtel" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="text" name="mem_hp"
					value="${member.mem_hp }" />
				<form:errors path="mem_hp" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="mem_mail" 
					value="${member.mem_mail }" />
				<form:errors path="mem_mail" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="mem_job"
					value="${member.mem_job }" />
				<form:errors path="mem_job" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="mem_like"
					value="${member.mem_like }" />
				<form:errors path="mem_like" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" name="mem_memorial"
					value="${member.mem_memorial }" />
				<form:errors path="mem_memorial" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="date" name="mem_memorialday"
					value="${member.mem_memorialday }" />
				<form:errors path="mem_memorialday" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>3000</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="저장" /></td>
			</tr>
		</table>
	</form:form>
<c:if test="${'update' ne command }">
	<script type="text/javascript" src="${cPath }/js/member/memberForm.js"></script>
</c:if>	


package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validator.constraint.IDReg;
import kr.or.ddit.validator.constraint.PWReg;
import kr.or.ddit.validator.constraint.TelephoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "employee_id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude="file")
public class EmployeeVO implements Serializable{
	
	private transient MultipartFile file;

	private int rnum;
	@NotBlank(message="{NotBlank.kr.or.ddit.vo.MemberVO.mem_pass.message}")
	@Size(max = 13, min = 4, message="{Size.kr.or.ddit.vo.MemberVO.mem_pass.message}")
	@IDReg
	private String employee_id;
	@NotBlank(message = "{NotBlank.kr.or.ddit.vo.MemberVO.mem_pass.message}")
	@Size(max = 13, min = 6, message = "{Size.kr.or.ddit.vo.MemberVO.mem_pass.message}")
	@PWReg
	private String employee_pwd;
	private String employee_authority;
	private String employee_picture;
	private String employee_name;
	@NotBlank(message="{NotBlank.kr.or.ddit.vo.MemberVO.mem_pass.message}")
	@TelephoneNumber
	private String employee_phone;
	@Email
	private String employee_email;
	
	public void saveTo(File saveFolder) throws IOException {
		file.transferTo(new File(saveFolder, employee_picture));
	}
	
}

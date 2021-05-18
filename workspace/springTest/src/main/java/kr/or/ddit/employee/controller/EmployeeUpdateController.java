package kr.or.ddit.employee.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.employee.service.IEmployeeService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class EmployeeUpdateController {
	@Inject
	private IEmployeeService service;
	
	
	@RequestMapping("/member/memberUpdate.do")
	public String updateForm(
			@RequestParam("who") String employee_id
			, Model model
			){
		EmployeeVO authEmp = service.retrieveEmp(EmployeeVO.builder().employee_id(employee_id).build());
//		String authId = authEmp.getEmployee_id();
 		model.addAttribute("emp", authEmp);
 		return "employee/registForm";
	}
	
	@RequestMapping(value="/member/memberUpdate.do", method=RequestMethod.POST)
	public String doPost(
			@Validated(UpdateGroup.class) @ModelAttribute("emp") EmployeeVO empVO
			, Errors errors
			, Model model
	){
		
		String view = null;
		String message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.modifyEmp(empVO);
			switch (result) {
			case OK:
				view = "redirect:/mypage.do";
				break;
			default: //fail
				message = "서버 오류, 잠시 뒤 다시 시도하세요.";
				view = "employee/registForm";
				break;
			}
		} else {
			// 에러 있음
			view = "employee/registForm";
		}

		model.addAttribute("message", message);

		return view;
	}
}

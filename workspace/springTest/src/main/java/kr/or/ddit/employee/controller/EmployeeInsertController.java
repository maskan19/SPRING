package kr.or.ddit.employee.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.employee.service.IEmployeeService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;
@Controller
public class EmployeeInsertController {
	@Inject
	private IEmployeeService service;
	
	@RequestMapping("/employee/registForm.do")
	public String form(){
		return "employee/registForm";
	}
	
	@RequestMapping(value="/employee/registForm.do", method=RequestMethod.POST)
	public String process(
//			@Validated(InsertGroup.class) 
			@ModelAttribute("member") EmployeeVO empVO
			, BindingResult errors
			, Model model) throws IOException {
		
		String view = null;
		String message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.enrollEmp(empVO);
			switch (result) {
			case PKDUPLICATED:
				view = "employee/registForm";
				message = "아이디 중복";
				break;
			case OK:
				view = "redirect:/employee/registForm.jsp";
				break;
			default://fail
				message = "서버 오류, 잠시 뒤 다시 시도하세요.";
				view = "employee/registForm";
				break;
			}
		} else {
			// 검증 불통
			view = "employee/registForm";
		}

		model.addAttribute("message", message);

		return view;
	}
	
	@RequestMapping(value="/employee/idCheck.do"
			, method=RequestMethod.POST
			, produces = MediaType.APPLICATION_JSON_UTF8_VALUE 
	)
	@ResponseBody
	public Map<String, Object> idCheck(@RequestParam("employee_id") String employee_id){
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			service.retrieveEmp(EmployeeVO.builder().employee_id(employee_id).build());
			resultMap.put("result", ServiceResult.FAIL);
		}catch (Exception e) {
			resultMap.put("result", ServiceResult.OK);
		}
		return resultMap;
	}
	
}

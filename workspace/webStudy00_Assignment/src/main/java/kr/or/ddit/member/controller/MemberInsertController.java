package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberInsertController {
	private IMemberService service = new MemberServiceImpl();

	@RequestMapping(value = "/member/idCheck.do", method = RequestMethod.POST)
	public String idChecker(@RequestParam("id") String mem_id, HttpServletResponse resp)
			throws ServletException, IOException {

		Map<String, Object> resultMap = new HashMap<>();
		try {
			service.retrieveMember(mem_id);
			resultMap.put("result", ServiceResult.FAIL);
		} catch (Exception e) {
			resultMap.put("result", ServiceResult.OK);
		}

		resp.setContentType(MimeType.JSON.getMime());
		try (PrintWriter out = resp.getWriter();) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, resultMap);
		}
		return null;
	}

	@RequestMapping("/member/memberInsert.do")
	public String memberInsertForm() {
		return "member/memberForm";
	}

	@RequestMapping(value = "/member/memberInsert.do", method = RequestMethod.POST)
	public String memberInsert(@ModelAttribute("member") MemberVO member, HttpServletRequest req, HttpServletResponse resp) {

//		2. 검증
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);

		boolean valid = new CommonValidator<MemberVO>().validate(member, errors, InsertGroup.class);

		String view = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:// forward
				view = "member/memberForm";
				message = "아이디 중복";
				break;
			case OK: // redirect
				view = "redirect:/login/loginForm";
				break;
			default:
				message = "서버 오류, 잠시 뒤 다시 시도하세요.";
				view = "member/memberForm";
				break;
			}
		} else {
			// 검증 불통
			view = "member/memberForm";
		}

		req.setAttribute("message", message);
		// 중복 코드 발생
		return view;
	}

}

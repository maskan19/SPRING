package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberInsertController{
	private IMemberService service = new MemberServiceImpl();

	@RequestMapping("/member/memberInsert.do")
	public String form(){
		return "member/memberForm";
	}

	@RequestMapping(value="/member/memberInsert.do", method=RequestMethod.POST)
	public String process(
			@ModelAttribute("member") MemberVO member
			, @RequestPart(value="mem_image", required=false) MultipartFile mem_image
			, HttpServletRequest req) throws IOException {
//		Locale.setDefault(Locale.ENGLISH);
//		1. 요청 접수

		if(mem_image!=null && !mem_image.isEmpty()) {
			String mime = mem_image.getContentType();
			if(!mime.startsWith("image/")) {
				throw new BadRequestException("이미지 이외의 프로필은 처리 불가.");
			}
			byte[] mem_img = mem_image.getBytes();
			member.setMem_img(mem_img);
		}	
//		2. 검증
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = 
				new CommonValidator<MemberVO>()
						.validate(member, errors, InsertGroup.class);
		
//		boolean valid = validate(member, errors);
		
		String view = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				view = "member/memberForm";
				message = "아이디 중복";
				break;
			case OK:
				view = "redirect:/login/loginForm.jsp";
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

		return view;
	}

}

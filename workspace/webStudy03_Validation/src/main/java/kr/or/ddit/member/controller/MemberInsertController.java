package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
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
	public String memberInsert(@ModelAttribute("member") MemberVO member,
			@RequestPart(value = "mem_image", required = false) MultipartFile mem_image, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

//		2. 검증
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);

		if (mem_image != null && !mem_image.isEmpty()) {
			String mime = mem_image.getContentType();
			if (!mime.startsWith("image/")) {
				throw new BadRequestException("이미지 파일만 사용할 수 있습니다.");
			}
			byte[] mem_img = mem_image.getBytes();
			member.setMem_img(mem_img);
		}

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
		// 중복 코드 발생
		return view;
	}

}

package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberInsertController {
	private IMemberService service = new MemberServiceImpl();

	@RequestMapping(value = "/member/idCheck.do", method = RequestMethod.POST)
	public String idChecker(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String mem_id = req.getParameter("id");
		if (mem_id == null || mem_id.isEmpty()) {
			resp.sendError(400);
		}

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
	public String form(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "member/memberForm";
		return view;
	}

	@RequestMapping(value = "/member/memberInsert.do", method = RequestMethod.POST)
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		1. 요청 접수
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
//		member.setMem_id(req.getParameter("mem_id"));
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e); // e parameter를 꼭 넘겨야한다.
		} // 속도가 느림
//		2. 검증
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);

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

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if (member.getMem_id() == null || member.getMem_id().isEmpty()) {
			valid = false;
			errors.put("mem_id", "회원아이디 누락");
		}
		if (member.getMem_pass() == null || member.getMem_pass().isEmpty()) {
			valid = false;
			errors.put("mem_pass", "비밀번호 누락");
		}
		if (member.getMem_name() == null || member.getMem_name().isEmpty()) {
			valid = false;
			errors.put("mem_name", "이름 누락");
		}
		if (member.getMem_zip() == null || member.getMem_zip().isEmpty()) {
			valid = false;
			errors.put("mem_zip", "우편번호 누락");
		}
		if (member.getMem_add1() == null || member.getMem_add1().isEmpty()) {
			valid = false;
			errors.put("mem_add1", "주소1 누락");
		}
		if (member.getMem_add2() == null || member.getMem_add2().isEmpty()) {
			valid = false;
			errors.put("mem_add2", "주소2 누락");
		}
		if (member.getMem_mail() == null || member.getMem_mail().isEmpty()) {
			valid = false;
			errors.put("mem_mail", "이메일 누락");
		}

		return valid;
	}
}

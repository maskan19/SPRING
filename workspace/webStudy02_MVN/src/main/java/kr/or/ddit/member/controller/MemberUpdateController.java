package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberUpdateController extends HttpServlet {
	private IMemberService service = new MemberServiceImpl();

	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "update");
	}

	@RequestMapping("/member/memberUpdate.do")
	public String memberUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addCommandAttribute(req);
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String authId = authMember.getMem_id();
		MemberVO member = service.retrieveMember(authId);
		req.setAttribute("member", member);
		String view = "member/memberForm";
		return view;
//		memberForm.jsp 재활용
	}

	@RequestMapping(method=RequestMethod.POST, value="/member/memberUpdate.do")
	public String memberUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addCommandAttribute(req);

		req.setCharacterEncoding("UTF-8");

//		1. 요청 접수
		MemberVO member = new MemberVO();
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String authId = authMember.getMem_id();
		member.setMem_id(authId);
		req.setAttribute("member", member);
//		member.setMem_id(req.getParameter("mem_id"));
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
//		2. 검증
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
		String view = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				view = "views/member/memberForm";
				message = "비번 오류";
				break;
			case OK:
				view = "redirect:/mypage.do";
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

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
//		if (member.getMem_id() == null || member.getMem_id().isEmpty()) {
//			valid = false;
//			errors.put("mem_id", "회원아이디 누락");
//		}
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

package kr.or.ddit.member.controller;

import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController {
	private IMemberService service = new MemberServiceImpl();
//	객체는 상태(전역변수)와 행동(메소드?)으로 구성

	@RequestMapping(value = "/member/memberDelete.do", method = RequestMethod.POST)
	public String memberDelete(@RequestParam("password") String password, HttpSession session) {
//		1. 요청 접수
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String authId = authMember.getMem_id();
		ServiceResult result = service.removeMember(new MemberVO(authId, password));
		String view = null;
//		현재 리퀘스트 정보를 보존하지 않음
		switch (result) {
		case INVALIDPASSWORD:
			view = "redirect:/mypage.do";
			session.setAttribute("message", "비번 오류");
			break;
		case OK:
			session.invalidate();
			view = "redirect:/";
			break;
		default:
			view = "redirect:/prod/prodView.do";
			session.setAttribute("message", "서버 오류");
			break;
		}
		return view;
	}
}

package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteServlet extends HttpServlet{
	
	private IMemberService service = new MemberServiceImpl();
	
	//객체는 상태(전역변수)와 행동(메소드?)으로 구성
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
//		1. 요청 접수
		String password = req.getParameter("password");
		if(password==null || password.isEmpty()) {
			resp.sendError(400);
			return;
		}
		HttpSession session = req.getSession();
		MemberVO authmember =  (MemberVO) session.getAttribute("authMember");
		ServiceResult result = service.removeMember(new MemberVO(authmember.getMem_id(), authmember.getMem_pass()));
		String view = null;
		
		
		//현재 리퀘스트 정보를 보존하지 않음
			switch (result) {
			case INVALIDPASSWORD:
				view = "redirect:/mypage.do";
				session.setAttribute("message",  "비번 오류");
				break;
			case OK:
				session.invalidate();
				view = "redirect:/";
				break;
			default:
				view = "redirect:/mypage.do";
				session.setAttribute("message",  "서버 오류");
				break;
			}

		boolean redirect = view.startsWith("redirect:");
		if (redirect) {
			view = view.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + view);
		} else {
			req.getRequestDispatcher(view).forward(req, resp);
		}
	}

}

package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage.do")
public class MypageServlet extends HttpServlet {
	IMemberService service = new MemberServiceImpl();
	IAuthenticateService authService = new AuthenticateServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/WEB-INF/views/member/passwordForm.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_pass = req.getParameter("mem_pass");
		if (mem_pass == null || mem_pass.isEmpty()) {
			resp.sendError(400);
			return;
		}
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String mem_id = authMember.getMem_id();

		ServiceResult result = authService.authenticate(new MemberVO(mem_id, mem_pass));
		String view = null;
		if (ServiceResult.OK.equals(result)) {
			MemberVO detailMember = service.retrieveMember(mem_id);

			req.setAttribute("member", detailMember);
			view = "/WEB-INF/views/member/mypage.jsp";
		} else {
			session.setAttribute("message", "비번 오류");
			view = "redirect:/mypage.do";
		}

		boolean redirect = view.startsWith("redirect:");
		if (redirect) {
			view = view.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + view);
		} else {
			req.getRequestDispatcher(view).forward(req, resp);
			// dispatch 로 가지고 다니는 이유
			// 인증은 늘 redirect
		}
	}
}

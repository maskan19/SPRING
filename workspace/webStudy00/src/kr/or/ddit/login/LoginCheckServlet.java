package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/loginCheck.do")
public class LoginCheckServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(400);
			return;
		}
		//		요청 분석
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		String view = null;
		boolean redirect = false;
		String message = null;
		boolean valid = validate(mem_id, mem_pass);
		if(valid) {
//		인증(id==password)
			boolean auth = authenticate(mem_id, mem_pass);	
			if(auth) {
//		인증 성공시 index.jsp 로 이동(현재 요청 정보 삭제).
				redirect = true;
				view = "/";
				session.setAttribute("authId", mem_id);
			}else {
//		인증 실패시 loginForm.jsp 로 이동
				redirect = true;
				view = "/login/loginForm.jsp";
//			2) 인증 실패(아이디 상태 유지)
				message = "비번 오류";
				session.setAttribute("failedId", mem_id);
			}
		}else {
//			1) 검증
			redirect = true;
			view = "/login/loginForm.jsp";
			message = "아이디나 비번 누락";
		}
		
//		view 로 이동
		if(redirect) {
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(req.getContextPath() + view);
		}else {
			req.setAttribute("message", message);
			req.getRequestDispatcher(view).forward(req, resp);
		}
		
	}

	private boolean authenticate(String mem_id, String mem_pass) {
		return mem_id.equals(mem_pass);
	}

	private boolean validate(String mem_id, String mem_pass) {
		boolean valid = true;
		valid = !(mem_id==null || mem_id.isEmpty() ||
				mem_pass==null || mem_pass.isEmpty());
		return valid;
	}
}


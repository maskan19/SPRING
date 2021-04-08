package kr.or.ddit.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginCheckServlet {
	private IAuthenticateService service = new AuthenticateServiceImpl();
	private static final Logger logger = LoggerFactory.getLogger(LoginCheckServlet.class);

	@RequestMapping(method=RequestMethod.POST, value="/login/loginCheck.do")
	public String  loginCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.isNew()) {
			resp.sendError(400);
			return null;
		}
		// 요청 분석
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		String saveId = req.getParameter("saveId");
		String view = null;
		String message = null;
		boolean valid = validate(mem_id, mem_pass);
		if (valid) {
//		인증(id==password)
			MemberVO member = new MemberVO(mem_id, mem_pass);
			ServiceResult result = service.authenticate(member);
			logger.debug("인증전 Memervo");
			switch (result) {
			case OK:
				view = "redirect:/";
				session.setAttribute("authMember", member);
				Cookie idCookie = new Cookie("idCookie", mem_id);
				idCookie.setPath(req.getContextPath());
				int maxAge = 0;
				if ("saveId".equals(saveId)) {
					maxAge = 60 * 60 * 24 * 7;
				}
				idCookie.setMaxAge(maxAge);
				resp.addCookie(idCookie);
				break;
			case NOTEXIST:
				view = "redirect:/login/loginForm";
				message = "아이디 오류";
				break;
			case INVALIDPASSWORD:
				view = "redirect:/login/loginForm";
//			2) 인증 실패(아이디 상태 유지)
				message = "비번 오류";
				session.setAttribute("failedId", mem_id);
				break;
			}
		} else {
//			1) 검증
			view = "redirect:/login/loginForm";
			message = "아이디나 비번 누락";
		}

		req.getSession().setAttribute("message", message);
		
		return view;

	}

	private boolean validate(String mem_id, String mem_pass) {
		boolean valid = true;
		valid = !(mem_id == null || mem_id.isEmpty() || mem_pass == null || mem_pass.isEmpty());
		return valid;
	}
}

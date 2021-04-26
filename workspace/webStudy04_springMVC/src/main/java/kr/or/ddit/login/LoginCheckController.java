package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginCheckController{
	private static final Logger logger =
			LoggerFactory.getLogger(LoginCheckController.class);
	private IAuthenticateService service =
			new AuthenticateServiceImpl();
	
	@RequestMapping(value="/login/loginCheck.do", method=RequestMethod.POST)
	public String doPost(
			@RequestParam(value="mem_id", required=true) String mem_id
			, @RequestParam(value="mem_pass", required=true) String mem_pass
			, @RequestParam(value="saveId", required=false) String saveId
			, HttpServletRequest req
			, HttpServletResponse resp
			, HttpSession session) throws ServletException, IOException {
		if(session.isNew()) {
			resp.sendError(400);
			return null;
		}
		//		요청 분석
		String view = null;
		
		String message = null;
		boolean valid = validate(mem_id, mem_pass);
		if(valid) {
//		인증(id==password)
			MemberVO member = new MemberVO(mem_id, mem_pass);
			if(logger.isDebugEnabled())
				logger.debug("인증전 MemberVO : {}", member);
			ServiceResult result = service.authenticate(member);
			switch (result) {
			case OK:
				if(logger.isInfoEnabled())
					logger.info("인증후 MemberVO : {}", member);
				view = "redirect:/";
				session.setAttribute("authMember", member);
				Cookie idCookie = new Cookie("idCookie", mem_id);
				idCookie.setPath(req.getContextPath());
				int maxAge = 0;
				if("saveId".equals(saveId)) {
					maxAge = 60*60*24*7;
				}
				idCookie.setMaxAge(maxAge);
				resp.addCookie(idCookie);
				break;
			case NOTEXIST:
				view = "redirect:/login/loginForm.jsp";
				message = "아이디 오류";
				break;
			case INVALIDPASSWORD:
				view = "redirect:/login/loginForm.jsp";
//			2) 인증 실패(아이디 상태 유지)
				message = "비번 오류";
				session.setAttribute("failedId", mem_id);
				break;
			}
		}else {
//			1) 검증
			view = "redirect:/login/loginForm.jsp";
			message = "아이디나 비번 누락";
		}
		
		session.setAttribute("message", message);
		
		return view;
	}


	private boolean validate(String mem_id, String mem_pass) {
		boolean valid = true;
		valid = !(mem_id==null || mem_id.isEmpty() ||
				mem_pass==null || mem_pass.isEmpty());
		return valid;
	}
}












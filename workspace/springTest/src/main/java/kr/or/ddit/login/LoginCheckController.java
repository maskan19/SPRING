package kr.or.ddit.login;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.employee.service.IAuthenticateService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.BadRequestException;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class LoginCheckController{
	private static final Logger logger =
			LoggerFactory.getLogger(LoginCheckController.class);
	
	@Inject
	private IAuthenticateService service;
	
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	@RequestMapping(value="/login/loginCheck.do", method=RequestMethod.POST)
	public String doPost(
			@ModelAttribute("emp") EmployeeVO empVO
			, @RequestParam(required=false) String savedId
			, HttpServletResponse resp
			, HttpSession session
			, RedirectAttributes redirectAttributes
			){
		if(session.isNew()) {
			throw new BadRequestException("비정상 세션");
		}
		String view = null;
		
		String message = null;

		//		요청 분석
//		if(logger.isDebugEnabled())	 logger.debug("인증전 MemberVO : {}", empVO);
		ServiceResult result = service.authenticate(empVO);
		switch (result) {
		case OK:
//			if(logger.isInfoEnabled())
//				logger.info("인증후 MemberVO : {}", empVO);
			view = "redirect:/employee/employeeList.do";
			session.setAttribute("authEmp", empVO);
			Cookie idCookie = new Cookie("idCookie", empVO.getEmployee_id());
			idCookie.setPath(application.getContextPath());
			int maxAge = 0;
			if("savedId".equals(savedId)) {
				maxAge = 60*60*24;
			}
			idCookie.setMaxAge(maxAge);
			resp.addCookie(idCookie);
			break;
		case NOTEXIST:
			view = "redirect:/index";
			message = "아이디 오류";
			break;
		case INVALIDPASSWORD:
			view = "redirect:/index";
//			2) 인증 실패(아이디 상태 유지)
			message = "비밀번호 오류";
			redirectAttributes.addFlashAttribute("failedId", empVO.getEmployee_id());
			break;
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return view;
	}
}












package kr.or.ddit.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.Constants;
import kr.or.ddit.vo.MemberVO;

@WebListener
public class CustomHttpSessionListener implements HttpSessionListener{
	private static final Logger logger = LoggerFactory.getLogger(CustomHttpSessionListener.class);
	
	//세션 이벤트
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		logger.info("세션 생성 : {}", session.getId());
		ServletContext application = session.getServletContext();
		int sessionCount = (Integer)application.getAttribute(Constants.SESSIONCOUNTATTRNAME);//객체 타입으로 캐스팅
		application.setAttribute(Constants.SESSIONCOUNTATTRNAME, sessionCount+1);
		logger.info("SESSIONCOUNTATTRNAME : {}", session.getAttribute(Constants.SESSIONCOUNTATTRNAME));
		
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		logger.info("세션 소멸 : {}", session.getId());
		ServletContext application = session.getServletContext();
		int sessionCount = (Integer)application.getAttribute(Constants.SESSIONCOUNTATTRNAME);//객체 타입으로 캐스팅
		application.setAttribute(Constants.SESSIONCOUNTATTRNAME, sessionCount-1);
		Set<String> userList = (Set<String>)application.getAttribute(Constants.USERLISTATTRNAME);
		if(session.getAttribute("authMember")!=null) {
			userList.remove(((MemberVO)session.getAttribute("authMember")).getMem_id());
		}
		application.setAttribute(Constants.USERLISTATTRNAME, userList);
	}

}

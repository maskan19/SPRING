package kr.or.ddit.listener;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.Constants;
import kr.or.ddit.vo.MemberVO;

/**
 * Application Lifecycle Listener implementation class CustomServletContextListener
 *
 */
//@WebListener //4 Since:Servlet 3.0

public class CustomServletContextListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(CustomServletContextListener.class);
	ServletContext application;
	// 3
	//처리할 수 있는 이벤트의 종류
	
	/**
	 * 어플리케이션의 시작
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce)  { //init
		logger.info("{}에서 어플리케이션 초기화 이벤트 처리", getClass().getName());
		application = sce.getServletContext();
		application.setAttribute(Constants.SESSIONCOUNTATTRNAME, 0);//초기 방문자수를 0으로 설정
		Set<MemberVO> userList = new LinkedHashSet<MemberVO>();
		application.setAttribute(Constants.USERLISTATTRNAME, userList);
		application.setAttribute("cPath", application.getContextPath());
	}

	/**
	 * 어플리케이션의 종료
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { //destroy
    	logger.info("{}에서 어플리케이션 소멸 이벤트 처리", getClass().getName());
    	application.setAttribute(Constants.SESSIONCOUNTATTRNAME, 0);//종료시 방문자수를 0으로 설정
    	Set<MemberVO> userList = new HashSet<MemberVO>();
		application.setAttribute(Constants.USERLISTATTRNAME, userList);
    }
	
}

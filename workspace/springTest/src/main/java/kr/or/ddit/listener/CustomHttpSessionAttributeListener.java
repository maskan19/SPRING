package kr.or.ddit.listener;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import kr.or.ddit.Constants;
import kr.or.ddit.vo.EmployeeVO;

/**
 * Application Lifecycle Listener implementation class CustomHttpSessionAttributeListener
 *
 */
//@WebListener
public class CustomHttpSessionAttributeListener implements HttpSessionAttributeListener {

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
        if("authMember".equals(event.getName())) {
        	EmployeeVO authMember = (EmployeeVO)event.getValue();
        	ServletContext application = event.getSession().getServletContext();
        	Set<EmployeeVO> userList = 
        			(Set) application.getAttribute(Constants.USERLISTATTRNAME);
        	userList.add(authMember);
        }
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
    	if("authMember".equals(event.getName())) {
    		EmployeeVO authMember = (EmployeeVO)event.getValue();
        	ServletContext application = event.getSession().getServletContext();
        	Set<EmployeeVO> userList = 
        			(Set) application.getAttribute(Constants.USERLISTATTRNAME);
        	userList.remove(authMember);
        }
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }
	
}

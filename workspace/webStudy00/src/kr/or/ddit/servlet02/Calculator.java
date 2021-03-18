package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/03/calculator")
public class Calculator extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nums = req.getParameter("image");// parameter빈 경우
		if (req.getParameter("num1") == null || req.getParameter("num1").isEmpty() || req.getParameter("num2") == null
				|| req.getParameter("num2").isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);/* 잘못된 접근입니다. */
			return;
		}
		
		if(isNumberic(req.getParameter("num1"))) {//숫자가 아닌 경우
			System.out.println("number");
			resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);/* 잘못된 접근입니다. */
			return;
		}
		
		

		req.setCharacterEncoding("utf-8");
		System.out.println(req.getParameter("num1"));
		req.getParameter("num2");
		req.getParameter("oop");

	}
	
	static boolean isNumberic(String s) {
		try {
	     	Double.parseDouble(s);
	    	return true;
	    } catch(NumberFormatException e) {
	    	return false;
	    }
	}
	
	
}

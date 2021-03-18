package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.OperatorType;

//@WebServlet("/03/calculator")
public class CalculatorServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("data Come!!!");
		String left_txt = req.getParameter("left");
		String right_txt = req.getParameter("right");
		String operator = req.getParameter("operator");
		
		// 필요 데이터가 빈 값으로 왔을 경우
		if(left_txt.equals("") || right_txt.equals("")) {
			System.out.println("빈값");
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);	// 클라이언트에게 상태코드를 전달한다. 400 페이지
			return; 
		}
		
		// 실수형이 아닐 경우
		if(!left_txt.contains(".") || !right_txt.contains(".")) {
			System.out.println("실수형 아님");
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}
		
		// 4칙연산 작업
		float left = Float.parseFloat(left_txt);
		float right = Float.parseFloat(right_txt);
		float result = 0;
//		if(operator.equals("+")) {
//			result = left + right;
//		} else if(operator.equals("-")) {
//			result = left - right;
//		} else if(operator.equals("*")) {
//			result = left * right;
//		} else if(operator.equals("/")) {
//			result = left / right;
//		}
		// enum 사용
//		System.out.println(Operator.getOperator(operator));
		
//		if(OperatorType.getOperator(operator).equals("+")) {
//			result = left + right;
//		} else if(OperatorType.getOperator(operator).equals("-")) {
//			result = left - right;
//		} else if(OperatorType.getOperator(operator).equals("*")) {
//			result = left * right;
//		} else if(OperatorType.getOperator(operator).equals("/")) {
//			result = left / right;
//		}
//		
//		int int_left = (int)left;
//		int int_right = (int)right;
//		int int_result = (int)result;
//		PrintWriter out = resp.getWriter();
//		out.println("<html><body>");
//		out.printf("<h3>%d %s %d = %d</h3>", int_left, OperatorType.getOperator(operator), int_right, int_result);
//		out.println("</body></html>");
		
	}

}

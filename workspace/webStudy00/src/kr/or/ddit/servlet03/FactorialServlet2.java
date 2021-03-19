package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.FactorialType;
import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.servlet03.view.JsonView;
import kr.or.ddit.servlet03.view.XmlView;
import kr.or.ddit.vo.FactorialVO;

/**
 * 
 * @author 답안
 *
 */
@WebServlet("/03/factorial2")
public class FactorialServlet2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/WEB-INF/views/factorialForm2.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}

	// RESTful URI - 행위에 대한 정보는 uri에 포함되지 않아야한다.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String single = req.getParameter("single");
		String accept = req.getHeader("accept");
		int status = 200;
		String message = null;
		String view = null;
		if (single == null || !single.matches("[0-9]{1,2}")) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "피연산자 확인");
			status = 400;
			message = "필수 파라미터 누락";
		} else {

			long op = Long.parseLong(single);
			try {
				long result = factorial(op);
				MimeType mime = MimeType.searchMimeType(accept);
				Map<String, Object> target = new HashMap<>();
				target.put("op",op);
				target.put("result",result);
				target.put("expression",String.format("%d!=%d", op, result));

				if (MimeType.JSON.equals(mime)) {
				resp.setHeader("Content-Type", "application/json; charser='utf-8'");
					new JsonView().mergeModelAndView(target, resp);

				} else {
					req.setAttribute("target", target);
					view = "/WEB-INF/views/factorialForm2.jsp";

				}

			} catch (IllegalArgumentException e) {
				// 0 혹은 음수가 들어왔다.
//				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "피연산자 확인");
				status = 400;
				message = "음수는 연산 불가";
			}
		}
		if (status == HttpServletResponse.SC_OK) {
			// 연산 성공 > 동기 / 비동기
			if(view!=null) {
				req.getRequestDispatcher(view).forward(req, resp);
			}else {
				resp.sendError(status, message);
			}
			

		} else {
			resp.sendError(status, message);
		}

//		응답 전송
	

	}

//	재귀호출(recursive calling) 
	private long factorial(long op) {
		if (op <= 0) {
			throw new IllegalArgumentException("양의 정수만 대상으로 연산 수행 가능");
		}
		if (op == 1) {
			return 1;
		} else {
			return op * factorial(op - 1);
		}
	}

	private int bindAndValidate(HttpServletRequest req) {
		int status = HttpServletResponse.SC_OK;
		String singleParam = req.getParameter("single");
		String factorialParam = req.getParameter("factorial");
		int single = 0;
		FactorialType factorial = null;
		if (singleParam == null || factorialParam == null) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		} else {
			try {
				single = Integer.parseInt(singleParam);
				factorial = FactorialType.valueOf(factorialParam.toUpperCase());
			} catch (IllegalArgumentException e) {
				status = HttpServletResponse.SC_BAD_REQUEST;
			}
		}

		if (status == HttpServletResponse.SC_OK) {
			FactorialVO vo = new FactorialVO(single, factorial);
			req.setAttribute("vo", vo);
		}

		return status;
	}

}

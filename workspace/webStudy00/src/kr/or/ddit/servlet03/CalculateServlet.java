package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.servlet03.view.JsonView;
import kr.or.ddit.servlet03.view.XmlView;
import kr.or.ddit.vo.CalculatorVO;

@WebServlet("/03/calculator2")
public class CalculateServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
//		요청 분석 (검증)
		int status = bindAndValidate(req);
		if (status != HttpServletResponse.SC_OK) {
			resp.sendError(status);
			return;
		}
//		연산
		CalculatorVO vo = (CalculatorVO) req.getAttribute("vo");
		OperatorType operator = vo.getOperator();
		double result = operator.operate(vo.getLeft(), vo.getRight());
		String expr = operator.expression(vo);
		vo.setResult(result);
		vo.setExpression(expr);
//		응답 전송 try with(){}로 리소스 자동 회수
		String accept = req.getHeader("accept");

		MimeType mime = MimeType.searchMimeType(accept);
		resp.setContentType(mime.getMime());

		StringBuffer respData = new StringBuffer();
		String view = null;

		switch (mime) {// 마샬링
		case JSON:
//			respData.append(String.format("{\"result\" : \"%s\"}", expr));
		new JsonView().mergeModelAndView(vo, resp);
			break;
		case XML:
//			respData.append(String.format("<result>%s</result>", expr));
			new XmlView().mergeModelAndView(vo, resp);
			break;
		case PLAIN:
			respData.append(expr);
			break;
		default:
			view = "/WEB-INF/views/calculatorView.jsp";
			break;
		}
		if (view != null) { //위임구조를 사용해야하므로  forward
			req.getRequestDispatcher(view).forward(req, resp);
		} else {
			try (PrintWriter out = resp.getWriter();) {
//			out.print(expr);
				out.print(respData);
			}
		}
	}

	private int bindAndValidate(HttpServletRequest req) {
		int status = HttpServletResponse.SC_OK;
		String leftParam = req.getParameter("left");// parameter빈 경우
		String rightParam = req.getParameter("right");// parameter빈 경우
		String operatorParam = req.getParameter("operator");// parameter빈 경우
		double left = -1;
		double right = -1;
		OperatorType operator = null;

		if (leftParam == null || rightParam == null || operatorParam == null) {
			status = HttpServletResponse.SC_BAD_REQUEST;/* 잘못된 접근입니다. */
		} else {
			try {
				left = Double.parseDouble(leftParam);
				right = Double.parseDouble(rightParam);
				operator = OperatorType.valueOf(operatorParam);
			} catch (IllegalArgumentException e) {
				status = HttpServletResponse.SC_BAD_REQUEST;/* 잘못된 접근입니다. */
			}
		}
		if (status == HttpServletResponse.SC_OK) {
			CalculatorVO vo = new CalculatorVO(left, right, operator);
			req.setAttribute("vo", vo);
		}
		return status;
	}
}

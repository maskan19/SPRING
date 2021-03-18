package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.servlet03.view.JsonView;
import kr.or.ddit.servlet03.view.XmlView;
import kr.or.ddit.vo.CalculateVO;

@WebServlet("/03/calculator")
public class CalculatorServlet2 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
//		요청분석(검증)
		int status = bindAndValidate(req);
		if(status != HttpServletResponse.SC_OK) {
			resp.sendError(status);
			return;
		}
//		연산
		// 데이터를 한 번에 전송 하기 위해 vo 객체 생성
		CalculateVO vo = (CalculateVO) req.getAttribute("vo");
		OperatorType operator = vo.getOperator();
		double result = operator.operate(vo.getLeft(), vo.getRight());
		String expr = operator.expression(vo);
		vo.setResult(result);
		vo.setExpression(expr);
		
//		응답 전송
		// 요청 받은 mime 값에 따른 응답 mime 결정하는 구간
		String accept = req.getHeader("accept");
		MimeType mimeType = MimeType.searchMimeType(accept);
		resp.setContentType(mimeType.getMime());
		
		StringBuffer respData = new StringBuffer();
		String view = null;
		switch (mimeType) {
		case JSON:
			// 마샬링 작업
			//respData.append(String.format("{\"result\":\"%s\"}", expr));
			new JsonView().mergeModelAndView(vo, resp);
			break;
		case XML:
			new XmlView().mergeModelAndView(vo, resp);
			break;
		case PLAIN:
			respData.append(expr);
			break;
		default:	// HTML
//			respData.append(
//				String.format("<p>%s</p>", expr)
//			);
			view = "/WEB-INF/views/calculatorView.jsp";
			break;
		}
		// 이 작업을 통해 모델 2 방식으로 변환
		if(view != null) {
			req.getRequestDispatcher(view).forward(req, resp);
		} else {
			try(
					PrintWriter out = resp.getWriter();	// 직렬화 과정이 됨 - JSON일때만
					){
				out.print(respData);
			}
		}
	}
	private int bindAndValidate(HttpServletRequest req) {
		int status = HttpServletResponse.SC_OK;
		String leftParam = req.getParameter("left");
		String rightParam = req.getParameter("right");
		String operatorParam = req.getParameter("operator");
		
		double left = -1;
		double right = -1;
		OperatorType operator = null;
		
		// 에러에 따른 상태코드 지정
		if(leftParam==null || rightParam==null || operatorParam == null) { // 빈 값인지 체크
			status = HttpServletResponse.SC_BAD_REQUEST;	// 클라이언트에게 상태코드를 전달한다. 400 페이지
		} else {
			try { // 실수인지 체크, 
				left = Double.parseDouble(leftParam);
				right = Double.parseDouble(rightParam);
				operator = OperatorType.valueOf(operatorParam);
			} catch (IllegalArgumentException e) {
				status = HttpServletResponse.SC_BAD_REQUEST;
			}
		}
		
		if(status == HttpServletResponse.SC_OK) {
			CalculateVO vo = new CalculateVO(left, right, operator);
			req.setAttribute("vo", vo);
		}
		return status;
	}

}

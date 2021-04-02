package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/03/factorial")
public class FactorialServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/WEB-INF/views/factorialForm.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
	
	//RESTful URI - 행위에 대한 정보는 uri에 포함되지 않아야한다.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		요청 분석(검증)
		int status = bindAndValidate(req);
		if(status!=HttpServletResponse.SC_OK) {
			resp.sendError(status);
			return;
		}
//		연산
		FactorialVO vo = (FactorialVO) req.getAttribute("vo");
		FactorialType factorial = vo.getFactorial();
		String expr = factorial.expression(vo);
		vo.setExpression(expr);
		
//		응답 전송
		String accept = req.getHeader("accept");
		MimeType mimeType = MimeType.searchMimeType(accept);
		resp.setContentType(mimeType.getMime());
		
		StringBuffer respData = new StringBuffer();
		String view = null;
		
		switch (mimeType) {
		case JSON:
			new JsonView().mergeModelAndView(vo, resp);
			break;
		case XML:
			new XmlView().mergeModelAndView(vo, resp);
			break;
		case PLAIN:
			respData.append(expr);
			break;
		default:
			view = "/WEB-INF/views/factorialForm.jsp";
			break;
		}
		if(view!=null) {
			req.getRequestDispatcher(view).forward(req, resp);
		}else {
			try (
					PrintWriter out = resp.getWriter();
					){
				out.print(respData);
			}
		}
		
	}

	private int bindAndValidate(HttpServletRequest req) {
		int status = HttpServletResponse.SC_OK;
		String singleParam = req.getParameter("single");
		String factorialParam = req.getParameter("factorial");
		int single = 0;
		FactorialType factorial = null;
		if(singleParam==null || factorialParam==null) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}else {
			try {
				single = Integer.parseInt(singleParam);
				factorial = FactorialType.valueOf(factorialParam.toUpperCase());
			} catch (IllegalArgumentException e) {
				status = HttpServletResponse.SC_BAD_REQUEST;
			}
		}
		
		if(status==HttpServletResponse.SC_OK) {
			FactorialVO vo = new FactorialVO(single, factorial);
			req.setAttribute("vo", vo);
		}
		
		return status;
	}
	
}

package kr.or.ddit;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DummyServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp); do계열 super코드는 405에러를 보냄
		resp.setContentType("text/plain; charser=utf-8");
//		DummyServlet.class
		URL url = this.getClass().getResource("/kr/or/ddit/dummy.xml");
		resp.getWriter().println("야ㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑ" + url);
	}

}

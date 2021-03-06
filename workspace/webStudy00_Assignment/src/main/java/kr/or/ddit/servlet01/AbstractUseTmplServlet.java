package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//template method pattern
public abstract class AbstractUseTmplServlet extends HttpServlet {
	protected ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		application = config.getServletContext();
	}
  
//	template method
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.service(req, resp);
//		0. mime 결정
		setContentType(resp);
//		1. tmpl 읽기
		StringBuffer tmplSrc = readTmpl(req);
//		2. 데이터 만들기
		makeData(req);
//		3. tmpl을 데이터로 치환
		StringBuffer html = replaceData(tmplSrc, req);
//		4. 응답을 전송
//		try~with resource: try catch의 발전형. try(close()가 가능한 객체){실행문}
		try (PrintWriter out = resp.getWriter();) {
			out.print(html);
		}

	}

	protected abstract void setContentType(HttpServletResponse resp);

	private StringBuffer replaceData(StringBuffer tmplSrc, HttpServletRequest req) {
		Pattern regex = Pattern.compile("%([a-zA-Z0-9_]+)%");
		Matcher matcher = regex.matcher(tmplSrc);
		StringBuffer html = new StringBuffer();
		while (matcher.find()) {
			String name = matcher.group(1);
			Object value = req.getAttribute(name);
			if (value != null) {
				matcher.appendReplacement(html, value.toString());
			}
		}
		matcher.appendTail(html);

		return html;
	}

//	hook method
	/**
	 * 
	 * @param req는 메소드 내에서 변형되어야한다.
	 */
	protected abstract void makeData(HttpServletRequest req);

//	hook method
	private StringBuffer readTmpl(HttpServletRequest req) throws IOException {
		String tmplPath = req.getServletPath();
		InputStream is = application.getResourceAsStream(tmplPath);
		InputStreamReader isr = new InputStreamReader(is); // 젠더. 변환용
		BufferedReader reader = new BufferedReader(isr);
		String temp = null;
		StringBuffer tmplSrc = new StringBuffer();
		while ((temp = reader.readLine()) != null) {
			tmplSrc.append(String.format("%s\n", temp));
		}
		return tmplSrc;
	}

}

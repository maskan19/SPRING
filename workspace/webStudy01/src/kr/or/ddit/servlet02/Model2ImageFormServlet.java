package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/02/imageForm.do") // 서버 사이드에선 상대경로를 사용 할 수 없다
// 상대 경로는 기준 위치가 필요한데 
// 현재 서블릿을 등록할 때의 기준 위치는 모른다. 그렇기 때문에 상대 경로를 해결 할 수 없다.
// 그렇기 때문에 앞에 '/' 친구를 꼭 써줘야한다.
public class Model2ImageFormServlet extends HttpServlet{
	private ServletContext application;	// 초기화 라이플 콜백이 돌았을 때 
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = config.getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Service 와 doGet 방식
		
		String folder = "D:\\contents\\images";
		File contents = new File(folder);
		String[] children = contents.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime != null && mime.startsWith("image/");
			}
		});
		
		req.setAttribute("children", children);
		// 절대경로 : 현재 컴포넌트가 가지고 있는 경로는 생략 가능
		String view = "/WEB-INF/views/imageForm.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
